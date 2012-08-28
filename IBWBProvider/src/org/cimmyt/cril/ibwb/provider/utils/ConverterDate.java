package org.cimmyt.cril.ibwb.provider.utils;

import java.util.Calendar;
import java.util.Date;

public class ConverterDate {
	
	public static Integer getInteger(Date fecha){
		if(fecha != null){
			Calendar fechaCalendar = Calendar.getInstance();
			fechaCalendar.setTime(fecha);
			StringBuffer resultado = new StringBuffer();
			resultado.append(fechaCalendar.get(Calendar.YEAR));
                        Integer mesTemp = fechaCalendar.get(Calendar.MONTH) + 1;
                        if(mesTemp < 10){
                            resultado.append("0" + mesTemp);
                        }else{
                            resultado.append(mesTemp);
                        }
                        Integer diaTemp = fechaCalendar.get(Calendar.DATE);
                        if(diaTemp < 10){
                            resultado.append("0" + diaTemp);
                        }else {
                            resultado.append(diaTemp);
                        }
			return Integer.valueOf(resultado.toString());
		}
		return null;
	}
	
	public static Date getDate(Integer fecha){
		if(fecha != null){
			Calendar fechaCalendar = Calendar.getInstance();
			Integer dia = fecha/1000000;
			Integer mes = (fecha/10000)%100;
			Integer anio = fecha%10000;
			fechaCalendar.set(anio, mes, dia);
			return fechaCalendar.getTime();
		}
		return null;
	}
}