package net.sf.openrocket.ActEC.flightcomputer;

import net.sf.openrocket.ActEC.flightcomputer.sensor.AllSensors;
import net.sf.openrocket.simulation.SimulationStatus;
import net.sf.openrocket.simulation.exception.SimulationException;

/**
 * Created by chris on 8/18/15.
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
        controllables.get(0).setControl(new double[]{0.0});
    }
}
