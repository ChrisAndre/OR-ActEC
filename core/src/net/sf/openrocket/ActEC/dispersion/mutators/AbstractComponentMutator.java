package net.sf.openrocket.ActEC.dispersion.mutators;

import net.sf.openrocket.rocketcomponent.Rocket;
import net.sf.openrocket.rocketcomponent.RocketComponent;

/*
Adapted from Bill Kuker's Dispersion project by Chris Andre
 */

public abstract class AbstractComponentMutator implements RocketMutator {
	public void mutate(Rocket r) {
		traverse(r);
	}

	private void traverse(final RocketComponent p) {
		mutate(p);
		for (RocketComponent c : p.getChildren()) {
			traverse(c);
		}
	}

	protected abstract void mutate(final RocketComponent c);
}
