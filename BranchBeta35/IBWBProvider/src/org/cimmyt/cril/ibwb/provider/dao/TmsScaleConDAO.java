package org.cimmyt.cril.ibwb.provider.dao;

import java.sql.SQLException;
import java.util.List;
import org.cimmyt.cril.ibwb.api.dao.AbstractDAO;

import org.cimmyt.cril.ibwb.domain.TmsScaleCon;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.DetachedCriteria;
import org.springframework.orm.hibernate3.HibernateCallback;

/**
 *
 * @author TMSANCHEZ
 */
public class TmsScaleConDAO extends AbstractDAO<TmsScaleCon, Integer> {

    public TmsScaleConDAO() {
        super(TmsScaleCon.class);
    }

    @Override
    public TmsScaleCon prepareToCreate(TmsScaleCon tmsScaleCon) {
        if (isLocal()) {
            tmsScaleCon.setTmsscaleconid(getNextMin());
        } else if (isCentral()) {
            tmsScaleCon.setTmsscaleconid(getNextMax());
        }
        return tmsScaleCon;
    }

    @Override
    public TmsScaleCon addOrUpdate(TmsScaleCon tmsScaleCon) {
        if (tmsScaleCon.getTmsscaleconid() == null) {
            if (isLocal()) {
                tmsScaleCon.setTmsscaleconid(getNextMin());
            }
            if (isCentral()) {
                tmsScaleCon.setTmsscaleconid(getNextMax());
            }
        } else if (tmsScaleCon.getTmsscaleconid() != null && tmsScaleCon.getTmsscaleconid().intValue() == 0) {
            if (isLocal()) {
                tmsScaleCon.setTmsscaleconid(getNextMin());
            }
            if (isCentral()) {
                tmsScaleCon.setTmsscaleconid(getNextMax());
            }
        }
        getHibernateTemplate().saveOrUpdate(tmsScaleCon);
        return tmsScaleCon;
    }

    @Override
    public String getKeyProperty() {
        return "tmsscaleconid";
    }

    @Override
    protected void buildCriteria(DetachedCriteria criteria, TmsScaleCon filter) {
    }

    @Override
    public String getConsulta(TmsScaleCon filtro) {
        String query = "from TmsScaleCon as t";
        return query;
    }

    /**
     * Gets a list of TmsScaleCon by it measuredin id
     * @param measuredindid measuredin to search
     * @return list of TmsScaleCon or empty list
     */
    @SuppressWarnings("unchecked")
    public List<TmsScaleCon> getTmsScaleConByMeasuredinId(final Integer measuredindid) {
        String queryString = "from TmsScaleCon as t where t.measuredinid = ?";
        return getHibernateTemplate().find(queryString, measuredindid);
    }

    /**
     * Gets a ScaleCon by Measured In ID
     * @param Measured In ID
     * @return TmsScaleCon Object if found, if not it returns NULL
     */
    public TmsScaleCon getScaleConByMeasuredinId(final Integer measuredinId) {
        TmsScaleCon scalecon = null;
        final String queryString = "from TmsScaleCon as sc where sc.measuredinid = :MEASUREDINID ";

        scalecon = (TmsScaleCon) getHibernateTemplate().execute(new HibernateCallback() {

            @Override
            public Object doInHibernate(Session session) throws HibernateException, SQLException {
                Query query = session.createQuery(queryString);
                query.setParameter("MEASUREDINID", measuredinId);
                TmsScaleCon scalecon = (TmsScaleCon) query.uniqueResult();
                return scalecon;
            }
        });

        return scalecon;
    }
}
