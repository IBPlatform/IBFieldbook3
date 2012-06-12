package ibfb.studyeditor.wizard;

import ibfb.studyeditor.core.StudyEditorTopComponent;
import javax.swing.event.ChangeListener;
import org.openide.WizardDescriptor;
import org.openide.util.HelpCtx;
import org.openide.util.NbPreferences;

public class TrialWizardWizardPanel2 implements WizardDescriptor.Panel {

    private TrialWizardVisualPanel2 component;

    @Override
    public TrialWizardVisualPanel2 getComponent() {
        if (component == null) {
            component = new TrialWizardVisualPanel2();
        }
        return component;
    }

    @Override
    public HelpCtx getHelp() {
        // Show no Help button for this panel:
        return HelpCtx.DEFAULT_HELP;
        // If you have context help:
        // return new HelpCtx(SampleWizardPanel1.class);
    }

    @Override
    public boolean isValid() {

        return true;
    }

    @Override
    public final void addChangeListener(ChangeListener l) {
    }

    @Override
    public final void removeChangeListener(ChangeListener l) {
    }

    @Override
    public void readSettings(Object settings) {
        if (!TrialWizardUtil.studyConditionsAssigned()) {
            TrialWizardWizardIterator.myExcelReader.readExcelWorkbookTemplateStudyConditions();
            String label = TrialWizardWizardIterator.myExcelReader.getMyWorkbook().getInstanceLabel();
            component.setLabelInstances(label);
            component.fillData(TrialWizardWizardIterator.myExcelReader.getMyWorkbook());
            
            // trial conditions are not filled yet
            TrialWizardUtil.unAssignTrialConditions();
        }
    }

    @Override
    public void storeSettings(Object settings) {
        doActions();
        ((WizardDescriptor) settings).putProperty("instaces", component.getInstances());
        NbPreferences.forModule(TrialWizardVisualPanel2.class).put("instaces", String.valueOf(component.getInstances()));
        StudyEditorTopComponent studyWindow = TrialWizardWizardIterator.studyTopComponent;
        studyWindow.jLabelInstances.setText(String.valueOf(component.getInstances()));
        component.copyValues();

        // Study conditions are now assigned
        TrialWizardUtil.assignStudyConditions();

    }

    private void doActions() {
        TrialWizardWizardIterator.myExcelReader.setNumInstances((Integer) component.getjSpinnerInstances().getValue());
    }
}
