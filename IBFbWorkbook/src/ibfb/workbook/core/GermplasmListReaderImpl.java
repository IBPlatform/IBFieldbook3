package ibfb.workbook.core;

import ibfb.workbook.utils.ExcelUtils;
import ibfb.domain.core.ListOfEntries;
import ibfb.domain.core.GermplasmList;
import ibfb.workbook.api.GermplasmListReader;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.cimmyt.cril.ibwb.api.AppServicesProxy;
import org.cimmyt.cril.ibwb.domain.Listdata;
import org.cimmyt.cril.ibwb.domain.ListdataPK;
import org.cimmyt.cril.ibwb.domain.Listnms;

/**
 *
 * @author TMSANCHEZ
 */
public class GermplasmListReaderImpl implements GermplasmListReader {
    private  int rowHeader;
    private static Logger log = Logger.getLogger(GermplasmListReaderImpl.class);

    @Override
    public boolean isValidTemplate(String fileName) throws Exception {
        boolean valid = false;

        log.info("Validating Excel file read BEGIN");
        log.info("Opening file...");
        InputStream inputStream = new FileInputStream(fileName);
        HSSFWorkbook excelBook = new HSSFWorkbook(inputStream);
        log.info("Number of sheets in book" + excelBook.getNumberOfSheets());

        // assume values are in B colum (index 1)
        int labelColNumber = 0;
        int listRowIndex = 0;

        // get sheet where is located list data
        HSSFSheet sheet = excelBook.getSheetAt(0);

        HSSFCell cellDataGid = null;
        HSSFCell cellDataEntryCode = null;
        HSSFCell cellDataDesignation = null;
        HSSFRow rowData = null;

        rowHeader = getRowHeaderIndex(excelBook);

        if (rowHeader > -1) {
            rowData = sheet.getRow(rowHeader);
            cellDataGid = rowData.getCell(COLUMN_GID);
            cellDataEntryCode = rowData.getCell(COLUMN_ENTRY_CODE);
            cellDataDesignation = rowData.getCell(COLUMN_DESIGNATION);
            if (ExcelUtils.getStringValueFromCell(cellDataGid).toUpperCase().startsWith(HEADER_GID)
                    && ExcelUtils.getStringValueFromCell(cellDataEntryCode).toUpperCase().startsWith(HEADER_ENTRY_CODE)
                    && ExcelUtils.getStringValueFromCell(cellDataDesignation).toUpperCase().startsWith(HEADER_DESIGNATION)) {
                valid = true;
            }
        }

        return valid;
    }
    
    
    
    @Override
    public boolean isValidCrossScript(String fileName) throws Exception {
        boolean valid = false;

        log.info("Validating Cross Excel file read BEGIN");
        log.info("Opening file...");
        InputStream inputStream = new FileInputStream(fileName);
        HSSFWorkbook excelBook = new HSSFWorkbook(inputStream);

        int labelColNumber = 0;
        int listRowIndex = 0;

        HSSFSheet sheet = excelBook.getSheetAt(0);

        HSSFCell cellDataGid = null;
        HSSFCell cellDataEntryCode = null;
        HSSFCell cellDataDesignation = null;
        HSSFRow rowData = null;

 
        //PENDIENTE 
        
        valid=true;
        
        return valid;
    }
    

    @Override
    public GermplasmList getGermPlasmList(String fileName) throws Exception {
        GermplasmList germplasmList = new GermplasmList();

        log.info("Excel file read BEGIN");
        log.info("Opening file...");
        InputStream inputStream = new FileInputStream(fileName);
        HSSFWorkbook excelBook = new HSSFWorkbook(inputStream);
        log.info("Number of sheets in book" + excelBook.getNumberOfSheets());

        fillGermplasmList(germplasmList, excelBook);

        log.info("Excel file read END");
        return germplasmList;
    }

