/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.cimmyt.cril.ibwb.provider.dao;

import com.sun.rowset.CachedRowSetImpl;
import ibfb.domain.core.Measurement;
import ibfb.domain.core.Workbook;
import java.math.BigInteger;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import javax.sql.rowset.RowSetMetaDataImpl;
import org.apache.log4j.Logger;
import org.cimmyt.cril.ibwb.api.dao.AbstractDAO;
import org.cimmyt.cril.ibwb.api.dao.utils.ValidatingDataType;
import org.cimmyt.cril.ibwb.domain.Study;
import org.cimmyt.cril.ibwb.domain.StudySearch;
import org.cimmyt.cril.ibwb.provider.dao.helpers.HelperGmsReader;
import org.cimmyt.cril.ibwb.provider.dao.helpers.HelperWorkbookReader;
import org.hibernate.Hibernate;
import org.hibernate.HibernateException;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.criterion.DetachedCriteria;
import org.springframework.orm.hibernate3.HibernateCallback;

/**
 *
 * @author gamaliel
 */
public class DMSReaderDAO extends AbstractDAO<Study, Integer> {
    
    private static final Logger log = Logger.getLogger(DMSReaderDAO.class);
    private static final int ltype = 1;
    private static final int fname = 0;
    private static final int represNo = 0;
    private static final int ounitid = 0;
    private static final int FNAME = 1;
    private static final int LVALUE = 2;
    private static final int LTYPE = 3;

    public DMSReaderDAO() {
        super(Study.class);
    }

    @Override
    public Study prepareToCreate(Study study) {
        if (isLocal()) {
            study.setStudyid(getNextMin());
        }
        if (isCentral()) {
            study.setStudyid(getNextMax());
        }
        return study;
    }

    @Override
    public String getKeyProperty() {
        return "studyid";
    }

    @Override
    protected void buildCriteria(DetachedCriteria criteria, Study filter) {
        // TODO Auto-generated method stub
    }

    @Override
    public String getConsulta(Study filtro) {
        String query = "from Study as s";

        if (filtro.getGlobalsearch() == null) {
            setQuery("s.studyid", filtro.getStudyid());
            setQuery("s.sname", filtro.getSname());
            setQuery("s.pmkey", filtro.getPmkey());
            setQuery("s.title", filtro.getTitle());
            setQuery("s.objectiv", filtro.getObjectiv());
            setQuery("s.investid", filtro.getInvestid());
            setQuery("s.stype", filtro.getStype());
            setQuery("s.sdate", filtro.getSdate());
            setQuery("s.edate", filtro.getEdate());
            setQuery("s.userid", filtro.getUserid());
            setQuery("s.sstatus", filtro.getSstatus());
            setQuery("s.shierarchy", filtro.getShierarchy());
        } else {
            global = true;
            if (ValidatingDataType.isNumeric(filtro.getGlobalsearch())) {
                setQuery("s.studyid", Integer.valueOf(filtro.getGlobalsearch()));
                setQueryInTo("s.sname", filtro.getGlobalsearch());
                setQuery("s.pmkey", Integer.valueOf(filtro.getGlobalsearch()));
                setQueryInTo("s.title", filtro.getGlobalsearch());
                setQueryInTo("s.objectiv", filtro.getGlobalsearch());
                setQuery("s.investid", Integer.valueOf(filtro.getGlobalsearch()));
                setQueryInTo("s.stype", filtro.getGlobalsearch());
                setQuery("s.sdate", Integer.valueOf(filtro.getGlobalsearch()));
                setQuery("s.edate", Integer.valueOf(filtro.getGlobalsearch()));
                setQuery("s.userid", Integer.valueOf(filtro.getGlobalsearch()));
                setQuery("s.sstatus", Integer.valueOf(filtro.getGlobalsearch()));
                setQuery("s.shierarchy", Integer.valueOf(filtro.getGlobalsearch()));
            } else {
                setQueryInTo("s.sname", filtro.getGlobalsearch());
                setQueryInTo("s.title", filtro.getGlobalsearch());
                setQueryInTo("s.objectiv", filtro.getGlobalsearch());
                setQueryInTo("s.stype", filtro.getGlobalsearch());
            }
        }
        return query;
    }

