/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.cimmyt.cril.ibwb.provider.dao.helpers;

import com.sun.rowset.CachedRowSetImpl;
import ibfb.domain.core.Measurement;
import ibfb.domain.core.MeasurementData;
import java.math.BigInteger;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.*;
import javax.management.Query;
import javax.sql.rowset.RowSetMetaDataImpl;
import org.apache.log4j.Logger;
import org.cimmyt.cril.ibwb.domain.*;
import org.cimmyt.cril.ibwb.provider.dao.DMSReaderDAO;
import org.cimmyt.cril.ibwb.provider.utils.DecimalUtils;
import org.hibernate.Hibernate;
import org.hibernate.SQLQuery;
import org.hibernate.Session;

/**
 *
 * @author gamaliel
 */
public class HelperWorkbookReader {
    
    private static final Logger log = Logger.getLogger(HelperWorkbookReader.class);
    private static final int ltype = 1;
    private static final int fname = 0;
    private static final int represNo = 0;
    private static final int ounitid = 0;
    private static final int FNAME = 1;
    private static final int LVALUE = 2;
    private static final int LTYPE = 3;

    public static ResultSet getTrialRandomizationFast(
            final int studyId,
            final int trialFactorId,
            final List<String> factoresPrincipales,
            final List<String> factoresSalida,
            final String trialName,
            Session session,
            boolean isLocal,
            boolean isCentral
            ) throws SQLException {
        SQLQuery query;
        
        log.info("Getting trial randomization");
        Integer numeroDeFactoresPrincipales = factoresPrincipales.size();
        String listaDeFactoresResultado = DMSReaderDAO.getFactoresParaUsoInQuery(factoresSalida);
        ResultSet pr = null;
        
        String consultaSQL = "SELECT represno, COUNT(*) FROM effect e "
                + "INNER JOIN factor f ON e.factorid=f.factorid "
                + "WHERE studyid=" + studyId + " AND "
                + "f.factorid = f.labelid AND "
                + "fname IN(" + DMSReaderDAO.getFactoresParaUsoInQuery(factoresPrincipales) + ") "
                + "GROUP BY represno HAVING COUNT(*)=" + numeroDeFactoresPrincipales;
        query = session.createSQLQuery(consultaSQL);
        List resultado = query.list();
        
        log.info("Definiendo orden de busquedas");
        String orden;
        if (isLocal) {
            orden = "DESC";
        } else if (isCentral) {
            orden = "ASC";
        } else {
            orden = "DESC";
        }
        
        int trepresNo = 0;
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
                + "WHERE studyid=" + studyId
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
                + "WHERE studyid=" + studyId
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
        return pr;
    }
    
    public static Study getFullFactorsByStudyIdAndEffectId(Study study, Integer effectId){
       SQLQuery query = null;
       
       return study;
    }
    
