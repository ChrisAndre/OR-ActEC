package net.sf.openrocket.dispersion.mutators;

import net.sf.openrocket.rocketcomponent.Rocket;

/*
Adapted from Bill Kuker's Dispersion project by Chris Andre
 */

public interface RocketMutator extends Mutator {
	public void mutate(Rocket r);
}
