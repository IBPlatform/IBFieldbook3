package org.cimmyt.cril.ibwb.provider.dao;


import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.cimmyt.cril.ibwb.api.dao.AbstractDAO;
import org.cimmyt.cril.ibwb.api.dao.utils.ValidatingDataType;
import org.cimmyt.cril.ibwb.domain.LevelT;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.DetachedCriteria;
import org.springframework.orm.hibernate3.HibernateCallback;

public class LevelTDAO extends AbstractDAO<LevelT, Integer> {

	public LevelTDAO() {
		super(LevelT.class);
	}

	@Override
	protected void buildCriteria(DetachedCriteria criteria, LevelT filter) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String getKeyProperty() {
		return "locationid";
	}

    @Override
    public String getConsulta(LevelT filtro) {
    	String query = "from LevelT as l";
        
        if(filtro.getGlobalsearch() == null){
            if(filtro.getLevelTPK() != null){
	        setQuery("l.levelTPK.labelid", filtro.getLevelTPK().getLabelid());
	        setQuery("l.levelTPK.levelno", filtro.getLevelTPK().getLevelno());
            }
	    setQuery("l.factorid", filtro.getFactorid());
	    setQuery("l.lvalue", filtro.getLvalue());
        }else{
        	global = true;
	        if(ValidatingDataType.isNumeric(filtro.getGlobalsearch())){
	        	setQuery("l.levelTPK.labelid", Integer.valueOf(filtro.getGlobalsearch()));
		        setQuery("l.levelTPK.levelno", Integer.valueOf(filtro.getGlobalsearch()));
		        setQuery("l.factorid", Integer.valueOf(filtro.getGlobalsearch()));
		        setQueryInTo("l.lvalue", filtro.getGlobalsearch());
	        }else{
	        	setQueryInTo("l.lvalue", filtro.getGlobalsearch());
	        }
        }
        return query;
    }
}