/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ibfb.studyexplorer.actions;

import ibfb.studyexplorer.explorer.StudyExplorerTopComponent;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ResourceBundle;
import org.openide.awt.ActionRegistration;
import org.openide.awt.ActionReference;
import org.openide.awt.ActionReferences;
import org.openide.awt.ActionID;
import org.openide.util.HelpCtx;
import org.openide.util.NbBundle;
import org.openide.util.NbBundle.Messages;
import org.openide.util.actions.SystemAction;
import org.openide.windows.WindowManager;

@ActionID(category = "Build",
id = "ibfb.studyexplorer.actions.RefreshStudiesAction")
@ActionRegistration(displayName = "#CTL_RefreshStudiesAction")
@ActionReferences({
    @ActionReference(path = "Menu/Study", position = 1100)
})
//@Messages("CTL_RefreshStudiesAction=Refresh study list")
//public final class RefreshStudiesAction implements ActionListener {
public final class RefreshStudiesAction extends SystemAction {
    private ResourceBundle bundle = NbBundle.getBundle(RefreshStudiesAction.class);
    public RefreshStudiesAction() {
        putValue(NAME, bundle.getString("CTL_RefreshStudiesAction"));
    }

    @Override
    public void actionPerformed(ActionEvent e) {
         StudyExplorerTopComponent studyExplorer = (StudyExplorerTopComponent) WindowManager.getDefault().findTopComponent("StudyExplorerTopComponent");
         studyExplorer.refreshStudyBrowser();
    }

    @Override
    public String getName() {
        return bundle.getString("CTL_RefreshStudiesAction");
    }

    @Override
    public HelpCtx getHelpCtx() {
        return null;
    }
}
