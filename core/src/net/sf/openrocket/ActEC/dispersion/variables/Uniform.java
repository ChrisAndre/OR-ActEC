package net.sf.openrocket.ActEC.dispersion.variables;

/*
Adapted from Bill Kuker's Dispersion project by Chris Andre
 */

public class Uniform extends Variable {
	private static final long serialVersionUID = 1L;
	private final Number min;
	private final Number max;

	public Uniform(final Number min, final Number max) {
		this.min = min;
		this.max = max;
	}

	@Override
	public double doubleValue() {
		return randomDouble() * (max.doubleValue() - min.doubleValue()) + min.doubleValue();
	}

}
