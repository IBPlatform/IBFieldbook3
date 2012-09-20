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
        SQLQuery query = null;
        List resultado;

        String nameStudy = null;
        String nameTrial = null;
        String nameEntry = null;
        Integer numberEntry = 0;
        String nameGID = null;
        String namePlot = null;

        List<String> factoresPrincipales = new ArrayList<String>();
        List<String> factoresSalida = new ArrayList<String>();

        log.info("Definiendo orden de busquedas");
        String orden = HelperWorkbookReader.getOrder(isLocal, isCentral);
        
        
        log.info("--> Recuperando el nombre del estudio");
        studySearch.setsName(
                HelperWorkbookReader.getScname(
                        session,
                        query,
                        studySearch.getStudyId()
                    )
                );
        log.info("--> Termina Recuperacion del nombre del estudio.");
        
        log.info("--> Recuperando factores principales.");
        resultado = HelperWorkbookReader.getFactoresPrincipales(
                session,
                query,
                studySearch.getStudyId(),
                orden
                );
        
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
        }else{
            log.error("No se encontro el factor correspondiente a study.");
        }

        if(nameTrial != null){
            factoresPrincipales.add(nameTrial);
            factoresSalida.add(nameTrial);
            studySearch.setNameTrial(nameTrial);
        }else{
            log.error("No se encontro el factor correspondiente a trial.");
        }
        
        if(nameEntry != null){
            factoresPrincipales.add(nameEntry);
            factoresSalida.add(nameEntry);
            studySearch.setNameEntry(nameEntry);
        }else{
            log.error("No se encontro el factor correspondiente a entry.");
        }
        
        if(namePlot != null){
            factoresPrincipales.add(namePlot);
            factoresSalida.add(namePlot);
            studySearch.setNamePlot(namePlot);
        }else{
            log.error("No se encontro el factor correspondiente a plot.");
        }
        
        if (nameStudy == null  && nameTrial == null && nameEntry == null && namePlot == null) {
            log.error("No se encontro ningun factor asociado correctamente.");
            int i = 0;
            for(Object objectTemp : resultado){
                Object[] fila = (Object[]) objectTemp;
                factoresPrincipales.add(fila[0].toString());
                switch (i){
                    case 0:
                        nameStudy = fila[0].toString();
                        studySearch.setNameStudy(nameStudy);
                        break;
                    case 1:
                        nameTrial = fila[0].toString();
                        factoresSalida.add(nameTrial);
                        studySearch.setNameTrial(nameTrial);
                        break;
                    case 2: 
                        nameEntry = fila[0].toString();
                        factoresSalida.add(nameEntry);
                        studySearch.setNameEntry(nameEntry);
                        numberEntry = (Integer)fila[3];
                        break;
                    case 3:
                        namePlot = fila[0].toString();
                        factoresSalida.add(namePlot);
                        studySearch.setNamePlot(namePlot);
                        break;
                }
            }
            log.warn("Se forza la asignacion de facotres mediante filas encontradas.");
            log.info("--> Study: " + nameStudy);
            log.info("--> Trial: " + nameTrial);
            log.info("--> Entry: " + nameEntry);
            log.info("--> --> numberEntry: " + numberEntry);
            log.info("--> Plot: " + namePlot);
        }
        log.info("--> Termina Recuperando factores principales.");
        
        if(factoresPrincipales == null){
            log.error("No se encontraron factores principales");
        }else if(factoresPrincipales.size() >= 0 && factoresPrincipales.size()<4){
            log.error("No se encontraron factores principales");
        }
        
        log.info("--> Recuperando factores salida.");
        resultado = HelperWorkbookReader.getFactoresSalida(
                session,
                query,
                studySearch.getStudyId(),
                numberEntry,
                orden
                );
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
        }else{
            log.error("No se encontro ninguna correspondencia para el factor GID.");
        }
        
        log.info("--> Termina Recuperando factores salida.");
        
        /*****Implementando recuperacion de informacion******/
        
        log.info("Getting trial randomization");
        Integer numeroDeFactoresPrincipales = factoresPrincipales.size();
        String factoresResultadoStr = DMSReaderDAO.getFactoresParaUsoInQuery(factoresSalida);
        ResultSet pr;
        
        String factoresPrincipalesStr = DMSReaderDAO.getFactoresParaUsoInQuery(factoresPrincipales);
        
        Integer trepresNo = HelperWorkbookReader.getRepresno(
                session,
                query,
                studySearch.getStudyId(),
                factoresPrincipalesStr,
                numeroDeFactoresPrincipales
                );
        
        if (trepresNo == null) {
            log.error("Repres no encontrado.");
            return null;
        }
        
        RowSetMetaDataImpl rsmd = new RowSetMetaDataImpl();
        
        Integer cuantosFR = HelperWorkbookReader.getNumeroFactoresResultado(
                session,
                query,
                studySearch.getStudyId(),
                factoresResultadoStr
                );
        
        resultado = HelperWorkbookReader.getFactoresResultado(
                session,
                query,
                studySearch.getStudyId(),
                factoresResultadoStr,
                orden
                );

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
        String condicionWhere = HelperWorkbookReader.getCondicionesWhere(
                session,
                query,
                studySearch.getStudyId(),
                studySearch.getTrial(),
                nameTrial,
                trepresNo,
                factoresResultadoStr
                );
        
        resultado = HelperWorkbookReader.getListFactorsAndLevels(
                session,
                query,
                condicionWhere,
                orden
                );
        
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
    
    
    
    /**
     * This method found List of trial or occ indicated by study indicated
     * @param studyId id del estudio
     * @param trialFactorId id del trial puede ser null
     * @return resulset whit trial, entry, plot and gids
     */
    public static StudySearch getListGermplasmAndPlotByStudyidAndTrial(
            StudySearch studySearch,
            List<String> factoresPrincipales,
            List<String> factoresSalida,
            Session session,
            boolean isLocal,
            boolean isCentral
            ) throws SQLException{
        SQLQuery query = null;
        List resultado;
        
        String nameTrial = studySearch.getNameTrial();

        log.info("Definiendo orden de busquedas");
        String orden = HelperWorkbookReader.getOrder(isLocal, isCentral);
        
        
        log.info("--> Recuperando el nombre del estudio");
        studySearch.setsName(
                HelperWorkbookReader.getScname(
                        session,
                        query,
                        studySearch.getStudyId()
                    )
                );
        log.info("--> Termina Recuperacion del nombre del estudio.");
        
        if(factoresPrincipales == null){
            log.error("No se encontraron factores principales");
        }else if(factoresPrincipales.size() >= 0 && factoresPrincipales.size()<4){
            log.error("No se encontraron todos los factores principales");
        }
        
        if(factoresSalida == null){
            log.error("No se encontraron factores de salida");
        }else if(factoresSalida.size() == 0){
            log.error("No se encontraron todos los factores de salida");
        }
        
        /*****Implementando recuperacion de informacion******/
        
        log.info("Getting trial randomization");
        Integer numeroDeFactoresPrincipales = factoresPrincipales.size();
        String factoresResultadoStr = DMSReaderDAO.getFactoresParaUsoInQuery(factoresSalida);
        ResultSet pr;
        
        String factoresPrincipalesStr = DMSReaderDAO.getFactoresParaUsoInQuery(factoresPrincipales);
        
        Integer trepresNo = HelperWorkbookReader.getRepresno(
                session,
                query,
                studySearch.getStudyId(),
                factoresPrincipalesStr,
                numeroDeFactoresPrincipales
                );
        
        if (trepresNo == null) {
            log.error("Repres no encontrado.");
            return null;
        }
        
        RowSetMetaDataImpl rsmd = new RowSetMetaDataImpl();
        
        Integer cuantosFR = HelperWorkbookReader.getNumeroFactoresResultado(
                session,
                query,
                studySearch.getStudyId(),
                factoresResultadoStr
                );
        
        resultado = HelperWorkbookReader.getFactoresResultado(
                session,
                query,
                studySearch.getStudyId(),
                factoresResultadoStr,
                orden
                );

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
        String condicionWhere = HelperWorkbookReader.getCondicionesWhere(
                session,
                query,
                studySearch.getStudyId(),
                studySearch.getTrial(),
                nameTrial,
                trepresNo,
                factoresResultadoStr
                );
        
        resultado = HelperWorkbookReader.getListFactorsAndLevels(
                session,
                query,
                condicionWhere,
                orden
                );
        
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