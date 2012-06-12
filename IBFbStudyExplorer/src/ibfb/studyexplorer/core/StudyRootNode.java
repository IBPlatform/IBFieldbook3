package ibfb.studyexplorer.core;

import ibfb.studyexplorer.actions.NewStudyAction;
import ibfb.studyexplorer.actions.RefreshStudiesAction;
import javax.swing.Action;
import org.openide.nodes.AbstractNode;
import org.openide.nodes.Children;
import org.openide.util.actions.SystemAction;

/**
 *
 * @author TMSANCHEZ
 */
public class StudyRootNode extends AbstractNode {

    public StudyRootNode(Children kids) {
        super(kids);
        setIconBaseWithExtension("ibfb/studyexplorer/images/gcpIcon.png");
        setDisplayName("Studies");
    }

    @Override
    public SystemAction[] getActions() {
        SystemAction[] actions = new SystemAction[0];

        if (this.getDisplayName().equals("Studies")) {
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
