package org.cimmyt.cril.ibwb.provider.dao;


import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.cimmyt.cril.ibwb.api.dao.AbstractDAO;
import org.cimmyt.cril.ibwb.api.dao.utils.ValidatingDataType;
import org.cimmyt.cril.ibwb.domain.Instln;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.DetachedCriteria;
import org.springframework.orm.hibernate3.HibernateCallback;

public class InstlnDAO extends AbstractDAO<Instln, Integer> {

	public InstlnDAO() {
		super(Instln.class);
	}
	
	@Override
	public Instln prepareToCreate(Instln instln) {
        if (isLocal()) {
        	instln.setAdmin(getNextMin());
		}
        if (isCentral()) {
        	instln.setAdmin(getNextMax());
		}
        return instln;
	}

	@Override
	protected void buildCriteria(DetachedCriteria criteria, Instln filter) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String getKeyProperty() {
		return "admin";
	}

    @Override
    public String getConsulta(Instln filtro) {
    	String query = "from Instln as i";
        
        if(filtro.getGlobalsearch() == null){
        	setQuery("i.admin", filtro.getAdmin());
        	setQuery("i.udate", filtro.getUdate());
        	setQuery("i.ugid", filtro.getUgid());
        	setQuery("i.ulocn", filtro.getUlocn());
        	setQuery("i.ucid", filtro.getUcid());
        	setQuery("i.unid", filtro.getUnid());
        	setQuery("i.uaid", filtro.getUaid());
        	setQuery("i.uldid", filtro.getUldid());
        	setQuery("i.umethn", filtro.getUmethn());
        	setQuery("i.ufldno", filtro.getUfldno());
        	setQuery("i.urefno", filtro.getUrefno());
        	setQuery("i.upid", filtro.getUpid());
        	setQuery("i.idesc", filtro.getIdesc());
        	setQuery("i.ulistid", filtro.getUlistid());
        	setQuery("i.dmsStatus", filtro.getDmsStatus());
        	setQuery("i.ulrecid", filtro.getUlrecid());
        	setQuery("i.instalid", filtro.getInstalid());
        }else{
        	global = true;
	        if(ValidatingDataType.isNumeric(filtro.getGlobalsearch())){
	        	setQuery("i.admin", Integer.valueOf(filtro.getGlobalsearch()));
	        	setQuery("i.udate", Integer.valueOf(filtro.getGlobalsearch()));
	        	setQuery("i.ugid", Integer.valueOf(filtro.getGlobalsearch()));
	        	setQuery("i.ulocn", Integer.valueOf(filtro.getGlobalsearch()));
	        	setQuery("i.ucid", Integer.valueOf(filtro.getGlobalsearch()));
	        	setQuery("i.unid", Integer.valueOf(filtro.getGlobalsearch()));
	        	setQuery("i.uaid", Integer.valueOf(filtro.getGlobalsearch()));
	        	setQuery("i.uldid", Integer.valueOf(filtro.getGlobalsearch()));
	        	setQuery("i.umethn", Integer.valueOf(filtro.getGlobalsearch()));
	        	setQuery("i.ufldno", Integer.valueOf(filtro.getGlobalsearch()));
	        	setQuery("i.urefno", Integer.valueOf(filtro.getGlobalsearch()));
	        	setQuery("i.upid", Integer.valueOf(filtro.getGlobalsearch()));
	        	setQueryInTo("i.idesc", filtro.getGlobalsearch());
	        	setQuery("i.ulistid", Integer.valueOf(filtro.getGlobalsearch()));
	        	setQuery("i.dmsStatus", Integer.valueOf(filtro.getGlobalsearch()));
	        	setQuery("i.ulrecid", Integer.valueOf(filtro.getGlobalsearch()));
	        	setQuery("i.instalid", Integer.valueOf(filtro.getGlobalsearch()));
	        }else{
	        	setQueryInTo("i.idesc", filtro.getGlobalsearch());
	        }
        }
        return query;
    }
}