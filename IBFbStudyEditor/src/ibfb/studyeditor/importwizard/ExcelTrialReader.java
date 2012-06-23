package ibfb.studyeditor.importwizard;

import ibfb.studyeditor.core.model.GermplasmEntriesTableModel;
import ibfb.studyeditor.core.model.ObservationsTableModel;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.cimmyt.cril.ibwb.commongui.DialogUtil;

public class ExcelTrialReader {

    private static Logger log = Logger.getLogger(ExcelTrialReader.class);
    String fileName = "";
    HSSFSheet sheetDescription;
    HSSFSheet sheetObservation;
    HSSFSheet sheetData;
    HSSFWorkbook excelBook;
    HSSFCell cellData = null;
    HSSFRow rowData = null;
    ObservationsTableModel observationsModel;
    GermplasmEntriesTableModel germplasmModel;
    int obsTrial = -1;
    int obsEntry = -1;
    int obsPlot = -1;
    int trial = 0;
    int instances = 0;
    int colGIDObs = 0;
    int colGIDgsm = 0;
    ArrayList<MatchesClass> matchesArray;
    ArrayList<String> gids;

    public void setFileName(String file) {
        this.fileName = file;
    }

    public void setInstances(int inst) {
        this.instances = inst;
    }

    public void readExcelFileCrossInfo() {

        try {

            int colNumber = 0;
            int rowIndex = 0;
            InputStream inputStream = new FileInputStream(fileName);
            excelBook = new HSSFWorkbook(inputStream);
            sheetData = excelBook.getSheetAt(0);
            rowData = sheetData.getRow(rowIndex);
            cellData = rowData.getCell(colNumber);


            int colGID = findCol("GID", sheetData);



            if (colGID == -1) {
                DialogUtil.displayError("File error, Data sheet. There is not GID column");
                return;
            }

            int matches = findMaches(sheetData);

            if (matches > 0) {
                System.out.println("ok tenemos " + matches + " coincidencias");

            } else {
                DialogUtil.displayError("Data error, Data sheet. There are not values to import");
                return;
            }

            fillGIDs(sheetData, colGID);
            ObservationsTableModel.setIsFromCrossInfo(true);

            for (int j = 0; j < observationsModel.getRowCount(); j++) {

                Object gid = observationsModel.getValueAt(j, colGIDObs);

                int rowOfGID = findRowForGID(gid.toString());

                System.out.println("GID: " + gid.toString() + "  ROW EXCEL: " + rowOfGID);

                if (rowOfGID >= 0) {
                    for (int i = 0; i < matchesArray.size(); i++) {

                        HSSFRow fila = sheetData.getRow(rowOfGID + 1);//por encabezados
                        HSSFCell celda = fila.getCell(matchesArray.get(i).getColCross());
                        String valor = getStringValueFromCell(celda);

                        observationsModel.setValueAt(valor, j, matchesArray.get(i).getColIBF());

                        //   System.out.println("PAREJAS: " + matchesArray.get(i).getColIBF() + " - " + matchesArray.get(i).getColCross());

                    }
                } else {
                    System.out.println(gid.toString() + " NO ENCONTRADO");
                }

            }
            ObservationsTableModel.setIsFromCrossInfo(false);
        } catch (Exception e) {
            ObservationsTableModel.setIsFromCrossInfo(false);
            log.error("Error al leer excel ", e);
        }

    }

    private String getStringValueFromCell(HSSFCell cellData) {

        String cellValue = null;

        switch (cellData.getCellType()) {

            case HSSFCell.CELL_TYPE_STRING:
                cellValue = cellData.getStringCellValue();
                break;

            case HSSFCell.CELL_TYPE_NUMERIC:
                cellValue = String.valueOf((int) cellData.getNumericCellValue());
                break;
        }

        if (cellValue == null) {
            cellValue = "";
        }

        return cellValue;
    }

