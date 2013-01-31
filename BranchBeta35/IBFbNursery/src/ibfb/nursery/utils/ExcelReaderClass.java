package ibfb.nursery.utils;

import ibfb.domain.core.Workbook;
import ibfb.workbook.api.WorkbookExcelReader;
import ibfb.workbook.core.WorkbookExcelReaderImpl;
import javax.swing.table.DefaultTableCellRenderer;


public class ExcelReaderClass {

    private WorkbookExcelReader myExcelReader = new WorkbookExcelReaderImpl();
    private Workbook myWorkbook = new Workbook();  
    private DefaultTableCellRenderer tcr = new DefaultTableCellRenderer();
    private String file;

    public String getFile() {
        return file;
    }

    public void setFile(String file) {
        this.file = file;
    }

    public Workbook getMyWorkbook() {
        return myWorkbook;
    }

    public void setMyWorkbook(Workbook myWorkbook) {
        this.myWorkbook = myWorkbook;
    }

    public void readExcelWorkbookTemplate() {
        try {
            myWorkbook = myExcelReader.getWorkbookData(this.file);
        } catch (Exception ex) {
            ex.printStackTrace();
            System.out.println("ERROR AL LEER EXCEL A: " + ex);
        }
    }

    
    public void readExcelWorkbookTemplateStudyConditions() {
        try {
            myWorkbook = myExcelReader.getWorkbookData(file);
        } catch (Exception ex) {
            System.out.println("ERROR AL LEER EXCEL B: " + ex);
        }

    }

  
}
