/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ibfb.studyeditor.export;

import com.csvreader.CsvWriter;
import ibfb.studyeditor.core.model.ObservationsTableModel;
import ibfb.studyeditor.roweditors.CSVOziel;
import java.awt.Desktop;
import org.cimmyt.cril.ibwb.commongui.DialogUtil;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import javax.swing.JTable;
import org.openide.NotifyDescriptor;

/**
 *
 * @author TMSANCHEZ
 */
public class FieldbookRExport {

    public static void exportToR(JTable jTableObservations, String trialFile, CSVOziel csv, int triallOption,
            int trialStart, int trialEnd, int trialSelected) {

        ObservationsTableModel modeloOriginal = (ObservationsTableModel) jTableObservations.getModel();

        ObservationsTableModel modeloFilter = new ObservationsTableModel(modeloOriginal.getWorkbook(), modeloOriginal.getVariateList());

        if (triallOption == 0) {
            modeloFilter = modeloOriginal;
        }


        if (triallOption == 1) {

            for (int i = 0; i < modeloOriginal.getRowCount(); i++) {
                if (Integer.parseInt(modeloOriginal.getValueAt(i, 0).toString()) == trialSelected) {
                    List<Object> row = modeloOriginal.getRow(i);
                    modeloFilter.addRow(row);
                }

            }
        }


        if (triallOption == 2) {
            for (int i = 0; i < modeloOriginal.getRowCount(); i++) {
                int trialNum = Integer.parseInt(modeloOriginal.getValueAt(i, 0).toString());
                if (trialNum >= trialStart && trialNum <= trialEnd) {
                    List<Object> row = modeloOriginal.getRow(i);
                    modeloFilter.addRow(row);
                }
            }
        }

        Export_to_R(new File(trialFile), modeloFilter, csv);


        //DialogUtil.displayInfo("The data was saved to " + trialFile + ".csv file");

        String fileSaved = "The data was saved to " + trialFile + " file.  \nDo you want to open file now?";

        if (DialogUtil.displayConfirmation(fileSaved, "File exported", NotifyDescriptor.OK_CANCEL_OPTION)) {
            try {

                if (Desktop.isDesktopSupported() == true) {
                    Desktop desktop = Desktop.getDesktop();
                    //String folderLocation = OSUtils.extractFilePath(trialFile + ".csv");
                    //File theFile = new File(folderLocation);
                    File theFile = new File(trialFile + ".csv");
                    desktop.open(theFile);
                }
            } catch (Exception e) {
                System.out.println("Error opening file");
            }
        }
    }

    private static void Export_to_R(File file, ObservationsTableModel modeloFiltro, CSVOziel csv) {
        String outputFile = file.toString() + ".csv";
        boolean alreadyExists = new File(outputFile).exists();
        try {
            CsvWriter csvOutput = new CsvWriter(new FileWriter(outputFile, false), ',');
            csvOutput.write("LOC");
            csvOutput.write("REP");
            csvOutput.write("BLK");
            csvOutput.write("ENTRY");
            //csvOutput.write("GY");
            csv.writeTraitsR(csvOutput, modeloFiltro);
            csvOutput.endRecord();
            csv.writeDATAR(csvOutput, modeloFiltro);
            csvOutput.close();
        } catch (IOException e) {
            System.out.println("ERROR AL CREAR CSV FILE FOR R");
        }
    }
}
