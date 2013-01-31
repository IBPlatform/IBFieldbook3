
package ibfb.studyeditor.export;

import com.csvreader.CsvWriter;
import ibfb.studyeditor.core.model.ObservationsTableModel;
import ibfb.studyeditor.roweditors.CSVOziel;
import java.awt.Desktop;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.ResourceBundle;
import javax.swing.JTable;
import org.cimmyt.cril.ibwb.commongui.DialogUtil;
import org.openide.NotifyDescriptor;
import org.openide.util.NbBundle;

/**
 *
 * @author TMSANCHEZ
 */
public class FieldbookCSVExporter {

    private static ResourceBundle bundle = NbBundle.getBundle(FieldbookCSVExporter.class);

    public static void exportToFieldlog(JTable jTableObservations, String trialFile, CSVOziel csv, int triallOption,
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

        Export_to_fieldlog(new File(trialFile), modeloFilter, csv);


        String fileSaved = bundle.getString("StudyEditorExport.dataSavedTo");
        fileSaved = fileSaved + " " + trialFile + bundle.getString("StudyEditorExport.dataSavedToEnd");

        if (DialogUtil.displayConfirmation(fileSaved, bundle.getString("StudyEditorExport.fileExported"), NotifyDescriptor.OK_CANCEL_OPTION)) {
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

    public static void Export_to_fieldlog(File file, ObservationsTableModel modeloFiltro, CSVOziel csv) {
        String outputFile = file.toString() + ".csv";
        boolean alreadyExists = new File(outputFile).exists();
        try {
            CsvWriter csvOutput = new CsvWriter(new FileWriter(outputFile, false), ',');
            csvOutput.write("Trial");
            csvOutput.write("Rep");
            csvOutput.write("Block");
            csvOutput.write("Plot");
            csvOutput.write("Entry");
            csvOutput.write("StockID");
            csvOutput.write("Name");
            csvOutput.write("BreedersPedigree1");
            csv.writeColums(csvOutput, 6);
            csvOutput.write("Origin");
            csv.writeColums(csvOutput, 8);
            csvOutput.write("GID");
            csv.writeColums(csvOutput, 2);
            csv.writeTraitsFromObservations(csvOutput, modeloFiltro);
            csvOutput.endRecord();
            csv.writeRows(csvOutput, 23);
            csv.writeDATA(csvOutput, modeloFiltro);
            csvOutput.close();
        } catch (IOException e) {
            System.out.println("ERROR AL CREAR CVS fieldlog");
        }
    }
}
