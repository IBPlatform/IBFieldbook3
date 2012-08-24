/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ibfb.studyexplorer.actions;

import ibfb.domain.core.SelectedStudy;
import ibfb.studyexplorer.core.nodes.StudyExperimentNode;
import ibfb.studyexplorer.explorer.StudyExplorerTopComponent;
import java.awt.event.ActionEvent;
import java.util.ResourceBundle;
import org.cimmyt.cril.ibwb.api.AppServicesProxy;
import org.cimmyt.cril.ibwb.commongui.DialogUtil;
import org.cimmyt.cril.ibwb.domain.Study;
import org.openide.NotifyDescriptor;
import org.openide.explorer.ExplorerManager;
import org.openide.nodes.Node;
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
        if (DialogUtil.displayConfirmation(bundle.getString("DeleteStudy.confirmation"), bundle.getString("DeleteStudy.title"), NotifyDescriptor.OK_CANCEL_OPTION)) {

            StudyExplorerTopComponent studyExplorer = (StudyExplorerTopComponent) WindowManager.getDefault().findTopComponent("StudyExplorerTopComponent");
            ExplorerManager explorerManager = studyExplorer.getExplorerManager();
            for (Node selectedNode : explorerManager.getSelectedNodes()) {
                if (selectedNode instanceof StudyExperimentNode) {
                    StudyExperimentNode studyExperimentNode = (StudyExperimentNode)selectedNode;
                    Study studyToDelete = new Study(true);
                    studyToDelete.setStudyid(studyExperimentNode.getStudy().getStudyid());
                    AppServicesProxy.getDefault().appServices().deleteStudy(studyToDelete);
                }

            }



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