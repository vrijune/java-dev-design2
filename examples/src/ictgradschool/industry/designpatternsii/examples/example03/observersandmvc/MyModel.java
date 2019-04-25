package ictgradschool.industry.designpatternsii.examples.example03.observersandmvc;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * A really simple "model" which contains a list of Strings, and uses the Observer pattern to maintain a list of
 * listeners (observers) which will be notified whenever data in that list changes.
 */
public class MyModel {

    private final List<String> data;
    private final Set<MyModelListener> listeners;

    /** Creates a new MyModel */
    public MyModel() {
        data = new ArrayList<>();
        listeners = new HashSet<>();
    }

    /** Adds an item to the list and notifies listeners */
    public void add(String s) {
        data.add(s);
        for(MyModelListener l : listeners) {
            l.modelDataAdded(this, s, data.size() - 1);
        }
    }

    /** Removes an item from the list and notifies listeners */
    public void remove(String s) {
        if (data.remove(s)) {
            for(MyModelListener l : listeners) {
                l.modelDataRemoved(this, s);
            }
        }
    }

    /** Changes an item in the list to the given value, then notifies listeners */
    public void change(int index, String newValue) {

        String oldValue = data.get(index);
        data.remove(index);
        data.add(index, newValue);
        for(MyModelListener l : listeners) {
            l.modelDataChanged(this, index, oldValue, newValue);
        }
    }

    /** Gets the value at the given index. */
    public String get(int index) {
        return data.get(index);
    }

    /** Gets the size of the list */
    public int size() {
        return data.size();
    }

    /** Adds a {@link MyModelListener}, which will be notified of any changes to the list */
    public void addListener(MyModelListener l) {
        listeners.add(l);
    }

    /** Removes a {@link MyModelListener}, which will no longer be notified of any changes to the list */
    public void removeListener(MyModelListener l) {
        listeners.remove(l);
    }


}
