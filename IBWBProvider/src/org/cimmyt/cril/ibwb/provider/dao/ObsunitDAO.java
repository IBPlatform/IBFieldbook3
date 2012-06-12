package org.cimmyt.cril.ibwb.provider.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.cimmyt.cril.ibwb.api.dao.AbstractDAO;
import org.cimmyt.cril.ibwb.api.dao.utils.ValidatingDataType;
import org.cimmyt.cril.ibwb.domain.Obsunit;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.DetachedCriteria;
import org.springframework.orm.hibernate3.HibernateCallback;

public class ObsunitDAO extends AbstractDAO<Obsunit, Integer> {

    public ObsunitDAO() {
        super(Obsunit.class);
    }

    @Override
    public Obsunit prepareToCreate(Obsunit obsunit) {
        if (isLocal()) {
            obsunit.setOunitid(getNextMin());
        }
        if (isCentral()) {
            obsunit.setOunitid(getNextMax());
        }
        return obsunit;
    }

    @Override
    protected void buildCriteria(DetachedCriteria criteria, Obsunit filter) {
        // TODO Auto-generated method stub
    }

    @Override
    public String getKeyProperty() {
        return "ounitid";
    }

    @Override
    public String getConsulta(Obsunit filtro) {
        String query = "from Obsunit as o";

        if (filtro.getGlobalsearch() == null) {
            setQuery("o.ounitid", filtro.getOunitid());
            setQuery("o.effectid", filtro.getEffectid());
        } else {
            global = true;
            if (ValidatingDataType.isNumeric(filtro.getGlobalsearch())) {
                setQuery("o.ounitid", Integer.valueOf(filtro.getGlobalsearch()));
                setQuery("o.effectid", Integer.valueOf(filtro.getGlobalsearch()));
            } else {
            }
        }
        return query;
    }

    /**
     * Get number of rows for an effect id.  For example you can retrieve row number
     * for Measurement Effect
     * @param effectId
     * @return 
     */
    public int getObservationsCount(final Integer effectId) {
        final String queryString = "select count(obsunit.ounitid) from Obsunit as obsunit where obsunit.effectid = :EFFECTID ";

        Integer observationsCount = 0;

        observationsCount = (Integer) getHibernateTemplate().execute(new HibernateCallback() {

            @Override
            public Object doInHibernate(Session session) throws HibernateException, SQLException {
                Integer observationsCount = 0;

                Query query = session.createQuery(queryString);
                query.setParameter("EFFECTID", effectId);
                Long totalCount = (Long)query.uniqueResult();
                observationsCount = totalCount.intValue();

                return observationsCount;
            }
        });


        return observationsCount;
    }

    /**
     * Gets a list of observations unit for a effect id
     * @param effectId Effect Id to search
     * @return List of observations units or empty list
     */
    public List<Obsunit> getObsunitListByEffectid(final Integer effectId) {
        List<Obsunit> obsunitList = new ArrayList<Obsunit>();
        
        String order = "";
        
        if (effectId.intValue() < 0) {
            order = " order by obsunit.ounitid desc";
        } else {
            order = " order by obsunit.ounitid asc";
        }
        
        final String queryString = "from Obsunit as obsunit where obsunit.effectid = ? " + order ;
        
        obsunitList = getHibernateTemplate().find(queryString,effectId);
        

        return obsunitList;
    }
}