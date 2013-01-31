package org.cimmyt.cril.ibwb.provider.dao;


import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.cimmyt.cril.ibwb.api.dao.AbstractDAO;
import org.cimmyt.cril.ibwb.api.dao.utils.ValidatingDataType;
import org.cimmyt.cril.ibwb.domain.Dudflds;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.DetachedCriteria;
import org.springframework.orm.hibernate3.HibernateCallback;

public class DudfldsDAO extends AbstractDAO<Dudflds, Integer> {

	public DudfldsDAO() {
		super(Dudflds.class);
	}
	
	@Override
	public Dudflds prepareToCreate(Dudflds dudflds) {
        if (isLocal()) {
        	dudflds.setFldno(getNextMin());
		}
        if (isCentral()) {
        	dudflds.setFldno(getNextMax());
		}
        return dudflds;
	}

	@Override
	protected void buildCriteria(DetachedCriteria criteria, Dudflds filter) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String getKeyProperty() {
		return "fldno";
	}

    @Override
    public String getConsulta(Dudflds filtro) {
    	String query = "from Dudflds as d";
        
        if(filtro.getGlobalsearch() == null){
        	setQuery("d.fldno", filtro.getLfldno());
        	setQuery("d.ftable", filtro.getFtable());
        	setQuery("d.ftype", filtro.getFtype());
        	setQuery("d.fcode", filtro.getFcode());
        	setQuery("d.fname", filtro.getFname());
        	setQuery("d.ffmt", filtro.getFfmt());
        	setQuery("d.fdesc", filtro.getFdesc());
        	setQuery("d.lfldno", filtro.getLfldno());
        	setQuery("d.fuid", filtro.getFuid());
        	setQuery("d.fdate", filtro.getFdate());
        	setQuery("d.oldfldno", filtro.getOldfldno());
        	setQuery("d.oldfldid", filtro.getOldfldid());
        }else{
        	global = true;
	        if(ValidatingDataType.isNumeric(filtro.getGlobalsearch())){
	        	setQuery("d.fldno", Integer.valueOf(filtro.getGlobalsearch()));
	        	setQueryInTo("d.ftable", filtro.getGlobalsearch());
	        	setQueryInTo("d.ftype", filtro.getGlobalsearch());
	        	setQueryInTo("d.fcode", filtro.getGlobalsearch());
	        	setQueryInTo("d.fname", filtro.getGlobalsearch());
	        	setQueryInTo("d.ffmt", filtro.getGlobalsearch());
	        	setQueryInTo("d.fdesc", filtro.getGlobalsearch());
	        	setQuery("d.lfldno", Integer.valueOf(filtro.getGlobalsearch()));
	        	setQuery("d.fuid", Integer.valueOf(filtro.getGlobalsearch()));
	        	setQuery("d.fdate", Integer.valueOf(filtro.getGlobalsearch()));
	        	setQuery("d.oldfldno", Integer.valueOf(filtro.getGlobalsearch()));
	        	setQuery("d.oldfldid", Integer.valueOf(filtro.getGlobalsearch()));
	        }else{
	        	setQueryInTo("d.ftable", filtro.getGlobalsearch());
	        	setQueryInTo("d.ftype", filtro.getGlobalsearch());
	        	setQueryInTo("d.fcode", filtro.getGlobalsearch());
	        	setQueryInTo("d.fname", filtro.getGlobalsearch());
	        	setQueryInTo("d.ffmt", filtro.getGlobalsearch());
	        	setQueryInTo("d.fdesc", filtro.getGlobalsearch());
	        }
	    }
        return query;
    }
}