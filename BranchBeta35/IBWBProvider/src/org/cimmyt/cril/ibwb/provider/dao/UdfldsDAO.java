package org.cimmyt.cril.ibwb.provider.dao;


import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.cimmyt.cril.ibwb.api.dao.AbstractDAO;
import org.cimmyt.cril.ibwb.api.dao.utils.ValidatingDataType;
import org.cimmyt.cril.ibwb.domain.Udflds;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.DetachedCriteria;
import org.springframework.orm.hibernate3.HibernateCallback;

public class UdfldsDAO extends AbstractDAO<Udflds, Integer> {

	public UdfldsDAO() {
		super(Udflds.class);
	}
	
	@Override
	public Udflds prepareToCreate(Udflds udflds) {
        if (isLocal()) {
        	udflds.setFldno(getNextMin());
		}
        if (isCentral()) {
        	udflds.setFldno(getNextMax());
		}
        return udflds;
	}

	@Override
	protected void buildCriteria(DetachedCriteria criteria, Udflds filter) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String getKeyProperty() {
		return "fldno";
	}

    @Override
    public String getConsulta(Udflds filtro) {
    	String query = "from Udflds as u";
        
        if(filtro.getGlobalsearch() == null){
        	setQuery("u.fldno", filtro.getFldno());
        	setQuery("u.ftable", filtro.getFtable());
        	setQuery("u.ftype", filtro.getFtype());
        	setQuery("u.fcode", filtro.getFcode());
        	setQuery("u.fname", filtro.getFname());
        	setQuery("u.ffmt", filtro.getFfmt());
        	setQuery("u.fdesc", filtro.getFdesc());
        	setQuery("u.lfldno", filtro.getLfldno());
        	setQuery("u.fuid", filtro.getFuid());
        	setQuery("u.fdate", filtro.getFdate());
        	setQuery("u.scaleid", filtro.getScaleid());
        }else{
        	global = true;
	        if(ValidatingDataType.isNumeric(filtro.getGlobalsearch())){
	        	setQuery("u.fldno", Integer.valueOf(filtro.getGlobalsearch()));
	        	setQueryInTo("u.ftable", filtro.getGlobalsearch());
	        	setQueryInTo("u.ftype", filtro.getGlobalsearch());
	        	setQueryInTo("u.fcode", filtro.getGlobalsearch());
	        	setQueryInTo("u.fname", filtro.getGlobalsearch());
	        	setQueryInTo("u.ffmt", filtro.getGlobalsearch());
	        	setQueryInTo("u.fdesc", filtro.getGlobalsearch());
	        	setQuery("u.lfldno", Integer.valueOf(filtro.getGlobalsearch()));
	        	setQuery("u.fuid", Integer.valueOf(filtro.getGlobalsearch()));
	        	setQuery("u.fdate", Integer.valueOf(filtro.getGlobalsearch()));
	        	setQuery("u.scaleid", Integer.valueOf(filtro.getGlobalsearch()));
	        }else{
	        	setQueryInTo("u.ftable", filtro.getGlobalsearch());
	        	setQueryInTo("u.ftype", filtro.getGlobalsearch());
	        	setQueryInTo("u.fcode", filtro.getGlobalsearch());
	        	setQueryInTo("u.fname", filtro.getGlobalsearch());
	        	setQueryInTo("u.ffmt", filtro.getGlobalsearch());
	        	setQueryInTo("u.fdesc", filtro.getGlobalsearch());
	        }
        }
        return query;
    }
}