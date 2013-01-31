/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.cimmyt.cril.ibwb.provider.testsStatic;

import ibfb.domain.core.*;
import org.apache.log4j.Logger;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author gamaliel
 */
public class TestWorkbook extends TestService {

    private static Logger log = Logger.getLogger(TestWorkbook.class);

    public void testWorkbook() {
        Workbook workbook = servicios.getWorkbookFull(new Integer(1), false);
        printWorkbook(workbook);
        //Workbook workbook2 = servicios.getWorkbookFull(new Integer(-2), false);
        //printWorkbook(workbook2);
    }
    
    public void testWorkbookReadFactorsVeryFast(){
        Integer studyId = 1;
        Integer trialId = 0;
        String trialName = "OCC";
        List<String> factoresPrincipales = new ArrayList<String>();
        factoresPrincipales.add("study");
        factoresPrincipales.add(trialName);
        factoresPrincipales.add("PLOT");
        factoresPrincipales.add("ENTRY NUMBER");
        List<String> factoresSalida = new ArrayList<String>();
        
        factoresSalida.add("OCC");
        factoresSalida.add("PLOT");
        factoresSalida.add("Rep");
        factoresSalida.add("ENTRY NUMBER");
        factoresSalida.add("FTID");
        factoresSalida.add("FOCC");
        factoresSalida.add("FENT");
        factoresSalida.add("MTID");
        factoresSalida.add("MOCC");
        factoresSalida.add("MENT");
        factoresSalida.add("STID");
        factoresSalida.add("SOCC");
        factoresSalida.add("SENT");
        factoresSalida.add("NSEL");
        factoresSalida.add("TEMP_INTRID");
        factoresSalida.add("TYPE");
        factoresSalida.add("CID");
        factoresSalida.add("SID");
        factoresSalida.add("GID");
        factoresSalida.add("CROSS NAME");
        factoresSalida.add("SELECTION HISTORY");
        factoresSalida.add("INTRID");
        factoresSalida.add("SHUid");
        factoresSalida.add("IsCheck");

        
        List<Measurement> lista = servicios.getTrialRandomizationVeryFast(studyId, trialId, factoresPrincipales, factoresSalida, "");
        log.info("recuperacion terminada " + lista.size() + " registros recuperados.");
        printMeasurements(lista);
        log.info("Impresion terminada");
    }
    
    public void testWorkbookReadFactorsVeryFast2(){
        Integer studyId = 16447;
        Integer trialId = 0;
        String trialName = "OCC";
        List<String> factoresPrincipales = new ArrayList<String>();
        factoresPrincipales.add("study");
        factoresPrincipales.add(trialName);
        factoresPrincipales.add("PLOT");
        factoresPrincipales.add("ENTRY NUMBER");
        List<String> factoresSalida = new ArrayList<String>();
        
        factoresSalida.add("OCC");
        factoresSalida.add("PLOT");
        factoresSalida.add("Rep");
        factoresSalida.add("SUB BLOCK");
        factoresSalida.add("ENTRY NUMBER");
        factoresSalida.add("FTID");
        factoresSalida.add("FOCC");
        factoresSalida.add("FENT");
        factoresSalida.add("MTID");
        factoresSalida.add("MOCC");
        factoresSalida.add("MENT");
        factoresSalida.add("STID");
        factoresSalida.add("SOCC");
        factoresSalida.add("SENT");
        factoresSalida.add("NSEL");
        factoresSalida.add("TYPE");
        factoresSalida.add("CID");
        factoresSalida.add("SID");
        factoresSalida.add("GID");
        factoresSalida.add("CROSS NAME");
        factoresSalida.add("SELECTION HISTORY");
        factoresSalida.add("INTRID");
        factoresSalida.add("SHUid");
        factoresSalida.add("IsCheck");
        factoresSalida.add("SUMMARY");
        
        List<Measurement> lista = servicios.getTrialRandomizationVeryFast(studyId, trialId, factoresPrincipales, factoresSalida, trialName);
        log.info("recuperacion terminada " + lista.size() + " registros recuperados.");
        printMeasurements(lista);
        log.info("Impresion terminada");
    }

    public void testSaveWorkbook() {
        Workbook workbook = getWORKBOOK();
        servicios.saveWorkbookFull(workbook);
    }
    
    public static void printMeasurements(List<Measurement> lista){
        for(Measurement measurement : lista){
            System.out.println("");
            for(Object objeto : measurement.getFactorLabelData()){
                System.out.print(" - " + objeto);
            }
        }
        System.out.println("");
    }

