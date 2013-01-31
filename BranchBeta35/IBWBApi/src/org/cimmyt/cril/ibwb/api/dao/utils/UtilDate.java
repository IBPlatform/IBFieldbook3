/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.cimmyt.cril.ibwb.api.dao.utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 *
 * @author gamaliel
 */
public class UtilDate {
        private static final String DATE_PATTERN = "yyyyMMdd";
    private static final SimpleDateFormat format = new SimpleDateFormat(DATE_PATTERN);
    
    public static Date addDays(Date date, Integer days){
        Calendar fechaCalendar = Calendar.getInstance();
        fechaCalendar.setTime(date);
        fechaCalendar.add(Calendar.DATE, days);
        return fechaCalendar.getTime();
    }
    
    public static Date setTimeToZeros(Date date){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(GregorianCalendar.HOUR,0);
        calendar.set(GregorianCalendar.MINUTE, 0);
        calendar.set(GregorianCalendar.SECOND, 0);
        return calendar.getTime();
    }
    
    public static Date addMonths(Date date, Integer months){
        Calendar fechaCalendar = Calendar.getInstance();
        fechaCalendar.setTime(date);
        fechaCalendar.add(Calendar.MONTH, months);
        return fechaCalendar.getTime();
    }
    
    public static Date addYears(Date date, Integer years){
        Calendar fechaCalendar = Calendar.getInstance();
        fechaCalendar.setTime(date);
        fechaCalendar.add(Calendar.YEAR, years);
        return fechaCalendar.getTime();
    }
    
    public static Date addHours(Date date, Integer hours){
        Calendar fechaCalendar = Calendar.getInstance();
        fechaCalendar.setTime(date);
        fechaCalendar.add(Calendar.HOUR, hours);
        return fechaCalendar.getTime();
    }
    
    public static Date addMinutes(Date date, Integer minutes){
        Calendar fechaCalendar = Calendar.getInstance();
        fechaCalendar.setTime(date);
        fechaCalendar.add(Calendar.MINUTE, minutes);
        return fechaCalendar.getTime();
    }
    
    public static Date addSeconds(Date date, Integer seconds){
        Calendar fechaCalendar = Calendar.getInstance();
        fechaCalendar.setTime(date);
        fechaCalendar.add(Calendar.SECOND, seconds);
        return fechaCalendar.getTime();
    }
    
    public static String uppercaseInicial(SimpleDateFormat fecha){
        String  dateString = fecha.toString();
        char inicial =dateString.charAt(0);
        StringBuffer resultado = new StringBuffer();
        resultado.append( String.valueOf(inicial).toUpperCase());
        for (int i = 1; i < dateString.toCharArray().length; i++) {
            resultado.append(dateString.charAt(i));
        }
        return resultado.toString();
    }
	
    public static String getFechaFormatoEncabezado(Date fecha){
        if(fecha==null){
                return "";
        }
        String resultado;
        Calendar fechaCalendar = Calendar.getInstance();
        fechaCalendar.setTime(fecha);
        resultado = getMesTexto(fechaCalendar.get(Calendar.MONTH)+1) + " " +
                Integer.toString(fechaCalendar.get(Calendar.DATE)) + " de " +
                Integer.toString(fechaCalendar.get(Calendar.YEAR));
        return resultado;
    }
    
    public static String getFechaFormatoEncabezadoMayus(Date fecha){
        if(fecha==null){
                return "";
        }
        String resultado;
        Calendar fechaCalendar = Calendar.getInstance();
        fechaCalendar.setTime(fecha);
        resultado = getMesTexto(fechaCalendar.get(Calendar.MONTH)+1).toUpperCase() + " " +
                Integer.toString(fechaCalendar.get(Calendar.DATE)) + " DE " +
                Integer.toString(fechaCalendar.get(Calendar.YEAR));
        return resultado;
    }
    
    public static String getFechaFormatoEncabezadoIngles(Date fecha){
        if(fecha==null){
                return "";
        }
        String resultado;
        Calendar fechaCalendar = Calendar.getInstance();
        fechaCalendar.setTime(fecha);
        resultado = getMesInglesTexto(fechaCalendar.get(Calendar.MONTH)+1) + " " +
                Integer.toString(fechaCalendar.get(Calendar.DATE)) + ", " +
                Integer.toString(fechaCalendar.get(Calendar.YEAR));
        return resultado;
    }

    public static String getFechaIngresoFormatoTexto(Date fecha){
        if(fecha==null){
                return null;
        }
        String resultado;
        Calendar fechaCalendar = Calendar.getInstance();
        fechaCalendar.setTime(fecha);
        resultado = Integer.toString(fechaCalendar.get(Calendar.DATE)) + " de " +
                getMesTexto(fechaCalendar.get(Calendar.MONTH)+1) + " de " +
                Integer.toString(fechaCalendar.get(Calendar.YEAR));
        return resultado;
    }

