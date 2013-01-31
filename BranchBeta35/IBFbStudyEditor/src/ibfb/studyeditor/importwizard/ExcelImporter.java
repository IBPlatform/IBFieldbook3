
package ibfb.studyeditor.importwizard;

import ibfb.domain.core.Condition;
import ibfb.domain.core.Variate;
import ibfb.domain.core.Workbook;
import ibfb.studyeditor.core.ExcelReaderClass;
import ibfb.studyeditor.core.model.ObservationsTableModel;
import ibfb.workbook.api.WorkbookExcelReader;
import ibfb.workbook.core.WorkbookExcelReaderImpl;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.swing.JTable;
import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.cimmyt.cril.ibwb.commongui.DialogUtil;


public class ExcelImporter {

    private static Logger Log = Logger.getLogger(ExcelImporter.class);
    private File excelFile;
    private JTable observationsTable;
    private Workbook myWorbook;

    public ExcelImporter(File excelFile, JTable observationsTable) {
        this.excelFile = excelFile;
        this.observationsTable = observationsTable;
    }

    public ExcelImporter(String excelFileName, JTable observationsTable) {
        this.excelFile = new File(excelFileName);
        this.observationsTable = observationsTable;
    }

    public void importValues() throws Exception {
        Log.info("Excel file read BEGIN");
        Log.info("Opening file...");
        InputStream inputStream = new FileInputStream(excelFile);
        HSSFWorkbook resultsBook = new HSSFWorkbook(inputStream);
        Log.info("Number of sheets in book" + resultsBook.getNumberOfSheets());

        ObservationsTableModel tableModel =  (ObservationsTableModel)observationsTable.getModel();
        
        int totalRowCount = tableModel.getValues().size();
        
        // to store headers text and column number
        Map<String, Integer> headers = new HashMap<String,Integer>();
        


        HSSFSheet resultsSheet = resultsBook.getSheetAt(1);
        HSSFCell cellEntryNo = null;
        HSSFRow rowData = null;
        
        rowData = resultsSheet.getRow(0);
        for (int col = 0; col < tableModel.getColumnCount(); col++) {
             String columnText = rowData.getCell(0).getStringCellValue();
             headers.put(columnText, col);
        }
        

        // iterate each row in excel file to retrieve each cell value
//        for (int rowIndex = 1; rowIndex < (totalRowCount+1);rowIndex++) {
//            rowData = resultsSheet.getRow(rowIndex);
//
//            // gets the entry number from excel
//            cellEntryNo = rowData.getCell(0);
//            entryNo = Integer.parseInt(cellEntryNo.getStringCellValue());
//            
//
//            // get all cell values
//            for (int colIndex = 0; colIndex < numOfParameters; colIndex++) {
//                HSSFCell cellValue = rowData.getCell(colIndex);
//                // If cell has some value then to assign the result to the study
//
//                if (cellValue != null) {
//                    String result = "";
//                    // Gets the cell result value depending of cell type
//                    switch (cellValue.getCellType()) {
//                        case HSSFCell.CELL_TYPE_NUMERIC:
//                            result = String.valueOf(cellValue.getNumericCellValue());
//                            break;
//                        case HSSFCell.CELL_TYPE_STRING:
//                            result = cellValue.getStringCellValue();
//                            break;
//                    }
//
////                    // get the column name from headers
////                    String columnName = columnHeaders[colIndex];
////                    // lookup the parameter for get its ID
////                    StudyTemplateParams param = storedParams.get(columnName);
////                    if (param != null) {
////                        // then update the result in database
////                        SampleDetResult sampleDetResult = sampleTrackingServices.getSampleDetResultBySampleDetailIdAndTemplateParamId(
////                                sampleDetail.getStudysampleid(), param.getTemplateparamid());
////                        if (sampleDetResult != null) {
////                            sampleDetResult.setResult(result);
////                            sampleTrackingServices.updateSampleDetResult(sampleDetResult);
////                        }
////                    }
//
//                }
//            }
//        }

        Log.info("Excel file read END");
    }
    
    
    public void LoadData(){
        
        ExcelReaderClass myExcelReader = new ExcelReaderClass();        
        WorkbookExcelReader validateExcelReader = new WorkbookExcelReaderImpl();
        
        myExcelReader.setFile(excelFile.toString());
        
        myExcelReader.readExcelWorkbookTemplateStudyConditions();
        myWorbook= myExcelReader.getMyWorkbook();
        
        
        
        boolean isValidFile = false;
        
        
        System.out.println("EXCELFILE: "+excelFile.toString());

 int currentTrial=0;

   
          
          
          List<Condition> conditionsEnArchivo=myExcelReader.getMyWorkbook().getStudyConditions();
          List<Variate> TraitsEnArchivo=myExcelReader.getMyWorkbook().getVariates();
          
          
          for (Condition condition : conditionsEnArchivo) {
              
           if (condition.getProperty().equals("TRIAL INSTANCE")){
               currentTrial=Integer.parseInt(condition.getValue().toString());
               break;
           }
          }
          
        System.out.println("CURRENT TRIAL= "+currentTrial);    
          
        ArrayList traits=new ArrayList();
       
        
        int colEntry = 0;
        int colPlot=0;
        int colGID=0;
        
        
          
        
        
        ObservationsTableModel model = (ObservationsTableModel) observationsTable.getModel();
      
          
         // for (int col = 0; col < model.getColumnCount(); col++) {
        
            colEntry = model.getHeaderIndex(ObservationsTableModel.ENTRY);
                if (colEntry != -1) {
                  System.out.println("NO HAY COLUMNA ENTRY");
                    return;
                }
               
                 colPlot = model.getHeaderIndex(ObservationsTableModel.PLOT);
                if (colPlot != -1) {
                    System.out.println("NO HAY COLUMNA PLOT");
                    return;
                }
                
                          colGID = model.getHeaderIndex(ObservationsTableModel.GID);
                if (colGID != -1) {
                    System.out.println("NO HAY COLUMNA GID");
                    return;
                }  
          
    //}
    
       
    }  
}
