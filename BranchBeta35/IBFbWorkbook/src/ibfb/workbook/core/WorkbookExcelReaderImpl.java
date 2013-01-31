package ibfb.workbook.core;

import ibfb.domain.core.*;
import ibfb.domain.core.Workbook;
import ibfb.workbook.api.WorkbookExcelReader;
import ibfb.workbook.utils.ExcelUtils;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.Logger;
import org.apache.poi.hssf.util.AreaReference;
import org.apache.poi.ss.usermodel.*;
import org.cimmyt.cril.ibwb.api.AppServicesProxy;
import org.cimmyt.cril.ibwb.domain.constants.TypeDB;

/**
 *
 * @author TMSANCHEZ
 */
public class WorkbookExcelReaderImpl implements WorkbookExcelReader {

    /**
     * Result of validation
     */
    private String validationResult;
    private static Logger log = Logger.getLogger(WorkbookExcelReaderImpl.class);

    @Override
    public Workbook getWorkbookData(String fileName) throws Exception {
        Workbook workbook = new Workbook();
        log.info("Excel file read BEGIN");
        log.info("Opening file...");
        InputStream inputStream = new FileInputStream(fileName);
        org.apache.poi.ss.usermodel.Workbook excelBook = WorkbookFactory.create(inputStream);
        log.info("Number of sheets in book" + excelBook.getNumberOfSheets());
        fillWoorkbook(workbook, excelBook);

        log.info("Excel file read END");

        //this.validationResult = workbook.getValidationMessage();

        return workbook;


    }

    @Override
    public boolean isValidTemplate(String fileName) throws Exception {
        boolean valid = false;

        log.info("Validating Excel file read BEGIN");
        //     log.info("Opening file...");
        InputStream inputStream = new FileInputStream(fileName);
        org.apache.poi.ss.usermodel.Workbook excelBook = WorkbookFactory.create(inputStream);
        //      log.info("Number of sheets in book" + excelBook.getNumberOfSheets());

        // assume values are in B colum (index 1)
        int labelColNumber = 0;
        int listRowIndex = 0;

        // get sheet where is located list data
        Sheet sheet = excelBook.getSheetAt(0);

        Cell cellData = null;
        Row rowData = null;

        rowData = sheet.getRow(listRowIndex);
        cellData = rowData.getCell(labelColNumber);
        if (ExcelUtils.getStringValueFromCell(cellData).startsWith(LABEL_STUDY)) {
            valid = true;
            Workbook workbookTemp = getWorkbookData(fileName);
            if (!workbookTemp.isValidTemplate()) {
                log.info("------------------- INVALID TEMPLATE: " + fileName);
                this.validationResult = workbookTemp.getValidationMessage();
                valid = false;
            }
        } else {
            this.validationResult = "Template must start with STUDY in cell A1";
        }



        return valid;
    }

    @Override
    public int giveMeCrop() throws Exception {

        int theCrop = 0;  //0=Wheat   1=maize   2=other crops

        TypeDB tipo = AppServicesProxy.getDefault().appServices().getTypeDB();


        switch (tipo.getType()) {
            case 0:
                theCrop = WHEAT;
                break;
            case 1:
                theCrop = MAIZE;
                break;
            case 2:
                theCrop = OTHERCROPS;
                break;
            default:
                theCrop = OTHERCROPS;
                break;

        }

       //  System.out.println("TENEMOS DB: " + tipo.getNombre());
       // System.out.println("TENEMOS DB type: " + tipo.getType());

        return theCrop;
    }

    @Override
    public boolean isValidNurseryTemplate(String fileName) throws Exception {
        boolean valid = false;

        log.info("Validating Excel Nursery file read BEGIN");
        InputStream inputStream = new FileInputStream(fileName);
        org.apache.poi.ss.usermodel.Workbook excelBook = WorkbookFactory.create(inputStream);

        // assume values are in B colum (index 1)
        int labelColNumber = 0;
        int listRowIndex = 0;

        // get sheet where is located list data
        Sheet sheet = excelBook.getSheetAt(0);

        Cell cellData = null;
        Row rowData = null;

        rowData = sheet.getRow(listRowIndex);
        cellData = rowData.getCell(labelColNumber);
        if (ExcelUtils.getStringValueFromCell(cellData).startsWith(LABEL_STUDY)) {


            rowData = sheet.getRow(6);
            cellData = rowData.getCell(1);
            if (ExcelUtils.getStringValueFromCell(cellData).equals("N")) {
                valid = true;
            }





            if (!getWorkbookData(fileName).isValidNurseryTemplate()) {
                log.info("------------------- INVALID TEMPLATE: " + fileName);
                valid = false;
            }
        }



        return valid;
    }

