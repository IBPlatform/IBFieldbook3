
package ibfb.germplasmlist.selectionWizard;

import javax.swing.event.ChangeListener;
import org.openide.WizardDescriptor;
import org.openide.util.HelpCtx;
import org.openide.util.NbPreferences;

public class SelectionWizardWizardPanel2 implements WizardDescriptor.Panel<WizardDescriptor> {

  
    private SelectionWizardVisualPanel2 component;

    @Override
    public SelectionWizardVisualPanel2 getComponent() {
        if (component == null) {
            component = new SelectionWizardVisualPanel2();
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
    public void addChangeListener(ChangeListener l) {
    }

    @Override
    public void removeChangeListener(ChangeListener l) {
    }

    @Override
    public void readSettings(WizardDescriptor wiz) {
        // use wiz.getProperty to retrieve previous panel state
    }

    @Override
    public void storeSettings(WizardDescriptor wiz) {
          NbPreferences.forModule(SelectionWizardWizardPanel2.class).put("methodIndex", String.valueOf(component.getMethodIndex()));
          NbPreferences.forModule(SelectionWizardWizardPanel2.class).put("convection", String.valueOf(component.getConvection()));
         
    }
}
