package net.sf.openrocket.ActEC;

import net.sf.openrocket.ActEC.controls.ControlElement;
import net.sf.openrocket.ActEC.controls.ElementType;
import net.sf.openrocket.ActEC.controls.InactiveElement;
import net.sf.openrocket.ActEC.flightcomputer.IControllable;
import net.sf.openrocket.rocketcomponent.ComponentChangeEvent;
import net.sf.openrocket.rocketcomponent.ExternalComponent;
import net.sf.openrocket.rocketcomponent.RocketComponent;
import net.sf.openrocket.rocketcomponent.SymmetricComponent;
import net.sf.openrocket.util.Coordinate;

import java.util.ArrayList;
import java.util.Collection;

/**
 * ControlElementSet
 *
 * @author Chris Andre
 */

public class ControlElementSet extends ExternalComponent implements IControllable {
    protected int num = 3;
    protected double indiv_size = 0.05;
    protected boolean deployed = true;
    private double multRadius = 1.0;
    protected double baseRot = 0.0;
    protected ControlElement[] elements;
    protected ElementType type;

    public ControlElementSet() {
        super(Position.BOTTOM);
        elements = new ControlElement[num];
        setElementType(ElementType.INACTIVE);
        length = 0.01;
    }

    @Override
    public void setControl(double[] u) {
        if (elements.length != u.length) {
            return;
        }
        for (int i = 0; i < elements.length; i++) {
            elements[i].setControl(u[i]);
        }
    }

    // Properties

    public boolean getDeployed() {
        return deployed;
    }
    public void setDeployed(boolean deployed) {
        this.deployed = deployed;
        fireComponentChangeEvent(ComponentChangeEvent.BOTH_CHANGE);
    }

    public int getElementCount() {
        return num;
    }
    public void setElementCount(int n) {
        num = n;
        elements = new ControlElement[num];
        try {
            for (int i = 0; i < num; i++) {
                // Default element
                elements[i] = type.getElement();
            }
        } catch (Exception e) {
            for (int i = 0; i < num; i++) {
                // Default element
                elements[i] = new InactiveElement();
            }
        }
        fireComponentChangeEvent(ComponentChangeEvent.BOTH_CHANGE);
    }

    public ElementType getElementType() {
        return type;
    }
    public void setElementType(ElementType type) {
        this.type = type;
        try {
            for (int i = 0; i < num; i++) {
                // Default element
                elements[i] = type.getElement();
            }
        } catch (Exception e) {
            for (int i = 0; i < num; i++) {
                // Default element
                elements[i] = new InactiveElement();
            }
        }
        fireComponentChangeEvent(ComponentChangeEvent.BOTH_CHANGE);
    }

    public double getBaseRotation() {return baseRot;}
    public void setBaseRotation(double rot) {baseRot = rot;fireComponentChangeEvent(ComponentChangeEvent.BOTH_CHANGE);}

    // Misc.

    public double getBodyRadius() {
        RocketComponent s;

        s = this.getParent();
        while (s != null) {
            if (s instanceof SymmetricComponent) {
                double x = this.toRelative(new Coordinate(0, 0, 0), s)[0].x;
                return ((SymmetricComponent) s).getRadius(x) * multRadius;
            }
            s = s.getParent();
        }
        return 0;
    }
    public double getIndividualSize() {
        return indiv_size;
    }
    public ControlElement[] getElements() {
        return elements;
    }
    public double[] getPos(int i) {
        double[] ret = new double[2];
        ret[0] = getBodyRadius() * Math.cos(getRot(i));
        ret[1] = getBodyRadius() * Math.sin(getRot(i));
        return ret;
    }
    public double getRot(int i) {
        return i * 2.0 * Math.PI / num + baseRot;
    }

    // Overrides

    @Override
    public double getComponentVolume() {
        return 0.0;
    }

    @Override
    public String getComponentName() {
        return "ControlElementSet";
    }

    @Override
    public Coordinate getComponentCG() {
        return new Coordinate(length/2.0,0,0,0.0);
    }

    @Override
    public double getLongitudinalUnitInertia() {
        return 0.0;
    }

    @Override
    public double getRotationalUnitInertia() {
        return 0.0;
    }

    @Override
    public boolean allowsChildren() {
        return false;
    }

    @Override
    public boolean isCompatible(Class<? extends RocketComponent> type) {
        return false;
    }

    @Override
    public Collection<Coordinate> getComponentBounds() {
        ArrayList<Coordinate> set = new ArrayList<Coordinate>();
        addBound(set, 0.0, getBodyRadius());
        addBound(set, length, getBodyRadius());
        return set;
    }
}
