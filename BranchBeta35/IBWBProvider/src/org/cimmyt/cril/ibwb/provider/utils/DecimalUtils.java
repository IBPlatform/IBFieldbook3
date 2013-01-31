package org.cimmyt.cril.ibwb.provider.utils;

import java.math.BigDecimal;

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
    
    public static Integer getValueAsInteger(Double value) {
        return value.intValue();
    }
    
    public static String getValueAsString(Double value) {
        return new BigDecimal(value).toString();
    }
    
    public static Object getValueAsString(Object value) {
        try {
            BigDecimal res = new BigDecimal((String)value);
            return res.toString();
	} catch (Exception e){
            return value;
	}
    }
 
    /**
     * It checks if a Double value has numbers after point sign (.)
     * @param value Double value to check
     * @return check
     */
    public static boolean isIntegerValue(Double value) {
//        boolean isIntegerValue = false;
//        
//        if (value != null) {
//            isIntegerValue = (value.doubleValue() - value.intValue()) == 0;
//        }
        
        BigDecimal temp = new BigDecimal(value);
        BigDecimal temp2 = new BigDecimal(temp.intValue());
        if(temp.compareTo(temp2) == 0){
            return true;
        }else{
            return false;
        }
//        return isIntegerValue;
    }
    
    public static boolean isDecimal(String cadena){
	try {
            new BigDecimal(cadena);
            return true;
	} catch (Exception e){
            return false;
	}
    }
}
