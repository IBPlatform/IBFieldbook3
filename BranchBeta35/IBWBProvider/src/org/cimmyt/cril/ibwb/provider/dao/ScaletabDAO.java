package org.cimmyt.cril.ibwb.provider.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.cimmyt.cril.ibwb.api.dao.AbstractDAO;
import org.cimmyt.cril.ibwb.api.dao.utils.ValidatingDataType;
import org.cimmyt.cril.ibwb.domain.Scaletab;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.DetachedCriteria;
import org.springframework.orm.hibernate3.HibernateCallback;

public class ScaletabDAO extends AbstractDAO<Scaletab, Integer> {

	public ScaletabDAO() {
		super(Scaletab.class);
	}
	
	@Override
	public Scaletab prepareToCreate(Scaletab scaletab) {
        if (isLocal()) {
        	scaletab.setScaleid(getNextMin());
		}
        if (isCentral()) {
        	scaletab.setScaleid(getNextMax());
		}
        return scaletab;
	}

	@Override
	protected void buildCriteria(DetachedCriteria criteria, Scaletab filter) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String getKeyProperty() {
		return "scaleid";
	}

    @Override
    public String getConsulta(Scaletab filtro) {
    	String query = "from Scaletab as s";
        
        if(filtro.getGlobalsearch() == null){
        	setQuery("s.scaleid", filtro.getScaleid());
        	setQuery("s.ssql", filtro.getSsql());
        	setQuery("s.smodule", filtro.getSmodule());
        }else{
        	global = true;
	        if(ValidatingDataType.isNumeric(filtro.getGlobalsearch())){
	        	setQuery("s.scaleid", Integer.valueOf(filtro.getScaleid()));
	        	setQueryInTo("s.ssql", filtro.getSsql());
	        	setQueryInTo("s.smodule", filtro.getSmodule());
	        }else{
	        	setQueryInTo("s.ssql", filtro.getSsql());
	        	setQueryInTo("s.smodule", filtro.getSmodule());
	        }
        }
        return query;
    }
}