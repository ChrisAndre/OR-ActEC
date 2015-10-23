package net.sf.openrocket.ActEC.controls;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

/**
 * Created by chris on 8/16/15.
 *
 * I don't want to muck with reflection, so I have to do this...
 */

public enum ElementType {
    SIMPLEFLATPLATE("Simple Flat Plate Control Element", SimpleFlatPlateElement.class),
    INACTIVE("Inactive Control Element", InactiveElement.class),
    DRAGPLATE("Fixed Drag Plate Control Element", DragPlateElement.class);

    private String name;
    private Class<? extends ControlElement> type;
    private Constructor<? extends ControlElement> ctor;

    ElementType(String name, Class<? extends ControlElement> type) {
        this.name = name;
        try {
            this.ctor = type.getConstructor(String.class);
        } catch (Exception e) {
            this.ctor = null;
        }
    }
    public ControlElement getElement(String name) throws InstantiationException, IllegalAccessException, InvocationTargetException{
        if (ctor == null) {
            return null;
        }
        return (ControlElement) ctor.newInstance(name);
    }
    @Override
    public String toString() {
        return name;
    }
}
