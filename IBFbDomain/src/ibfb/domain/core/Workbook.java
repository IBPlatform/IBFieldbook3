package ibfb.domain.core;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author TMSANCHEZ
 */
public class Workbook {

    private static final String TRIAL_INSTANCE_PROPERTY = "TRIALINSTANCE";
//    public static String ENTRY_LABEL = "ENTRY";
//    public static String PLOT_LABEL = "PLOT";
    public static final String STUDY = "STUDY";
    public static final String STUDY_NAME = "STUDYNAME";
//    public static String TRIAL_LABEL = "TRIAL";
    public static final String TRIAL_INSTANCE_NUMBER = "TRIALINSTANCENUMBER";
    public static final String NURSERY_INSTANCE_NUMBER = "NURSERYINSTANCENUMBER";
    public static final String GERMPLASM_ENTRY_NUMBER = "GERMPLASMENTRYNUMBER";
    public static final String GERMPLASM_ENTRY_NESTED_NUMBER = "GERMPLASMENTRYNESTEDNUMBER";
    public static final String GERMPLASM_ENTRYCD_CODE = "GERMPLASMENTRYCODE";
    public static final String GERMPLASM_DESIG_DBCV = "GERMPLASMIDDBCV";
    public static final String GERMPLASM_GID_DBID = "GERMPLASMIDDBID";
    public static final String FIELD_PLOT_NUMBER = "FIELDPLOTNUMBER";
    public static final String FIELD_PLOT_NESTEDNUMBER = "FIELDPLOTNESTEDNUMBER";
    public static final String FIELD_PLOT_REP_NUMBER = "REPLICATIONNUMBER";
    public static final String FIELD_PLOT_BLOCK_NUMBER = "BLOCKNUMBER";
    public static final String FIELD_PLOT_ROW_NUMBER = "ROWINLAYOUTNUMBER";
    public static final String FIELD_PLOT_COL_NUMBER = "COLUMNINLAYOUTNUMBER";
    public static final String TRIAL_INSTANCE_NUMBER_ENUMERATED_N = "TRIALINSTANCENUMBERENUMERATEDN";
    public static final String GERMPLASM_ENTRY_NUMBER_ENUMERATED_N = "GERMPLASMENTRYNUMBERENUMERATEDN";
    public static final String FIELD_PLOT_NUMBER_ENUMERATED_N = "FIELDPLOTNUMBERENUMERATEDN";
    public static final String FIELD_PLOT_NESTED_NUMBER_ENUMERATED_N = "FIELDPLOTNESTEDNUMBERENUMERATEDN";
    public static final String CHECK_CODE_ASSIGNED = "CHECKCODEASSIGNED";
    private static final List<String> constraintWords = new ArrayList<String>();
    private Study study;
    private List<Condition> studyConditions;
    private List<Condition> conditions;
    /**
     * 
     */
    private List<Factor> factors;
    /**
     * Constants for study
     * When reads from template it contains all constants in excel file
     * When storing contains selected constants
     */
    private List<Constant> constants;
    private List<Variate> variates;
    /**
     * Number of instances
     */
    private Integer instanceNumber;
    /**
     * Constants with values data for each instance
     */
    private List<Constant> constantsData;
    /**
     * 
     */
    /**
     * Conditions with values data
     */
    private List<Condition> conditionsData;
    /**
     * 
     */
    private List<Factor> factorsData;
    /**
     * List of germplasm for curren study
     * It contents a list of several rows and each row contains it value for each factor with label "ENTRY"
     * In the same order!
     */
    private List<List<Object>> germplasmData;
    /**
     * Selected variates (Traits) from template with value data
     */
    private List<Variate> variatesData;
    /**
     * All measurement
     */
    private List<Measurement> measurements;
    private List<Measurement> measurementsRep;
    /**
     * Map to store in which column index is stored a variate(trait)
     */
    private Map<Integer, Integer> variatesMap = new HashMap<Integer, Integer>();
    /**
     * Label text used to identify TRIALs
     */
    private String trialLabel;
    /**
     * Label text used to identify ENTRYs
     */
    private String entryLabel;
    /**
     * Label text used to identify ENTRYCD
     */
    private String entryCdLabel;
    /**
     * Label text used to identify DESIG
     */
    private String desigLabel;
    /**
     * Label text used to identify GID
     */
    private String gidLabel;
    /**
     * Label text used to identify PLOTS
     */
    private String plotLabel;
    /**
     * Label text used to identify PLOTS
     */
    private String repLabel;
    /**
     * Label text used to identify PLOTS
     */
    private String blockLabel;
    /**
     * Label text used to identify PLOTS
     */
    private String rowLabel;
    /**
     * Label text used to identify PLOTS
     */
    private String colLabel;
    /**
     * Message message when validating the template
     */
    private String validationMessage;

