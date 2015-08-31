package net.sf.openrocket.ActEC.flightcomputer.sensor;

import net.sf.openrocket.simulation.listeners.AbstractSimulationListener;

/**
 * Sensor
 *
 * @author Chris Andre
 */

public abstract class Sensor extends AbstractSimulationListener {
    public abstract double[] read();
    public abstract void reset();
}
