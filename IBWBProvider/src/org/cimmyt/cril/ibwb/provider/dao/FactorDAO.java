package org.cimmyt.cril.ibwb.provider.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.Logger;
import org.cimmyt.cril.ibwb.api.dao.AbstractDAO;
import org.cimmyt.cril.ibwb.api.dao.utils.Order;
import org.cimmyt.cril.ibwb.api.dao.utils.ValidatingDataType;
import org.cimmyt.cril.ibwb.domain.Factor;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.DetachedCriteria;
import org.springframework.orm.hibernate3.HibernateCallback;

public class FactorDAO extends AbstractDAO<Factor, Integer> {

    private static Logger log = Logger.getLogger(FactorDAO.class);
    
    public FactorDAO() {
        super(Factor.class);
    }

    @Override
    public Factor prepareToCreate(Factor factor) {
        if (isLocal()) {
            factor.setLabelid(getNextMin());
        }
        if (isCentral()) {
            factor.setLabelid(getNextMax());
        }
        return factor;
    }

    @Override
    protected void buildCriteria(DetachedCriteria criteria, Factor filter) {
        // TODO Auto-generated method stub
    }

    @Override
    public String getKeyProperty() {
        return "labelid";
    }

    @Override
    public String getConsulta(Factor filtro) {
        String query = "from Factor as f";

        if (filtro.getGlobalsearch() == null) {
            setQuery("f.labelid", filtro.getLabelid());
            setQuery("f.factorid", filtro.getFactorid());
            setQuery("f.studyid", filtro.getStudyid());
            setQuery("f.fname", filtro.getFname());
            setQuery("f.traitid", filtro.getTraitid());
            setQuery("f.scaleid", filtro.getScaleid());
            setQuery("f.tmethid", filtro.getTmethid());
            setQuery("f.ltype", filtro.getLtype());
        } else {
            global = true;
            if (ValidatingDataType.isNumeric(filtro.getGlobalsearch())) {
                setQuery("f.labelid", Integer.valueOf(filtro.getGlobalsearch()));
                setQuery("f.factorid", Integer.valueOf(filtro.getGlobalsearch()));
                setQuery("f.studyid", Integer.valueOf(filtro.getGlobalsearch()));
                setQueryInTo("f.fname", filtro.getGlobalsearch());
                setQuery("f.traitid", Integer.valueOf(filtro.getGlobalsearch()));
                setQuery("f.scaleid", Integer.valueOf(filtro.getGlobalsearch()));
                setQuery("f.tmethid", Integer.valueOf(filtro.getGlobalsearch()));
                setQueryInTo("f.ltype", filtro.getGlobalsearch());
            } else {
                setQueryInTo("f.fname", filtro.getGlobalsearch());
                setQueryInTo("f.ltype", filtro.getGlobalsearch());
            }
        }
        if (isLocal()) {
            setOrder("f.labelid", Order.DESCENDENTE);
        } else {
            setOrder("f.labelid", Order.ASCENDENTE);
        }
        return query;
    }

    /**
     * Return a list of grouping factors by study id
     * @param studyid ID for study
     * @return list of factor or empty list if study id not found
     */
    public List<Factor> getFactorsForStudy(final Integer studyid) {
        List<Factor> factorList = new ArrayList<Factor>();

        String order = "";
        if (isLocal()) {
            order = " order by factor.labelid desc, factor.factorid desc ";
        } else {
            order = " order by factor.labelid , factor.factorid ";
        }

        final String queryString = " from Factor as factor where factor.labelid = factor.factorid and "
                + " factor.studyid = ? " + order;

        factorList = getHibernateTemplate().find(queryString, studyid);

        return factorList;
    }
    
