package net.sf.openrocket.rocketcomponent;

import net.sf.openrocket.aerodynamics.controls.ControlElement;
import net.sf.openrocket.aerodynamics.controls.InactiveElement;
import net.sf.openrocket.util.Coordinate;

import java.util.ArrayList;
import java.util.Collection;

/**
 * ControlElementSet
 *
 * @author Chris Andre
 */

public class ControlElementSet extends ExternalComponent {
    protected int num = 3;
    protected double radius = 0.04;
    protected double indiv_size = 0.01;
    protected boolean deployed = true;
    protected ControlElement[] elements;
    public void setControls(double[] u) {
        for (int i = 0; i < num; i++) {
            elements[i].setControl(u[i]);
        }
    }
    public ControlElementSet() {
        super(Position.BOTTOM);
        elements = new ControlElement[num];
        for (int i = 0; i < num; i++) {
            // Default element
            elements[i] = new InactiveElement();
        }
        length = 0.02;
    }
    public void deploy(boolean deployed) {
        this.deployed = deployed;
    }
    public boolean isDeployed() {
        return deployed;
    }
    public int getElementCount() {
        return num;
    }
    public double getRadius() {
        return radius;
    }
    public double getIndividualSize() {
        return indiv_size;
    }
    public ControlElement[] getElements() {
        return elements;
    }
    public double[] getPos(int i) {
        double[] ret = new double[2];
        ret[0] = radius * Math.cos(i * 2.0 * Math.PI / num);
        ret[1] = radius * Math.sin(i * 2.0 * Math.PI / num);
        return ret;
    }
    public double getRot(int i) {
        return i * 2.0 * Math.PI / num;
    }

    // Overrides

    @Override
    public double getComponentVolume() {
        return 0.0001;
    }

    @Override
    public String getComponentName() {
        return "ControlElementSet";
    }

    @Override
    public Coordinate getComponentCG() {
        return new Coordinate(length/2.0,0,0,0.0001);
    }

    @Override
    public double getLongitudinalUnitInertia() {
        return 0.0001;
    }

    @Override
    public double getRotationalUnitInertia() {
        return 0.0001;
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
        addBound(set, 0.0, radius);
        addBound(set, length, radius);
        return set;
    }
}
