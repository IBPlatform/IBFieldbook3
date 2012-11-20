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
import org.cimmyt.cril.ibwb.domain.DiscreteConversion;
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
public class DiscreteConversionDAO extends AbstractDAO<DiscreteConversion, Integer> {

    private static Logger log = Logger.getLogger(DiscreteConversionDAO.class);

    public DiscreteConversionDAO() {
        super(DiscreteConversion.class);
    }

    @Override
    public DiscreteConversion prepareToCreate(DiscreteConversion discreteConversion) {
        if (isLocal()) {
            //TODO: Ask if tid and traitid should have same value
            discreteConversion.setTransid(getNextMin());
        }
        if (isCentral()) {
            //TODO: Ask if tid and traitid should have same value            
            discreteConversion.setTransid(getNextMax());
        }
        return discreteConversion;
    }

    @Override
    public String getKeyProperty() {
        return "transid";
    }

    @Override
    protected void buildCriteria(DetachedCriteria criteria, DiscreteConversion filter) {
        // TODO Auto-generated method stub
    }

    @Override
    public String getConsulta(DiscreteConversion filtro) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    /**
     * Checks if DiscreteConversion table already exists in database
     * @return <code>true</code> if exists, <code>false</code> if does not exist.
     */
    public boolean existsTable() {
        Boolean result = false;
        log.info("Checking if TmsDiscrete-conversion table exists");
        result = (Boolean) getHibernateTemplate().execute(new HibernateCallback() {

            @Override
            public Object doInHibernate(Session session) throws HibernateException, SQLException {
                Boolean result = false;
                SQLQuery query = session.createSQLQuery("select * from `TmsDiscrete_conversion` where 1 = 2");
                try {
                    query.list();
                    result = true;
                    log.info("TmsDiscrete-conversion table found!");
                } catch (Exception e) {
                    result = false;
                    //log.error("DiscreteConversion table not found", e);
                    log.error("TmsDiscrete-conversion table not found");
                }
                return result;
            }
        });
        log.info("Checking if TmsDiscrete-conversion table exists DONE....");
        return result;
    }
    
    public void createTable(){
        log.info("Creating TmsDiscrete-conversion table...");
        final String sql = getQueryCreateTable();
        getHibernateTemplate().execute(new HibernateCallback() {
            @Override
            public Object doInHibernate(Session session) throws HibernateException, SQLException {
                SQLQuery query = null;
                try {
                    query = session.createSQLQuery(sql);
                    query.executeUpdate();
                } catch (Exception e) {
                    log.error("Can´t create TmsDiscrete-conversion table", e);
                }
                return null;
            }
        });
        log.info("Creating TmsDiscrete-conversion table DONE....");
    }
    
    private String getQueryCreateTable(){
        StringBuilder s = new StringBuilder();
        s.append("CREATE TABLE `TmsDiscrete_conversion` (");
        s.append("`transid` INT(10) NOT NULL DEFAULT '0',");
        s.append("`value1` DOUBLE NULL DEFAULT NULL,");
        s.append("`value2` DOUBLE NULL DEFAULT NULL,");
        s.append("PRIMARY KEY (`transid`)");
        s.append(")");
        return s.toString();
    }
}
