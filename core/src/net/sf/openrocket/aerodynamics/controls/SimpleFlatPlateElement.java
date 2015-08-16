package net.sf.openrocket.aerodynamics.controls;

import net.sf.openrocket.aerodynamics.controls.ControlElement;
import net.sf.openrocket.aerodynamics.FlightConditions;
import net.sf.openrocket.aerodynamics.WarningSet;
import net.sf.openrocket.util.Coordinate;

/**
 * SimpleFlatPlateElement
 *
 * @author Chris Andre
 */

public class SimpleFlatPlateElement extends ControlElement {
    protected double width, height;
    public SimpleFlatPlateElement(double w, double h) {
        width = w;
        height = h;
        u = 0.0;
    }
    @Override
    public double calculatePressureDragForce(FlightConditions conditions,
                                                      double stagnationCD, double baseCD, WarningSet warnings) {
        double extArea = width * height * u;
        double effectArea = extArea * Math.sqrt(1.0 - Math.pow(conditions.getSinAOA(), 2.0));
        return stagnationCD * effectArea / conditions.getRefArea();
    }
}
