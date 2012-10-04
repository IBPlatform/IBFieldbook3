package org.cimmyt.cril.ibwb.provider.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.cimmyt.cril.ibwb.api.dao.AbstractDAO;
import org.cimmyt.cril.ibwb.api.dao.utils.ValidatingDataType;
import org.cimmyt.cril.ibwb.domain.Listnms;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate3.HibernateCallback;

public class ListnmsDAO extends AbstractDAO<Listnms, Integer> {

    public ListnmsDAO() {
        super(Listnms.class);
    }

    @Override
    public Listnms prepareToCreate(Listnms listnms) {
        if (isLocal()) {
            listnms.setListid(getNextMin());
        }
        if (isCentral()) {
            listnms.setListid(getNextMax());
        }
        return listnms;
    }

    @Override
    protected void buildCriteria(DetachedCriteria criteria, Listnms filter) {
        if (filter.getListid() != null) {
            criteria.add(Restrictions.eq("", filter.getListid()));
        }
    }

    @Override
    public String getKeyProperty() {
        return "listid";
    }

    @SuppressWarnings("unchecked")
    public List<Listnms> getListnmsLista(final Listnms filtro, final int start, final int pageSize) {
        List<Listnms> resultLst = new ArrayList<Listnms>();

        final StringBuilder queryString = new StringBuilder("from Listnms as o "
                + " where 1 = 1 ");

        resultLst = getHibernateTemplate().executeFind(
                new HibernateCallback() {

                    @Override
                    public Object doInHibernate(Session session)
                            throws HibernateException, SQLException {
                        List<Listnms> resultList = new ArrayList<Listnms>();

                        Query query = session.createQuery(queryString.toString());
                        query.setFirstResult(start);
                        query.setFetchSize(pageSize);

                        //query.setParameter("RESPONSIBLEID", responsibleId);

                        resultList = query.list();

                        // populate each dto
                        for (Listnms dto : resultList) {
//						for (ActivityResponsible ar : activity.getActivityResponsibleCollection() ) {
//							ar.getResponsible().getFullName();
//						}
                        }

                        return resultList;
                    }
                });



        return resultLst;
    }

    @Override
    public String getConsulta(Listnms filtro) {
        String query = "from Listnms as l";

        if (filtro.getGlobalsearch() == null) {
            setQuery("l.listid", filtro.getListid());
            setQuery("l.listname", filtro.getListname());
            setQuery("l.listdate", filtro.getListdate());
            setQuery("l.listtype", filtro.getListtype());
            setQuery("l.listuid", filtro.getListuid());
            setQuery("l.listdesc", filtro.getListdesc());
            setQuery("l.lhierarchy", filtro.getLhierarchy());
            //setQuery("l.liststatus", filtro.getListstatus());
            // only active lists
            setQueryNotEqual("l.liststatus", Listnms.LSSTATUS_DELETED);
        } else {
            global = true;
            if (ValidatingDataType.isNumeric(filtro.getGlobalsearch())) {
                setQuery("l.listid", Integer.valueOf(filtro.getGlobalsearch()));
                setQueryInTo("l.listname", filtro.getGlobalsearch());
                setQuery("l.listdate", Integer.valueOf(filtro.getGlobalsearch()));
                setQueryInTo("l.listtype", filtro.getGlobalsearch());
                setQuery("l.listuid", Integer.valueOf(filtro.getGlobalsearch()));
                setQueryInTo("l.listdesc", filtro.getGlobalsearch());
                setQuery("l.lhierarchy", Integer.valueOf(filtro.getGlobalsearch()));
                //setQuery("l.liststatus", Integer.valueOf(filtro.getGlobalsearch()));
                // only active lists

            } else {
                setQueryInTo("l.listname", filtro.getGlobalsearch());
                setQueryInTo("l.listtype", filtro.getGlobalsearch());
                setQueryInTo("l.listdesc", filtro.getGlobalsearch());
                // only active lists

            }
            addFinalCriteria = true;


        }
        return query;
    }

    @SuppressWarnings(value = "unchecked")
    @Override
    public List<Listnms> findAll() {
        List<Listnms> resultList = null;

        resultList = this.getHibernateTemplate().executeFind(
                new HibernateCallback() {

                    @Override
                    public Object doInHibernate(Session session)
                            throws HibernateException, SQLException {
                        DetachedCriteria criteria = DetachedCriteria.forClass(Listnms.class);
                        criteria.add(Restrictions.ne("listid", 0));
                        criteria.add(Restrictions.ne("liststatus", 0));
                        criteria.add(Restrictions.ne("liststatus", 9));
                        return getHibernateTemplate().findByCriteria(criteria);
                    }
                });

        return resultList;
    }

    /**
     * Deletes logically a list
     *
     * @param listnms List to delete
     */
    public void logicalDelete(Listnms listnms) {
        listnms = read(listnms.getListid());
        listnms.setListstatus(Listnms.LSSTATUS_DELETED);
        getHibernateTemplate().update(listnms);
    }

    @Override
    protected String getFinalCriteria() {
        StringBuilder finalCriteria = new StringBuilder();
        if (criterions.isEmpty()) {
            finalCriteria.append(" where ");
        } else if (criterions.size() > 0) {
            finalCriteria.append(" and ");
        }
        finalCriteria.append(" l.liststatus <> ").append(Listnms.LSSTATUS_DELETED);
        finalCriteria.append(" and l.liststatus <> ").append(Listnms.LSSTATUS_FOLDER);
        return finalCriteria.toString();
    }

    /**
     * Checks if a List already exists in local or central
     *
     * @param listName list name to search
     * @return
     * <code>true</code> if exists,
     * <code>false</code> if not
     */
    public boolean existGermplasmListName(String listName) {
        boolean existGermplasm = false;
        String queryString = " from Listnms as l where  l.listname = ? ";
        existGermplasm = getHibernateTemplate().find(queryString, listName).size() > 0;
        return existGermplasm;
    }
}