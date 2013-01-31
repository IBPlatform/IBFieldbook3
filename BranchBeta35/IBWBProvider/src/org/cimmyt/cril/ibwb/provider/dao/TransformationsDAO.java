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
import org.cimmyt.cril.ibwb.domain.Transformations;
import org.hibernate.HibernateException;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.criterion.DetachedCriteria;
import org.springframework.orm.hibernate3.HibernateCallback;

/**
 *
 * @author MasterGama
 */
public class TransformationsDAO extends AbstractDAO<Transformations, Integer> {

    private static Logger log = Logger.getLogger(TransformationsDAO.class);

    public TransformationsDAO() {
        super(Transformations.class);
    }

    @Override
    public Transformations prepareToCreate(Transformations transformations) {
        if (isLocal()) {
            //TODO: Ask if tid and traitid should have same value
            transformations.setTransid(getNextMin());
        }
        if (isCentral()) {
            //TODO: Ask if tid and traitid should have same value            
            transformations.setTransid(getNextMax());
        }
        return transformations;
    }

    @Override
    public String getKeyProperty() {
        return "transid";
    }

    @Override
    protected void buildCriteria(DetachedCriteria criteria, Transformations filter) {
        // TODO Auto-generated method stub
    }

    @Override
    public String getConsulta(Transformations filtro) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    /**
     * Checks if Transformations table already exists in database
     * @return <code>true</code> if exists, <code>false</code> if does not exist.
     */
    public boolean existsOldTable() {
        Boolean result = false;
        log.info("Checking if Transformations table exists");
        result = (Boolean) getHibernateTemplate().execute(new HibernateCallback() {

            @Override
            public Object doInHibernate(Session session) throws HibernateException, SQLException {
                Boolean result = false;
                SQLQuery query = session.createSQLQuery("select * from transformations where 1 = 2");
                try {
                    query.list();
                    result = true;
                    log.info("Transformations table found!");
                } catch (Exception e) {
                    result = false;
                    //log.error("Transformations table not found", e);
                    log.error("Transformations table not found");
                }
                return result;
            }
        });
        log.info("Checking if Transformations table exists DONE....");
        return result;
    }
    
    /**
     * Checks if TmsTransformations table already exists in database
     * @return <code>true</code> if exists, <code>false</code> if does not exist.
     */
    public boolean existsTable() {
        Boolean result = false;
        log.info("Checking if TmsTransformations table exists");
        result = (Boolean) getHibernateTemplate().execute(new HibernateCallback() {

            @Override
            public Object doInHibernate(Session session) throws HibernateException, SQLException {
                Boolean result = false;
                SQLQuery query = session.createSQLQuery("select * from TmsTransformations where 1 = 2");
                try {
                    query.list();
                    result = true;
                    log.info("TmsTransformations table found!");
                } catch (Exception e) {
                    result = false;
                    //log.error("Transformations table not found", e);
                    log.error("TmsTransformations table not found");
                }
                return result;
            }
        });
        log.info("Checking if TmsTransformations table exists DONE....");
        return result;
    }
    
    public void createTable(){
        log.info("Creating TmsTransformations table...");
        final String sql = getQueryCreateTable();
        getHibernateTemplate().execute(new HibernateCallback() {
            @Override
            public Object doInHibernate(Session session) throws HibernateException, SQLException {
                SQLQuery query = null;
                try {
                    query = session.createSQLQuery(sql);
                    query.executeUpdate();
                } catch (Exception e) {
                    log.error("Can´t create TmsTransformations table", e);
                }
                return null;
            }
        });
        log.info("Creating Transformations table DONE....");
    }
    
    private String getQueryCreateTable(){
        StringBuilder s = new StringBuilder();
        s.append("CREATE TABLE `TmsTransformations` (");
        s.append("`transid` INT(10) NOT NULL DEFAULT '0',");
        s.append("`fromscaleid` INT(10) NULL DEFAULT NULL,");
        s.append("`toscaleid` INT(10) NULL DEFAULT NULL,");
        s.append("`transtype` VARCHAR(1) NULL DEFAULT NULL,");
        s.append("PRIMARY KEY (`transid`)");
        s.append(")");
        return s.toString();
    }
}
