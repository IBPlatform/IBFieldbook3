/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ibfb.studyexplorer.actions;

import ibfb.domain.core.SelectedStudy;
import ibfb.studyexplorer.explorer.StudyExplorerTopComponent;
import java.awt.event.ActionEvent;
import java.util.ResourceBundle;
import org.cimmyt.cril.ibwb.api.AppServicesProxy;
import org.cimmyt.cril.ibwb.commongui.DialogUtil;
import org.cimmyt.cril.ibwb.domain.Study;
import org.openide.NotifyDescriptor;
import org.openide.util.HelpCtx;
import org.openide.util.NbBundle;
import org.openide.util.actions.SystemAction;
import org.openide.windows.WindowManager;

/**
 *
 * @author tmsg
 */
public class DeleteStudyAction extends SystemAction {
    private ResourceBundle bundle = NbBundle.getBundle(DeleteStudyAction.class);    

    public DeleteStudyAction() {
        putValue(NAME, bundle.getString("DeleteStudyAction.name"));
        setEnabled(Boolean.TRUE);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
       if (DialogUtil.displayConfirmation(bundle.getString("DeleteStudy.confirmation"),bundle.getString("DeleteStudy.title"), NotifyDescriptor.OK_CANCEL_OPTION)) {
           Study studyToDelete = new Study(true);
           studyToDelete.setStudyid(SelectedStudy.selected.getStudyid());
           AppServicesProxy.getDefault().appServices().deleteStudy(studyToDelete);
           StudyExplorerTopComponent studyExplorer = (StudyExplorerTopComponent) WindowManager.getDefault().findTopComponent("StudyExplorerTopComponent");
           studyExplorer.refreshStudyBrowser();
       }
    }

    @Override
    public String getName() {
        return bundle.getString("DeleteStudyAction.title");
    }

    @Override
    public HelpCtx getHelpCtx() {
        return null;
    }
}