    /**
     *
     * @param studyId id del estudio
     * @param trialFactorId id del trial puede ser null
     * @param factoresPrincipales donde FactorId = labelId
     * @param factoresResultado donde FactorId = labelId
     * @param nombreDelTrial puede ser null
     * @return
     */
    public ResultSet getTrialRandomization(
            int studyId,
            int trialFactorId,
            List<String> factoresPrincipales,
            List<String> factoresSalida,
            String trialName) {
        log.info("Getting trial randomization");
        Integer numeroDeFactoresPrincipales = factoresPrincipales.size();
        String listaDeFactoresResultado = getFactoresParaUsoInQuery(factoresSalida);
        ResultSet pr = null;
//        ResultSet rsTemp = null;
        String consultaSQL = "SELECT represno, COUNT(*) FROM effect e "
                + "INNER JOIN factor f ON e.factorid=f.factorid "
                + "WHERE studyid=" + studyId + " AND "
                + "f.factorid = f.labelid AND "
                + "fname IN(" + getFactoresParaUsoInQuery(factoresPrincipales) + ") "
                + "GROUP BY represno HAVING COUNT(*)=" + numeroDeFactoresPrincipales;

        try {
            List resultado = executeQueryCustomListOfGSqlNat(consultaSQL);
            String orden;
            if (isLocal()) {
                orden = "DESC";
            } else if (isCentral()) {
                orden = "ASC";
            } else {
                orden = "DESC";
            }
//            if (studyId < 0) {
//                st = conLocalDMS.createStatement();
//            } else {
//                st = conCentralDMS.createStatement();
//            }
//            rsTemp = st.executeQuery(parasource1);

            int trepresNo = 0;
            if (resultado != null) {
                if (resultado.size() > 0) {
                    Object[] fila = (Object[]) resultado.get(0);
                    trepresNo = (Integer) fila[represNo]; // rsTemp.getInt("represno");
                } else {
                    return null;
                }
            } else {
                return null;
            }
//            rsTemp.close();
//            if (trepresNo == 0) {
//                return null;
//            }
            RowSetMetaDataImpl rsmd = new RowSetMetaDataImpl();
            consultaSQL = "SELECT count(*) FROM factor "
                    + "WHERE studyid=" + studyId
                    + " and fname IN(" + listaDeFactoresResultado + ")";

//            rsTemp = st.executeQuery(consultaSQL);
            int cuantosFR = 0;
//            rsTemp.next();

            Object tempObject = executeQueryCustomUniqueResultSqlNat(consultaSQL);
            if (tempObject instanceof BigInteger) {
                BigInteger temp = (BigInteger) tempObject;
                cuantosFR = temp.intValue();
            } else if (tempObject instanceof Integer) {
                Integer temp = (Integer) tempObject;
                cuantosFR = temp.intValue();
            }
//            rsTemp.close();
            consultaSQL = "SELECT fname, ltype, labelid FROM factor "
                    + "WHERE studyid=" + studyId
                    + " and fname IN(" + listaDeFactoresResultado + ")"
                    + " ORDER BY labelid " + orden;
//            rsTemp = st.executeQuery(consultaSQL);
            //resultado = executeQueryCustomListOfGSqlNat(consultaSQL);
            resultado = executeQueryRandomtNative(consultaSQL);
            rsmd.setColumnCount(cuantosFR);
            int tconsecutivo = 0;
            for (Object fila : resultado) {
                tconsecutivo += 1;
                Object[] casilla = (Object[]) fila;
                rsmd.setColumnName(tconsecutivo, (String) casilla[fname]);
                String ltypeTemp = casilla[ltype].toString();
                if (ltypeTemp.equals("N")) {
                    rsmd.setColumnType(tconsecutivo, Types.INTEGER);
                } else {
                    rsmd.setColumnType(tconsecutivo, Types.VARCHAR);
                }
            }
//            rsTemp.close();
            CachedRowSetImpl crs = new CachedRowSetImpl();
            int i889 = 0;
            crs.setMetaData(rsmd);
            String condicionWhere = "f.fname IN (" + listaDeFactoresResultado + ") AND studyid = " + studyId + " AND represno =" + trepresNo + "";
            if (trialFactorId > 0) {
                consultaSQL = "SELECT OUNITID FROM FACTOR F "
                        + "INNER JOIN (LEVEL_N L INNER JOIN OINDEX O "
                        + "ON (L.LEVELNO = O.LEVELNO) AND (L.FACTORID = O.FACTORID)) "
                        + "ON (F.FACTORID = L.FACTORID) "
                        + "AND (F.LABELID = L.LABELID) "
                        + "WHERE f.fname IN ('" + trialName + "') "
                        + "AND studyid = " + studyId
                        + " AND represno =" + trepresNo
                        + " AND lvalue = " + trialFactorId;
//                
//                rsTemp = st.executeQuery(consultaSQL);
                resultado = executeQueryCustomListOfGSqlNat(consultaSQL);
                int cuantosRegistros = 0;
                String cadOunitid = "";
//                while (rsTemp.next()) {
                if (resultado.size() == 0) {
                    return null;
                } else {
                    for (Object fila : resultado) {
                        cuantosRegistros += 1;
                        //                    cadOunitid += rsTemp.getString("ounitid") + ",";
                        cadOunitid += fila.toString() + ",";
                    }
                }
//                rsTemp.close();
//                if (cuantosRegistros == 0) {
//                    return null;
//                }
                cadOunitid = cadOunitid.substring(0, cadOunitid.length() - 1);
                condicionWhere += " and ounitid in (" + cadOunitid + ")";
            }
            consultaSQL = "SELECT O.OUNITID, FNAME, LVALUE, LTYPE, F.LABELID "
                    + "FROM FACTOR F INNER JOIN (LEVEL_N L "
                    + "INNER JOIN OINDEX O ON (L.LEVELNO = O.LEVELNO) "
                    + "AND (L.FACTORID = O.FACTORID)) "
                    + "ON (F.FACTORID = L.FACTORID) "
                    + "AND (F.LABELID = L.LABELID) "
                    + "WHERE " + condicionWhere + "";
            consultaSQL += " UNION ";
            consultaSQL += "SELECT O.OUNITID, FNAME, LVALUE, LTYPE, F.LABELID "
                    + "FROM FACTOR F INNER JOIN (LEVEL_C L "
                    + "INNER JOIN OINDEX O ON (L.LEVELNO = O.LEVELNO) "
                    + "AND (L.FACTORID = O.FACTORID)) "
                    + "ON (F.FACTORID = L.FACTORID) "
                    + "AND (F.LABELID = L.LABELID) "
                    + "WHERE " + condicionWhere + "";
            consultaSQL += " ORDER BY OUNITID " + orden + ", LABELID " + orden;

            //System.out.println(parasource1);
            resultado = executeQueryCustomListNative(consultaSQL);
//            rsTemp = st.executeQuery(consultaSQL);
            int tounitidAnt = 0;
            int tounitidActual = 0;
            String fname = "";
            int tlvalue = 0;
            for (Object fila : resultado) {
                Object[] celdas = (Object[]) fila;

                tounitidActual = (Integer) celdas[ounitid];
                if (tounitidAnt != tounitidActual) {
                    if (tounitidAnt != 0) {
                        crs.insertRow();
                    }
                    crs.moveToInsertRow();
                    for (i889 = 1; i889 <= cuantosFR; i889++) {
                        crs.updateNull(i889);
                    }
                }
                fname = (String) celdas[FNAME];
                String ltypeTemp = (String) celdas[LTYPE];
                ltypeTemp = ltypeTemp.trim().toUpperCase();
                if (ltypeTemp.equals("N")) {
                    if (celdas[2] instanceof String) {
                        String valueTemp = (String) celdas[LVALUE];
                        tlvalue = Integer.valueOf(valueTemp).intValue();
                    } else {
                        byte[] bytes = (byte[]) celdas[LVALUE];
                        String valueTemp = new String(bytes);
                        tlvalue = Integer.valueOf(valueTemp).intValue();
                    }
                    crs.updateInt(fname, tlvalue);
                } else {
                    if (celdas[2] instanceof String) {
                        crs.updateString(fname, (String) celdas[LVALUE]);
                    } else {
                        byte[] bytes = (byte[]) celdas[LVALUE];
                        String valueTemp = new String(bytes);
                        crs.updateString(fname, valueTemp);
                    }

                }
                tounitidAnt = tounitidActual;
            }
            if (tounitidAnt != 0) {
                crs.insertRow();
            }
            crs.moveToCurrentRow();
            crs.beforeFirst();
            pr = crs;
//            pr.close();
        } catch (SQLException sqle) {
            sqle.printStackTrace();
            pr = null;
        } catch (Exception e) {
            e.printStackTrace();
            pr = null;
        }
//        muestraRegistros(pr);
        log.info("Getting trial randomization.... DONE");
        return pr;
    }
    
