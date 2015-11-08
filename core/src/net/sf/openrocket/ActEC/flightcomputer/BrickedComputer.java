package net.sf.openrocket.ActEC.flightcomputer;

import net.sf.openrocket.ActEC.flightcomputer.sensor.AllSensors;
import net.sf.openrocket.simulation.SimulationStatus;
import net.sf.openrocket.simulation.exception.SimulationException;

/**
 * Openrocket ActEC Project
 * Author: Chris Andre
 */
public class BrickedComputer extends FlightComputer {

    public BrickedComputer() {
        super();
        sensors = new AllSensors();
        log("This computer doesn't respond to emails or return calls.");
    }

    @Override
    public void startSimulation(SimulationStatus status) throws SimulationException {
        super.startSimulation(status);
        for (IControllable i : controllables)
            i.resetControl();
    }
}
