package net.sf.openrocket.ActEC.controls;

import net.sf.openrocket.aerodynamics.FlightConditions;
import net.sf.openrocket.aerodynamics.WarningSet;

public class InactiveElement extends ControlElement {
    public InactiveElement(String name) {
        super(name);
    }
    @Override
    public double calculatePressureDragForce(FlightConditions conditions,
                                                      double stagnationCD, double baseCD, WarningSet warnings) {
        return 0.0;
    }
}
