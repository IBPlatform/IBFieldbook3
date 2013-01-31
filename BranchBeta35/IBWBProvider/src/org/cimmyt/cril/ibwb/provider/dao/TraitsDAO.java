package org.cimmyt.cril.ibwb.provider.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.Logger;
import org.cimmyt.cril.ibwb.api.dao.AbstractDAO;
import org.cimmyt.cril.ibwb.api.dao.utils.ValidatingDataType;
import org.cimmyt.cril.ibwb.domain.Traits;
import org.hibernate.HibernateException;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.criterion.DetachedCriteria;
import org.springframework.orm.hibernate3.HibernateCallback;

public class TraitsDAO extends AbstractDAO<Traits, Integer> {

    private static Logger log = Logger.getLogger(TraitsDAO.class);

    public TraitsDAO() {
        super(Traits.class);
    }

    @Override
    public Traits prepareToCreate(Traits traits) {
        if (isLocal()) {
            //TODO: Ask if tid and traitid should have same value
            traits.setTid(getNextMin());
            traits.setTraitid(getNextMin());
        }
        if (isCentral()) {
            //TODO: Ask if tid and traitid should have same value            
            traits.setTid(getNextMax());
            traits.setTraitid(getNextMax());
        }
        return traits;
    }

    @Override
    protected void buildCriteria(DetachedCriteria criteria, Traits filter) {
        // TODO Auto-generated method stub
    }

    @Override
    public String getKeyProperty() {
        return "tid";
    }

    @Override
    public String getConsulta(Traits filtro) {
        String query = "from Traits as t";

        if (filtro.getGlobalsearch() == null) {
            setQuery("t.tid", filtro.getTid());
            setQuery("t.traitid", filtro.getTraitid());
            setQuery("t.trname", filtro.getTrname());
            setQuery("t.trabbr", filtro.getTrabbr());
            setQuery("t.trdesc", filtro.getTrdesc());
//            setQuery("t.tmethid", filtro.getTmethid());
            setQuery("t.tnstat", filtro.getTnstat());
            setQuery("t.traitGroup", filtro.getTraitGroup());
            setQuery("t.ontology", filtro.getOntology());
//            setQuery("t.traittype", filtro.getTraittype());
        } else {
            global = true;
            if (ValidatingDataType.isNumeric(filtro.getGlobalsearch())) {
                setQuery("t.tid", Integer.valueOf(filtro.getGlobalsearch()));
                setQuery("t.traitid", Integer.valueOf(filtro.getGlobalsearch()));
                setQueryInTo("t.trname", filtro.getGlobalsearch());
                setQueryInTo("t.trabbr", filtro.getGlobalsearch());
                setQueryInTo("t.trdesc", filtro.getGlobalsearch());
//                setQuery("t.tmethid", Integer.valueOf(filtro.getGlobalsearch()));
                setQuery("t.tnstat", Integer.valueOf(filtro.getGlobalsearch()));
                setQueryInTo("t.traitGroup", filtro.getGlobalsearch());
                setQueryInTo("t.ontology", filtro.getGlobalsearch());
//                setQueryInTo("t.traittype", filtro.getGlobalsearch());
            } else {
                setQueryInTo("t.trname", filtro.getGlobalsearch());
                setQueryInTo("t.trabbr", filtro.getGlobalsearch());
                setQueryInTo("t.trdesc", filtro.getGlobalsearch());
                setQueryInTo("t.traitGroup", filtro.getGlobalsearch());
                setQueryInTo("t.ontology", filtro.getGlobalsearch());
//                setQueryInTo("t.traittype", filtro.getGlobalsearch());
            }
        }
        return query;
    }

    /**
     * Checks if Tratis, Scales and Measuredin tables already exists in database
     * @return <code>true</code> if exists, <code>false</code> if does not exist.
     */
    public boolean existsTratisTable() {
        Boolean result = false;

        log.info("Checking if Traits table exists");

        result = (Boolean) getHibernateTemplate().execute(new HibernateCallback() {

            @Override
            public Object doInHibernate(Session session) throws HibernateException, SQLException {
                Boolean result = false;
                SQLQuery query = session.createSQLQuery("select * from TMSTRAITS where 1 = 2");
                try {
                    query.list();
                    result = true;
                    log.info("Traits table found!");
                } catch (Exception e) {
                    result = false;
                    //log.error("Traits table not found", e);
                    log.error("Traits table not found");
                }
                return result;
            }
        });

        log.info("Checking if Traits table exists DONE....");
        return result;
    }

