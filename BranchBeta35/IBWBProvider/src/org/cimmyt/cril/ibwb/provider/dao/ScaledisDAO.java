package org.cimmyt.cril.ibwb.provider.dao;


import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.cimmyt.cril.ibwb.api.dao.AbstractDAO;
import org.cimmyt.cril.ibwb.api.dao.utils.ValidatingDataType;
import org.cimmyt.cril.ibwb.domain.Scaledis;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.DetachedCriteria;
import org.springframework.orm.hibernate3.HibernateCallback;

public class ScaledisDAO extends AbstractDAO<Scaledis, Integer> {

	public ScaledisDAO() {
		super(Scaledis.class);
	}

	@Override
	protected void buildCriteria(DetachedCriteria criteria, Scaledis filter) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String getKeyProperty() {
		return "locationid";
	}

    @Override
    public String getConsulta(Scaledis filtro) {
    	String query = "from Scaledis as s";
        
        if(filtro.getGlobalsearch() == null){
            if(filtro.getScaledisPK() != null){
	        setQuery("s.scaledisPK.scaleid", filtro.getScaledisPK().getScaleid());
	        setQuery("s.scaledisPK.value", filtro.getScaledisPK().getValue());
            }
	    setQuery("s.valdesc", filtro.getValdesc());
        }else{
        	global = true;
	        if(ValidatingDataType.isNumeric(filtro.getGlobalsearch())){
	        	setQuery("s.scaledisPK.scaleid", Integer.valueOf(filtro.getGlobalsearch()));
		        setQuery("s.scaledisPK.value", Integer.valueOf(filtro.getGlobalsearch()));
		        setQueryInTo("s.valdesc", filtro.getGlobalsearch());
	        }else{
	        	setQueryInTo("s.valdesc", filtro.getGlobalsearch());
	        }
        }
        return query;
    }
}