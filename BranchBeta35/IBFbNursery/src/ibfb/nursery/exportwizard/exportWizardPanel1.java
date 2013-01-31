
package ibfb.nursery.exportwizard;


import ibfb.nursery.core.NurseryEditorTopComponent;
import java.awt.Component;
import javax.swing.event.ChangeListener;
import org.openide.WizardDescriptor;
import org.openide.util.HelpCtx;
import org.openide.windows.WindowManager;

public class exportWizardPanel1 implements WizardDescriptor.Panel {


    private exportVisualPanel1 component;

  
    @Override
    public Component getComponent() {
        if (component == null) {
            component = new exportVisualPanel1();
        }
        return component;
    }
    
    
  
    @Override
    public HelpCtx getHelp() {
        
        return HelpCtx.DEFAULT_HELP;
       
    }

    @Override
    public boolean isValid() {
        // If it is always OK to press Next or Finish, then:
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
         NurseryEditorTopComponent nurseryEditor = (NurseryEditorTopComponent) WindowManager.getDefault().getRegistry().getActivated();

        if (nurseryEditor.getSelectedTraits().size()>0){      
            component.enabledR(true);
        } else {
            component.enabledR(false);

        }
    }

    @Override
    public void storeSettings(Object settings) {
        
      NurseryEditorTopComponent nurseryEditor = (NurseryEditorTopComponent) WindowManager.getDefault().getRegistry().getActivated();


        if (component.jRadioButtonToFieldlog.isSelected()) {
            nurseryEditor.opcionFiltro = 1;
           nurseryEditor.opcionExport = 0;
            return;
        }


        if (component.jRadioButtonToR.isSelected()) {
            nurseryEditor.opcionFiltro = 1;
            nurseryEditor.opcionExport = 1;
            return;
        }

        if (component.jRadioButtonToExcel.isSelected()) {
            nurseryEditor.opcionFiltro = 0;
            nurseryEditor.opcionExport = 2;
            
            return;
        }
 
    }
}
