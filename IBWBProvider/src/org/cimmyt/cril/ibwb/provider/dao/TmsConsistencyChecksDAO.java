/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.cimmyt.cril.ibwb.provider.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.Logger;
import org.cimmyt.cril.ibwb.api.dao.AbstractDAO;
import org.cimmyt.cril.ibwb.api.dao.utils.ValidatingDataType;
import org.cimmyt.cril.ibwb.domain.Traits;
import org.cimmyt.cril.ibwb.domain.TmsConsistencyChecks;
import org.hibernate.HibernateException;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.criterion.DetachedCriteria;
import org.springframework.orm.hibernate3.HibernateCallback;

/**
 *
 * @author MasterGama
 */
public class TmsConsistencyChecksDAO extends AbstractDAO<TmsConsistencyChecks, Integer> {

    private static Logger log = Logger.getLogger(TmsConsistencyChecksDAO.class);

    public TmsConsistencyChecksDAO() {
        super(TmsConsistencyChecks.class);
    }

    @Override
    public TmsConsistencyChecks prepareToCreate(TmsConsistencyChecks tmsConsistencyChecks) {
        if (isLocal()) {
            //TODO: Ask if tid and traitid should have same value
            tmsConsistencyChecks.setImplicationid(getNextMin());
        }
        if (isCentral()) {
            //TODO: Ask if tid and traitid should have same value            
            tmsConsistencyChecks.setImplicationid(getNextMax());
        }
        return tmsConsistencyChecks;
    }

    @Override
    public String getKeyProperty() {
        return "implicationid";
    }

    @Override
    protected void buildCriteria(DetachedCriteria criteria, TmsConsistencyChecks filter) {
        // TODO Auto-generated method stub
    }

    @Override
    public String getConsulta(TmsConsistencyChecks filtro) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    /**
     * Checks if TmsConsistency-checks table already exists in database
     * @return <code>true</code> if exists, <code>false</code> if does not exist.
     */
    public boolean existsOldTable() {
        Boolean result = false;
        log.info("Checking if TmsConsistency_checks table exists");
        result = (Boolean) getHibernateTemplate().execute(new HibernateCallback() {

            @Override
            public Object doInHibernate(Session session) throws HibernateException, SQLException {
                Boolean result = false;
                SQLQuery query = session.createSQLQuery("select * from tmsconsistency_checks where 1 = 2");
                try {
                    query.list();
                    result = true;
                    log.info("TmsConsistency-checks table found!");
                } catch (Exception e) {
                    result = false;
                    //log.error("Transformations table not found", e);
                    log.error("TmsConsistency-checks table not found");
                }
                return result;
            }
        });
        log.info("Checking if TmsConsistency-checks table exists DONE....");
        return result;
    }
    
    /**
     * Checks if TmsConsistency-checks table already exists in database
     * @return <code>true</code> if exists, <code>false</code> if does not exist.
     */
    public boolean existsTable() {
        Boolean result = false;
        log.info("Checking if TmsConsistency-checks table exists");
        result = (Boolean) getHibernateTemplate().execute(new HibernateCallback() {

            @Override
            public Object doInHibernate(Session session) throws HibernateException, SQLException {
                Boolean result = false;
                SQLQuery query = session.createSQLQuery("select * from `tmsconsistency_checks` where 1 = 2");
                try {
                    query.list();
                    result = true;
                    log.info("TmsConsistency-checks table found!");
                } catch (Exception e) {
                    result = false;
                    //log.error("TmsConsistency-checks table not found", e);
                    log.error("TmsConsistency-checks table not found");
                }
                return result;
            }
        });
        log.info("Checking if TmsConsistency-checks table exists DONE....");
        return result;
    }
    
    public void createTable(){
        log.info("Creating TmsConsistency_checks table...");
        final String sql = getQueryCreateTable();
        getHibernateTemplate().execute(new HibernateCallback() {
            @Override
            public Object doInHibernate(Session session) throws HibernateException, SQLException {
                SQLQuery query = null;
                try {
                    query = session.createSQLQuery(sql);
                    query.executeUpdate();
                } catch (Exception e) {
                    log.error("Can´t create tmsconsistency_checks table", e);
                }
                return null;
            }
        });
        log.info("Creating TmsConsistency-checks table DONE....");
    }
    
    private String getQueryCreateTable(){
        StringBuilder s = new StringBuilder();
        s.append("CREATE TABLE `tmsconsistency_checks` (");
        s.append("`implicationid` int(11) NOT NULL,");
        s.append("`logicaloperator` varchar(1) NOT NULL,");
        s.append("`traitid` int(11) NOT NULL,");
        s.append("`scaleid` int(11) NOT NULL,");
        s.append("`methodid` int(11) NOT NULL,");
        s.append("`value` varchar(255) NOT NULL,");
        s.append("`link` int(11) NOT NULL,");
        s.append("PRIMARY KEY (`IMPLICATIONID`)");
        s.append(")");
        return s.toString();
    }
}
