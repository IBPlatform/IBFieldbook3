package org.cimmyt.cril.ibwb.provider.dao;



import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.cimmyt.cril.ibwb.api.dao.AbstractDAO;
import org.cimmyt.cril.ibwb.api.dao.utils.ValidatingDataType;
import org.cimmyt.cril.ibwb.domain.Bibrefs;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate3.HibernateCallback;

public class BibrefsDAO extends AbstractDAO<Bibrefs, Integer> {

	public BibrefsDAO() {
		super(Bibrefs.class);
	}
	
	@Override
	public Bibrefs prepareToCreate(Bibrefs bibrefs) {
        if (isLocal()) {
        	bibrefs.setRefid(getNextMin());
		}
        if (isCentral()) {
        	bibrefs.setRefid(getNextMax());
		}
        return bibrefs;
	}

	@Override
	protected void buildCriteria(DetachedCriteria criteria, Bibrefs filter) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String getKeyProperty() {
		return "refid";
	}

    @Override
    public String getConsulta(Bibrefs filtro) {
    	String query = "from Bibrefs as b";
        
        if(filtro.getGlobalsearch() == null){
        	setQuery("b.refid", filtro.getRefid());
        	setQuery("b.pubtype", filtro.getPubtype());
        	setQuery("b.pubdate", filtro.getPubdate());
        	setQuery("b.authors", filtro.getAuthors());
        	setQuery("b.editors", filtro.getEditors());
        	setQuery("b.analyt", filtro.getAnalyt());
        	setQuery("b.monogr", filtro.getMonogr());
        	setQuery("b.series", filtro.getSeries());
        	setQuery("b.volume", filtro.getVolume());
        	setQuery("b.issue", filtro.getIssue());
        	setQuery("b.pagecol", filtro.getPagecol());
        	setQuery("b.publish", filtro.getPublish());
        	setQuery("b.pucity", filtro.getPucity());
        	setQuery("b.pucntry", filtro.getPucntry());
        }else{
        	global = true;
	        if(ValidatingDataType.isNumeric(filtro.getGlobalsearch())){
	        	setQuery("b.refid", Integer.valueOf(filtro.getGlobalsearch()));
	        	setQuery("b.pubtype", Integer.valueOf(filtro.getGlobalsearch()));
	        	setQuery("b.pubdate", Integer.valueOf(filtro.getGlobalsearch()));
	        	setQueryInTo("b.authors", filtro.getGlobalsearch());
	        	setQueryInTo("b.editors", filtro.getGlobalsearch());
	        	setQueryInTo("b.analyt", filtro.getGlobalsearch());
	        	setQueryInTo("b.monogr", filtro.getGlobalsearch());
	        	setQueryInTo("b.series", filtro.getGlobalsearch());
	        	setQueryInTo("b.volume", filtro.getGlobalsearch());
	        	setQueryInTo("b.issue", filtro.getGlobalsearch());
	        	setQueryInTo("b.pagecol", filtro.getGlobalsearch());
	        	setQueryInTo("b.publish", filtro.getGlobalsearch());
	        	setQueryInTo("b.pucity", filtro.getGlobalsearch());
	        	setQueryInTo("b.pucntry", filtro.getGlobalsearch());
	        }else{
	        	setQueryInTo("b.authors", filtro.getGlobalsearch());
	        	setQueryInTo("b.editors", filtro.getGlobalsearch());
	        	setQueryInTo("b.analyt", filtro.getGlobalsearch());
	        	setQueryInTo("b.monogr", filtro.getGlobalsearch());
	        	setQueryInTo("b.series", filtro.getGlobalsearch());
	        	setQueryInTo("b.volume", filtro.getGlobalsearch());
	        	setQueryInTo("b.issue", filtro.getGlobalsearch());
	        	setQueryInTo("b.pagecol", filtro.getGlobalsearch());
	        	setQueryInTo("b.publish", filtro.getGlobalsearch());
	        	setQueryInTo("b.pucity", filtro.getGlobalsearch());
	        	setQueryInTo("b.pucntry", filtro.getGlobalsearch());
	        }
        }
        return query;
    }
}