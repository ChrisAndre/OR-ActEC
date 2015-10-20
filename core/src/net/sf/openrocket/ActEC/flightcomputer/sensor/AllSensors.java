package net.sf.openrocket.ActEC.flightcomputer.sensor;

public class AllSensors extends SensorGroup {
    public AllSensors() {
        super();
        sensors.add(new Altimeter());
        sensors.add(new ZenithAngle());
    }
}
