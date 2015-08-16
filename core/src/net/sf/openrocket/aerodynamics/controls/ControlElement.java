package net.sf.openrocket.aerodynamics.controls;

import net.sf.openrocket.aerodynamics.FlightConditions;
import net.sf.openrocket.aerodynamics.WarningSet;
import net.sf.openrocket.util.Coordinate;

/**
 * ControlElement
 *
 * @author Chris Andre
 */

public abstract class ControlElement {
    protected double u;
    public void setControl(double u) {
        this.u = u;
    }
    public double getControl() {
        return u;
    }
    public abstract double calculatePressureDragForce(FlightConditions conditions,
                                      double stagnationCD, double baseCD, WarningSet warnings);
}
