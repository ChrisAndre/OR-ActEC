package net.sf.openrocket.ActEC.controls;

import net.sf.openrocket.aerodynamics.FlightConditions;
import net.sf.openrocket.aerodynamics.WarningSet;

/**
 * Created by chris on 8/16/15.
 */
public class DragPlateElement extends SimpleFlatPlateElement {
    public DragPlateElement(String name, double area) {
        super(name, area);
    }
    public DragPlateElement(String name) {
        this(name, 0.09*0.09);
    }
    @Override
    public double calculatePressureDragForce(FlightConditions conditions,
                                             double stagnationCD, double baseCD, WarningSet warnings) {
        double extArea = area; // no need to account for control setting
        double effectArea = extArea * (1.0 - Math.pow(conditions.getSinAOA(), 2.0));
        return (stagnationCD + baseCD) * effectArea / conditions.getRefArea();
    }
}
