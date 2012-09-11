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
    
    private String createTable(){
        StringBuilder s = new StringBuilder();
        s.append("CREATE TABLE `continuous-function` (");
        s.append("`transid` INT(10) NOT NULL DEFAULT '0',");
        s.append("`function` VARCHAR(255) NULL DEFAULT NULL,");
        s.append("`funabbr` VARCHAR(255) NULL DEFAULT NULL,");
        s.append("PRIMARY KEY (`transid`)");
        s.append(")");
        return s.toString();
    }
}