    public static void printWorkbook(Workbook workbook) {

        salidaConsola("==================================================");
        salidaConsola("STUDY: ->" + workbook.getStudy().getStudy());
        salidaConsola("TITLE: ->" + workbook.getStudy().getTitle());
        salidaConsola("PMKEY: ->" + workbook.getStudy().getPmkey());
        salidaConsola("OBJECTIVE: ->" + workbook.getStudy().getObjective());
        salidaConsola("START DATE: ->" + workbook.getStudy().getStarDate());
        salidaConsola("END DATE: ->" + workbook.getStudy().getEndDate());
        salidaConsola("STUDY TYPE: ->" + workbook.getStudy().getStudyType());
        salidaConsola("==================================================");
        salidaConsola("--------------------------------------------------");
        salidaConsola("==================================================");
        salidaConsola(
                "|  STUDY CONDITION   "
                + "|      DESCRIPTION     "
                + "|      PROPERTY     "
                + "|      SCALE     "
                + "|      METHOD     "
                + "|      DATA TYPE     "
                + "|      VALUE      "
                + "|      LABEL     |");
        for (Condition condition : workbook.getStudyConditions()) {
            salidaConsola(
                    condition.getConditionName() + " -|- "
                    + condition.getDescription() + " -|- "
                    + condition.getProperty() + " -|- "
                    + condition.getScale() + " -|- "
                    + condition.getMethod() + " -|- "
                    + condition.getDataType() + " -|- "
                    + condition.getValue() + " -|- "
                    + condition.getLabel());
        }

        salidaConsola("=================================================");
        salidaConsola(
                "|      CONDITION     "
                + "|      DESCRIPTION     "
                + "|      PROPERTY     "
                + "|      SCALE     "
                + "|      METHOD     "
                + "|      DATA TYPE     "
                + "|      VALUE      "
                + "|      LABEL     |");
        for (Condition condition : workbook.getConditions()) {
            salidaConsola(
                    condition.getConditionName() + " -|- "
                    + condition.getDescription() + " -|- "
                    + condition.getProperty() + " -|- "
                    + condition.getScale() + " -|- "
                    + condition.getMethod() + " -|- "
                    + condition.getDataType() + " -|- "
                    + condition.getValue() + " -|- "
                    + condition.getLabel());
        }

        salidaConsola("=================================================");
        salidaConsola(
                "|       FACTOR       "
                + "|      DESCRIPTION     "
                + "|      PROPERTY     "
                + "|      SCALE     "
                + "|      METHOD     "
                + "|      DATA TYPE     "
                + "|      LABEL     |");
        for (Factor factor : workbook.getFactors()) {
            salidaConsola(
                    factor.getFactorName() + " -|- "
                    + factor.getDescription() + " -|- "
                    + factor.getProperty() + " -|- "
                    + factor.getScale() + " -|- "
                    + factor.getMethod() + " -|- "
                    + factor.getDataType() + " -|- "
                    + factor.getLabel());
        }

        salidaConsola("=================================================");
        salidaConsola(
                "|      VARIATE     "
                + "|      DESCRIPTION     "
                + "|     PROPERTY     "
                + "|     SCALE     "
                + "|     METHOD     "
                + "|     DATA TYPE   |");

        for (Variate variate : workbook.getVariates()) {
            salidaConsola(
                    variate.getVariateName() + "-|-"
                    + variate.getDescription() + "-|-"
                    + variate.getProperty() + "-|-"
                    + variate.getScale() + "-|-"
                    + variate.getMethod() + "-|-"
                    + variate.getDataType() + "-|-");
        }
    }

    private static void salidaConsola(String salida) {
        System.out.println(salida);
    }

    private static void salidaConsolaLog(String salida) {
        log.info(salida);
    }

