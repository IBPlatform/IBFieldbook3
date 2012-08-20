package org.cimmyt.cril.ibwb.provider.dao;


import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.Logger;
import org.cimmyt.cril.ibwb.api.dao.AbstractDAO;
import org.cimmyt.cril.ibwb.api.dao.utils.ValidatingDataType;
import org.cimmyt.cril.ibwb.domain.Steffect;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.DetachedCriteria;
import org.springframework.orm.hibernate3.HibernateCallback;

public class SteffectDAO extends AbstractDAO<Steffect, Integer> {

    private static Logger log = Logger.getLogger(SteffectDAO.class);

    public SteffectDAO() {
            super(Steffect.class);
    }

    @Override
    public Steffect prepareToCreate(Steffect steffect) {
        if (isLocal()) {
            steffect.setEffectid(getNextMin());
        }
        if (isCentral()) {
            steffect.setEffectid(getNextMax());
        }
        return steffect;
    }

    @Override
    protected void buildCriteria(DetachedCriteria criteria, Steffect filter) {
            // TODO Auto-generated method stub

    }

    @Override
    public String getKeyProperty() {
            return "effectid";
    }

    @Override
    public String getConsulta(Steffect filtro) {
    	String query = "from Steffect as s";
        
        if(filtro.getGlobalsearch() == null){
        	setQuery("s.effectid", filtro.getEffectid());
        	setQuery("s.studyid", filtro.getStudyid());
        	setQuery("s.effectname", filtro.getEffectname());
        }else{
        	global = true;
	        if(ValidatingDataType.isNumeric(filtro.getGlobalsearch())){
	        	setQuery("s.effectid", Integer.valueOf(filtro.getEffectid()));
	        	setQuery("s.studyid", Integer.valueOf(filtro.getStudyid()));
	        	setQueryInTo("s.effectname", filtro.getEffectname());
	        }else{
	        	setQueryInTo("s.effectname", filtro.getEffectname());
	        }
        }
        return query;
    }
    
    
    
    /**
     * Return a steffects
     * @param steffects
     * @return Steffect
     */
    public List<Steffect> getSteffectByStudyid(Integer studyid){
        StringBuilder sbHql = new StringBuilder();
        Object [] parametros = {studyid};
        sbHql.append("FROM Steffect as s WHERE s.studyid = ? ORDER BY s.effectid ");
        if(isLocal()){
            sbHql.append("DESC");
        }else{
            sbHql.append("ASC");
        }
      //  log.info(sbHql.toString());
        List<Steffect> listSteffects = getHibernateTemplate().find(sbHql.toString(), parametros);
        
        if(listSteffects != null){
            return listSteffects;
        }else{
            return listSteffects = new ArrayList<Steffect>();
        }
    }
    
    /**
     * Return a effectsID
     * @param Integer studyid
     * @return List<Integer> effecstIds
     */
    public List<Integer> getEffectidsByStudyid(Integer studyid){
        StringBuilder sbSQLNat = new StringBuilder();
        sbSQLNat.append("select s.effectid from steffect as s where s.STUDYID = ");
        sbSQLNat.append(studyid);
        sbSQLNat.append(" order by s.effectid ");
        if(isLocal()){
            sbSQLNat.append("DESC");
        }else{
            sbSQLNat.append("ASC");
        }
     //   log.info(sbSQLNat.toString());
        List<Integer> listEffectsid = this.executeQueryCustomListOfGSqlNat(sbSQLNat.toString());
        
        if(listEffectsid != null){
            return listEffectsid;
        }else{
            return listEffectsid = new ArrayList<Integer>();
        }
    }
}