    public void readExcelFile() {

        try {

            int colNumber = 0;
            int rowIndex = 0;
            InputStream inputStream = new FileInputStream(fileName);
            excelBook = new HSSFWorkbook(inputStream);

            if (excelBook.getNumberOfSheets() != 2) {
                log.info("Hay menos de dos hojas en el archivo");
                DialogUtil.displayError("Template error. This is not a valid template file");
                return;
            }

            sheetDescription = excelBook.getSheetAt(0);
            sheetObservation = excelBook.getSheetAt(1);
            rowData = sheetDescription.getRow(rowIndex);
            cellData = rowData.getCell(colNumber);

            if (cellData.getStringCellValue().equals("STUDY")) {
                log.info("Ok tenemos study");

            } else {
                log.info("No tenemos study");
                DialogUtil.displayError("Template error, Description sheet. Bad format");
                return;
            }

            int rowCondition = findRow("CONDITION");
            int rowFactor = findRow("FACTOR");
            int rowConstant = findRow("CONSTANT");
            int rowVariate = findRow("VARIATE");


            if (!validaSecciones(rowCondition, rowFactor, rowConstant, rowVariate)) {
                log.info("Error en el template. Mal formato");
                DialogUtil.displayError("Template error, Description sheet. Bad format");
                return;
            }

            if (rowCondition > 0 && (rowFactor > rowCondition)) {
                trial = buscaTrialInstance(rowFactor, rowCondition);
                if (trial == 0) {
                    log.info("TRIAL: 0.  Missing value!!!! ");
                    DialogUtil.displayError("Template error, TRIAL INSTANCE. MISSING VALUE");
                    return;
                } else {
                    log.info("TRIAL: " + trial);
                }
            }

            ArrayList traits = readVariates(rowVariate);
            String entryLabel = observationsModel.getWorkbook().getEntryLabel();
            String plotLabel = observationsModel.getWorkbook().getPlotLabel();
            //int colEntry = findCol("ENTRY");
            int colEntry = findCol(entryLabel, sheetObservation);
            //int colPlot = findCol("PLOT");
            int colPlot = findCol(plotLabel, sheetObservation);

            if (!validaObservationSecciones(colEntry, colPlot)) {
                log.info("Error en el template. Sheet observations. Mal formato");
                DialogUtil.displayError("Template error, Observations sheet. Bad format");
                return;
            }

            readTraitsValues(traits, colEntry, colPlot);
            DialogUtil.displayInfo("Data from excel file was loaded");

        } catch (Exception e) {
            log.error("Error al leer excel ", e);
        }

    }

    private int findMaches(HSSFSheet sheet) {

        int result = 0;

        HSSFRow fila = sheet.getRow(0); //Encabezados
        int cells = fila.getLastCellNum();

        ArrayList<String> encabezados = new ArrayList<String>();
        matchesArray = new ArrayList<MatchesClass>();

        for (int i = 0; i < observationsModel.getColumnCount(); i++) {
            encabezados.add(observationsModel.getColumnName(i).toUpperCase().trim());
        }

        colGIDObs = encabezados.indexOf("GID");

        for (int i = 0; i < cells; i++) {

            try {
                HSSFCell celda = fila.getCell(i);

                if (!celda.getStringCellValue().toUpperCase().trim().equals("GID")) {

                    if (encabezados.contains(celda.getStringCellValue().toUpperCase().trim())) {
                        MatchesClass match = new MatchesClass();
                        match.setColIBF(encabezados.indexOf(celda.getStringCellValue().toUpperCase().trim()));
                        match.setColCross(i);
                        matchesArray.add(match);
                        result++;
                    }

                }
            } catch (Exception ex) {
                log.error("ERROR EN FINDMACHES", ex);
            }




        }

        return result;
    }

    
    
    
    private int findMachesForGermplasm(HSSFSheet sheet) {

  
        int result = 0;

        HSSFRow fila = sheet.getRow(0); //Encabezados
        int cells = fila.getLastCellNum();

        ArrayList<String> encabezados = new ArrayList<String>();
        matchesArray = new ArrayList<MatchesClass>();

        for (int i = 0; i < germplasmModel.getColumnCount(); i++) {
            encabezados.add(germplasmModel.getColumnName(i).toUpperCase().trim());
        }

        colGIDgsm = encabezados.indexOf("GID");

        for (int i = 0; i < cells; i++) {

            try {
                HSSFCell celda = fila.getCell(i);

                if (!celda.getStringCellValue().toUpperCase().trim().equals("GID")) {

                    if (encabezados.contains(celda.getStringCellValue().toUpperCase().trim())) {
                        MatchesClass match = new MatchesClass();
                        match.setColIBF(encabezados.indexOf(celda.getStringCellValue().toUpperCase().trim()));
                        match.setColCross(i);
                        matchesArray.add(match);
                        result++;
                    }

                }
            } catch (Exception ex) {
                log.error("ERROR EN FINDMACHES", ex);
            }




        }

        return result;
    }

    
    
