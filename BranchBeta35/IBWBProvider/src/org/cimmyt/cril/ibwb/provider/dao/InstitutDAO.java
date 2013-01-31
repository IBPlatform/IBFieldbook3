package org.cimmyt.cril.ibwb.provider.dao;


import java.util.ArrayList;
import java.util.List;
import org.cimmyt.cril.ibwb.api.dao.AbstractDAO;
import org.cimmyt.cril.ibwb.api.dao.utils.ValidatingDataType;
import org.cimmyt.cril.ibwb.domain.Institut;

import org.hibernate.criterion.DetachedCriteria;

public class InstitutDAO extends AbstractDAO<Institut, Integer> {

    public InstitutDAO() {
        super(Institut.class);
    }
    
    @Override
	public Institut prepareToCreate(Institut institut) {
        if (isLocal()) {
        	institut.setInstitid(getNextMin());
		}
        if (isCentral()) {
        	institut.setInstitid(getNextMax());
		}
        return institut;
	}

    @Override
    protected void buildCriteria(DetachedCriteria criteria, Institut filter) {
        // TODO Auto-generated method stub
    }

    @Override
    public String getKeyProperty() {
        return "institid";
    }

    @Override
    public String getConsulta(Institut filtro) {
    	String query = "from Institut as i";
        
        if(filtro.getGlobalsearch() == null){
        	setQuery("i.institid", filtro.getInstitid());
        	setQuery("i.pinsid", filtro.getPinsid());
        	setQuery("i.insname", filtro.getInsname());
        	setQuery("i.insacr", filtro.getInsacr());
        	setQuery("i.instype", filtro.getInstype());
        	setQuery("i.street", filtro.getStreet());
        	setQuery("i.postbox", filtro.getPostbox());
        	setQuery("i.city", filtro.getCity());
        	setQuery("i.stateid", filtro.getStateid());
        	setQuery("i.cpostal", filtro.getCpostal());
        	setQuery("i.cntryid", filtro.getCntryid());
        	setQuery("i.aphone", filtro.getAphone());
        	setQuery("i.afax", filtro.getAfax());
        	setQuery("i.aemail", filtro.getAemail());
        	setQuery("i.weburl", filtro.getWeburl());
        	setQuery("i.sins", filtro.getSins());
        	setQuery("i.eins", filtro.getEins());
        	setQuery("i.ichange", filtro.getIchange());
      //  	setQuery("i.faocode", filtro.getFaocode());
        }else{
        	global = true;
	        if(ValidatingDataType.isNumeric(filtro.getGlobalsearch())){
	        	setQuery("i.institid", Integer.valueOf(filtro.getGlobalsearch()));
	        	setQuery("i.pinsid", Integer.valueOf(filtro.getGlobalsearch()));
	        	setQueryInTo("i.insname", filtro.getGlobalsearch());
	        	setQueryInTo("i.insacr", filtro.getGlobalsearch());
	        	setQuery("i.instype", Integer.valueOf(filtro.getGlobalsearch()));
	        	setQueryInTo("i.street", filtro.getGlobalsearch());
	        	setQueryInTo("i.postbox", filtro.getGlobalsearch());
	        	setQueryInTo("i.city", filtro.getGlobalsearch());
	        	setQuery("i.stateid", Integer.valueOf(filtro.getGlobalsearch()));
	        	setQueryInTo("i.cpostal", filtro.getGlobalsearch());
	        	setQuery("i.cntryid", Integer.valueOf(filtro.getGlobalsearch()));
	        	setQueryInTo("i.aphone", filtro.getGlobalsearch());
	        	setQueryInTo("i.afax", filtro.getGlobalsearch());
	        	setQueryInTo("i.aemail", filtro.getGlobalsearch());
	        	setQueryInTo("i.weburl", filtro.getGlobalsearch());
	        	setQuery("i.sins", Integer.valueOf(filtro.getGlobalsearch()));
	        	setQuery("i.eins", Integer.valueOf(filtro.getGlobalsearch()));
	        	setQuery("i.ichange", Integer.valueOf(filtro.getGlobalsearch()));
	        //	setQueryInTo("i.faocode", filtro.getGlobalsearch());
	        }else{
	        	setQueryInTo("i.insname", filtro.getGlobalsearch());
	        	setQueryInTo("i.insacr", filtro.getGlobalsearch());
	        	setQueryInTo("i.street", filtro.getGlobalsearch());
	        	setQueryInTo("i.postbox", filtro.getGlobalsearch());
	        	setQueryInTo("i.city", filtro.getGlobalsearch());
	        	setQueryInTo("i.cpostal", filtro.getGlobalsearch());
	        	setQueryInTo("i.aphone", filtro.getGlobalsearch());
	        	setQueryInTo("i.afax", filtro.getGlobalsearch());
	        	setQueryInTo("i.aemail", filtro.getGlobalsearch());
	        	setQueryInTo("i.weburl", filtro.getGlobalsearch());
	        //	setQueryInTo("i.faocode", filtro.getGlobalsearch());
	        }
        }
        return query;
    }

    /**
     * Gets a list of Institutions by city name
     * @param city name of the city to retrieve
     * @return a list of <code>Institut</code> of empty list if there are not
     * records
     */
    @SuppressWarnings("unchecked")
    public List<Institut> getInstitutionsByCity(final String city) {
        List<Institut> list = new ArrayList();
        Object[] params =  new Object[1];
        params[0] = city;
        //list = getHibernateTemplate().findByNamedQuery("Institut.findByCity",city);
        list = getHibernateTemplate().findByNamedQuery("Institut.findByCity",params);
        return list;
    }
}