/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ibfb.workbook.tests;

import ibfb.domain.core.Workbook;
import ibfb.workbook.api.WorkbookExcelReader;
import ibfb.workbook.core.WorkbookExcelReaderImpl;
import java.io.File;
import java.io.FilenameFilter;
import org.apache.log4j.Logger;
import org.cimmyt.cril.ibwb.commongui.DialogUtil;



/**
 *
 * @author mtrulat
 */
public class TestValidTemplate {
    private static Logger log = Logger.getLogger(TestValidTemplate.class);

    public static void main(String[] args) throws Exception {

       String directory = "/Users/spiffy/Documents/CIMMYT2011/IBFieldbook1/Examples/Templates/";
       String[] XlsFiles = getTemplateFilename(directory);
      
       String valid = "";
       String invalid = "";
         
       for (String filename : XlsFiles) {
                      
            WorkbookExcelReader templateFile = new WorkbookExcelReaderImpl();
            boolean isValid = templateFile.isValidTemplate(directory+filename);
            
            if (isValid) {
               Workbook workbook = templateFile.getWorkbookData(directory+filename);
              
               if (workbook.isValidTemplate()) {
                    log.info("---------- VALID TEMPLATE: "+filename);
                    valid+="\n\t"+filename;
               } else {
                    log.info("------------------- INVALID TEMPLATE: "+filename);
                    invalid+="\n\t"+filename;
               }
            }
         
       
       }
       
       DialogUtil.display("VALID TEMPLATES : "+valid+"\n\n INVALID TEMPLATES : "+invalid);
       
                      
    }
    
    private static String[] getTemplateFilename(String directory) {
    
        File dir = new File(directory);
        String[] templates = null; 
       
        FilenameFilter filterXLS = new FilenameFilter() {
            @Override
             public boolean accept(File dir, String name) {
                return name.endsWith(".xls");
             }
        };
        
        templates = dir.list(filterXLS);
        
               
        return templates;
    }
    
}
