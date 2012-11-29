package ibfb.nursery.maize;

import javax.swing.event.ChangeListener;
import org.openide.WizardDescriptor;
import org.openide.util.HelpCtx;
import org.openide.util.NbPreferences;

public class PolinizationWizardPanel1 implements WizardDescriptor.Panel<WizardDescriptor> {

    private PolinizationVisualPanel1 component;

    @Override
    public PolinizationVisualPanel1 getComponent() {
        if (component == null) {
            component = new PolinizationVisualPanel1();
        }
        return component;
    }

    @Override
    public HelpCtx getHelp() {

        return null;

    }

    @Override
    public boolean isValid() {

        return true;

    }

    @Override
    public void addChangeListener(ChangeListener l) {
    }

    @Override
    public void removeChangeListener(ChangeListener l) {
    }

    @Override
    public void readSettings(WizardDescriptor wiz) {

wiz.putProperty("text1", "HOLA");
        
    }

    @Override
    public void storeSettings(WizardDescriptor wiz) {
        NbPreferences.forModule(PolinizationWizardPanel1.class).putInt("maizeMethod", component.getMethodIndex());
        NbPreferences.forModule(PolinizationWizardPanel1.class).putInt("whitParentheses", component.getParentheses());
        NbPreferences.forModule(PolinizationWizardPanel1.class).put("maizeMethodNameLabel", component.getMethodName());
        NbPreferences.forModule(PolinizationWizardPanel1.class).put("maizeTooltip", component.getMaizeTooltip());
        NbPreferences.forModule(PolinizationWizardPanel1.class).put("maizeMethodName", component.getMethodNameForMaize());
        NbPreferences.forModule(PolinizationWizardPanel1.class).put("maizeMethodID", String.valueOf(component.getMethodID()));


    }
}
