package ibfb.studyeditor.wizard;

import java.awt.Component;
import javax.swing.event.ChangeListener;
import org.openide.WizardDescriptor;
import org.openide.util.HelpCtx;
import org.openide.util.NbPreferences;

public class TrialWizardWizardPanel5 implements WizardDescriptor.Panel {

    private TrialWizardVisualPanel5 component;

    @Override
    public Component getComponent() {
        if (component == null) {
            component = new TrialWizardVisualPanel5();
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
        
        int otherFactors=TrialWizardWizardIterator.myExcelReader.getMyWorkbook().getOtherFactors().size();
        ((WizardDescriptor) settings).putProperty("otherFactors", otherFactors);
        NbPreferences.forModule(TrialWizardVisualPanel5.class).put("otherFactors", String.valueOf(otherFactors));
        component.copyLevels();
    }
}
