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
    
}
