package org.cimmyt.cril.ibwb.provider.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.cimmyt.cril.ibwb.api.dao.AbstractDAO;
import org.cimmyt.cril.ibwb.api.dao.utils.ValidatingDataType;
import org.cimmyt.cril.ibwb.domain.Oindex;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.DetachedCriteria;
import org.springframework.orm.hibernate3.HibernateCallback;

public class OindexDAO extends AbstractDAO<Oindex, Integer> {

    public OindexDAO() {
        super(Oindex.class);
    }

    @Override
    protected void buildCriteria(DetachedCriteria criteria, Oindex filter) {
        // TODO Auto-generated method stub
    }

    @Override
    public String getKeyProperty() {
        return "locationid";
    }

    @Override
    public String getConsulta(Oindex filtro) {
        String query = "from Oindex as o";

        if (filtro.getGlobalsearch() == null) {
            if (filtro.getOindexPK() != null) {
                setQuery("o.oindexPK.ounitid", filtro.getOindexPK().getOunitid());
                setQuery("o.oindexPK.factorid", filtro.getOindexPK().getFactorid());
                setQuery("o.oindexPK.levelno", filtro.getOindexPK().getLevelno());
                setQuery("o.oindexPK.represno", filtro.getOindexPK().getRepresno());
            }
        } else {
            global = true;
            if (ValidatingDataType.isNumeric(filtro.getGlobalsearch())) {
                setQuery("o.oindexPK.ounitid", Integer.valueOf(filtro.getGlobalsearch()));
                setQuery("o.oindexPK.factorid", Integer.valueOf(filtro.getGlobalsearch()));
                setQuery("o.oindexPK.levelno", Integer.valueOf(filtro.getGlobalsearch()));
                setQuery("o.oindexPK.represno", Integer.valueOf(filtro.getGlobalsearch()));
            } else {
            }
        }
        return query;
    }

    /**
     * Return a list of Oindex by it represno
     * @param represno respresno to search
     * @return List of Oindex or empty list if not records match
     */
    public List<Oindex> getOindexListByRepresno(final Integer represno) {
        List<Oindex> oindexList = new ArrayList<Oindex>();

        String order = "";
        if (isCentral()) {
            order = " order by oindex.oindexPK.ounitid , oindex.oindexPK.factorid , oindex.oindexPK.levelno  ";
        } else {
            order = " order by oindex.oindexPK.ounitid desc, oindex.oindexPK.factorid desc, oindex.oindexPK.levelno desc ";
        }

        final String queryString = "from Oindex as oindex where oindex.oindexPK.represno = ? " + order;

        oindexList = getHibernateTemplate().find(queryString, represno);

        return oindexList;
    }
}