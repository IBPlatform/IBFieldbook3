package org.cimmyt.cril.ibwb.provider.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.cimmyt.cril.ibwb.api.dao.AbstractDAO;
import org.cimmyt.cril.ibwb.api.dao.utils.ValidatingDataType;
import org.cimmyt.cril.ibwb.domain.ImsLot;
import org.cimmyt.cril.ibwb.domain.Users;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.DetachedCriteria;
import org.springframework.orm.hibernate3.HibernateCallback;

/**
 *
 * @author TMSANCHEZ
 */
public class ImsLotDAO extends AbstractDAO<ImsLot, Integer> {

    public ImsLotDAO() {
        super(ImsLot.class);
    }

    @Override
    public ImsLot prepareToCreate(ImsLot imsLot) {
        if (isLocal()) {
            imsLot.setLotid(getNextMin());
        } else {

            imsLot.setLotid(getNextMax());
        }
        return imsLot;
    }

    @Override
    public String getKeyProperty() {
        return "lotid";
    }

    @Override
    protected void buildCriteria(DetachedCriteria criteria, ImsLot filter) {
    }

    @Override
    public String getConsulta(ImsLot filtro) {
        return "";
    }
}
