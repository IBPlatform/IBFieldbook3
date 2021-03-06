
package ibfb.germplasmlist.location;

import java.util.*;
import javax.swing.*;


public class SortedComboBoxModel extends DefaultComboBoxModel {

    private Comparator comparator;

    /*
     * Static method is required to make sure the data is in sorted order before
     * it is added to the model
     */
    @SuppressWarnings("unchecked")
    protected static Vector sortVector(Vector items, Comparator comparator) {
        Collections.sort(items, comparator);
        return items;
    }

    /*
     * Static method is required to make sure the data is in sorted order before
     * it is added to the model
     */
    @SuppressWarnings("unchecked")
    protected static Object[] sortArray(Object[] items, Comparator comparator) {
        Arrays.sort(items, comparator);
        return items;
    }

    /*
     * Create an empty model that will use the natural sort order of the item
     */
    public SortedComboBoxModel() {
        super();
    }

    /*
     * Create an empty model that will use the specified Comparator
     */
    public SortedComboBoxModel(Comparator comparator) {
        super();
        this.comparator = comparator;
    }

    /*
     * Create a model with data and use the nature sort order of the items
     */
    public SortedComboBoxModel(Object[] items) {
        super(sortArray(items, null));
    }

    /*
     * Create a model with data and use the specified Comparator
     */
    public SortedComboBoxModel(Object[] items, Comparator comparator) {
        super(sortArray(items, comparator));
        this.comparator = comparator;
    }

    /*
     * Create a model with data and use the nature sort order of the items
     */
    public SortedComboBoxModel(Vector items) {
        this(items, null);
    }

    /*
     * Create a model with data and use the specified Comparator
     */
    public SortedComboBoxModel(Vector items, Comparator comparator) {
        super(sortVector(items, comparator));
        this.comparator = comparator;
    }

    @Override
    public void addElement(Object element) {
        insertElementAt(element, 0);
    }

    @SuppressWarnings("unchecked")
    @Override
    public void insertElementAt(Object element, int index) {
        int size = getSize();

        //  Determine where to insert element to keep model in sorted order

        for (index = 0; index < size; index++) {
            if (comparator != null) {
                Object o = getElementAt(index);

                if (comparator.compare(o, element) > 0) {
                    break;
                }
            } else {
                Comparable c = (Comparable) getElementAt(index);

                if (c.compareTo(element) > 0) {
                    break;
                }
            }
        }

        super.insertElementAt(element, index);
    }
}
