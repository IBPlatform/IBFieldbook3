package ibfb.workbook.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;

/**
 * Utility methods for an Excel file
 * @author TMSANCHEZ
 */
public class ExcelUtils {

    /**
     * Gets an integer value from a Cell
     * @param cellData
     * @return
     */
    public static Integer getIntValueFromCell(Cell cellData) {
        Integer result = 0;

        String cellValue = null;
        switch (cellData.getCellType()) {
            case Cell.CELL_TYPE_STRING:
                cellValue = cellData.getStringCellValue();
                break;
            case Cell.CELL_TYPE_NUMERIC:
                //cellValue = String.valueOf(getStringValueFromCell(cellData));
                Double value = cellData.getNumericCellValue();
                cellValue = String.valueOf(value.intValue());
                //cellValue = String.valueOf(getStringValueFromCell(cellData));
                break;
        }
        if (cellValue == null) {
            result = 0;
        } else {
            try {

                result = Integer.parseInt(cellValue);
            } catch (Exception e) {
                result = null;
            }
        }

        return result;
    }

    /**
     * Get a String value from a cell
     * @param cellData
     * @return
     */
    public static String getStringValueFromCell(Cell cellData) {
        String result = "";

        String cellValue = null;
        if (cellData == null) {
            return "";
        }
        switch (cellData.getCellType()) {
            case Cell.CELL_TYPE_STRING:
                cellValue = cellData.getStringCellValue();
                break;
            case Cell.CELL_TYPE_NUMERIC:
                cellValue = String.valueOf(cellData.getNumericCellValue());
                break;
        }
        if (cellValue == null) {
            result = "";
        } else {
            result = cellValue;
        }

        return result;

    }

    /**
     * Gets a Date value from an excel Cell
     * @param cellData
     * @return
     */
    public static Date getDateValueFromCell(Cell cellData) {
        Date result = null;

        Date cellValue = null;
        switch (cellData.getCellType()) {
            case Cell.CELL_TYPE_STRING:
                SimpleDateFormat sdf = new SimpleDateFormat("m/dd/yyyy");
                try {
                    cellValue = sdf.parse(cellData.getStringCellValue());
                } catch (ParseException ex) {
                    cellValue = null;
                }
                break;
            case Cell.CELL_TYPE_NUMERIC:
                Double d = cellData.getNumericCellValue();
                cellValue = new Date(d.longValue());

                break;
            default:
                cellValue = cellData.getDateCellValue();
        }
        if (cellValue == null) {
            result = null;
        } else {
            result = cellValue;
        }

        return result;

    }

    /**
     * More rows to read? , checks if are there more rows to read,
     * this mean that first cell in row has any value
     * @param rowData
     * @return
     */
    public static boolean isMoreRows(Row rowData, int columnNumber) {
        boolean result = true;
        Object value = null;

        if (rowData == null) {
            return false;
        }

        if (rowData.getCell(columnNumber) == null) {
            return false;
        }

        switch (rowData.getCell(columnNumber).getCellType()) {
            case Cell.CELL_TYPE_STRING:
                if (rowData.getCell(columnNumber).getStringCellValue() == null) {
                    return false;
                } else {
                    value = rowData.getCell(columnNumber).getStringCellValue();
                }
                if (value == null) {
                    result = false;
                } else if (((String) value).trim().isEmpty()) {
                    result = false;
                }
                break;
            case Cell.CELL_TYPE_NUMERIC:
                value = (Double)rowData.getCell(columnNumber).getNumericCellValue();
                if (value == null) {
                    result = false;
                } else {
                    result = true;
                }
                break;
            case Cell.CELL_TYPE_BLANK:
                result = false;
        }

        return result;
    }
}
