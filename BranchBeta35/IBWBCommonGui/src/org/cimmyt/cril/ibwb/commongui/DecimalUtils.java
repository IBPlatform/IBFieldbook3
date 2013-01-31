/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.cimmyt.cril.ibwb.commongui;

/**
 *
 * @author tmsg
 */
public class DecimalUtils {

    /**
     * Gets a value object as Integer
     *
     * @param value Value to convert
     * @return Integer value or 0 if can not convert
     */
    public static Integer getValueAsInteger(Object value) {
        Integer valueAsInteger = 0;

        if (value instanceof String) {
            String sValue = (String) value;
            try {
                if (sValue.contains(".")) {
                    try {
                        Double tmpDouble = Double.parseDouble(sValue);
                        valueAsInteger = tmpDouble.intValue();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                } else {
                    if (!sValue.trim().isEmpty()) {
                        valueAsInteger = Integer.parseInt(sValue);
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (value instanceof Integer) {
            valueAsInteger = (Integer) value;
        } else if (value instanceof Double) {
            Double doubleValue = (Double) value;
            try {
                valueAsInteger = doubleValue.intValue();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return valueAsInteger;
    }

    /**
     * It checks if a Double value has numbers after point sign (.)
     *
     * @param value Double value to check
     * @return check
     */
    public static boolean isIntegerValue(Double value) {
        boolean isIntegerValue = false;

        if (value != null) {
            isIntegerValue = (value.doubleValue() - value.intValue()) == 0;
        }

        return isIntegerValue;

    }
}
