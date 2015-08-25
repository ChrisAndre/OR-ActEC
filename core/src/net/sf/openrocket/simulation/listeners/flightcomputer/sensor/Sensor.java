package net.sf.openrocket.simulation.listeners.flightcomputer.sensor;

import net.sf.openrocket.aerodynamics.FlightConditions;
import net.sf.openrocket.models.atmosphere.AtmosphericConditions;
import net.sf.openrocket.simulation.listeners.AbstractSimulationListener;
import net.sf.openrocket.simulation.listeners.SimulationComputationListener;
import net.sf.openrocket.simulation.listeners.SimulationListener;

/**
 * Sensor
 *
 * @author Chris Andre
 */

public abstract class Sensor extends AbstractSimulationListener {
    public abstract double[] read(FlightConditions fc, AtmosphericConditions ac);
    public abstract void reset();
}
