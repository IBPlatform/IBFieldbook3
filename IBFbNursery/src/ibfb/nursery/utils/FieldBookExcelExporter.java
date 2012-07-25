package ibfb.nursery.utils;

import ibfb.domain.core.*;
import ibfb.nursery.models.ObservationsTableModel;
import java.awt.Desktop;
import java.io.*;
import java.util.List;
import javax.swing.JTable;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.hssf.util.HSSFColor;
import org.cimmyt.cril.ibwb.commongui.DialogUtil;
import org.openide.NotifyDescriptor;
import org.openide.util.NbBundle;

/**
 * Export to Excel format Measurement table
 *  * @author TMSANCHEZ
 */
public class FieldBookExcelExporter {

    private static final int NAME = 0;
    private static final int DESCRIPTION = 1;
    private static final int PROPERTY = 2;
    private static final int SCALE = 3;
    private static final int METHOD = 4;
    private static final int DATA_TYPE = 5;
    private static final int VALUE = 6;
    private static final int LABEL = 7;
    private JTable jTableObservations;
    private String fileTemplate;
    private String trialFile;
    private Workbook workbook;
    private List<Constant> constants;
    private List<Variate> variates;
    /**
     * Study information
     */
    private Study study;
    /**
     * Current row in file
     */
    private int currentRow = 0;

    /**
     * Constructs excel exporter
     * @param jTableObservations
     * @param fileTemplate Template file name
     * @param trialFile Trial file name
     * @param study Study information
     * @param workbook Workbooks objects from template
     * @param constants selected constantts
     */
    public FieldBookExcelExporter(JTable jTableObservations, String fileTemplate, String trialFile, Study study, Workbook workbook, List<Constant> constants) {
        this.jTableObservations = jTableObservations;
        this.fileTemplate = fileTemplate;
        this.trialFile = trialFile;
        this.study = study;
        this.workbook = workbook;
        this.constants = constants;

        ObservationsTableModel model = (ObservationsTableModel) jTableObservations.getModel();
        variates = model.getVariateList();

    }

    public void exportToExcel(int trialOption, int trialStart, int trialEnd, int trialSelected) {
        ObservationsTableModel modeloOriginal = (ObservationsTableModel) jTableObservations.getModel();

        ObservationsTableModel modeloFilter = new ObservationsTableModel(modeloOriginal.getWorkbook(), modeloOriginal.getVariateList());

        if (trialOption == 0) {
            modeloFilter = modeloOriginal;
        }


        if (trialOption == 1) {

            for (int i = 0; i < modeloOriginal.getRowCount(); i++) {
                if (Integer.parseInt(modeloOriginal.getValueAt(i, 0).toString()) == trialSelected) {
                    List<Object> row = modeloOriginal.getRow(i);
                    modeloFilter.addRow(row);
                }

            }
        }


        if (trialOption == 2) {
            for (int i = 0; i < modeloOriginal.getRowCount(); i++) {
                int trialNum = Integer.parseInt(modeloOriginal.getValueAt(i, 0).toString());
                if (trialNum >= trialStart && trialNum <= trialEnd) {
                    List<Object> row = modeloOriginal.getRow(i);
                    modeloFilter.addRow(row);
                }
            }
        }


        JTable jTableToExport = new JTable();
        jTableToExport.setModel(modeloFilter);

        //File template = new File(this.fileTemplate);
        File destFile = new File(this.trialFile);

        //temporary disable copy template
        //copyFile(template.toString(), destFile.toString() + ".xls");

        saveDataToWorkbook(destFile, jTableToExport);
    }

    /**
     * Copies original template file used by workbook
     * @param myFile
     * @param outputFile 
     */
    private void copyFile(String myFile, String outputFile) {
        try {
            File inFile = new File(myFile);
            File outFile = new File(outputFile);
            FileInputStream in = new FileInputStream(inFile);
            FileOutputStream out = new FileOutputStream(outFile);
            int c;
            while ((c = in.read()) != -1) {
                out.write(c);
            }
            in.close();
            out.close();
        } catch (IOException e) {
            System.err.println("Hubo un error de entrada/salida al copiar Excel template");
        }
    }