    public static List<Measurement> getTrialRandomizationVeryFast(
            final int studyId,
            final int trialFactorId,
            final List<String> factoresPrincipales,
            final List<String> factoresSalida,
            final String trialName,
            Session session,
            boolean isLocal,
            boolean isCentral
            ) throws SQLException {
        
        String factoresPrincipalesStr = DMSReaderDAO.getFactoresParaUsoInQuery(factoresPrincipales);
        String factoresResultadoStr = DMSReaderDAO.getFactoresParaUsoInQuery(factoresSalida);
        
        SQLQuery query = null;
        List resultado;

        log.info("Getting trial randomization fast");
        Integer numeroDeFactoresPrincipales = factoresPrincipales.size();
        
        Integer trepresNo = HelperWorkbookReader.getRepresno(
                session,
                query,
                studyId,
                factoresPrincipalesStr,
                numeroDeFactoresPrincipales
                );
        
        if (trepresNo == null) {
            log.error("Repres no encontrado.");
            return null;
        }

        log.info("Definiendo orden de busquedas");
        String orden = HelperWorkbookReader.getOrder(isLocal, isCentral);
        
        Integer cuantosFR = HelperWorkbookReader.getNumeroFactoresResultado(
                session,
                query,
                studyId,
                factoresResultadoStr
                );
        
        if(! cuantosFR.equals(factoresSalida.size())){
            log.error("No se encontraron todos los factores solicitados.");
            return null;
        }

//        resultado = HelperWorkbookReader.getFactoresResultado(
//                session,
//                query,
//                studyId,
//                factoresResultadoStr,
//                orden
//                );
        
        String condicionWhere = HelperWorkbookReader.getCondicionesWhere(
                session,
                query,
                studyId,
                trialFactorId,
                trialName,
                trepresNo,
                factoresResultadoStr
                );
        
        resultado = HelperWorkbookReader.getListFactorsAndLevels(
                session,
                query,
                condicionWhere,
                orden
                );
        
        Integer ounitInicial;
        if(resultado == null){
            log.error("No se encontro ningun dato referente a los factores.");
            return null;
        }else if(resultado.isEmpty()){
            log.error("No se encontro ningun dato referente a los factores.");
            return null;
        }else{
            Object fila = resultado.get(0);
            Object[] celdas = (Object[]) fila;
            ounitInicial = (Integer) celdas[0];
        }
        Map<String, Integer> ordenFactoresSalida = new HashMap<String, Integer>();
        for(String factorS : factoresSalida){
            ordenFactoresSalida.put(factorS, factoresSalida.indexOf(factorS));
        }
        List<Object> factorLabelList = new ArrayList<Object>();
        for(int i = 0 ; i<factoresSalida.size() ; i++){
            factorLabelList.add("");
        }
        List<Measurement> measurementList = new ArrayList<Measurement>();
        Measurement measurement = new Measurement();
        Integer ounitTemp = ounitInicial.intValue();
        for(Object fila : resultado){
            Object[] celdas = (Object[]) fila;
            //Condiciones para cambio de fila
            if(!ounitTemp.equals(celdas[0])){
                measurement.setFactorLabelData(factorLabelList);
                measurement.setOunitId(ounitTemp);
                measurementList.add(measurement);
                measurement = new Measurement();
                factorLabelList = new ArrayList<Object>();
                for(int i = 0; i<factoresSalida.size() ; i++){
                    factorLabelList.add("");
                }
                ounitTemp = (Integer) celdas[0];
            }
            factorLabelList.set(ordenFactoresSalida.get((String) celdas[1]), celdas[2]);
        }
        measurement.setFactorLabelData(factorLabelList);
        measurement.setOunitId(ounitTemp);
        measurementList.add(measurement);
        
        log.info("Getting trial randomization fast.... DONE");
        
        log.info("Getting variates fast....");
        List<Integer> listVariates = getVariatesByRepresno(session, query, trepresNo);
        if(listVariates == null){
            log.info("No se encontraron variates por recuperar.");
        }else if(listVariates.isEmpty()){
            log.info("No se encontraron variates por recuperar.");
        }else if(listVariates.size() == 0){
            log.info("No se encontraron variates por recuperar.");
        }else{
            List<DataN> dataNs = getDataN(session, listVariates, orden);
            List<DataC> dataCs = getDataC(session, listVariates, orden);
            List<Variate> variates = getVariates(session, listVariates, orden);
            Map<Integer, Integer> mapaVariates = new HashMap<Integer, Integer>();

            Map<Integer, Integer> mapaOunitId = new HashMap<Integer, Integer>();

            for(Variate variate : variates){
                mapaVariates.put(variate.getVariatid(), variates.indexOf(variate));
            }

            for(Measurement measurement1 : measurementList){
                measurement1.initMeasurementData(variates.size());
                mapaOunitId.put(measurement1.getOunitId(), measurementList.indexOf(measurement1));
            }

            for(DataN dataN : dataNs){
                Integer indiceY = mapaOunitId.get(dataN.getDataNPK().getOunitid());
                Integer indiceX = mapaVariates.get(dataN.getDataNPK().getVariatid());
                if(indiceY != null){
    //                log.info(" indice x: " + indiceX + " indice y: " + indiceY);
    //                if(indiceX == null || indiceY == null){
    //                    log.info(" indice x: " + dataN.getDataNPK().getVariatid() + " indice y: " + dataN.getDataNPK().getOunitid());
    //                }
                    Measurement measurementTemp = measurementList.get(indiceY);
                    MeasurementData measurementData = measurementTemp.getMeasurementsData().get(indiceX);
                    measurementData.setData(dataN);
                }
            }

            for(DataC dataC : dataCs){
                Integer indiceY = mapaOunitId.get(dataC.getDataCPK().getOunitid());
                Integer indiceX = mapaVariates.get(dataC.getDataCPK().getVariatid());
                if(indiceY != null){
                    Measurement measurementTemp = measurementList.get(indiceY);
                    MeasurementData measurementData = measurementTemp.getMeasurementsData().get(indiceX);
                    measurementData.setData(dataC);
                }
            }
        }
        log.info("Getting variates fast.... DONE");
        return measurementList;
    }
    
