
package ibfb.nursery.mainwizard;

import java.awt.Component;
import javax.swing.event.ChangeListener;
import org.openide.WizardDescriptor;
import org.openide.util.HelpCtx;
import org.openide.util.NbPreferences;

public class NurseryWizardPanel7 implements WizardDescriptor.Panel {

  
    private NurseryVisualPanel7 component;

  
    @Override
    public Component getComponent() {
        if (component == null) {
            component = new NurseryVisualPanel7();
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
        int otherFactors=NurseryWizardIterator.myExcelReader.getMyWorkbook().getOtherFactors().size();
        ((WizardDescriptor) settings).putProperty("otherFactors", otherFactors);
        NbPreferences.forModule(NurseryVisualPanel5.class).put("otherFactors", String.valueOf(otherFactors));
        component.copyLevels();
        
    }
}
