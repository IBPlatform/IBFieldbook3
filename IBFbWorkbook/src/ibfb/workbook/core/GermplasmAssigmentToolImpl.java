/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ibfb.workbook.core;

import ibfb.domain.core.ListOfEntries;
import ibfb.domain.core.GermplasmList;
import ibfb.domain.core.Factor;
import ibfb.workbook.api.GermplasmAssigmentTool;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 *
 * @author TMSANCHEZ
 */
public class GermplasmAssigmentToolImpl implements GermplasmAssigmentTool {
    private List<Factor> localFactors;

    /**
     * Mapping table containing map criteria for each combination
     */
    private HashMap<String, String> mappingTable = new HashMap<String, String>();

    public GermplasmAssigmentToolImpl() {
        initMappingTable();
    }


    @Override
    public List<String> getColumnList(List<Factor> factorList) {
        this.localFactors = factorList;
        List<String> columnList = new ArrayList<String>();

        // iterate over factor list and look up for matching criteria
        for (Factor factor: factorList) {
            String searchCriteria = factor.getProperty() + factor.getScale();
            searchCriteria = getStringWithOutBlanks(searchCriteria);
            // search column combination in mapping table
            String columnName = mappingTable.get(searchCriteria);

            // if found then add to column list
            if (columnName != null) {
                System.out.println("criteria " + searchCriteria);
                //columnList.add(columnName);
                columnList.add(factor.getFactorName());
                //columnList.add(searchCriteria);
            }
        }

        return columnList;
    }
    
    public List<String> getColumnRules() {
        List<String> columnList = new ArrayList<String>();

        // iterate over factor list and look up for matching criteria
        for (Factor factor: localFactors) {
            String searchCriteria = factor.getProperty() + factor.getScale();
            searchCriteria = getStringWithOutBlanks(searchCriteria);
            // search column combination in mapping table
            String columnName = mappingTable.get(searchCriteria);

            // if found then add to column list
            if (columnName != null) {
                System.out.println("criteria " + searchCriteria);
                //columnList.add(columnName);
                //columnList.add(factor.getFactorName());
                columnList.add(searchCriteria);
            }
        }

        return columnList;
    }    

    @Override
    public List<List<Object>> getMappedColumns(List<String> columnList, GermplasmList germplasmList) {
        List<String> columnListRules = getColumnRules();

        List<List<Object>> mappedRows =  new ArrayList<List<Object>>();

        for (ListOfEntries entries: germplasmList.getListEntries()) {
            //List<Object> objectValues = entries.getColumnValues(columnList);
            //List<Object> objectValues = entries.getColumnValues(columnListRules);
            List<Object> objectValues = entries.getColumnValuesFromFactors(localFactors);
            mappedRows.add(objectValues);
        }

        return mappedRows;
    }

    /**
     * Initialize mapping table to define each combination of label and property
     * to retrieve selected column in the germplasm list.  In order reduce
     * criteria errors, all trailing blanks are removed from strings constants.
     * The matching table is defined in IBFB-WorkFlowNo5.Doc
     * List Column	Label PROPERTY	Label SCALE
     * LISTID	Germplasm List	DBID
     * List Name	Germplasm List	DBCV
     * GID	Germplasm ID	DBID
     * DESIG	Germplasm ID	DBCV
     * ENTRYCD	Germplasm Entry	Code
     * SOURCE	Seed Source	Name
     * GRPNAME	Cross History	Pedigree String
     * ENTRYID	Germplasm Entry	Entry ID
     * LREDID	Germplasm List Record	DBID
ENTRY	ENTRY NUMBER	GERMPLASM ENTRY	NUMBER
ENTRY CD	ENTRY CODE	GERMPLASM ENTRY	CODE
DESIG	ENTRY DESIGNATION	GERMPLASM ENTRY	DBCV
GID	GERMPLASM ID	GERMPLASM ENTRY	DBID

     */
    private void initMappingTable() {
        mappingTable.put(getStringWithOutBlanks(GERMPLASM_LIST + DBID), LIST_ID);
        mappingTable.put(getStringWithOutBlanks(GERMPLASM_LIST + DBCV), LIST_NAME);

        mappingTable.put(getStringWithOutBlanks(GERMPLASM_ID + DBID), GID);
        mappingTable.put(getStringWithOutBlanks(GERMPLASM_ID + DBCV), DESIG);

        mappingTable.put(getStringWithOutBlanks(GERMPLASM_ENTRY + DBCV), DESIG);
        mappingTable.put(getStringWithOutBlanks(GERMPLASM_ENTRY + NUMBER), ENTRY);
        mappingTable.put(getStringWithOutBlanks(GERMPLASM_ENTRY + CODE), ENTRY_CD);

        mappingTable.put(getStringWithOutBlanks(SEED_SOURCE + NAME), SOURCE);

        mappingTable.put(getStringWithOutBlanks(CROSS_HISTORY + PEDIGREE_STRING), GRPNAME);

        //mappingTable.put(getStringWithOutBlanks(GERMPLASM_ENTRY + ENTRY_ID), ENTRYID);

        mappingTable.put(getStringWithOutBlanks(GERMPLASM_LIST_RECORD+DBID), LREDID);

    }

    /**
     * Removes all blank spaces and convert to uppercase all characters\
     * in the string.
     * Example "Germ plasm list" will return "GERMPLASMLIST"
     * @param stringValue
     * @return
     */
    private String getStringWithOutBlanks(String stringValue) {
        String resultString = stringValue;

        // fisrt remove all blank spaces
        resultString = resultString.replaceAll(" ", "");

        // then change to uppercase
        resultString = resultString.toUpperCase();


        return resultString;
    }

   
}