    /**
     * This method found List of trial or occ indicated by study indicated
     * @param studyId id del estudio
     * @param trialFactorId id del trial puede ser null
     * @return resulset whit trial, entry, plot and gids
     */
    public StudySearch getListGermplasmAndPlotByStudyidAndTrial(
            final StudySearch studySearch
            ) {
        StudySearch ss = (StudySearch) getHibernateTemplate().execute(new HibernateCallback() {
            public Object doInHibernate(Session session)
                    throws HibernateException, SQLException {                
                return HelperGmsReader.getListGermplasmAndPlotByStudyidAndTrial(
                        studySearch,
                        session,
                        isLocal(),
                        isCentral()
                        );
            }
        });
        return ss;
    }
    
    
    
    /**
     * This method found List of trial or occ indicated by study indicated
     * @param studyId id del estudio
     * @param trialFactorId id del trial puede ser null
     * @return resulset whit trial, entry, plot and gids
     */
    public StudySearch getListGermplasmAndPlotByStudyidAndTrial(
            final StudySearch studySearch, 
            final List<String> factorsKey,
            final List<String> factorsReturn
            ) {
        StudySearch ss = (StudySearch) getHibernateTemplate().execute(new HibernateCallback() {
            public Object doInHibernate(Session session)
                    throws HibernateException, SQLException {                
                return HelperGmsReader.getListGermplasmAndPlotByStudyidAndTrial(
                        studySearch,
                        factorsKey,
                        factorsReturn,
                        session,
                        isLocal(),
                        isCentral()
                        );
            }
        });
        return ss;
    }
    
