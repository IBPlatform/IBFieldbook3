package org.cimmyt.cril.ibwb.provider.dao;



import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.cimmyt.cril.ibwb.api.dao.AbstractDAO;
import org.cimmyt.cril.ibwb.api.dao.utils.ValidatingDataType;
import org.cimmyt.cril.ibwb.domain.Veffect;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.DetachedCriteria;
import org.springframework.orm.hibernate3.HibernateCallback;

public class VeffectDAO extends AbstractDAO<Veffect, Integer> {

	public VeffectDAO() {
		super(Veffect.class);
	}

	@Override
	protected void buildCriteria(DetachedCriteria criteria, Veffect filter) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String getKeyProperty() {
		return "locationid";
	}

    @Override
    public String getConsulta(Veffect filtro) {
    	String query = "from Veffect as v";
        
        if(filtro.getGlobalsearch() == null){
            if(filtro.getVeffectPK() != null){
	        setQuery("v.veffectPK.represno", filtro.getVeffectPK().getRepresno());
	        setQuery("v.veffectPK.variatid", filtro.getVeffectPK().getVariatid());
            }
        }else{
        	global = true;
	        if(ValidatingDataType.isNumeric(filtro.getGlobalsearch())){
	        	setQuery("v.veffectPK.represno", Integer.valueOf(filtro.getGlobalsearch()));
		        setQuery("v.veffectPK.variatid", Integer.valueOf(filtro.getGlobalsearch()));
	        }else{
	        	
	        }
        }
        return query;
    }
}