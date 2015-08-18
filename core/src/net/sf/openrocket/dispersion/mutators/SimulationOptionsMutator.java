package net.sf.openrocket.dispersion.mutators;

import net.sf.openrocket.simulation.SimulationOptions;

/*
Adapted from Bill Kuker's Dispersion project by Chris Andre
 */

public interface SimulationOptionsMutator extends Mutator {
	public void mutate(SimulationOptions op);
}
