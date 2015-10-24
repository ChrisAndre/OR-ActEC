package net.sf.openrocket.ActEC.flightcomputer;

import net.sf.openrocket.ActEC.flightcomputer.sensor.AllSensors;
import net.sf.openrocket.simulation.SimulationStatus;
import net.sf.openrocket.simulation.exception.SimulationException;
import org.python.core.PyCode;
import org.python.core.PyFunction;
import org.python.core.PyObject;
import org.python.util.PythonInterpreter;

/**
 * Created by chris on 10/20/15.
 *
 * FlightComputer with control logic in external Python file.
 */
public class JyComputer extends FlightComputer {
    protected String controllerScript;
    private PythonInterpreter interp;
    private PyFunction controller;
    public JyComputer() {
        super();
        interp = new PythonInterpreter();
        sensors = new AllSensors();
        setControllerScript("def control():\n\tpass");
    }

    public String getControllerScript() {
        return controllerScript;
    }
    public void setControllerScript(String cscript) {
        controllerScript = cscript;
        interp.exec(controllerScript);
        controller = (PyFunction) interp.get("control");
    }

    @Override
    public void postStep(SimulationStatus status) throws SimulationException {
        interp.set("controls", controllables);
        interp.set("sensors", sensors);
        controller.__call__();
    }
}
