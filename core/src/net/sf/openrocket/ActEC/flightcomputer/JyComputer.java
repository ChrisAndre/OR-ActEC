package net.sf.openrocket.ActEC.flightcomputer;

import net.sf.openrocket.ActEC.flightcomputer.sensor.AllSensors;
import net.sf.openrocket.simulation.SimulationStatus;
import net.sf.openrocket.simulation.exception.SimulationException;
import org.python.core.*;
import org.python.util.PythonInterpreter;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.PrintStream;

/**
 * Created by chris on 10/20/15.
 *
 * FlightComputer with control logic in external Python file.
 */
public class JyComputer extends FlightComputer {
    protected String controllerScript;
    protected File controllerFile;
    private PythonInterpreter interp;
    private PyFunction controller;
    private PyObject inst;
    public JyComputer() {
        super();
        interp = new PythonInterpreter();
        sensors = new AllSensors();
        setControllerScript(new File("/home/chris/Desktop/Rocketry/control.py"));
        reset();
    }

    public String getControllerScript() {
        return controllerScript;
    }
    public void setControllerScript(String cscript) {
        controllerScript = cscript;
        interp.exec(controllerScript);
        inst = interp.eval("FlightComputer()");
//        inst.invoke("startup");
//        controller = (PyFunction) inst.__getattr__("control");
    }

    public File getControllerFile() {
        return controllerFile;
    }
    public void setControllerScript(File file) {
        controllerFile = file;
        interp.execfile(file.getAbsolutePath());
        inst = (PyObject) interp.eval("FlightComputer()");
//        inst.invoke("startup");
//        controller = (PyFunction) inst.__getattr__("control");
    }

    @Override
    public void setPrintStream(PrintStream ps) {
        super.setPrintStream(ps);
        interp.setOut(printStream);
    }

    @Override
    public void reset() {
        super.reset();
        interp.set("controls", controllables);
        interp.set("sensors", sensors);
        if (controllerScript != null)
            setControllerScript(controllerScript);
        else
            setControllerScript(controllerFile);
    }

    @Override
    public void startSimulation(SimulationStatus status) throws SimulationException {
        super.startSimulation(status);
        inst.invoke("startup");
    }
    @Override
    public void postStep(SimulationStatus status) throws SimulationException {
        super.postStep(status);
        interp.set("controls", controllables);
        interp.set("sensors", sensors);
//        controller.__call__();
        inst.invoke("control");
    }
}
