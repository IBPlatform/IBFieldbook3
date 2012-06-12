package org.cimmyt.cril.ibwb.provider.dao;


import java.util.ArrayList;
import java.util.List;
import org.cimmyt.cril.ibwb.api.dao.AbstractDAO;
import org.cimmyt.cril.ibwb.api.dao.utils.Order;
import org.cimmyt.cril.ibwb.api.dao.utils.ValidatingDataType;
import org.cimmyt.cril.ibwb.domain.DataN;

import org.hibernate.criterion.DetachedCriteria;

public class DataNDAO extends AbstractDAO<DataN, Integer> {

	public DataNDAO() {
		super(DataN.class);
	}

	@Override
	protected void buildCriteria(DetachedCriteria criteria, DataN filter) {
		// TODO Auto-generated method stub
	}

	@Override
	public String getKeyProperty() {
		return "variatid";
	}

    @Override
    public String getConsulta(DataN filtro) {
    	String query = "from DataN as d";
        
        if(filtro.getGlobalsearch() == null){
            if(filtro.getDataNPK() != null){
	        setQuery("d.dataNPK.ounitid", filtro.getDataNPK().getOunitid());
	        setQuery("d.dataNPK.variatid", filtro.getDataNPK().getVariatid());
            }
	    setQuery("d.dvalue", filtro.getDvalue());
        }else{
        	global = true;
	        if(ValidatingDataType.isNumeric(filtro.getGlobalsearch())){
	        	setQuery("d.dataNPK.ounitid", Integer.valueOf(filtro.getGlobalsearch()));
		        setQuery("d.dataNPK.variatid", Integer.valueOf(filtro.getGlobalsearch()));
		        setQuery("d.dvalue", Double.valueOf(filtro.getGlobalsearch()));
	        }else{
	        	
	        }
        }
        if (isLocal()) {
            setOrder("d.dataNPK.ounitid", Order.DESCENDENTE);
        } else {
            setOrder("d.dataNPK.ounitid", Order.ASCENDENTE);
        }
        return query;
    }
    
    
    /**
     * Retrieve a list of DATA_n records by its Effect ID
     * @param effectId
     * @return 
     */
    public List<DataN> getDataNByEffectId(final Integer effectId) {
        List<DataN> dataNList = new ArrayList<DataN>();
        
        String order = "";
        
        if (isCentral()) {
            order = " order by dataN.dataNPK.ounitid asc, dataN.dataNPK.variatid asc "; 
        } else {
            order = " order by dataN.dataNPK.ounitid desc, dataN.dataNPK.variatid desc "; 
        }
        
        final String queryString = "from DataN as dataN where dataN.dataNPK.ounitid in " +
                " (select obsunit.ounitid from Obsunit as obsunit where obsunit.effectid = ?) " +
                order;
        
        dataNList = getHibernateTemplate().find(queryString, effectId);
        
        return dataNList;
    }
}