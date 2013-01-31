package ibfb.nursery.mainwizard;

import java.awt.Component;
import javax.swing.event.ChangeListener;
import org.openide.WizardDescriptor;
import org.openide.util.HelpCtx;
import org.openide.util.NbPreferences;

public class NurseryWizardPanel4 implements WizardDescriptor.Panel {

    private NurseryVisualPanel4 component;

    @Override
    public Component getComponent() {
        if (component == null) {
            component = new NurseryVisualPanel4();
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
    }

    @Override
    public void storeSettings(Object settings) {

        if (component.quiereNuevo()) {
            NbPreferences.forModule(NurseryVisualPanel3.class).put("opcion", "new");
        } else {
            NbPreferences.forModule(NurseryVisualPanel3.class).put("opcion", "lista");

        }
        
        
       
    }
}
