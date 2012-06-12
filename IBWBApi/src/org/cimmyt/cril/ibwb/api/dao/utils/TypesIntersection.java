package org.cimmyt.cril.ibwb.api.dao.utils;

public enum TypesIntersection {
	inner	(1,	"inner",	"inner join",		"inner join fetch"),
	left	(2,	"left",		"left outer join",	"left join fetch"),
	right	(3,	"right",	"right outer join",	"right join fetch"),
	full	(4,	"full",		"full join",		"full join fetch");
	
	private Integer numIntersection;
	private String tipo;
	private String intersectionJoins;
	private String intersectionJoinsFetch;
	
	private TypesIntersection(
			Integer numIntersection,
			String tipo,
			String intersectionJoins,
			String intersectionJoinsFetch
			){
		this.numIntersection = numIntersection;
		this.tipo = tipo;
		this.intersectionJoins = intersectionJoins;
		this.intersectionJoinsFetch = intersectionJoinsFetch;
	}
	
	public static String getJoin(String tipo) {
		String out = null;
		for (TypesIntersection listIntersections: TypesIntersection.values()) {
			if (listIntersections.getTipo() == tipo ){
				out = listIntersections.getIntersectionJoins();
				break;
			}
		}
		return out;
	}
	
	public static String getJoinFetch(String tipo) {
		String out = null;
		for (TypesIntersection listIntersections: TypesIntersection.values()) {
			if (listIntersections.getTipo() == tipo ){
				out = listIntersections.getIntersectionJoinsFetch();
				break;
			}
		}
		return out;
	}
	
//------------------Encapsulado de campos------------------
	public Integer getNumIntersection() {
		return numIntersection;
	}

	public void setNumIntersection(Integer numIntersection) {
		this.numIntersection = numIntersection;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public String getIntersectionJoins() {
		return intersectionJoins;
	}

	public void setIntersectionJoins(String intersectionJoins) {
		this.intersectionJoins = intersectionJoins;
	}

	public String getIntersectionJoinsFetch() {
		return intersectionJoinsFetch;
	}

	public void setIntersectionJoinsFetch(String intersectionJoinsFetch) {
		this.intersectionJoinsFetch = intersectionJoinsFetch;
	}
}
