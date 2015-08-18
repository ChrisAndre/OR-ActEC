package net.sf.openrocket.gui.main;

import net.miginfocom.swing.MigLayout;
import net.sf.openrocket.document.OpenRocketDocument;
import net.sf.openrocket.gui.dispersion.Display;

import javax.media.opengl.GLEventListener;
import javax.media.opengl.awt.GLJPanel;
import javax.swing.*;
import java.awt.*;

/**
 * Created by chris on 8/17/15.
 */

public class TortureTestPanel extends JSplitPane {

    private final OpenRocketDocument document;

    public TortureTestPanel(OpenRocketDocument doc) {
        super(JSplitPane.HORIZONTAL_SPLIT, true);
        this.document = doc;
        setResizeWeight(0.5);
        setDividerLocation(0.2);

        JPanel panel = new JPanel(new MigLayout("fillx, gapx 0"));
        panel.add(new JButton("Add Mutator"), "span 4, growx, wrap");
        panel.add(new JLabel("Torture Runs: "));
        panel.add(new JSpinner());
        panel.add(new JLabel("Flight Computer: "));
        panel.add(new JComboBox<Integer>());
        panel.add(new JButton("Run"));
        panel.add(new JButton("Show Log"));

        setLeftComponent(panel);
        setRightComponent(new Display());
    }
}
