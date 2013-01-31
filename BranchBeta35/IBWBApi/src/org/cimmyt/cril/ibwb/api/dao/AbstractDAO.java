package org.cimmyt.cril.ibwb.api.dao;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.List;
import java.util.ArrayList;
import java.util.Date;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Projection;
import org.hibernate.criterion.Projections;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.hibernate.Query;

import org.cimmyt.cril.ibwb.api.dao.utils.Criterion;
import org.cimmyt.cril.ibwb.api.dao.utils.Order;


import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.cimmyt.cril.ibwb.api.dao.utils.Intersection;
import org.cimmyt.cril.ibwb.api.dao.utils.TypesIntersection;
import org.hibernate.*;

/**
 * Base class to manage CRUD operations
 *
 * @author <a href="mailto:t.m.sanchez@cgiar.org">tmsanchez</a>
 * @author last edited by: $Author: tmsanchez $
 *
 * @version $Revision$, $Date: 2010-08-11 17:38:20 -0500 (Wed, 11 Aug 2010) $
 */
public abstract class AbstractDAO<T, PK extends Serializable> extends HibernateDaoSupport implements GenericDAO<T, PK> {

    private Class<T> type;
    private String accessType;
    protected List<Criterion> criterions = new ArrayList<Criterion>();
    private List<Criterion> pivots = new ArrayList<Criterion>();
    private List<Order> orders = new ArrayList<Order>();
    private List<Intersection> intersections = new ArrayList<Intersection>();
    protected boolean global = false;
    
    protected boolean addFinalCriteria;

    public AbstractDAO(Class<T> type) {
        this.type = type;
    }

    /*
     * (non-Javadoc) @see
     * org.cimmyt.cril.apps.sampletracking.core.persistence.dao.GenericDAO#create(java.lang.Object)
     */
    @Override
    public T create(T newInstance) {
        newInstance = prepareToCreate(newInstance);
        getHibernateTemplate().save(newInstance);
        return newInstance;
    }

    /*
     * (non-Javadoc) @see
     * org.cimmyt.cril.apps.sampletracking.core.persistence.dao.GenericDAO#create(java.lang.Object)
     */
    @Override
    public T addOrUpdate(T transientObject) {
        //newInstance = prepareToCreate(newInstance);
        getHibernateTemplate().saveOrUpdate(transientObject);
        return transientObject;
    }

    public T prepareToCreate(T newInstance) {
        return newInstance;
    }
    /*
     * (non-Javadoc) @see
     * org.cimmyt.cril.apps.sampletracking.core.persistence.dao.GenericDAO#delete(java.lang.Object)
     */

    @Override
    public void delete(T persistentObject) {
        getHibernateTemplate().delete(persistentObject);
    }

    /*
     * (non-Javadoc) @see
     * org.cimmyt.cril.apps.sampletracking.core.persistence.dao.GenericDAO#read(java.io.Serializable)
     */
    @Override
    @SuppressWarnings(value = "unchecked")
    public T read(PK id) {
        T result = (T) getHibernateTemplate().get(type, id);
        getHibernateTemplate().initialize(result);
        //return (T)getHibernateTemplate().get(type, id);
        return result;
    }

    /*
     * (non-Javadoc) @see
     * org.cimmyt.cril.apps.sampletracking.core.persistence.dao.GenericDAO#read(java.io.Serializable)
     */
    @SuppressWarnings(value = "unchecked")
    public T findById(PK id) {
        T result = (T) getHibernateTemplate().get(type, id);
        getHibernateTemplate().initialize(result);
        //return (T)getHibernateTemplate().get(type, id);
        return result;
    }

    /*
     * (non-Javadoc) @see
     * org.cimmyt.cril.apps.sampletracking.core.persistence.dao.GenericDAO#update(java.lang.Object)
     */
    @Override
    public void update(T transientObject) {
        getHibernateTemplate().saveOrUpdate(transientObject);
        //getHibernateTemplate().(transientObject);
    }

