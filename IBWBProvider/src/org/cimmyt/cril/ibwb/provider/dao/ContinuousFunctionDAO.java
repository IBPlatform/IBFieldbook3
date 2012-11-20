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
import org.cimmyt.cril.ibwb.domain.ContinuousFunction;
import org.cimmyt.cril.ibwb.domain.Traits;
import org.hibernate.HibernateException;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.criterion.DetachedCriteria;
import org.springframework.orm.hibernate3.HibernateCallback;

/**
 *
 * @author MasterGama
 */
public class ContinuousFunctionDAO extends AbstractDAO<ContinuousFunction, Integer> {

    private static Logger log = Logger.getLogger(ContinuousFunctionDAO.class);

    public ContinuousFunctionDAO() {
        super(ContinuousFunction.class);
    }

    @Override
    public ContinuousFunction prepareToCreate(ContinuousFunction continuousFunction) {
        if (isLocal()) {
            //TODO: Ask if tid and traitid should have same value
            continuousFunction.setTransid(getNextMin());
        }
        if (isCentral()) {
            //TODO: Ask if tid and traitid should have same value            
            continuousFunction.setTransid(getNextMax());
        }
        return continuousFunction;
    }

    @Override
    public String getKeyProperty() {
        return "transid";
    }

    @Override
    protected void buildCriteria(DetachedCriteria criteria, ContinuousFunction filter) {
        // TODO Auto-generated method stub
    }

    @Override
    public String getConsulta(ContinuousFunction filtro) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    /**
     * Checks if ContinuousFunction table already exists in database
     * @return <code>true</code> if exists, <code>false</code> if does not exist.
     */
    public boolean existsTable() {
        Boolean result = false;
        log.info("Checking if TmsContinuous-function table exists");
        result = (Boolean) getHibernateTemplate().execute(new HibernateCallback() {

            @Override
            public Object doInHibernate(Session session) throws HibernateException, SQLException {
                Boolean result = false;
                SQLQuery query = session.createSQLQuery("select * from `TmsContinuous_function` where 1 = 2");
                try {
                    query.list();
                    result = true;
                    log.info("TmsContinuous-function table found!");
                } catch (Exception e) {
                    result = false;
                    //log.error("ContinuousFunction table not found", e);
                    log.error("TmsContinuous-function table not found");
                }
                return result;
            }
        });
        log.info("Checking if TmsContinuous-function table exists DONE....");
        return result;
    }
    
    public void createTable(){
        log.info("Creating TmsContinuous-function table...");
        final String sql = getQueryCreateTable();
        getHibernateTemplate().execute(new HibernateCallback() {
            @Override
            public Object doInHibernate(Session session) throws HibernateException, SQLException {
                SQLQuery query = null;
                try {
                    query = session.createSQLQuery(sql);
                    query.executeUpdate();
                } catch (Exception e) {
                    log.error("Can´t create TmsContinuous-function table", e);
                }
                return null;
            }
        });
        log.info("Creating TmsContinuous-function table DONE....");
    }
    
    private String getQueryCreateTable(){
        StringBuilder s = new StringBuilder();
        s.append("CREATE TABLE `TmsContinuous_function` (");
        s.append("`transid` INT(10) NOT NULL DEFAULT '0',");
        s.append("`function` VARCHAR(255) NULL DEFAULT NULL,");
        s.append("`funabbr` VARCHAR(255) NULL DEFAULT NULL,");
        s.append("PRIMARY KEY (`transid`)");
        s.append(")");
        return s.toString();
    }
}
