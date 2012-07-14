package ibfb.studyexplorer.actions;

import ibfb.domain.core.Study;
import ibfb.studyexplorer.explorer.StudyExplorerTopComponent;
import java.awt.event.ActionEvent;
import java.util.ResourceBundle;
import org.openide.explorer.ExplorerUtils;
import org.openide.nodes.Node;
import org.openide.util.HelpCtx;
import org.openide.util.NbBundle;
import org.openide.util.actions.SystemAction;
import org.openide.windows.WindowManager;

public class CloseNodeAction extends SystemAction {

    private org.openide.util.Lookup.Result<Study> result;
    private ResourceBundle bundle = NbBundle.getBundle(CloseNodeAction.class);
    
    Node miNodo = null;

    public CloseNodeAction() {
        
        putValue(NAME, bundle.getString("CloseNodeAction.close"));
        setEnabled(Boolean.TRUE);
        result = org.openide.util.Utilities.actionsGlobalContext().lookupResult(Study.class);
}

    @Override
    public void actionPerformed(ActionEvent e) {
       System.out.println("REMOVER NODO");

        
       
       StudyExplorerTopComponent studyExplorer = (StudyExplorerTopComponent) WindowManager.getDefault().findTopComponent("StudyExplorerTopComponent");


       Node[] nodes=studyExplorer.getExplorerManager().getSelectedNodes();
       
        for (int i = 0; i < nodes.length; i++) {
            System.out.println(nodes[i]);
            
        }
        
       // studyExplorer.getExplorerManager()
       
       ExplorerUtils.actionDelete(studyExplorer.getExplorerManager(), false);

       
     //  studyExplorer.getStudyList().remove(studyExplorer.getExplorerManager().getSelectedNodes());
     //  studyExplorer.refreshStudyBrowser();
       
    }

    @Override
    public String getName() {
        return bundle.getString("CloseNodeAction.close");
    }

    @Override
    public HelpCtx getHelpCtx() {
        return null;
    }
}
