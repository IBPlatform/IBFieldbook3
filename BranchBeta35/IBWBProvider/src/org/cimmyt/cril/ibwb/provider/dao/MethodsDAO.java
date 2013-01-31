package org.cimmyt.cril.ibwb.provider.dao;


import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.cimmyt.cril.ibwb.api.dao.AbstractDAO;
import org.cimmyt.cril.ibwb.api.dao.utils.ValidatingDataType;
import org.cimmyt.cril.ibwb.domain.Methods;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.DetachedCriteria;
import org.springframework.orm.hibernate3.HibernateCallback;

public class MethodsDAO extends AbstractDAO<Methods, Integer> {

	public MethodsDAO() {
		super(Methods.class);
	}
	
	@Override
	public Methods prepareToCreate(Methods methods) {
        if (isLocal()) {
        	methods.setMid(getNextMin());
		}
        if (isCentral()) {
        	methods.setMid(getNextMax());
		}
        return methods;
	}

	@Override
	protected void buildCriteria(DetachedCriteria criteria, Methods filter) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String getKeyProperty() {
		return "mid";
	}

    @Override
    public String getConsulta(Methods filtro) {
    	String query = "from Methods as m";
        
        if(filtro.getGlobalsearch() == null){
        	setQuery("m.mid", filtro.getLmid());
        	setQuery("m.mtype", filtro.getMtype());
        	setQuery("m.mgrp", filtro.getMgrp());
        	setQuery("m.mcode", filtro.getMcode());
        	setQuery("m.mname", filtro.getMname());
        	setQuery("m.mdesc", filtro.getMdesc());
        	setQuery("m.mref", filtro.getMref());
        	setQuery("m.mprgn", filtro.getMprgn());
        	setQuery("m.mfprg", filtro.getMfprg());
        	setQuery("m.mattr", filtro.getMattr());
        	setQuery("m.geneq", filtro.getGeneq());
        	setQuery("m.muid", filtro.getMuid());
        	setQuery("m.lmid", filtro.getLmid());
        	setQuery("m.mdate", filtro.getMdate());
        }else{
        	global = true;
	        if(ValidatingDataType.isNumeric(filtro.getGlobalsearch())){
	        	setQuery("m.mid", Integer.valueOf(filtro.getGlobalsearch()));
	        	setQueryInTo("m.mtype", filtro.getGlobalsearch());
	        	setQueryInTo("m.mgrp", filtro.getGlobalsearch());
	        	setQueryInTo("m.mcode", filtro.getGlobalsearch());
	        	setQueryInTo("m.mname", filtro.getGlobalsearch());
	        	setQueryInTo("m.mdesc", filtro.getGlobalsearch());
	        	setQuery("m.mref", Integer.valueOf(filtro.getGlobalsearch()));
	        	setQuery("m.mprgn", Integer.valueOf(filtro.getGlobalsearch()));
	        	setQuery("m.mfprg", Integer.valueOf(filtro.getGlobalsearch()));
	        	setQuery("m.mattr", Integer.valueOf(filtro.getGlobalsearch()));
	        	setQuery("m.geneq", Integer.valueOf(filtro.getGlobalsearch()));
	        	setQuery("m.muid", Integer.valueOf(filtro.getGlobalsearch()));
	        	setQuery("m.lmid", Integer.valueOf(filtro.getGlobalsearch()));
	        	setQuery("m.mdate", Integer.valueOf(filtro.getGlobalsearch()));
	        }else{
	        	setQueryInTo("m.mtype", filtro.getGlobalsearch());
	        	setQueryInTo("m.mgrp", filtro.getGlobalsearch());
	        	setQueryInTo("m.mcode", filtro.getGlobalsearch());
	        	setQueryInTo("m.mname", filtro.getGlobalsearch());
	        	setQueryInTo("m.mdesc", filtro.getGlobalsearch());
	        }
        }
        return query;
    }
}