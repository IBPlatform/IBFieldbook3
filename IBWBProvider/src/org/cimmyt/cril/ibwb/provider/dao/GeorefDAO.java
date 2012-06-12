package org.cimmyt.cril.ibwb.provider.dao;


import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.cimmyt.cril.ibwb.api.dao.AbstractDAO;
import org.cimmyt.cril.ibwb.api.dao.utils.ValidatingDataType;
import org.cimmyt.cril.ibwb.domain.Georef;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.DetachedCriteria;
import org.springframework.orm.hibernate3.HibernateCallback;

public class GeorefDAO extends AbstractDAO<Georef, Integer> {

	public GeorefDAO() {
		super(Georef.class);
	}
	
	@Override
	public Georef prepareToCreate(Georef georef) {
        if (isLocal()) {
        	georef.setLocid(getNextMin());
		}
        if (isCentral()) {
        	georef.setLocid(getNextMax());
		}
        return georef;
	}

	@Override
	protected void buildCriteria(DetachedCriteria criteria, Georef filter) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String getKeyProperty() {
		return "locid";
	}

    @Override
    public String getConsulta(Georef filtro) {
    	String query = "from Georef as g";
        
        if(filtro.getGlobalsearch() == null){
        	setQuery("g.locid", filtro.getLocid());
        	setQuery("g.llpn", filtro.getLlpn());
        	setQuery("g.lat", filtro.getLat());
        	setQuery("g.lon", filtro.getLon());
        	setQuery("g.alt", filtro.getAlt());
        }else{
        	global = true;
	        if(ValidatingDataType.isNumeric(filtro.getGlobalsearch())){
	        	setQuery("g.locid", Integer.valueOf(filtro.getGlobalsearch()));
	        	setQuery("g.llpn", Integer.valueOf(filtro.getGlobalsearch()));
	        	setQuery("g.lat", Double.valueOf(filtro.getGlobalsearch()));
	        	setQuery("g.lon", Double.valueOf(filtro.getGlobalsearch()));
	        	setQuery("g.alt", Double.valueOf(filtro.getGlobalsearch()));
	        }else{
	        	
	        }
        }
        return query;
    }
}