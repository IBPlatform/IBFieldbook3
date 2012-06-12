/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.cimmyt.cril.ibwb.provider.testsStatic;

import org.apache.log4j.Logger;
import ibfb.domain.core.Workbook;
import ibfb.domain.core.Condition;
import ibfb.domain.core.Constant;
import ibfb.domain.core.Factor;
import ibfb.domain.core.Measurement;
import ibfb.domain.core.MeasurementData;
import ibfb.domain.core.Study;
import ibfb.domain.core.Variate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

/**
 *
 * @author TMSANCHEZ
 */
public class TestWorkbookSaving extends TestService {

    private static Logger log = Logger.getLogger(TestWorkbook.class);

    public void testSaveWorkbook() {
        Workbook workbook = getWORKBOOK();
        servicios.saveWorkbookFull(workbook);
    }

    private static Workbook getWORKBOOK() {

        List<Condition> studyConditions = new ArrayList<Condition>();
        List<Condition> conditions = new ArrayList<Condition>();
        List<Factor> factors = new ArrayList<Factor>();
        List<Factor> otherFactors = new ArrayList<Factor>();
        List<Constant> constants = new ArrayList<Constant>();
        List<Variate> variates = new ArrayList<Variate>();

        List<Condition> conditionsData = new ArrayList<Condition>();
        List<Factor> factorsData = new ArrayList<Factor>();
        List<Constant> constantsData = new ArrayList<Constant>();
        List<Variate> variatesData = new ArrayList<Variate>();

        Study study = new Study();

//        study.setPmkey(null);
        //--------------------------------
        // STUDY
        //--------------------------------
        study.setStudy("IBFB5-MET Template with Labels and Factorial Treatments");
        study.setTitle("Template for a multi-environment variety trial with extra labels and factorial treatments");
        study.setPmkey("IBFB5");
        study.setObjective("Multi-environment variety trial");
        study.setStarDate(new Date(2010, 12, 12));
        study.setEndDate(new Date(2010, 12, 12));
        study.setStudyType("E");
        study.setShierarchy(-1);

        //--------------------------------
        // STUDY CONDITION
        //--------------------------------
        Condition studyCondition = new Condition();

        studyCondition.setConditionName("TID");
        studyCondition.setDescription("TRIAL SEQUENCE NUMBER");
        studyCondition.setProperty("TRIAL");
        studyCondition.setScale("NUMBER");
        studyCondition.setMethod("TRIAL METHOD");
        studyCondition.setDataType("N");
        studyCondition.setValue(null);
        studyCondition.setLabel("STUDY");

        studyConditions.add(studyCondition);
        studyCondition = new Condition();

        studyCondition.setConditionName("CYCLE");
        studyCondition.setDescription("CROP CYCLE CODE (Main/Off)");
        studyCondition.setProperty("SEASON");
        studyCondition.setScale("CODE");
        studyCondition.setMethod("TRIAL METHOD");
        studyCondition.setDataType("C");
        studyCondition.setValue(null);
        studyCondition.setLabel("STUDY");

        studyConditions.add(studyCondition);
        studyCondition = new Condition();

        studyCondition.setConditionName("PI ID");
        studyCondition.setDescription("PRINCIPAL INVESTIGATOR");
        studyCondition.setProperty("PERSON");
        studyCondition.setScale("DBID");
        studyCondition.setMethod("ASSIGNED");
        studyCondition.setDataType("N");
        studyCondition.setValue(null);
        studyCondition.setLabel("STUDY");

        studyConditions.add(studyCondition);
        studyCondition = new Condition();

        studyCondition.setConditionName("PI NAME");
        studyCondition.setDescription("PRINCIPAL INVESTIGATOR");
        studyCondition.setProperty("PERSON");
        studyCondition.setScale("DBCV");
        studyCondition.setMethod("ASSIGNED");
        studyCondition.setDataType("C");
        studyCondition.setValue(null);
        studyCondition.setLabel("STUDY");

        studyConditions.add(studyCondition);

        //--------------------------------
        // TRIAL CONDITION
        //--------------------------------

        Condition conditionTrial = new Condition();

        conditionTrial.setConditionName("TRIAL");
        conditionTrial.setDescription("TRIAL NUMBER");
        conditionTrial.setProperty("TRIAL INSTANCE");
        conditionTrial.setScale("NUMBER");
        conditionTrial.setMethod("ENUMERATED");
        conditionTrial.setDataType("N");
        conditionTrial.setValue(null);
        conditionTrial.setLabel("TRIAL");

        conditions.add(conditionTrial);
        conditionTrial = new Condition();

        conditionTrial.setConditionName("OCCURANCE");
        conditionTrial.setDescription("COCCURANCE NUMBER");
        conditionTrial.setProperty("TRIAL INSTANCE");
        conditionTrial.setScale("NUMBER");
        conditionTrial.setMethod("ASSIGNED");
        conditionTrial.setDataType("N");
        conditionTrial.setValue(null);
        conditionTrial.setLabel("TRIAL");

        conditions.add(conditionTrial);
        conditionTrial = new Condition();

        conditionTrial.setConditionName("COOPERATOR ID");
        conditionTrial.setDescription("COOPERATOR ID");
        conditionTrial.setProperty("PERSON");
        conditionTrial.setScale("DBID");
        conditionTrial.setMethod("ASSIGNED");
        conditionTrial.setDataType("N");
        conditionTrial.setValue(null);
        conditionTrial.setLabel("TRIAL");

        conditions.add(conditionTrial);
        conditionTrial = new Condition();

        conditionTrial.setConditionName("COOPERATOR");
        conditionTrial.setDescription("COOPERATOR NAME");
        conditionTrial.setProperty("PERSON");
        conditionTrial.setScale("DBCV");
        conditionTrial.setMethod("ASSIGNED");
        conditionTrial.setDataType("C");
        conditionTrial.setValue(null);
        conditionTrial.setLabel("TRIAL");

        conditions.add(conditionTrial);




        //--------------------------------
        // FACTORS
        //--------------------------------

        Factor factor = new Factor();

        //Other factors
        factor.setFactorName("FERTLE");
        factor.setDescription("FERTILIZER LEVEL");
        factor.setProperty("FERTILIZER");
        factor.setScale("NUMBER");
        factor.setMethod("APPLIED");
        factor.setDataType("N");
        factor.setLabel("FERTLE");

        factors.add(factor);
        otherFactors.add(factor);
        factor = new Factor();

        factor.setFactorName("FERTAM");
        factor.setDescription("FERTILIZER AMOUNT");
        factor.setProperty("FERTILIZER");
        factor.setScale("KG/HA");
        factor.setMethod("APPLIED");
        factor.setDataType("N");
        factor.setLabel("FERTLE");

        factors.add(factor);
        otherFactors.add(factor);
        factor = new Factor();

        factor.setFactorName("PDATE");
        factor.setDescription("PLATING DATE");
        factor.setProperty("PLANTING DATE");
        factor.setScale("NUMBER");
        factor.setMethod("APPLIED");
        factor.setDataType("N");
        factor.setLabel("PDATE");

        factors.add(factor);
        otherFactors.add(factor);
        factor = new Factor();

        factor.setFactorName("TIME");
        factor.setDescription("PLATING TIME");
        factor.setProperty("PLANTING DATE");
        factor.setScale("TEXT");
        factor.setMethod("APPLIED");
        factor.setDataType("C");
        factor.setLabel("PDATE");

        factors.add(factor);
        otherFactors.add(factor);

        //ENTRY FACTORS
        factor = new Factor();

        factor.setFactorName("ENTRY");
        factor.setDescription("ENTRY NUMBER");
        factor.setProperty("GERMPLASM ENTRY");
        factor.setScale("NUMBER");
        factor.setMethod("ENUMERATED");
        factor.setDataType("N");
        factor.setLabel("ENTRY");

        factors.add(factor);
        factor = new Factor();

        factor.setFactorName("ENTRY CD");
        factor.setDescription("ENTRY CODE");
        factor.setProperty("GERMPLASM ENTRY");
        factor.setScale("CODE");
        factor.setMethod("ASSIGNED");
        factor.setDataType("C");
        factor.setLabel("ENTRY");

        factors.add(factor);
        factor = new Factor();

        factor.setFactorName("DESIG");
        factor.setDescription("ENTRY DESIGNATION");
        factor.setProperty("GERMPLASM ID");
        factor.setScale("DBCV");
        factor.setMethod("ASSIGNED");
        factor.setDataType("C");
        factor.setLabel("ENTRY");

        factors.add(factor);
        factor = new Factor();

        factor.setFactorName("GID");
        factor.setDescription("GERMPLASM ID");
        factor.setProperty("GERMPLASM ID");
        factor.setScale("DBID");
        factor.setMethod("ASSIGNED");
        factor.setDataType("N");
        factor.setLabel("ENTRY");

        factors.add(factor);

        //PLOT Factors
        factor = new Factor();

        factor.setFactorName("PLOT");
        factor.setDescription("PLOT NUMBER");
        factor.setProperty("FIELD PLOT");
        factor.setScale("NESTED NUMBER");
        factor.setMethod("ENUMERATED");
        factor.setDataType("N");
        factor.setLabel("PLOT");

        factors.add(factor);
        factor = new Factor();

        factor.setFactorName("REP");
        factor.setDescription("REPLICATION");
        factor.setProperty("REPLICATION");
        factor.setScale("NUMBER");
        factor.setMethod("ENUMERATED");
        factor.setDataType("N");
        factor.setLabel("PLOT");

        factors.add(factor);
        factor = new Factor();

        factor.setFactorName("BLOCK");
        factor.setDescription("INCOMPLETE BLOCK");
        factor.setProperty("BLOCK");
        factor.setScale("NUMBER");
        factor.setMethod("ENUMERATED");
        factor.setDataType("N");
        factor.setLabel("PLOT");

        factors.add(factor);
        factor = new Factor();

        factor.setFactorName("ROW");
        factor.setDescription("ROW NUMBER");
        factor.setProperty("ROW IN LAYOUT");
        factor.setScale("NUMBER");
        factor.setMethod("ENUMERATED");
        factor.setDataType("N");
        factor.setLabel("PLOT");

        factors.add(factor);
        factor = new Factor();

        factor.setFactorName("COL");
        factor.setDescription("COLUMN NUMBER");
        factor.setProperty("COLUMN IN LAYOUT");
        factor.setScale("NUMBER");
        factor.setMethod("ENUMERATED");
        factor.setDataType("N");
        factor.setLabel("PLOT");

        factors.add(factor);



        //--------------------------------
        // CONSTANTS
        //--------------------------------

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

        //--------------------------------
        // VARIATES
        //--------------------------------

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
        workbook.setStudyConditions(studyConditions);
        workbook.setConditions(conditions);
        workbook.setFactors(factors);
        workbook.setConstants(constants);
        workbook.setVariates(variates);

        workbook.setInstanceNumber(2);

        //Add datos

        //tipo de dato numerico
        Double numero = 555.0;
        //tipo de dato texto
        String texto = "asd";

        for (int i = 0; i < workbook.getInstanceNumber(); i++) {
            for (Condition conditionIterado : workbook.getConditions()) {
                Condition conditionTemp = new Condition();
                conditionTemp.setConditionName(conditionIterado.getConditionName());
                conditionTemp.setDescription(conditionIterado.getDescription());
                conditionTemp.setProperty(conditionIterado.getProperty());
                conditionTemp.setScale(conditionIterado.getScale());
                conditionTemp.setMethod(conditionIterado.getMethod());
                conditionTemp.setDataType(conditionIterado.getDataType());
                if (conditionIterado.getDataType().equals("N")) {
                    conditionTemp.setValue(numero);
                } else {
                    conditionTemp.setValue(texto);
                }
                conditionTemp.setLabel(conditionIterado.getLabel());
                conditionTemp.setInstance(i + 1);
                conditionsData.add(conditionTemp);
            }

            for (Factor factorIterado : otherFactors) {
                Factor factorTemp = new Factor();

                factorTemp.setFactorName(factorIterado.getFactorName());
                factorTemp.setDescription(factorIterado.getDescription());
                factorTemp.setProperty(factorIterado.getProperty());
                factorTemp.setScale(factorIterado.getScale());
                factorTemp.setMethod(factorIterado.getMethod());
                factorTemp.setDataType(factorIterado.getDataType());
                factorTemp.setLabel(factorIterado.getLabel());
                factorTemp.setInstance(i+1);
                if (factorTemp.getDataType().equals("N")) {
                    factorTemp.setValue(numero);
                } else {
                    factorTemp.setValue(texto);
                }
                factorsData.add(factorTemp);
            }

            for (Constant constantIterado : workbook.getConstants()) {
                Constant constantTemp = new Constant();

                constantTemp.setConstantName(constantIterado.getConstantName());
                constantTemp.setDescription(constantIterado.getDescription());
                constantTemp.setProperty(constantIterado.getProperty());
                constantTemp.setScale(constantIterado.getScale());
                constantTemp.setMethod(constantIterado.getMethod());
                constantTemp.setDataType(constantIterado.getDataType());
                if (constantTemp.getDataType().equals("N")) {
                    constantTemp.setValue(numero);
                } else {
                    constantTemp.setValue(texto);
                }
                constantsData.add(constantTemp);
            }

            numero++;

        }

        workbook.setConditionsData(conditionsData);
        workbook.setFactorsData(factorsData);
        workbook.setConstantsData(constantsData);

        List<Measurement> listMeasurements = new ArrayList<Measurement>();

        int trial = 1;
        int rep = 1;
        int block = 3;
        int plot = 1;
        int entry = 1;
        int gid = 1;
        String desig = "CM9204-";
        int row = 1;
        int column = 1;

        //---------------- Generate measurement
        for (int i = 0; i < 20; i++) {
            Measurement m = new Measurement();
            m.setTrial(trial++);
            m.setReplication(rep++);
            m.setBlock(block++);
            m.setPlot(plot++);
            m.setEntry(entry++);
            m.setGid(gid++);
            m.setDesignation(desig + i);
            m.setRow(row++);
            m.setColumn(column++);

            List<MeasurementData> mdList = new ArrayList<MeasurementData>();

            Random rnd = new Random(1000L);
            for (int col = 0; col < 6; col++) {
                MeasurementData md = new MeasurementData();
                md.setData("N", rnd.nextInt());
                mdList.add(md);
            }
            m.setMeasurementsData(mdList);

            listMeasurements.add(m);
        }

        workbook.setMeasurements(listMeasurements);

        fillGermplasmList(workbook);

        return workbook;
    }