    /**
     * Save information to all file
     * @param template
     * @param obsFilterTable 
     */
    private void saveDataToWorkbook(File template, JTable obsFilterTable) {

        String nombreArchivo = template.toString() + ".xls";
        try {

            //InputStream inputStream = new FileInputStream(nombreArchivo);
            //HSSFWorkbook excelBook = new HSSFWorkbook(inputStream);
            //excelBook.removeSheetAt(1);               

            HSSFWorkbook excelBook = new HSSFWorkbook();

            saveDescriptionSheet(excelBook);

            HSSFSheet sheetMeasurements = excelBook.createSheet("Observation");
            HSSFCell celdaExcel;
            HSSFRow filaExcel;

            HSSFFont whiteFont = excelBook.createFont();
            whiteFont.setColor(new HSSFColor.WHITE().getIndex());

            HSSFPalette palette = excelBook.getCustomPalette();

            short marronColor = 10;
            short greenColor = 11;
            short blueColor = 12;


            // Green style
            HSSFCellStyle greenStyle = excelBook.createCellStyle();
            greenStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
            greenStyle.setFillForegroundColor(greenColor);
            greenStyle.setFont(whiteFont);
            //greenStyle.

            // Blue style
            HSSFCellStyle blueStyle = excelBook.createCellStyle();
            blueStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
            blueStyle.setFillForegroundColor(blueColor);
            blueStyle.setFont(whiteFont);

            TableColumnModel tableColumns = obsFilterTable.getColumnModel();
            ObservationsTableModel tableModel = (ObservationsTableModel) obsFilterTable.getModel();


            filaExcel = sheetMeasurements.createRow(0);

            for (int col = 1; col < tableColumns.getColumnCount(); col++) {
                TableColumn tableColumn = tableColumns.getColumn(col);

                celdaExcel = filaExcel.createCell(col - 1, HSSFCell.CELL_TYPE_STRING);
                celdaExcel.setCellValue((String) tableColumn.getHeaderValue());
                if (tableModel.getHeaders().get(col - 1) instanceof Variate) {
                    celdaExcel.setCellStyle(blueStyle);
                } else {
                    celdaExcel.setCellStyle(greenStyle);
                }
            }

            for (int i = 0; i < obsFilterTable.getRowCount(); i++) {
                filaExcel = sheetMeasurements.createRow(i + 1);


                for (int col = 1; col < tableColumns.getColumnCount(); col++) {

                    celdaExcel = filaExcel.createCell(col - 1, HSSFCell.CELL_TYPE_STRING);

                    String valor;
                    try {
                        valor = obsFilterTable.getValueAt(i, col - 1).toString();
                    } catch (NullPointerException ex) {
                        valor = " ";
                    }

                    celdaExcel.setCellValue(valor);
                }

            }

            grabaLibro(excelBook, nombreArchivo);

            String fileSaved = NbBundle.getMessage(FieldBookExcelExporter.class,"FieldBookExcelExporter.saved") + nombreArchivo + ".csv"+ NbBundle.getMessage(FieldBookExcelExporter.class,"FieldBookExcelExporter.wantOpen");

            if (DialogUtil.displayConfirmation(fileSaved, NbBundle.getMessage(FieldBookExcelExporter.class,"FieldBookExcelExporter.exported"), NotifyDescriptor.OK_CANCEL_OPTION)) {
                try {

                    if (Desktop.isDesktopSupported() == true) {
                        Desktop desktop = Desktop.getDesktop();
                        File theFile = new File(nombreArchivo);
                        desktop.open(theFile);
                    }
                } catch (Exception e) {
                    System.out.println("Error opening file");
                }
            }



        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    private void grabaLibro(HSSFWorkbook myBook, String file) {
        File objFile = new File(file);
        FileOutputStream archivoSalida;
        try {
            archivoSalida = new FileOutputStream(objFile);
            myBook.write(archivoSalida);
            archivoSalida.close();

        } catch (FileNotFoundException ex) {
            System.out.println("ERROR AL CREAR EXCEL FILE " + ex);
        } catch (IOException ex) {
            System.out.println("ERROR AL CREAR EXCEL FILE " + ex);
        }
    }

    /**
     * Saves the description sheet from selected variates and constants
     * @param excelBook 
     * @author tmsanchez
     */
    private void saveDescriptionSheet(HSSFWorkbook excelBook) {
        HSSFSheet descriptionSheet = excelBook.createSheet("Description");
        HSSFCell excelCell;
        HSSFRow excelRow;

        int PIXEL_SIZE = 250;
        descriptionSheet.setColumnWidth(NAME, 20 * PIXEL_SIZE);
        descriptionSheet.setColumnWidth(DESCRIPTION, 24 * PIXEL_SIZE);
        descriptionSheet.setColumnWidth(PROPERTY, 30 * PIXEL_SIZE);
        descriptionSheet.setColumnWidth(SCALE, 18 * PIXEL_SIZE);
        descriptionSheet.setColumnWidth(METHOD, 18 * PIXEL_SIZE);
        descriptionSheet.setColumnWidth(DATA_TYPE, 15 * PIXEL_SIZE);
        descriptionSheet.setColumnWidth(VALUE, 20 * PIXEL_SIZE);
        descriptionSheet.setColumnWidth(LABEL, 20 * PIXEL_SIZE);



        HSSFFont whiteFont = excelBook.createFont();
        whiteFont.setColor(new HSSFColor.WHITE().getIndex());

        HSSFPalette palette = excelBook.getCustomPalette();

        short marronColor = 10;
        short greenColor = 11;
        short blueColor = 12;

        //HSSFColor marron = palette.addColor((byte)153, (byte)51, (byte)0);
        palette.setColorAtIndex(marronColor, (byte) 153, (byte) 51, (byte) 0);
        palette.setColorAtIndex(greenColor, (byte) 51, (byte) 153, (byte) 102);
        palette.setColorAtIndex(blueColor, (byte) 51, (byte) 51, (byte) 153);


        // Marron style
        HSSFCellStyle marronStyle = excelBook.createCellStyle();
        marronStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
        marronStyle.setFillForegroundColor(marronColor);
        marronStyle.setFont(whiteFont);

        // Green style
        HSSFCellStyle greenStyle = excelBook.createCellStyle();
        greenStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
        greenStyle.setFillForegroundColor(greenColor);
        greenStyle.setFont(whiteFont);
        //greenStyle.

        // Blue style
        HSSFCellStyle blueStyle = excelBook.createCellStyle();
        blueStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
        blueStyle.setFillForegroundColor(blueColor);
        blueStyle.setFont(whiteFont);

        //currentRow++;
        excelRow = descriptionSheet.createRow(currentRow);
        writeStudySection(descriptionSheet, marronStyle, excelRow);

        currentRow++;
        currentRow++;

        excelRow = descriptionSheet.createRow(currentRow);
        writeSectionHeaders(descriptionSheet, "CONDITION", greenStyle, excelRow);
        // now all conditions
        writeConditions(descriptionSheet, workbook.getStudyConditions());
        writeConditions(descriptionSheet, workbook.getConditions());

        currentRow++;
        excelRow = descriptionSheet.createRow(currentRow);
        writeSectionHeaders(descriptionSheet, "FACTOR", greenStyle, excelRow);
        writeFactors(descriptionSheet, workbook.getFactors());

        currentRow++;
        excelRow = descriptionSheet.createRow(currentRow);
        writeSectionHeaders(descriptionSheet, "CONSTANT", blueStyle, excelRow);
        writeConstants(descriptionSheet, constants);

        currentRow++;
        excelRow = descriptionSheet.createRow(currentRow);
        writeSectionHeaders(descriptionSheet, "VARIATE", blueStyle, excelRow);
        writeVariates(descriptionSheet, variates);


    }

    private void writeStudySection(HSSFSheet descriptionSheet, HSSFCellStyle marronStyle, HSSFRow excelRow) {

        HSSFCell excelCell;
        excelCell = excelRow.createCell(0, HSSFCell.CELL_TYPE_STRING);
        excelCell.setCellValue("STUDY");
        excelCell.setCellStyle(marronStyle);
        excelCell = excelRow.createCell(1, HSSFCell.CELL_TYPE_STRING);
        excelCell.setCellValue(study.getStudy());
        currentRow++;

        excelRow = descriptionSheet.createRow(currentRow);
        excelCell = excelRow.createCell(0, HSSFCell.CELL_TYPE_STRING);
        excelCell.setCellValue("TITLE");
        excelCell.setCellStyle(marronStyle);
        excelCell = excelRow.createCell(1, HSSFCell.CELL_TYPE_STRING);
        excelCell.setCellValue(study.getTitle());
        currentRow++;

        excelRow = descriptionSheet.createRow(currentRow);
        excelCell = excelRow.createCell(0, HSSFCell.CELL_TYPE_STRING);
        excelCell.setCellValue("PMKEY");
        excelCell.setCellStyle(marronStyle);
        excelCell = excelRow.createCell(1, HSSFCell.CELL_TYPE_STRING);
        excelCell.setCellValue(study.getPmkey());
        currentRow++;

        excelRow = descriptionSheet.createRow(currentRow);
        excelCell = excelRow.createCell(0, HSSFCell.CELL_TYPE_STRING);
        excelCell.setCellValue("OBJECTIVE");
        excelCell.setCellStyle(marronStyle);
        excelCell = excelRow.createCell(1, HSSFCell.CELL_TYPE_STRING);
        excelCell.setCellValue(study.getObjective());
        currentRow++;

        excelRow = descriptionSheet.createRow(currentRow);
        excelCell = excelRow.createCell(0, HSSFCell.CELL_TYPE_STRING);
        excelCell.setCellValue("START DATE");
        excelCell.setCellStyle(marronStyle);
        excelCell = excelRow.createCell(1, HSSFCell.CELL_TYPE_STRING);
        excelCell.setCellValue(study.getStarDate());
        currentRow++;

        excelRow = descriptionSheet.createRow(currentRow);
        excelCell = excelRow.createCell(0, HSSFCell.CELL_TYPE_STRING);
        excelCell.setCellValue("END DATE");
        excelCell.setCellStyle(marronStyle);
        excelCell = excelRow.createCell(1, HSSFCell.CELL_TYPE_STRING);
        excelCell.setCellValue(study.getStarDate());
    }

    /**
     * 
     * @param descriptionSheet
     * @param currentRow
     * @param nameText
     * @param cellStyle 
     */
    private void writeSectionHeaders(HSSFSheet descriptionSheet, String nameText, HSSFCellStyle cellStyle, HSSFRow excelRow) {

        HSSFCell excelCell;
        // CONDITIONS
        excelCell = excelRow.createCell(NAME, HSSFCell.CELL_TYPE_STRING);
        excelCell.setCellValue(nameText);
        excelCell.setCellStyle(cellStyle);

        excelCell = excelRow.createCell(DESCRIPTION, HSSFCell.CELL_TYPE_STRING);
        excelCell.setCellValue("DESCRIPTION");
        excelCell.setCellStyle(cellStyle);

        excelCell = excelRow.createCell(PROPERTY, HSSFCell.CELL_TYPE_STRING);
        excelCell.setCellValue("PROPERTY");
        excelCell.setCellStyle(cellStyle);

        excelCell = excelRow.createCell(SCALE, HSSFCell.CELL_TYPE_STRING);
        excelCell.setCellValue("SCALE");
        excelCell.setCellStyle(cellStyle);

        excelCell = excelRow.createCell(METHOD, HSSFCell.CELL_TYPE_STRING);
        excelCell.setCellValue("METHOD");
        excelCell.setCellStyle(cellStyle);

        excelCell = excelRow.createCell(DATA_TYPE, HSSFCell.CELL_TYPE_STRING);
        excelCell.setCellValue("DATA TYPE");
        excelCell.setCellStyle(cellStyle);

        excelCell = excelRow.createCell(VALUE, HSSFCell.CELL_TYPE_STRING);
        excelCell.setCellValue("VALUE");
        excelCell.setCellStyle(cellStyle);

        excelCell = excelRow.createCell(LABEL, HSSFCell.CELL_TYPE_STRING);
        excelCell.setCellValue("LABEL");
        excelCell.setCellStyle(cellStyle);

        currentRow++;
    }

    /**
     * 
     * @param descriptionSheet
     * @param currentRow
     * @param conditionList 
     */
    private void writeConditions(HSSFSheet descriptionSheet, List<Condition> conditionList) {
        HSSFRow excelRow;
        HSSFCell excelCell;
        for (Condition condition : conditionList) {
            if (!condition.getProperty().equalsIgnoreCase("STUDY") && !condition.getScale().equalsIgnoreCase("NAME")) {
                excelRow = descriptionSheet.createRow(currentRow);

                excelCell = excelRow.createCell(NAME, HSSFCell.CELL_TYPE_STRING);;
                excelCell.setCellValue(condition.getConditionName());

                excelCell = excelRow.createCell(DESCRIPTION, HSSFCell.CELL_TYPE_STRING);;
                excelCell.setCellValue(condition.getDescription());

                excelCell = excelRow.createCell(PROPERTY, HSSFCell.CELL_TYPE_STRING);;
                excelCell.setCellValue(condition.getProperty());

                excelCell = excelRow.createCell(SCALE, HSSFCell.CELL_TYPE_STRING);;
                excelCell.setCellValue(condition.getScale());

                excelCell = excelRow.createCell(METHOD, HSSFCell.CELL_TYPE_STRING);;
                excelCell.setCellValue(condition.getMethod());

                excelCell = excelRow.createCell(DATA_TYPE, HSSFCell.CELL_TYPE_STRING);;
                excelCell.setCellValue(condition.getDataType());

                if (condition.getValue() != null) {
                    excelCell = excelRow.createCell(VALUE, HSSFCell.CELL_TYPE_STRING);;
                    excelCell.setCellValue(condition.getValue().toString());
                }

                excelCell = excelRow.createCell(LABEL, HSSFCell.CELL_TYPE_STRING);;
                excelCell.setCellValue(condition.getLabel());

                currentRow++;
            }
        }
    }

    /**
     * 
     * @param descriptionSheet
     * @param currentRow
     * @param factorList 
     */
    private void writeFactors(HSSFSheet descriptionSheet, List<Factor> factorList) {
        HSSFRow excelRow;
        HSSFCell excelCell;
        for (Factor factor : factorList) {
            excelRow = descriptionSheet.createRow(currentRow);

            excelCell = excelRow.createCell(NAME, HSSFCell.CELL_TYPE_STRING);;
            excelCell.setCellValue(factor.getFactorName());

            excelCell = excelRow.createCell(DESCRIPTION, HSSFCell.CELL_TYPE_STRING);;
            excelCell.setCellValue(factor.getDescription());

            excelCell = excelRow.createCell(PROPERTY, HSSFCell.CELL_TYPE_STRING);;
            excelCell.setCellValue(factor.getProperty());

            excelCell = excelRow.createCell(SCALE, HSSFCell.CELL_TYPE_STRING);;
            excelCell.setCellValue(factor.getScale());

            excelCell = excelRow.createCell(METHOD, HSSFCell.CELL_TYPE_STRING);;
            excelCell.setCellValue(factor.getMethod());

            excelCell = excelRow.createCell(DATA_TYPE, HSSFCell.CELL_TYPE_STRING);;
            excelCell.setCellValue(factor.getDataType());

            excelCell = excelRow.createCell(VALUE, HSSFCell.CELL_TYPE_STRING);;
            //excelCell.setCellValue(factor.getValue().toString());

            excelCell = excelRow.createCell(LABEL, HSSFCell.CELL_TYPE_STRING);;
            excelCell.setCellValue(factor.getLabel());

            currentRow++;
        }
    }

    /**
     * 
     * @param descriptionSheet
     * @param currentRow
     * @param constantList 
     */
    private void writeConstants(HSSFSheet descriptionSheet, List<Constant> constantList) {
        HSSFRow excelRow;
        HSSFCell excelCell;
        for (Constant constant : constantList) {
            excelRow = descriptionSheet.createRow(currentRow);
            excelCell = excelRow.createCell(NAME, HSSFCell.CELL_TYPE_STRING);;
            excelCell.setCellValue(constant.getConstantName());

            excelCell = excelRow.createCell(DESCRIPTION, HSSFCell.CELL_TYPE_STRING);;
            excelCell.setCellValue(constant.getDescription());

            excelCell = excelRow.createCell(PROPERTY, HSSFCell.CELL_TYPE_STRING);;
            excelCell.setCellValue(constant.getProperty());

            excelCell = excelRow.createCell(SCALE, HSSFCell.CELL_TYPE_STRING);;
            excelCell.setCellValue(constant.getScale());

            excelCell = excelRow.createCell(METHOD, HSSFCell.CELL_TYPE_STRING);;
            excelCell.setCellValue(constant.getMethod());

            excelCell = excelRow.createCell(DATA_TYPE, HSSFCell.CELL_TYPE_STRING);;
            excelCell.setCellValue(constant.getDataType());

            excelCell = excelRow.createCell(VALUE, HSSFCell.CELL_TYPE_STRING);;
            if (constant.getValue() != null) {
                excelCell.setCellValue(constant.getValue().toString());
            }


            excelCell = excelRow.createCell(LABEL, HSSFCell.CELL_TYPE_STRING);;
            //excelCell.setCellValue(constant.getLabel());

            currentRow++;
        }
    }

    /**
     * 
     * @param descriptionSheet
     * @param currentRow
     * @param variateList 
     */
    private void writeVariates(HSSFSheet descriptionSheet, List<Variate> variateList) {
        HSSFRow excelRow;
        HSSFCell excelCell;
        for (Variate variate : variateList) {
            excelRow = descriptionSheet.createRow(currentRow);
            excelCell = excelRow.createCell(NAME, HSSFCell.CELL_TYPE_STRING);;
            excelCell.setCellValue(variate.getVariateName());

            excelCell = excelRow.createCell(DESCRIPTION, HSSFCell.CELL_TYPE_STRING);;
            excelCell.setCellValue(variate.getDescription());

            excelCell = excelRow.createCell(PROPERTY, HSSFCell.CELL_TYPE_STRING);;
            excelCell.setCellValue(variate.getProperty());

            excelCell = excelRow.createCell(SCALE, HSSFCell.CELL_TYPE_STRING);;
            excelCell.setCellValue(variate.getScale());

            excelCell = excelRow.createCell(METHOD, HSSFCell.CELL_TYPE_STRING);;
            excelCell.setCellValue(variate.getMethod());

            excelCell = excelRow.createCell(DATA_TYPE, HSSFCell.CELL_TYPE_STRING);;
            excelCell.setCellValue(variate.getDataType());

            excelCell = excelRow.createCell(VALUE, HSSFCell.CELL_TYPE_STRING);;
            //excelCell.setCellValue(variate.getValue().toString());


            excelCell = excelRow.createCell(LABEL, HSSFCell.CELL_TYPE_STRING);;
            //excelCell.setCellValue(constant.getLabel());

            currentRow++;
        }
    }
}