    /**
     * Create TraTratis, Scales and Measuredin
     */
    public void createTraitsTables() {
        log.info("Creating traits, scales and measuredin tables....");
        final StringBuilder createTraitsTable = new StringBuilder();

        createTraitsTable.append("CREATE  TABLE TMSTRAITS (tid INTEGER  ,traitid INTEGER NOT NULL , ");
        createTraitsTable.append(" trname VARCHAR(50)  ,trabbr VARCHAR(8)  , trdesc VARCHAR(255)  ,");
        createTraitsTable.append(" tnstat INTEGER  ,traitgroup VARCHAR(50)  ,");
        createTraitsTable.append(" ontology VARCHAR(50), traittype CHAR(1)   );");


        final StringBuilder migrateTraitsTable = new StringBuilder();
        migrateTraitsTable.append("INSERT INTO TMSTRAITS (tid, traitid, trname, trabbr, trdesc, tnstat, traitgroup, ontology ) ");
        migrateTraitsTable.append(" select trait.tid as tid,trait.traitid as traitid,trait.trname as trname,trait.trabbr as trabbr, ");
        migrateTraitsTable.append(" trait.trdesc as trdesc,trait.tnstat as tnstat,trait.TraitGroup as traitgroup, ");
        migrateTraitsTable.append(" trait.Ontology as ontology from trait");
        //where scaleid <> 0; ");

        final StringBuilder createScalesTable = new StringBuilder();
        createScalesTable.append("CREATE  TABLE  TMSSCALES (scaleid INTEGER NOT NULL , scname VARCHAR(50) NULL , ");
        createScalesTable.append(" sctype VARCHAR(1) NULL, ontology VARCHAR(50) NULL, dtype VARCHAR(1) NULL ); ");

        final StringBuilder migrateScalesTable = new StringBuilder();
        migrateScalesTable.append("INSERT INTO TMSSCALES (scaleid, scname, sctype, ontology, dtype) ");
        migrateScalesTable.append(" select scale.scaleid as scaleid, scale.scname as scname, scale.sctype as sctype, ");
        migrateScalesTable.append(" '---' as ontology, scale.sctype  FROM scale; ");

        final StringBuilder createMeasuredInTable = new StringBuilder();
        createMeasuredInTable.append("CREATE  TABLE TMSMEASUREDIN ( measuredinid INTEGER NOT NULL , traitid INTEGER NOT NULL , ");
        createMeasuredInTable.append(" scaleid INTEGER NOT NULL ,standardscale VARCHAR(50) NULL , ");
        createMeasuredInTable.append(" report VARCHAR(50) NULL , formula VARCHAR(50) NULL , ");
        createMeasuredInTable.append(" tmethid INTEGER NOT NULL ); ");

//        final StringBuilder fillMeasuredInTable = new StringBuilder();
//        fillMeasuredInTable.append("INSERT INTO TMSMEASUREDIN ( scaleid, traitid, standardscale, report, formula, tms_method_tmethid) ");
//        fillMeasuredInTable.append(" select scale.scaleid as scaleid, trait.traitid as traitid, '' as standardscale, ");
//        fillMeasuredInTable.append(" '' as report, '' as formula, trait.tmethid as tms_method_tmethid FROM scale INNER JOIN trait on (scale.traitid = trait.traitid); ");

        final StringBuilder createTmsMethodTable = new StringBuilder();
        createTmsMethodTable.append("CREATE  TABLE TMSMETHOD ( tmethid INTEGER NOT NULL , tmname VARCHAR(50) NOT NULL , ");
        createTmsMethodTable.append(" tmabbr VARCHAR(6)  , tmdesc VARCHAR(255)  ); ");

        final StringBuilder migrateTmsMethodTable = new StringBuilder();
        migrateTmsMethodTable.append("INSERT INTO TMSMETHOD (tmethid, tmname, tmabbr, tmdesc) ");
        migrateTmsMethodTable.append(" select tmethod.tmethid as tmethid, tmethod.tmname as tmname, "
                + " tmethod.tmabbr as tmabbr, tmethod.tmdesc as tmdesc "
                + " FROM tmethod; ");

        final StringBuilder createTmsScaleCon = new StringBuilder();
        createTmsScaleCon.append(" CREATE TABLE tmsscalecon (  tmsscaleconid int  NOT NULL, measuredinid int NOT NULL, ");
        createTmsScaleCon.append(" slevel double NOT NULL, elevel double NOT NULL); ");


        final StringBuilder migrateTmsScaleCon = new StringBuilder();
        migrateTmsScaleCon.append("");

        final StringBuilder createTmsScaleDis = new StringBuilder();
        createTmsScaleDis.append(" CREATE TABLE tmsscaledis (tmsscaledisid int  NOT NULL, measuredinid int NOT NULL, ");
        createTmsScaleDis.append(" valuename varchar(20), valdesc varchar(255)); ");


        final StringBuilder migrateTmsScaleDis = new StringBuilder();
        migrateTmsScaleDis.append("");

        getHibernateTemplate().execute(new HibernateCallback() {

            @Override
            public Object doInHibernate(Session session) throws HibernateException, SQLException {

                SQLQuery query = null;
                try {
                    log.info("Creating Traits table!");
                    query = session.createSQLQuery(createTraitsTable.toString());
                    query.executeUpdate();
                    log.info("Creating Traits table DONE...!");

                    log.info("Creating Scales table!");
                    query = session.createSQLQuery(createScalesTable.toString());
                    query.executeUpdate();
                    log.info("Creating Scales table DONE...!");

                    log.info("Creating TmsMethod table!");
                    query = session.createSQLQuery(createTmsMethodTable.toString());
                    query.executeUpdate();
                    log.info("Creating  table DONE...!");

                    log.info("Creating MeasuredIn table!");
                    query = session.createSQLQuery(createMeasuredInTable.toString());
                    query.executeUpdate();
                    log.info("Creating  table DONE...!");

                    log.info("Migrating Traits table!");
                    query = session.createSQLQuery(migrateTraitsTable.toString());
                    query.executeUpdate();
                    log.info("Migrating Traits table DONE!...");

                    log.info("Migrating TmsMethod table!");
                    query = session.createSQLQuery(migrateTmsMethodTable.toString());
                    query.executeUpdate();
                    log.info("Migrating TmsMethod table DONE!...");
//                    log.info("Migrating Scales table!");
//                    query = session.createSQLQuery(migrateScalesTable.toString());
//                    query.executeUpdate();
//                    log.info("Migrating Scales table DONE!...");
//
//                    log.info("Filling MeasuredIn table!");
//                    query = session.createSQLQuery(fillMeasuredInTable.toString());
//                    query.executeUpdate();
//                    log.info("Filling MeasuredIn table DONE...!");

                    log.info("Creating TMSScaleCon table!");
                    query = session.createSQLQuery(createTmsScaleCon.toString());
                    query.executeUpdate();
                    log.info("Creating TMSScaleCon table DONE!.");


                    log.info("Creating TMSScaleDis table!");
                    query = session.createSQLQuery(createTmsScaleDis.toString());
                    query.executeUpdate();
                    log.info("Creating TMSScaleDis table DONE!.");
                } catch (Exception e) {
                    log.error("Traits table not found", e);
                }
                return null;
            }
        });
        log.info("Creating traits, scales and measuredin tables DONE....");
    }

