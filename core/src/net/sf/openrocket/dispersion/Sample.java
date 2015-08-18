package net.sf.openrocket.dispersion;

import net.sf.openrocket.document.Simulation;

/*
Adapted from Bill Kuker's Dispersion project by Chris Andre
 */

public class Sample {
	private final Simulation s;

	Sample(final Simulation s) {
		this.s = s;
	}

	public Simulation getSimulation() {
		return s;
	}
}