    /**
     * Fill Workbook object with current excel file
     *
     * @param workbook
     * @param excelBook
     */
    private void fillWoorkbook(Workbook workbook, org.apache.poi.ss.usermodel.Workbook excelBook) {
        Sheet resultsSheet = excelBook.getSheetAt(0);
        Cell cellEntryNo = null;
        Row rowData = null;

        fillStudyData(workbook, excelBook);
        fillConditions(workbook, excelBook);
        fillFactors(workbook, excelBook);
        fillConstants(workbook, excelBook);
        fillVariates(workbook, excelBook);

    }

    /**
     *
     * @param workbook
     * @param excelBook
     */
    private void fillStudyData(Workbook workbook, org.apache.poi.ss.usermodel.Workbook excelBook) {
        AreaReference areaReference = getAreaReferenceForRangeName(RANGE_NAME_STUDY, excelBook);
        int studyDataColNumber = 1;
        int studyRowIndex = areaReference.getFirstCell().getRow();
        Study study = new Study();

        // get sheet where is located Study range name
        Sheet sheet = getSheetForRangeName(excelBook, areaReference);

        Cell cellData = null;
        Row rowData = null;

        rowData = sheet.getRow(studyRowIndex);
        cellData = rowData.getCell(studyDataColNumber);
        study.setStudy(getStringValueFromCell(cellData));

        studyRowIndex++;
        rowData = sheet.getRow(studyRowIndex);
        cellData = rowData.getCell(studyDataColNumber);
        study.setTitle(getStringValueFromCell(cellData));

        studyRowIndex++;
        rowData = sheet.getRow(studyRowIndex);
        cellData = rowData.getCell(studyDataColNumber);
        study.setPmkey(getStringValueFromCell(cellData));

        studyRowIndex++;
        rowData = sheet.getRow(studyRowIndex);
        cellData = rowData.getCell(studyDataColNumber);
        study.setObjective(getStringValueFromCell(cellData));

        studyRowIndex++;
        rowData = sheet.getRow(studyRowIndex);
        cellData = rowData.getCell(studyDataColNumber);
        //study.setStarDate(getIntValueFromCell(cellData));

        studyRowIndex++;
        rowData = sheet.getRow(studyRowIndex);
        cellData = rowData.getCell(studyDataColNumber);
        //study.setEndDate(getIntValueFromCell(cellData));

        studyRowIndex++;
        rowData = sheet.getRow(studyRowIndex);
        cellData = rowData.getCell(studyDataColNumber);
        study.setStudyType(getStringValueFromCell(cellData));

        //    log.info("Data for Study: " + study.toString());

        workbook.setStudy(study);
    }

    /**
     * fills conditions items for study
     *
     * @param workbook
     * @param excelBook
     */
    private void fillConditions(Workbook workbook, org.apache.poi.ss.usermodel.Workbook excelBook) {
        AreaReference areaReference = getAreaReferenceForRangeName(RANGE_NAME_CONDITION, excelBook);
        int rowIndex = areaReference.getFirstCell().getRow();
        List<Condition> studyConditions = new ArrayList<Condition>();
        List<Condition> conditions = new ArrayList<Condition>();
        boolean moreRowsToRead = true;

        // get sheet where is located CONDITION range name
        Sheet sheet = getSheetForRangeName(excelBook, areaReference);
        Cell cellData = null;
        Row rowData = null;

        while (moreRowsToRead) {
            rowIndex++;
            rowData = sheet.getRow(rowIndex);

            moreRowsToRead = isMoreRows(rowData);

            if (!moreRowsToRead) {
                break;
            }

            // create a new CONDITION
            Condition condition = new Condition();

            // assign data
            cellData = rowData.getCell(COL_NAME);
            condition.setConditionName(getStringValueFromCell(cellData));

            cellData = rowData.getCell(COL_DESCRIPTION);
            condition.setDescription(getStringValueFromCell(cellData));

            cellData = rowData.getCell(COL_PROPERTY);
            condition.setProperty(getStringValueFromCell(cellData));

            cellData = rowData.getCell(COL_SCALE);
            condition.setScale(getStringValueFromCell(cellData));

            cellData = rowData.getCell(COL_METHOD);
            condition.setMethod(getStringValueFromCell(cellData));

            cellData = rowData.getCell(COL_DATATYPE);
            condition.setDataType(getStringValueFromCell(cellData));

            cellData = rowData.getCell(COL_VALUE);
            if (cellData != null) {
                condition.setValue(getStringValueFromCell(cellData));
            }

            cellData = rowData.getCell(COL_LABEL);
            condition.setLabel(getStringValueFromCell(cellData));

            // log.info("Data for condition: " + condition.toString());

            // add readed condition to list
            if (condition.getLabel().toUpperCase().startsWith(STUDY_PREFIX)) {
                studyConditions.add(condition);
            } else {
                conditions.add(condition);
            }




        }

        // Show number of item conditions for Study and Trials
        log.info("Total study conditions found: " + studyConditions.size());
        log.info("Total conditions found: " + conditions.size());

        //set conditions lists to workbook
        workbook.setStudyConditions(studyConditions);
        workbook.setConditions(conditions);
    }

