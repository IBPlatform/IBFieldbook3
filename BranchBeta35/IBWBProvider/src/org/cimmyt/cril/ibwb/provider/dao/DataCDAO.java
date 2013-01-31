package org.cimmyt.cril.ibwb.provider.dao;



import java.util.ArrayList;
import java.util.List;
import org.cimmyt.cril.ibwb.api.dao.AbstractDAO;
import org.cimmyt.cril.ibwb.api.dao.utils.Order;
import org.cimmyt.cril.ibwb.api.dao.utils.ValidatingDataType;
import org.cimmyt.cril.ibwb.domain.DataC;

import org.hibernate.criterion.DetachedCriteria;

public class DataCDAO extends AbstractDAO<DataC, Integer> {

	public DataCDAO() {
		super(DataC.class);
	}

	@Override
	protected void buildCriteria(DetachedCriteria criteria, DataC filter) {
		// TODO Auto-generated method stub
	}

	@Override
	public String getKeyProperty() {
		return "variatid";
	}

    @Override
    public String getConsulta(DataC filtro) {
    	String query = "from DataC as d";
        
        if(filtro.getGlobalsearch() == null){
            if(filtro.getDataCPK()!= null){
	        setQuery("d.dataCPK.ounitid", filtro.getDataCPK().getOunitid());
	        setQuery("d.dataCPK.variatid", filtro.getDataCPK().getVariatid());
            }
	    setQuery("d.dvalue", filtro.getDvalue());
        }else{
        	global = true;
	        if(ValidatingDataType.isNumeric(filtro.getGlobalsearch())){
	        	setQuery("d.dataCPK.ounitid", Integer.valueOf(filtro.getGlobalsearch()));
		        setQuery("d.dataCPK.variatid", Integer.valueOf(filtro.getGlobalsearch()));
		        setQueryInTo("d.dvalue", filtro.getGlobalsearch());
	        }else{
	        	setQueryInTo("d.dvalue", filtro.getGlobalsearch());
	        }
        }
        if (isLocal()) {
            setOrder("d.dataCPK.ounitid", Order.DESCENDENTE);
        } else {
            setOrder("d.dataCPK.ounitid", Order.ASCENDENTE);
        }
        return query;
    }
    
     /**
     * Retrieve a list of DATA_n records by its Effect ID
     * @param effectId
     * @return 
     */
    public List<DataC> getDataNByEffectId(final Integer effectId) {
        List<DataC> dataNList = new ArrayList<DataC>();
        
        String order = "";
        
        if (isCentral()) {
            order = " order by dataC.dataCPK.ounitid asc, dataC.dataCPK.variatid asc "; 
        } else {
            order = " order by dataC.dataCPK.ounitid desc, dataC.dataCPK.variatid desc "; 
        }
        
        final String queryString = "from DataC as dataC where dataC.dataCPK.ounitid in " +
                " (select obsunit.ounitid from Obsunit as obsunit where obsunit.effectid = ?) " +
                order;
        
        dataNList = getHibernateTemplate().find(queryString, effectId);
        
        return dataNList;
    }
}