    private static String getMesTexto(Integer mes){
        switch (mes) {
            case 1: return "Enero";
            case 2: return "Febrero";
            case 3: return "Marzo";
            case 4: return "Abril";
            case 5: return "Mayo";
            case 6: return "Junio";
            case 7: return "Julio";
            case 8: return "Agosto";
            case 9: return "Septiembre";
            case 10: return "Octubre";
            case 11: return "Noviembre";
            case 12: return "Diciembre";
            default: return "";
        }
    }

    private static String getMesTextoMAYUS(Integer mes){
        switch (mes) {
            case 1: return "ENERO";
            case 2: return "FEBRERO";
            case 3: return "MARZO";
            case 4: return "ABRIL";
            case 5: return "MAYO";
            case 6: return "JUNIO";
            case 7: return "JULIO";
            case 8: return "AGOSTO";
            case 9: return "SEPTIEMBRE";
            case 10: return "OCTUBRE";
            case 11: return "NOVIEMBRE";
            case 12: return "DICIEMBRE";
            default: return "";
        }
    }

    private static String getMesInglesTexto(Integer mes){
        switch (mes) {
            case 1: return "January";
            case 2: return "February";
            case 3: return "March";
            case 4: return "April";
            case 5: return "May";
            case 6: return "June";
            case 7: return "July";
            case 8: return "August";
            case 9: return "September";
            case 10: return "October";
            case 11: return "November";
            case 12: return "December";
            default: return "";
        }
    }

    public static String getMesTexto(Date fecha){
        Calendar fechaCalendar = Calendar.getInstance();
        fechaCalendar.setTime(fecha);
        int mes=fechaCalendar.get(Calendar.MONTH)+1;
        switch (mes) {
            case 1: return "Enero";
            case 2: return "Febrero";
            case 3: return "Marzo";
            case 4: return "Abril";
            case 5: return "Mayo";
            case 6: return "Junio";
            case 7: return "Julio";
            case 8: return "Agosto";
            case 9: return "Septiembre";
            case 10: return "Octubre";
            case 11: return "Noviembre";
            case 12: return "Diciembre";
            default: return "";
        }
    }

    public static String getMesTextoMAYUS(Date fecha){
        Calendar fechaCalendar = Calendar.getInstance();
        fechaCalendar.setTime(fecha);
        int mes=fechaCalendar.get(Calendar.MONTH)+1;
        switch (mes) {
            case 1: return "ENERO";
            case 2: return "FEBRERO";
            case 3: return "MARZO";
            case 4: return "ABRIL";
            case 5: return "MAYO";
            case 6: return "JUNIO";
            case 7: return "JULIO";
            case 8: return "AGOSTO";
            case 9: return "SEPTIEMBRE";
            case 10: return "OCTUBRE";
            case 11: return "NOVIEMBRE";
            case 12: return "DICIEMBRE";
            default: return "";
        }
    }

    public static int getOnlyDay(Date date) {
        Calendar fechaCalendar = Calendar.getInstance();
        fechaCalendar.setTime(date);
        return fechaCalendar.get(Calendar.DATE);
    }

    public static int getOnlyMonth(Date date) {
        Calendar fechaCalendar = Calendar.getInstance();
        fechaCalendar.setTime(date);
        return fechaCalendar.get(Calendar.MONTH);
    }

    public static int getOnlyYear(Date date) {
        Calendar fechaCalendar = Calendar.getInstance();
        fechaCalendar.setTime(date);
        return fechaCalendar.get(Calendar.YEAR);
    }

    public static String getFechaPeriodo(Date fechaInicio,Date fechaTermino){
        String vigencia="";
        if(fechaInicio!=null){
                vigencia= "DEL " 
                + getOnlyDay(fechaInicio) 
                + " DE "
                + getMesTextoMAYUS(fechaInicio);
        }
        if(fechaTermino!=null){
                vigencia = vigencia+" AL "
                + getOnlyDay(fechaTermino) 
                + " DE "
                + getMesTextoMAYUS(fechaTermino) 
                + " DE "
                + getOnlyYear(fechaTermino);
        }

        return vigencia;
    }
    
    public static Integer getDateFormatNumberYEARandMONTHandDAY(Date date){
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(getOnlyYear(date));
        stringBuffer.append(getOnlyMonth(date));
        stringBuffer.append(getOnlyDay(date));
        return Integer.valueOf(stringBuffer.toString());
    }
    
   /**
     * Converts a Date to an Integer value
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
}
