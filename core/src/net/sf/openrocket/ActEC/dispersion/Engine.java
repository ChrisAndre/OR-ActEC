package net.sf.openrocket.ActEC.dispersion;

import java.util.List;
import java.util.Random;
import java.util.Vector;

import net.sf.openrocket.ActEC.flightcomputer.FlightComputer;
import net.sf.openrocket.document.OpenRocketDocument;
import net.sf.openrocket.document.Simulation;
import net.sf.openrocket.ActEC.flightcomputer.FlightComputerType;
import net.sf.openrocket.simulation.SimulationStatus;
import net.sf.openrocket.simulation.exception.SimulationException;
import net.sf.openrocket.simulation.listeners.AbstractSimulationListener;

import net.sf.openrocket.ActEC.dispersion.mutators.Mutator;
import net.sf.openrocket.ActEC.dispersion.mutators.RocketMutator;
import net.sf.openrocket.ActEC.dispersion.mutators.SimulationConditionsMutator;
import net.sf.openrocket.ActEC.dispersion.mutators.SimulationOptionsMutator;
import net.sf.openrocket.util.ArrayList;

/*
Adapted from Bill Kuker's Dispersion project by Chris Andre
 */

public class Engine {
	final OpenRocketDocument doc;
	int simulationNumber = 0;
	int runNumber = 1000;

	int runCount;
	FlightComputerType fctype;

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

	public void addSimListener(final SampleListener l) {
		sampleListeners.add(l);
	}

	public int getRunCount() {
		return runCount;
	}
	public void setRunCount(int n) {
		runCount = n;
	}

	public FlightComputerType getFlightComputerType() {
		return fctype;
	}
	public void setFlightComputerType(FlightComputerType type) {
		fctype = type;
	}

	public int getSimulationNumber() { return simulationNumber; }
	public void setSimulationNumber(int simnum) { simulationNumber = simnum; }

	public int getRunNumber() { return runNumber; }
	public void setRunNumber(int runnum) { runNumber = runnum; }

	public void run() throws SimulationException {
		for (int i = 0; i < runNumber; i++) {
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
			s.simulate(mutatorListener, fc); // TODO high: the FC cannot set control values because the document is not updated...

			Sample sim = new Sample(s);

			for (SampleListener l : sampleListeners)
				l.sampleSimulationComplete(sim);
		}
	}
}