    public Workbook() {
        initConstraitWords();
    }

    public Workbook(Study study, List<Condition> conditions, List<Factor> factors, List<Constant> constants, List<Variate> variates) {
        this.study = study;
        this.conditions = conditions;
        this.factors = factors;
        this.constants = constants;
        this.variates = variates;
        initConstraitWords();
    }

    private void initConstraitWords() {
        constraintWords.add("STUDY");
        constraintWords.add("TRIALINSTANCE");
        constraintWords.add("GERMPLASMENTRY");
        constraintWords.add("FIELDPLOT");
        constraintWords.add("SAMPLINGUNIT");
        studyConditions = new ArrayList<Condition>();
        conditions = new ArrayList<Condition>();
        factors = new ArrayList<Factor>();
        constants = new ArrayList<Constant>();
    }

    public String[] getConstantAsArray() {
        String[] list = new String[constants.size()];
        for (int i = 0; i < constants.size(); i++) {
            list[i] = constants.get(i).getConstantName();
        }
        return list;
    }

    public String[] getVariateAsArray() {
        String[] list = new String[variates.size()];
        for (int i = 0; i < variates.size(); i++) {
            list[i] = variates.get(i).getVariateName();
        }
        return list;
    }

    public String[] getFactorsAsArray() {
        String[] list = new String[factors.size()];
        for (int i = 0; i < factors.size(); i++) {
            list[i] = factors.get(i).getFactorName();
        }
        return list;
    }

    /**
     * Checks if TRIAL INSTANCE property is present in Study Conditions
     * @return <code>true</code> if exists, <code>false</code> if does not exits
     */
    public boolean hasPropertyTrialInstance() {
        boolean result = false;
        for (Condition condition : conditions) {
            String conditionProperty = condition.getProperty();
            if (getStringWithOutBlanks(conditionProperty).equals(TRIAL_INSTANCE_PROPERTY)) {
                result = true;
                break;
            }
        }
        return result;
    }

    /**
     * Gets the desired label for TRIAL_INSTANCE from TEMPLATE
     * @return Text in column LABEL in row where TRIAL_INSTANCE property is located.
     * if not TRIAL_INSTANCE property found, then return "INSTANCES"
     */
    public String getInstanceLabel() {
        String result = "INSTANCES";

        for (Condition condition : conditions) {
            String conditionProperty = condition.getProperty();
            if (getStringWithOutBlanks(conditionProperty).equals(TRIAL_INSTANCE_PROPERTY)) {
                result = condition.getLabel();
                break;
            }
        }

        return result;
    }

    /**
     * 
     * @param word
     * @return 
     */
    private boolean isConstraintWord(String word) {
        boolean result = false;

        result = constraintWords.contains(getStringWithOutBlanks(word));

        return result;
    }

    /**
     * Get a list of Strings for
     * @return 
     */
    public List<String> getMeasurementHeaders() {
        List<String> measurementHeaders = new ArrayList<String>();

        for (Factor factor : this.getFactors()) {
            measurementHeaders.add(factor.getFactorName());
        }


        return measurementHeaders;
    }

    /**
     * Return a list of OtherTreatmen factors
     * @return 
     */
    public List<Factor> getOtherFactors() {
        List<Factor> otherFactors = new ArrayList<Factor>();
        for (Factor factor : this.factors) {
            String factorName = getStringWithOutBlanks(factor.getFactorName());
            String factorLabel = getStringWithOutBlanks(factor.getLabel());
            if (factorName.equals(factorLabel) && !isConstraintWord(factor.getProperty())) {
                otherFactors.add(factor);
            }
        }
        return otherFactors;
    }

    /**
     * Gets a list of factors and nested factor for each other treatment factor
     * @return 
     */
    public HashMap<String, List<Factor>> getChildFactors() {
        HashMap<String, List<Factor>> childFactors = new HashMap<String, List<Factor>>();

        // Iterate over other factors
        for (Factor otherFactor : getOtherFactors()) {
            List<Factor> children = new ArrayList<Factor>();
            String otherFactorName = getStringWithOutBlanks(otherFactor.getFactorName());
            for (Factor factor : getFactors()) {
                String factorLabel = getStringWithOutBlanks(factor.getLabel());
                if (otherFactorName.equals(factorLabel) && !factor.getFactorName().equals(factor.getLabel())) {
                    children.add(factor);
                }


            }

            childFactors.put(otherFactor.getFactorName(), children);
        }

        return childFactors;


    }

