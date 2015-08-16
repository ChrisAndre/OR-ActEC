package net.sf.openrocket.flightcomputer;

import net.sf.openrocket.aerodynamics.FlightConditions;
import net.sf.openrocket.models.atmosphere.AtmosphericConditions;

/**
 * ISensor
 *
 * @author Chris Andre
 */

public interface ISensor {
    double[] read(FlightConditions fc, AtmosphericConditions ac);
}