    private void fillGIDs(HSSFSheet sheet, int colGID) {
        try {
            gids = new ArrayList<String>();
            int total = sheet.getLastRowNum();

            for (int i = 0; i < total; i++) {
                HSSFRow fila = sheet.getRow(i + 1);
                HSSFCell celda = fila.getCell(colGID);
                int valor = (int) (celda.getNumericCellValue());
                gids.add(String.valueOf(valor));
            }

        } catch (Exception ex) {
            log.error("ERROR EN fillGIDs", ex);
        }

    }

    private int findRowForGID(String elGID) {

        int result = -1;
        result = gids.indexOf(elGID);
        return result;
    }

    private int findCol(String title, HSSFSheet sheet) {

        int result = -1;

        HSSFRow fila = sheet.getRow(0); //Encabezados
        int cells = fila.getLastCellNum();


        for (int i = 0; i < cells; i++) {

            try {

                HSSFCell celda = fila.getCell(i);

                if (celda.getStringCellValue().equals(title)) {
                    log.info("Celda " + title + " encontrada en columna " + i);
                    return i;

                }

            } catch (Exception ex) {
                log.error("SIN VALOR EN FILA EXCEL", ex);
            }




        }

        return result;
    }

    private int findRow(String title) {
        int result = 0;
        for (int i = 0; i < sheetDescription.getLastRowNum(); i++) {
            log.info("Looking for " + title + " in row " + i);
            HSSFRow fila = sheetDescription.getRow(i);
            try {
                if (fila != null) {
                    HSSFCell celda = fila.getCell(0);
                    if (celda != null && celda.getStringCellValue() != null) {
                        if (celda.getStringCellValue().equals(title)) {
                            log.info("Celda " + title + " encontrada en renglon " + i);
                            return i;
                        }
                    }
                }
            } catch (Exception ex) {
                log.error("SIN VALOR EN FILA EXCEL ", ex);
            }
        }

        return result;
    }

    private int buscaTrialInstance(int max, int min) {
        int result = 0;

        for (int i = 0; i < (max - min); i++) {

            HSSFRow fila = sheetDescription.getRow(min + 1 + i);
            HSSFCell celda = fila.getCell(2);//property
            int type = celda.getCellType();

            try {
                if (celda.getStringCellValue().equals("TRIAL INSTANCE")) {

                    celda = fila.getCell(6);
                    int tipo = celda.getCellType();
                    if (tipo == 0) {
                        result = (int) fila.getCell(6).getNumericCellValue();
                        return result;
                    } else {
                        result = Integer.parseInt(fila.getCell(6).getStringCellValue().toString());
                        return result;
                    }
                }

            } catch (Exception ex) {
                log.info("Sin valor en celda", ex);
            }
        }
        return result;
    }

    private boolean validaSecciones(int rowCondition, int rowFactor, int rowConstant, int rowVariate) {
        boolean isValid = false;
        if (rowCondition > 0) {
            if (rowFactor > rowCondition) {
                if (rowConstant > rowCondition) {
                    if (rowVariate > rowCondition) {
                        isValid = true;
                    }

                }

            }
        }

        return isValid;
    }

