package net.sf.openrocket.aerodynamics.controls;

import net.sf.openrocket.aerodynamics.FlightConditions;
import net.sf.openrocket.aerodynamics.WarningSet;

/**
 * Created by chris on 8/16/15.
 */
public class DragPlateElement extends ControlElement {
    protected double width, height;
    public DragPlateElement(double w, double h) {
        width = w;
        height = h;
        u = 0.0;
    }
    public DragPlateElement() {
        this(0.03, 0.03);
    }
    @Override
    public double calculatePressureDragForce(FlightConditions conditions,
                                             double stagnationCD, double baseCD, WarningSet warnings) {
        double extArea = width * height;
        double effectArea = extArea * (1.0 - Math.pow(conditions.getSinAOA(), 2.0));
        return (stagnationCD + baseCD) * effectArea / conditions.getRefArea();
    }
}
