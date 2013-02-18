package ibfb.studyeditor.core.db;

import ibfb.domain.core.Condition;
import ibfb.domain.core.Constant;
import ibfb.domain.core.Measurement;
import ibfb.domain.core.MeasurementData;
import ibfb.domain.core.Study;
import ibfb.domain.core.Variate;
import ibfb.domain.core.Workbook;
import ibfb.studyeditor.core.StudyEditorTopComponent;
import ibfb.studyeditor.core.model.ExperimentalConditionsTableModel;
import ibfb.studyeditor.core.model.GermplasmEntriesTableModel;
import ibfb.studyeditor.core.model.ObservationsTableModel;
import ibfb.studyeditor.core.model.StudyConditionsTableModel;
import ibfb.studyeditor.core.model.TrialConditionsTableModel;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.beanutils.BeanUtils;
import org.cimmyt.cril.ibwb.api.AppServicesProxy;
import org.cimmyt.cril.ibwb.commongui.ConvertUtils;
import org.openide.util.Exceptions;

/**
 * Helper Class to save IBFieldbook
 *
 * @author TMSANCHEZ
 */
public class WorkbookSavingHelper {

    /**
     *
     * @param workbook
     */
    public static void saveFieldbook(StudyEditorTopComponent studyEditor) {

        Workbook savingWorkbook = new Workbook();


        savingWorkbook.setStudy(getStudy(studyEditor));
        // assign number of instances
        Integer instanceNumber = Integer.parseInt(studyEditor.jLabelInstances.getText());
        savingWorkbook.setInstanceNumber(instanceNumber);

        // study conditions
        savingWorkbook.setStudyConditions(getStudyConditions(studyEditor));

        savingWorkbook.setConditions(getConditions(studyEditor));

        // assign studyConditions data
        savingWorkbook.setConditionsData(getConditionsData(studyEditor, instanceNumber.intValue()));

        // assign factors
        savingWorkbook.setFactors(studyEditor.getMyWorkbook().getFactors());



        //assign selected constants
        savingWorkbook.setConstants(getSelectedConstants(studyEditor));

        // assign constants data 
        savingWorkbook.setConstantsData(getConstantData(studyEditor));

        // assign selected variates
        savingWorkbook.setVariates(getSelectedVariates(studyEditor));

        //savingWorkbook.setFactorsData(); 

        savingWorkbook.setGermplasmData(getGermplasmData(studyEditor));

        savingWorkbook.setMeasurements(getMeasurements(studyEditor));

        savingWorkbook.getValuesForGroupingLabels();


        // if study already exists then update it
        if (studyEditor.isStudyAlreadyExists()) {
            AppServicesProxy.getDefault().appServices().updateWorkbook(savingWorkbook);


        } else {
            // study does not exist!, then create it 
            AppServicesProxy.getDefault().appServices().saveWorkbookFull(savingWorkbook);

            // but if user press save study already exists
            studyEditor.setStudyAlreadyExists(true);


        }



    }

    /**
     * Get data for study
     *
     * @param studyEditor
     * @return
     */
    private static Study getStudy(StudyEditorTopComponent studyEditor) {
        Study study = new Study();

        //study.setStudy(studyEditor.jTextFieldStudy.getText());

        study.setStudy(studyEditor.getStudyInfo().getStudy());

        study.setTitle(studyEditor.jTextFieldTitle.getText());
        study.setObjective(studyEditor.jTextFieldObjective.getText());
        study.setPmkey(studyEditor.jTextFieldPMKey.getText());


        study = studyEditor.getStudy();
        study.setStudy(studyEditor.getStudyInfo().getStudy());
        study.setShierarchy(studyEditor.getStudy().getStudyid());
        study.setStudyType(Study.S_TYPE_TRIAL);
        study.setSstatus(1);

        if (studyEditor.isStudyAlreadyExists()) {
            //org.cimmyt.cril.ibwb.domain.Study existingStudy = AppServicesProxy.getDefault().appServices().getStudyByName(studyEditor.jTextFieldStudy.getText().trim());
            org.cimmyt.cril.ibwb.domain.Study existingStudy = AppServicesProxy.getDefault().appServices().getStudyByName(studyEditor.getjTextTrialName().getText().trim());
            study.setStudyid(existingStudy.getStudyid());
            study.setStudy(existingStudy.getSname());
            study.setStudy(studyEditor.getjTextTrialName().getText());
            study.setTitle(studyEditor.jTextFieldTitle.getText());
            study.setObjective(studyEditor.jTextFieldObjective.getText());
            study.setPmkey(studyEditor.jTextFieldPMKey.getText());
            study.setStarDate(ConvertUtils.getIntegerAsDate(existingStudy.getSdate()));
            study.setEndDate(ConvertUtils.getIntegerAsDate(existingStudy.getEdate()));
            study.setShierarchy(existingStudy.getShierarchy());
        } else {
            study.setStudyid(null);
        }

        return study;
    }

