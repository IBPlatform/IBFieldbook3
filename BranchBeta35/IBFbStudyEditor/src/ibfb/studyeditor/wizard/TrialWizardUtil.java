/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ibfb.studyeditor.wizard;

import java.util.prefs.Preferences;
import org.openide.util.NbPreferences;

/**
 *
 * @author TMSANCHEZ
 */
public class TrialWizardUtil {

    private static Preferences trialWizardPreferences = NbPreferences.forModule(TrialWizardUtil.class);
    public static final String TRUE = "TRUE";
    public static final String FALSE = "FALSE";
    public static final String CURRENT_TEMPLATE = "CURRENT_TEMPLATE";
    public static final String STUDY_CONDITIONS_LOADED = "STUDY_CONDITIONS_LOADED";
    public static final String TRIAL_CONDITIONS_LOADED = "TRIAL_CONDITIONS_LOADED";
    public static final String CONSTANTS_LOADED = "CONSTANTS_LOADED";
    public static final String TRAITS_LOADED = "TRAITS_LOADED";
    public static final String DESIGN_LOADED = "DESIGN_LOADED";    

    public static void resetSettings() {
        trialWizardPreferences.put(CURRENT_TEMPLATE, "");
        unAssignStudyConditions();
        unAssignTrialConditions();
        unAssignConstants();
        unAssignTraits();
        unAssignDesign();
    }

    /**
     * Assign current template name used by wizard
     */
    public static void assignTemplateName(String templateName) {
        trialWizardPreferences.put(CURRENT_TEMPLATE, templateName);
    }

    /**
     * Check is template name is same previous used
     * @param templateName
     * @return <code>true</code> if is same template, <code>false</code> if not is same template
     */
    public static boolean isSameTemplate(String templateName) {
        return trialWizardPreferences.get(CURRENT_TEMPLATE, "__").equals(templateName);
    }

    public static void unAssignStudyConditions() {
        trialWizardPreferences.put(STUDY_CONDITIONS_LOADED, FALSE);
    }

    public static void assignStudyConditions() {
        trialWizardPreferences.put(STUDY_CONDITIONS_LOADED, TRUE);
    }

    public static boolean studyConditionsAssigned() {
        return trialWizardPreferences.get(STUDY_CONDITIONS_LOADED, FALSE).equals(TRUE);
    }

    public static void unAssignTrialConditions() {
        trialWizardPreferences.put(TRIAL_CONDITIONS_LOADED, FALSE);
    }

    public static void assignTrialConditions() {
        trialWizardPreferences.put(TRIAL_CONDITIONS_LOADED, TRUE);
    }

    public static boolean trialConditionsAssigned() {
        return trialWizardPreferences.get(TRIAL_CONDITIONS_LOADED, FALSE).equals(TRUE);
    }

    public static void unAssignConstants() {
        trialWizardPreferences.put(CONSTANTS_LOADED, FALSE);
    }

    public static void assignConstants() {
        trialWizardPreferences.put(CONSTANTS_LOADED, TRUE);
    }

    public static boolean constantsAssigned() {
        return trialWizardPreferences.get(CONSTANTS_LOADED, FALSE).equals(TRUE);
    }
    
    
    public static void unAssignTraits() {
        trialWizardPreferences.put(TRAITS_LOADED, FALSE);
    }

    public static void assignTraits() {
        trialWizardPreferences.put(TRAITS_LOADED, TRUE);
    }

    public static boolean traitsAssigned() {
        return trialWizardPreferences.get(TRAITS_LOADED, FALSE).equals(TRUE);
    }
    
    public static void unAssignDesign() {
        trialWizardPreferences.put(DESIGN_LOADED, FALSE);
    }

    public static void assignDesign() {
        trialWizardPreferences.put(DESIGN_LOADED, TRUE);
    }

    public static boolean designAssigned() {
        return trialWizardPreferences.get(DESIGN_LOADED, FALSE).equals(TRUE);
    }    
}
