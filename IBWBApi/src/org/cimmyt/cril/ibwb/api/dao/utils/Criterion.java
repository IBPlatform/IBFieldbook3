package org.cimmyt.cril.ibwb.api.dao.utils;

public class Criterion {
	
	private String campo;
	private String valor;
	private String consulta;
	
	public void setCampo(String campo) {
		this.campo = campo;
	}
	
	public String getCampo(){
		return this.campo;
	}
	
	public void setValor(String valor) {
		this.valor = valor;
	}
	
	public String getValor(){
		return this.valor;
	}
	
	public void setConsulta(String consulta){
		this.consulta = consulta;
	}
	
	public String getConsulta(){
		return this.consulta;
	}
}