    private static Workbook getWORKBOOK() {

        List<Condition> conditionsStudy = new ArrayList<Condition>();
        List<Condition> conditions = new ArrayList<Condition>();
        List<Factor> factors = new ArrayList<Factor>();
        List<Constant> constants = new ArrayList<Constant>();
        List<Variate> variates = new ArrayList<Variate>();

        Study study = new Study();

//        study.setPmkey(null);
        study.setStudy("IBFB5-MET Template with Labels and Factorial Treatments");
        study.setTitle("Template for a multi-environment variety trial with extra labels and factorial treatments");
        study.setPmkey("IBFB5");
        study.setObjective("Multi-environment variety trial");
        study.setStarDate(new Date(2010, 12, 12));
        study.setEndDate(new Date(2010, 12, 12));
        study.setStudyType("E");
        study.setShierarchy(-1);

        Condition studyCondition = new Condition();

        studyCondition.setConditionName("TID");
        studyCondition.setDescription("TRIAL SEQUENCE NUMBER");
        studyCondition.setProperty("TRIAL");
        studyCondition.setScale("NUMBER");
        studyCondition.setMethod("TRIAL METHOD");
        studyCondition.setDataType("N");
        studyCondition.setValue(null);
        studyCondition.setLabel("STUDY");

        conditionsStudy.add(studyCondition);
        studyCondition = new Condition();

        studyCondition.setConditionName("CYCLE");
        studyCondition.setDescription("CROP CYCLE CODE (Main/Off)");
        studyCondition.setProperty("SEASON");
        studyCondition.setScale("CODE");
        studyCondition.setMethod("TRIAL METHOD");
        studyCondition.setDataType("C");
        studyCondition.setValue(null);
        studyCondition.setLabel("STUDY");

        conditionsStudy.add(studyCondition);
        studyCondition = new Condition();

        studyCondition.setConditionName("PI ID");
        studyCondition.setDescription("PRINCIPAL INVESTIGATOR");
        studyCondition.setProperty("PERSON");
        studyCondition.setScale("DBID");
        studyCondition.setMethod("ASSIGNED");
        studyCondition.setDataType("N");
        studyCondition.setValue(null);
        studyCondition.setLabel("STUDY");

        conditionsStudy.add(studyCondition);



        Condition condition = new Condition();

        condition.setConditionName("OCCURANCE");
        condition.setDescription("TRIAL NUMBER");
        condition.setProperty("TRIAL INSTANCE");
        condition.setScale("NUMBER");
        condition.setMethod("ENUMERATED");
        condition.setDataType("N");
        condition.setValue(null);
        condition.setLabel("TRIAL");

        conditions.add(condition);
        condition = new Condition();

        condition.setConditionName("OCCURANCE");
        condition.setDescription("COCCURANCE NUMBER");
        condition.setProperty("TRIAL INSTANCE");
        condition.setScale("NUMBER");
        condition.setMethod("ASSIGNED");
        condition.setDataType("N");
        condition.setValue(null);
        condition.setLabel("TRIAL");

        conditions.add(condition);
        condition = new Condition();

        condition.setConditionName("COOPERATOR ID");
        condition.setDescription("COOPERATOR ID");
        condition.setProperty("PERSON");
        condition.setScale("DBID");
        condition.setMethod("ASSIGNED");
        condition.setDataType("N");
        condition.setValue(null);
        condition.setLabel("TRIAL");

        conditions.add(condition);



        Factor factor = new Factor();

        factor.setFactorName("FERTLE");
        factor.setDescription("FERTILIZER LEVEL");
        factor.setProperty("FERTILIZER");
        factor.setScale("NUMBER");
        factor.setMethod("APPLIED");
        factor.setDataType("N");
        factor.setLabel("FERTLE");

        factors.add(factor);
        factor = new Factor();

        factor.setFactorName("FERTAM");
        factor.setDescription("FERTILIZER AMOUNT");
        factor.setProperty("FERTILIZER");
        factor.setScale("KG/HA");
        factor.setMethod("APPLIED");
        factor.setDataType("N");
        factor.setLabel("FERTLE");

        factors.add(factor);
        factor = new Factor();

        factor.setFactorName("TIME");
        factor.setDescription("PLATING TIME");
        factor.setProperty("PLANTING DATE");
        factor.setScale("TEXT");
        factor.setMethod("APPLIED");
        factor.setDataType("C");
        factor.setLabel("PDATE");

        factors.add(factor);



        Constant constant = new Constant();

        constant.setConstantName("SDATE");
        constant.setDescription("SOWING DATE");
        constant.setProperty("SOWING_DATE");
        constant.setScale("DATE");
        constant.setMethod("FIELD APPLICATION");
        constant.setDataType("N");
        constant.setValue("TRIAL");

        constants.add(constant);
        constant = new Constant();

        constant.setConstantName("NOROWS");
        constant.setDescription("NO_OF_ROWS_SOWN");
        constant.setProperty("SOWING_DATE");
        constant.setScale("integer");
        constant.setMethod("FIELD APPLICATION");
        constant.setDataType("N");
        constant.setValue("TRIAL");

        constants.add(constant);
        constant = new Constant();

        constant.setConstantName("LEROWS");
        constant.setDescription("LENGTH_OF_ROWS_SOWN");
        constant.setProperty("LENGTH_OF_ROWS_SOWN");
        constant.setScale("m");
        constant.setMethod("FIELD APPLICATION");
        constant.setDataType("N");
        constant.setValue("TRIAL");

        constants.add(constant);


        Variate variate = new Variate();

        variate.setVariateName("HEADING");
        variate.setDescription("HEADING");
        variate.setProperty("DAYS_TO_HEADING");
        variate.setScale("DAYS");
        variate.setMethod("DAYSHD METHOD");
        variate.setDataType("N");

        variates.add(variate);
        variate = new Variate();

        variate.setVariateName("HEIGHT");
        variate.setDescription("HEIGHT");
        variate.setProperty("PLANT_HEIGHT");
        variate.setScale("CM");
        variate.setMethod("PLNTHT STANDARD VALUE");
        variate.setDataType("N");

        variates.add(variate);
        variate = new Variate();

        variate.setVariateName("GRAIN YIELD");
        variate.setDescription("GRAIN_YIELD");
        variate.setProperty("GRAIN_YIELD");
        variate.setScale("G/PLOT");
        variate.setMethod("GRNYLD METHOD");
        variate.setDataType("N");

        variates.add(variate);


        Workbook workbook = new Workbook();

        workbook.setStudy(study);
        workbook.setStudyConditions(conditionsStudy);
        workbook.setConditions(conditions);
        workbook.setFactors(factors);
        workbook.setConstants(constants);
        workbook.setVariates(variates);

        return workbook;
    }

    public static void main(String[] args) {
        TestWorkbook tt = new TestWorkbook();
//        tt.testWorkbook();
        //tt.testSaveWorkbook();
        tt.testWorkbookReadFactorsVeryFast();
    }
}