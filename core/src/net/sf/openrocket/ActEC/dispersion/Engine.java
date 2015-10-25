package net.sf.openrocket.ActEC.dispersion;

import java.util.*;

import net.sf.openrocket.ActEC.flightcomputer.FlightComputer;
import net.sf.openrocket.document.OpenRocketDocument;
import net.sf.openrocket.document.Simulation;
import net.sf.openrocket.ActEC.flightcomputer.FlightComputerType;
import net.sf.openrocket.rocketcomponent.ComponentChangeEvent;
import net.sf.openrocket.simulation.SimulationStatus;
import net.sf.openrocket.simulation.exception.SimulationException;
import net.sf.openrocket.simulation.listeners.AbstractSimulationListener;

import net.sf.openrocket.ActEC.dispersion.mutators.Mutator;
import net.sf.openrocket.ActEC.dispersion.mutators.RocketMutator;
import net.sf.openrocket.ActEC.dispersion.mutators.SimulationConditionsMutator;
import net.sf.openrocket.ActEC.dispersion.mutators.SimulationOptionsMutator;
import net.sf.openrocket.util.ArrayList;
import net.sf.openrocket.util.ChangeSource;
import net.sf.openrocket.util.SafetyMutex;
import net.sf.openrocket.util.StateChangeListener;

/*
Adapted from Bill Kuker's Dispersion project by Chris Andre
 */

public class Engine implements ChangeSource {
	final OpenRocketDocument doc;
	int simulationNumber = 0;
	private List<EventListener> listeners = new ArrayList<EventListener>();
	int runCount = 100;
	FlightComputerType fctype;
	private SafetyMutex mutex = SafetyMutex.newInstance();
	final List<Mutator> mutators;
	final List<SampleListener> sampleListeners = new Vector<SampleListener>();

	Random r = new Random(0);

	public interface SampleListener {
		public void sampleSimulationComplete(Sample s);
	}

	public Engine(final OpenRocketDocument doc) {
		this.doc = doc;
		// Instantiate all available mutators
		mutators = new ArrayList<Mutator>();
	}

	@Override
	public final void addChangeListener(StateChangeListener l) {
		mutex.verify();
		listeners.add(l);
	}
	@Override
	public final void removeChangeListener(StateChangeListener l) {
		mutex.verify();
		listeners.remove(l);
	}

	protected void fireChangeEvent() {
		EventObject e = new EventObject(this);
		// Copy the list before iterating to prevent concurrent modification exceptions.
		EventListener[] ls = listeners.toArray(new EventListener[0]);
		for (EventListener l : ls) {
			if (l instanceof StateChangeListener) {
				((StateChangeListener) l).stateChanged(e);
			}
		}
	}

	public void addSimListener(final SampleListener l) {
		sampleListeners.add(l);
	}

	public int getRunCount() {
		return runCount;
	}
	public void setRunCount(int n) {
		runCount = n;
		fireChangeEvent();
	}

	public FlightComputerType getFlightComputerType() {
		return fctype;
	}
	public void setFlightComputerType(FlightComputerType type) {
		fctype = type;
		fireChangeEvent();
	}

	public int getSimulationNumber() { return simulationNumber; }
	public void setSimulationNumber(int simnum) { simulationNumber = simnum; }

	public void run() throws SimulationException {
		for (int i = 0; i < runCount; i++) {
			OpenRocketDocument doc = this.doc.copy();
			Simulation s = doc.getSimulation(simulationNumber).copy();

			for (Mutator m : mutators) {
				if (m instanceof RocketMutator) {
					((RocketMutator) m).mutate(doc.getRocket());
				} else if (m instanceof SimulationOptionsMutator) {
					((SimulationOptionsMutator) m).mutate(s.getOptions());
				}
			}
			FlightComputer fc = null;
			try {
				fc = fctype.getComputer();
			}
			catch(Exception e) {
				//TODO Low
			}
			AbstractSimulationListener mutatorListener = new AbstractSimulationListener() {
				public void startSimulation(SimulationStatus status) throws SimulationException {
					for (Mutator m : mutators) {
						if (m instanceof SimulationConditionsMutator) {
							((SimulationConditionsMutator) m).mutate(status.getSimulationConditions());
						}
					}
				}
			};

			s.getOptions().setRandomSeed(r.nextInt());
			s.simulate(mutatorListener, fc);

			Sample sim = new Sample(s);

			for (SampleListener l : sampleListeners)
				l.sampleSimulationComplete(sim);
		}
	}
}
