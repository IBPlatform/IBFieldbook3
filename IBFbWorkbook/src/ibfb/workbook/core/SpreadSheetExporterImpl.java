
package ibfb.workbook.core;

import ibfb.workbook.api.SpreadSheetExporter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigDecimal;

import java.util.List;


import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFRichTextString;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 *
 * @author TMSANCHEZ
 */
public class SpreadSheetExporterImpl implements SpreadSheetExporter {

    @Override
     @SuppressWarnings("unchecked")  
    public void exportExcel2007(String fileName, List<String> headerColumns, List<List<Object>> data) {
        Workbook workbook = new XSSFWorkbook();


        Sheet sheet = workbook.createSheet();

        // Put column headers
        Row headersRow = sheet.createRow(0);
        int colCount = 0;
        Cell cell = null;
        for (String header : headerColumns) {
            cell = headersRow.createCell(colCount);
            XSSFRichTextString cellHeader = new XSSFRichTextString(header);
            cell.setCellValue(cellHeader);
            colCount++;
        }

        // Then put all values
        int currentRow = 1;
        int currentCol = 0;
        int totalRows = data.size();

        Row rowData = null;
        Cell cellData = null;
        for (int row = 0; row < totalRows; row++) {
            rowData = sheet.createRow(currentRow);
            currentCol = 0;

            Object info = data.get(row);
            List<Object> colsInfo = (List<Object>) info;

            for (int col = 0; col < colsInfo.size(); col++) {
                cellData = rowData.createCell(currentCol);
                Object objectValue = colsInfo.get(col);
                if (objectValue != null) {
                    if (objectValue instanceof String) {
                        String stringValue = (String) objectValue;
                        XSSFRichTextString cellValue = new XSSFRichTextString(
                                stringValue);
                        cellData.setCellValue(cellValue);

                    } else {
                        Integer intValue = (Integer) colsInfo.get(col);
                        cellData.setCellValue(new BigDecimal(intValue).doubleValue());
                    }
                }
                currentCol++;
            }
            currentRow++;
        }

        try {
            FileOutputStream outputFile = new FileOutputStream(fileName);

            workbook.write(outputFile);
            outputFile.flush();
            outputFile.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }


    }

    @Override
     @SuppressWarnings("unchecked")  
    public void exportExcel2003(String fileName, List<String> headerColumns, List<List<Object>> data) {
        HSSFWorkbook workbook = new HSSFWorkbook();


        HSSFSheet sheet = workbook.createSheet();

        // Put column headers
        HSSFRow headersRow = sheet.createRow(0);
        int colCount = 0;
        HSSFCell cell = null;
        for (String header : headerColumns) {
            cell = headersRow.createCell(colCount);
            HSSFRichTextString cellHeader = new HSSFRichTextString(header);
            cell.setCellValue(cellHeader);
        }

        // Then put all values
        int currentRow = 1;
        int currentCol = 0;
        int totalRows = data.size();

        HSSFRow rowData = null;
        HSSFCell cellData = null;
        for (int row = 0; row < totalRows; row++) {
            rowData = sheet.createRow(currentRow);
            currentCol = 0;

            Object info = data.get(row);
            List<Object> colsInfo = (List<Object>) info;

            for (int col = 0; col < colsInfo.size(); col++) {
                cellData = rowData.createCell(currentCol);
                if (col == 0) {
                    Integer intValue = (Integer) colsInfo.get(col);
                    cellData.setCellValue(new BigDecimal(intValue).doubleValue());
                } else {
                    String stringValue = (String) colsInfo.get(col);
                    HSSFRichTextString cellValue = new HSSFRichTextString(
                            stringValue);
                    cellData.setCellValue(cellValue);
                }
                currentCol++;
            }
            currentRow++;
        }

        // finally write excel output to file
        try {
            FileOutputStream outputFile = new FileOutputStream(fileName);

            workbook.write(outputFile);
            outputFile.flush();
            outputFile.close();
        } catch (IOException ex) {
            System.out.println("ERROR "+ex);
        }

    }
}
