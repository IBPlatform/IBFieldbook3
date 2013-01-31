package ibfb.studyeditor.core;

import ibfb.domain.core.Condition;
import ibfb.domain.core.Factor;
import ibfb.domain.core.Workbook;
import ibfb.workbook.api.WorkbookExcelReader;
import ibfb.workbook.core.WorkbookExcelReaderImpl;
import javax.swing.JComboBox;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import org.openide.windows.WindowManager;


public class ExcelReaderClass {

    private Integer numInstances;
    private DefaultTableModel modeloTablaConditions = new DefaultTableModel();
    private DefaultTableModel modeloTablaTrialConditions = new DefaultTableModel();
    private DefaultTableModel modeloTablaConstants = new DefaultTableModel();
    private DefaultTableModel modeloTablaFactors = new DefaultTableModel();
    private DefaultTableModel modeloTablaVariates = new DefaultTableModel();
    private DefaultTableModel modeloTablaMain = new DefaultTableModel();
    private Object[] columnas;
    
    private WorkbookExcelReader myExcelReader = new WorkbookExcelReaderImpl();
    private Workbook myWorkbook = new Workbook();
    StudyEditorTopComponent studyWindow = (StudyEditorTopComponent) WindowManager.getDefault().findTopComponent("StudyEditorTopComponent");

    
    private DefaultTableCellRenderer tcr = new DefaultTableCellRenderer();
    private String file;

    public String getFile() {
        return file;
    }

    public void setFile(String file) {
        this.file = file;
    }

    public Workbook getMyWorkbook() {
        return myWorkbook;
    }

    public void setMyWorkbook(Workbook myWorkbook) {
        this.myWorkbook = myWorkbook;
    }

    public void readExcelWorkbookTemplate() {
        try {
            myWorkbook = myExcelReader.getWorkbookData(this.file);
            setWorkbookDataIntoTables(myWorkbook);

        } catch (Exception ex) {
            System.out.println("ERROR AL LEER EXCEL A: " + ex);
        }

    }

    public void readExcelWorkbookTemplateStudyConditions() {
        try {
            myWorkbook = myExcelReader.getWorkbookData(file);
            setWorkbookDataStudyConditions(myWorkbook);

        } catch (Exception ex) {
            System.out.println("ERROR AL LEER EXCEL B: " + ex);
        }

    }

    public Integer getNumInstances() {
        return numInstances;
    }

    public void setNumInstances(Integer numInstances) {
        this.numInstances = numInstances;
    }


    
    
    @SuppressWarnings("unchecked")

    private void setWorkbookDataStudyConditions(Workbook workbook) {
       
        tcr.setHorizontalAlignment(SwingConstants.CENTER);

// STUDY CONDITIONS
//--------------------------------------------------------------------------------------------------------
        modeloTablaConditions = (DefaultTableModel) studyWindow.jTableStudyConditions.getModel();
        modeloTablaConditions.setNumRows(0);
        java.util.ArrayList<Condition> studyConditions = new java.util.ArrayList();
        studyConditions = (java.util.ArrayList<Condition>) workbook.getStudyConditions();
        modeloTablaConditions.setNumRows(workbook.getStudyConditions().size());
        for (int i = 0; i < studyConditions.size(); i++) {
            studyWindow.jTableStudyConditions.setValueAt(studyConditions.get(i).getConditionName(), i, 0);
            studyWindow.jTableStudyConditions.setValueAt(studyConditions.get(i).getDescription(), i, 1);
            studyWindow.jTableStudyConditions.setValueAt(studyConditions.get(i).getProperty(), i, 2);
            studyWindow.jTableStudyConditions.setValueAt(studyConditions.get(i).getScale(), i, 3);
        }
        if (workbook.getStudyConditions().size() > 0) {
            studyWindow.jTableStudyConditions.setRowSelectionInterval(0, 0);
        }

        JComboBox IDCooperatorComboBox = new JComboBox(new Object[]{"1", "2", "3", "4", "5"});
        IDCooperatorComboBox.setEditable(true);
    }

    @SuppressWarnings("unchecked")
    private void setWorkbookDataIntoTables(Workbook workbook) {
        studyWindow.jLabelInstances.setText(this.getNumInstances().toString());
     
        tcr.setHorizontalAlignment(SwingConstants.CENTER);

// TRIAL CONDITIONS
//--------------------------------------------------------------------------------------------------------
        modeloTablaTrialConditions = (DefaultTableModel) studyWindow.jTableTrialConditions.getModel();
        modeloTablaTrialConditions.setNumRows(0);
        java.util.ArrayList<Condition> trialConditions = new java.util.ArrayList();
        trialConditions = (java.util.ArrayList<Condition>) workbook.getConditions();
        modeloTablaTrialConditions.setNumRows((workbook.getConditions().size()) * this.getNumInstances());
        Integer instance = 0;
        Integer renglon = 0;
        for (int j = 0; j < this.getNumInstances(); j++) {
            for (int i = 0; i < trialConditions.size(); i++) {
                if (!trialConditions.get(i).getConditionName().equals(trialConditions.get(i).getLabel())) {
                    studyWindow.jTableTrialConditions.setValueAt(instance + 1, renglon, 0);
                    studyWindow.jTableTrialConditions.setValueAt(trialConditions.get(i).getConditionName(), renglon, 1);
                    studyWindow.jTableTrialConditions.setValueAt(trialConditions.get(i).getDescription(), renglon, 2);
                    studyWindow.jTableTrialConditions.setValueAt(trialConditions.get(i).getScale(), renglon, 3);
                    renglon++;
                } else {
                    modeloTablaTrialConditions.setNumRows((modeloTablaTrialConditions.getRowCount()) - 1);
                }
            }
            instance++;
        }
        studyWindow.jTableTrialConditions.getColumnModel().getColumn(0).setCellRenderer(tcr);
        if (workbook.getConditions().size() > 0) {
            studyWindow.jTableTrialConditions.setRowSelectionInterval(0, 0);
        }



        // FACTORS
//--------------------------------------------------------------------------------------------------------
        modeloTablaFactors = (DefaultTableModel) studyWindow.jTableOtherFactorLabels.getModel();
        modeloTablaFactors.setNumRows(0);
        java.util.ArrayList<Factor> factores = new java.util.ArrayList();
        factores = (java.util.ArrayList<Factor>) workbook.getFactors();
        modeloTablaFactors.setNumRows(workbook.getFactors().size());
        for (int i = 0; i < factores.size(); i++) {
            studyWindow.jTableOtherFactorLabels.setValueAt(factores.get(i).getFactorName(), i, 0);
            studyWindow.jTableOtherFactorLabels.setValueAt(factores.get(i).getDescription(), i, 1);
            studyWindow.jTableOtherFactorLabels.setValueAt(factores.get(i).getScale(), i, 2);
        }
        if (workbook.getFactors().size() > 0) {
            studyWindow.jTableOtherFactorLabels.setRowSelectionInterval(0, 0);
        }


    }

    public void cambiaTamTablaObservations(int col, int tam) {
        studyWindow.jTableObservations.getColumnModel().getColumn(col).setPreferredWidth(tam);
        studyWindow.jTableObservations.getColumnModel().getColumn(col).setMaxWidth(tam);
        studyWindow.jTableObservations.getColumnModel().getColumn(col).setMinWidth(tam);
    }
}
