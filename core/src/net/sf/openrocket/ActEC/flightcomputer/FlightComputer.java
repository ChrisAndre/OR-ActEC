package net.sf.openrocket.ActEC.flightcomputer;

import net.sf.openrocket.ActEC.flightcomputer.sensor.SensorGroup;
import net.sf.openrocket.rocketcomponent.RocketComponent;
import net.sf.openrocket.simulation.SimulationStatus;
import net.sf.openrocket.simulation.exception.SimulationException;
import net.sf.openrocket.simulation.listeners.AbstractSimulationListener;
import net.sf.openrocket.ActEC.flightcomputer.sensor.Sensor;

import java.io.PrintStream;
import java.util.*;

/**
 * FlightComputer
 *
 * @author Chris Andre
 */

public abstract class FlightComputer extends PureListenerGroup {
    private StringBuilder log;
    protected SensorGroup sensors;
    protected List<IControllable> controllables;
    protected PrintStream printStream;
    public FlightComputer() {
        log = new StringBuilder("");
    }
    public void log(String msg) {
        log.append(msg);
        log.append('\n');
    }
    public String getLogCopy() {
        return log.toString();
    }
    public void reset() {
        log = new StringBuilder("");
        for (Sensor s : sensors.getSensors()) {
            s.reset();
        }
    }
    public void setPrintStream(PrintStream ps) {
        this.printStream = ps;
    }
    private ArrayList<RocketComponent> locateControllables(RocketComponent r) {
        ArrayList<RocketComponent> rcs = new ArrayList<RocketComponent>();
        if (r instanceof IControllable) {
            rcs.add(r);
        }
        for (RocketComponent rc : r.getChildren()) {
            rcs.addAll(locateControllables(rc));
        }
        return rcs;
    }
    @Override
    public void startSimulation(SimulationStatus status) throws SimulationException {
        reset();
        controllables = (ArrayList<IControllable>)(ArrayList<?>) locateControllables(status.getConfiguration().getRocket());
        registerListeners((List<AbstractSimulationListener>)(List<?>)sensors.getSensors());
        super.startSimulation(status);
    }
}
