
package org.cimmyt.cril.ibwb.provider.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.cimmyt.cril.ibwb.api.dao.AbstractDAO;
import org.cimmyt.cril.ibwb.api.dao.utils.ValidatingDataType;
import org.cimmyt.cril.ibwb.domain.ImsLabelinfo;
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
public class ImsLabelInfoDAO extends AbstractDAO<ImsLabelinfo, Integer> {
    
    public ImsLabelInfoDAO() {
        super(ImsLabelinfo.class);
    }

    @Override
    public ImsLabelinfo prepareToCreate(ImsLabelinfo imsLabelinfo) {
        if (isLocal()) {
            imsLabelinfo.setId(getNextMin());
        } else {
            imsLabelinfo.setId(getNextMax());
        }
        return imsLabelinfo;
    }
    
    

    @Override
    public String getKeyProperty() {
        return "id";
    }

    @Override
    protected void buildCriteria(DetachedCriteria criteria, ImsLabelinfo filter) {
        
    }

    @Override
    public String getConsulta(ImsLabelinfo filtro) {
        return "";
    }
    
}
