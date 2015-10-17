package net.sf.openrocket.gui.main;

import net.sf.openrocket.document.OpenRocketDocument;
import net.sf.openrocket.gui.ActEC.dispersion.Display;

import javax.swing.*;

/**
 * Created by chris on 8/18/15.
 */
public class SplitSim3DViewportPanel extends JSplitPane {
    private Display display;
    private SimulationPanel simpanel;
    public SplitSim3DViewportPanel(OpenRocketDocument doc) {
        super(JSplitPane.HORIZONTAL_SPLIT, true);
        setResizeWeight(0.5);
        setDividerLocation(0.5);
        display = new Display();
        simpanel = new SimulationPanel(doc, display);
        setRightComponent(display);
        setLeftComponent(simpanel);
    }
    public SimulationPanel getSimPanel() {
        return simpanel;
    }
}
