
package ibfb.nursery.mainwizard;

import java.awt.Component;
import javax.swing.event.ChangeListener;
import org.openide.WizardDescriptor;
import org.openide.util.HelpCtx;

public class NurseryWizardPanel8 implements WizardDescriptor.Panel {

    
    private NurseryVisualPanel8 component;

   
    @Override
    public Component getComponent() {
        if (component == null) {
            component = new NurseryVisualPanel8();
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
                component.StopEditor();
        component.generateCombinations();
    }
}