    public static String getOrder(boolean local, boolean central){
        if (local) {
            return "DESC";
        } else if (central) {
            return "ASC";
        } else {
            return "DESC";
        }
    }
    
    public static String getScname(
            Session session,
            SQLQuery query,
            Integer studyid
            ){
        if(studyid == null){
            log.error("El siguiente studyid = " + studyid + " no es un estudio valido.");
        }
        String consultaSQL = "select SNAME as SNAME "
                + "from study "
                + "where study.STUDYID = " + studyid + ";";
        query = session.createSQLQuery(consultaSQL);
        query.addScalar("SNAME", Hibernate.STRING);
        Object snameTemp = query.uniqueResult();
        if (snameTemp == null){
            log.error("No se encontro el nombre del estudio para el id = " + studyid);
        }
        return (String)snameTemp;
    }
    
    public static List getFactoresPrincipales(
            Session session,
            SQLQuery query,
            Integer studyid,
            String orden
            ){
        List resultado;
        
        String consultaSQL = "SELECT "
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
                + studyid
                + " and factor.FACTORID = factor.LABELID "
                + "order by LABELID " + orden + ";";

        query = session.createSQLQuery(consultaSQL);
        query.addScalar("FNAME", Hibernate.STRING);
        query.addScalar("TRNAME", Hibernate.STRING);
        query.addScalar("SCNAME", Hibernate.STRING);
        query.addScalar("LABELID", Hibernate.INTEGER);
        resultado = query.list();
        if(resultado == null){
            log.error("No se encontraron factores principales.");
        }
        return resultado;
    }
    
    public static List getFactoresSalida(
            Session session,
            SQLQuery query,
            Integer studyid,
            Integer numberEntry,
            String orden
            ){
        List resultado;
        
        String consultaSQL = "SELECT "
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
                + studyid
                + " and factor.FACTORID = "
                + numberEntry
                + " order by LABELID " + orden + ";";
        
        query = session.createSQLQuery(consultaSQL);
        query.addScalar("FNAME", Hibernate.STRING);
        query.addScalar("TRNAME", Hibernate.STRING);
        query.addScalar("SCNAME", Hibernate.STRING);
        query.addScalar("LABELID", Hibernate.INTEGER);
        resultado = query.list();
        if(resultado == null){
            log.error("No se encontro ningun factor para los facores de salida.");
        }
        return resultado;
    }