    private void fillGermplasmList(GermplasmList germplasmList, HSSFWorkbook excelBook) {
        if (rowHeader > 4) {
        fillHeader(germplasmList, excelBook);
        }
        fillEntryList(germplasmList, excelBook);
    }

    private void fillHeader(GermplasmList germplasmList, HSSFWorkbook excelBook) {
        // assume values are in B colum (index 1)
        int dataColNumber = 1;
        int listRowIndex = 0;

        // get sheet where is located list data
        HSSFSheet sheet = excelBook.getSheetAt(0);

        HSSFCell cellData = null;
        HSSFRow rowData = null;

        rowData = sheet.getRow(listRowIndex);
        cellData = rowData.getCell(dataColNumber);
        germplasmList.setId(ExcelUtils.getIntValueFromCell(cellData));

        listRowIndex++;
        rowData = sheet.getRow(listRowIndex);
        cellData = rowData.getCell(dataColNumber);
        germplasmList.setName(ExcelUtils.getStringValueFromCell(cellData));

        listRowIndex++;
        rowData = sheet.getRow(listRowIndex);
        cellData = rowData.getCell(dataColNumber);
        germplasmList.setDate(ExcelUtils.getDateValueFromCell(cellData));

        listRowIndex++;
        rowData = sheet.getRow(listRowIndex);
        cellData = rowData.getCell(dataColNumber);
        germplasmList.setType(ExcelUtils.getStringValueFromCell(cellData));

        listRowIndex++;
        rowData = sheet.getRow(listRowIndex);
        cellData = rowData.getCell(dataColNumber);
        germplasmList.setTitle(ExcelUtils.getStringValueFromCell(cellData));

        log.info("Data for Germplasm List: " + germplasmList.toString());

    }

    private void fillEntryList(GermplasmList germplasmList, HSSFWorkbook excelBook) {
        List<ListOfEntries> listEntries = new ArrayList<ListOfEntries>();
        // assume values are in B colum (index 1)
        //int entryRowIndex = ROW_HEADER_INDEX + 1;
        int entryRowIndex = rowHeader + 1;
        int lastEntryRowIndex = getLastEntryRowIndex(excelBook);


        // Assume that all values are in first sheet
        HSSFSheet sheet = excelBook.getSheetAt(0);
        HSSFCell cellData = null;
        HSSFRow rowData = null;

        // internal index for count number of items
        int rowCounter = 1;

        for (int rowIndex = entryRowIndex; rowIndex < lastEntryRowIndex; rowIndex++) {

            rowData = sheet.getRow(rowIndex);

            // create a new FACTOR
            ListOfEntries listEntry = new ListOfEntries();

            // assign the curren index (number of row in file)
            listEntry.setNumber(rowCounter);

            // assign data
            cellData = rowData.getCell(COLUMN_GID);
            listEntry.setGid(ExcelUtils.getIntValueFromCell(cellData));

            cellData = rowData.getCell(COLUMN_ENTRY_CODE);
            listEntry.setEntryCode(ExcelUtils.getStringValueFromCell(cellData));

            cellData = rowData.getCell(COLUMN_DESIGNATION);
            listEntry.setDesignation(ExcelUtils.getStringValueFromCell(cellData));

            cellData = rowData.getCell(COLUMN_CROSS);
            listEntry.setCross(ExcelUtils.getStringValueFromCell(cellData));

            cellData = rowData.getCell(COLUMN_SOURCE);
            listEntry.setSource(ExcelUtils.getStringValueFromCell(cellData));

            cellData = rowData.getCell(COLUMN_UNIQUE_ID);
            listEntry.setUniqueId(ExcelUtils.getStringValueFromCell(cellData));

            cellData = rowData.getCell(COLUMN_ENTRY_ID);
            listEntry.setEntryId(ExcelUtils.getIntValueFromCell(cellData));


            log.info("Data for Entry: " + listEntry.toString());
            // add readed Entry to list
            listEntries.add(listEntry);

            rowCounter++;
        }

        log.info("Total liet entries found: " + listEntries.size());

        germplasmList.setListEntries(listEntries);
    }

