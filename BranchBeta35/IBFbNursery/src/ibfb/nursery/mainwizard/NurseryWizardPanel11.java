
package ibfb.nursery.mainwizard;

import ibfb.nursery.core.NurseryEditorTopComponent;
import java.awt.Component;
import java.util.HashSet;
import java.util.Set;
import javax.swing.event.ChangeListener;
import org.openide.WizardDescriptor;
import org.openide.util.HelpCtx;
import org.openide.util.NbPreferences;

public class NurseryWizardPanel11 implements WizardDescriptor.Panel {

 
    private NurseryVisualPanel11 component;
    private boolean isValid = false;
    private Set<ChangeListener> listeners = new HashSet<ChangeListener>(1);

    @Override
    public Component getComponent() {
        if (component == null) {
            component = new NurseryVisualPanel11();
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
  
        int checks=Integer.parseInt(NbPreferences.forModule(NurseryVisualPanel5.class).get("checks", "0"));
        int entries=Integer.parseInt(NbPreferences.forModule(NurseryVisualPanel41.class).get("entries", "0"));
        int total=entries+checks;
        
        //component.getjTextFieldEntries().setText(NbPreferences.forModule(NurseryVisualPanel6.class).get("entries", "0"));
        component.getjTextFieldEntries().setText(String.valueOf(total));
    }

    @Override
    public void storeSettings(Object settings) {
        NurseryEditorTopComponent nurseryWindow = NurseryWizardIterator.nurseryTopComponent;   
         int checks=Integer.parseInt(NbPreferences.forModule(NurseryVisualPanel5.class).get("checks", "0"));
        int entries=Integer.parseInt(NbPreferences.forModule(NurseryVisualPanel41.class).get("entries", "0"));
        int total=entries+checks;
        //nurseryWindow.getjTextFieldEntries().setText(NbPreferences.forModule(NurseryVisualPanel6.class).get("entries", "0"));
        nurseryWindow.getjTextFieldEntries().setText(String.valueOf(total));

    }



    
}
