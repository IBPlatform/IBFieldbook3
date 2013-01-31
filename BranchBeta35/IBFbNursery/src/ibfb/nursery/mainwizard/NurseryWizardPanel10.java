 
package ibfb.nursery.mainwizard;

import java.awt.Component;
import java.util.ResourceBundle;
import javax.swing.event.ChangeListener;
import org.openide.WizardDescriptor;
import org.openide.util.HelpCtx;
import org.openide.util.NbBundle;

public class NurseryWizardPanel10 implements WizardDescriptor.Panel {
   
    private NurseryVisualPanel10 component;

    @Override
    public Component getComponent() {
        if (component == null) {
            component = new NurseryVisualPanel10();
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
        component.fillData(NurseryWizardIterator.myExcelReader.getMyWorkbook());
    }

    @Override
    public void storeSettings(Object settings) {
         component.copyValues(NurseryWizardIterator.myExcelReader.getMyWorkbook());
    }
}
