package net.sf.openrocket.ActEC.controls;

import net.sf.openrocket.aerodynamics.FlightConditions;
import net.sf.openrocket.aerodynamics.WarningSet;

/**
 * SimpleFlatPlateElement
 *
 * @author Chris Andre
 */

public class SimpleFlatPlateElement extends ControlElement {
    protected double area;
    public SimpleFlatPlateElement(String name, double area) {
        super(name);
        this.area = area;
    }
    public SimpleFlatPlateElement(String name) {
        this(name, 0.09*0.09);
    }

    public double getArea() {
        return area;
    }
    public void setArea(double area) {
        this.area = area;
    }

    @Override
    public double calculatePressureDragForce(FlightConditions conditions,
                                                      double stagnationCD, double baseCD, WarningSet warnings) {
        double extArea = area * u;
        double effectArea = extArea * (1.0 - Math.pow(conditions.getSinAOA(), 2.0));
        return (stagnationCD + baseCD) * effectArea / conditions.getRefArea();
    }
}
