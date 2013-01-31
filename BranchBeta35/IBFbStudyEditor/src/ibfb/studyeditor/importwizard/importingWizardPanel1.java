
package ibfb.studyeditor.importwizard;

import ibfb.studyeditor.core.StudyEditorTopComponent;
import java.awt.Component;
import javax.swing.event.ChangeListener;
import org.openide.WizardDescriptor;
import org.openide.util.HelpCtx;
import org.openide.windows.WindowManager;

public class importingWizardPanel1 implements WizardDescriptor.Panel {

  
    private importingVisualPanel1 component;
   
   
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

        StudyEditorTopComponent studyEditor = (StudyEditorTopComponent) WindowManager.getDefault().getRegistry().getActivated();

        if (component.jRadioButtonToFieldlog.isSelected()) {
            studyEditor.opcionImport = 1;

        } else if (component.jRadioButtonToExcel.isSelected()) {
            studyEditor.opcionImport = 0;
        } else {
            studyEditor.opcionImport = 2;
        }
    }
}