    /**
     * Get all conditions for study
     *
     * @return
     */
    private static List<Condition> getStudyConditions(StudyEditorTopComponent studyEditor) {
        List<Condition> studyConditions = new ArrayList<Condition>();

        StudyConditionsTableModel conditionsTable = (StudyConditionsTableModel) studyEditor.jTableStudyConditions.getModel();

        studyConditions = conditionsTable.getStudyConditions();

        return studyConditions;
    }

    /**
     * Assign Conditions for study where label equals trial
     *
     * @param studyEditor
     * @return
     */
    private static List<Condition> getConditions(StudyEditorTopComponent studyEditor) {
        List<Condition> conditionList = new ArrayList<Condition>();
        for (Condition condition : studyEditor.getMyWorkbook().getConditions()) {

//            if (!condition.getConditionName().equals(condition.getLabel())) {
            conditionList.add(condition);
//            }

        }

        return conditionList;
    }

    /*
     * Get selected variate from editor. It checks first selected variates in
     * listbox, then find it int variates list from template, if found then add
     * it to list
     */
    public static List<Variate> getSelectedVariates(StudyEditorTopComponent studyEditor) {
        List<Variate> variates = new ArrayList<Variate>();
        variates = studyEditor.getDoubleListPanel().getTargetList();

        return variates;
    }

    /**
     * Get the list of GERMPLASM from editor
     *
     * @param studyEditor
     * @return
     */
    private static List<List<Object>> getGermplasmData(StudyEditorTopComponent studyEditor) {
        GermplasmEntriesTableModel tableModel = (GermplasmEntriesTableModel) studyEditor.jTableEntries.getModel();
        List<List<Object>> germplasmData = tableModel.getGermplasmData();
        return germplasmData;
    }

    /**
     *
     * @param studyEditor
     * @return
     */
    public static List<Constant> getSelectedConstants(StudyEditorTopComponent studyEditor) {
        List<Constant> selectedConstants = new ArrayList<Constant>();

        List<String> differentConstants = new ArrayList<String>();

        ExperimentalConditionsTableModel constantsTable = (ExperimentalConditionsTableModel) studyEditor.jTableConstants.getModel();

        // first get different constants from constants table, because it contains 
        // repeated constans for each Trial
        for (int row = 0; row < constantsTable.getRowCount(); row++) {
            String constantName = (String) constantsTable.getValueAt(row, 1);
            // put only different constants
            if (!differentConstants.contains(constantName)) {
                differentConstants.add(constantName);
            }
        }

        // then, get selected constants from readed constants from template,
        // (this, because constants in constants grid does not contain property and method)

        for (String constantName : differentConstants) {
            // now iterate constans to search constant name
            for (Constant constant : studyEditor.getMyWorkbook().getConstants()) {
                // Check if selected constant exists in constants from template
                if (constantName.equals(constant.getConstantName())) {
                    selectedConstants.add(constant);
                    break;
                }
            }
        }


        return selectedConstants;
    }

    /**
     * Get study conditions for each instance, this condition list contains
     * filled values for each factor
     *
     * @param studyEditor
     * @return
     */
    private static List<Condition> getConditionsData(StudyEditorTopComponent studyEditor, int totalInstances) {
        List<Condition> conditionsData = new ArrayList<Condition>();
        TrialConditionsTableModel trialConditions = (TrialConditionsTableModel) studyEditor.jTableTrialConditions.getModel();
        //conditionsData = studyConditions.getTrialConditions();

        int totalConditions = studyEditor.getMyWorkbook().getConditions().size();
        int currentInstance = 0;
        int instanceCounter = 1;

        if (!studyEditor.isStudyAlreadyExists()) {

            Condition trialInstanceCondition = studyEditor.getMyWorkbook().getTrialInstanceCondition();

            for (instanceCounter = 1; instanceCounter <= totalInstances; instanceCounter++) {
                Condition instanceCondition = new Condition();
                try {
                    BeanUtils.copyProperties(instanceCondition, trialInstanceCondition);

                } catch (Exception ex) {
                    //Exceptions.printStackTrace(ex);
                }
                instanceCondition.setInstance(instanceCounter);
                instanceCondition.setValue(instanceCounter);
                conditionsData.add(instanceCondition);
                addAllConstantsDataFromInstance(trialConditions.getTrialConditions(), instanceCounter, conditionsData);

            }
        } else {
            //conditionsData = studyConditions.getTrialConditions();

            for (Condition condition : trialConditions.getTrialConditions()) {


                Condition instanceCondition = new Condition();
                try {
                    BeanUtils.copyProperties(instanceCondition, condition);

                } catch (Exception ex) {
                    //Exceptions.printStackTrace(ex);
                }
                conditionsData.add(instanceCondition);


                currentInstance++;
            }
        }
        return conditionsData;
    }

    /**
     *
     * @param trialConditions
     * @param instanceCounter
     * @param conditionsData
     */
    private static void addAllConstantsDataFromInstance(List<Condition> trialConditions, int instanceCounter, List<Condition> conditionsData) {
        for (Condition condition : trialConditions) {
            if (condition.getInstance().intValue() == instanceCounter) {
                conditionsData.add(condition);
            }
        }
    }

