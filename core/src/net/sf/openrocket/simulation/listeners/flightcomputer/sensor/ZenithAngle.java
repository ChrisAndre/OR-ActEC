package net.sf.openrocket.simulation.listeners.flightcomputer.sensor;

import net.sf.openrocket.aerodynamics.FlightConditions;
import net.sf.openrocket.models.atmosphere.AtmosphericConditions;
import net.sf.openrocket.simulation.SimulationStatus;
import net.sf.openrocket.simulation.exception.SimulationException;
import net.sf.openrocket.util.Quaternion;

/**
 * Created by chris on 8/30/15.
 */
public class ZenithAngle extends Sensor {
    private double m_angle;

    public ZenithAngle() {
        m_angle = 0.0;
    }

    public double[] read() {
        return new double[]{m_angle};
    }

    public void reset() {
        m_angle = 0.0;
    }

    @Override
    public void postStep(SimulationStatus status) throws SimulationException {
        Quaternion q = status.getRocketOrientationQuaternion();
        double[] xyz = new double[]{q.getX(), q.getY(), q.getZ()};
        double[] zenith = new double[]{0, 0, 1};
        double xyz_dot_zenith = 0.0;
        double xyz_norm = 0.0;
        double zenith_norm = 1.0;
        for (int i = 0; i < 3; i++) {
            xyz_dot_zenith += xyz[i] * zenith[i];
        }
        for (int i = 0; i < 3; i++) {
            xyz_norm += xyz[i] * xyz[i];
        }
        xyz_norm = Math.sqrt(xyz_norm);
        m_angle = Math.acos(xyz_dot_zenith / xyz_norm / zenith_norm);
        m_angle = 0.0;
    }
}