    private static void fillGermplasmList(Workbook workbook) {
        List<List<Object>> lstGermplasm = null;
        lstGermplasm = new ArrayList<List<Object>>();

        List<Object> lstCols1 = new ArrayList<Object>();
        lstCols1.add(new Double(1));
        lstCols1.add("21 IBYT:2");
        lstCols1.add("CMB91A.1142-J-5B-1Y-0B");
        lstCols1.add(new Double(277027.0));
        lstGermplasm.add(lstCols1);
        List<Object> lstCols2 = new ArrayList<Object>();
        lstCols2.add(new Double(2));
        lstCols2.add("21 IBYT:1");
        lstCols2.add("LOCAL CHECK");
        lstCols2.add(new Double(304660));
        lstGermplasm.add(lstCols2);
        List<Object> lstCols3 = new ArrayList<Object>();
        lstCols3.add(new Double(3));
        lstCols3.add("21 IBYT:9");
        lstCols3.add("CMB92A.83-1M-1Y-1B-0Y");
        lstCols3.add(new Double(305488));
        lstGermplasm.add(lstCols3);
        List<Object> lstCols4 = new ArrayList<Object>();
        lstCols4.add(new Double(4));
        lstCols4.add("21 IBYT:19");
        lstCols4.add("CMB92A.1358-J-2M-1Y-2B-0Y");
        lstCols4.add(new Double(305599));
        lstGermplasm.add(lstCols4);
        List<Object> lstCols5 = new ArrayList<Object>();
        lstCols5.add(new Double(5));
        lstCols5.add("21 IBYT:5");
        lstCols5.add("CMB93.747-B-7Y-3M-0Y");
        lstCols5.add(new Double(305655));
        lstGermplasm.add(lstCols5);
        List<Object> lstCols6 = new ArrayList<Object>();
        lstCols6.add(new Double(6));
        lstCols6.add("21 IBYT:6");
        lstCols6.add("CMB93.748-E-4Y-1M-0Y");
        lstCols6.add(new Double(305669));
        lstGermplasm.add(lstCols6);
        List<Object> lstCols7 = new ArrayList<Object>();
        lstCols7.add(new Double(7));
        lstCols7.add("21 IBYT:4");
        lstCols7.add("CMB93.747-E-3Y-7M-0Y");
        lstCols7.add(new Double(305810));
        lstGermplasm.add(lstCols7);
        List<Object> lstCols8 = new ArrayList<Object>();
        lstCols8.add(new Double(8));
        lstCols8.add("21 IBYT:10");
        lstCols8.add("CMB92A.1358-J-1M-1Y-1B-0Y");
        lstCols8.add(new Double(305870));
        lstGermplasm.add(lstCols8);
        List<Object> lstCols9 = new ArrayList<Object>();
        lstCols9.add(new Double(9));
        lstCols9.add("21 IBYT:11");
        lstCols9.add("CMB92A.1325-D-6M-1Y-2B-0Y");
        lstCols9.add(new Double(315888));
        lstGermplasm.add(lstCols9);
        List<Object> lstCols10 = new ArrayList<Object>();
        lstCols10.add(new Double(10));
        lstCols10.add("21 IBYT:7");
        lstCols10.add("CMB93.747-B-2Y-10M-0Y");
        lstCols10.add(new Double(315904));
        lstGermplasm.add(lstCols10);
        List<Object> lstCols11 = new ArrayList<Object>();
        lstCols11.add(new Double(11));
        lstCols11.add("21 IBYT:3");
        lstCols11.add("CMB93.747-C-5Y-1M-0Y");
        lstCols11.add(new Double(315916));
        lstGermplasm.add(lstCols11);
        List<Object> lstCols12 = new ArrayList<Object>();
        lstCols12.add(new Double(12));
        lstCols12.add("21 IBYT:15");
        lstCols12.add("CMB92A.1233-A-4M-1Y-1B-0Y");
        lstCols12.add(new Double(315933));
        lstGermplasm.add(lstCols12);
        List<Object> lstCols13 = new ArrayList<Object>();
        lstCols13.add(new Double(13));
        lstCols13.add("21 IBYT:12");
        lstCols13.add("CMB92A.892-C-2M-2Y-1B-0Y");
        lstCols13.add(new Double(315968));
        lstGermplasm.add(lstCols13);
        List<Object> lstCols14 = new ArrayList<Object>();
        lstCols14.add(new Double(14));
        lstCols14.add("21 IBYT:13");
        lstCols14.add("CMB92A.1219-B-7M-1Y-1B-0Y");
        lstCols14.add(new Double(315974));
        lstGermplasm.add(lstCols14);
        List<Object> lstCols15 = new ArrayList<Object>();
        lstCols15.add(new Double(15));
        lstCols15.add("21 IBYT:16");
        lstCols15.add("CMB92A.1219-B-7M-1Y-1B-0Y");
        lstCols15.add(new Double(315974));
        lstGermplasm.add(lstCols15);
        List<Object> lstCols16 = new ArrayList<Object>();
        lstCols16.add(new Double(16));
        lstCols16.add("21 IBYT:14");
        lstCols16.add("CMB92A.920-D-3M-1Y-2B-0Y");
        lstCols16.add(new Double(316011));
        lstGermplasm.add(lstCols16);
        List<Object> lstCols17 = new ArrayList<Object>();
        lstCols17.add(new Double(17));
        lstCols17.add("21 IBYT:21");
        lstCols17.add("CMB92A.1358-K-8M-1Y-1B-0Y");
        lstCols17.add(new Double(316138));
        lstGermplasm.add(lstCols17);
        List<Object> lstCols18 = new ArrayList<Object>();
        lstCols18.add(new Double(18));
        lstCols18.add("21 IBYT:8");
        lstCols18.add("CMB93.870-A-7Y-1M-0Y");
        lstCols18.add(new Double(316599));
        lstGermplasm.add(lstCols18);
        List<Object> lstCols19 = new ArrayList<Object>();
        lstCols19.add(new Double(19));
        lstCols19.add("21 IBYT:24");
        lstCols19.add("CMB92A.1426-B-3M-2Y-1B-0Y");
        lstCols19.add(new Double(317332));
        lstGermplasm.add(lstCols19);
        List<Object> lstCols20 = new ArrayList<Object>();
        lstCols20.add(new Double(20));
        lstCols20.add("21 IBYT:17");
        lstCols20.add("CMB92A.1358-T-5M-1Y-2B-0Y");
        lstCols20.add(new Double(359623));
        lstGermplasm.add(lstCols20);
        List<Object> lstCols21 = new ArrayList<Object>();
        lstCols21.add(new Double(21));
        lstCols21.add("21 IBYT:18");
        lstCols21.add("CMB92A.1358-F-2M-1Y-2B-0Y");
        lstCols21.add(new Double(359643));
        lstGermplasm.add(lstCols21);
        List<Object> lstCols22 = new ArrayList<Object>();
        lstCols22.add(new Double(22));
        lstCols22.add("21 IBYT:23");
        lstCols22.add("CMB91A.167-2M-1Y-1M-1Y-2B-0Y");
        lstCols22.add(new Double(380489));
        lstGermplasm.add(lstCols22);
        List<Object> lstCols23 = new ArrayList<Object>();
        lstCols23.add(new Double(23));
        lstCols23.add("21 IBYT:20");
        lstCols23.add("CMB91A.262-7M-1Y-1M-0Y");
        lstCols23.add(new Double(1990459));
        lstGermplasm.add(lstCols23);
        List<Object> lstCols24 = new ArrayList<Object>();
        lstCols24.add(new Double(24));
        lstCols24.add("21 IBYT:25");
        lstCols24.add("CMB92A.1118-D-5M-1Y-2B-0Y");
        lstCols24.add(new Double(2031190));
        lstGermplasm.add(lstCols24);
        List<Object> lstCols25 = new ArrayList<Object>();
        lstCols25.add(new Double(25));
        lstCols25.add("21 IBYT:22");
        lstCols25.add("CMB92A.1216-A-2M-1Y-2B-0Y");
        lstCols25.add(new Double(2031495));
        lstGermplasm.add(lstCols25);


        workbook.setGermplasmData(lstGermplasm);
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
                "|       CONSTANT       "
                + "|      DESCRIPTION     "
                + "|      PROPERTY     "
                + "|      SCALE     "
                + "|      METHOD     "
                + "|      DATA TYPE     "
                + "|      VAUE     |");
        for (Constant constant : workbook.getConstants()) {
            salidaConsola(
                    constant.getConstantName() + " -|- "
                    + constant.getDescription() + " -|- "
                    + constant.getProperty() + " -|- "
                    + constant.getScale() + " -|- "
                    + constant.getMethod() + " -|- "
                    + constant.getDataType() + " -|- "
                    + constant.getValue());
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

    public static void main(String[] args) {
        TestWorkbookSaving tt = new TestWorkbookSaving();
        //tt.testWorkbook();
        tt.testSaveWorkbook();
//        tt.printWorkbook(getWORKBOOK());
    }
}
