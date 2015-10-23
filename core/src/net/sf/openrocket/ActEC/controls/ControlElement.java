package net.sf.openrocket.ActEC.controls;

import net.sf.openrocket.aerodynamics.FlightConditions;
import net.sf.openrocket.aerodynamics.WarningSet;

/**
 * ControlElement
 *
 * @author Chris Andre
 */

public abstract class ControlElement {
    protected double u = 0.0;
    protected String name;
    private double visRad = 0.01;

    public ControlElement(String name) {
        this.name = name;
    }

    public double getControl() {
        return u;
    }
    public void setControl(double u) {
        this.u = u;
    }
    public double getVisualRadius() {
        return visRad;
    }
    public void setVisualRadius(double radius) {
        visRad = radius;
    }
    public abstract double calculatePressureDragForce(FlightConditions conditions,
                                      double stagnationCD, double baseCD, WarningSet warnings);
}