    private ArrayList readVariates(int rowVariate) {
        ArrayList variates = new ArrayList();
        try {
            for (int i = 0; i < sheetDescription.getLastRowNum(); i++) {
                HSSFRow fila = sheetDescription.getRow(rowVariate + 1 + i);
                if (fila != null) {
                    HSSFCell celda = fila.getCell(0);//property
                    variates.add(celda.getStringCellValue().toString());
                }
            }

        } catch (Exception ex) {
            log.error("Sin variate valor", ex);
        }
        return variates;
    }

    private boolean validaObservationSecciones(int colEntry, int colPlot) {
        boolean isValid = false;
        if (colEntry > -1) {
            if (colPlot > colEntry) {
                isValid = true;
            }
        }

        return isValid;
    }

    private void readTraitsValues(ArrayList traits, int colEntry, int colPlot) {
        int totalValues = sheetObservation.getLastRowNum();
        int totalObservations = observationsModel.getRowsPerTrial().get(trial); //observationsModel.getRowCount() / instances;



        if (totalValues != totalObservations) {
            DialogUtil.displayError("Template error. The number of rows does not match");
            return;
        }




        for (int i = 0; i < traits.size(); i++) {//columnas

            String trait = traits.get(i).toString();
            int col = findCol(trait, sheetObservation);
            int colObs = observationsModel.findColumn(trait);


            String variateDataType = observationsModel.getVariate(colObs).getDataType();

            String trialLabel = observationsModel.getWorkbook().getTrialLabel();
            String entryLabel = observationsModel.getWorkbook().getEntryLabel();
            String plotLabel = observationsModel.getWorkbook().getPlotLabel();

            //obsTrial = observationsModel.findColumn("TRIAL");
            //obsEntry = observationsModel.findColumn("ENTRY");
            //obsPlot = observationsModel.findColumn("PLOT");
            obsTrial = observationsModel.findColumn(trialLabel);
            obsEntry = observationsModel.findColumn(entryLabel);
            obsPlot = observationsModel.findColumn(plotLabel);


            if (!validaHeadersObservations(obsTrial, obsEntry, obsPlot)) {
                log.info("ERROR EN LA TABLA MEASUREMENTS");
                DialogUtil.displayError("Template error, Observations sheet. Bad format");
                return;
            }

            if ((col) > 0 && (colObs > 0)) {
                for (int j = 0; j < totalValues; j++) {//filas
                    double result = 0;
                    int filaObs = 0;
                    try {
                        HSSFRow fila = sheetObservation.getRow(j + 1);
                        HSSFCell celda = fila.getCell(colEntry);
                        int entry = Integer.parseInt(celda.getStringCellValue());
                        celda = fila.getCell(colPlot);
                        int plot = Integer.parseInt(celda.getStringCellValue());
                        celda = fila.getCell(col);
                        filaObs = findFila(entry, plot);
                        // System.out.println("FILA ENCONTRADA: "+filaObs);
                        if (celda == null) {
                            continue;
                        }
                        int tipo = celda.getCellType();
                        if (tipo == 0) {

                            result = celda.getNumericCellValue();
                            if (filaObs >= 0) {
                                observationsModel.setValueAt(String.valueOf(result), filaObs, colObs);
                            }

                        } else {
                            if (celda.getStringCellValue() != null && !celda.getStringCellValue().trim().isEmpty()) {
                                if (variateDataType.equals("N")) {
                                    result = Double.parseDouble(celda.getStringCellValue().toString());
                                    if (filaObs >= 0) {
                                        observationsModel.setValueAt(String.valueOf(result), filaObs, colObs);
                                    }
                                } else if (variateDataType.equals("C")) {
                                    String stringResult = (String) celda.getStringCellValue().toString();
                                    if (filaObs >= 0) {
                                        observationsModel.setValueAt(stringResult, filaObs, colObs);
                                    }


                                }
                            }
                        }
                    } catch (Exception ex) {
                        log.error("NO TRAIT VALUE", ex);
                        result = 0;
                        if (filaObs >= 0) {
                            observationsModel.setValueAt(String.valueOf(result), filaObs, colObs);
                        }
                    }

                    log.info("TRAIT: " + traits.get(i) + "   VALUE: " + result);
                }
            }//fin del IF

        }
    }

