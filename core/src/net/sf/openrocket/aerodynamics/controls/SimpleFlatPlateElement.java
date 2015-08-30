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
    protected double area;
    public SimpleFlatPlateElement(double area) {
        this.area = area;
        u = 0.0;
    }
    public SimpleFlatPlateElement() {
        this(0.09*0.09);
    }
    @Override
    public double calculatePressureDragForce(FlightConditions conditions,
                                                      double stagnationCD, double baseCD, WarningSet warnings) {
        double extArea = area * u;
        double effectArea = extArea * (1.0 - Math.pow(conditions.getSinAOA(), 2.0));
        return (stagnationCD + baseCD) * effectArea / conditions.getRefArea();
    }
}
