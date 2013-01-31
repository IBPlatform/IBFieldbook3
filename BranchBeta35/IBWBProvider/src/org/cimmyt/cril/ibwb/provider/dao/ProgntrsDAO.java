package org.cimmyt.cril.ibwb.provider.dao;


import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.cimmyt.cril.ibwb.api.dao.AbstractDAO;
import org.cimmyt.cril.ibwb.api.dao.utils.ValidatingDataType;
import org.cimmyt.cril.ibwb.domain.Progntrs;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.DetachedCriteria;
import org.springframework.orm.hibernate3.HibernateCallback;

public class ProgntrsDAO extends AbstractDAO<Progntrs, Integer> {

	public ProgntrsDAO() {
		super(Progntrs.class);
	}

	@Override
	protected void buildCriteria(DetachedCriteria criteria, Progntrs filter) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String getKeyProperty() {
		return "locationid";
	}

    @Override
    public String getConsulta(Progntrs filtro) {
    	String query = "from Progntrs as p";
        
        if(filtro.getGlobalsearch() == null){
            if(filtro.getProgntrsPK() != null){
	        setQuery("p.progntrsPK.gid", filtro.getProgntrsPK().getGid());
	        setQuery("p.progntrsPK.pno", filtro.getProgntrsPK().getPno());
            }
	    setQuery("p.pid", filtro.getPid());
        }else{
        	global = true;
	        if(ValidatingDataType.isNumeric(filtro.getGlobalsearch())){
	        	setQuery("p.progntrsPK.gid", Integer.valueOf(filtro.getGlobalsearch()));
		        setQuery("p.progntrsPK.pno", Integer.valueOf(filtro.getGlobalsearch()));
		        setQuery("p.pid", Integer.valueOf(filtro.getGlobalsearch()));
	        }else{
	        	
	        }
        }
        return query;
    }
}