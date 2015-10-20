package net.sf.openrocket.ActEC.flightcomputer.sensor;

import net.sf.openrocket.util.ArrayList;

/**
 * Created by chris on 10/20/15.
 *
 * Group of all sensors attached to a flight computer.
 */
public class SensorGroup {
    protected ArrayList<Sensor> sensors;
    public SensorGroup() {
        sensors = new ArrayList<Sensor>();
    }
    public ArrayList<Sensor> getSensors() {
        return sensors;
    }
}