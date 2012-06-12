/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ibfb.workbook.tests;

import ibfb.domain.core.Factor;
import ibfb.domain.core.Workbook;
import ibfb.workbook.api.WorkbookExcelReader;
import ibfb.workbook.core.WorkbookExcelReaderImpl;
import org.apache.log4j.Logger;

/**
 *
 * @author TMSANCHEZ
 */
public class TestWorkbookReader {
     private static Logger log = Logger.getLogger(TestWorkbookReader.class);

    public static void main(String[] args) throws Exception {
        // all of this are valid template
        //String filename = "C:/tmsanchez/sistemasJava/netbeans/netbeanscril/demosplatform/WorkbookReader/templatedemo.xls";
        //String filename = "C:/tmsanchez/proyectos/cril/FIELDBOOK/13SAWYT_Fieldbook_Template_29.xls";
        //String filename = "C:/tmsanchez/proyectos/cril/FIELDBOOK/13SAWYT_Fieldbook_Template_102.xls";
        //String filename = "C:/tmsanchez/proyectos/cril/FIELDBOOK/Sorghum-MARS-template.xls";
        //String filename = "C:/tmsanchez/proyectos/cril/FIELDBOOK/SAWYT_Fieldbook_Template-admin.xls";

        //String filename = "C:/tmsanchez/proyectos/cril/FIELDBOOK/databases/Databases28Abril2011/Database/IVIS/TEMPLATES/Cowpea_MST_Template.xls";

        // this is a wrong template file
        //String filename = "C:/tmsanchez/proyectos/cril/FIELDBOOK/IBFB/GermplasmLists/IBWB1-RiceGermplasmList.XLS";

        //String filename = "C:/templates/WHEAT-MET-TemplateV03 - Copy.xls";

        String filename = "C:/traitTemplates/IBFB5-Wheat MultiFactorialMETwith Labels.xls";
        
        WorkbookExcelReader excelReader = new WorkbookExcelReaderImpl();

        boolean isValid = excelReader.isValidTemplate(filename);

        log.info("is valid file: " + isValid);


        if (isValid) {
            Workbook workbook = excelReader.getWorkbookData(filename);
            log.info("study info: " + workbook.getStudy().toString());
            for (Factor factor:workbook.getFactors()) {
                log.info("Factor: " + factor.toString());
            }
            for (Factor factor:workbook.getOtherFactors()) {
                log.info("OtherFactor: " + factor.toString());
            }
            
            for (String header: workbook.getMeasurementHeaders()) {
                log.info("Header " + header);
            }
            
            for (String otherFactor: workbook.getChildFactors().keySet()) {
                log.info("Other factor.................-----------" + otherFactor);
                for (Factor factor: workbook.getChildFactors().get(otherFactor)) {
                    log.info("            ......" + factor.getFactorName());
                }
            }
            
                log.info("childs for FERTLE");
            for (Factor factor: workbook.getChildFactors("FERTLE")) {
                    log.info("            ......" + factor.getFactorName());
                }
        }
    }
}
