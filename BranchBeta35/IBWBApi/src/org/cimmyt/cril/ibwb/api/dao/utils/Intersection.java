package org.cimmyt.cril.ibwb.api.dao.utils;

public class Intersection {

	public final static String inner = "inner";
	public final static String left = "left";
	public final static String right = "right";
	public final static String full = "full";
	
	private String campo;
	private String intersection;
	
	public String getCampo() {
		return campo;
	}
	public void setCampo(String campo) {
		this.campo = campo;
	}
	public String getIntersection() {
		return intersection;
	}
	public void setIntersection(String intersection) {
		this.intersection = intersection;
	}
	
}
