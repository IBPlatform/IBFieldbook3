package org.cimmyt.cril.ibwb.provider.dao;


import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.cimmyt.cril.ibwb.api.dao.AbstractDAO;
import org.cimmyt.cril.ibwb.api.dao.utils.ValidatingDataType;
import org.cimmyt.cril.ibwb.domain.Datattr;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.DetachedCriteria;
import org.springframework.orm.hibernate3.HibernateCallback;

public class DatattrDAO extends AbstractDAO<Datattr, Integer> {

	public DatattrDAO() {
		super(Datattr.class);
	}
	
	@Override
	public Datattr prepareToCreate(Datattr datattr) {
        if (isLocal()) {
        	datattr.setDattrid(getNextMin());
		}
        if (isCentral()) {
        	datattr.setDattrid(getNextMax());
		}
        return datattr;
	}

	@Override
	protected void buildCriteria(DetachedCriteria criteria, Datattr filter) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public String getKeyProperty() {
		return "dattrid";
	}
	
    @Override
    public String getConsulta(Datattr filtro) {
    	String query = "from Datattr as d";
        
        if(filtro.getGlobalsearch() == null){
        	setQuery("d.dattrid", filtro.getDattrid());
        	setQuery("d.datype", filtro.getDatype());
        	setQuery("d.datable", filtro.getDatable());
        	setQuery("d.ounitid", filtro.getOunitid());
        	setQuery("d.variatid", filtro.getVariatid());
        	setQuery("d.datval", filtro.getDatval());
        }else{
        	global = true;
	        if(ValidatingDataType.isNumeric(filtro.getGlobalsearch())){
	        	setQuery("d.dattrid", Integer.valueOf(filtro.getGlobalsearch()));
	        	setQuery("d.datype", Integer.valueOf(filtro.getGlobalsearch()));
	        	setQueryInTo("d.datable", filtro.getGlobalsearch());
	        	setQuery("d.ounitid", Integer.valueOf(filtro.getGlobalsearch()));
	        	setQuery("d.variatid", Integer.valueOf(filtro.getGlobalsearch()));
	        	setQueryInTo("d.datval", filtro.getGlobalsearch());
	        }else{
	        	setQueryInTo("d.datable", filtro.getGlobalsearch());
	        	setQueryInTo("d.datval", filtro.getGlobalsearch());
	        }
        }
        return query;
    }
}