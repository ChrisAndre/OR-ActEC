package net.sf.openrocket.ActEC.flightcomputer;

import net.sf.openrocket.simulation.SimulationStatus;
import net.sf.openrocket.simulation.exception.SimulationException;
import org.python.util.PythonInterpreter;

/**
 * Created by chris on 10/20/15.
 *
 * FlightComputer with control logic in external Python file.
 */
public class JyComputer extends FlightComputer {
    protected String controllerScript;
    private PythonInterpreter interp;
    public JyComputer(String controllerScript) {
        super();
        this.controllerScript = controllerScript;
        interp = new PythonInterpreter();
    }
    @Override
    public void startSimulation(SimulationStatus status) throws SimulationException {
        super.startSimulation(status);
        controllables.get(0).setControl(new double[]{1.0});
    }

    @Override
    public void endSimulation(SimulationStatus status, SimulationException exception) {
        super.endSimulation(status, exception);
        controllables.get(0).setControl(new double[]{0.0});
    }

    @Override
    public void postStep(SimulationStatus status) throws SimulationException {
        controllables.get(0).setControl(new double[]{status.getSimulationTime() > 2.5 ? 1.0 : 0.0});
    }
}
