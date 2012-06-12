package ibfb.domain.core;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author TMSANCHEZ
 */
public class ListOfEntries {
        // Constants for list column
    public static final String LIST_ID = "LISTID";
    public static final String LIST_NAME = "LIST NAME";
    public static final String GID = "GID";
    public static final String DESIG = "DESIG";
    public static final String DESIGNATION = "DESIGNATION";
    public static final String ENTRY_CD = "ENTRY CD";
    public static final String ENTRY_CODE = "ENTRY CODE";    
    public static final String SOURCE = "SOURCE";
    public static final String GRPNAME = "GRPNAME";
    public static final String ENTRYID = "ENTRYID";
    public static final String LREDID = "LREDID";
    public static final String ENTRY_NUMBER = "ENTRY_NUMBER";
    public static final String ENTRY = "ENTRY";   
    
    
    public static final String GERMPLASM_ENTRY_NUMBER = "GERMPLASMENTRYNUMBER";
    public static final String GERMPLASM_ENTRY_CODE = "GERMPLASMENTRYCODE";
    public static final String GERMPLASM_ID_DBCV = "GERMPLASMIDDBCV";
    public static final String GERMPLASM_ID_DBID = "GERMPLASMIDDBID";
    public static final String SEED_SOURCE_NAME = "SEEDSOURCENAME";
    public static final String CROSS_HISTORY_PEDIGREE_STRING = "CROSSHISTORYPEDIGREESTRING";
    

    private Integer gid;
    private String entryCode;
    private String designation;
    private String cross;
    private String source;
    private String uniqueId;
    private Integer entryId;
    private Integer number;

    public ListOfEntries() {
    }


    public ListOfEntries(Integer gid, String entryCode, String designation, String cross, String source, String uniqueId, Integer entryId) {
        this.gid = gid;
        this.entryCode = entryCode;
        this.designation = designation;
        this.cross = cross;
        this.source = source;
        this.uniqueId = uniqueId;
        this.entryId = entryId;
    }

    /**
     * Get the list of values according to combination of PROPERTY + SCALE
     * It check if columnName matches with predefined values, if match OK
     * then assign the value from entries properties (gid, entry code, etc)
     * @param columnList Column headers list with header to display
     * @return a list of values for each column
     */
    public List<Object> getColumnValues(List<String> columnList) {
       List<Object> columnValues = new ArrayList<Object>();
       for (String columnName: columnList) {
           if (columnName.equals(GERMPLASM_ENTRY_NUMBER)) {
             columnValues.add(number);  
           } else  if (columnName.equals(GERMPLASM_ENTRY_CODE)) {
               columnValues.add(entryCode);
           } else  if (columnName.equals(GERMPLASM_ID_DBCV)) {
               columnValues.add(designation);
           } else  if (columnName.equals(GERMPLASM_ID_DBID)) {
               columnValues.add(gid);
           } else  if (columnName.equals(SEED_SOURCE_NAME)) {
               columnValues.add(source);
           } else  if (columnName.equals(CROSS_HISTORY_PEDIGREE_STRING)) {
               columnValues.add(cross);
           }
           
       }
       return columnValues;
    }

    public String getCross() {
        return cross;
    }

    public void setCross(String cross) {
        this.cross = cross;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public String getEntryCode() {
        return entryCode;
    }

    public void setEntryCode(String entryCode) {
        this.entryCode = entryCode;
    }

    public Integer getGid() {
        return gid;
    }

    public void setGid(Integer gid) {
        this.gid = gid;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getUniqueId() {
        return uniqueId;
    }

    public void setUniqueId(String uniqueId) {
        this.uniqueId = uniqueId;
    }

    public Integer getEntryId() {
        return entryId;
    }

    public void setEntryId(Integer entryId) {
        this.entryId = entryId;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }


    
    @Override
    public String toString() {
        return "ListEntry{" + "gid=" + gid + "entryCode=" + entryCode + "designation=" + designation + "cross=" + cross + "source=" + source + "uniqueId=" + uniqueId + "entryId=" + entryId + '}';
    }

    
}
