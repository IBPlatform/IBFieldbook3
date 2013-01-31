package org.cimmyt.cril.ibwb.provider.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.Logger;
import org.cimmyt.cril.ibwb.api.dao.AbstractDAO;
import org.cimmyt.cril.ibwb.api.dao.utils.Order;
import org.cimmyt.cril.ibwb.api.dao.utils.ValidatingDataType;
import org.cimmyt.cril.ibwb.domain.LevelC;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.DetachedCriteria;
import org.springframework.orm.hibernate3.HibernateCallback;

public class LevelCDAO extends AbstractDAO<LevelC, Integer> {

    private static Logger log = Logger.getLogger(LevelCDAO.class);

    public LevelCDAO() {
        super(LevelC.class);
    }

    @Override
    protected void buildCriteria(DetachedCriteria criteria, LevelC filter) {
        // TODO Auto-generated method stub
    }

    @Override
    public String getKeyProperty() {
        return "locationid";
    }

    @Override
    public String getConsulta(LevelC filtro) {
    	String query = "from LevelC as l";
        
        if(filtro.getGlobalsearch() == null){
            if(filtro.getLevelCPK()!= null){
	        setQuery("l.levelCPK.labelid", filtro.getLevelCPK().getLabelid());
	        setQuery("l.levelCPK.levelno", filtro.getLevelCPK().getLevelno());
            }
            setQuery("l.factorid", filtro.getFactorid());
            setQuery("l.lvalue", filtro.getLvalue());
        }else{
        	global = true;
	        if(ValidatingDataType.isNumeric(filtro.getGlobalsearch())){
	        	setQuery("l.levelCPK.labelid", Integer.valueOf(filtro.getGlobalsearch()));
		        setQuery("l.levelCPK.levelno", Integer.valueOf(filtro.getGlobalsearch()));
		        setQuery("l.factorid", Integer.valueOf(filtro.getGlobalsearch()));
		        setQueryInTo("l.lvalue", filtro.getGlobalsearch());
	        }else{
	        	setQueryInTo("l.lvalue", filtro.getGlobalsearch());
	        }
        }
        if(isLocal()){
            setOrder("l.levelCPK.labelid", Order.DESCENDENTE);
            setOrder("l.levelCPK.levelno", Order.DESCENDENTE);
        }else{
            setOrder("l.levelCPK.labelid", Order.ASCENDENTE);
            setOrder("l.levelCPK.levelno", Order.ASCENDENTE);
        }
        return query;
    }
    
    /**
     * Returns all LevelsC by labelid
     * @return List<LevelC>
     */
    public List<LevelC> getLevelsCByLabelid(Integer labelid){
        StringBuilder sbHql = new StringBuilder();
        sbHql.append("FROM LevelC as l where l.levelCPK.labelid = ");
        sbHql.append(labelid);
        sbHql.append(" ORDER BY l.levelCPK.levelno ");
        if(isLocal()){
            sbHql.append("DESC");
        }else{
            sbHql.append("ASC");
        }
    //    log.info(sbHql.toString());
        List<LevelC> levelsC = getHibernateTemplate().find(sbHql.toString());
        return levelsC;
    }
}