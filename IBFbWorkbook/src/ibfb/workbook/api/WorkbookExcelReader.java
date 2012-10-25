package ibfb.workbook.api;

import ibfb.domain.core.Workbook;

/**
 *
 * @author TMSANCHEZ
 */
public interface WorkbookExcelReader {
    //Define excel ranges names constants
    public static final String RANGE_NAME_STUDY= "STUDY";
    public static final String RANGE_NAME_CONDITION= "CONDITION";
    public static final String RANGE_NAME_FACTOR= "FACTOR";
    public static final String RANGE_NAME_VARIATE= "VARIATE";
    public static final String RANGE_NAME_CONSTANT= "CONSTANT";
    public static final String RANGE_NAME_ROWTAG= "ROWTAG";

    public static final int COL_NAME = 0;
    public static final int COL_DESCRIPTION = 1;
    public static final int COL_PROPERTY = 2;
    public static final int COL_SCALE = 3;
    public static final int COL_METHOD = 4;
    public static final int COL_DATATYPE = 5;
    public static final int COL_VALUE = 6;
    public static final int COL_LABEL = 7;
    /**
     * Prefix used in LABEL column for conditions
     */
    public static final String STUDY_PREFIX = "STUDY";
    public static final String LABEL_STUDY = "STUDY";


     public static final int WHEAT = 0;
      public static final int MAIZE = 1;
       public static final int OTHERCROPS = 2;
    
    /**
     * Get Workbook info from a excel file according to Workbook template
     * @param fileName The file name including full path FullName
     * @return List of String values according to excel
     */
    public Workbook getWorkbookData(String fileName) throws Exception;

    public boolean isValidTemplate(String fileName) throws Exception;
    
    public boolean isValidNurseryTemplate(String fileName) throws Exception;
   
    public int giveMeCrop() throws Exception;
    
    /**
     * Returns a text with the result of validation
     * @return String text or empty String if validation success
     */
    public String getValidationMessage();

}
