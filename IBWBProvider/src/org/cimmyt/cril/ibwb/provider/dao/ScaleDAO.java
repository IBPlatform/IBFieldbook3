package org.cimmyt.cril.ibwb.provider.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import javax.sql.DataSource;
import org.apache.log4j.Logger;
import org.cimmyt.cril.ibwb.api.dao.AbstractDAO;
import org.cimmyt.cril.ibwb.api.dao.utils.ValidatingDataType;
import org.cimmyt.cril.ibwb.domain.Scale;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.criterion.DetachedCriteria;
import org.springframework.orm.hibernate3.HibernateCallback;

public class ScaleDAO extends AbstractDAO<Scale, Integer> {

    private static Logger log = Logger.getLogger(ScaleDAO.class);

    public ScaleDAO() {
        super(Scale.class);
    }

    @Override
    public Scale prepareToCreate(Scale scale) {
        if (isLocal()) {
            scale.setScaleid(getNextMin());
        }
        if (isCentral()) {
            scale.setScaleid(getNextMax());
        }
        return scale;
    }

    @Override
    protected void buildCriteria(DetachedCriteria criteria, Scale filter) {
        // TODO Auto-generated method stub
    }

    @Override
    public String getKeyProperty() {
        return "scaleid";
    }

    @Override
    public String getConsulta(Scale filtro) {
        String query = "from Scale as s";

        if (filtro.getGlobalsearch() == null) {
            setQuery("s.scaleid", filtro.getScaleid());
            setQuery("s.scname", filtro.getScname());
            setQuery("s.traitid", filtro.getTraitid());
            setQuery("s.sctype", filtro.getSctype());
        } else {
            global = true;
            if (ValidatingDataType.isNumeric(filtro.getGlobalsearch())) {
                setQuery("s.scaleid", Integer.valueOf(filtro.getGlobalsearch()));
                setQueryInTo("s.scname", filtro.getGlobalsearch());
                setQuery("s.traitid", Integer.valueOf(filtro.getGlobalsearch()));
                setQueryInTo("s.sctype", filtro.getGlobalsearch());
            } else {
                setQueryInTo("s.scname", filtro.getGlobalsearch());
                setQueryInTo("s.sctype", filtro.getGlobalsearch());
            }
        }
        return query;
    }

    public List<Scale> getScaleGroups() {
        List<Scale> resultLst = new ArrayList<Scale>();
        StringBuilder queryString = new StringBuilder("select s from Scale as s ");
        
        
        List<Scale> resultLstTemp = getHibernateTemplate().find(queryString.toString());
        HashMap<String,Scale> diffScales = new HashMap<String, Scale>();
        for (Scale scale:resultLstTemp) {
            diffScales.put(scale.getScname()+scale.getSctype(), scale);
        }
        
        for (Scale scale: diffScales.values()) {
            resultLst.add(scale);
        }
        /*
        queryString.append(" where s.scname+s.sctype in (select distinct sc.scname+sc.sctype from Scale as sc)");
        //queryString.append(" group by s.scname, s.sctype ");
        //StringBuilder queryString = new StringBuilder("select distinct s.scname, s.sctype from Scale as s ");
        //queryString.append(" group by s.scname, s.sctype ");

        final String qString = queryString.toString();

        PreparedStatement ps = null;
        ResultSet rs = null;
        //* 
        try {
            //Session session = getHibernateTemplate().getSessionFactory().openSession();
            //PreparedStatement ps = session.connection().prepareStatement(qString);
            ps = dataSource.getConnection().prepareStatement(qString);
            rs = ps.executeQuery();
            while (rs.next()) {
                Scale scale = new Scale();
                scale.setScaleid(rs.getInt("SCALEID"));
                scale.setScname(rs.getString("SCNAME"));
                scale.setTraitid(rs.getInt("TRAITID"));
                scale.setSctype(rs.getString("SCTYPE"));

                resultLst.add(scale);

            }
            //session.close();
            rs.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (ps != null) {
                try {
                    ps.close();
                } catch (Exception ex1) {
                    ex1.printStackTrace();
                }
            }

        }
        //*/

        /*
        resultLst = getHibernateTemplate().executeFind(
        new HibernateCallback() {
        
        @Override
        public Object doInHibernate(Session session)
        throws HibernateException, SQLException {
        
        List<Scale> resultList = new ArrayList<Scale>();
        
        SQLQuery query = session.createSQLQuery(qString);
        try {
        resultList = query.list();
        } catch (Exception e) {
        }
        return resultList;
        }
        });
        / / */
        return resultLst;
    }
    
    public Integer getTotalDiferentes(){
        String sqlNat = "select * from scale as s GROUP BY s.SCNAME, s.SCTYPE";
        List resultado = executeQueryCustomListOfGSqlNat(sqlNat);
        if(resultado == null){
            return 0;
        }else{
            return resultado.size();
        }
    }
    
    public void migrateScalesDirect(){
        StringBuilder migrateScalesTable = new StringBuilder();
        migrateScalesTable.append("INSERT INTO TMSSCALES (scaleid, scname, sctype, ontology, dtype) ");
        migrateScalesTable.append(" select scale.scaleid as scaleid, scale.scname as scname, scale.sctype as sctype, ");
        migrateScalesTable.append(" '---' as ontology, sctype  FROM scale; ");
        try{
            int resultado = executeUpdateCustomOfGSqlNat(migrateScalesTable.toString());
        }catch(Exception e){
            log.info("ERRROR To migrate scale to TMSSCALES");
            e.printStackTrace();
        }
        
    }
    
    /**
     * Returns all scales
     * @return List<Scale>
     */
    public List<Scale> getScaleAll(){
        StringBuilder sbHql = new StringBuilder();
        sbHql.append("FROM Scale ORDER BY scaleid ");
        if(isLocal()){
            sbHql.append("DESC");
        }else{
            sbHql.append("ASC");
        }
    //    log.info(sbHql.toString());
        List<Scale> scales = getHibernateTemplate().find(sbHql.toString());
        return scales;
    }
}