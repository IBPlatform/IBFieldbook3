package ibfb.studyeditor.wizard;

import java.awt.Component;
import javax.swing.event.ChangeListener;
import org.openide.WizardDescriptor;
import org.openide.util.HelpCtx;

public class TrialWizardWizardPanel8 implements WizardDescriptor.Panel {

    private TrialWizardVisualPanel8 component;

    @Override
    public Component getComponent() {
        if (component == null) {

            component = new TrialWizardVisualPanel8();

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
        if (!TrialWizardUtil.traitsAssigned()) {
            component.fillData(TrialWizardWizardIterator.myExcelReader.getMyWorkbook());
        }
    }

    @Override
    public void storeSettings(Object settings) {
        component.copyValues(TrialWizardWizardIterator.myExcelReader.getMyWorkbook());
        TrialWizardUtil.assignTraits();
    
    }
}
