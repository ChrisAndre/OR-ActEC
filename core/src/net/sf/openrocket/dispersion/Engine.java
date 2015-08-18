package net.sf.openrocket.dispersion;

import java.util.List;
import java.util.Random;
import java.util.Vector;

import net.sf.openrocket.document.OpenRocketDocument;
import net.sf.openrocket.document.Simulation;
import net.sf.openrocket.flightcomputer.FlightComputerType;
import net.sf.openrocket.simulation.SimulationStatus;
import net.sf.openrocket.simulation.exception.SimulationException;
import net.sf.openrocket.simulation.listeners.AbstractSimulationListener;

import net.sf.openrocket.dispersion.mutators.Mutator;
import net.sf.openrocket.dispersion.mutators.RocketMutator;
import net.sf.openrocket.dispersion.mutators.SimulationConditionsMutator;
import net.sf.openrocket.dispersion.mutators.SimulationOptionsMutator;

/*
Adapted from Bill Kuker's Dispersion project by Chris Andre
 */

public class Engine {
	final OpenRocketDocument doc;
	final int simulationNumber;

	int runCount;
	FlightComputerType fctype;

	final List<Mutator> mutators;
	final List<SampleListener> sampleListeners = new Vector<SampleListener>();

	Random r = new Random(0);

	public interface SampleListener {
		public void sampleSimulationComplete(Sample s);
	}

	public Engine(final OpenRocketDocument doc, final int simulationNumber, final List<Mutator> mutators) {
		this.doc = doc.copy();
		this.simulationNumber = simulationNumber;
		this.mutators = mutators;
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

	public void run(final int iterations) throws SimulationException {
		for (int i = 0; i < iterations; i++) {
			OpenRocketDocument doc = this.doc.copy();
			Simulation s = doc.getSimulation(simulationNumber).copy();

			for (Mutator m : mutators) {
				if (m instanceof RocketMutator) {
					((RocketMutator) m).mutate(doc.getRocket());
				} else if (m instanceof SimulationOptionsMutator) {
					((SimulationOptionsMutator) m).mutate(s.getOptions());
				}
			}

			s.getOptions().setRandomSeed(r.nextInt());

			s.simulate(new AbstractSimulationListener() {
				public void startSimulation(SimulationStatus status) throws SimulationException {
					for (Mutator m : mutators) {
						if (m instanceof SimulationConditionsMutator) {
							((SimulationConditionsMutator) m).mutate(status.getSimulationConditions());
						}
					}
				}
			});

			Sample sim = new Sample(s);

			for (SampleListener l : sampleListeners)
				l.sampleSimulationComplete(sim);
		}
	}
}
