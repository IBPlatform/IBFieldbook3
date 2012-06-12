package ibfb.studyexplorer.actions;

import ibfb.domain.core.Study;
import ibfb.studyexplorer.explorer.StudyExplorerTopComponent;
import java.awt.event.ActionEvent;
import javax.swing.Action;
import org.openide.explorer.ExplorerUtils;
import org.openide.nodes.Node;
import org.openide.windows.WindowManager;
import org.openide.util.HelpCtx;
import org.openide.util.actions.SystemAction;

public class CloseNodeAction extends SystemAction {

    private org.openide.util.Lookup.Result<Study> result;
    
    Node miNodo = null;

    public CloseNodeAction() {
        
        putValue(NAME, "Close Study");
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
        return "Close Study";
    }

    @Override
    public HelpCtx getHelpCtx() {
        return null;
    }
}