    /**
     * Get the All child factors for an OtherTreatment parent
     * @param parentFactor Other Treatment factor looking for
     * @return List of factor for that parent or empty list if parent does not exist
     */
    public List<Factor> getChildFactors(String parentFactor) {
        List<Factor> childFactors = new ArrayList<Factor>();

        if (getChildFactors().containsKey(parentFactor)) {
            childFactors = getChildFactors().get(parentFactor);
        }

        return childFactors;
    }

    /**
     * Get a list of Factors where Label = ENTRY
     * @return List of Factor with Label = ENTRY or Empty List
     */
    public List<Factor> getEntryFactors() {
        getValuesForGroupingLabels();
        List<Factor> entryFactors = new ArrayList<Factor>();
        for (Factor factor : factors) {
            //if (factor.getLabel().toUpperCase().equals(ENTRY_LABEL)) {
            if (factor.getLabel().toUpperCase().equals(entryLabel)) {
                entryFactors.add(factor);
            }
        }
        return entryFactors;
    }

    /**
     * Get a list of Factors where Label = PLOT
     * @return List of Factor with Label = PLOT or Empty List
     */
    public List<Factor> getPlotFactors() {
        getValuesForGroupingLabels();
        List<Factor> entryFactors = new ArrayList<Factor>();
        for (Factor factor : factors) {
            //if (factor.getLabel().toUpperCase().equals(PLOT_LABEL)) {
            if (factor.getLabel().toUpperCase().equals(plotLabel)) {
                entryFactors.add(factor);
            }
        }
        return entryFactors;
    }

    public List<Condition> getConditions() {
        return conditions;
    }

    public void setConditions(List<Condition> conditions) {
        this.conditions = conditions;
    }

    public List<Constant> getConstants() {
        return constants;
    }

    public void setConstants(List<Constant> constants) {
        this.constants = constants;
    }

    public List<Factor> getFactors() {

        return factors;
        //return getOtherFactors();
    }

    public void setFactors(List<Factor> factors) {
        this.factors = factors;
    }

    public Study getStudy() {
        return study;
    }

    public void setStudy(Study study) {
        this.study = study;
    }

    public List<Variate> getVariates() {
        return variates;
    }

    public void setVariates(List<Variate> variates) {
        this.variates = variates;
        arrangeVariatesMap();
    }

    public List<Condition> getStudyConditions() {
        return studyConditions;
    }

    public void setStudyConditions(List<Condition> studyConditions) {
        this.studyConditions = studyConditions;
    }

    /**
     * Removes all blank spaces and convert to uppercase all characters\
     * in the string.
     * Example "Germ plasm list" will return "GERMPLASMLIST"
     * @param stringValue
     * @return
     */
    public static String getStringWithOutBlanks(String stringValue) {
        String resultString = stringValue;

        // fisrt remove all blank spaces
        resultString = resultString.replaceAll(" ", "");

        // then change to uppercase
        resultString = resultString.toUpperCase();


        return resultString;
    }

    /**
     * @return the instanceNumber
     */
    public Integer getInstanceNumber() {
        return instanceNumber;
    }

    /**
     * @param instanceNumber the instanceNumber to set
     */
    public void setInstanceNumber(Integer instanceNumber) {
        this.instanceNumber = instanceNumber;
    }

    /**
     * @return the conditionsData
     */
    public List<Condition> getConditionsData() {
        return conditionsData;
    }

    /**
     * @param conditionsData the conditionsData to set
     */
    public void setConditionsData(List<Condition> conditionsData) {
        this.conditionsData = conditionsData;
    }

    /**
     * @return the factorsData
     */
    public List<Factor> getFactorsData() {
        return factorsData;
    }

    /**
     * @param factorsData the factorsData to set
     */
    public void setFactorsData(List<Factor> factorsData) {
        this.factorsData = factorsData;
    }

    /**
     * @return the germplasmData
     */
    public List<List<Object>> getGermplasmData() {
        return germplasmData;
    }

