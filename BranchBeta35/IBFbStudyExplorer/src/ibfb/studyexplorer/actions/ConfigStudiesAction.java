
package ibfb.studyexplorer.actions;

import ibfb.studyexplorer.explorer.StudyExplorerTopComponent;
import ibfb.studyexplorer.wizard.ExplorerWizardWizardIterator;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.MessageFormat;
import org.openide.DialogDisplayer;
import org.openide.WizardDescriptor;
import org.openide.awt.ActionID;
import org.openide.awt.ActionReference;
import org.openide.awt.ActionReferences;
import org.openide.awt.ActionRegistration;
import org.openide.util.NbBundle.Messages;
import org.openide.util.NbPreferences;
import org.openide.windows.WindowManager;

@ActionID(category = "Database",
id = "ibfb.studyexplorer.actions.ConfigStudies")
@ActionRegistration(iconBase = "ibfb/studyexplorer/images/config_16.png",
displayName = "#CTL_ConfigStudies")
@ActionReferences({
    @ActionReference(path = "Menu/Database", position = 3033),
    @ActionReference(path = "Toolbars/File", position = -500)
})
@Messages("CTL_ConfigStudies=Set Studies")
public final class ConfigStudiesAction implements ActionListener {

    @Override
    public void actionPerformed(ActionEvent e) {
       WizardDescriptor wiz = new WizardDescriptor(new ExplorerWizardWizardIterator());
    //             // {0} will be replaced by WizardDescriptor.Panel.getComponent().getName()
    //             // {1} will be replaced by WizardDescriptor.Iterator.name()
                 wiz.setTitleFormat(new MessageFormat("{0} ({1})"));
                 wiz.setTitle("...dialog title...");
                 if (DialogDisplayer.getDefault().notify(wiz) == WizardDescriptor.FINISH_OPTION) {
                             StudyExplorerTopComponent explorer = (StudyExplorerTopComponent) WindowManager.getDefault().findTopComponent("StudyExplorerTopComponent");
                             explorer.setSelectedStudyList(ExplorerWizardWizardIterator.selectedStudyList);
                             explorer.refreshSelectedStudyBrowser();
                             NbPreferences.forModule(ConfigStudiesAction.class).put("SELECTED", ExplorerWizardWizardIterator.selectedStudies);
                 }
    }
}
