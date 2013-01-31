package ibfb.studyeditor.importwizard;

import ibfb.studyeditor.core.model.GermplasmEntriesTableModel;
import ibfb.studyeditor.core.model.ObservationsTableModel;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import org.apache.log4j.Logger;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.cimmyt.cril.ibwb.commongui.ConvertUtils;
import org.cimmyt.cril.ibwb.commongui.DialogUtil;

/**
 *
 * @author tmsg
 */
public class ExcelXlsxTrialReader {

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
    
     public void setModel(ObservationsTableModel tableModel) {
        this.observationsModel = tableModel;
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

            readTraitsValues(traits, colEntry, colPlot);
            DialogUtil.displayInfo("Data from excel file was loaded");

        } catch (Exception e) {
            log.error("Error al leer excel ", e);
        }

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

    private int buscaTrialInstance(int max, int min) {
        int result = 0;

        for (int i = 0; i < (max - min); i++) {

            Row fila = sheetDescription.getRow(min + 1 + i);
            Cell celda = fila.getCell(2);//property
            Cell celdaMethod = fila.getCell(4);//property
            int type = celda.getCellType();

            try {
                if (celda.getStringCellValue().equals("TRIAL INSTANCE") && celdaMethod.getStringCellValue().equals("ENUMERATED")) {

                    celda = fila.getCell(6);
                    int tipo = celda.getCellType();
                    if (tipo == 0) {
                        result = (int) fila.getCell(6).getNumericCellValue();
                        return result;
                    } else {
                        result = ConvertUtils.getValueAsInteger(fila.getCell(6).getStringCellValue());
                        // Integer.parseInt(fila.getCell(6).getStringCellValue().toString());
                        return result;
                    }
                }

            } catch (Exception ex) {
                log.info("Sin valor en celda", ex);
            }
        }
        return result;
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
                        Row fila = sheetObservation.getRow(j + 1);
                        Cell celda = fila.getCell(colEntry);
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
}
