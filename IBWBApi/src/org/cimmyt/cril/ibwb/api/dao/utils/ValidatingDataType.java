/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.cimmyt.cril.ibwb.api.dao.utils;

import java.math.BigDecimal;

/**
 *
 * @author MasterGama
 */
public class ValidatingDataType {
	
	public static boolean isNumeric(String cadena) {
		try {
			Integer.parseInt(cadena);
			return true;
		} catch (NumberFormatException nfe) {
			return false;
		}
	}
	
	public static boolean isBigDecimal(String cadena) {
		try {
			new BigDecimal(cadena);
			return true;
		} catch (NumberFormatException nfe) {
			return false;
		}
	}
}