    public static Integer getRepresno(
            Session session,
            SQLQuery query,
            Integer studyid,
            String factoresPrincipalesStr,
            Integer numeroDeFactoresPrincipales
            ){
        String consultaSQL = "SELECT represno, COUNT(*) FROM effect e "
                + "INNER JOIN factor f ON e.factorid=f.factorid "
                + "WHERE studyid=" + studyid + " AND "
                + "f.factorid = f.labelid AND "
                + "fname IN (" + factoresPrincipalesStr + ") "
                + "GROUP BY represno HAVING COUNT(*) = " + numeroDeFactoresPrincipales;
        query = session.createSQLQuery(consultaSQL);
        List resultado = query.list();
        if (resultado != null) {
            if (resultado.size() > 0) {
                Object[] fila = (Object[]) resultado.get(0);
                return (Integer) fila[represNo];
            } else {
                log.error("No se encontro el represNo");
                return null;
            }
        } else {
            log.error("No se encontro el represNo");
            return null;
        }
    }

    public static Integer getNumeroFactoresResultado(
            Session session,
            SQLQuery query,
            Integer studyid,
            String factoresResultadoStr
            ){
        String consultaSQL = "SELECT count(*) FROM factor "
                + "WHERE studyid=" + studyid
                + " and fname IN(" + factoresResultadoStr + ")";
        
        Integer cuantosFR = 0;
        
        query = session.createSQLQuery(consultaSQL);
        Object tempObject = query.uniqueResult();
        
        if (tempObject instanceof BigInteger) {
            BigInteger temp = (BigInteger) tempObject;
            cuantosFR = temp.intValue();
        } else if (tempObject instanceof Integer) {
            Integer temp = (Integer) tempObject;
            cuantosFR = temp.intValue();
        } else if(tempObject == null){
            log.error("No se encontro ningun factor resultado");
        }
        return cuantosFR;
    }
    
    public static List getFactoresResultado(
            Session session,
            SQLQuery query,
            Integer studyid,
            String factoresResultadoStr,
            String orden
            ){
        String consultaSQL = "SELECT fname, ltype, labelid FROM factor "
                + "WHERE studyid=" + studyid
                + " and fname IN(" + factoresResultadoStr + ")"
                + " ORDER BY labelid " + orden;
        if(factoresResultadoStr == null){
            log.error("La lista de factores esta vacia.");
        }else if(factoresResultadoStr.isEmpty()){
            log.error("La lista de factores esta vacia.");
        }
        query = session.createSQLQuery(consultaSQL);
        List resultado = query.list();
        if(resultado == null){
            log.error("No se encontraron factores para regresar.");
        }
        return resultado;
    }
    
