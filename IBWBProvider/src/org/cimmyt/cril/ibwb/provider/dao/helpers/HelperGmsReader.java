/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.cimmyt.cril.ibwb.provider.dao.helpers;

import com.sun.rowset.CachedRowSetImpl;
import ibfb.domain.core.Workbook;
import java.math.BigInteger;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;
import javax.sql.rowset.RowSetMetaDataImpl;
import org.apache.log4j.Logger;
import org.cimmyt.cril.ibwb.domain.StudySearch;
import org.cimmyt.cril.ibwb.provider.dao.DMSReaderDAO;
import org.hibernate.Hibernate;
import org.hibernate.SQLQuery;
import org.hibernate.Session;

/**
 *
 * @author gamaliel
 */
public class HelperGmsReader {
    
    private static final Logger log = Logger.getLogger(HelperGmsReader.class);
    private static final int ltype = 1;
    private static final int fname = 0;
    private static final int represNo = 0;
    private static final int ounitid = 0;
    private static final int FNAME = 1;
    private static final int LVALUE = 2;
    private static final int LTYPE = 3;
    
    /**
     * This method found List of trial or occ indicated by study indicated
     * @param studyId id del estudio
     * @param trialFactorId id del trial puede ser null
     * @return resulset whit trial, entry, plot and gids
     */
    public static StudySearch getListGermplasmAndPlotByStudyidAndTrial(
            StudySearch studySearch,
            Session session,
            boolean isLocal,
            boolean isCentral
            ) throws SQLException{
        SQLQuery query;
        List resultado;
        String consultaSQL;

        String nameStudy = null;
        String nameTrial = null;
        String nameEntry = null;
        Integer numberEntry = 0;
        String nameGID = null;
        String namePlot = null;

        List<String> factoresPrincipales = new ArrayList<String>();
        List<String> factoresSalida = new ArrayList<String>();

        log.info("Definiendo orden de busquedas");
        String orden;
        if (isLocal) {
            orden = "DESC";
        } else if (isCentral) {
            orden = "ASC";
        } else {
            orden = "DESC";
        }
        
        log.info("--> Recuperando el nombre del estudio");
        consultaSQL = "select SNAME as SNAME "
                + "from study "
                + "where study.STUDYID = " + studySearch.getStudyId() + ";";
        query = session.createSQLQuery(consultaSQL);
        query.addScalar("SNAME", Hibernate.STRING);
        Object snameTemp = query.uniqueResult();
        studySearch.setsName((String)snameTemp);
        log.info("--> Termina Recuperacion del nombre del estudio.");
        
        log.info("--> Recuperando factores principales.");

        consultaSQL = "SELECT "
                + "factor.FNAME as FNAME, "
                + "tmstraits.trname as TRNAME, "
                + "tmsscales.scname as SCNAME, "
                + "factor.LABELID as LABELID "
                + "from factor "
                + "LEFT join tmsmeasuredin on "
                + "factor.TID = tmsmeasuredin.traitid and "
                + "tmsmeasuredin.scaleid = factor.SCALEID and "
                + "factor.TMETHID = tmsmeasuredin.tmethid "
                + "LEFT JOIN tmsscales ON "
                + "tmsscales.scaleid = tmsmeasuredin.scaleid "
                + "LEFT JOIN tmstraits ON "
                + "tmstraits.tid = tmsmeasuredin.traitid "
                + "where factor.STUDYID = "
                + studySearch.getStudyId()
                + " and factor.FACTORID = factor.LABELID "
                + "order by LABELID " + orden + ";";

        query = session.createSQLQuery(consultaSQL);
        query.addScalar("FNAME", Hibernate.STRING);
        query.addScalar("TRNAME", Hibernate.STRING);
        query.addScalar("SCNAME", Hibernate.STRING);
        query.addScalar("LABELID", Hibernate.INTEGER);
        resultado = query.list();
        for(Object filaTemp : resultado){
            Object[] fila = (Object[]) filaTemp;
            String nombre = Workbook.getStringWithOutBlanks((String)fila[1] + (String)fila[2]);

            if(Workbook.STUDY_NAME.equals(nombre)){
                nameStudy = (String)fila[0];
            }else if(Workbook.TRIAL_INSTANCE_NUMBER.equals(nombre)){
                nameTrial = (String)fila[0];
            }else if(Workbook.GERMPLASM_ENTRY_NUMBER.equals(nombre)){
                nameEntry = (String)fila[0];
                numberEntry = (Integer)fila[3];
            }else if(Workbook.FIELD_PLOT_NUMBER.equals(nombre) ||
                    Workbook.FIELD_PLOT_NESTEDNUMBER.equals(nombre)){
                namePlot = (String)fila[0];
            }
        }

        if(nameStudy != null){
            factoresPrincipales.add(nameStudy);
            studySearch.setNameStudy(nameStudy);
        }

        if(nameTrial != null){
            factoresPrincipales.add(nameTrial);
            factoresSalida.add(nameTrial);
            studySearch.setNameTrial(nameTrial);
        }

        if(nameEntry != null){
            factoresPrincipales.add(nameEntry);
            factoresSalida.add(nameEntry);
            studySearch.setNameEntry(nameEntry);
        }

        if(namePlot != null){
            factoresPrincipales.add(namePlot);
            factoresSalida.add(namePlot);
            studySearch.setNamePlot(namePlot);
        }


        log.info("--> Termina Recuperando factores principales.");

        log.info("--> Recuperando factores salida.");

        consultaSQL = "SELECT "
                + "factor.FNAME as FNAME, "
                + "tmstraits.trname as TRNAME, "
                + "tmsscales.scname as SCNAME, "
                + "factor.LABELID as LABELID "
                + "from factor "
                + "LEFT join tmsmeasuredin on "
                + "factor.TID = tmsmeasuredin.traitid and "
                + "tmsmeasuredin.scaleid = factor.SCALEID and "
                + "factor.TMETHID = tmsmeasuredin.tmethid "
                + "LEFT JOIN tmsscales ON "
                + "tmsscales.scaleid = tmsmeasuredin.scaleid "
                + "LEFT JOIN tmstraits ON "
                + "tmstraits.tid = tmsmeasuredin.traitid "
                + "where factor.STUDYID = "
                + studySearch.getStudyId()
                + " and factor.FACTORID = "
                + numberEntry
                + " order by LABELID " + orden + ";";

        query = session.createSQLQuery(consultaSQL);
        query.addScalar("FNAME", Hibernate.STRING);
        query.addScalar("TRNAME", Hibernate.STRING);
        query.addScalar("SCNAME", Hibernate.STRING);
        query.addScalar("LABELID", Hibernate.INTEGER);
        resultado = query.list();

        for(Object filaTemp : resultado){
            Object[] fila = (Object[]) filaTemp;
            String nombre = Workbook.getStringWithOutBlanks((String)fila[1] + (String)fila[2]);

            if(Workbook.GERMPLASM_GID_DBID.equals(nombre)){
                nameGID = (String)fila[0];
                break;
            }
        }

        if(nameGID != null){
            factoresSalida.add(nameGID);
            studySearch.setNameGid(nameGID);
        }

        log.info("--> Termina Recuperando factores salida.");

        log.info("Getting trial randomization");

        Integer numeroDeFactoresPrincipales = factoresPrincipales.size();
        String listaDeFactoresResultado = DMSReaderDAO.getFactoresParaUsoInQuery(factoresSalida);
        ResultSet pr;

        consultaSQL = "SELECT represno, COUNT(*) FROM effect e "
                + "INNER JOIN factor f ON e.factorid=f.factorid "
                + "WHERE studyid=" + studySearch.getStudyId() + " AND "
                + "f.factorid = f.labelid AND "
                + "fname IN(" + DMSReaderDAO.getFactoresParaUsoInQuery(factoresPrincipales) + ") "
                + "GROUP BY represno HAVING COUNT(*)=" + numeroDeFactoresPrincipales;
        query = session.createSQLQuery(consultaSQL);
        resultado = query.list();

        int trepresNo;
        if (resultado != null) {
            if (resultado.size() > 0) {
                Object[] fila = (Object[]) resultado.get(0);
                trepresNo = (Integer) fila[represNo];
            } else {
                return null;
            }
        } else {
            return null;
        }

        RowSetMetaDataImpl rsmd = new RowSetMetaDataImpl();
        consultaSQL = "SELECT count(*) FROM factor "
                + "WHERE studyid=" + studySearch.getStudyId()
                + " and fname IN(" + listaDeFactoresResultado + ")";

        int cuantosFR = 0;

        query = session.createSQLQuery(consultaSQL);
        Object tempObject = query.uniqueResult();

        if (tempObject instanceof BigInteger) {
            BigInteger temp = (BigInteger) tempObject;
            cuantosFR = temp.intValue();
        } else if (tempObject instanceof Integer) {
            Integer temp = (Integer) tempObject;
            cuantosFR = temp.intValue();
        }

        consultaSQL = "SELECT fname, ltype, labelid FROM factor "
                + "WHERE studyid=" + studySearch.getStudyId()
                + " and fname IN(" + listaDeFactoresResultado + ")"
                + " ORDER BY labelid " + orden;

        query = session.createSQLQuery(consultaSQL);
        resultado = query.list();

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

        CachedRowSetImpl crs = new CachedRowSetImpl();
        int i889 = 0;
        crs.setMetaData(rsmd);
        String condicionWhere = "f.fname IN (" + listaDeFactoresResultado + ") AND studyid = " + studySearch.getStudyId() + " AND represno =" + trepresNo + "";
        if (studySearch.getTrial() > 0) {
            consultaSQL = "SELECT OUNITID FROM FACTOR F "
                    + "INNER JOIN (LEVEL_N L INNER JOIN OINDEX O "
                    + "ON (L.LEVELNO = O.LEVELNO) AND (L.FACTORID = O.FACTORID)) "
                    + "ON (F.FACTORID = L.FACTORID) "
                    + "AND (F.LABELID = L.LABELID) "
                    + "WHERE f.fname IN ('" + nameTrial + "') "
                    + "AND studyid = " + studySearch.getStudyId()
                    + " AND represno =" + trepresNo
                    + " AND lvalue = " + studySearch.getTrial();

            query = session.createSQLQuery(consultaSQL);
            resultado = query.list();

            int cuantosRegistros = 0;
            String cadOunitid = "";

            if (resultado.size() == 0) {
                return null;
            } else {
                for (Object fila : resultado) {
                    cuantosRegistros += 1;
                    cadOunitid += fila.toString() + ",";
                }
            }
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

        query = session.createSQLQuery(consultaSQL);

        query.addScalar("OUNITID", Hibernate.INTEGER);
        query.addScalar("FNAME", Hibernate.STRING);
        query.addScalar("LVALUE", Hibernate.STRING);
        query.addScalar("LTYPE", Hibernate.STRING);
        query.addScalar("LABELID", Hibernate.INTEGER);

        resultado = query.list();

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
        log.info("Getting trial randomization.... DONE");
        studySearch.setRst(pr);
        return studySearch;
    }
}