    @SuppressWarnings(value = "unchecked")
    public List<T> findAll() {
        List<T> resultList = null;

        resultList = this.getHibernateTemplate().executeFind(
                new HibernateCallback() {

                    @Override
                    public Object doInHibernate(Session session)
                            throws HibernateException, SQLException {
                        DetachedCriteria criteria = DetachedCriteria.forClass(type);
                        return getHibernateTemplate().findByCriteria(criteria);
                    }
                });

        return resultList;
    }

    /**
     *
     * @param filter
     * @return
     */
    @SuppressWarnings(value = "unchecked")
    public List<T> getListPorFiltro(final T filter, int start, int pageSize) {
        List<T> resultList = null;
        resultList = this.getHibernateTemplate().executeFind(
                new HibernateCallback() {

                    @Override
                    public Object doInHibernate(Session session)
                            throws HibernateException, SQLException {
                        DetachedCriteria criteria = DetachedCriteria.forClass(type);
                        buildCriteria(criteria, filter);
                        return getHibernateTemplate().findByCriteria(criteria);
                    }
                });
        return resultList;
    }

    @SuppressWarnings(value = "unchecked")
    public int getTotalRegistros(final T filter) {
        Integer result = (Integer) getHibernateTemplate().execute(
                new HibernateCallback() {

                    @Override
                    public Object doInHibernate(Session session)
                            throws HibernateException, SQLException {
                        DetachedCriteria criteria = DetachedCriteria.forClass(type);
                        buildCriteria(criteria, filter);
                        criteria.setProjection(Projections.projectionList().add(Projections.count(getKeyProperty())));
                        try {
                            List lista = getHibernateTemplate().findByCriteria(
                                    criteria);
                            if (lista.size() > 0) {
                                return lista.get(0);
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        return 0;
                    }
                });
        return result;
    }

    public abstract String getKeyProperty();

    /**
     *
     * @param criteria
     * @param filter
     */
    protected abstract void buildCriteria(DetachedCriteria criteria, final T filter);

    //Metodos para agilizar la creacion y definicion de consultas
    public void setQuery(String campo, String valor) {
        if (valor != null && !valor.isEmpty()) {
            criterions.add(HelperAbstractDAO.getQueryLikeAP(campo, valor));
        }
    }

    public void setQueryPivote(String campo, String valor) {
        if (valor != null && !valor.isEmpty()) {
            pivots.add(HelperAbstractDAO.getQueryLikeAP(campo, valor));
        }
    }

    public void setQueryInTo(String campo, String valor) {
        if (valor != null && !valor.isEmpty()) {
            criterions.add(HelperAbstractDAO.getQueryIntoAP(campo, valor));
        }
    }

    public void setQueryInToPivote(String campo, String valor) {
        if (valor != null && !valor.isEmpty()) {
            pivots.add(HelperAbstractDAO.getQueryIntoAP(campo, valor));
        }
    }

    public void setQuery(String campo, Integer valor) {
        if (valor != null) {
            criterions.add(HelperAbstractDAO.getQueryAP(campo, valor));
        }
    }

    public void setQueryNotEqual(String campo, Integer valor) {
        if (valor != null) {
            criterions.add(HelperAbstractDAO.getQueryNotEqual(campo, valor));
        }
    }

    public void setQueryAndNotEqual(String campo, Integer valor) {
        if (valor != null) {
            criterions.add(HelperAbstractDAO.getQueryAndNotEqual(campo, valor));
        }
    }

    public void setQueryPivote(String campo, Integer valor) {
        if (valor != null) {
            pivots.add(HelperAbstractDAO.getQueryAP(campo, valor));
        }
    }

    public void setQuery(String campo, BigDecimal valor) {
        if (valor != null) {
            criterions.add(HelperAbstractDAO.getQueryAP(campo, valor));
        }
    }

    public void setQueryPivote(String campo, BigDecimal valor) {
        if (valor != null) {
            pivots.add(HelperAbstractDAO.getQueryAP(campo, valor));
        }
    }

    public void setQuery(String campo, Double valor) {
        if (valor != null) {
            criterions.add(HelperAbstractDAO.getQueryAP(campo, valor));
        }
    }

    public void setQueryPivote(String campo, Double valor) {
        if (valor != null) {
            pivots.add(HelperAbstractDAO.getQueryAP(campo, valor));
        }
    }

    public void setQuery(String campo, Boolean valor) {
        System.out.println("Campo vale " + campo);
        System.out.println("Valor vale " + valor);
        if (valor != null) {
            criterions.add(HelperAbstractDAO.getQueryAP(campo, valor));
        }
    }

    public void setQueryPivote(String campo, Boolean valor) {
        System.out.println("Campo vale " + campo);
        System.out.println("Valor vale " + valor);
        if (valor != null) {
            pivots.add(HelperAbstractDAO.getQueryAP(campo, valor));
        }
    }

    public void setQuery(String campo, Date valor) {
        if (valor != null) {
            criterions.add(HelperAbstractDAO.getQueryAP(campo, valor));
        }
    }

    public void setQueryPivote(String campo, Date valor) {
        if (valor != null) {
            pivots.add(HelperAbstractDAO.getQueryAP(campo, valor));
        }
    }

    public void setQueryParametrizada(String campo, Object valor, String parametro) {
        if (valor != null) {
            criterions.add(HelperAbstractDAO.getQueryParametrizadaAP(campo, valor, parametro));
        }
    }

    public void setQueryParametrizadaPivote(String campo, Object valor, String parametro) {
        if (valor != null) {
            pivots.add(HelperAbstractDAO.getQueryParametrizadaAP(campo, valor, parametro));
        }
    }

    public void setQueryRango(String campo, Object valor, String parameter, Object valor2, String parameter2) {
        if (valor != null && valor2 != null) {
            criterions.add(HelperAbstractDAO.getQueryRangoAP(campo, valor, parameter, valor2, parameter2));
        }
    }

    public void setQueryRangoPivote(String campo, Object valor, String parameter, Object valor2, String parameter2) {
        if (valor != null && valor2 != null) {
            pivots.add(HelperAbstractDAO.getQueryRangoAP(campo, valor, parameter, valor2, parameter2));
        }
    }

    public void setQueryNull(String campo) {
        System.out.println("Campo vale " + campo);
        Criterion criterio = new Criterion();
        criterio.setCampo(campo);
        criterio.setValor("null");
        criterio.setConsulta(" " + campo + " is " + "null" + " ");
        criterions.add(criterio);
    }

    public void setOrder(String campo, boolean orden) {
        Order ordena = new Order();
        ordena.setCampo(campo);
        ordena.setOrden(orden);
        if (orden) {
            ordena.setOrdena(" " + campo + " asc");
        } else {
            ordena.setOrdena(" " + campo + " desc");
        }
        orders.add(ordena);
    }

    private String getCriterions() {
        String consulta = "";
        if (!criterions.isEmpty()) {
            consulta += " where ";
            for (Criterion criterio : criterions) {
                consulta += criterio.getConsulta() + " and ";
            }
            consulta += "@";
            consulta = consulta.replace(" and @", " ");
        }
        return consulta;
    }

    private String getCriterionsOr() {
        String consulta = "";
        if (!criterions.isEmpty()) {
            consulta += " where (";
            for (Criterion criterio : criterions) {
                consulta += criterio.getConsulta() + " or ";
            }
            consulta += "@";
            consulta = consulta.replace(" or @", " ");
            consulta += " ) ";
        }
        return consulta;
    }

    private String getCriterionsPivot() {
        StringBuffer query = new StringBuffer();
        if (!criterions.isEmpty()) {
            query.append(" where (");
            for (Criterion criterio : criterions) {
                query.append(criterio.getConsulta() + " or ");
            }
            query.replace(query.length() - 4, query.length(), ") ");
            if (!pivots.isEmpty()) {
                query.append("and ");
                for (Criterion criterio : pivots) {
                    query.append(criterio.getConsulta() + " and ");
                }
                query.replace(query.length() - 4, query.length(), " ");
            }
        }
        return query.toString();
    }

    private String getOrders() {
        StringBuffer orderSB = new StringBuffer();
        if (!orders.isEmpty()) {
            orderSB.append(" order by ");
            for (Order orndena : orders) {
                orderSB.append(orndena.getOrdena() + ", ");
            }
            orderSB.replace(orderSB.length() - 2, orderSB.length(), " ");
        }
        return orderSB.toString();
    }

    public void setIntersection(String campo, String intersection) {
        if (intersection != null) {
            Intersection intersectionT = new Intersection();
            intersectionT.setCampo(campo);
            intersectionT.setIntersection(intersection);
            intersections.add(intersectionT);
        }
    }

    private String getIntersectionsJoin() {
        StringBuffer intersectionSB = new StringBuffer();
        if (!intersections.isEmpty()) {
            for (Intersection intersectionT : intersections) {
                intersectionSB.append(
                        " "
                        + TypesIntersection.getJoin(intersectionT.getIntersection())
                        + " "
                        + intersectionT.getCampo()
                        + " ");
            }
//			intersectionSB.replace(intersectionSB.length()-2, intersectionSB.length(), " ");
        }
        return intersectionSB.toString();
    }

    private String getIntersectionsJoinFetch() {
        StringBuffer intersectionSB = new StringBuffer();
        if (!intersections.isEmpty()) {
            for (Intersection intersectionT : intersections) {
                intersectionSB.append(
                        " "
                        + TypesIntersection.getJoinFetch(intersectionT.getIntersection())
                        + " "
                        + intersectionT.getCampo()
                        + " ");
            }
//			intersectionSB.replace(intersectionSB.length()-2, intersectionSB.length(), " ");
        }
        return intersectionSB.toString();
    }

    @SuppressWarnings("unchecked")
    public List<T> getList(final T filtroBase, final int inicio,
            final int tamanioPagina, final boolean paginado) {

        criterions.clear();
        orders.clear();
        intersections.clear();
        pivots.clear();

        List<T> lista = new ArrayList<T>();
        lista = getHibernateTemplate().executeFind(new HibernateCallback() {

            @Override
            public Object doInHibernate(Session session) throws HibernateException,
                    SQLException {

//                String queryHQL = getConsulta(filtroBase);
//                queryHQL += " " + intersection + " " + getCriterions();
//                queryHQL = getOrders(queryHQL);
                StringBuffer queryStringBuffer = new StringBuffer();
                queryStringBuffer.append(getConsulta(filtroBase));
                queryStringBuffer.append(getIntersectionsJoinFetch());
//                if(filtroBase.isPivot()){
//                    queryStringBuffer.append(getCriterionsPivot());
//                }else{
                if (global) {
                    queryStringBuffer.append(getCriterionsOr());
                } else {
                    queryStringBuffer.append(getCriterions());
                }
                
                if (addFinalCriteria) {
                    queryStringBuffer.append(getFinalCriteria());
                } 
                
//                }
                queryStringBuffer.append(getOrders());

                Query query = session.createQuery(queryStringBuffer.toString());
                //assignParameters(query, filtroBase);

                global = false;
                if (paginado) {
                    query.setFirstResult(inicio);
                    query.setMaxResults(tamanioPagina);
                }
                return query.list();
            }
        });
        return lista;
    }

    public int getTotal(final T filtroBase) {

        criterions.clear();
        orders.clear();
        intersections.clear();
        pivots.clear();

        Integer result = (Integer) getHibernateTemplate().execute(new HibernateCallback() {

            public Object doInHibernate(final org.hibernate.Session session) throws HibernateException,
                    SQLException {
//				String queryHQL = "select count(*) " + getConsulta(filtroBase);

//	          queryHQL += "" + getCriterions();
                StringBuffer queryStringBuffer = new StringBuffer();
                queryStringBuffer.append("select count(*) ");
                queryStringBuffer.append(getConsulta(filtroBase));
                queryStringBuffer.append(getIntersectionsJoin());
//                if(filtroBase.isPivot()){
//                    queryStringBuffer.append(getCriterionsPivot());
//                }else{
                if (global) {
                    queryStringBuffer.append(getCriterionsOr());
                } else {
                    queryStringBuffer.append(getCriterions());
                }
//                }
                Query query = session.createQuery(queryStringBuffer.toString());

                //assignParameters(query, filtroBase);

                global = false;
                Long totalRegistros = (Long) query.uniqueResult();
                return totalRegistros.intValue();
            }
        });
        return result;
    }

    public boolean isDuplicated(final T filtroBase) {

        criterions.clear();
        orders.clear();
        intersections.clear();
        pivots.clear();

        boolean existe = false;
        Integer result = (Integer) getHibernateTemplate().execute(new HibernateCallback() {

            public Object doInHibernate(final org.hibernate.Session session) throws HibernateException,
                    SQLException {

//					String queryHQL = "select count(*) " + getConsulta(filtro);
//					queryHQL += "" + getCriterions();

                StringBuffer queryStringBuffer = new StringBuffer();
                queryStringBuffer.append("select count(*) ");
                queryStringBuffer.append(getConsulta(filtroBase));
//                if(filtroBase.isPivot()){
//                    queryStringBuffer.append(getCriterionsPivot());
//                }else{
                if (global) {
                    queryStringBuffer.append(getCriterionsOr());
                } else {
                    queryStringBuffer.append(getCriterions());
                }
//                }

                Query query = session.createQuery(queryStringBuffer.toString());
                //assignParameters(query, filtro);
                global = false;
                Long totalRegistros = (Long) query.uniqueResult();
                return totalRegistros.intValue();
            }
        });
        existe = result > 0;
        return existe;
    }

    public abstract String getConsulta(final T filtro);
    //public abstract void assignParameters(Query query, final T filtro);

    public String getAccessType() {
        return accessType;
    }

    public void setAccessType(String accessType) {
        this.accessType = accessType;
    }

    protected boolean isLocal() {
        return accessType.equals("local");
    }

    protected boolean isCentral() {
        return accessType.equals("central");
    }

    public Integer getNextMin() {
        Integer result = (Integer) getHibernateTemplate().execute(
                new HibernateCallback() {

                    public Object doInHibernate(
                            final org.hibernate.Session session)
                            throws HibernateException, SQLException {
                        DetachedCriteria criteria = DetachedCriteria.forClass(type);
                        int siguienteFolio = 0;
                        try {
                            criteria.setProjection(Projections.projectionList().add(Projections.min(getKeyProperty())));
                        } catch (Exception ex) {
                            System.out.println("Error getMin");
                            System.out.println(ex);
                            return new ArrayList<T>();
                        }
                        try {
                            List lista = getHibernateTemplate().findByCriteria(criteria);
                            if (lista.size() > 0) {
                                if (lista.get(0) == null) {
                                    siguienteFolio = 0;
                                } else {
                                    siguienteFolio = (Integer) lista.get(0);
                                }
                                return siguienteFolio - 1;
                            }
                        } catch (Exception e) {
                            System.out.println("Error get min iterator list");
                            System.out.println(e);
                        }
                        return 0;
                    }
                });
        if (result > 0) {
            result = -1;
        }
     //   System.out.println("getMinResult : " + result);

        return result;
    }

    public Integer getNextMax() {
        Integer result = (Integer) getHibernateTemplate().execute(
                new HibernateCallback() {

                    public Object doInHibernate(
                            final org.hibernate.Session session)
                            throws HibernateException, SQLException {
                        DetachedCriteria criteria = DetachedCriteria.forClass(type);
                        int siguienteFolio = 0;
                        try {
                            criteria.setProjection(Projections.projectionList().add(Projections.max(getKeyProperty())));
                        } catch (Exception ex) {
                            System.out.println("Error getMax");
                            System.out.println(ex);
                            return new ArrayList<T>();
                        }
                        try {
                            List lista = getHibernateTemplate().findByCriteria(criteria);
                            if (lista.size() > 0) {
                                if (lista.get(0) == null) {
                                    siguienteFolio = 0;
                                } else {
                                    siguienteFolio = (Integer) lista.get(0);
                                }
                                return siguienteFolio + 1;
                            }
                        } catch (Exception e) {
                            System.out.println("Error get max iterator list");
                            System.out.println(e);
                        }
                        return 0;
                    }
                });
        if (result < 0) {
            result = 1;
        }
        System.out.println("getMaxResult : " + result);
        return result;
    }

    protected Integer executeQueryCustomUniqueResult(final String queryHQL) {
        Integer result = (Integer) getHibernateTemplate().execute(new HibernateCallback() {

            public Object doInHibernate(Session session) throws HibernateException,
                    SQLException {
                Query query = session.createQuery(queryHQL);
                return (Integer) query.uniqueResult();
            }
        });
        return result;
    }

    protected Object executeQueryCustomUniqueResultSqlNat(final String querySQL) {
        Object result = getHibernateTemplate().execute(new HibernateCallback() {

            public Object doInHibernate(Session session) throws HibernateException,
                    SQLException {
                SQLQuery query = session.createSQLQuery(querySQL);
                return query.uniqueResult();
            }
        });
        return result;
    }

    @SuppressWarnings("unchecked")
    protected List executeQueryCustomListOfG(final String queryHQL) {
        List resultList = new ArrayList();
        resultList = getHibernateTemplate().executeFind(new HibernateCallback() {

            public Object doInHibernate(Session session)
                    throws HibernateException, SQLException {
                Query query = session.createQuery(queryHQL);
                return query.list();
            }
        });
        return resultList;
    }

    protected List executeQueryCustomListOfGSqlNat(final String querySQL) {
        List resultList = new ArrayList();
        resultList = getHibernateTemplate().executeFind(new HibernateCallback() {

            public Object doInHibernate(Session session)
                    throws HibernateException, SQLException {
                SQLQuery query = session.createSQLQuery(querySQL);

                return query.list();
            }
        });
        return resultList;
    }

    protected int executeUpdateCustomOfGSqlNat(final String querySQL) {
        int result = 0;
        result = (Integer) getHibernateTemplate().execute(new HibernateCallback() {

            public Object doInHibernate(Session session)
                    throws HibernateException, SQLException {
                SQLQuery query = session.createSQLQuery(querySQL);
                return query.executeUpdate();
            }
        });
        return result;
    }

    @SuppressWarnings("unchecked")
    protected List<T> executeQueryCustomListOfT(final String queryHQL) {
        List<T> resultList = new ArrayList<T>();
        resultList = getHibernateTemplate().executeFind(new HibernateCallback() {

            public Object doInHibernate(Session session)
                    throws HibernateException, SQLException {
                Query query = session.createQuery(queryHQL);
                return query.list();
            }
        });
        return resultList;
    }

    protected BigDecimal executeQueryCustomUniqueResultSum(final String queryHQL) {
        BigDecimal result = (BigDecimal) getHibernateTemplate().execute(new HibernateCallback() {

            public Object doInHibernate(Session session) throws HibernateException,
                    SQLException {
                Query query = session.createQuery(queryHQL);
                return (BigDecimal) query.uniqueResult();
            }
        });
        return result;
    }

    protected T executeQueryCustomUniqueResultOfT(final String queryHQL) {
        T result = (T) getHibernateTemplate().execute(new HibernateCallback() {

            public Object doInHibernate(Session session) throws HibernateException,
                    SQLException {
                Query query = session.createQuery(queryHQL);
                return (T) query.uniqueResult();
            }
        });
        return result;
    }
    
    protected String getFinalCriteria() {
        return "";
    }
}