package net.sf.openrocket.aerodynamics.barrowman;

import net.sf.openrocket.aerodynamics.AerodynamicForces;
import net.sf.openrocket.aerodynamics.BarrowmanCalculator;
import net.sf.openrocket.aerodynamics.FlightConditions;
import net.sf.openrocket.aerodynamics.WarningSet;
import net.sf.openrocket.aerodynamics.controls.ControlElement;
import net.sf.openrocket.rocketcomponent.ControlElementSet;
import net.sf.openrocket.rocketcomponent.RocketComponent;
import net.sf.openrocket.util.Coordinate;

/**
 * ControlElementSetCalc
 *
 * @author Chris Andre
 */

public class ControlElementSetCalc extends RocketComponentCalc {
    ControlElementSet ces;
    public ControlElementSetCalc(RocketComponent component) {
        super(component);
        ces = (ControlElementSet)component;
    }

    @Override
    public void calculateNonaxialForces(FlightConditions conditions,
                                        AerodynamicForces forces, WarningSet warnings) {
        if (!ces.getDeployed()) {
            return;
        }
        double stagCD = BarrowmanCalculator.calculateStagnationCD(conditions.getMach());
        double baseCD = BarrowmanCalculator.calculateBaseCD(conditions.getMach());
        double[] elem_forces = new double[ces.getElementCount()]; // these are confusingly normalized by dynamic pressure
        for (int i = 0; i < ces.getElementCount(); i++) {
            elem_forces[i] = ces.getElements()[i].calculatePressureDragForce(conditions, stagCD, baseCD, warnings);
        }
        double mx = 0.0, my = 0.0;
        for (int i = 0; i < ces.getElementCount(); i++) {
            mx += -elem_forces[i] * Math.cos(ces.getRot(i)) * ces.getBodyRadius() / conditions.getRefLength();
            my += -elem_forces[i] * Math.sin(ces.getRot(i)) * ces.getBodyRadius() / conditions.getRefLength();
        }
        forces.setCMoment(new Coordinate(mx, my, 0.0));
    }

    @Override
    public double calculatePressureDragForce(FlightConditions conditions,
                                             double stagnationCD, double baseCD, WarningSet warnings) {
        if (!ces.getDeployed()) {
            return 0.0;
        }
        ControlElement[] elems = ces.getElements();
        double total = 0.0;
        for (ControlElement elem : elems) {
            total += elem.calculatePressureDragForce(conditions, stagnationCD, baseCD, warnings);
        }
        return total;
    }
}
