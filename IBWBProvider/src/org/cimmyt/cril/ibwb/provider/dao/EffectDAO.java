package org.cimmyt.cril.ibwb.provider.dao;



import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.Logger;
import org.cimmyt.cril.ibwb.api.dao.AbstractDAO;
import org.cimmyt.cril.ibwb.api.dao.utils.ValidatingDataType;
import org.cimmyt.cril.ibwb.domain.Effect;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.DetachedCriteria;
import org.springframework.orm.hibernate3.HibernateCallback;

public class EffectDAO extends AbstractDAO<Effect, Integer> {

    private static Logger log = Logger.getLogger(EffectDAO.class);

    public EffectDAO() {
        super(Effect.class);
    }

    @Override
    protected void buildCriteria(DetachedCriteria criteria, Effect filter) {
        // TODO Auto-generated method stub
    }

    @Override
    public String getKeyProperty() {
        return "effectid";
    }

    @Override
    public String getConsulta(Effect filtro) {
    	String query = "from Effect as e";
        if(filtro.getGlobalsearch() == null){
            if(filtro.getEffectPK() != null){
	        setQuery("e.effectPK.represno", filtro.getEffectPK().getRepresno());
	        setQuery("e.effectPK.factorid", filtro.getEffectPK().getFactorid());
	        setQuery("e.effectPK.effectid", filtro.getEffectPK().getEffectid());
            }
        }else{
        	global = true;
	        if(ValidatingDataType.isNumeric(filtro.getGlobalsearch())){
	        	setQuery("e.effectPK.represno", Integer.valueOf(filtro.getGlobalsearch()));
		        setQuery("e.effectPK.factorid", Integer.valueOf(filtro.getGlobalsearch()));
		        setQuery("e.effectPK.effectid", Integer.valueOf(filtro.getGlobalsearch()));
	        }else{
	        	
	        }
        }
        return query;
    }
    
    /**
     * Return a effects
     * @param effectsIds
     * @return Effect
     */
    public List<Effect> getEffectsByEffectsids(final List effectsIds){
        
        
        List<Effect> resultList = new ArrayList<Effect>();
        resultList = getHibernateTemplate().executeFind(new HibernateCallback() {
            public Object doInHibernate(Session session)
                throws HibernateException, SQLException {
                StringBuilder sbHql = new StringBuilder();
                Object [] parametros = effectsIds.toArray();
                sbHql.append("FROM Effect as e WHERE e.effectPK.effectid IN(:ids) ORDER BY e.effectPK.effectid ");
                String orden;
                if(isLocal()){
                    orden = "DESC";
                }else{
                    orden = "ASC";
                }
                sbHql.append(orden);
                sbHql.append(", e.effectPK.factorid ");
                sbHql.append(orden);
                
            //    log.info(sbHql.toString());
                Query query = session.createQuery(sbHql.toString());
                query.setParameterList("ids", parametros);
                return  query.list();
            }
        });
        return resultList;
    }
}