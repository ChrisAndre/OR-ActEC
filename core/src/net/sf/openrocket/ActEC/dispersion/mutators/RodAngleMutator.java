package net.sf.openrocket.ActEC.dispersion.mutators;

import net.sf.openrocket.simulation.SimulationOptions;

/*
Adapted from Bill Kuker's Dispersion project by Chris Andre
 */

public class RodAngleMutator implements SimulationOptionsMutator {

	private final Number dAngle;
	private final Number dDirection;

	public RodAngleMutator(Number dAngle, Number dDirection) {
		super();
		this.dAngle = dAngle;
		this.dDirection = dDirection;
	}

	@Override
	public void mutate(SimulationOptions op) {
		// TODO CRITICAL This is usless and incorrect
		// this code not reasonable when the rod is pointed nearly straight up,
		// because the area it varies the angle in becomes wedge shaped.

		op.setLaunchRodAngle(op.getLaunchRodAngle() + dAngle.doubleValue());
		op.setLaunchRodDirection(op.getLaunchRodDirection() + dDirection.doubleValue());
	}

}
