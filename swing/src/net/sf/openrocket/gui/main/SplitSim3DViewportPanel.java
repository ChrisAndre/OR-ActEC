package net.sf.openrocket.gui.main;

import net.sf.openrocket.ActEC.dispersion.Engine;
import net.sf.openrocket.document.OpenRocketDocument;
import net.sf.openrocket.gui.ActEC.dispersion.Display;

import javax.swing.*;

/**
 * Created by chris on 8/18/15.
 */
public class SplitSim3DViewportPanel extends JSplitPane {
    private Display display;
    private SimulationPanel simpanel;
    public SplitSim3DViewportPanel(OpenRocketDocument doc, Engine eng) {
        super(JSplitPane.HORIZONTAL_SPLIT, true);
        setResizeWeight(0.0);
        display = new Display();
        simpanel = new SimulationPanel(doc, display, eng);
        setLeftComponent(simpanel);
        setRightComponent(display);
    }
    public SimulationPanel getSimPanel() {
        return simpanel;
    }
}
