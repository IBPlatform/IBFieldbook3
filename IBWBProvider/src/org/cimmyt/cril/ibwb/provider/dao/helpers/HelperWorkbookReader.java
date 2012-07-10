/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.cimmyt.cril.ibwb.provider.dao.helpers;

import com.sun.rowset.CachedRowSetImpl;
import java.math.BigInteger;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.List;
import javax.sql.rowset.RowSetMetaDataImpl;
import org.apache.log4j.Logger;
import org.cimmyt.cril.ibwb.domain.GermplasmSearch;
import org.cimmyt.cril.ibwb.provider.dao.DMSReaderDAO;
import org.hibernate.Hibernate;
import org.hibernate.HibernateException;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;

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
    
}
