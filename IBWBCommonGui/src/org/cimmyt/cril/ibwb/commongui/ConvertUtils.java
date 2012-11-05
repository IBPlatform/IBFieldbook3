/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.cimmyt.cril.ibwb.commongui;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author TMSANCHEZ
 */
public class ConvertUtils {

    public static final String DATE_PATTERN = "yyyyMMdd";
    private static final SimpleDateFormat format = new SimpleDateFormat(DATE_PATTERN);

    /**
     * Converts a Date to an Integer value
     *
     * @param dateToConvert
     * @return Date in format as DATE_PATTERN or 0 if can not convert
     */
    public static Integer getDateAsInteger(Date dateToConvert) {
        Integer dateAsInteger = 0;
        if (dateToConvert != null) {
            String stringDate = format.format(dateToConvert);
            try {
                dateAsInteger = Integer.parseInt(stringDate);
            } catch (Exception e) {
                dateAsInteger = 0;
            }
        }
        return dateAsInteger;
    }

    /**
     *
     * @param numToConvert
     * @return
     */
    public static Date getIntegerAsDate(Integer numToConvert) {
        Date integerAsDate = null;

        if (numToConvert != null) {
            String stringDate = numToConvert.toString();
            try {
                integerAsDate = format.parse(stringDate);
            } catch (Exception e) {
                integerAsDate = null;
            }
        }
        return integerAsDate;
    }

    /**
     * Converts a Date to a String value
     *
     * @param dateToConvert
     * @return Date in format as DATE_PATTERN or 0 if can not convert
     */
    public static String getDateAsString(Date dateToConvert) {
        String dateAsString = "";
        if (dateToConvert != null) {
            try {

                dateAsString = format.format(dateToConvert);
            } catch (Exception e) {
                dateAsString = "";
            }
        }
        return dateAsString;
    }

    /**
     *
     * @param value
     * @param num
     * @return
     */
    public static String getZeroLeading(Integer value, int num) {
        StringBuilder zeros = new StringBuilder();
        String valueAsString = value.toString();
        int zerosToAdd = num - valueAsString.length();

        for (int zeroCount = 0; zeroCount < zerosToAdd; zeroCount++) {
            zeros.append("0");
        }


        return zeros.toString() + valueAsString;
    }

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
     * Gets a value object as double
     *
     * @param value Value to convert
     * @return Integer value or 0 if can not convert
     */
    public static Double getValueAsDouble(Object value) {
        Double valueAsDouble = null;

        if (value == null) {
            // nothing!, is null
        } else if (value instanceof String) {
            String sValue = (String) value;
            try {
                if (!sValue.trim().isEmpty()) {
                    valueAsDouble = Double.parseDouble(sValue);
                }
            } catch (Exception e) {
                //e.printStackTrace();
                System.out.println("Cannot convert a String to Double " + e.getMessage());
            }
        } else if (value instanceof Integer) {
            valueAsDouble = new Double(value.toString());
        } else if (value instanceof Double) {
            Double doubleValue = (Double) value;
            try {
                valueAsDouble = doubleValue.doubleValue();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return valueAsDouble;
    }

    /**
     * Converts an Object to String
     * @param value
     * @return 
     */
    public static String getValueAsString(Object value) {
        String stringValue = null;

        if (value == null) {
            // nothing!, is null
        } else if (value instanceof String) {
            stringValue = (String) value;
        } else if (value instanceof Integer) {
            stringValue = value.toString();
        } else if (value instanceof Double) {
            stringValue = value.toString();
        }

        return stringValue;
    }    
    
    public static void main(String[] args) {
        System.out.println(getZeroLeading(5, 2));
    }
}
