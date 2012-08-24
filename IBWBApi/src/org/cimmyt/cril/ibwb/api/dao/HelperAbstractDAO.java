package org.cimmyt.cril.ibwb.api.dao;

import java.math.BigDecimal;
import java.util.Date;
import org.cimmyt.cril.ibwb.api.dao.utils.Criterion;

public class HelperAbstractDAO {

	public static Criterion getQueryLikeAP(String campo, String valor){
		Criterion criterio = new Criterion();
		criterio.setCampo(campo);
		criterio.setValor(valor);
		//criterio.setConsulta(" " + campo + " like '" + valor + "%' ");
                criterio.setConsulta(" " + campo + " = '" + valor + "' ");
		return criterio;
	}
	
	public static Criterion getQueryIntoAP(String campo, String valor){
		Criterion criterio = new Criterion();
		criterio.setCampo(campo);
		criterio.setValor(valor);
		criterio.setConsulta(" " + campo + " like '%" + valor + "%' ");
                //criterio.setConsulta(" " + campo + " = '" + valor + "' ");
		return criterio;
	}
	
	public static Criterion getQueryAP(String campo, Integer valor){
		Criterion criterio = new Criterion();
		criterio.setCampo(campo);
		criterio.setValor(valor.toString());
		criterio.setConsulta(" " + campo + " = " + valor + " ");
		return criterio;
	}
	
	public static Criterion getQueryNotEqual(String campo, Integer valor){
		Criterion criterio = new Criterion();
		criterio.setCampo(campo);
		criterio.setValor(valor.toString());
		criterio.setConsulta(" " + campo + " <> " + valor + " ");
		return criterio;
	}
        
	public static Criterion getQueryAndNotEqual(String campo, Integer valor){
		Criterion criterio = new Criterion();
		criterio.setCampo(campo);
		criterio.setValor(valor.toString());
		criterio.setConsulta(" " + campo + " <> " + valor + " ");
                criterio.setOperador("and");
		return criterio;
	}        
        
        
	public static Criterion getQueryAP(String campo, BigDecimal valor){
		Criterion criterio = new Criterion();
		criterio.setCampo(campo);
		criterio.setValor(valor.toString());
		criterio.setConsulta(" " + campo + " = " + valor + " ");
		return criterio;
	}
	
	public static Criterion getQueryAP(String campo, Double valor){
		Criterion criterio = new Criterion();
		criterio.setCampo(campo);
		criterio.setValor(valor.toString());
		criterio.setConsulta(" " + campo + " = " + valor + " ");
		return criterio;
	}
	
	public static Criterion getQueryAP(String campo, Boolean valor){
		Criterion criterio = new Criterion();
		criterio.setCampo(campo);
		criterio.setValor(valor.toString());
		criterio.setConsulta(" " + campo + " = " + valor + " ");
		return criterio;
	}
	
	public static Criterion getQueryAP(String campo, Date valor){
		Criterion criterio = new Criterion();
		criterio.setCampo(campo);
		criterio.setValor(valor.toString());
		criterio.setConsulta(" " + campo + " = " + valor + " ");
		return criterio;
	}
	
	public static Criterion getQueryParametrizadaAP(String campo, Object valor, String parametro){
		Criterion criterio = new Criterion();
		criterio.setCampo(campo);
		criterio.setValor(valor.toString());
		criterio.setConsulta(" " + campo + " = " + parametro + " ");
		return criterio;
	}
	
	public static Criterion getQueryRangoAP(String campo, Object valor, String parameter, Object valor2, String parameter2){
		Criterion criterio = new Criterion();
		criterio.setCampo(campo);
		criterio.setValor(parameter + " and " + parameter2);
		criterio.setConsulta(" " + campo + " between " + criterio.getValor() + " ");
		return criterio;
	}
}
