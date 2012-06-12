package org.cimmyt.cril.ibwb.provider.dao;

import java.sql.SQLException;
import java.util.List;
import org.cimmyt.cril.ibwb.api.dao.AbstractDAO;

import org.cimmyt.cril.ibwb.domain.TmsScaleDis;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.DetachedCriteria;
import org.springframework.orm.hibernate3.HibernateCallback;

/**
 *
 * @author TMSANCHEZ
 */
public class TmsScaleDisDAO extends AbstractDAO<TmsScaleDis, Integer> {

    public TmsScaleDisDAO() {
        super(TmsScaleDis.class);
    }

    @Override
    public TmsScaleDis prepareToCreate(TmsScaleDis tmsScaleDis) {
        if (isLocal()) {
            tmsScaleDis.setTmsscaledisid(getNextMin());
        } else if (isCentral()) {
            tmsScaleDis.setTmsscaledisid(getNextMax());
        }
        return tmsScaleDis;
    }

    @Override
    public TmsScaleDis addOrUpdate(TmsScaleDis tmsScaleDis) {
        if (tmsScaleDis.getTmsscaledisid() == null) {
            if (isLocal()) {
                tmsScaleDis.setTmsscaledisid(getNextMin());
            }
            if (isCentral()) {
                tmsScaleDis.setTmsscaledisid(getNextMax());
            }
        } else if (tmsScaleDis.getTmsscaledisid() != null && tmsScaleDis.getTmsscaledisid().intValue() == 0) {
            if (isLocal()) {
                tmsScaleDis.setTmsscaledisid(getNextMin());
            }
            if (isCentral()) {
                tmsScaleDis.setTmsscaledisid(getNextMax());
            }
        }
        getHibernateTemplate().saveOrUpdate(tmsScaleDis);
        return tmsScaleDis;
    }

    @Override
    public String getKeyProperty() {
        return "tmsscaledisid";
    }

    @Override
    protected void buildCriteria(DetachedCriteria criteria, TmsScaleDis filter) {
    }

    @Override
    public String getConsulta(TmsScaleDis filtro) {
        String query = "from TmsScaleDis as t";
        return query;
    }

    /**
     * Gets a list of TmsScaleDis by it measuredin id
     * @param measuredindid measuredin to search
     * @return list of TmsScaleDis or empty list
     */
    @SuppressWarnings("unchecked")
    public List<TmsScaleDis> getTmsScaleDisByMeasuredinId(final Integer measuredindid) {
        String queryString = "from TmsScaleDis as t where t.measuredinid = ?";
        return getHibernateTemplate().find(queryString, measuredindid);
    }

    /**
     * Gets a TmsScaleDis by Measured In ID
     * @param Measured In ID
     * @return TmsScaleDis Object if found, if not it returns NULL
     */
    public TmsScaleDis getScaleDisByMeasuredinId(final Integer measuredinId) {
        TmsScaleDis scaledis = null;
        final String queryString = "from TmsScaleDis as sc where sc.measuredinid = :MEASUREDINID ";

        scaledis = (TmsScaleDis) getHibernateTemplate().execute(new HibernateCallback() {

            @Override
            public Object doInHibernate(Session session) throws HibernateException, SQLException {
                Query query = session.createQuery(queryString);
                query.setParameter("MEASUREDINID", measuredinId);
                TmsScaleDis scaledis = (TmsScaleDis) query.uniqueResult();
                return scaledis;
            }
        });

        return scaledis;
    }
}
