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
import org.cimmyt.cril.ibwb.domain.ContinuousConversion;
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
public class ContinuousConversionDAO extends AbstractDAO<ContinuousConversion, Integer> {

    private static Logger log = Logger.getLogger(ContinuousConversionDAO.class);

    public ContinuousConversionDAO() {
        super(ContinuousConversion.class);
    }

    @Override
    public ContinuousConversion prepareToCreate(ContinuousConversion continuousConversion) {
        if (isLocal()) {
            //TODO: Ask if tid and traitid should have same value
            continuousConversion.setTransid(getNextMin());
        }
        if (isCentral()) {
            //TODO: Ask if tid and traitid should have same value            
            continuousConversion.setTransid(getNextMax());
        }
        return continuousConversion;
    }

    @Override
    public String getKeyProperty() {
        return "transid";
    }

    @Override
    protected void buildCriteria(DetachedCriteria criteria, ContinuousConversion filter) {
        // TODO Auto-generated method stub
    }

    @Override
    public String getConsulta(ContinuousConversion filtro) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
    private String createTable(){
        StringBuilder s = new StringBuilder();
        s.append("CREATE TABLE `continuous-conversion` (");
        s.append("`transid` INT(10) NOT NULL DEFAULT '0',");
        s.append("`operator` DOUBLE NULL DEFAULT NULL,");
        s.append("`factor` DOUBLE NULL DEFAULT NULL,");
        s.append("PRIMARY KEY (`transid`)");
        s.append(")");
        return s.toString();
    }
}
