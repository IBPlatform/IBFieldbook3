package org.cimmyt.cril.ibwb.provider.dao;


import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.cimmyt.cril.ibwb.api.dao.AbstractDAO;
import org.cimmyt.cril.ibwb.api.dao.utils.ValidatingDataType;
import org.cimmyt.cril.ibwb.domain.Sndivs;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.DetachedCriteria;
import org.springframework.orm.hibernate3.HibernateCallback;

public class SndivsDAO extends AbstractDAO<Sndivs, Integer> {

	public SndivsDAO() {
		super(Sndivs.class);
	}
	
	@Override
	public Sndivs prepareToCreate(Sndivs sndivs) {
        if (isLocal()) {
        	sndivs.setSnlid(getNextMin());
		}
        if (isCentral()) {
        	sndivs.setSnlid(getNextMax());
		}
        return sndivs;
	}

	@Override
	protected void buildCriteria(DetachedCriteria criteria, Sndivs filter) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String getKeyProperty() {
		return "snlid";
	}

    @Override
    public String getConsulta(Sndivs filtro) {
    	String query = "from Sndivs as s";
        
        if(filtro.getGlobalsearch() == null){
        	setQuery("s.snlid", filtro.getSnlid());
        	setQuery("s.snlevel", filtro.getSnlevel());
        	setQuery("s.cntryid", filtro.getCntryid());
        	setQuery("s.snliso", filtro.getSnliso());
        	setQuery("s.snlfips", filtro.getSnlfips());
        	setQuery("s.isofull", filtro.getIsofull());
        	setQuery("s.schange", filtro.getSchange());
        }else{
        	global = true;
	        if(ValidatingDataType.isNumeric(filtro.getGlobalsearch())){
	        	setQuery("s.snlid", Integer.valueOf(filtro.getGlobalsearch()));
	        	setQuery("s.snlevel", Integer.valueOf(filtro.getGlobalsearch()));
	        	setQuery("s.cntryid", Integer.valueOf(filtro.getGlobalsearch()));
	        	setQueryInTo("s.snliso", filtro.getGlobalsearch());
	        	setQueryInTo("s.snlfips", filtro.getGlobalsearch());
	        	setQueryInTo("s.isofull", filtro.getGlobalsearch());
	        	setQuery("s.schange", Integer.valueOf(filtro.getGlobalsearch()));
	        }else{
	        	setQueryInTo("s.snliso", filtro.getGlobalsearch());
	        	setQueryInTo("s.snlfips", filtro.getGlobalsearch());
	        	setQueryInTo("s.isofull", filtro.getGlobalsearch());
	        }
        }
        return query;
    }
}