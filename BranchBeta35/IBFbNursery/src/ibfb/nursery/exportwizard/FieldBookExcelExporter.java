package ibfb.nursery.exportwizard;

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
import org.cimmyt.cril.ibwb.commongui.ConvertUtils;
import org.cimmyt.cril.ibwb.commongui.DialogUtil;
import org.openide.NotifyDescriptor;
import org.openide.util.NbBundle;

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
    private JTable jtableStudyCondition;
    private JTable jtableTrialCondition;
    private JTable jtableConstants;
    private Study study;
    private int currentRow = 0;

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

            JTable jTableToExport = new JTable();
            int trial = 0;
            int current = 0;



            for (int i = 0; i < modeloOriginal.getRowCount(); i++) {

                trial = Integer.parseInt(modeloOriginal.getValueAt(i, 0).toString());
                List<Object> row = modeloOriginal.getRow(i);
                modeloFilter.addRow(row);
            }

            createFile(jTableToExport, modeloFilter, current);

            return;
        }



        if (trialOption == 1) {
            for (int i = 0; i < modeloOriginal.getRowCount(); i++) {
                if (Integer.parseInt(modeloOriginal.getValueAt(i, 0).toString()) == trialSelected) {
                    List<Object> row = modeloOriginal.getRow(i);
                    modeloFilter.addRow(row);
                }

            }
            JTable jTableToExport = new JTable();
            jTableToExport.setModel(modeloFilter);
            File destFile = new File(this.trialFile);
            saveDataToWorkbook(destFile, jTableToExport, 1);
        }


        if (trialOption == 2) {
            JTable jTableToExport = new JTable();

            int trial = trialStart;


            for (int i = 0; i < modeloOriginal.getRowCount(); i++) {


                int trialNum = Integer.parseInt(modeloOriginal.getValueAt(i, 0).toString());
                int current = trialNum;

                if (trialNum >= trialStart && trialNum <= trialEnd) {



                    if (trial == current) {
                        List<Object> row = modeloOriginal.getRow(i);
                        modeloFilter.addRow(row);

                    } else {

                        createFile(jTableToExport, modeloFilter, trial);


                        trial = current;
                        modeloFilter = new ObservationsTableModel(modeloOriginal.getWorkbook(), modeloOriginal.getVariateList());
                        List<Object> row = modeloOriginal.getRow(i);
                        modeloFilter.addRow(row);

                    }
                }
            }

            createFile(jTableToExport, modeloFilter, trial);
        }

    }

    /**
     * Copies original template file used by workbook
     *
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
     *
     * @param template
     * @param obsFilterTable
     */
    private void saveDataToWorkbook(File template, JTable obsFilterTable, int trial) {

        String nombreArchivo = template.toString() + ".xls";
        try {

            //InputStream inputStream = new FileInputStream(nombreArchivo);
            //HSSFWorkbook excelBook = new HSSFWorkbook(inputStream);
            //excelBook.removeSheetAt(1);               

            HSSFWorkbook excelBook = new HSSFWorkbook();

            saveDescriptionSheet(excelBook, trial);

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

            System.out.println("Columnas totales: " + obsFilterTable.getColumnModel().getColumnCount());

            for (int i = 0; i < obsFilterTable.getColumnModel().getColumnCount(); i++) {
                System.out.println("COLUMNAS: " + tableColumns.getColumn(i).getHeaderValue().toString());

            }


            ObservationsTableModel tableModel = (ObservationsTableModel) obsFilterTable.getModel();


            filaExcel = sheetMeasurements.createRow(0);

            for (int col = 0; col < tableColumns.getColumnCount(); col++) {
                TableColumn tableColumn = tableColumns.getColumn(col);

                celdaExcel = filaExcel.createCell(col, HSSFCell.CELL_TYPE_STRING);
                celdaExcel.setCellValue((String) tableColumn.getHeaderValue());
                if (tableModel.getHeaders().get(col) instanceof Variate) {
                    celdaExcel.setCellStyle(blueStyle);
                } else {
                    celdaExcel.setCellStyle(greenStyle);
                }
            }

            boolean isNumeric = false;

            for (int i = 0; i < obsFilterTable.getRowCount(); i++) {
                filaExcel = sheetMeasurements.createRow(i + 1);


                for (int col = 0; col < tableColumns.getColumnCount(); col++) {

                    isNumeric = isNumericDataType(tableModel.getHeaders().get(col));

                    if (isNumeric) {
                        Double doubleValue = ConvertUtils.getValueAsDouble(obsFilterTable.getValueAt(i, col));
                        if (doubleValue != null) {
                            celdaExcel = filaExcel.createCell(col , HSSFCell.CELL_TYPE_NUMERIC);
                            celdaExcel.setCellValue(doubleValue);
                        }
                    } else {
                        String valor = ConvertUtils.getValueAsString(obsFilterTable.getValueAt(i, col));
                        if (valor != null && !valor.isEmpty()) {
                            celdaExcel = filaExcel.createCell(col , HSSFCell.CELL_TYPE_STRING);
                            celdaExcel.setCellValue(valor);
                        }
                    }
                }

            }

            grabaLibro(excelBook, nombreArchivo);

            String fileSaved = NbBundle.getMessage(FieldBookExcelExporter.class, "FieldBookExcelExporter.saved") + nombreArchivo + NbBundle.getMessage(FieldBookExcelExporter.class, "FieldBookExcelExporter.wantOpen");

            if (DialogUtil.displayConfirmation(fileSaved, NbBundle.getMessage(FieldBookExcelExporter.class, "FieldBookExcelExporter.exported"), NotifyDescriptor.OK_CANCEL_OPTION)) {
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
            System.out.println("ERROR EN -saveDataToWorkbook- :" + e);
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

    private void saveDescriptionSheet(HSSFWorkbook excelBook, int nursery) {
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
        writeSectionHeaders(descriptionSheet, "CONDITION", greenStyle, excelRow,currentRow);
        writeConditions(descriptionSheet, workbook.getStudyConditions());
        writeConditions(descriptionSheet, workbook.getConditions(), nursery);

        currentRow++;
        excelRow = descriptionSheet.createRow(currentRow);
        writeSectionHeaders(descriptionSheet, "FACTOR", greenStyle, excelRow,currentRow);
        writeFactors(descriptionSheet, workbook.getFactors());

        currentRow++;
        excelRow = descriptionSheet.createRow(currentRow);
        writeSectionHeaders(descriptionSheet, "CONSTANT", blueStyle, excelRow,currentRow);
        writeConstants(descriptionSheet, constants, nursery);

        currentRow++;
        excelRow = descriptionSheet.createRow(currentRow);
        writeSectionHeaders(descriptionSheet, "VARIATE", blueStyle, excelRow,currentRow);
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
        excelCell.setCellValue(ConvertUtils.getDateAsString(study.getStarDate()));
        currentRow++;

        excelRow = descriptionSheet.createRow(currentRow);
        excelCell = excelRow.createCell(0, HSSFCell.CELL_TYPE_STRING);
        excelCell.setCellValue("END DATE");
        excelCell.setCellStyle(marronStyle);
        excelCell = excelRow.createCell(1, HSSFCell.CELL_TYPE_STRING);
        excelCell.setCellValue(ConvertUtils.getDateAsString(study.getEndDate()));
        currentRow++;        
        
        excelRow = descriptionSheet.createRow(currentRow);
        excelCell = excelRow.createCell(0, HSSFCell.CELL_TYPE_STRING);
        excelCell.setCellValue("STUDY TYPE");
        excelCell.setCellStyle(marronStyle);
        excelCell = excelRow.createCell(1, HSSFCell.CELL_TYPE_STRING);
        excelCell.setCellValue("N");     
        
      
        HSSFName namedCell = descriptionSheet.getWorkbook().createName();
        namedCell.setNameName("STUDY");
        String reference = descriptionSheet.getSheetName() + "!A" + 1 +":H" + 1; // area reference
        namedCell.setRefersToFormula(reference);        
           
    }

    /**
     *
     * @param descriptionSheet
     * @param currentRow
     * @param nameText
     * @param cellStyle
     */
    private void writeSectionHeaders(HSSFSheet descriptionSheet, String nameText, HSSFCellStyle cellStyle, HSSFRow excelRow, int rowNumber) {

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
        
        HSSFName namedCell = descriptionSheet.getWorkbook().createName();
        namedCell.setNameName(nameText);
        int fixedRow = rowNumber +1;
        String reference = descriptionSheet.getSheetName() + "!$A$" + fixedRow +":$H$" + fixedRow; // area reference
        namedCell.setRefersToFormula(reference);
        

        currentRow++;
    }

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





                String theValue = giveMeConditionValue(condition.getConditionName());

                //  if (condition.getValue() != null) {
                if (!theValue.isEmpty()) {
                    excelCell = excelRow.createCell(VALUE, HSSFCell.CELL_TYPE_STRING);;
                    excelCell.setCellValue(theValue);
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
     * @param conditionList
     */
    private void writeConditions(HSSFSheet descriptionSheet, List<Condition> conditionList, int trial) {
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




                if (condition.getProperty().equals("NURSERY INSTANCE")) {
                    excelCell = excelRow.createCell(VALUE, HSSFCell.CELL_TYPE_STRING);;
                    excelCell.setCellValue(trial);
                } else {



                    String theValue = giveMeConditionValue(condition.getConditionName(), trial);


                    //  if (condition.getValue() != null) {
                    if (!theValue.isEmpty()) {
                        excelCell = excelRow.createCell(VALUE, HSSFCell.CELL_TYPE_STRING);;
                        excelCell.setCellValue(theValue);
                    }

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
    private void writeConstants(HSSFSheet descriptionSheet, List<Constant> constantList, int trial) {
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



            String theValue = giveMeConstantValue(constant.getConstantName(), trial);


            // if (constant.getValue() != null) {

            if (!theValue.isEmpty()) {

                excelCell.setCellValue(theValue);
            } else {
                excelCell.setCellValue("");
            }


            excelCell = excelRow.createCell(LABEL, HSSFCell.CELL_TYPE_STRING);;
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

    private void createFile(JTable jTableToExport, ObservationsTableModel modeloFilter, int current) {
        jTableToExport.setModel(modeloFilter);
        File destFile = new File(this.trialFile);
        saveDataToWorkbook(destFile, jTableToExport, 1);
        currentRow = 0;
    }

    private String giveMeConditionValue(String condition) {


        String value = "";
        for (int i = 0; i < this.jtableStudyCondition.getRowCount(); i++) {

            if (jtableStudyCondition.getValueAt(i, 0).equals(condition)) {
                try {
                    value = jtableStudyCondition.getValueAt(i, 4).toString();
                    System.out.println("VALUE FOUNDED: " + value);
                    break;
                } catch (NullPointerException ex) {
                    value = "";
                }

            }
        }

        return value;
    }

    public void setStudyConditionsTable(JTable StudyConditionsTable) {
        this.jtableStudyCondition = StudyConditionsTable;
    }

    public void setTrialConditionsTable(JTable ConditionsTable) {
        this.jtableTrialCondition = ConditionsTable;
    }

    public void setConstantsTable(JTable ConstantsTable) {
        this.jtableConstants = ConstantsTable;
    }

    private String giveMeConditionValue(String conditionName, int trial) {
        String value = "";

        for (int i = 0; i < this.jtableTrialCondition.getRowCount(); i++) {

            if (jtableTrialCondition.getValueAt(i, 0).equals(trial) && jtableTrialCondition.getValueAt(i, 1).equals(conditionName)) {
                try {
                    value = jtableTrialCondition.getValueAt(i, 5).toString();
                    break;
                } catch (NullPointerException ex) {
                    value = "";
                }

            }
        }

        return value;
    }

    private String giveMeConstantValue(String constantName, int trial) {
        String value = "";

        for (int i = 0; i < this.jtableConstants.getRowCount(); i++) {
            if (jtableConstants.getValueAt(i, 0) != null && jtableConstants.getValueAt(i, 1) != null) {
                if (jtableConstants.getValueAt(i, 0).equals(trial) && jtableConstants.getValueAt(i, 1).equals(constantName)) {
                    try {
                        value = jtableConstants.getValueAt(i, 4).toString();
                        break;
                    } catch (NullPointerException ex) {
                        value = "";
                    }

                }
            }
        }

        return value;
    }

    /**
     * Checks if the column header data type is numeric type
     *
     * @param columnHeader column header to validate
     * @return
     * <code>true</code> if is numeric ,
     * <code>false</code> if not
     */
    private boolean isNumericDataType(Object columnHeader) {
        boolean isNumericType = false;
        if (columnHeader instanceof Variate) {
            Variate variate = (Variate) columnHeader;
            isNumericType = variate.getDataType().equals("N");
        } else if (columnHeader instanceof Factor) {
            Factor factor = (Factor) columnHeader;
            isNumericType = factor.getDataType().equals("N");
        }
        return isNumericType;

    }
}
