package org.cimmyt.cril.ibwb.provider.dao;


import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.cimmyt.cril.ibwb.api.dao.AbstractDAO;
import org.cimmyt.cril.ibwb.api.dao.utils.ValidatingDataType;
import org.cimmyt.cril.ibwb.domain.Locdes;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.DetachedCriteria;
import org.springframework.orm.hibernate3.HibernateCallback;

public class LocdesDAO extends AbstractDAO<Locdes, Integer> {

	public LocdesDAO() {
		super(Locdes.class);
	}
	
	@Override
	public Locdes prepareToCreate(Locdes locdes) {
        if (isLocal()) {
        	locdes.setLdid(getNextMin());
		}
        if (isCentral()) {
        	locdes.setLdid(getNextMax());
		}
        return locdes;
	}

	@Override
	protected void buildCriteria(DetachedCriteria criteria, Locdes filter) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String getKeyProperty() {
		return "ldid";
	}
	
    @Override
    public String getConsulta(Locdes filtro) {
    	String query = "from Locdes as l";
        
        if(filtro.getGlobalsearch() == null){
        	setQuery("l.ldid", filtro.getLdid());
        	setQuery("l.locid", filtro.getLocid());
        	setQuery("l.dtype", filtro.getDtype());
        	setQuery("l.duid", filtro.getDuid());
        	setQuery("l.dval", filtro.getDval());
        	setQuery("l.ddate", filtro.getDdate());
        	setQuery("l.dref", filtro.getDref());
        }else{
        	global = true;
	        if(ValidatingDataType.isNumeric(filtro.getGlobalsearch())){
	        	setQuery("l.ldid", Integer.valueOf(filtro.getGlobalsearch()));
	        	setQuery("l.locid", Integer.valueOf(filtro.getGlobalsearch()));
	        	setQuery("l.dtype", Integer.valueOf(filtro.getGlobalsearch()));
	        	setQuery("l.duid", Integer.valueOf(filtro.getGlobalsearch()));
	        	setQueryInTo("l.dval", filtro.getGlobalsearch());
	        	setQuery("l.ddate", Integer.valueOf(filtro.getGlobalsearch()));
	        	setQuery("l.dref", Integer.valueOf(filtro.getGlobalsearch()));
	        }else{
	        	setQueryInTo("l.dval", filtro.getGlobalsearch());
	        }
        }
        return query;
    }
}