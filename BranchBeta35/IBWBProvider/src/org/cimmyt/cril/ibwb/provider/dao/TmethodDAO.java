package org.cimmyt.cril.ibwb.provider.dao;


import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.cimmyt.cril.ibwb.api.dao.AbstractDAO;
import org.cimmyt.cril.ibwb.api.dao.utils.ValidatingDataType;
import org.cimmyt.cril.ibwb.domain.Tmethod;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.DetachedCriteria;
import org.springframework.orm.hibernate3.HibernateCallback;

public class TmethodDAO extends AbstractDAO<Tmethod, Integer> {

	public TmethodDAO() {
		super(Tmethod.class);
	}
	
	@Override
	public Tmethod prepareToCreate(Tmethod tmethod) {
        if (isLocal()) {
        	tmethod.setTmethid(getNextMin());
		}
        if (isCentral()) {
        	tmethod.setTmethid(getNextMax());
		}
        return tmethod;
	}

	@Override
	protected void buildCriteria(DetachedCriteria criteria, Tmethod filter) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String getKeyProperty() {
		return "tmethid";
	}

    @Override
    public String getConsulta(Tmethod filtro) {
    	String query = "from Tmethod as t";
        
        if(filtro.getGlobalsearch() == null){
        	setQuery("t.tmethid", filtro.getTmethid());
        	setQuery("t.tmname", filtro.getTmname());
        	setQuery("t.traitid", filtro.getTraitid());
        	setQuery("t.tmabbr", filtro.getTmabbr());
        	setQuery("t.tmdesc", filtro.getTmdesc());
        }else{
        	global = true;
	        if(ValidatingDataType.isNumeric(filtro.getGlobalsearch())){
	        	setQuery("t.tmethid", Integer.valueOf(filtro.getGlobalsearch()));
	        	setQueryInTo("t.tmname", filtro.getGlobalsearch());
	        	setQuery("t.traitid", Integer.valueOf(filtro.getGlobalsearch()));
	        	setQueryInTo("t.tmabbr", filtro.getGlobalsearch());
	        	setQueryInTo("t.tmdesc", filtro.getGlobalsearch());
	        }else{
	        	setQueryInTo("t.tmname", filtro.getGlobalsearch());
	        	setQueryInTo("t.tmabbr", filtro.getGlobalsearch());
	        	setQueryInTo("t.tmdesc", filtro.getGlobalsearch());
	        }
        }
        return query;
    }
}