    /**
     * @param germplasmData the germplasmData to set
     */
    public void setGermplasmData(List<List<Object>> germplasmData) {
        this.germplasmData = germplasmData;
    }

    /**
     * @return the variatesData
     */
    public List<Variate> getVariatesData() {
        return variatesData;
    }

    /**
     * @param variatesData the variatesData to set
     */
    public void setVariatesData(List<Variate> variatesData) {
        this.variatesData = variatesData;
    }

    /**
     * @return the measurements
     */
    public List<Measurement> getMeasurements() {
        return measurements;
    }

    /**
     * @param measurements the measurements to set
     */
    public void setMeasurements(List<Measurement> measurements) {
        this.measurements = measurements;
    }

    public List<Constant> getConstantsData() {
        return constantsData;
    }

    public void setConstantsData(List<Constant> constantsData) {
        this.constantsData = constantsData;
    }

    public void setGroupRepsMeasurements() {
        if (measurements != null) {
            List<Measurement> measurementsDiferentes = new ArrayList<Measurement>();
            Map<String, Measurement> mapaMeasurements = new HashMap<String, Measurement>();
            
            for (Measurement measurement : this.measurements) {
                
                Integer repHash = measurement.getReplication();
                Integer blockHash = measurement.getBlock();
                Integer plotHash = measurement.getPlot();
                
                if (repHash == null) {
                    repHash = new Integer("1");
                }
                if (blockHash == null) {
                    blockHash = new Integer("1");
                }
                if (plotHash == null) {
                    plotHash = new Integer("1");
                }
                
                String conbinacion = plotHash.toString()
                        + "|" + repHash.toString()
                        + "|" + blockHash.toString();

                if (mapaMeasurements.get(conbinacion) == null) {
                    mapaMeasurements.put(conbinacion, measurement);
                    measurementsDiferentes.add(measurement);
                }
            }
            measurementsRep = measurementsDiferentes;
        }
    }
    
    public void setGroupRepsMeasurementsForNursery() {
        if (measurements != null) {
            List<Measurement> measurementsDiferentes = new ArrayList<Measurement>();
            Map<String, Measurement> mapaMeasurements = new HashMap<String, Measurement>();

            for (Measurement measurement : this.measurements) {
                
                Integer repHash = measurement.getReplication();
                Integer blockHash = measurement.getBlock();
                Integer plotHash = measurement.getPlot();
                
                String convinacion = plotHash.toString();
                
                if (mapaMeasurements.get(convinacion) == null) {
                    mapaMeasurements.put(convinacion, measurement);
                    measurementsDiferentes.add(measurement);
                }
            }
            measurementsRep = measurementsDiferentes;
        }
    }
    
    public List<Measurement> getMeasurementsRep() {
        return measurementsRep;
    }
    
    public void setMeasurementsRep(List<Measurement> measurementsRep) {
        this.measurementsRep = measurementsRep;
    }
    
    /**
     * Looks for combination of PROPERTY + SCALE to get text in LABEL column used
     * to group TRIAL, ENTRY and PLOT
     */
    public void getValuesForGroupingLabels() {
        boolean trialLabelAssigned = false;
        for (Condition condition : conditions) {
            String text = getStringWithOutBlanks(condition.getProperty() + condition.getScale());
            if (TRIAL_INSTANCE_NUMBER.equals(text) && !trialLabelAssigned) {
                //TRIAL_LABEL = condition.getLabel().toUpperCase();
                trialLabel = condition.getLabel().toUpperCase();
                trialLabelAssigned = true;
                break;
            }
        }
        boolean entryLabelAssigned = false;
        for (Factor factor : factors) {
            String text = getStringWithOutBlanks(factor.getProperty() + factor.getScale());
            if ((GERMPLASM_ENTRY_NUMBER.equals(text) || GERMPLASM_ENTRY_NESTED_NUMBER.equals(text)) && !entryLabelAssigned) {

                // ENTRY_LABEL = factor.getLabel().toUpperCase();

                entryLabel = factor.getFactorName().toUpperCase();
                entryLabelAssigned = true;
            } else if (GERMPLASM_ENTRYCD_CODE.equals(text)) {
                entryCdLabel = factor.getFactorName().toUpperCase();
            } else if (GERMPLASM_DESIG_DBCV.equals(text)) {
                desigLabel = factor.getFactorName().toUpperCase();
            } else if (GERMPLASM_GID_DBID.equals(text)) {
                gidLabel = factor.getFactorName().toUpperCase();
            } else if (FIELD_PLOT_NESTEDNUMBER.equals(text) ) {
                //PLOT_LABEL =  factor.getLabel().toUpperCase();
                plotLabel = factor.getFactorName().toUpperCase();
            } else if (FIELD_PLOT_NUMBER.equals(text)) {
                //PLOT_LABEL =  factor.getLabel().toUpperCase();
                plotLabel = factor.getFactorName().toUpperCase();
            }                     
            else if (FIELD_PLOT_REP_NUMBER.equals(text)) {
                repLabel = factor.getFactorName().toUpperCase();
            } else if (FIELD_PLOT_BLOCK_NUMBER.equals(text)) {
                blockLabel = factor.getFactorName().toUpperCase();
            } else if (FIELD_PLOT_ROW_NUMBER.equals(text)) {
                rowLabel = factor.getFactorName().toUpperCase();
            } else if (FIELD_PLOT_COL_NUMBER.equals(text)) {
                colLabel = factor.getFactorName().toUpperCase();
            }
        }

    }

