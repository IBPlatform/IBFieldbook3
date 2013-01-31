/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ibfb.workbook.api;

import java.util.List;

/**
 *
 * @author TMSANCHEZ
 */
public interface SpreadSheetExporter {

    public static final String ATTACHMENT_FILENAME = "attachment; filename=";
    public static final String CONTENT_DISPOSITION = "Content-disposition";
    public static final String APPLICATION_VND_MS_EXCEL = "application/vnd.ms-excel";

    /**
     * Create a new excel file in 2007 format
     * @param fileName File name with full path
     * @param headerColumns List with header columns
     * @param data all data for cells
     */
    public  void exportExcel2007(String fileName, List<String> headerColumns, List<List<Object>> data);

    /**
     * Create a new excel file in 2003 format
     * @param fileName File name with full path
     * @param headerColumns List with header columns
     * @param data all data for cells
     */
    public  void exportExcel2003(String fileName, List<String> headerColumns, List<List<Object>> data);


}
