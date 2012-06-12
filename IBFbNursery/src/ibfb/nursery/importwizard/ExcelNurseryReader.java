package ibfb.nursery.importwizard;

import ibfb.nursery.models.ObservationsTableModel;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.cimmyt.cril.ibwb.commongui.DialogUtil;

public class ExcelNurseryReader {

    private static Logger log = Logger.getLogger(ExcelNurseryReader.class);
    String fileName = "";
    HSSFSheet sheetDescription;
    HSSFSheet sheetObservation;
    HSSFWorkbook excelBook;
    HSSFCell cellData = null;
    HSSFRow rowData = null;
    ObservationsTableModel observationsModel;
    int obsEntry = -1;
    int obsPlot = -1;
    int instances = 0;

    public void setFileName(String file) {
        this.fileName = file;
    }

    public void setInstances(int inst) {
        this.instances = inst;
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
            ArrayList traits = readVariates(rowVariate);
            int colEntry = findCol("ENTRY");
            int colPlot = findCol("PLOT");

            if (!validaObservationSecciones(colEntry, colPlot)) {
                log.info("Error en el template. Sheet observations. Mal formato");
                DialogUtil.displayError("Template error, Observations sheet. Bad format");
                return;
            }

            readTraitsValues(traits, colEntry, colPlot, rowVariate);
            DialogUtil.displayInfo("Data from excel file was loaded");

        } catch (Exception e) {
            log.error("Error al leer excel ", e);
        }
    }

    private int findCol(String title) {
        int result = -1;
        HSSFRow fila = sheetObservation.getRow(0); //Encabezados
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

    private int buscaNurseryInstance(int max, int min) {
        int result = 0;

        for (int i = 0; i < (max - min); i++) {

            HSSFRow fila = sheetDescription.getRow(min + 1 + i);
            HSSFCell celda = fila.getCell(2);//property
            int type = celda.getCellType();

            try {
                if (celda.getStringCellValue().equals("NURSERY INSTANCE")) {

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

    private void readTraitsValues(ArrayList traits, int colEntry, int colPlot, int rowVariate) {
        boolean matchDataTypes = false;
        boolean isNewTrait = false;
        int totalValues = sheetObservation.getLastRowNum();
        int totalObservations = observationsModel.getRowCount(); // instances;


        if (totalValues != totalObservations) {
            DialogUtil.displayError("Template error. The number of rows does not match");
            return;
        }


        obsEntry = observationsModel.findColumn("ENTRY");
        obsPlot = observationsModel.findColumn("PLOT");


        for (int i = 0; i < traits.size(); i++) {//columnas

            String trait = traits.get(i).toString();
            int col = findCol(trait);

            System.out.println("BUSCAMOS: " + trait);


            String modelDataType = observationsModel.getDataTypeForVariate(trait);
            String excelDataType = giveMeTraitDataType(trait, rowVariate);


            if (modelDataType.isEmpty()) {
                isNewTrait = true;
            }

            if (modelDataType.equals(excelDataType)) {
                matchDataTypes = true;
            } else {
                matchDataTypes = false;
            }

            System.out.println("TIPO EN MODEL: " + modelDataType);
            System.out.println("TIPO EN EXCEL: " + excelDataType);

            int colObs = observationsModel.findColumn(trait);

            if (!validaHeadersObservations(obsEntry, obsPlot)) {
                log.info("ERROR EN LA TABLA MEASUREMENTS");
                DialogUtil.displayError("Template error, Observations sheet. Bad format");
                return;
            }

            if ((col) > 0 && (colObs > 0)) {
                for (int j = 0; j < totalValues; j++) {//filas
                    double result = 0;
                    String resultCad = "";
                    int filaObs = 0;
                    try {
                        HSSFRow fila = sheetObservation.getRow(j + 1);

                        HSSFCell celda = fila.getCell(colEntry);
                        int entry = Integer.parseInt(celda.getStringCellValue());

                        celda = fila.getCell(colPlot);
                        int plot = Integer.parseInt(celda.getStringCellValue());

                        celda = fila.getCell(col);
                        filaObs = findFila(entry, plot);

                        System.out.println("FILA: " + filaObs);
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
                                // result = Double.parseDouble(celda.getStringCellValue().toString());
                                resultCad = celda.getStringCellValue().toString();
                                if (filaObs >= 0) {
                                    observationsModel.setValueAt(resultCad, filaObs, colObs);
                                }
                            }
                        }
                    } catch (NullPointerException ex) {
                        log.error("NO TRAIT VALUE", ex);
                        // result = 0;
                        // if (filaObs >= 0) {
                        //     observationsModel.setValueAt(String.valueOf(result), filaObs, colObs);
                        // }
                    } catch (Exception e) {
                        log.error("ERROR TYPE", e);
                    }

                    log.info("TRAIT: " + traits.get(i) + "   VALUE: " + result);
                }
            }//fin del IF

        }
    }

    private String giveMeTraitDataType(String trait, int rowVariate) {
        String tipo = "";
        try {
            for (int i = 0; i < sheetDescription.getLastRowNum(); i++) {
                HSSFRow fila = sheetDescription.getRow(rowVariate + 1 + i);
                if (fila != null) {
                    HSSFCell celda = fila.getCell(0);

                    if (celda.getStringCellValue().toString().equals(trait)) {
                        celda = fila.getCell(5);
                        tipo = celda.getStringCellValue().toString();
                        return tipo;
                    }

                }
            }

        } catch (Exception ex) {
            log.error("Sin variate valor", ex);
            return tipo;
        }
        return tipo;
    }

    public void setModel(ObservationsTableModel tableModel) {
        this.observationsModel = tableModel;
    }

    private boolean validaHeadersObservations(int obsEntry, int obsPlot) {
        if (obsEntry >= 0 && obsPlot >= 0) {
            return true;
        } else {
            return false;
        }
    }

    private int findFila(int entry, int plot) {
        int fila = -1;
        int total = observationsModel.getRowCount();
        for (int i = 0; i < total; i++) {
            Object entryFound = observationsModel.getValueAt(i, obsEntry);
            Object plotFound = observationsModel.getValueAt(i, obsPlot);

            // in some cases when call R script plot is returned as String not as Integer
            // so, for trial and plot MUST BE FORCED as INTEGER!!!

            Integer intEntryFound = 0;
            Integer intPlotFound = 0;


            // first force cast trial as Integer
            if (entryFound instanceof String) {
                intEntryFound = Integer.parseInt((String) entryFound);
            } else if (entryFound instanceof Integer) {
                intEntryFound = (Integer) entryFound;
            }


            // then force cast plot as Integer
            if (plotFound instanceof String) {
                intPlotFound = Integer.parseInt((String) plotFound);
            } else if (plotFound instanceof Integer) {
                intPlotFound = (Integer) plotFound;
            }
            if (intEntryFound.intValue() == entry && intPlotFound.intValue() == plot) {
                return i;
            }

        }
        return fila;
    }
}
