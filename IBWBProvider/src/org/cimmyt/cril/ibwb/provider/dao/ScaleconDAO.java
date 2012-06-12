package org.cimmyt.cril.ibwb.provider.dao;


import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.cimmyt.cril.ibwb.api.dao.AbstractDAO;
import org.cimmyt.cril.ibwb.api.dao.utils.ValidatingDataType;
import org.cimmyt.cril.ibwb.domain.Scalecon;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.DetachedCriteria;
import org.springframework.orm.hibernate3.HibernateCallback;

public class ScaleconDAO extends AbstractDAO<Scalecon, Integer> {

	public ScaleconDAO() {
		super(Scalecon.class);
	}

	@Override
	protected void buildCriteria(DetachedCriteria criteria, Scalecon filter) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String getKeyProperty() {
		return "locationid";
	}

    @Override
    public String getConsulta(Scalecon filtro) {
    	String query = "from Scalecon as s";
        
        if(filtro.getGlobalsearch() == null){
            if(filtro.getScaleconPK() != null){
	        setQuery("s.scaleconPK.scaleid", filtro.getScaleconPK().getScaleid());
	        setQuery("s.scaleconPK.slevel", filtro.getScaleconPK().getSlevel());
	        setQuery("s.scaleconPK.elevel", filtro.getScaleconPK().getElevel());
            }
        }else{
        	global = true;
	        if(ValidatingDataType.isNumeric(filtro.getGlobalsearch())){
	        	setQuery("s.scaleconPK.scaleid", Integer.valueOf(filtro.getGlobalsearch()));
		        setQuery("s.scaleconPK.slevel", Integer.valueOf(filtro.getGlobalsearch()));
		        setQuery("s.scaleconPK.elevel", Integer.valueOf(filtro.getGlobalsearch()));
	        }else{
	        	
	        }
        }
        return query;
    }
}