package ibfb.studyexplorer.wizard;

import ibfb.studyexplorer.actions.ConfigStudiesAction;
import javax.swing.event.ChangeListener;
import org.openide.WizardDescriptor;
import org.openide.util.HelpCtx;
import org.openide.util.NbPreferences;

public class ExplorerWizardWizardPanel1 implements WizardDescriptor.Panel<WizardDescriptor> {

   
    private ExplorerWizardVisualPanel1 component;

   
    @Override
    public ExplorerWizardVisualPanel1 getComponent() {
        if (component == null) {
            component = new ExplorerWizardVisualPanel1();
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
        String selected = NbPreferences.forModule(ConfigStudiesAction.class).get("SELECTED", "");
        component.fillStudies();
        component.fillSelection(selected);
    }

    @Override
    public void storeSettings(WizardDescriptor wiz) {
       component.verifySelectedStudies();             
       String seleccionados=component.getSelected();
       ExplorerWizardWizardIterator.selectedStudies=seleccionados;
       ExplorerWizardWizardIterator.selectedStudyList=component.getSelectedStudyList();
       
       
    }
}
