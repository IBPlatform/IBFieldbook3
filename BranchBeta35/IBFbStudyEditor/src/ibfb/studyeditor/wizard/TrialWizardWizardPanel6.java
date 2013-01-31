package ibfb.studyeditor.wizard;

import java.awt.Component;
import javax.swing.event.ChangeListener;
import org.openide.WizardDescriptor;
import org.openide.util.HelpCtx;
import org.openide.util.NbPreferences;

public class TrialWizardWizardPanel6 implements WizardDescriptor.Panel {

    private TrialWizardVisualPanel6 component;

    @Override
    public Component getComponent() {
        if (component == null) {
            component = new TrialWizardVisualPanel6();
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

        component.fillData(TrialWizardWizardIterator.myExcelReader.getMyWorkbook());
    }

    @Override
    public void storeSettings(Object settings) {
        component.StopEditor();
        component.copyValues(TrialWizardWizardIterator.myExcelReader.getMyWorkbook());
        //component.generateCombinations();

        //  component.copyValues(TrialWizardWizardIterator.myExcelReader.getMyWorkbook());
    }
}
