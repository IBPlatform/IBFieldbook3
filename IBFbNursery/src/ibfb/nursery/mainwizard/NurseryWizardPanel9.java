
package ibfb.nursery.mainwizard;

import java.awt.Component;
import javax.swing.event.ChangeListener;
import org.openide.WizardDescriptor;
import org.openide.util.HelpCtx;

public class NurseryWizardPanel9 implements WizardDescriptor.Panel {


    private NurseryVisualPanel9 component;


    @Override
    public Component getComponent() {
        if (component == null) {
            component = new NurseryVisualPanel9();
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
         if(component.giveMeTotalConstants(NurseryWizardIterator.myExcelReader.getMyWorkbook()) >0){
             NurseryWizardIterator.existenConstantes=true;
         }else{
               NurseryWizardIterator.existenConstantes=false;
         }
    }

    @Override
    public void storeSettings(Object settings) {
        component.copyValues(NurseryWizardIterator.myExcelReader.getMyWorkbook());
    }
}
