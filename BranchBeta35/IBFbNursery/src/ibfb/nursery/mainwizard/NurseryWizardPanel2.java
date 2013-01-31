
package ibfb.nursery.mainwizard;
import java.awt.Component;
import javax.swing.event.ChangeListener;
import org.openide.WizardDescriptor;
import org.openide.util.HelpCtx;
import org.openide.util.NbPreferences;

public class NurseryWizardPanel2 implements WizardDescriptor.Panel {
    
    private NurseryVisualPanel2 component;

    
    @Override
    public Component getComponent() {
        if (component == null) {
            component = new NurseryVisualPanel2();
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
       
        if(NbPreferences.forModule(NurseryVisualPanel2.class).get("loaded2", "no").equals("no")){
         NurseryWizardIterator.myExcelReader.readExcelWorkbookTemplateStudyConditions();       
         component.fillData(NurseryWizardIterator.myExcelReader.getMyWorkbook());   
         NbPreferences.forModule(NurseryVisualPanel3.class).put("loaded3", "no");
        }
           
    }

    @Override
    public void storeSettings(Object settings) {
        NbPreferences.forModule(NurseryVisualPanel2.class).put("loaded2", "yes");
        component.copyValues();
    }
}
