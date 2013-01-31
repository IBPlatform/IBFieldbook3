/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ibfb.workbook.tests;

import ibfb.domain.core.GermplasmList;
import ibfb.workbook.api.GermplasmListReader;
import ibfb.workbook.core.GermplasmListReaderImpl;
import org.apache.log4j.Logger;
/**
 *
 * @author TMSANCHEZ
 */
public class TestGermplasmReader {
    private static Logger log = Logger.getLogger(TestGermplasmReader.class);

    public static void main(String[] args) throws Exception {
        // this are valid templates
        //String filename = "C:/tmsanchez/proyectos/cril/FIELDBOOK/IBFB/GermplasmLists/IBWB1-RiceGermplasmList.XLS";
        String filename = "C:/tmsanchez/proyectos/cril/FIELDBOOK/IBFB/GermplasmLists/IBFB3-Maize5233TRIAL07B_Noheader.XLS";
        // wrong file template
        //String filename = "C:/tmsanchez/proyectos/cril/FIELDBOOK/SAWYT_Fieldbook_Template-admin.xls";

        GermplasmListReader germplasmListReader = new GermplasmListReaderImpl();

        boolean validFile = germplasmListReader.isValidTemplate(filename);

        log.info("is valid file: " + validFile);

        if (validFile) {
            GermplasmList germplasmList = germplasmListReader.getGermPlasmList(filename);

            log.info("Germplasm info: " + germplasmList.toString());
            
            
        }
    }
}
