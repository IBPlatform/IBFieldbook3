package org.cimmyt.cril.ibwb.provider.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.cimmyt.cril.ibwb.api.dao.AbstractDAO;
import org.cimmyt.cril.ibwb.api.dao.utils.ValidatingDataType;
import org.cimmyt.cril.ibwb.domain.ImsLabelOtherinfo;
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
public class ImsLabelOtherInfoDAO extends AbstractDAO<ImsLabelOtherinfo, Integer> {

    public ImsLabelOtherInfoDAO() {
        super(ImsLabelOtherinfo.class);
    }

    @Override
    public ImsLabelOtherinfo prepareToCreate(ImsLabelOtherinfo imsLabelOtherinfo) {
        if (isLocal()) {
            imsLabelOtherinfo.setId(getNextMin());
        } else {
            imsLabelOtherinfo.setId(getNextMax());
        }
        return imsLabelOtherinfo;
    }

    @Override
    public String getKeyProperty() {
        return "id";
    }

    @Override
    protected void buildCriteria(DetachedCriteria criteria, ImsLabelOtherinfo filter) {
    }

    @Override
    public String getConsulta(ImsLabelOtherinfo filtro) {
        return "";
    }
}
