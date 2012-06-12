package org.cimmyt.cril.ibwb.provider.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.cimmyt.cril.ibwb.api.dao.AbstractDAO;
import org.cimmyt.cril.ibwb.api.dao.utils.ValidatingDataType;
import org.cimmyt.cril.ibwb.domain.Trait;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.criterion.DetachedCriteria;
import org.springframework.orm.hibernate3.HibernateCallback;

public class TraitDAO extends AbstractDAO<Trait, Integer> {

    public TraitDAO() {
        super(Trait.class);
    }

    @Override
    public Trait prepareToCreate(Trait trait) {
        if (isLocal()) {
            trait.setTid(getNextMin());
        }
        if (isCentral()) {
            trait.setTid(getNextMax());
        }
        return trait;
    }

    @Override
    protected void buildCriteria(DetachedCriteria criteria, Trait filter) {
        // TODO Auto-generated method stub
    }

    @SuppressWarnings("unchecked")
    public List<Trait> getTraitLista(final Trait filtro, final int start, final int pageSize) {
        List<Trait> resultLst = new ArrayList<Trait>();
        StringBuilder queryString = new StringBuilder("from Trait as o ");
        queryString.append(" join fetch o.scale ");
        queryString.append(" where 1 = 1 ");
        if (filtro.getTraitGroup() != null && !filtro.getTraitGroup().isEmpty()) {
            queryString.append(" and o.traitGroup = '");
            queryString.append(filtro.getTraitGroup()).append("' ");
        }
        if (filtro.getGlobalsearch() != null && !filtro.getGlobalsearch().isEmpty()) {
            queryString.append(" and o.trname like '%").append(filtro.getGlobalsearch()).append("%' ");
        }
        queryString.append(" ");
        final String qString = queryString.toString();
        resultLst = getHibernateTemplate().executeFind(
                new HibernateCallback() {

                    @Override
                    public Object doInHibernate(Session session)
                            throws HibernateException, SQLException {
                        List<Trait> resultList = new ArrayList<Trait>();

                        Query query = session.createQuery(qString.toString());
                        query.setFirstResult(start);
                        query.setFetchSize(pageSize);


                        resultList = query.list();


                        return resultList;
                    }
                });
        return resultLst;
    }

    @Override
    public String getConsulta(Trait filtro) {
        String query = "from Trait as t";

//        setIntersection("t.scale", Intersection.left);

        if (filtro.getGlobalsearch() == null) {
            setQuery("t.tid", filtro.getTid());
            setQuery("t.traitid", filtro.getTraitid());
            setQuery("t.trname", filtro.getTrname());
            setQuery("t.trabbr", filtro.getTrabbr());
            setQuery("t.trdesc", filtro.getTrdesc());
            setQuery("t.scaleid", filtro.getScaleid());
            setQuery("t.tmethid", filtro.getTmethid());
            setQuery("t.tnstat", filtro.getTnstat());
            setQuery("t.traitGroup", filtro.getTraitGroup());
            setQuery("t.ontology", filtro.getOntology());
        } else {
            global = true;
            if (ValidatingDataType.isNumeric(filtro.getGlobalsearch())) {
                setQuery("t.tid", Integer.valueOf(filtro.getTid()));
                setQuery("t.traitid", Integer.valueOf(filtro.getTraitid()));
                setQueryInTo("t.trname", filtro.getTrname());
                setQueryInTo("t.trabbr", filtro.getTrabbr());
                setQueryInTo("t.trdesc", filtro.getTrdesc());
                setQuery("t.scaleid", Integer.valueOf(filtro.getScaleid()));
                setQuery("t.tmethid", Integer.valueOf(filtro.getTmethid()));
                setQuery("t.tnstat", Integer.valueOf(filtro.getTnstat()));
                setQueryInTo("t.traitGroup", filtro.getTraitGroup());
                setQueryInTo("t.ontology", filtro.getOntology());
            } else {
                setQueryInTo("t.trname", filtro.getTrname());
                setQueryInTo("t.trabbr", filtro.getTrabbr());
                setQueryInTo("t.trdesc", filtro.getTrdesc());
                setQueryInTo("t.traitGroup", filtro.getTraitGroup());
                setQueryInTo("t.ontology", filtro.getOntology());
            }
        }
        setQuery("t.traitGroup", filtro.getTraitGroup());

        return query;
    }

    @SuppressWarnings("unchecked")
    public List<String> getTraitGroups() {
        List<String> traitGroups = new ArrayList<String>();

        traitGroups = getHibernateTemplate().executeFind(new HibernateCallback() {

            @Override
            public Object doInHibernate(Session sesion) throws HibernateException, SQLException {
                List<String> traitGroups = new ArrayList<String>();
                String queryString = "SELECT DISTINCT TRAITGROUP FROM TRAIT ";

                SQLQuery sQLQuery = sesion.createSQLQuery(queryString);
                for (Object object : sQLQuery.list()) {
                    String group = (String) object;
                    traitGroups.add(group);
                }

                return traitGroups;
            }
        });

        return traitGroups;
    }

    @Override
    public String getKeyProperty() {
        return "tid";
    }

    public List<Trait> getTraitsWithScales() {
        List<Trait> traitList = new ArrayList<Trait>();


        final String queryString = "from Trait as trait where trait.scaleid <> 0 ";

        traitList = getHibernateTemplate().find(queryString);

        return traitList;
    }
}
