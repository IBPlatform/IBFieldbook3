
package ibfb.studyexplorer.actions;

import ibfb.studyexplorer.explorer.StudyExplorerTopComponent;
import java.awt.event.ActionEvent;
import java.util.ResourceBundle;
import org.openide.util.HelpCtx;
import org.openide.util.NbBundle;
import org.openide.util.actions.SystemAction;
import org.openide.windows.WindowManager;


public class CloseAction extends SystemAction {
    
        private ResourceBundle bundle = NbBundle.getBundle(CloseAction.class);


    public CloseAction() {
        putValue(NAME, bundle.getString("CloseAction.name"));
        setEnabled(Boolean.TRUE);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
              StudyExplorerTopComponent studyExplorer = (StudyExplorerTopComponent) WindowManager.getDefault().findTopComponent("StudyExplorerTopComponent");               
              studyExplorer.refreshStudyBrowserOnClose(); 
    }

    @Override
    public String getName() {
        return bundle.getString("CloseAction.name");
    }

    @Override
    public HelpCtx getHelpCtx() {
        return null;
    }
}
