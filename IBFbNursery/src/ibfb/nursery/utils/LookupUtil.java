package ibfb.nursery.utils;

import ibfb.workbook.api.PickListResolver;
import ibfb.workbook.core.PickListResolverImpl;
import javax.swing.JTable;

public class LookupUtil {
    
    private static PickListResolver pickListResolver = PickListResolverImpl.instance();
    public static final String PERSON = "PERSON";
    public static final String LOCATION = "LOCATION";
    public static final String METHOD = "METHOD";
    public static final String DBID = "DBID";
    public static final String DBCV = "DBCV";

    public static void lookupPersonId(JTable searchTable, Object searchValue, int propertyColumn, int scaleColumn, int valueColumn) {
        for (int rowIndex = 0; rowIndex < searchTable.getRowCount(); rowIndex++) {
            Object property = searchTable.getValueAt(rowIndex, propertyColumn).toString().toUpperCase();
            Object scale = searchTable.getValueAt(rowIndex, scaleColumn).toString().toUpperCase();
            if (property.equals(PERSON) && scale.equals(DBID)) {
                searchTable.setValueAt(pickListResolver.getValue(PickListResolver.LABEL_PRINCIPAL_INVESTIGATOR, searchValue.toString()).toUpperCase(), rowIndex, valueColumn);
            }
        }
    }

    public static void lookupPersonName(JTable searchTable, Object searchValue, int propertyColumn, int scaleColumn, int valueColumn) {

        for (int rowIndex = 0; rowIndex < searchTable.getRowCount(); rowIndex++) {
            Object property = searchTable.getValueAt(rowIndex, propertyColumn).toString().toUpperCase();
            Object scale = searchTable.getValueAt(rowIndex, scaleColumn).toString().toUpperCase();
            if (property.equals(PERSON) && scale.equals(DBCV)) {
                searchTable.setValueAt(pickListResolver.getLabel(PickListResolver.LABEL_PRINCIPAL_INVESTIGATOR, searchValue.toString()).toUpperCase(), rowIndex, valueColumn);
            }
        }
    }
    
        public static void lookupMethodId(JTable searchTable, Object searchValue, int propertyColumn, int scaleColumn, int valueColumn) {
        for (int rowIndex = 0; rowIndex < searchTable.getRowCount(); rowIndex++) {
            Object property = searchTable.getValueAt(rowIndex, propertyColumn).toString().toUpperCase();
            Object scale = searchTable.getValueAt(rowIndex, scaleColumn).toString().toUpperCase();
            if (property.equals(METHOD) && scale.equals(DBID)) {
                searchTable.setValueAt(pickListResolver.getValue(PickListResolver.LABEL_METHOD, searchValue.toString()).toUpperCase(), rowIndex, valueColumn);
            }
        }
    }

    public static void lookupMethodName(JTable searchTable, Object searchValue, int propertyColumn, int scaleColumn, int valueColumn) {

        for (int rowIndex = 0; rowIndex < searchTable.getRowCount(); rowIndex++) {
            Object property = searchTable.getValueAt(rowIndex, propertyColumn).toString().toUpperCase();
            Object scale = searchTable.getValueAt(rowIndex, scaleColumn).toString().toUpperCase();
            if (property.equals(METHOD) && scale.equals(DBCV)) {
                searchTable.setValueAt(pickListResolver.getLabel(PickListResolver.LABEL_METHOD, searchValue.toString()).toUpperCase(), rowIndex, valueColumn);
            }
        }
    }

    public static void lookupLocationId(JTable searchTable, Object searchValue, int propertyColumn, int scaleColumn, int valueColumn) {

        for (int rowIndex = 0; rowIndex < searchTable.getRowCount(); rowIndex++) {
            Object property = searchTable.getValueAt(rowIndex, propertyColumn).toString().toUpperCase();
            Object scale = searchTable.getValueAt(rowIndex, scaleColumn).toString().toUpperCase();
            if (property.equals(LOCATION) && scale.equals(DBID)) {
                searchTable.setValueAt(pickListResolver.getValue(PickListResolver.LABEL_SITE, searchValue.toString()).toUpperCase(), rowIndex, valueColumn);
            }
        }
    }

    public static void lookupLocationName(JTable searchTable, Object searchValue, int propertyColumn, int scaleColumn, int valueColumn) {

        for (int rowIndex = 0; rowIndex < searchTable.getRowCount(); rowIndex++) {
            Object property = searchTable.getValueAt(rowIndex, propertyColumn).toString().toUpperCase();
            Object scale = searchTable.getValueAt(rowIndex, scaleColumn).toString().toUpperCase();
            if (property.equals(LOCATION) && scale.equals(DBCV)) {
                searchTable.setValueAt(pickListResolver.getLabel(PickListResolver.LABEL_SITE, searchValue.toString()).toUpperCase(), rowIndex, valueColumn);
            }
        }
    }

    public static void lookupPersonId(JTable searchTable, int instance, Object searchValue, int propertyColumn, int scaleColumn, int valueColumn) {

        for (int rowIndex = 0; rowIndex < searchTable.getRowCount(); rowIndex++) {
            int currentInstance = Integer.parseInt(searchTable.getValueAt(rowIndex, 0).toString());
            Object property = searchTable.getValueAt(rowIndex, propertyColumn).toString().toUpperCase();
            Object scale = searchTable.getValueAt(rowIndex, scaleColumn).toString().toUpperCase();
            if (currentInstance == instance && property.equals(PERSON) && scale.equals(DBID)) {
                searchTable.setValueAt(pickListResolver.getValue(PickListResolver.LABEL_PRINCIPAL_INVESTIGATOR, searchValue.toString()).toUpperCase(), rowIndex, valueColumn);
            }
        }
    }

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
    
    
    public static void lookupMethodId(JTable searchTable, int instance, Object searchValue, int propertyColumn, int scaleColumn, int valueColumn) {
        for (int rowIndex = 0; rowIndex < searchTable.getRowCount(); rowIndex++) {
            int currentInstance = Integer.parseInt(searchTable.getValueAt(rowIndex, 0).toString());
            Object property = searchTable.getValueAt(rowIndex, propertyColumn).toString().toUpperCase();
            Object scale = searchTable.getValueAt(rowIndex, scaleColumn).toString().toUpperCase();
            if (currentInstance == instance && property.equals(METHOD) && scale.equals(DBID)) {
                searchTable.setValueAt(pickListResolver.getValue(PickListResolver.LABEL_METHOD, searchValue.toString()).toUpperCase(), rowIndex, valueColumn);
            }
        }
    }

    public static void lookupMethodName(JTable searchTable, int instance, Object searchValue, int propertyColumn, int scaleColumn, int valueColumn) {
        for (int rowIndex = 0; rowIndex < searchTable.getRowCount(); rowIndex++) {
            int currentInstance = Integer.parseInt(searchTable.getValueAt(rowIndex, 0).toString());
            Object property = searchTable.getValueAt(rowIndex, propertyColumn).toString().toUpperCase();
            Object scale = searchTable.getValueAt(rowIndex, scaleColumn).toString().toUpperCase();
            if (currentInstance == instance && property.equals(METHOD) && scale.equals(DBCV)) {
                searchTable.setValueAt(pickListResolver.getLabel(PickListResolver.LABEL_METHOD, searchValue.toString()).toUpperCase(), rowIndex, valueColumn);
            }
        }
    }
    
}
