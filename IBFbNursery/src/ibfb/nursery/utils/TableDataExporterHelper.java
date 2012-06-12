package ibfb.nursery.utils;

import ibfb.workbook.api.SpreadSheetExporter;
import ibfb.workbook.core.SpreadSheetExporterImpl;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.filechooser.FileFilter;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableModel;


public class TableDataExporterHelper {

    private JFileChooser fileChooser;

    public void processExportToExcel(JTable measurementsTable) {
        fileChooser = new JFileChooser();
        fileChooser.setFileFilter(new FileFilterImpl());
        fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);

        if (fileChooser.showSaveDialog(null) == JFileChooser.CANCEL_OPTION) {
            return;
        }

        String fileName = fileChooser.getSelectedFile().getPath() + ".xlsx";


        List<String> headerColumns = new ArrayList<String>();

        TableColumnModel tableColumns = measurementsTable.getColumnModel();
        for (int col= 0; col < tableColumns.getColumnCount(); col++ ) {
            TableColumn tableColumn = tableColumns.getColumn(col);
            headerColumns.add((String)tableColumn.getHeaderValue());
        }

        List<List<Object>> data = new ArrayList<List<Object>>();
        TableModel tableModel = measurementsTable.getModel();
        for (int row = 0; row < tableModel.getRowCount(); row++ ) {
            List<Object> colsData = new ArrayList<Object>();
            for (int col =0 ; col < tableModel.getColumnCount(); col++) {
                colsData.add(tableModel.getValueAt(row, col));
            }
            data.add(colsData);
        }



        SpreadSheetExporter sheetExporter = (SpreadSheetExporter) new SpreadSheetExporterImpl();
      
        sheetExporter.exportExcel2007(fileName, headerColumns, data);

        JOptionPane.showMessageDialog(measurementsTable.getParent(),"Excel export to file: " + fileName + " success...");
    }

    private static class FileFilterImpl extends FileFilter {

        public FileFilterImpl() {
        }

        @Override
        public boolean accept(File f) {
            return f.getName().toLowerCase().endsWith(".xlsx") || f.isDirectory();
        }

        @Override
        public String getDescription() {
            return "EXCEL FILE (.xlsx)";
        }

    
    }
}
