package ictgradschool.industry.designpatternsii.examples.example03.observersandmvc;

/**
 * Implementing classes will be notified whenever an associated {@link MyModel} changes.
 */
public interface MyModelListener {

    void modelDataAdded(MyModel model, String dataItem, int index);

    void modelDataRemoved(MyModel model, String dataItem);

    void modelDataChanged(MyModel model, int index, String oldValue, String newValue);
}
