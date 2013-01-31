
package ibfb.nursery.mainwizard;

import java.awt.Component;
import javax.swing.event.ChangeListener;
import org.openide.WizardDescriptor;
import org.openide.util.HelpCtx;
import org.openide.util.NbPreferences;

public class NurseryWizardPanel3 implements WizardDescriptor.Panel {

    private NurseryVisualPanel3 component;

    @Override
    public Component getComponent() {
        if (component == null) {
            component = new NurseryVisualPanel3();
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
        
        if(NbPreferences.forModule(NurseryVisualPanel3.class).get("loaded3", "no").equals("no")){
         NurseryWizardIterator.myExcelReader.readExcelWorkbookTemplate();
         component.fillData(NurseryWizardIterator.myExcelReader.getMyWorkbook());              
        
         if(component.giveMeTotalConditions()>0){
             NurseryWizardIterator.existenConditions=true;
         }else{
               NurseryWizardIterator.existenConditions=false;
         }
        
        }
    }

    @Override
    public void storeSettings(Object settings) {
        component.copyValues();
        NbPreferences.forModule(NurseryVisualPanel3.class).put("loaded3", "yes");
    }
}
