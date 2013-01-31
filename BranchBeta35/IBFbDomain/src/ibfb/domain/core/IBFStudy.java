/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ibfb.domain.core;

import java.util.List;

/**
 *
 * @author MasterGama
 */
public class IBFStudy {
    
    private Study study;
    private List<Condition> studyConditions;
    private List<Condition> conditions;
    private List<Factor> factors;
    private List<Constant> constants;
    private List<Variate> variates;

    public IBFStudy() {
    }

    public IBFStudy(Study study, List<Condition> conditions, List<Factor> factors, List<Constant> constants, List<Variate> variates) {
        this.study = study;
        this.conditions = conditions;
        this.factors = factors;
        this.constants = constants;
        this.variates = variates;
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
    }

     public List<Condition> getStudyConditions() {
        return studyConditions;
    }

    public void setStudyConditions(List<Condition> studyConditions) {
        this.studyConditions = studyConditions;
    }
    
}