    /**
     * Return last Row Index where list contains data
     * @return
     */
    private int getLastEntryRowIndex(HSSFWorkbook excelBook) {
        int entryRowIndex = ROW_HEADER_INDEX + 1;
        int lastEntryRowIndex = entryRowIndex;
        boolean moreRowsToRead = true;

        HSSFSheet sheet = excelBook.getSheetAt(0);
        HSSFCell cellData = null;
        HSSFRow rowData = null;

        while (moreRowsToRead) {
            lastEntryRowIndex++;
            rowData = sheet.getRow(lastEntryRowIndex);

            moreRowsToRead = ExcelUtils.isMoreRows(rowData, COLUMN_ENTRY_ID);

            if (!moreRowsToRead) {
                break;
            }

        }
        return lastEntryRowIndex;
    }

    /**
     * Gets row number where GID header is located in spreadshet
     * @param excelBook
     * @return A row number greater than -1 if GID label found or -1 if not found
     */
    private int getRowHeaderIndex(HSSFWorkbook excelBook) {
        int gidRowIndex = -1;

        HSSFSheet sheet = excelBook.getSheetAt(0);
        HSSFCell cellData = null;
        HSSFRow rowData = null;

        for (int index = 0; index < MAX_ROW; index++) {

            rowData = sheet.getRow(index);
            if (rowData != null) {
                cellData = rowData.getCell(COLUMN_GID);
                String cell = ExcelUtils.getStringValueFromCell(cellData);

                if (cell.equals(HEADER_GID)) {
                    gidRowIndex = index;
                    break;
                }
            }
        }
        return gidRowIndex;

    }

    /**
     * @autor tmsanchez
     * @param listid
     * @return
     */
    @Override
    public GermplasmList getGermPlasmListFromDB(Integer listid) {
        GermplasmList germplasmList = new GermplasmList();

        fillHeader(germplasmList, listid);
        fillEntryList(germplasmList, listid);

        return germplasmList;
    }

    /**
     * Fill header list from DB
     * @param germplasmList GermplasmList to fill
     * @param listid ID for germplasm list
     */
    private void fillHeader(GermplasmList germplasmList, Integer listid) {

        ///* PENDIENTE 

        // retrieve list from DB

        Listnms listnms = AppServicesProxy.getDefault().appServices().getListnms(listid);

        // Assign from DB
        germplasmList.setId(listid);
        germplasmList.setName(listnms.getListname());
        germplasmList.setTitle(listnms.getListdesc());
//*/
    }

    /**
     * Fills a GermplasmList from all items in DB
     * @param germplasmList  GermplasmList to fill
     * @param listid
     */
    private void fillEntryList(GermplasmList germplasmList, Integer listid) {

        ///* PENDIENTE 
        List<ListOfEntries> listEntries = new ArrayList<ListOfEntries>();

        // Define search filter
        Listdata filter = new Listdata(true);
        filter.setListdataPK(new ListdataPK(listid, null));
        int total = AppServicesProxy.getDefault().appServices().getTotalListdata(filter);
        // Retrieve list data for listId
        List<Listdata> listdataList = AppServicesProxy.getDefault().appServices().getListListdata(filter, 0, total, false);

        int rowCounter = 1;

        // Then assign to list of entries
        for (Listdata listdata : listdataList) {
            ListOfEntries entries = new ListOfEntries();
            // assing from DB
            entries.setCross(listdata.getGrpname());
            entries.setDesignation(listdata.getDesig());
            entries.setEntryId(listdata.getEntryid());
            entries.setSource(listdata.getSource());
            entries.setEntryCode(listdata.getEntrycd());
            entries.setGid(listdata.getGid());
            entries.setNumber(rowCounter);

            // add to entries list
            listEntries.add(entries);

            // increment row counter
            rowCounter++;
        }
        germplasmList.setListEntries(listEntries);


        // */
    }
}