    /**
     * Returns different combinations of TRAIT, SCALE and METHOD
     * @return List<Factor>
     */
    public List<Factor> getFactorConvinacionesTraitScaleMethod(){
        StringBuilder sbHql = new StringBuilder();
        //sbHql.append("FROM Factor GROUP BY TRAITID, SCALEID, TMETHID ORDER by TRAITID ");
        sbHql.append("select distinct f.traitid, f.scaleid, f.tmethid FROM Factor as f order by f.traitid ");
        if(isLocal()){
            sbHql.append("DESC");
        }else{
            sbHql.append("ASC");
        }
   //     log.info(sbHql.toString());
        //List<Factor> factorList = getHibernateTemplate().find(sbHql.toString());
        List<Factor> factorList = new ArrayList<Factor>();
        List<Object> tempList = getHibernateTemplate().find(sbHql.toString());
        for (Object object:tempList) {
            Object[] integerArray = (Object[])object;
            Integer traitid = (Integer)integerArray[0];
            Integer scaleid = (Integer)integerArray[1];
            Integer tmethid = (Integer)integerArray[2];
            Factor factorTemp = new Factor(true);
            factorTemp.setTraitid(traitid);
            factorTemp.setScaleid(scaleid);
            factorTemp.setTmethid(tmethid);
            factorTemp.setTid(traitid);
            factorList.add(factorTemp);
        }
        
        return factorList;
    }
    
    /**
     * Return the main factors by studyid
     * @param Integer studyid
     * @return List<Factor>
     */
    public List<Factor> getMainFactorsByStudyid(Integer studyid){
        StringBuilder sbHql = new StringBuilder();
        sbHql.append("FROM Factor as f WHERE f.studyid = ");
        sbHql.append(studyid);
        sbHql.append(" and f.labelid = f.factorid ORDER BY f.labelid ");
        if(isLocal()){
            sbHql.append("DESC");
        }else{
            sbHql.append("ASC");
        }
  //      log.info(sbHql.toString());
        List<Factor> factorList = getHibernateTemplate().find(sbHql.toString());
        return factorList;
    }
    
    
    
    /**
     * Return the main factors by studyid
     * @param Integer studyid
     * @return List<Factor>
     */
    public List<Factor> getGroupFactorsByStudyidAndFactorid(Integer studyid, Integer factorid){
        StringBuilder sbHql = new StringBuilder();
        sbHql.append("FROM Factor as f WHERE f.studyid = ");
        sbHql.append(studyid);
        sbHql.append(" and f.factorid = ");
        sbHql.append(factorid);
        sbHql.append(" ORDER BY f.labelid ");
        if(isLocal()){
            sbHql.append("DESC");
        }else{
            sbHql.append("ASC");
        }
    //    log.info(sbHql.toString());
        List<Factor> factorList = getHibernateTemplate().find(sbHql.toString());
        return factorList;
    }
    
    /**
     * Return a factors
     * @param factorIds Integer
     * @return Factor
     */
    public List<Factor> getFactorsByFactorsids(final List factorIds){
        log.info(" -- getFactorsByFactorsids" + factorIds);
        
        List<Factor> resultList = new ArrayList<Factor>();
        resultList = getHibernateTemplate().executeFind(new HibernateCallback() {
            public Object doInHibernate(Session session)
                throws HibernateException, SQLException {
                StringBuilder sbHql = new StringBuilder();
                Object [] parametros = factorIds.toArray();
                sbHql.append("FROM Factor as f WHERE f.factorid IN(:ids) ORDER BY f.labelid ");
                String orden;
                if(isLocal()){
                    orden = "DESC";
                }else{
                    orden = "ASC";
                }
                sbHql.append(orden);
                
      //          log.info(sbHql.toString());
                Query query = session.createQuery(sbHql.toString());
                query.setParameterList("ids", parametros);
                return  query.list();
            }
        });
        return resultList;
    }
    
    /**
     * Return factor by studyid an fname
     * @param Integer studyid
     * @param String fname
     * @return Factor
     */
    public Factor getFactorByStudyidAndFname(Integer studyid, String fname){
        StringBuilder sbHql = new StringBuilder();
        sbHql.append("FROM Factor as f WHERE f.studyid = ");
        sbHql.append(studyid);
        sbHql.append(" and f.fname = ");
        sbHql.append("'");
        sbHql.append(fname);
        sbHql.append("'");
        sbHql.append(" ORDER BY f.labelid ");
        if(isLocal()){
            sbHql.append("DESC");
        }else{
            sbHql.append("ASC");
        }
    //    log.info(sbHql.toString());
        List<Factor> factorList = getHibernateTemplate().find(sbHql.toString());
        if(factorList == null){
            return null;
        }else if(factorList.isEmpty()){
            return null;
        }else if(factorList.size() == 0){
            return null;
        }
        for(Factor factorTemp : factorList){
            return factorTemp;
        }
        return null;
    }
}