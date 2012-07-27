
package ibfb.studyexplorer.actions;

import ibfb.domain.core.Study;
import ibfb.studyexplorer.explorer.StudyExplorerTopComponent;
import java.awt.event.ActionEvent;
import java.util.List;
import org.openide.util.HelpCtx;
import org.openide.util.actions.SystemAction;
import org.openide.windows.WindowManager;


public class CloseAction extends SystemAction {

    public CloseAction() {
        putValue(NAME, "Close");
        setEnabled(Boolean.TRUE);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
              StudyExplorerTopComponent studyExplorer = (StudyExplorerTopComponent) WindowManager.getDefault().findTopComponent("StudyExplorerTopComponent");               
              studyExplorer.getStudyList().remove(studyExplorer.getSelectedNode());
            studyExplorer.refreshStudyBrowserOnClose();

  
    }

    @Override
    public String getName() {
        return "Close";
    }

    @Override
    public HelpCtx getHelpCtx() {
        return null;
    }
}