    /**
     * fills Factors items for study
     *
     * @param workbook
     * @param excelBook
     */
    private void fillFactors(Workbook workbook, org.apache.poi.ss.usermodel.Workbook excelBook) {
        AreaReference areaReference = getAreaReferenceForRangeName(RANGE_NAME_FACTOR, excelBook);
        int rowIndex = areaReference.getFirstCell().getRow();
        List<Factor> factors = new ArrayList<Factor>();
        boolean moreRowsToRead = true;

        // get sheet where is located FACTOR range name
        Sheet sheet = getSheetForRangeName(excelBook, areaReference);
        Cell cellData = null;
        Row rowData = null;

        while (moreRowsToRead) {
            rowIndex++;
            rowData = sheet.getRow(rowIndex);

            moreRowsToRead = isMoreRows(rowData);

            if (!moreRowsToRead) {
                break;
            }

            // create a new FACTOR
            Factor factor = new Factor();

            // assign data
            cellData = rowData.getCell(COL_NAME);
            factor.setFactorName(getStringValueFromCell(cellData));

            cellData = rowData.getCell(COL_DESCRIPTION);
            factor.setDescription(getStringValueFromCell(cellData));

            cellData = rowData.getCell(COL_PROPERTY);
            factor.setProperty(getStringValueFromCell(cellData));

            cellData = rowData.getCell(COL_SCALE);
            factor.setScale(getStringValueFromCell(cellData));

            cellData = rowData.getCell(COL_METHOD);
            factor.setMethod(getStringValueFromCell(cellData));

            cellData = rowData.getCell(COL_DATATYPE);
            factor.setDataType(getStringValueFromCell(cellData));

            cellData = rowData.getCell(COL_LABEL);
            factor.setLabel(getStringValueFromCell(cellData));

            //      log.info("Data for factor: " + factor.toString());

            // add readed condition to list
            factors.add(factor);


        }

        log.info("Total factors found: " + factors.size());
        workbook.setFactors(factors);
    }

    /**
     *
     * @param workbook
     * @param excelBook
     */
    private void fillConstants(Workbook workbook, org.apache.poi.ss.usermodel.Workbook excelBook) {
        AreaReference areaReference = getAreaReferenceForRangeName(RANGE_NAME_CONSTANT, excelBook);
        int rowIndex = areaReference.getFirstCell().getRow();
        List<Constant> constants = new ArrayList<Constant>();
        boolean moreRowsToRead = true;

        // get sheet where is located FACTOR range name
        Sheet sheet = getSheetForRangeName(excelBook, areaReference);
        Cell cellData = null;
        Row rowData = null;

        while (moreRowsToRead) {
            rowIndex++;
            rowData = sheet.getRow(rowIndex);

            moreRowsToRead = isMoreRows(rowData);

            if (!moreRowsToRead) {
                break;
            }

            // create a new FACTOR
            Constant constant = new Constant();

            // assign data
            cellData = rowData.getCell(COL_NAME);
            constant.setConstantName(getStringValueFromCell(cellData));

            cellData = rowData.getCell(COL_DESCRIPTION);
            constant.setDescription(getStringValueFromCell(cellData));

            cellData = rowData.getCell(COL_PROPERTY);
            constant.setProperty(getStringValueFromCell(cellData));

            cellData = rowData.getCell(COL_SCALE);
            constant.setScale(getStringValueFromCell(cellData));

            cellData = rowData.getCell(COL_METHOD);
            constant.setMethod(getStringValueFromCell(cellData));

            cellData = rowData.getCell(COL_DATATYPE);
            constant.setDataType(getStringValueFromCell(cellData));

            cellData = rowData.getCell(COL_VALUE);
            if (cellData != null) {
                constant.setValue(getStringValueFromCell(cellData));
            }
            log.info("Data for Constant: " + constant.toString());
            // add readed condition to list
            constants.add(constant);


        }


        workbook.setConstants(constants);
    }

