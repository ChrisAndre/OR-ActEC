package net.sf.openrocket.aerodynamics.controls;

import de.congrace.exp4j.Example;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

/**
 * Created by chris on 8/16/15.
 *
 * I don't want to muck with reflection, so I have to do this...
 */

public enum ElementType {
    INACTIVE("Inactive Control Element", InactiveElement.class),
    SIMPLEFLATPLATE("Simple Flat Plate Control Element", SimpleFlatPlateElement.class),
    DRAGPLATE("Fixed Drag Plate Control Element", DragPlateElement.class);

    private String name;
    private Class<? extends ControlElement> type;
    private Constructor<? extends ControlElement> ctor;

    ElementType(String name, Class<? extends ControlElement> type) {
        this.name = name;
        try {
            this.ctor = type.getConstructor();
        } catch (Exception e) {
            this.ctor = null;
        }
    }
    public ControlElement getElement() throws InstantiationException, IllegalAccessException, InvocationTargetException{
        if (ctor == null) {
            return null;
        }
        return (ControlElement) ctor.newInstance();
    }
    @Override
    public String toString() {
        return name;
    }
}