    public void arrangeVariatesMap() {
        int columnIndex = 0;
        variatesMap = new HashMap<Integer, Integer>();
        for (Variate variate : variates) {
            if (variate.getVariateId() != null) {
                variatesMap.put(variate.getVariateId(), columnIndex);
            }
            columnIndex++;
        }
    }

    public int getVariateColumnIndex(Integer variateId) {
        int variateColumnIndex = -1;

        if (variatesMap.containsKey(variateId)) {
            variateColumnIndex = variatesMap.get(variateId);
        }

        return variateColumnIndex;
    }

    /* Checks that the template chosen is valid. Checks for valid rows for
     * TRIAL INSTANCE, FIELD PLOT and GERMPLASM ENTRY
     * 
     * @see http://ibp.generationcp.org/confluence/display/MBP/Application+2.2.1+Tool+2.8+-+Study+Templates
     * @author mtrulat
     * @param none
     * @returns boolean true is template is valid
     */
    public boolean isValidTemplate() {
        boolean hasTrialInstance = false;
        boolean hasGermplasmEntry = false;
        boolean hasFieldPlot = false;

        for (Condition condition : conditions) {

            String text = getStringWithOutBlanks(condition.getProperty() + condition.getScale() + condition.getMethod() + condition.getDataType());

            if (TRIAL_INSTANCE_NUMBER_ENUMERATED_N.equals(text)
                    && condition.getConditionName().equals(condition.getLabel())) {
                hasTrialInstance = true;
            }
        }

        if (!hasTrialInstance) {
            validationMessage = "One of following values is missing in template: \nProperty = TRIAL INSTANCE \n"
                    + "Scale = NUMBER \n"
                    + "Method = ENUMERATED";

            return false;
        }

        for (Factor factor : factors) {
            String text = getStringWithOutBlanks(factor.getProperty() + factor.getScale() + factor.getMethod() + factor.getDataType());

            if (GERMPLASM_ENTRY_NUMBER_ENUMERATED_N.equals(text)
                    && factor.getFactorName().equals(factor.getLabel())) {
                hasGermplasmEntry = true;
            }
            if ((FIELD_PLOT_NESTED_NUMBER_ENUMERATED_N.equals(text)||(FIELD_PLOT_NUMBER_ENUMERATED_N.equals(text)))
                    && factor.getFactorName().equals(factor.getLabel())) {
                hasFieldPlot = true;
            }
        }
        
        if (! hasGermplasmEntry || ! hasFieldPlot) {
           validationMessage = "One of following values is missing in template: ";
        }

        if (!hasGermplasmEntry) {
            validationMessage = validationMessage + "\nProperty = GERMPLASM ENTRY  \n"
                    + "Scale = NUMBER \n"
                    + "Method = ENUMERATED";
        }
        
        if (!hasFieldPlot) {
            validationMessage = validationMessage + "\nProperty = FIELD PLOT  \n"
                    + "Scale = NESTED NUMBER \n"
                    + "Method = ENUMERATED";
        }
        
        

        return (hasTrialInstance && hasGermplasmEntry && hasFieldPlot);
    }

    public boolean isValidNurseryTemplate() {

        return (true);
    }

    public static boolean isPlotLabel(String text) {
        String cleanText = getStringWithOutBlanks(text).toUpperCase();
        
        if(cleanText.equals(FIELD_PLOT_NUMBER)){
           return true; 
        }else if(cleanText.equals(FIELD_PLOT_NESTEDNUMBER)){
              return true; 
        }else{
              return false; 
        }
        
       
      //  return cleanText.equals(FIELD_PLOT_NUMBER) || cleanText.equals(FIELD_PLOT_NESTEDNUMBER);
    }

