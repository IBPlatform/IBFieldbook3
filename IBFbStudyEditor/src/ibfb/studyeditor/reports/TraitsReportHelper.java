/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ibfb.studyeditor.reports;

import ibfb.studyeditor.core.model.ObservationsTableModel;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JTable;

/**
 * Helper class to generate report
 *
 * @author TMSANCHEZ
 */
public class TraitsReportHelper {

    /**
     * Assign JavaBean objects from observations table to generate label report
     *
     * @param observationsTable
     */
    public static void printTraitsReport(JTable observationsTable) {
        ObservationsTableModel tableModel = (ObservationsTableModel) observationsTable.getModel();

        int trialCol = tableModel.getHeaderIndex(ObservationsTableModel.TRIAL);
        int repCol = tableModel.getHeaderIndex(ObservationsTableModel.REPLICATION);
        int blockCol = tableModel.getHeaderIndex(ObservationsTableModel.BLOCK);
        int plotCol = tableModel.getHeaderIndex(ObservationsTableModel.PLOT);
        int entryCol = tableModel.getHeaderIndex(ObservationsTableModel.ENTRY);
        int gidCol = tableModel.getHeaderIndex(ObservationsTableModel.GID);
        int desigCol = tableModel.getHeaderIndex(ObservationsTableModel.DESIG);

        if (plotCol == -1) {
            plotCol = tableModel.getHeaderIndex(ObservationsTableModel.PLOTNUMBER);
        }

        if (plotCol == -1) {
            plotCol = tableModel.getHeaderIndex(ObservationsTableModel.PLOT_NESTED);
        }


        if (blockCol == -1) {
            blockCol = tableModel.getHeaderIndex(ObservationsTableModel.BLOCK_NESTED);
        }


        List<TraitsReport> traits = new ArrayList<TraitsReport>();

        for (int row = 0; row < tableModel.getRowCount(); row++) {
            TraitsReport tr = new TraitsReport();
            Integer trait = forceCastToInteger(tableModel.getValueAt(row, trialCol));
            Integer rep = forceCastToInteger(tableModel.getValueAt(row, repCol));
            Integer block = 0;
            if (blockCol > -1) {
                block = forceCastToInteger(tableModel.getValueAt(row, blockCol));
            }

            //Integer subblock = Integer.parseInt((String)tableModel.getValueAt(row, SUBBLOCK_COL));            

            tr.setTrial(trait);
            tr.setRep(rep);
            tr.setBlock(block);
            //tr.setSubBlock(subblock);
            tr.setPlot(forceCastToInteger(tableModel.getValueAt(row, plotCol)));
            tr.setEntry(forceCastToInteger(tableModel.getValueAt(row, entryCol)));
            tr.setGid(forceCastToInteger(tableModel.getValueAt(row, gidCol)));
            if (desigCol == -1) {
                tr.setDesig("");
            } else {
                tr.setDesig(forceCastToString(tableModel.getValueAt(row, desigCol)));
            }

            traits.add(tr);

        }

        ProcessReport.printTraitsBarCodeReport(traits);

    }

    /**
     * Forces a convert from Object to Integer.
     *
     * @param objectValue
     * @return
     */
    private static Integer forceCastToInteger(Object objectValue) {
        Integer result = null;

        if (objectValue instanceof Integer) {
            result = (Integer) objectValue;
        } else if (objectValue instanceof String) {
            result = Integer.parseInt((String) objectValue);
        }

        return result;
    }

    /**
     * Forcer a convert from Object to String
     *
     * @param objectValue
     * @return
     */
    private static String forceCastToString(Object objectValue) {
        String result = null;

        if (objectValue instanceof Integer) {
            result = String.valueOf(objectValue);
        } else if (objectValue instanceof String) {
            result = (String) objectValue;
        }

        return result;
    }

    /**
     * Returns the column number for desired column name
     *
     * @param columnName Name of the column to search
     * @return Number of column
     */
    private static int getColumNumberFor(String columnName, JTable traitsTable) {
        int result = 0;

        for (int colNumber = 0; colNumber < traitsTable.getColumnCount(); colNumber++) {
            if (traitsTable.getColumnName(colNumber).equals(columnName)) {
                result = colNumber;
                break;
            }
        }

        return result;
    }
}
