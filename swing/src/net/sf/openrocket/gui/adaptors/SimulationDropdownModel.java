package net.sf.openrocket.gui.adaptors;

import java.util.ArrayList;
import java.util.EventObject;

import javax.swing.AbstractListModel;
import javax.swing.ComboBoxModel;
import javax.swing.MutableComboBoxModel;

import net.sf.openrocket.ActEC.dispersion.Engine;
import net.sf.openrocket.document.Simulation;
import org.jfree.util.Log;

import net.sf.openrocket.util.ChangeSource;
import net.sf.openrocket.util.Reflection;
import net.sf.openrocket.util.StateChangeListener;

// DEFUNCT - THIS CRASHES MY COMPUTER

public class SimulationDropdownModel extends AbstractListModel implements ComboBoxModel, MutableComboBoxModel, StateChangeListener {

    private final ChangeSource source;
    private final String valueName;
    private final String nullText;

    private final Simulation[] values;
    private Simulation currentValue = null;

    ArrayList<Simulation> displayedValues = new ArrayList<Simulation>();

    private final Reflection.Method getMethod;
    private final Reflection.Method setMethod;

    @SuppressWarnings("unchecked")
    public SimulationDropdownModel(ChangeSource source, String valueName, String nullText) {
        Class<Simulation> simClass;
        this.source = source;
        this.valueName = valueName;

        try {
            java.lang.reflect.Method getM = source.getClass().getMethod("get" + valueName);
            simClass = (Class<Simulation>) getM.getReturnType();

            getMethod = new Reflection.Method(getM);
            setMethod = new Reflection.Method(source.getClass().getMethod("set" + valueName,
                    simClass));
        } catch (NoSuchMethodException e) {
            throw new IllegalArgumentException("get/is methods for enum '"+valueName+
                    "' not present in class "+source.getClass().getCanonicalName());
        }

//        this.values = values;
        this.values = ((Engine)source).doc.getSimulations().toArray(new Simulation[0]);

        for (Simulation e : this.values){
            this.displayedValues.add( e );
        }
        this.nullText = nullText;

        stateChanged(null);  // Update current value
        source.addChangeListener(this);
    }



    @Override
    public Object getSelectedItem() {
        if (currentValue==null)
            return nullText;
        return currentValue;
    }

    @Override
    public void setSelectedItem(Object item) {
        if (item == null) {
            // Clear selection - huh?
            return;
        }
        if (item instanceof String) {
            if (currentValue != null)
                setMethod.invoke(source, (Object)null);
            return;
        }

//        if (!(item instanceof Enum<?>)) {
//            throw new IllegalArgumentException("Not String or Enum, item="+item);
//        }

        // Comparison with == ok, since both are enums
        if (currentValue == item)
            return;
        setMethod.invoke(source, item);
    }

    @Override
    public Object getElementAt(int index) {
        if( ( index < 0 ) || ( index >= this.displayedValues.size())){
            return nullText; // bad parameter
        }

        if (values[index] == null)
            return nullText;
        return displayedValues.get( index);
    }

    @Override
    public int getSize() {
        return displayedValues.size();
    }

    @SuppressWarnings("unchecked")
    @Override
    public void stateChanged(EventObject e) {
        Simulation value = (Simulation) getMethod.invoke(source);
        if (value != currentValue) {
            currentValue = value;
            this.fireContentsChanged(this, 0, values.length);
        }
    }



    @Override
    public String toString() {
        return "EnumModel["+source.getClass().getCanonicalName()+":"+valueName+"]";
    }

    @Override
    public void addElement(Object item) {
        // Not actually allowed.  The model starts out with all the enums, and only allows hiding some.
    }

    @Override
    public void removeElement(Object obj) {
        if( null == obj ){
            return;
        }
        this.displayedValues.remove( obj );
    }

    @Override
    public void insertElementAt(Object item, int index) {
        // Not actually allowed.  The model starts out with all the enums, and only allows hiding some.
    }

    @Override
    public void removeElementAt(int index) {
        if( ( index < 0 ) || ( index >= this.displayedValues.size())){
            return; // bad parameter
        }

        this.displayedValues.remove( index );
    }

}