    /**
     *
     * @param workbook
     * @param excelBook
     */
    private void fillVariates(Workbook workbook, org.apache.poi.ss.usermodel.Workbook excelBook) {
        AreaReference areaReference = getAreaReferenceForRangeName(RANGE_NAME_VARIATE, excelBook);
        int rowIndex = areaReference.getFirstCell().getRow();
        List<Variate> variates = new ArrayList<Variate>();
        boolean moreRowsToRead = true;

        // get sheet where is located VARIATES range name
        Sheet sheet = getSheetForRangeName(excelBook, areaReference);
        Cell cellData = null;
        Row rowData = null;

        while (moreRowsToRead) {
            rowIndex++;
            rowData = sheet.getRow(rowIndex);

            moreRowsToRead = isMoreRows(rowData);

            if (!moreRowsToRead) {
                break;
            }

            // create a new FACTOR
            Variate variate = new Variate();

            // assign data
            cellData = rowData.getCell(COL_NAME);
            variate.setVariateName(getStringValueFromCell(cellData));

            cellData = rowData.getCell(COL_DESCRIPTION);
            variate.setDescription(getStringValueFromCell(cellData));

            cellData = rowData.getCell(COL_PROPERTY);
            variate.setProperty(getStringValueFromCell(cellData));

            cellData = rowData.getCell(COL_SCALE);
            variate.setScale(getStringValueFromCell(cellData));

            cellData = rowData.getCell(COL_METHOD);
            variate.setMethod(getStringValueFromCell(cellData));

            cellData = rowData.getCell(COL_DATATYPE);
            variate.setDataType(getStringValueFromCell(cellData));

            //       log.info("Data for Variate: " + variate.toString());
            // add readed condition to list
            variates.add(variate);


        }


        workbook.setVariates(variates);
    }

    /**
     * More rows to read? , checks if are there more rows to read, this mean
     * that first cell in row has any value
     *
     * @param rowData
     * @return
     */
    private boolean isMoreRows(Row rowData) {
        boolean result = true;

        if (rowData == null) {
            return false;
        }

        if (rowData.getCell(COL_NAME) == null) {
            return false;
        }
        if (rowData.getCell(COL_NAME).getStringCellValue() == null) {
            return false;
        }
        String value = rowData.getCell(COL_NAME).getStringCellValue();

        if (value == null) {
            result = false;
        } else if (value.trim().isEmpty()) {
            result = false;
        }

        return result;
    }

    /**
     * Return the Sheet where is locate the range indicated in area reference
     *
     * @param excelBook
     * @param areaReference
     * @return
     */
    private Sheet getSheetForRangeName(org.apache.poi.ss.usermodel.Workbook excelBook, AreaReference areaReference) {
        String sheetName = areaReference.getFirstCell().getSheetName();
        Sheet sheet = excelBook.getSheet(sheetName);
        return sheet;
    }

    /**
     * Returns the row number for specified range name in excel file
     *
     * @param rangeName
     * @return
     */
    private AreaReference getAreaReferenceForRangeName(String rangeName, org.apache.poi.ss.usermodel.Workbook excelBook) {
        // first retrieve index in all ranges names
        int namedCellIndex = excelBook.getNameIndex(rangeName);
        //    log.info("Range " + rangeName + " has indext " + namedCellIndex);
        // after create the name reference
        Name namedCellRange = excelBook.getNameAt(namedCellIndex);

        // then lookup for it range content
        AreaReference areaReference = new AreaReference(namedCellRange.getRefersToFormula());
        // and finally get the first row in range
        //    log.info("Range " + rangeName + " starts at row " + areaReference.getFirstCell().getRow());

        return areaReference;
    }

    /**
     * Gets an integer value from a Cell
     *
     * @param cellData
     * @return
     */
    private Integer getIntValueFromCell(Cell cellData) {
        Integer result = 0;

        String cellValue = null;
        switch (cellData.getCellType()) {
            case Cell.CELL_TYPE_STRING:
                cellValue = cellData.getStringCellValue();
                break;
            case Cell.CELL_TYPE_NUMERIC:
                cellValue = String.valueOf(getStringValueFromCell(cellData));
                break;
        }
        if (cellValue == null) {
            result = null;
        } else {
            try {
                result = Integer.parseInt(cellValue);
            } catch (Exception e) {
                result = null;
            }
        }

        return result;
    }

    private String getStringValueFromCell(Cell cellData) {
        String result = "";

        String cellValue = null;
        switch (cellData.getCellType()) {
            case Cell.CELL_TYPE_STRING:
                cellValue = cellData.getStringCellValue();
                break;
            case Cell.CELL_TYPE_NUMERIC:
                cellValue = String.valueOf(cellData.getNumericCellValue());
                break;
        }
        if (cellValue == null) {
            result = null;
        } else {
            result = cellValue;
        }

        return result;

    }

    @Override
    public String getValidationMessage() {
        return this.validationResult;
    }
}
