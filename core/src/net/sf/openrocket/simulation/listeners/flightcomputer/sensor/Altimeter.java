package net.sf.openrocket.simulation.listeners.flightcomputer.sensor;

import net.sf.openrocket.simulation.SimulationStatus;
import net.sf.openrocket.simulation.exception.SimulationException;

/**
 * Created by chris on 8/30/15.
 */
public class Altimeter extends Sensor {
    private double m_height;
    public Altimeter() {
        m_height = 0.0;
    }
    public double[] read() {
        return new double[]{m_height};
    }

    public void reset() {
        m_height = 0.0;
    }

    @Override
    public void postStep(SimulationStatus status) throws SimulationException {
        m_height = status.getRocketPosition().z;
    }
}
