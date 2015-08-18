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
        double[] elem_forces = new double[ces.getElementCount()];
        for (int i = 0; i < ces.getElementCount(); i++) {
            elem_forces[i] = ces.getElements()[i].calculatePressureDragForce(conditions, stagCD, baseCD, warnings);
        }
        double[] moment = new double[2];
        for (int i = 0; i < ces.getElementCount(); i++) {
            moment[0] += elem_forces[i] * Math.sin(ces.getRot(i)) * ces.getBodyRadius() / conditions.getRefLength();
            moment[1] += -elem_forces[i] * Math.cos(ces.getRot(i)) * ces.getBodyRadius() / conditions.getRefLength();
        }
        forces.setCm(0);
        forces.setCyaw(-1);
        forces.setCN(0);
        forces.setCNa(0);
        forces.setCP(Coordinate.NUL);
        forces.setCroll(0);
        forces.setCrollDamp(0);
        forces.setCrollForce(0);
        forces.setCside(0);
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