    public static String getCondicionesWhere(
            Session session,
            SQLQuery query,
            Integer studyid,
            Integer trial,
            String trialName,
            Integer represNo,
            String factoresResultadoStr
            ){
        String condicionWhere = "f.fname IN (" + factoresResultadoStr + ") AND studyid = " + studyid + " AND represno =" + represNo + "";
        if (trial > 0) {
            String consultaSQL = "SELECT OUNITID FROM FACTOR F "
                    + "INNER JOIN (LEVEL_N L INNER JOIN OINDEX O "
                    + "ON (L.LEVELNO = O.LEVELNO) AND (L.FACTORID = O.FACTORID)) "
                    + "ON (F.FACTORID = L.FACTORID) "
                    + "AND (F.LABELID = L.LABELID) "
                    + "WHERE f.fname IN ('" + trialName + "') "
                    + "AND studyid = " + studyid
                    + " AND represno =" + represNo
                    + " AND lvalue = " + trial;

            query = session.createSQLQuery(consultaSQL);
            List resultado = query.list();

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
        return condicionWhere;
    }

    public static List getListFactorsAndLevels(
            Session session,
            SQLQuery query,
            String condicionWhere,
            String orden
            
            ){
        String consultaSQL = "SELECT O.OUNITID, FNAME, LVALUE, LTYPE, F.LABELID "
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
        
        List resultado = query.list();
        if(resultado == null){
            log.error("No se encontro ningun listado de factores y levesl a devolver");
            log.error("No se logro recuperar estructura");
        }else if(resultado.isEmpty()){
            log.error("No se encontro ningun listado de factores y levesl a devolver");
            log.error("No se logro recuperar estructura");
        }
        return resultado;
    }
    
    public static List<Integer> getVariatesByRepresno(
            Session session,
            SQLQuery query,
            Integer represNo
            ){
        String consultaSQL = "select variatid "
                + "from veffect "
                + "WHERE veffect.REPRESNO = " + represNo + ";";
        query = session.createSQLQuery(consultaSQL);
        query.addScalar("variatid", Hibernate.INTEGER);
        List resultado = query.list();
        if (resultado != null) {
            if (resultado.size() > 0) {
                return resultado;
            } else {
                log.error("No se encontro ningun variate para el represNo " + represNo);
                return null;
            }
        } else {
            log.error("No se encontro ningun variate para el represNo " + represNo);
            return null;
        }
    }
    
    public static List<DataN> getDataN(
            Session session,
            List<Integer> variates,
            String orden
            ){
        if(variates == null){
            log.error("No existen variates para recuperar DATA_N");
        }else if(variates.isEmpty()){
            log.error("No existen variates para recuperar DATA_N");
        }
        List<DataN> resultado;
        String consultaHQL = "from DataN as dataN "
                + "where dataN.dataNPK.variatid in (:VariatesStr) "
                + "order by dataN.dataNPK.ounitid " + orden + ", dataN.dataNPK.variatid " + orden;
        org.hibernate.Query query = session.createQuery(consultaHQL);
        query.setParameterList("VariatesStr", variates.toArray());
        resultado = query.list();
        if(resultado != null){
            return resultado;
        }else{
            log.error("No se encontraron datos en data_n.");
            return null;
        }
    }
    
    public static List<DataC> getDataC(
            Session session,
            List<Integer> variates,
            String orden
            ){
        if(variates == null){
            log.error("No existen variates para recuperar DATA_C");
        }else if(variates.isEmpty()){
            log.error("No existen variates para recuperar DATA_C");
        }
        List<DataC> resultado;
        String consultaHQL = "from DataC as dataC "
                + "where dataC.dataCPK.variatid in (:VariatesStr) "
                + "order by dataC.dataCPK.ounitid " + orden + ", dataC.dataCPK.variatid " + orden;
        org.hibernate.Query query = session.createQuery(consultaHQL);
        query.setParameterList("VariatesStr", variates.toArray());
        resultado = query.list();
        if(resultado != null){
            return resultado;
        }else{
            log.error("No se encontraron datos en data_c.");
            return null;
        }
    }
    
    public static List<Variate> getVariates(
            Session session,
            List<Integer> variates,
            String orden
            ){
        List<Variate> resultado;
        String consultaHQL = "from Variate as variate "
                + "where variatid in (:VariatesStr) "
                + "order by variatid " + orden;
        org.hibernate.Query query = session.createQuery(consultaHQL);
        query.setParameterList("VariatesStr", variates.toArray());
        resultado = query.list();
        if(resultado != null){
            return resultado;
        }else{
            log.error("No se encontraron variates a partir de la lista de variates  de veffect proporcionada.");
            return null;
        }
    }
    
    public static List<Factor> getFactors(
            Session session,
            Study study,
            List<Integer> labelIds,
            String orden
            ){
        List<Factor> resultado;
        String consultaHQL = "from Factor as f "
                + "where f.labelid in (:FactorsStr) "
                + "order by labelid " + orden
                + ", order by labelid " + orden
                ;
        org.hibernate.Query query = session.createQuery(consultaHQL);
        query.setParameterList("FactorsStr", labelIds.toArray());
        resultado = query.list();
        if(resultado != null){
            return resultado;
        }else{
            log.error("No se encontraron factores a partir de la lista de labels proporcionada.");
            return null;
        }
    }
}