    public void setModel(ObservationsTableModel tableModel) {
        this.observationsModel = tableModel;
    }
    
    
    public void setGermplasmModel(GermplasmEntriesTableModel tableModel){
        this.germplasmModel=tableModel;
    }

    private boolean validaHeadersObservations(int obsTrial, int obsEntry, int obsPlot) {
        if (obsTrial >= 0 && obsEntry >= 0 && obsPlot >= 0) {
            return true;
        } else {
            return false;
        }
    }

    private int findFila(int entry, int plot) {
        int fila = -1;
        int total = observationsModel.getRowCount();
        for (int i = 0; i < total; i++) {
            Object trialFound = observationsModel.getValueAt(i, obsTrial);
            Object plotFound = observationsModel.getValueAt(i, obsPlot);

            // in some cases when call R script plot is returned as String not as Integer
            // so, for trial and plot MUST BE FORCED as INTEGER!!!

            Integer intTrialFound = 0;
            Integer intPlotFound = 0;
            // first force cast trial as Integer
            if (trialFound instanceof String) {
                intTrialFound = Integer.parseInt((String) trialFound);
            } else if (trialFound instanceof Integer) {
                intTrialFound = (Integer) trialFound;
            }
            // then force cast plot as Integer
            if (plotFound instanceof String) {
                intPlotFound = Integer.parseInt((String) plotFound);
            } else if (plotFound instanceof Integer) {
                intPlotFound = (Integer) plotFound;
            }

            // now we can compare all values as Integers!!!
            if (intTrialFound.intValue() == trial && intPlotFound.intValue() == plot) {
                return i;
            }
        }
        return fila;
    }

    public void readExcelFileCrossInfoToGermplasm() {
        try {

            int colNumber = 0;
            int rowIndex = 0;
            InputStream inputStream = new FileInputStream(fileName);
            excelBook = new HSSFWorkbook(inputStream);
            sheetData = excelBook.getSheetAt(0);
            rowData = sheetData.getRow(rowIndex);
            cellData = rowData.getCell(colNumber);


            int colGID = findCol("GID", sheetData);

            if (colGID == -1) {
                DialogUtil.displayError("File error, Data sheet. There is not GID column");
                return;
            }

            int matches = findMachesForGermplasm(sheetData);

            if (matches > 0) {
                System.out.println("ok tenemos " + matches + " coincidencias");

            } else {
                DialogUtil.displayError("Data error, Data sheet. There are not values to import");
                return;
            }

            fillGIDs(sheetData, colGID);
        
            
               germplasmModel.setIsFromCrossInfo(true);

            for (int j = 0; j < germplasmModel.getRowCount(); j++) {

                Object gid = germplasmModel.getValueAt(j, colGIDgsm);

                int rowOfGID = findRowForGID(gid.toString());

                

                if (rowOfGID >= 0) {
                    for (int i = 0; i < matchesArray.size(); i++) {

                        HSSFRow fila = sheetData.getRow(rowOfGID + 1);//por encabezados
                        HSSFCell celda = fila.getCell(matchesArray.get(i).getColCross());
                        String valor = getStringValueFromCell(celda);

                        germplasmModel.setValueAt(valor, j, matchesArray.get(i).getColIBF());
                    }
                } else {
                    System.out.println(gid.toString() + " NO ENCONTRADO");
                }

            }
           germplasmModel.setIsFromCrossInfo(false);
        } catch (Exception e) {
           germplasmModel.setIsFromCrossInfo(false);
            log.error("Error al leer excel ", e);
        }

    }
}
