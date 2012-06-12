package org.cimmyt.cril.ibwb.provider.dao;


import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.cimmyt.cril.ibwb.api.dao.AbstractDAO;
import org.cimmyt.cril.ibwb.api.dao.utils.ValidatingDataType;
import org.cimmyt.cril.ibwb.domain.Cntry;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate3.HibernateCallback;

public class CntryDAO extends AbstractDAO<Cntry, Integer> {

	public CntryDAO() {
		super(Cntry.class);
	}
	
	@Override
	public Cntry prepareToCreate(Cntry cntry) {
        if (isLocal()) {
        	cntry.setCntryid(getNextMin());
		}
        if (isCentral()) {
        	cntry.setCntryid(getNextMax());
		}
        return cntry;
	}

	@Override
	protected void buildCriteria(DetachedCriteria criteria, Cntry filter) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String getKeyProperty() {
		return "cntryid";
	}

    @Override
    public String getConsulta(Cntry filtro) {
    	String query = "from Cntry as c";
        
        if(filtro.getGlobalsearch() == null){
        	setQuery("c.cntryid", filtro.getCntryid());
        	setQuery("c.isonum", filtro.getIsonum());
        	setQuery("c.isotwo", filtro.getIsotwo());
        	setQuery("c.isothree", filtro.getIsothree());
        	setQuery("c.faothree", filtro.getFaothree());
        	setQuery("c.fips", filtro.getFips());
        	setQuery("c.wb", filtro.getWb());
        	setQuery("c.isofull", filtro.getIsofull());
        	setQuery("c.isoabbr", filtro.getIsoabbr());
        	setQuery("c.cont", filtro.getCont());
        	setQuery("c.scntry", filtro.getScntry());
        	setQuery("c.ecntry", filtro.getEcntry());
        	setQuery("c.cchange", filtro.getCchange());
        }else{
        	global = true;
	        if(ValidatingDataType.isNumeric(filtro.getGlobalsearch())){
	        	setQuery("c.cntryid", Integer.valueOf(filtro.getGlobalsearch()));
	        	setQuery("c.isonum", Integer.valueOf(filtro.getGlobalsearch()));
	        	setQueryInTo("c.isotwo", filtro.getGlobalsearch());
	        	setQueryInTo("c.isothree", filtro.getGlobalsearch());
	        	setQueryInTo("c.faothree", filtro.getGlobalsearch());
	        	setQueryInTo("c.fips", filtro.getGlobalsearch());
	        	setQueryInTo("c.wb", filtro.getGlobalsearch());
	        	setQueryInTo("c.isofull", filtro.getGlobalsearch());
	        	setQueryInTo("c.isoabbr", filtro.getGlobalsearch());
	        	setQueryInTo("c.cont", filtro.getGlobalsearch());
	        	setQuery("c.scntry", Integer.valueOf(filtro.getGlobalsearch()));
	        	setQuery("c.ecntry", Integer.valueOf(filtro.getGlobalsearch()));
	        	setQuery("c.cchange", Integer.valueOf(filtro.getGlobalsearch()));
	        }else{
	        	setQueryInTo("c.isotwo", filtro.getGlobalsearch());
	        	setQueryInTo("c.isothree", filtro.getGlobalsearch());
	        	setQueryInTo("c.faothree", filtro.getGlobalsearch());
	        	setQueryInTo("c.fips", filtro.getGlobalsearch());
	        	setQueryInTo("c.wb", filtro.getGlobalsearch());
	        	setQueryInTo("c.isofull", filtro.getGlobalsearch());
	        	setQueryInTo("c.isoabbr", filtro.getGlobalsearch());
	        	setQueryInTo("c.cont", filtro.getGlobalsearch());
	        }
        }
        return query;
    }
}