package net.sf.openrocket.gui.main;

import net.miginfocom.swing.MigLayout;
import net.sf.openrocket.ActEC.controls.ElementType;
import net.sf.openrocket.ActEC.dispersion.Engine;
import net.sf.openrocket.ActEC.dispersion.Sample;
import net.sf.openrocket.ActEC.dispersion.mutators.Mutator;
import net.sf.openrocket.ActEC.flightcomputer.FlightComputerType;
import net.sf.openrocket.document.OpenRocketDocument;
import net.sf.openrocket.document.Simulation;
import net.sf.openrocket.gui.ActEC.dispersion.Display;
import net.sf.openrocket.gui.SpinnerEditor;
import net.sf.openrocket.gui.adaptors.EnumModel;
import net.sf.openrocket.gui.adaptors.IntegerModel;
import net.sf.openrocket.gui.adaptors.SimulationDropdownModel;

import javax.swing.*;
import javax.swing.text.SimpleAttributeSet;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.PrintStream;
import java.util.List;
import java.util.Vector;
import java.io.OutputStream;
import java.io.IOException;

/**
 * Created by chris on 8/17/15.
 */
class TextConsole extends OutputStream {
    private JTextPane textPane;

    public TextConsole(JTextPane textPane) {
        this.textPane = textPane;
        this.textPane.setEditable(false);
    }

    @Override
    public void write(int c) throws IOException {
//        textPane.append(String.valueOf((char)c));
        try {
            textPane.getDocument().insertString(textPane.getDocument().getLength(), String.valueOf((char) c), new SimpleAttributeSet());
        }
        catch (Exception e) {
            // TODO...
        }
        textPane.setCaretPosition(textPane.getDocument().getLength());
    }
}
public class TortureTestPanel extends JSplitPane {

    private final OpenRocketDocument document;
    private Engine engine;
    private Display display;

    private JButton run;
    private JTextArea mutators;
    private JTextArea filepath;
    private JTextPane consolePane;
    private TextConsole consoleOutput;

    public TortureTestPanel(OpenRocketDocument doc, Engine engine) {
        super(JSplitPane.HORIZONTAL_SPLIT, true);
        this.document = doc;
        this.engine = engine;
        setResizeWeight(0.0);
        display = new Display();
        engine.setFlightComputerType(FlightComputerType.BRICKED_COMPUTER);

        JPanel panel = new JPanel(new MigLayout("debug", "[][][][grow]", "[][grow][]"));

        panel.add(new JLabel("Torture Runs: "));
        IntegerModel num = new IntegerModel(engine, "RunCount", 1);
        JSpinner spin = new JSpinner(num.getSpinnerModel());
        spin.setEditor(new SpinnerEditor(spin));
        panel.add(spin, "growx");

        panel.add(new JLabel("Flight Computer: "));
        JComboBox combo = new JComboBox(new EnumModel<FlightComputerType>(engine, "FlightComputerType"));
        panel.add(combo, "spanx, growx");

//        JButton showlog = new JButton("Show Log");
//        panel.add(showlog, "spanx");

        panel.add(new JLabel("JyComputer Path: "));
        filepath = new JTextArea();
        panel.add(filepath, "growx, spanx 1");

        run = new JButton("Run");
        run.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                display.purgeSimulations();
                run();
            }
        });
        panel.add(run, "wrap");

        mutators = new JTextArea();
        panel.add(mutators, "span, grow");

        consolePane = new JTextPane();
        consoleOutput = new TextConsole(consolePane);
        panel.add(consolePane, "spanx, grow");
        engine.setPrintStream(new PrintStream(consoleOutput));


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
