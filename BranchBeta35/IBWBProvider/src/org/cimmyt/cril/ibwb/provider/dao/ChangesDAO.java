package org.cimmyt.cril.ibwb.provider.dao;



import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.cimmyt.cril.ibwb.api.dao.AbstractDAO;
import org.cimmyt.cril.ibwb.api.dao.utils.ValidatingDataType;
import org.cimmyt.cril.ibwb.domain.Changes;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate3.HibernateCallback;

public class ChangesDAO extends AbstractDAO<Changes, Integer> {

	public ChangesDAO() {
		super(Changes.class);
	}
	
	@Override
	public Changes prepareToCreate(Changes changes) {
        if (isLocal()) {
        	changes.setCid(getNextMin());
		}
        if (isCentral()) {
        	changes.setCid(getNextMax());
		}
        return changes;
	}

	@Override
	protected void buildCriteria(DetachedCriteria criteria, Changes filter) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String getKeyProperty() {
		return "cid";
	}

    @Override
    public String getConsulta(Changes filtro) {
    	String query = "from Changes as c";
        
        if(filtro.getGlobalsearch() == null){
        	setQuery("c.cid", filtro.getCid());
        	setQuery("c.ctable", filtro.getCtable());
        	setQuery("c.cfield", filtro.getCfield());
        	setQuery("c.crecord", filtro.getCrecord());
        	setQuery("c.cfrom", filtro.getCfrom());
        	setQuery("c.cto", filtro.getCto());
        	setQuery("c.cdate", filtro.getCdate());
        	setQuery("c.ctime", filtro.getCtime());
        	setQuery("c.cgroup", filtro.getCgroup());
        	setQuery("c.cuid", filtro.getCuid());
        	setQuery("c.cref", filtro.getCref());
        	setQuery("c.cstatus", filtro.getCstatus());
        	setQuery("c.cdesc", filtro.getCdesc());
        }else{
        	global = true;
	        if(ValidatingDataType.isNumeric(filtro.getGlobalsearch())){
	        	setQuery("c.cid", Integer.valueOf(filtro.getGlobalsearch()));
	        	setQueryInTo("c.ctable", filtro.getGlobalsearch());
	        	setQueryInTo("c.cfield", filtro.getGlobalsearch());
	        	setQuery("c.crecord", Integer.valueOf(filtro.getGlobalsearch()));
	        	setQuery("c.cfrom", Integer.valueOf(filtro.getGlobalsearch()));
	        	setQuery("c.cto", Integer.valueOf(filtro.getGlobalsearch()));
	        	setQuery("c.cdate", Integer.valueOf(filtro.getGlobalsearch()));
	        	setQuery("c.ctime", Integer.valueOf(filtro.getGlobalsearch()));
	        	setQueryInTo("c.cgroup", filtro.getGlobalsearch());
	        	setQuery("c.cuid", Integer.valueOf(filtro.getGlobalsearch()));
	        	setQuery("c.cref", Integer.valueOf(filtro.getGlobalsearch()));
	        	setQuery("c.cstatus", Integer.valueOf(filtro.getGlobalsearch()));
	        	setQueryInTo("c.cdesc", filtro.getGlobalsearch());
	        }else{
	        	setQueryInTo("c.ctable", filtro.getGlobalsearch());
	        	setQueryInTo("c.cfield", filtro.getGlobalsearch());
	        	setQueryInTo("c.cgroup", filtro.getGlobalsearch());
	        	setQueryInTo("c.cdesc", filtro.getGlobalsearch());
	        }
        }
        return query;
    }
}