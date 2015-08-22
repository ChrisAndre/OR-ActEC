package net.sf.openrocket.gui.main;

import net.miginfocom.swing.MigLayout;
import net.sf.openrocket.document.OpenRocketDocument;
import net.sf.openrocket.gui.dispersion.Display;

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
        setDividerLocation(0.3);
        display = new Display();
        simpanel = new SimulationPanel(doc);
        simpanel.passDisplayObject(display);
        setRightComponent(display);
        setLeftComponent(simpanel);
    }
}
