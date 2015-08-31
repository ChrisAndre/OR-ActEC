package net.sf.openrocket.gui.configdialog;

import javax.swing.*;

import net.miginfocom.swing.MigLayout;
import net.sf.openrocket.ActEC.controls.ElementType;
import net.sf.openrocket.document.OpenRocketDocument;
import net.sf.openrocket.gui.SpinnerEditor;
import net.sf.openrocket.gui.adaptors.BooleanModel;
import net.sf.openrocket.gui.adaptors.DoubleModel;
import net.sf.openrocket.gui.adaptors.EnumModel;
import net.sf.openrocket.gui.adaptors.IntegerModel;
import net.sf.openrocket.gui.components.BasicSlider;
import net.sf.openrocket.gui.components.UnitSelector;
import net.sf.openrocket.rocketcomponent.RocketComponent;
import net.sf.openrocket.unit.UnitGroup;

/**
 * Created by chris on 8/16/15.
 */
public class ControlElementSetConfig extends RocketComponentConfig {
    private MotorConfig motorConfigPane = null;
    public ControlElementSetConfig(OpenRocketDocument d, RocketComponent c) {
        super(d, c);
        JPanel primary = new JPanel(new MigLayout("fill"));
        JPanel panel = new JPanel(new MigLayout("gap rel unrel", "[][65lp::][30lp::][]", ""));

        // Number of elements
        panel.add(new JLabel("Number of Elements"));
        IntegerModel num = new IntegerModel(component, "ElementCount", 1);

        JSpinner spin = new JSpinner(num.getSpinnerModel());
        spin.setEditor(new SpinnerEditor(spin));
        panel.add(spin, "growx, wrap");

        // Element type
        panel.add(new JLabel("Element Type"));
        JComboBox combo = new JComboBox(new EnumModel<ElementType>(component, "ElementType"));
        panel.add(combo, "spanx, growx, wrap");

        // Active
        panel.add(new JLabel("Is Deployed"));
        BooleanModel b = new BooleanModel(component, "Deployed");
        JCheckBox box = new JCheckBox(b);
        panel.add(box, "growx, wrap");

        // Base rot
        panel.add(new JLabel("Base Rotation"));
        DoubleModel m = new DoubleModel(component, "BaseRotation", UnitGroup.UNITS_ANGLE);
        spin = new JSpinner(m.getSpinnerModel());
        spin.setEditor(new SpinnerEditor(spin));
        panel.add(spin, "growx");
        panel.add(new UnitSelector(m), "growx");
        panel.add(new BasicSlider(m.getSliderModel(-Math.PI, Math.PI)), "w 100lp, wrap");

        // Configuration
        primary.add(panel, "grow, gapright 20lp");
        tabbedPane.insertTab("General", null, primary,
                "General Properties", 0);
        tabbedPane.setSelectedIndex(0);
    }
    @Override
    public void updateFields() {
        super.updateFields();
    }
}
