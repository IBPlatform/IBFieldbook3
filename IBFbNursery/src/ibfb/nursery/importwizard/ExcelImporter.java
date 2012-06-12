
package ibfb.nursery.importwizard;



import ibfb.nursery.models.ObservationsTableModel;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JTable;
import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;


public class ExcelImporter {

    private static Logger Log = Logger.getLogger(ExcelImporter.class);
    private File excelFile;
    private JTable observationsTable;

    public ExcelImporter(File excelFile, JTable observationsTable) {
        this.excelFile = excelFile;
        this.observationsTable = observationsTable;
    }

    public ExcelImporter(String excelFileName, JTable observationsTable) {
        this.excelFile = new File(excelFileName);
        this.observationsTable = observationsTable;
    }

     @SuppressWarnings("unchecked")
    public void importValues() throws Exception {
        InputStream inputStream = new FileInputStream(excelFile);
        HSSFWorkbook resultsBook = new HSSFWorkbook(inputStream);
        ObservationsTableModel tableModel =  (ObservationsTableModel)observationsTable.getModel();
        Map<String, Integer> headers = new HashMap<String,Integer>();
        HSSFSheet resultsSheet = resultsBook.getSheetAt(1);
        HSSFCell cellEntryNo = null;
        HSSFRow rowData = null;    
        rowData = resultsSheet.getRow(0);
        for (int col = 0; col < tableModel.getColumnCount(); col++) {
             String columnText = rowData.getCell(0).getStringCellValue();
             headers.put(columnText, col);
        }
        
    }
}
