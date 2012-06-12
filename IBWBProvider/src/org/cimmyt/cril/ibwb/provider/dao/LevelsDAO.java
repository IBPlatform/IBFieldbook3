package org.cimmyt.cril.ibwb.provider.dao;


import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.cimmyt.cril.ibwb.api.dao.AbstractDAO;
import org.cimmyt.cril.ibwb.api.dao.utils.ValidatingDataType;
import org.cimmyt.cril.ibwb.domain.Levels;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.DetachedCriteria;
import org.springframework.orm.hibernate3.HibernateCallback;

public class LevelsDAO extends AbstractDAO<Levels, Integer> {

    public LevelsDAO() {
            super(Levels.class);
    }

    @Override
    public Levels prepareToCreate(Levels levels) {
//        if (isLocal()) {
//            levels.setLevelno(getNextMin());
//        }
//        if (isCentral()) {
//            levels.setLevelno(getNextMax());
//        }
        return levels;
    }
    
    public Integer getNextLevelNo(){
        if (isLocal()) {
            return getNextMin();
        }
        if (isCentral()) {
            return getNextMax();
        }
        return 0;
    }

    @Override
    protected void buildCriteria(DetachedCriteria criteria, Levels filter) {
            // TODO Auto-generated method stub

    }

    @Override
    public String getKeyProperty() {
            return "levelno";
    }

    @Override
    public String getConsulta(Levels filtro) {
    	String query = "from Levels as l";
        
        if(filtro.getGlobalsearch() == null){
        	setQuery("l.levelno", filtro.getLevelno());
        	setQuery("l.factorid", filtro.getFactorid());
        }else{
        	global = true;
	        if(ValidatingDataType.isNumeric(filtro.getGlobalsearch())){
	        	setQuery("l.levelno", Integer.valueOf(filtro.getGlobalsearch()));
	        	setQuery("l.factorid", Integer.valueOf(filtro.getGlobalsearch()));
	        }else{
	        	
	        }
        }
        return query;
    }
}