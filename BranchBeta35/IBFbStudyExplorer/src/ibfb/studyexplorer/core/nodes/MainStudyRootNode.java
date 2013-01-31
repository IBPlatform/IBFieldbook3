/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ibfb.studyexplorer.core.nodes;

import ibfb.studyexplorer.actions.NewStudyAction;
import ibfb.studyexplorer.actions.RefreshStudiesAction;
import ibfb.studyexplorer.explorer.StudyExplorerTopComponent;
import java.util.ResourceBundle;
import javax.swing.Action;
import org.openide.nodes.AbstractNode;
import org.openide.nodes.Children;
import org.openide.util.NbBundle;
import org.openide.util.actions.SystemAction;

/**
 * Main node for Study explorer
 * @author TMSANCHEZ
 */
public class MainStudyRootNode extends AbstractNode {
    private ResourceBundle bundle = NbBundle.getBundle(StudyExplorerTopComponent.class);
    public MainStudyRootNode(Children children) {
        super(children);
        setIconBaseWithExtension("ibfb/studyexplorer/core/nodes/rooticon.png");
        setDisplayName(bundle.getString("MainStudyRootNode.Studies"));
    }

    @Override
    public SystemAction[] getActions() {
        SystemAction[] actions = new SystemAction[0];

        if (this.getDisplayName().equals(bundle.getString("MainStudyRootNode.Studies"))) {
            actions = new SystemAction[2];
            actions[0] = SystemAction.get(NewStudyAction.class);
            actions[1] = SystemAction.get(RefreshStudiesAction.class);
            
        }

        return actions;

    }

    @Override
    public Action getPreferredAction() {
        return SystemAction.get(NewStudyAction.class);
    }
}
