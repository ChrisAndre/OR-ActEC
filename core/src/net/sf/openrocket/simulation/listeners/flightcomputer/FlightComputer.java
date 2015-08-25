package net.sf.openrocket.simulation.listeners.flightcomputer;

import net.sf.openrocket.rocketcomponent.Rocket;
import net.sf.openrocket.rocketcomponent.RocketComponent;
import net.sf.openrocket.simulation.SimulationStatus;
import net.sf.openrocket.simulation.exception.SimulationException;
import net.sf.openrocket.simulation.listeners.AbstractSimulationListener;
import net.sf.openrocket.simulation.listeners.flightcomputer.sensor.Sensor;

import java.util.*;

/**
 * FlightComputer
 *
 * @author Chris Andre
 */

public abstract class FlightComputer extends PureListenerGroup {
    private StringBuilder log;
    protected List<Sensor> sensors;
    protected List<IControllable> controllables;
    public FlightComputer() {
        log = new StringBuilder("");
        sensors = new ArrayList<Sensor>();
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
        for (Sensor s : sensors) {
            s.reset();
        }
    }
    protected void registerSensor(Sensor s) {
        sensors.add(s);
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
        registerListeners((List<AbstractSimulationListener>)(List<?>)sensors);
        super.startSimulation(status);
    }
}
