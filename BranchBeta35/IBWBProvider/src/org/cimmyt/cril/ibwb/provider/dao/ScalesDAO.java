package org.cimmyt.cril.ibwb.provider.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.Logger;
import org.cimmyt.cril.ibwb.api.dao.AbstractDAO;
import org.cimmyt.cril.ibwb.api.dao.utils.Intersection;
import org.cimmyt.cril.ibwb.api.dao.utils.ValidatingDataType;
import org.cimmyt.cril.ibwb.domain.Scales;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate3.HibernateCallback;

public class ScalesDAO extends AbstractDAO<Scales, Integer> {

    private static Logger log = Logger.getLogger(ScalesDAO.class);

    public ScalesDAO() {
        super(Scales.class);
    }

    @Override
    public Scales prepareToCreate(Scales scales) {
        if (isLocal()) {
            scales.setScaleid(getNextMin());
        }
        if (isCentral()) {
            scales.setScaleid(getNextMax());
        }
        return scales;
    }

    @Override
    protected void buildCriteria(DetachedCriteria criteria, Scales filter) {
        // TODO Auto-generated method stub
    }

    @Override
    public String getKeyProperty() {
        return "scaleid";
    }

    @Override
    public String getConsulta(Scales filtro) {
        String query = "from Scales as s";

        if (filtro.getGlobalsearch() == null) {
            setQuery("s.scaleid", filtro.getScaleid());
            setQuery("s.scname", filtro.getScname());
            setQuery("s.sctype", filtro.getSctype());
            setQuery("s.ontology", filtro.getOntology());
        } else {
            global = true;
            if (ValidatingDataType.isNumeric(filtro.getGlobalsearch())) {
                setQuery("s.scaleid", Integer.valueOf(filtro.getGlobalsearch()));
                setQueryInTo("s.scname", filtro.getGlobalsearch());
                setQueryInTo("s.sctype", filtro.getGlobalsearch());
                setQueryInTo("s.ontology", filtro.getGlobalsearch());
            } else {
                setQueryInTo("s.scname", filtro.getGlobalsearch());
                setQueryInTo("s.sctype", filtro.getGlobalsearch());
                setQueryInTo("s.ontology", filtro.getGlobalsearch());
            }
        }
        return query;
    }
    
    public void refresh() {
        getHibernateTemplate().flush();
    }
    
    /**
     * Return a scales
     * @param scales
     * @return Scales
     */
    public Scales getScalesByScnameAndSctype(Scales scales){
        StringBuilder sbHql = new StringBuilder();
        Object [] parametros = {scales.getScname(), scales.getSctype()};
        sbHql.append("FROM Scales as s WHERE s.scname = ? and s.sctype = ? ORDER BY s.scaleid ");
        if(isLocal()){
            sbHql.append("DESC");
        }else{
            sbHql.append("ASC");
        }
      //  log.info(sbHql.toString());
        List<Scales> listScales = getHibernateTemplate().find(sbHql.toString(), parametros);
        if(listScales.size()>0){
            scales = listScales.get(0);
        }else{
            scales = null;
        }
        return scales;
    }
    
    /**
     * Return a scales
     * @param scales
     * @return Scales
     */
    public Scales getScalesByScname(Scales scales){
        StringBuilder sbHql = new StringBuilder();
        Object [] parametros = {scales.getScname()};
        sbHql.append("FROM Scales as s WHERE s.scname = ? ORDER BY s.scaleid ");
        if(isLocal()){
            sbHql.append("DESC");
        }else{
            sbHql.append("ASC");
        }
    //    log.info(sbHql.toString());
        List<Scales> listScales = getHibernateTemplate().find(sbHql.toString(), parametros);
        if(listScales.size()>0){
            scales = listScales.get(0);
        }else{
            scales = null;
        }
        return scales;
    }
}