    public String getEntryLabel() {
        if (entryLabel == null) {
            getValuesForGroupingLabels();
        }
        return entryLabel;
    }

    public void setEntryLabel(String entryLabel) {
        this.entryLabel = entryLabel;
    }

    public String getPlotLabel() {
        if (plotLabel == null) {
            getValuesForGroupingLabels();
        }

        return plotLabel;
    }

    public void setPlotLabel(String plotLabel) {
        this.plotLabel = plotLabel;
    }

    public String getTrialLabel() {
        if (trialLabel == null) {
            getValuesForGroupingLabels();
        }

        return trialLabel;
    }

    public void setTrialLabel(String trialLabel) {
        this.trialLabel = trialLabel;
    }

    public String getEntryCdLabel() {
        if (entryCdLabel == null) {
            getValuesForGroupingLabels();
        }
        return entryCdLabel;
    }

    public void setEntryCdLabel(String entryCdLabel) {
        this.entryCdLabel = entryCdLabel;
    }

    public String getDesigLabel() {
        if (desigLabel == null) {
            getValuesForGroupingLabels();
        }
        return desigLabel;
    }

    public void setDesigLabel(String desigLabel) {
        this.desigLabel = desigLabel;
    }

    public String getGidLabel() {
        if (gidLabel == null) {
            getValuesForGroupingLabels();
        }
        return gidLabel;
    }

    public void setGidLabel(String gidLabel) {
        this.gidLabel = gidLabel;
    }

    public String getRepLabel() {
        if (repLabel == null) {
            getValuesForGroupingLabels();
        }
        return repLabel;
    }

    public void setRepLabel(String repLabel) {
        this.repLabel = repLabel;
    }

    public String getBlockLabel() {
        if (blockLabel == null) {
            getValuesForGroupingLabels();
        }
        return blockLabel;
    }

    public void setBlockLabel(String blockLabel) {
        this.blockLabel = blockLabel;
    }

    public String getRowLabel() {
        if (rowLabel == null) {
            getValuesForGroupingLabels();
        }
        return rowLabel;
    }

    public void setRowLabel(String rowLabel) {
        this.rowLabel = rowLabel;
    }

    public String getColLabel() {
        if (colLabel == null) {
            getValuesForGroupingLabels();
        }
        return colLabel;
    }

    public void setColLabel(String colLabel) {
        this.colLabel = colLabel;
    }

    /**
     * Returns the Condition where propery =  TRIAL_INSTANCE + NUMBER
     */
    public Condition getTrialInstanceCondition() {
        Condition trialInstanceCondition = null;
        boolean trialLabelAssigned = false;
        for (Condition condition : conditions) {
            String text = getStringWithOutBlanks(condition.getProperty() + condition.getScale());
            if (TRIAL_INSTANCE_NUMBER.equals(text) && !trialLabelAssigned) {
                trialInstanceCondition = condition;

                trialLabelAssigned = true;
                break;
            }
        }
        return trialInstanceCondition;
    }

    public String getValidationMessage() {
        return validationMessage;
    }

    public void setValidationMessage(String validationMessage) {
        this.validationMessage = validationMessage;
    }

    /**
     * Return the check factor identify by PROPERTY + SCALE + METHOD acording to
     * <code>CHECK_CODE_ASSIGNED</code> constant
     * @return Check factor or <code>null</code> if not found
     */
    public Factor getCheckFactor() {
        Factor checkFactor = null;
         for (Factor factor : factors) {
            String text = getStringWithOutBlanks(factor.getProperty() + factor.getScale() + factor.getMethod());
            if (text.equals(CHECK_CODE_ASSIGNED)) {
                checkFactor = factor;
                break;
            }
        }
        return checkFactor;
    }
    
    /**
     * It checks if template has a factor with plot + nested + number
     * @return 
     */
    public boolean hasPlotNestedNumber() {
        boolean hasPlotNested = false;
        
       for (Factor factor : factors) {
            String text = getStringWithOutBlanks(factor.getProperty() + factor.getScale() );

            if (FIELD_PLOT_NESTEDNUMBER.equals(text)) {
               hasPlotNested  = true;
               break;
            }
        }
        
        
        return hasPlotNested;
    }
}
