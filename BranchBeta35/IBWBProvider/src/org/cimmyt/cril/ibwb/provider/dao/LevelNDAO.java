package org.cimmyt.cril.ibwb.provider.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.Logger;
import org.cimmyt.cril.ibwb.api.dao.AbstractDAO;
import org.cimmyt.cril.ibwb.api.dao.utils.Order;
import org.cimmyt.cril.ibwb.api.dao.utils.ValidatingDataType;
import org.cimmyt.cril.ibwb.domain.LevelN;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.DetachedCriteria;
import org.springframework.orm.hibernate3.HibernateCallback;

public class LevelNDAO extends AbstractDAO<LevelN, Integer> {

    private static Logger log = Logger.getLogger(LevelNDAO.class);

    public LevelNDAO() {
        super(LevelN.class);
    }

    @Override
    protected void buildCriteria(DetachedCriteria criteria, LevelN filter) {
        // TODO Auto-generated method stub
    }

    @Override
    public String getKeyProperty() {
        return "locationid";
    }

    @Override
    public String getConsulta(LevelN filtro) {
        String query = "from LevelN as l";

        if (filtro.getGlobalsearch() == null) {
            if (filtro.getLevelNPK() != null) {
                setQuery("l.levelNPK.labelid", filtro.getLevelNPK().getLabelid());
                setQuery("l.levelNPK.levelno", filtro.getLevelNPK().getLevelno());
            }
            setQuery("l.factorid", filtro.getFactorid());
            setQuery("l.lvalue", filtro.getLvalue());
        } else {
            global = true;
            if (ValidatingDataType.isNumeric(filtro.getGlobalsearch())) {
                setQuery("l.levelNPK.labelid", Integer.valueOf(filtro.getLevelNPK().getLabelid()));
                setQuery("l.levelNPK.levelno", Integer.valueOf(filtro.getLevelNPK().getLevelno()));
                setQuery("l.factorid", Integer.valueOf(filtro.getFactorid()));
                setQuery("l.lvalue", Double.valueOf(filtro.getLvalue()));
            } else {
            }
        }
        if (isLocal()) {
            setOrder("l.levelNPK.labelid", Order.DESCENDENTE);
            setOrder("l.levelNPK.levelno", Order.DESCENDENTE);
        } else {
            setOrder("l.levelNPK.labelid", Order.ASCENDENTE);
            setOrder("l.levelNPK.levelno", Order.ASCENDENTE);
        }
        return query;
    }
    
    /**
     * Returns all LevelsN by labelid
     * @return List<LevelN>
     */
    public List<LevelN> getLevelsnByLabelid(Integer labelid){
        StringBuilder sbHql = new StringBuilder();
        sbHql.append("FROM LevelN as l where l.levelNPK.labelid = ");
        sbHql.append(labelid);
        sbHql.append(" ORDER BY l.levelNPK.levelno ");
        if(isLocal()){
            sbHql.append("DESC");
        }else{
            sbHql.append("ASC");
        }
   //     log.info(sbHql.toString());
        List<LevelN> levelsN = getHibernateTemplate().find(sbHql.toString());
        return levelsN;
    }
}