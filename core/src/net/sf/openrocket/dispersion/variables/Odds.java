package net.sf.openrocket.dispersion.variables;

/*
Adapted from Bill Kuker's Dispersion project by Chris Andre
 */

public class Odds {

	private final double pct;
	private final Uniform u = new Uniform(0, 1);

	public Odds(final double pct) {
		this.pct = pct;
	}

	public boolean occurs() {
		return u.doubleValue() <= pct;
	}
}