    private static List<Measurement> getMeasurements(StudyEditorTopComponent studyEditor) {
        List<Measurement> measurements = new ArrayList<Measurement>();

        ObservationsTableModel model = (ObservationsTableModel) studyEditor.jTableObservations.getModel();

        int colNumber = 0;
        //---------------- Generate measurement
        for (int row = 0; row < model.getRowCount(); row++) {
            Measurement m = new Measurement();
            List<MeasurementData> mdList = new ArrayList<MeasurementData>();
            for (int col = 0; col < model.getColumnCount(); col++) {

                colNumber = model.getHeaderIndex(ObservationsTableModel.TRIAL);
                if (colNumber != -1) {
                    try {
                        m.setTrial(Integer.parseInt(model.getValueAt(row, colNumber).toString()));
                    } catch (NullPointerException ex) {
                        m.setTrial(0);
                    }
                }
                colNumber = model.getHeaderIndex(ObservationsTableModel.ENTRY);
                if (colNumber != -1) {
                    try {
                        m.setEntry(Integer.parseInt(model.getValueAt(row, colNumber).toString()));
                    } catch (NullPointerException ex) {
                        m.setEntry(0);
                    }
                }
                colNumber = model.getHeaderIndex(ObservationsTableModel.ENTRY_CODE);
                if (colNumber != -1) {
                    //m.set(Integer.parseInt(model.getValueAt(row, colNumber).toString()));                    
                }

                colNumber = model.getHeaderIndex(ObservationsTableModel.DESIG);
                if (colNumber != -1) {
                    try {
                        m.setDesignation(model.getValueAt(row, colNumber).toString());
                    } catch (NullPointerException ex) {
                        m.setDesignation("");
                    }
                }
                colNumber = model.getHeaderIndex(ObservationsTableModel.GID);
                if (colNumber != -1) {
                    try {
                        m.setGid(Integer.parseInt(model.getValueAt(row, colNumber).toString()));
                    } catch (NullPointerException ex) {
                        m.setGid(0);
                    }
                }

                if (model.getHeaderIndex(ObservationsTableModel.PLOT) > 0) {
                    colNumber = model.getHeaderIndex(ObservationsTableModel.PLOT);
                } else {
                    colNumber = model.getHeaderIndex(ObservationsTableModel.PLOTNUMBER);
                }



                if (colNumber != -1) {
                    try {
                        m.setPlot(Integer.parseInt(model.getValueAt(row, colNumber).toString()));
                    } catch (NullPointerException ex) {
                        m.setPlot(0);
                    }
                }
                colNumber = model.getHeaderIndex(ObservationsTableModel.REPLICATION);
                if (colNumber != -1) {
                    try {
                        if (model.getValueAt(row, colNumber).toString() != null && !model.getValueAt(row, colNumber).toString().isEmpty()) {
                            m.setReplication(Integer.parseInt(model.getValueAt(row, colNumber).toString()));
                        }
                    } catch (NullPointerException ex) {
                        m.setReplication(0);
                    }
                }
                colNumber = model.getHeaderIndex(ObservationsTableModel.BLOCK);
                if (colNumber != -1) {
                    try {
                        m.setBlock(Integer.parseInt(model.getValueAt(row, colNumber).toString()));
                    } catch (NullPointerException ex) {
                        m.setBlock(0);
                    }

                }

                colNumber = model.getHeaderIndex(ObservationsTableModel.ROW);
                if (colNumber != -1) {
                    try {
                        m.setRow(Integer.parseInt(model.getValueAt(row, colNumber).toString()));
                    } catch (NullPointerException ex) {
                        m.setRow(0);
                    }

                }
                colNumber = model.getHeaderIndex(ObservationsTableModel.COL);
                if (colNumber != -1) {
                    try {
                        m.setColumn(Integer.parseInt(model.getValueAt(row, colNumber).toString()));
                    } catch (NullPointerException ex) {
                        m.setColumn(0);
                    }
                }

                if (model.getHeaders().get(col) instanceof Variate) {
                    Variate headerVariate = (Variate) model.getHeaders().get(col);
                    // create the measurement
                    MeasurementData md = new MeasurementData();
                    // assign variate from header
                    md.setVariate(headerVariate);

                    //md.setData("N", model.getValueAt(row, col));
                    md.setData(headerVariate.getDataType(), model.getValueAt(row, col));
                    mdList.add(md);
                }
            }
            m.setMeasurementsData(mdList);
            measurements.add(m);
        }

        return measurements;
    }

    private static List<Constant> getConstantData(StudyEditorTopComponent studyEditor) {
        List<Constant> constantDataList = new ArrayList<Constant>();
        ExperimentalConditionsTableModel model = (ExperimentalConditionsTableModel) studyEditor.getjTableConstants().getModel();
        constantDataList = model.getConstantList();
        return constantDataList;
    }
}