    @SuppressWarnings("unchecked")
    public List<String> getTraitGroups() {
        List<String> traitGroups = new ArrayList<String>();

        traitGroups = getHibernateTemplate().executeFind(new HibernateCallback() {

            @Override
            public Object doInHibernate(Session sesion) throws HibernateException, SQLException {
                List<String> traitGroups = new ArrayList<String>();
                String queryString = "SELECT DISTINCT TRAITGROUP FROM TMSTRAITS ";

                SQLQuery sQLQuery = sesion.createSQLQuery(queryString);
                for (Object object : sQLQuery.list()) {
                    String group = (String) object;
                    traitGroups.add(group);
                }

                return traitGroups;
            }
        });

        return traitGroups;
    }

    /**
     * Get a trait by traitid
     * @param traitid
     * @return Traits
     */
    public Traits getTraitByTraitid(final Integer traitid) {
        Traits trait = null;
        String queryString = "from Traits as traits where traits.traitid = ? ";
        List<Traits> traitsList = getHibernateTemplate().find(queryString, traitid);

        if (!traitsList.isEmpty()) {
            trait = traitsList.get(0);
        }
        return trait;
    }

    /**
     * Return a traits by Trname
     * @param traits
     * @return Traits
     */
    public Traits getTraitsByTrname(Traits traits) {
        StringBuilder sbHql = new StringBuilder();
        Object[] parametros = {traits.getTrname()};
        sbHql.append("FROM Traits as t WHERE t.trname = ? ORDER BY t.traitid ");
        if (isLocal()) {
            sbHql.append("DESC");
        } else {
            sbHql.append("ASC");
        }
   //     log.info(sbHql.toString());
        List<Traits> listTraitses = getHibernateTemplate().find(sbHql.toString(), parametros);
        if (listTraitses.size() > 0) {
            traits = listTraitses.get(0);
        } else {
            traits = null;
        }
        return traits;
    }
}