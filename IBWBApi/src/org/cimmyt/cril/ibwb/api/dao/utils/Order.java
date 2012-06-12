package org.cimmyt.cril.ibwb.api.dao.utils;

public class Order {
	
	public final static boolean ASCENDENTE = true;
	public final static boolean DESCENDENTE = false;
	
	private String campo;
	private boolean orden;
	private String ordena;
	
	public void setCampo(String campo) {
		this.campo = campo;
	}
	
	public String getCampo(){
		return this.campo;
	}
	
	public void setOrden(boolean orden) {
		this.orden = orden;
	}
	
	public boolean getOrden(){
		return this.orden;
	}
	
	public void setOrdena(String ordena){
		this.ordena = ordena;
	}
	
	public String getOrdena(){
		return this.ordena;
	}
}
