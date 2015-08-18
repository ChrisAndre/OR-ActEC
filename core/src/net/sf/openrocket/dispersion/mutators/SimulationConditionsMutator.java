package net.sf.openrocket.dispersion.mutators;

import net.sf.openrocket.simulation.SimulationConditions;

/*
Adapted from Bill Kuker's Dispersion project by Chris Andre
 */

public interface SimulationConditionsMutator extends Mutator {
	public void mutate(SimulationConditions sc);
}
