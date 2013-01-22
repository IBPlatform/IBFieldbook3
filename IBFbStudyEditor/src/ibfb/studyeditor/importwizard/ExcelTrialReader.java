package ibfb.studyeditor.importwizard;

import ibfb.studyeditor.core.model.GermplasmEntriesTableModel;
import ibfb.studyeditor.core.model.ObservationsTableModel;
import java.io.FileInputStream;
import java.io.InputStream;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import org.apache.log4j.Logger;

import org.cimmyt.cril.ibwb.commongui.DialogUtil;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.cimmyt.cril.ibwb.commongui.ConvertUtils;
import org.cimmyt.cril.ibwb.commongui.DecimalUtils;
import org.openide.util.NbBundle;

public class ExcelTrialReader {

    private ResourceBundle bundle = NbBundle.getBundle(ExcelTrialReader.class);
    private static Logger log = Logger.getLogger(ExcelTrialReader.class);
    String fileName = "";
    Sheet sheetDescription;
    Sheet sheetObservation;
    Sheet sheetData;
    Workbook excelBook;
    Cell cellData = null;
    Row rowData = null;
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
            excelBook = WorkbookFactory.create(inputStream);
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

                        Row fila = sheetData.getRow(rowOfGID + 1);//por encabezados
                        Cell celda = fila.getCell(matchesArray.get(i).getColCross());
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

    private String getStringValueFromCell(Cell cellData) {

        String cellValue = null;

        switch (cellData.getCellType()) {

            case Cell.CELL_TYPE_STRING:
                cellValue = cellData.getStringCellValue();
                break;

            case Cell.CELL_TYPE_NUMERIC:
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
            excelBook = WorkbookFactory.create(inputStream);

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

            if (!validDesigAndGid()) {
                String desigLabel = observationsModel.getColumnName(observationsModel.getHeaderIndex(ObservationsTableModel.DESIG));
                String gidLabel = observationsModel.getColumnName(observationsModel.getHeaderIndex(ObservationsTableModel.GID));
                DialogUtil.displayError(MessageFormat.format(bundle.getString("ExcelTrialReader.desigOrGidNotValid"), desigLabel, gidLabel));
                return;
            }


            readTraitsValues(traits, colEntry, colPlot);
            DialogUtil.displayInfo("Data from excel file was loaded");

        } catch (Exception e) {
            log.error("Error al leer excel ", e);
        }

    }

