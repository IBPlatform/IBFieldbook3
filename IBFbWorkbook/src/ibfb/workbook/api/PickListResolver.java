package ibfb.workbook.api;

import ibfb.domain.core.PickItem;
import java.util.List;

/**
 *  PickListResolver return a list of items according to each label in
 * the workbook
 * @author TMSANCHEZ
 */
public interface PickListResolver {
    public static final String LABEL_STUDY ="STUDY";
    public static final String LABEL_SITE ="SITE";
    public static final String LABEL_PRINCIPAL_INVESTIGATOR = "PI";
    public static final String LABEL_PRINCIPAL_COOPERATOR = "CO";
    public static final String LABEL_METHOD = "BM";
   /**
     * Get all PickItem for a matching label
     * @param labelName The label to return each item
     * @return a List of <code>PickItem</code> if matching or empty list if not
     */
    public List<PickItem> getPickList(String labelName);

    /**
     * Return the value for a pickItem according with specified labelName
     * and Label
     * @param labelName Label to Find
     * @param label Label description to find
     * @return Pick Item value property or empty string "" if not found
     */
    public String getValue(String labelName, String label);

    /**
     * Return the value for a pickItem according with specified labelName
     * and value
     * @param labelName Label to Find
     * @param value value or id to find
     * @return Pick Item value property or empty string "" if not found
     */
    public String getLabel(String labelName, String value);


}
