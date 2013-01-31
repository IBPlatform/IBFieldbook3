package org.cimmyt.cril.ibwb.provider.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.naming.spi.DirStateFactory;
import org.apache.log4j.Logger;
import org.cimmyt.cril.ibwb.api.dao.AbstractDAO;
import org.cimmyt.cril.ibwb.api.dao.utils.Intersection;
import org.cimmyt.cril.ibwb.api.dao.utils.ValidatingDataType;
import org.cimmyt.cril.ibwb.domain.Measuredin;

import org.cimmyt.cril.ibwb.domain.Scales;
import org.cimmyt.cril.ibwb.domain.TmsMethod;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate3.HibernateCallback;

public class MeasuredinDAO extends AbstractDAO<Measuredin, Integer> {

    private static Logger log = Logger.getLogger(MeasuredinDAO.class);

    public MeasuredinDAO() {
        super(Measuredin.class);
    }

    @Override
    public Measuredin prepareToCreate(Measuredin measuredin) {
        if (isLocal()) {
            measuredin.setMeasuredinid(getNextMin());
        }
        if (isCentral()) {
            measuredin.setMeasuredinid(getNextMax());
        }
        return measuredin;
    }

    @Override
    public Measuredin addOrUpdate(Measuredin measuredin) {
        if (measuredin.getMeasuredinid() == null) {
            if (isLocal()) {
                measuredin.setMeasuredinid(getNextMin());
            }
            if (isCentral()) {
                measuredin.setMeasuredinid(getNextMax());
            }
        } else if (measuredin.getMeasuredinid() != null && measuredin.getMeasuredinid().intValue() == 0) {
            if (isLocal()) {
                measuredin.setMeasuredinid(getNextMin());
            }
            if (isCentral()) {
                measuredin.setMeasuredinid(getNextMax());
            }
        }
        getHibernateTemplate().saveOrUpdate(measuredin);
        return measuredin;
    }

    @Override
    protected void buildCriteria(DetachedCriteria criteria, Measuredin filter) {
        // TODO Auto-generated method stub
    }

    @Override
    public String getKeyProperty() {
        return "measuredinid";
    }

    @Override
    public String getConsulta(Measuredin filtro) {
        String query = "from Measuredin as m";

//        setIntersection("m.scales", Intersection.left);

        if (filtro.getGlobalsearch() == null) {
            setQuery("m.traitid", filtro.getTraitid());
            setQuery("m.scaleid", filtro.getScaleid());
            setQuery("m.tmethid", filtro.getTmethid());
//            if(filtro.getScales() != null){
//                setQuery("m.scales.scaleid", filtro.getScales().getScaleid());
//            }
            setQuery("m.standardscale", filtro.getStandardscale());
            setQuery("m.report", filtro.getReport());
            setQuery("m.formula", filtro.getFormula());
        } else {
            global = true;
            if (ValidatingDataType.isNumeric(filtro.getGlobalsearch())) {
                setQuery("m.traitid", filtro.getGlobalsearch());
                setQuery("m.scaleid", filtro.getGlobalsearch());
                setQuery("m.tmethid", filtro.getGlobalsearch());
//                setQuery("m.scales.scaleid", filtro.getGlobalsearch());
                setQueryInTo("m.standardscale", filtro.getGlobalsearch());
                setQueryInTo("m.report", filtro.getGlobalsearch());
                setQueryInTo("m.formula", filtro.getGlobalsearch());
            } else {
                setQueryInTo("m.standardscale", filtro.getGlobalsearch());
                setQueryInTo("m.report", filtro.getGlobalsearch());
                setQueryInTo("m.formula", filtro.getGlobalsearch());
            }
        }
        return query;
    }

    /**
     * Return a Measuredin
     * @param Measuredin
     * @return Measuredin
     */
    public Measuredin getMeasuredinByTraitidScaleidTmethid(Measuredin measuredin) {
        StringBuilder sbHql = new StringBuilder();
        Object[] parametros = {measuredin.getTraitid(), measuredin.getScaleid(), measuredin.getTmethid()};
        sbHql.append("FROM Measuredin as m WHERE m.traitid = ? and m.scaleid = ? and m.tmethid = ? ORDER BY m.scaleid ");
        if (isLocal()) {
            sbHql.append("DESC");
        } else {
            sbHql.append("ASC");
        }
      //  log.info(sbHql.toString());
       // log.info("Where parameters :" + parametros);
        List<Measuredin> listMeasuredins = getHibernateTemplate().find(sbHql.toString(), parametros);
        if (listMeasuredins.size() > 0) {
            measuredin = listMeasuredins.get(0);
        } else {
            measuredin = null;
        }
        return measuredin;
    }


    /**
     * Gets a list of measured in list by trait id
     * @param traitId Trait ID
     * @return List of measuredin objects filled with it's scale and method
     */
    public List<Measuredin> getMeasuredInListByTrait(final Integer traitId) {
        List<Measuredin> measuredinList = new ArrayList<Measuredin>();

        measuredinList = getHibernateTemplate().executeFind(new HibernateCallback() {

            @Override
            public Object doInHibernate(Session session) throws HibernateException, SQLException {
                List<Measuredin> measuredinList = new ArrayList<Measuredin>();
                String queryString = "from Measuredin as mi where mi.traitid = ? ";

                measuredinList = getHibernateTemplate().find(queryString, traitId);



                return measuredinList;
            }
        });

        return measuredinList;
    }

    
    public Measuredin getMeasuredinByTraitidAndScaleid(Measuredin measuredin){
        StringBuilder sbHql = new StringBuilder();
        Object [] parametros = {measuredin.getTraitid(), measuredin.getScaleid()};
        sbHql.append("FROM Measuredin as m WHERE m.traitid = ? and m.scaleid = ? ORDER BY m.scaleid ");
        if(isLocal()){
            sbHql.append("DESC");
        }else{
            sbHql.append("ASC");
        }
        log.info(sbHql.toString());
        log.info("Where parameters :" + parametros);
        List<Measuredin> listMeasuredins = getHibernateTemplate().find(sbHql.toString(), parametros);
        if(listMeasuredins.size()>0){
            measuredin = listMeasuredins.get(0);
        }else{
            measuredin = null;
        }
        return measuredin;
    }

}