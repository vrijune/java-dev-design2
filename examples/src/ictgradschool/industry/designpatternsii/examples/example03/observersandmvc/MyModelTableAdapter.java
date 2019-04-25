package ictgradschool.industry.designpatternsii.examples.example03.observersandmvc;

import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableModel;

/**
 * An adapter class which allows an instance of {@link MyModel} to be displayed in a {@link JTable}.
 *
 * <p>Extends {@link AbstractTableModel} to allow display in a {@link JTable}. Does not directly implement the
 * {@link TableModel} interface as this would create a lot of extra work for us.</p>
 *
 * <p>Implements {@link MyModelListener} so we can be notified of changes in the underlying {@link MyModel}, and
 * forward those events on to the {@link JTable} as {@link javax.swing.event.TableModelEvent}s.</p>
 */
public class MyModelTableAdapter extends AbstractTableModel implements MyModelListener {

    /** The model we're viewing in a {@link JTable} */
    private MyModel model;

    /** Creates a new {@link MyModelTableAdapter} and adds a lisener to it so we can be notified of changes */
    public MyModelTableAdapter(MyModel model) {
        this.model = model;
        model.addListener(this);
    }

    /** Gets the number of rows in the table. In this case, that's equal to the model's {@link MyModel#size()}. */
    @Override
    public int getRowCount() {
        return model.size();
    }

    /** Gets the number of columns in the table. In this case, that's always just one. */
    @Override
    public int getColumnCount() {
        return 1;
    }

    /**
     * Gets the value at the given row and column. In this case, the row index corresponds to an index into
     * {@link MyModel}, and the column index doesn't matter as there's only one column.
     */
    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        return model.get(rowIndex);
    }

    /** Gets the name of the given column. There's only one column so this method is pretty simple here. */
    @Override
    public String getColumnName(int column) {
        return "The Column";
    }

    /**
     * Called when data is added to the underlying {@link MyModel}. Forwards the change to the {@link JTable} using
     * the {@link #fireTableDataChanged()} method, which is implemented by {@link AbstractTableModel}.
     *
     * <p>Note: There are many other <code>fire...()</code> methods implemented by {@link AbstractTableModel}, which
     * may be more efficient. For example, {@link #fireTableDataChanged()} notifies the table that any of its data
     * could have changed and it needs to entirely redraw itself, whereas {@link #fireTableCellUpdated(int, int)}
     * specifies the exact table cell which changed, meaning it doesn't need to redraw the whole table. Investigate
     * these other methods yourself. You can see them at:
     * https://docs.oracle.com/javase/8/docs/api/javax/swing/table/AbstractTableModel.html</p>
     *
     * @param model the model that changed
     * @param dataItem the item that was added
     * @param index the index of the new item
     */
    @Override
    public void modelDataAdded(MyModel model, String dataItem, int index) {
        fireTableDataChanged();
    }

    /**
     * Called when data is removed from the underlying {@link MyModel}. Forwards the change to the {@link JTable} using
     * the {@link #fireTableDataChanged()} method, which is implemented by {@link AbstractTableModel}.
     *
     * @param model the model that changed
     * @param dataItem the item that was removed
     */
    @Override
    public void modelDataRemoved(MyModel model, String dataItem) {
        fireTableDataChanged();
    }

    /**
     * Called when data is changed in the underlying {@link MyModel}. Forwards the change to the {@link JTable} using
     * the {@link #fireTableDataChanged()} method, which is implemented by {@link AbstractTableModel}.
     *
     * @param model the model that changed
     * @param index the index of the item that changed
     * @param oldValue the old value of the item
     * @param newValue the new value of the item
     */
    @Override
    public void modelDataChanged(MyModel model, int index, String oldValue, String newValue) {
        fireTableDataChanged();
    }

    /**
     * These methods below are optional, and may be implemented if you wish to make your table editable.
     * Have a go yourself.
     */
//    @Override
//    public boolean isCellEditable(int rowIndex, int columnIndex) {
//        return super.isCellEditable(rowIndex, columnIndex);
//    }
//
//    @Override
//    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
//        super.setValueAt(aValue, rowIndex, columnIndex);
//    }
}
