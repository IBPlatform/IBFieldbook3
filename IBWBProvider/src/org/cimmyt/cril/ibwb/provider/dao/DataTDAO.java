package org.cimmyt.cril.ibwb.provider.dao;


import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.cimmyt.cril.ibwb.api.dao.AbstractDAO;
import org.cimmyt.cril.ibwb.api.dao.utils.ValidatingDataType;
import org.cimmyt.cril.ibwb.domain.DataT;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.DetachedCriteria;
import org.springframework.orm.hibernate3.HibernateCallback;

public class DataTDAO extends AbstractDAO<DataT, Integer> {

	public DataTDAO() {
		super(DataT.class);
	}
	
	@Override
	protected void buildCriteria(DetachedCriteria criteria, DataT filter) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public String getKeyProperty() {
		return "variatid";
	}
	
    @Override
    public String getConsulta(DataT filtro) {
    	String query = "from DataT as d";
        
        if(filtro.getGlobalsearch() == null){
            if(filtro.getDataTPK() != null){
	        setQuery("d.dataTPK.ounitid", filtro.getDataTPK().getOunitid());
	        setQuery("d.dataTPK.variatid", filtro.getDataTPK().getVariatid());
            }
	    setQuery("d.dvalue", filtro.getDvalue());
        }else{
        	global = true;
	        if(ValidatingDataType.isNumeric(filtro.getGlobalsearch())){
	        	setQuery("d.dataTPK.ounitid", Integer.valueOf(filtro.getGlobalsearch()));
		        setQuery("d.dataTPK.variatid", Integer.valueOf(filtro.getGlobalsearch()));
		        setQueryInTo("d.dvalue", filtro.getGlobalsearch());
	        }else{
	        	setQueryInTo("d.dvalue", filtro.getGlobalsearch());
	        }
        }
        return query;
    }
}