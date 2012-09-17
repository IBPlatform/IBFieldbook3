
package ibfb.studyeditor.exportformaize;

import ibfb.studyeditor.core.StudyEditorTopComponent;
import javax.swing.event.ChangeListener;
import org.openide.WizardDescriptor;
import org.openide.util.HelpCtx;
import org.openide.windows.WindowManager;

public class maizeExportWizardPanel1 implements WizardDescriptor.Panel<WizardDescriptor> {


    private maizeExportVisualPanel1 component;

   
    @Override
    public maizeExportVisualPanel1 getComponent() {
        if (component == null) {
            component = new maizeExportVisualPanel1();
        }
        return component;
    }

    @Override
    public HelpCtx getHelp() {
        // Show no Help button for this panel:
        return HelpCtx.DEFAULT_HELP;
        // If you have context help:
        // return new HelpCtx("help.key.here");
    }

    @Override
    public boolean isValid() {
        return true;
        
    }

    @Override
    public void addChangeListener(ChangeListener l) {
    }

    @Override
    public void removeChangeListener(ChangeListener l) {
    }

    @Override
    public void readSettings(WizardDescriptor wiz) {
    }

    @Override
    public void storeSettings(WizardDescriptor wiz) {
       StudyEditorTopComponent studyEditor = (StudyEditorTopComponent) WindowManager.getDefault().getRegistry().getActivated();

        if (component.jRadioButtonToFieldlog.isSelected()) {
            
            studyEditor.opcionFiltro = 1;
            studyEditor.opcionExport = 0;
            return;
        }
        
         if (component.jRadioButtonToExcel.isSelected()) {
             
            studyEditor.opcionFiltro = 0;
            studyEditor.opcionExport = 2;
        }
    }
}
