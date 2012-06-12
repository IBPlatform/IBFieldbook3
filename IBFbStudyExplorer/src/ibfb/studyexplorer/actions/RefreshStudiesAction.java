/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ibfb.studyexplorer.actions;

import ibfb.studyexplorer.explorer.StudyExplorerTopComponent;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import org.openide.awt.ActionRegistration;
import org.openide.awt.ActionReference;
import org.openide.awt.ActionReferences;
import org.openide.awt.ActionID;
import org.openide.util.HelpCtx;
import org.openide.util.NbBundle.Messages;
import org.openide.util.actions.SystemAction;
import org.openide.windows.WindowManager;

@ActionID(category = "Build",
id = "ibfb.studyexplorer.actions.SomeAction")
@ActionRegistration(displayName = "#CTL_SomeAction")
@ActionReferences({
    @ActionReference(path = "Menu/Study", position = 1100)
})
@Messages("CTL_SomeAction=Refresh study list")
//public final class RefreshStudiesAction implements ActionListener {
public final class RefreshStudiesAction extends SystemAction {
    public RefreshStudiesAction() {
        putValue(NAME, "Refresh study list");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
         StudyExplorerTopComponent studyExplorer = (StudyExplorerTopComponent) WindowManager.getDefault().findTopComponent("StudyExplorerTopComponent");
         studyExplorer.refreshStudyBrowser();
    }

    @Override
    public String getName() {
        return "Refresh study list";
    }

    @Override
    public HelpCtx getHelpCtx() {
        return null;
    }
}
