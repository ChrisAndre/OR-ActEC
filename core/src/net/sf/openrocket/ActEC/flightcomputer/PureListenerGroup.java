package net.sf.openrocket.ActEC.flightcomputer;

import net.sf.openrocket.aerodynamics.AerodynamicForces;
import net.sf.openrocket.aerodynamics.FlightConditions;
import net.sf.openrocket.models.atmosphere.AtmosphericConditions;
import net.sf.openrocket.motor.MotorId;
import net.sf.openrocket.motor.MotorInstance;
import net.sf.openrocket.rocketcomponent.MotorMount;
import net.sf.openrocket.rocketcomponent.RecoveryDevice;
import net.sf.openrocket.simulation.AccelerationData;
import net.sf.openrocket.simulation.FlightEvent;
import net.sf.openrocket.simulation.MassData;
import net.sf.openrocket.simulation.SimulationStatus;
import net.sf.openrocket.simulation.exception.SimulationException;
import net.sf.openrocket.simulation.listeners.AbstractSimulationListener;
import net.sf.openrocket.util.BugException;
import net.sf.openrocket.util.Coordinate;

import java.util.List;

/**
 * Created by chris on 8/23/15.
 */
public abstract class PureListenerGroup extends AbstractSimulationListener {
    private List<AbstractSimulationListener> listeners;
    public void registerListeners(List<AbstractSimulationListener> listeners) {
        this.listeners = listeners;
    }

    ////  SimulationListener  ////

    @Override
    public void startSimulation(SimulationStatus status) throws SimulationException {
        for (AbstractSimulationListener a : listeners) {
            a.startSimulation(status);
        }
    }

    @Override
    public void endSimulation(SimulationStatus status, SimulationException exception) {
        for (AbstractSimulationListener a : listeners) {
            a.endSimulation(status, exception);
        }
    }

    @Override
    public boolean preStep(SimulationStatus status) throws SimulationException {
        for (AbstractSimulationListener a : listeners) {
            a.preStep(status);
        }
        return true;
    }

    @Override
    public void postStep(SimulationStatus status) throws SimulationException {
        for (AbstractSimulationListener a : listeners) {
            a.postStep(status);
        }
    }
    @Override
    public boolean isSystemListener() {
        return false;
    }
    ////  SimulationEventListener  ////

    @Override
    public boolean addFlightEvent(SimulationStatus status, FlightEvent event) throws SimulationException {
        for (AbstractSimulationListener a : listeners) {
            a.addFlightEvent(status, event);
        }
        return true;
    }

    @Override
    public boolean handleFlightEvent(SimulationStatus status, FlightEvent event) throws SimulationException {
        for (AbstractSimulationListener a : listeners) {
            a.handleFlightEvent(status, event);
        }
        return true;
    }

    @Override
    public boolean motorIgnition(SimulationStatus status, MotorId motorId, MotorMount mount, MotorInstance instance) throws SimulationException {
        for (AbstractSimulationListener a : listeners) {
            a.motorIgnition(status, motorId, mount, instance);
        }
        return true;
    }

    @Override
    public boolean recoveryDeviceDeployment(SimulationStatus status, RecoveryDevice recoveryDevice) throws SimulationException {
        for (AbstractSimulationListener a : listeners) {
            a.recoveryDeviceDeployment(status, recoveryDevice);
        }
        return true;
    }



    ////  SimulationComputationListener  ////

    @Override
    public AccelerationData preAccelerationCalculation(SimulationStatus status) throws SimulationException {
        for (AbstractSimulationListener a : listeners) {
            a.preAccelerationCalculation(status);
        }
        return null;
    }

    @Override
    public AerodynamicForces preAerodynamicCalculation(SimulationStatus status) throws SimulationException {
        for (AbstractSimulationListener a : listeners) {
            a.preAerodynamicCalculation(status);
        }
        return null;
    }

    @Override
    public AtmosphericConditions preAtmosphericModel(SimulationStatus status) throws SimulationException {
        for (AbstractSimulationListener a : listeners) {
            a.preAtmosphericModel(status);
        }
        return null;
    }

    @Override
    public FlightConditions preFlightConditions(SimulationStatus status) throws SimulationException {
        for (AbstractSimulationListener a : listeners) {
            a.preFlightConditions(status);
        }
        return null;
    }

    @Override
    public double preGravityModel(SimulationStatus status) throws SimulationException {
        for (AbstractSimulationListener a : listeners) {
            a.preGravityModel(status);
        }
        return Double.NaN;
    }

    @Override
    public MassData preMassCalculation(SimulationStatus status) throws SimulationException {
        for (AbstractSimulationListener a : listeners) {
            a.preMassCalculation(status);
        }
        return null;
    }

    @Override
    public double preSimpleThrustCalculation(SimulationStatus status) throws SimulationException {
        for (AbstractSimulationListener a : listeners) {
            a.preSimpleThrustCalculation(status);
        }
        return Double.NaN;
    }

    @Override
    public Coordinate preWindModel(SimulationStatus status) throws SimulationException {
        for (AbstractSimulationListener a : listeners) {
            a.preWindModel(status);
        }
        return null;
    }

    @Override
    public AccelerationData postAccelerationCalculation(SimulationStatus status, AccelerationData acceleration) throws SimulationException {
        for (AbstractSimulationListener a : listeners) {
            a.postAccelerationCalculation(status, acceleration);
        }
        return null;
    }

    @Override
    public AerodynamicForces postAerodynamicCalculation(SimulationStatus status, AerodynamicForces forces) throws SimulationException {
        for (AbstractSimulationListener a : listeners) {
            a.postAerodynamicCalculation(status, forces);
        }
        return null;
    }

    @Override
    public AtmosphericConditions postAtmosphericModel(SimulationStatus status, AtmosphericConditions atmosphericConditions) throws SimulationException {
        for (AbstractSimulationListener a : listeners) {
            a.postAtmosphericModel(status, atmosphericConditions);
        }
        return null;
    }

    @Override
    public FlightConditions postFlightConditions(SimulationStatus status, FlightConditions flightConditions) throws SimulationException {
        for (AbstractSimulationListener a : listeners) {
            a.postFlightConditions(status, flightConditions);
        }
        return null;
    }

    @Override
    public double postGravityModel(SimulationStatus status, double gravity) throws SimulationException {
        for (AbstractSimulationListener a : listeners) {
            a.postGravityModel(status, gravity);
        }
        return Double.NaN;
    }

    @Override
    public MassData postMassCalculation(SimulationStatus status, MassData massData) throws SimulationException {
        for (AbstractSimulationListener a : listeners) {
            a.postMassCalculation(status, massData);
        }
        return null;
    }

    @Override
    public double postSimpleThrustCalculation(SimulationStatus status, double thrust) throws SimulationException {
        for (AbstractSimulationListener a : listeners) {
            a.postSimpleThrustCalculation(status, thrust);
        }
        return Double.NaN;
    }

    @Override
    public Coordinate postWindModel(SimulationStatus status, Coordinate wind) throws SimulationException {
        for (AbstractSimulationListener a : listeners) {
            a.postWindModel(status, wind);
        }
        return null;
    }

    @Override
    public AbstractSimulationListener clone() {
        try {
            return (AbstractSimulationListener) super.clone();
        } catch (Exception e) {
            throw new BugException(e);
        }
    }
}
