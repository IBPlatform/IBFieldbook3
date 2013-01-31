package ibfb.studyeditor.wizard;

import java.awt.Component;
import javax.swing.SpinnerNumberModel;
import javax.swing.event.ChangeListener;
import org.openide.WizardDescriptor;
import org.openide.util.HelpCtx;

public class TrialWizardWizardPanel3 implements WizardDescriptor.Panel {

    private TrialWizardVisualPanel3 component;

    @Override
    public Component getComponent() {
        if (component == null) {
            component = new TrialWizardVisualPanel3();
        }
        return component;
    }

    @Override
    public HelpCtx getHelp() {

        return HelpCtx.DEFAULT_HELP;

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
        if (! TrialWizardUtil.trialConditionsAssigned()) {
            TrialWizardWizardIterator.myExcelReader.readExcelWorkbookTemplate();
            component.fillData(TrialWizardWizardIterator.myExcelReader.getMyWorkbook(), TrialWizardWizardIterator.myExcelReader.getNumInstances());
            SpinnerNumberModel modelo = new SpinnerNumberModel(1, 1, TrialWizardWizardIterator.myExcelReader.getNumInstances().intValue(), 1);
            component.getjSpinnerTrial().setModel(modelo);
        }

    }

    @Override
    public void storeSettings(Object settings) {
        // now TRIAL conditions are assigned
        TrialWizardUtil.assignTrialConditions();
        component.copyValues();
    }
}
