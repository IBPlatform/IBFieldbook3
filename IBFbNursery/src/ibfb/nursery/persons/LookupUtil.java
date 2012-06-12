
package ibfb.nursery.persons;

import ibfb.workbook.api.PickListResolver;
import ibfb.workbook.core.PickListResolverImpl;
import javax.swing.JTable;


public class LookupUtil {

    private static PickListResolver pickListResolver = PickListResolverImpl.instance();
    public static final String PERSON = "PERSON";
    public static final String LOCATION = "LOCATION";
    public static final String DBID = "DBID";
    public static final String DBCV = "DBCV";

    /**
     * Checks if searchValue exists in pickupListResolver  and if found then assign it corresponding ID
     * @param searchTable Table containing all columns
     * @param searchValue value to search
     * @param propertyColumn  column number for property
     * @param scaleColumn column number for scale
     * @param valueColumn  column number used to assign found value
     */
    public static void lookupPersonId(JTable searchTable, Object searchValue, int propertyColumn, int scaleColumn, int valueColumn) {

        for (int rowIndex = 0; rowIndex < searchTable.getRowCount(); rowIndex++) {
            Object property = searchTable.getValueAt(rowIndex, propertyColumn).toString().toUpperCase();
            Object scale = searchTable.getValueAt(rowIndex, scaleColumn).toString().toUpperCase();
            if (property.equals(PERSON) && scale.equals(DBID)) {
                searchTable.setValueAt(pickListResolver.getValue(PickListResolver.LABEL_PRINCIPAL_INVESTIGATOR, searchValue.toString()).toUpperCase(), rowIndex, valueColumn);
            }
        }
    }
    
    /**
     * Checks if searchValue exists in pickupListResolver  and if found then assign it corresponding NAME
     * @param searchTable Table containing all columns
     * @param searchValue value to search
     * @param propertyColumn  column number for property
     * @param scaleColumn column number for scale
     * @param valueColumn  column number used to assign found value
     */
    public static void lookupPersonName(JTable searchTable, Object searchValue, int propertyColumn, int scaleColumn, int valueColumn) {

        for (int rowIndex = 0; rowIndex < searchTable.getRowCount(); rowIndex++) {
            Object property = searchTable.getValueAt(rowIndex, propertyColumn).toString().toUpperCase();
            Object scale = searchTable.getValueAt(rowIndex, scaleColumn).toString().toUpperCase();
            if (property.equals(PERSON) && scale.equals(DBCV)) {
                searchTable.setValueAt(pickListResolver.getLabel(PickListResolver.LABEL_PRINCIPAL_INVESTIGATOR, searchValue.toString()).toUpperCase(), rowIndex, valueColumn);
            }
        }
    }   
    
 /**
     * Checks if searchValue exists in pickupListResolver  and if found then assign it corresponding ID
     * @param searchTable Table containing all columns
     * @param searchValue value to search
     * @param propertyColumn  column number for property
     * @param scaleColumn column number for scale
     * @param valueColumn  column number used to assign found value
     */
    public static void lookupLocationId(JTable searchTable, Object searchValue, int propertyColumn, int scaleColumn, int valueColumn) {

        for (int rowIndex = 0; rowIndex < searchTable.getRowCount(); rowIndex++) {
            Object property = searchTable.getValueAt(rowIndex, propertyColumn).toString().toUpperCase();
            Object scale = searchTable.getValueAt(rowIndex, scaleColumn).toString().toUpperCase();
            if (property.equals(LOCATION) && scale.equals(DBID)) {
                searchTable.setValueAt(pickListResolver.getValue(PickListResolver.LABEL_SITE, searchValue.toString()).toUpperCase(), rowIndex, valueColumn);
            }
        }
    }
    