    private int findMaches(Sheet sheet) {

        int result = 0;

        Row fila = sheet.getRow(0); //Encabezados
        int cells = fila.getLastCellNum();

        ArrayList<String> encabezados = new ArrayList<String>();
        matchesArray = new ArrayList<MatchesClass>();

        for (int i = 0; i < observationsModel.getColumnCount(); i++) {
            encabezados.add(observationsModel.getColumnName(i).toUpperCase().trim());
        }

        colGIDObs = encabezados.indexOf("GID");

        for (int i = 0; i < cells; i++) {

            try {
                Cell celda = fila.getCell(i);

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

    private int findMachesForGermplasm(Sheet sheet) {


        int result = 0;

        Row fila = sheet.getRow(0); //Encabezados
        int cells = fila.getLastCellNum();

        ArrayList<String> encabezados = new ArrayList<String>();
        matchesArray = new ArrayList<MatchesClass>();

        for (int i = 0; i < germplasmModel.getColumnCount(); i++) {
            encabezados.add(germplasmModel.getColumnName(i).toUpperCase().trim());
        }

        colGIDgsm = encabezados.indexOf("GID");

        for (int i = 0; i < cells; i++) {

            try {
                Cell celda = fila.getCell(i);

                if (!celda.getStringCellValue().toUpperCase().trim().equals("GID")) {


                    if (celda.getStringCellValue().toUpperCase().trim().equals("PEDIGREE")) {

                        if (encabezados.contains("CROSS NAME") || (encabezados.contains("PEDIGREE"))) {
                            MatchesClass match = new MatchesClass();
                            match.setColIBF(encabezados.indexOf("PEDIGREE"));
                            match.setColCross(i);
                            matchesArray.add(match);
                            result++;
                        }


                    } else if (encabezados.contains(celda.getStringCellValue().toUpperCase().trim())) {

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

    private void fillGIDs(Sheet sheet, int colGID) {
        try {
            gids = new ArrayList<String>();
            int total = sheet.getLastRowNum();

            for (int i = 0; i < total; i++) {
                Row fila = sheet.getRow(i + 1);
                Cell celda = fila.getCell(colGID);
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

    private int findCol(String title, Sheet sheet) {

        int result = -1;

        Row fila = sheet.getRow(0); //Encabezados
        int cells = fila.getLastCellNum();


        for (int i = 0; i < cells; i++) {

            try {

                Cell celda = fila.getCell(i);

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
            Row fila = sheetDescription.getRow(i);
            try {
                if (fila != null) {
                    Cell celda = fila.getCell(0);
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

            Row fila = sheetDescription.getRow(min + 1 + i);
            Cell celda = fila.getCell(2);//property
            Cell celdaMethod = fila.getCell(4);
            int type = celda.getCellType();

            try {
                if (celda.getStringCellValue().equals("TRIAL INSTANCE") && celdaMethod.getStringCellValue().equals("ENUMERATED")) {

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
                Row fila = sheetDescription.getRow(rowVariate + 1 + i);
                if (fila != null) {
                    Cell celda = fila.getCell(0);//property
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

        System.out.println("TOTAL VALUES: " + totalValues);
        System.out.println("TOTAL OBSERVATIONS: " + totalObservations);



//        if (totalValues != totalObservations) {
//            DialogUtil.displayError("Template error. The number of rows does not match");
//            return;
//        }

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
                        Row fila = sheetObservation.getRow(j + 1);
                        Cell celda = fila.getCell(colEntry);

                        int entry = ConvertUtils.getValueAsInteger(celda.getNumericCellValue());
                        celda = fila.getCell(colPlot);
                        int plot = ConvertUtils.getValueAsInteger(celda.getNumericCellValue());
                        celda = fila.getCell(col);
                        filaObs = findFila(entry, plot);
                        // System.out.println("FILA ENCONTRADA: "+filaObs);
                        if (celda == null) {
                            observationsModel.setValueAt("", filaObs, colObs);
                            continue;
                        }
                        int tipo = celda.getCellType();
                        if (tipo == 0) {

                            result = celda.getNumericCellValue();
                            if (filaObs >= 0) {
                                if (DecimalUtils.isIntegerValue(result)) {
                                    Integer intValue = DecimalUtils.getValueAsInteger(result);
                                    observationsModel.setValueAt(intValue.toString(), filaObs, colObs);
                                } else {
                                    observationsModel.setValueAt(String.valueOf(result), filaObs, colObs);
                                }
                            }

                        } else {
                            if (celda.getStringCellValue() != null && !celda.getStringCellValue().trim().isEmpty()) {
                                if (variateDataType.equals("N")) {
                                    result = Double.parseDouble(celda.getStringCellValue().toString());
                                    if (filaObs >= 0) {
                                        if (DecimalUtils.isIntegerValue(result)) {
                                            Integer intValue = DecimalUtils.getValueAsInteger(result);
                                            observationsModel.setValueAt(intValue.toString(), filaObs, colObs);
                                        } else {
                                            observationsModel.setValueAt(String.valueOf(result), filaObs, colObs);
                                        }
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

    public void setGermplasmModel(GermplasmEntriesTableModel tableModel) {
        this.germplasmModel = tableModel;
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
            excelBook = WorkbookFactory.create(inputStream);
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

                        Row fila = sheetData.getRow(rowOfGID + 1);//por encabezados
                        Cell celda = fila.getCell(matchesArray.get(i).getColCross());
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

    /**
     * Validate if DESIG and GID are same that values readed from excel file
     *
     * @return
     * <code>true</code> if DESIG and GID are the same,
     * <code>false</code> if not
     */
    private boolean validDesigAndGid() {
        boolean correctDesigAndGid = true;
        int desigColumn = observationsModel.getHeaderIndex(ObservationsTableModel.DESIG);
        int gidColumn = observationsModel.getHeaderIndex(ObservationsTableModel.GID);
        int trialColumn = observationsModel.getHeaderIndex(ObservationsTableModel.TRIAL);

        int totalObservations = 0;

        Map<Integer, Integer> rowsPerTrial = observationsModel.getRowsPerTrial();

        try {
            totalObservations = rowsPerTrial.get(trial);
        } catch (Exception e) {
            System.out.println("ERROR EN validDesigAndGid: " + e);
        }

        System.out.println("TotalObservations: " + totalObservations);




        int rowToIncrement = 0;
        if (trial > 1) {
            //rowToIncrement = totalObservations * (trial - 1);
            //rowToIncrement = totalObservations;
            for (int previousTrial = trial-1; previousTrial >= 1; previousTrial--) {
                rowToIncrement = rowToIncrement + rowsPerTrial.get(previousTrial);
            }

        }

        for (int row = 0; row <= totalObservations; row++) {
            Row fila = sheetObservation.getRow(row + 1);
            if (fila != null) {
                // validate same trial
                String trialFromGrid = observationsModel.getValueAt(row + rowToIncrement, trialColumn).toString();
                if (Integer.parseInt(trialFromGrid) == trial) {
                    String desigFromExcel = fila.getCell(desigColumn - 1).getStringCellValue();
                    String gidFromExcel = "";
                    if (fila.getCell(gidColumn - 1).getCellType() == Cell.CELL_TYPE_STRING) {
                        gidFromExcel = fila.getCell(gidColumn - 1).getStringCellValue();
                    } else if (fila.getCell(gidColumn - 1).getCellType() == Cell.CELL_TYPE_NUMERIC) {
                        Double gidValue = fila.getCell(gidColumn - 1).getNumericCellValue();
                        gidFromExcel = DecimalUtils.getValueAsInteger(gidValue).toString();
                    }


                    String desigFromGrid = observationsModel.getValueAt(row + rowToIncrement, desigColumn).toString();
                    String gidFromGrid = observationsModel.getValueAt(row + rowToIncrement, gidColumn).toString();



                    if (!desigFromGrid.equals(desigFromExcel) || !gidFromGrid.equals(gidFromExcel)) {
                        correctDesigAndGid = false;
                        break;
                    }
                }
            }
        }

        return correctDesigAndGid;
    }
}
