package org.cimmyt.cril.ibwb.provider.dao;

import org.cimmyt.cril.ibwb.api.dao.AbstractDAO;
import org.cimmyt.cril.ibwb.domain.Dmsattr;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.Logger;
import org.cimmyt.cril.ibwb.api.dao.utils.ValidatingDataType;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.DetachedCriteria;
import org.springframework.orm.hibernate3.HibernateCallback;

public class DmsattrDAO extends AbstractDAO<Dmsattr, Integer> {

    private static Logger log = Logger.getLogger(DmsattrDAO.class);

    public DmsattrDAO() {
        super(Dmsattr.class);
    }

    @Override
    public Dmsattr prepareToCreate(Dmsattr dmsattr) {
        if (isLocal()) {
            dmsattr.setDmsatid(getNextMin());
        }
        if (isCentral()) {
            dmsattr.setDmsatid(getNextMax());
        }
        return dmsattr;
    }

    @Override
    protected void buildCriteria(DetachedCriteria criteria, Dmsattr filter) {
        // TODO Auto-generated method stub
    }

    @Override
    public String getKeyProperty() {
        return "dmsatid";
    }

    @Override
    public String getConsulta(Dmsattr filtro) {
        String query = "from Dmsattr as d";

        if (filtro.getGlobalsearch() == null) {
            setQuery("d.dmsatid", filtro.getDmsatid());
            setQuery("d.dmsatype", filtro.getDmsatype());
            setQuery("d.dmsatab", filtro.getDmsatab());
            setQuery("d.dmsatrec", filtro.getDmsatrec());
            setQuery("d.dmsatval", filtro.getDmsatval());
        } else {
            global = true;
            if (ValidatingDataType.isNumeric(filtro.getGlobalsearch())) {
                setQuery("d.dmsatid", Integer.valueOf(filtro.getGlobalsearch()));
                setQuery("d.dmsatype", Integer.valueOf(filtro.getGlobalsearch()));
                setQueryInTo("d.dmsatab", filtro.getGlobalsearch());
                setQuery("d.dmsatrec", Integer.valueOf(filtro.getGlobalsearch()));
                setQueryInTo("d.dmsatval", filtro.getGlobalsearch());
            } else {
                setQueryInTo("d.dmsatab", filtro.getGlobalsearch());
                setQueryInTo("d.dmsatval", filtro.getGlobalsearch());
            }
        }
        return query;
    }

    /**
     * Return a scales
     *
     * @param scales
     * @return Scales
     */
    public Dmsattr getDmsattrByDmsatrecAndDmsatype(Dmsattr dmsattr) {
        StringBuilder sbHql = new StringBuilder();
        Object[] parametros = {dmsattr.getDmsatype(), dmsattr.getDmsatrec()};
        sbHql.append("FROM Dmsattr as d WHERE d.dmsatype = ? and d.dmsatrec = ? ORDER BY d.dmsatid ");
        if (isLocal()) {
            sbHql.append("DESC");
        } else {
            sbHql.append("ASC");
        }
        //    log.info(sbHql.toString());
        List<Dmsattr> listScales = getHibernateTemplate().find(sbHql.toString(), parametros);
        if (listScales.size() > 0) {
            dmsattr = listScales.get(0);
        } else {
            dmsattr = null;
        }
        return dmsattr;
    }

    /**
     * Gets a list of DMSAttrs for each atribute
     *
     * @param listid
     * @return
     */
    public List<Dmsattr> getDmsAttributesByListId(final Integer listid, final List<Integer> lrecIdList) {
        final String queryString = "FROM Dmsattr as d WHERE d.dmsatrec in "
                + " ( :lrecIdList ) and  "
                + " d.dmsatype in (804,805,806,807,808,809) ";
        //List<Dmsattr> dmsattrList = getHibernateTemplate().find(queryString, listid);
//        if (lrecIdList.isEmpty()) {
//            return new ArrayList<Dmsattr>();
//        }
        List<Dmsattr> dmsattrList = getHibernateTemplate().executeFind(new HibernateCallback() {

            @Override
            public Object doInHibernate(Session session) throws HibernateException, SQLException {
                List<Dmsattr> dmsattrList =null;
                Query query = session.createQuery(queryString);
                query.setParameterList("lrecIdList", lrecIdList);
                dmsattrList = query.list();
                return dmsattrList;
            }
        });
        return dmsattrList;
    }
}