    /**
     * Checks if searchValue exists in pickupListResolver  and if found then assign it corresponding NAME
     * @param searchTable Table containing all columns
     * @param searchValue value to search
     * @param propertyColumn  column number for property
     * @param scaleColumn column number for scale
     * @param valueColumn  column number used to assign found value
     */
    public static void lookupLocationName(JTable searchTable, Object searchValue, int propertyColumn, int scaleColumn, int valueColumn) {

        for (int rowIndex = 0; rowIndex < searchTable.getRowCount(); rowIndex++) {
            Object property = searchTable.getValueAt(rowIndex, propertyColumn).toString().toUpperCase();
            Object scale = searchTable.getValueAt(rowIndex, scaleColumn).toString().toUpperCase();
            if (property.equals(LOCATION) && scale.equals(DBCV)) {
                searchTable.setValueAt(pickListResolver.getLabel(PickListResolver.LABEL_SITE, searchValue.toString()).toUpperCase(), rowIndex, valueColumn);
            }
        }
    }      
    
    
 /**
     * Checks if searchValue exists in pickupListResolver  and if found then assign it corresponding ID
     * @param searchTable Table containing all columns
     * @param instance number of instance to search
     * @param searchValue value to search
     * @param propertyColumn  column number for property
     * @param scaleColumn column number for scale
     * @param valueColumn  column number used to assign found value
     */
    public static void lookupPersonId(JTable searchTable, int instance, Object searchValue, int propertyColumn, int scaleColumn, int valueColumn) {

        for (int rowIndex = 0; rowIndex < searchTable.getRowCount(); rowIndex++) {
            int currentInstance = Integer.parseInt(searchTable.getValueAt(rowIndex, 0).toString());
            Object property = searchTable.getValueAt(rowIndex, propertyColumn).toString().toUpperCase();
            Object scale = searchTable.getValueAt(rowIndex, scaleColumn).toString().toUpperCase();
            if ( currentInstance == instance && property.equals(PERSON) && scale.equals(DBID)) {
                searchTable.setValueAt(pickListResolver.getValue(PickListResolver.LABEL_PRINCIPAL_INVESTIGATOR, searchValue.toString()).toUpperCase(), rowIndex, valueColumn);
            }
        }
    }
    
    /**
     * Checks if searchValue exists in pickupListResolver  and if found then assign it corresponding NAME
     * @param searchTable Table containing all columns
     * @param instance number of instance to search
     * @param searchValue value to search
     * @param propertyColumn  column number for property
     * @param scaleColumn column number for scale
     * @param valueColumn  column number used to assign found value
     */
    public static void lookupPersonName(JTable searchTable, int instance, Object searchValue, int propertyColumn, int scaleColumn, int valueColumn) {

        for (int rowIndex = 0; rowIndex < searchTable.getRowCount(); rowIndex++) {
            int currentInstance = Integer.parseInt(searchTable.getValueAt(rowIndex, 0).toString());            
            Object property = searchTable.getValueAt(rowIndex, propertyColumn).toString().toUpperCase();
            Object scale = searchTable.getValueAt(rowIndex, scaleColumn).toString().toUpperCase();
            if (currentInstance == instance && property.equals(PERSON) && scale.equals(DBCV)) {
                searchTable.setValueAt(pickListResolver.getLabel(PickListResolver.LABEL_PRINCIPAL_INVESTIGATOR, searchValue.toString()).toUpperCase(), rowIndex, valueColumn);
            }
        }
    }   
    
 /**
     * Checks if searchValue exists in pickupListResolver  and if found then assign it corresponding ID
     * @param searchTable Table containing all columns
     * @param instance number of instance to search
     * @param searchValue value to search
     * @param propertyColumn  column number for property
     * @param scaleColumn column number for scale
     * @param valueColumn  column number used to assign found value
     */
    public static void lookupLocationId(JTable searchTable, int instance, Object searchValue, int propertyColumn, int scaleColumn, int valueColumn) {

        for (int rowIndex = 0; rowIndex < searchTable.getRowCount(); rowIndex++) {
            int currentInstance = Integer.parseInt(searchTable.getValueAt(rowIndex, 0).toString());            
            Object property = searchTable.getValueAt(rowIndex, propertyColumn).toString().toUpperCase();
            Object scale = searchTable.getValueAt(rowIndex, scaleColumn).toString().toUpperCase();
            if (currentInstance == instance && property.equals(LOCATION) && scale.equals(DBID)) {
                searchTable.setValueAt(pickListResolver.getValue(PickListResolver.LABEL_SITE, searchValue.toString()).toUpperCase(), rowIndex, valueColumn);
            }
        }
    }
    
    /**
     * Checks if searchValue exists in pickupListResolver  and if found then assign it corresponding NAME
     * @param searchTable Table containing all columns
     * @param instance number of instance to search
     * @param searchValue value to search
     * @param propertyColumn  column number for property
     * @param scaleColumn column number for scale
     * @param valueColumn  column number used to assign found value
     */
    public static void lookupLocationName(JTable searchTable, int instance, Object searchValue, int propertyColumn, int scaleColumn, int valueColumn) {

        for (int rowIndex = 0; rowIndex < searchTable.getRowCount(); rowIndex++) {
            int currentInstance = Integer.parseInt(searchTable.getValueAt(rowIndex, 0).toString());            
            Object property = searchTable.getValueAt(rowIndex, propertyColumn).toString().toUpperCase();
            Object scale = searchTable.getValueAt(rowIndex, scaleColumn).toString().toUpperCase();
            if (currentInstance == instance && property.equals(LOCATION) && scale.equals(DBCV)) {
                searchTable.setValueAt(pickListResolver.getLabel(PickListResolver.LABEL_SITE, searchValue.toString()).toUpperCase(), rowIndex, valueColumn);
            }
        }
    }      
        
}
