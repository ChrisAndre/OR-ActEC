package net.sf.openrocket.simulation.listeners.flightcomputer;

/**
 * Created by chris on 8/18/15.
 */
public class BrickedComputer extends FlightComputer {
    public BrickedComputer() {
        super();
        log("This computer does not seem to be functioning!");
    }
}
