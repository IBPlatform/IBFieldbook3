
package ibfb.nursery.persons;

import ibfb.nursery.editors.DateCellEditor;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


public class ValidityUtil {

    public ValidityUtil() {
    }

    /*
     * Utility to check the correct data type of a given value
     * Specifically, checks the check is done if the data type is N (numeric)
     * 
     * @param aValue - the actual value
     * @param columnDataType - correct data type of aValue
     * @return boolean - true if aValue if of type columnDataType
     */
    public static boolean isValidValueforDataType(Object aValue, String columnDataType) {
        boolean isValid = true;
        System.out.print("value datatype : " + aValue + " " + columnDataType);
        if ((columnDataType.toUpperCase()).equals("N")) {
            if (!isNumeric(aValue)) {
                isValid = false;
            }
        }

        return isValid;
    }

    public static boolean isValidValueforScale(Object aValue, String columnScale) {
        boolean isValid = true;

        if ((columnScale.toUpperCase()).startsWith("DATE")) {
            if (!isValidDate(aValue)) {
                isValid = false;
                System.out.println("InValid Date " + aValue);
            }
        }

        return isValid;
    }

    public static boolean isValidValue(Object aValue, String columnDataType, String columnScale) {
        boolean isValid = false;

        if (isValidValueforDataType(aValue, columnDataType)
                && isValidValueforScale(aValue, columnScale)) {
            return true;
        }


        return isValid;
    }

    public static boolean isNumeric(Object aValue) {
        boolean isNumeric = true;

        if (aValue instanceof Integer) {
            return isNumeric;
        }
        try {
            Double.parseDouble((String) aValue);
        } catch (NumberFormatException nme) {
            isNumeric = false;
        }
        return isNumeric;
    }

    public static boolean isValidDate(Object aValue) {
        Date date;
        String dateValue = (String) aValue;
        SimpleDateFormat sdf = new SimpleDateFormat(DateCellEditor.DATE_FORMAT);
        try {
            date = sdf.parse(dateValue);
        } catch (ParseException pe) {
            System.out.println(dateValue + " not in yyyymmmdd format : " + pe);
            return false;
        }

        if (!sdf.format(date).equals(dateValue)) {
            System.out.println("Invalid Date Entry.");
            return false;
        }

        return true;

    }
}