    /**
     *
     * @param studyId id del estudio
     * @param trialFactorId id del trial puede ser null
     * @param factoresPrincipales donde FactorId = labelId
     * @param factoresResultado donde FactorId = labelId
     * @param nombreDelTrial puede ser null
     * @return
     */
    public ResultSet getTrialRandomizationFast(
            final int studyId,
            final int trialFactorId,
            final List<String> factoresPrincipales,
            final List<String> factoresSalida,
            final String trialName) {
        
        ResultSet rs = (ResultSet) getHibernateTemplate().execute(new HibernateCallback() {

            public Object doInHibernate(Session session)
                    throws HibernateException, SQLException {
                
                return HelperWorkbookReader.getTrialRandomizationFast(
                        studyId,
                        trialFactorId,
                        factoresPrincipales,
                        factoresSalida,
                        trialName,
                        session,
                        isLocal(),
                        isCentral()
                        );
            }
        });
        return rs;
    }
    
    public List<Measurement> getTrialRandomizationVeryFast(
            final int studyId,
            final int trialFactorId,
            final List<String> factoresPrincipales,
            final List<String> factoresSalida,
            final String trialName) {
        
        List<Measurement> rs = (List<Measurement>) getHibernateTemplate().executeFind(new HibernateCallback() {

            public Object doInHibernate(Session session)
                    throws HibernateException, SQLException {
                
                return HelperWorkbookReader.getTrialRandomizationVeryFast(
                        studyId,
                        trialFactorId,
                        factoresPrincipales,
                        factoresSalida,
                        trialName,
                        session,
                        isLocal(),
                        isCentral()
                        );
            }
        });
        return rs;
    }

    public static String getFactoresParaUsoInQuery(List<String> factoresPrincipales) {
        if (factoresPrincipales == null) {
            return "";
        } else if (factoresPrincipales.isEmpty()) {
            return "";
        } else if (factoresPrincipales.size() == 0) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        for (String nombreFactor : factoresPrincipales) {
            sb.append("'");
            sb.append(nombreFactor);
            sb.append("',");
        }
        return sb.substring(0, sb.length() - 1);
    }
    
    public static String getIntegersParaUsoInQuery(List<Integer> integers) {
        if (integers == null) {
            return "";
        } else if (integers.isEmpty()) {
            return "";
        } else if (integers.size() == 0) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        for (Integer numero : integers) {
            sb.append(numero);
            sb.append(",");
        }
        return sb.substring(0, sb.length() - 1);
    }

    private static void muestraRegistros(ResultSet rst) {
        try {
            if (rst != null) {
                ResultSetMetaData rsmd = rst.getMetaData();
                for (int col = 1; col <= rsmd.getColumnCount(); col++) {
                    System.out.print(rsmd.getColumnName(col) + "\t");
                }
                System.out.println();

                while (rst.next()) {
                    for (int col = 1; col <= rsmd.getColumnCount(); col++) {
                        System.out.print(rst.getString(col) + "\t");
                    }
                    System.out.println();
                }
            }
//            rst.close();
        } catch (Exception e) {
        }
    }

    private List executeQueryCustomListNative(final String querySQL) {
        List resultList = new ArrayList();
        resultList = getHibernateTemplate().executeFind(new HibernateCallback() {

            public Object doInHibernate(Session session)
                    throws HibernateException, SQLException {
                SQLQuery query = session.createSQLQuery(querySQL);
                query.addScalar("OUNITID", Hibernate.INTEGER);
                query.addScalar("FNAME", Hibernate.STRING);
                query.addScalar("LVALUE", Hibernate.STRING);
                query.addScalar("LTYPE", Hibernate.STRING);
                query.addScalar("LABELID", Hibernate.INTEGER);

                return query.list();
            }
        });
        return resultList;
    }
    
    private List executeQueryRandomtNative(final String querySQL) {
        List resultList = new ArrayList();
        resultList = getHibernateTemplate().executeFind(new HibernateCallback() {

            public Object doInHibernate(Session session)
                    throws HibernateException, SQLException {
                SQLQuery query = session.createSQLQuery(querySQL);
                query.addScalar("FNAME", Hibernate.STRING);
                query.addScalar("LTYPE", Hibernate.STRING);
                query.addScalar("LABELID", Hibernate.INTEGER);
                
                return query.list();
            }
        });
        return resultList;
    }
}