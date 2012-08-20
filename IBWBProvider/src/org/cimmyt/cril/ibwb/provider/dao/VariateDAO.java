package org.cimmyt.cril.ibwb.provider.dao;

import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.Logger;
import org.cimmyt.cril.ibwb.api.dao.AbstractDAO;
import org.cimmyt.cril.ibwb.api.dao.utils.Order;
import org.cimmyt.cril.ibwb.api.dao.utils.ValidatingDataType;
import org.cimmyt.cril.ibwb.domain.Variate;

import org.hibernate.criterion.DetachedCriteria;

public class VariateDAO extends AbstractDAO<Variate, Integer> {

    private static Logger log = Logger.getLogger(VariateDAO.class);

    public VariateDAO() {
        super(Variate.class);
    }

    @Override
    public Variate prepareToCreate(Variate variate) {
        if (isLocal()) {
            variate.setVariatid(getNextMin());
        }
        if (isCentral()) {
            variate.setVariatid(getNextMax());
        }
        return variate;
    }

    @Override
    protected void buildCriteria(DetachedCriteria criteria, Variate filter) {
        // TODO Auto-generated method stub
    }

    @Override
    public String getKeyProperty() {
        return "variatid";
    }

    @Override
    public String getConsulta(Variate filtro) {
        String query = "from Variate as v";

        if (filtro.getGlobalsearch() == null) {
            setQuery("v.variatid", filtro.getVariatid());
            setQuery("v.studyid", filtro.getStudyid());
            setQuery("v.vname", filtro.getVname());
            setQuery("v.traitid", filtro.getTraitid());
            setQuery("v.scaleid", filtro.getScaleid());
            setQuery("v.tmethid", filtro.getTmethid());
            setQuery("v.dtype", filtro.getDtype());
            setQuery("v.vtype", filtro.getVtype());
        } else {
            global = true;
            if (ValidatingDataType.isNumeric(filtro.getGlobalsearch())) {
                setQuery("v.variatid", Integer.valueOf(filtro.getGlobalsearch()));
                setQuery("v.studyid", Integer.valueOf(filtro.getGlobalsearch()));
                setQueryInTo("v.vname", filtro.getGlobalsearch());
                setQuery("v.traitid", Integer.valueOf(filtro.getGlobalsearch()));
                setQuery("v.scaleid", Integer.valueOf(filtro.getGlobalsearch()));
                setQuery("v.tmethid", Integer.valueOf(filtro.getGlobalsearch()));
                setQueryInTo("v.dtype", filtro.getGlobalsearch());
                setQueryInTo("v.vtype", filtro.getGlobalsearch());
            } else {
                setQueryInTo("v.vname", filtro.getGlobalsearch());
                setQueryInTo("v.dtype", filtro.getGlobalsearch());
                setQueryInTo("v.vtype", filtro.getGlobalsearch());
            }
        }
        if (isLocal()) {
            setOrder("v.variatid", Order.DESCENDENTE);
        } else {
            setOrder("v.variatid", Order.ASCENDENTE);
        }
        return query;
    }
    
    /**
     * Return a list of Variates where variate ID are stored in VEFFECT table
     * according to represno ID
     * @param represenoId represno ID for resprestn number
     * @return list of Variates
     */
    public List<Variate> getVarieteFromVeffects(final Integer represenoId) {
        List<Variate> variateList =  new ArrayList<Variate>();
        
        final String queryString = "from Variate as v where v.variatid in " +
                "( select ve.veffectPK.variatid from Veffect as ve where ve.veffectPK.represno = " + represenoId + ") ";
        
        variateList = getHibernateTemplate().find(queryString);
        
        return variateList;
    }
    
    /**
     * Returns different combinations of TRAIT, SCALE and METHOD
     * @return List<Variate>
     */
    public List<Variate> getVariateConvinacionesTraitScaleMethod(){
        StringBuilder sbHql = new StringBuilder();
        
        //sbHql.append("FROM Variate ORDER by traitid ");
        sbHql.append("select distinct v.traitid, v.scaleid, v.tmethid FROM Variate as v  ORDER by v.traitid ");
        if(isLocal()){
            sbHql.append("DESC");
        }else{
            sbHql.append("ASC");
        }
    //    log.info(sbHql.toString());
        //List<Variate> variates = getHibernateTemplate().find(sbHql.toString());
        List<Variate> variates = new ArrayList<Variate>();
        
        List<Object> variateListTemp = getHibernateTemplate().find(sbHql.toString());
        
        for (Object object: variateListTemp) {
            
            Object[] integerArray = (Object[])object;
            Integer traitid = (Integer)integerArray[0];
            Integer scaleid = (Integer)integerArray[1];
            Integer tmethid = (Integer)integerArray[2];
            Variate variateTemp =  new Variate(true);
            variateTemp.setTraitid(traitid);
            variateTemp.setScaleid(scaleid);
            variateTemp.setTmethid(tmethid);
            variateTemp.setTid(traitid);
            variates.add(variateTemp);
        }
        
        return variates;
    }
}