
package ibfb.nursery.utils;

import com.csvreader.CsvReader;
import com.csvreader.CsvWriter;
import ibfb.nursery.models.ObservationsTableModel;
import javax.swing.table.DefaultTableModel;
import java.io.File;
import java.io.FileWriter;
import javax.swing.JTable;
import org.openide.util.Exceptions;


public class FieldbookCSVUtil {

    
    private JTable fieldbookTable;
    
    private String fileName;
   
    private CsvWriter fieldbookWriter;
    private String studyName;

    public FieldbookCSVUtil(JTable fieldbookTable, String studyName) {
        this.fieldbookTable = fieldbookTable;
        this.studyName = studyName;

    }

    public void saveToCsv() {
        try {
            fileName = getFileName(studyName);
            
            File file = new File(fileName);
            
            if (file.exists()) {
                file.delete();
            }
            
            fieldbookWriter = new CsvWriter(new FileWriter(fileName, true), ',');

            writeFieldbooHeaders();

            writeFieldbookData();

            fieldbookWriter.close();

        } catch (Exception ex) {
            Exceptions.printStackTrace(ex);
        }

    }


    public void populateFiedlbook(JTable fieldbookToFill, String studyName) {
        try {
            fileName = getFileName(studyName);

            CsvReader fieldbook = new CsvReader(fileName);

            fieldbook.readHeaders();
            String[] headers = fieldbook.getHeaders();

            DefaultTableModel tableModel = (DefaultTableModel) fieldbookToFill.getModel();

            tableModel.setColumnCount(0);
            tableModel.setNumRows(0);

            for (int headerIndex = 0; headerIndex < headers.length; headerIndex++) {
                tableModel.addColumn(headers[headerIndex]);
            }

            tableModel.setNumRows(getRowsCount(studyName));

            int currentRow = 0;

            while (fieldbook.readRecord()) {

                String[] values = fieldbook.getValues();

                //tableModel.addRow(values);
                for (int col = 0; col < values.length; col++) {
                    tableModel.setValueAt(values[col], currentRow, col);
                }
                currentRow++;
            }

            fieldbook.close();

            fieldbookToFill.setModel(tableModel);

        } catch (Exception ex) {
            Exceptions.printStackTrace(ex);
        }
    }

    private int getRowsCount(String studyName) {
        int rowCount = 0;

        try {
            fileName = getFileName(studyName);

            CsvReader fieldbook = new CsvReader(fileName);

            fieldbook.readHeaders();


            while (fieldbook.readRecord()) {
                rowCount++;
            }

            fieldbook.close();
        } catch (Exception e) {
        }

        return rowCount;
    }

    private void writeFieldbooHeaders() throws Exception {
        for (int col = 0; col < fieldbookTable.getColumnCount(); col++) {
            fieldbookWriter.write(fieldbookTable.getColumnName(col));
        }
        fieldbookWriter.endRecord();
    }

    /**
     * Write all cell data contents into file
     */
    private void writeFieldbookData() throws Exception {

        ObservationsTableModel tableModel = (ObservationsTableModel) fieldbookTable.getModel();
        int columnCount = fieldbookTable.getColumnCount();
        for (int row = 0; row < tableModel.getRowCount(); row++) {
            for (int col = 0; col < columnCount; col++) {
                Object value = tableModel.getValueAt(row, col);
                if (value != null) {
                    fieldbookWriter.write(value.toString());
                } else {
                    fieldbookWriter.write(null);
                }
            }
            fieldbookWriter.endRecord();
        }
    }

    /**
     * 
     * @param studyName
     * @return 
     */
    public static String getFileName(String studyName) {
        String tempDirectory = System.getProperty("java.io.tmpdir");

        return tempDirectory + studyName + ".csv";
    }
    
}
