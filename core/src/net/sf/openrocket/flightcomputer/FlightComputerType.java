package net.sf.openrocket.flightcomputer;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

/**
 * Created by chris on 8/18/15.
 *
 * I don't want to muck about with reflection, so I have to do this...
 */
public enum FlightComputerType {

    BRICKED_COMPUTER("Bricked Computer", BrickedComputer.class);

    private String name;
    private Class<? extends FlightComputer> type;
    private Constructor<? extends FlightComputer> ctor;

    FlightComputerType(String name, Class<? extends FlightComputer> type) {
        this.name = name;
        try {
            this.ctor = type.getConstructor();
        } catch (Exception e) {
            this.ctor = null;
        }
    }
    public FlightComputer getComputer() throws InstantiationException, IllegalAccessException, InvocationTargetException {
        if (ctor == null) {
            return null;
        }
        return (FlightComputer) ctor.newInstance();
    }
    @Override
    public String toString() {
        return name;
    }
}
