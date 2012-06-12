package ibfb.workbook.api;

import ibfb.domain.core.Factor;
import ibfb.domain.core.GermplasmList;
import java.util.List;

/**
 * The assignment of germplasm labels according to the table in Step 6 of IBFB-WorkFlowNo5.Doc
 * @author TMSANCHEZ
 */
public interface GermplasmAssigmentTool {
    // Constants for list column
    public static final String LIST_ID = "LISTID";
    public static final String LIST_NAME = "LIST NAME";
    public static final String GID = "GID";
    public static final String DESIG = "DESIG";
    public static final String DESIGNATION = "DESIGNATION";
    public static final String ENTRY_CD = "ENTRY_CODE";
    public static final String SOURCE = "SOURCE";
    public static final String GRPNAME = "GRPNAME";
    public static final String ENTRYID = "ENTRYID";
    public static final String LREDID = "LREDID";
    public static final String ENTRY_NUMBER = "ENTRY_NUMBER";
    public static final String ENTRY = "ENTRY";

    // Constants for Label property
    public static final String GERMPLASM_LIST = "GERMPLASM LIST";
    public static final String GERMPLASM_ID = "GERMPLASM ID";
    public static final String GERMPLASM_ENTRY = "GERMPLASM ENTRY";
    public static final String GERMPLASM = "GERMPLASM";    
    public static final String SEED_SOURCE = "SEED SOURCE";
    public static final String CROSS_HISTORY = "CROSS HISTORY";
    public static final String GERMPLASM_LIST_RECORD = "GERMPLASM LIST RECORD";
    
    // Constants for Scale property
    public static final String DBID = "DBID";
    public static final String DBCV = "DBCV";
    public static final String CODE = "CODE";
    public static final String NAME = "NAME";
    public static final String PEDIGREE_STRING = "PEDIGREE STRING";
    public static final String ENTRY_ID = "CODE";
    public static final String NUMBER = "NUMBER";

    /**
     * Return the column list of germplasm list looking for label an property
     * combination in factors
     * @param factorList List of factor to search a pair of property-scale
     * @return List of column names, or empty list if not matching values
     */
    public List<String> getColumnList(List<Factor> factorList);


    /**
     * Return a list of mapped columns
     * @param columnList List of columns to retrieve from germplasm list
     * @param germplasmList Germplasm list where columns are searched
     * @return A list containing a list of columns
     */
    public List<List<Object>> getMappedColumns(List<String> columnList, GermplasmList germplasmList);
    
}
