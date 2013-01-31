/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ibfb.nursery.importwizard;

import ibfb.nursery.core.NurseryEditorTopComponent;
import java.awt.Component;
import javax.swing.event.ChangeListener;
import org.openide.WizardDescriptor;
import org.openide.util.HelpCtx;
import org.openide.windows.WindowManager;

public class importingWizardPanel1 implements WizardDescriptor.Panel {

    private importingVisualPanel1 component;

    @Override
    public Component getComponent() {
        if (component == null) {
            component = new importingVisualPanel1();
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
        
              NurseryEditorTopComponent nurseryEditor = (NurseryEditorTopComponent) WindowManager.getDefault().getRegistry().getActivated();

              

        if (component.jRadioButtonToFieldlog.isSelected()) {
            nurseryEditor.opcionImport = 1;

        } else {
            nurseryEditor.opcionImport = 0;
        }    
                   
    }
}
