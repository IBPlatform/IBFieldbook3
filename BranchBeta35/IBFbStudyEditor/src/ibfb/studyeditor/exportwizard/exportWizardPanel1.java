package ibfb.studyeditor.exportwizard;

import ibfb.studyeditor.core.StudyEditorTopComponent;
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
      
        StudyEditorTopComponent studyEditor = (StudyEditorTopComponent) WindowManager.getDefault().getRegistry().getActivated();

        if (studyEditor.getSelectedTraits().size()>0){      
            component.enabledR(true);
        } else {
            component.enabledR(false);

        }
        
          if (studyEditor.isForMaster()){      
            component.enabledCSV(false);
        } else {
            component.enabledCSV(true);

        }
        
    }

    @Override
    public void storeSettings(Object settings) {

        StudyEditorTopComponent studyEditor = (StudyEditorTopComponent) WindowManager.getDefault().getRegistry().getActivated();

        if (component.jRadioButtonToFieldlog.isSelected()) {
            
            studyEditor.opcionFiltro = 1;
            studyEditor.opcionExport = 0;
            return;
        }

        if (component.jRadioButtonToR.isSelected()) {
            
            studyEditor.opcionFiltro = 1;
            studyEditor.opcionExport = 1;


            return;
        }

        if (component.jRadioButtonToExcel.isSelected()) {
             
            studyEditor.opcionFiltro = 0;
            studyEditor.opcionExport = 2;
        }

       if(studyEditor.hasGY() ){
           
       }
        
    }
    

}
