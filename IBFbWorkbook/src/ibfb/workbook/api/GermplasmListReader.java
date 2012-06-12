package ibfb.workbook.api;

import ibfb.domain.core.GermplasmList;



/**
 * Reader for Germplasm lists
 * @author TMSANCHEZ
 */
public interface GermplasmListReader {
    public static final String LABEL_LIST_ID = "LIST ID";
    public static final String LABEL_LIST_NAME = "LIST NAME";
    public static final String LABEL_LIST_DATE = "LIST DATE";
    public static final String LABEL_LIST_TYPE = "LIST TYPE";
    public static final String LABEL_LIST_TITLE = "LIST TITLE";

    public static final String HEADER_GID = "GID";
    public static final String HEADER_ENTRY_CD = "ENTRY CD";
    public static final String HEADER_ENTRY_CODE = "ENTRY CODE";    
    public static final String HEADER_DESIGNATION = "DESIG";
    public static final String HEADER_CROSS = "CROSS";
    public static final String HEADER_SOURCE = "SOURCE";
    public static final String HEADER_UNIQUE_ID = "UNIQUE ID";
    public static final String HEADER_ENTRY_ID = "ENTRY";

    // Assume that entry values starts at 7 (8)
    public static final int ROW_HEADER_INDEX = 7;

    public static final int COLUMN_GID = 0;
    public static final int COLUMN_ENTRY_CODE = 1;
    public static final int COLUMN_DESIGNATION = 2;
    public static final int COLUMN_CROSS = 3;
    public static final int COLUMN_SOURCE = 4;
    public static final int COLUMN_UNIQUE_ID = 5;
    public static final int COLUMN_ENTRY_ID = 6;
    
    public static final int MAX_ROW = 2000;


     /**
     * Get Germplasm info from a excel file according to Germplasm template
     * @param fileName The file name including full path FullName
     * @return GermplasmList object with all entries
     */
    public GermplasmList getGermPlasmList(String fileName) throws Exception;

    public boolean isValidTemplate(String fileName) throws Exception;

    public boolean isValidCrossScript(String fileName) throws Exception;

     /**
     * Get Germplasm info from database according to Germplasm list id
     * @param listid ID for germplasm list
     * @return GermplasmList object with all entries
     */
    public GermplasmList getGermPlasmListFromDB(Integer listid);


    
}
