package net.sf.openrocket.gui.main;

import net.miginfocom.swing.MigLayout;
import net.sf.openrocket.ActEC.dispersion.Engine;
import net.sf.openrocket.ActEC.dispersion.Sample;
import net.sf.openrocket.ActEC.dispersion.mutators.Mutator;
import net.sf.openrocket.ActEC.flightcomputer.FlightComputerType;
import net.sf.openrocket.document.OpenRocketDocument;
import net.sf.openrocket.document.Simulation;
import net.sf.openrocket.gui.ActEC.dispersion.Display;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Vector;

/**
 * Created by chris on 8/17/15.
 */

public class TortureTestPanel extends JSplitPane {

    private final OpenRocketDocument document;
    private Engine engine;
    private Display display;

    private JButton run;

    public TortureTestPanel(OpenRocketDocument doc, Engine engine) {
        super(JSplitPane.HORIZONTAL_SPLIT, true);
        this.document = doc;
        this.engine = engine;
        setResizeWeight(0.5);
        setDividerLocation(0.5);
        display = new Display();
        engine.setFlightComputerType(FlightComputerType.BRICKED_COMPUTER);

        run = new JButton("Run");
        run.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                display.purgeSimulations();
                run();
            }
        });

        JPanel panel = new JPanel(new MigLayout(""));
        panel.add(new JLabel("Torture Runs: "));
        panel.add(new JSpinner());
        panel.add(new JLabel("Flight Computer: "));
        panel.add(new JComboBox<Integer>());
        panel.add(run);
        panel.add(new JButton("Show Log"));

        setLeftComponent(panel);
        setRightComponent(display);
    }

    public void run() {
        engine.addSimListener(new Engine.SampleListener() {
            @Override
            public void sampleSimulationComplete(Sample s) {
                display.addSimulation(s.getSimulation());
            }
        });
        try {
            engine.run();
        }
        catch (Exception e) {
            // TODO:
        }
    }
}
