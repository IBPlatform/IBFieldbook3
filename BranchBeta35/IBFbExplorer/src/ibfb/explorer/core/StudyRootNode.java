
package ibfb.explorer.core;

import org.openide.nodes.AbstractNode;
import org.openide.nodes.Children;
import org.openide.util.actions.SystemAction;

/**
 *
 * @author TMSANCHEZ
 */
public class StudyRootNode extends AbstractNode{

    public StudyRootNode(Children kids) {
        super(kids);
        setDisplayName("Studies");
    }

    @Override
    public SystemAction[] getActions() {
        SystemAction[] actions = new SystemAction[0];

        if (this.getDisplayName().equals("Studies")) {
           actions = new SystemAction[1];
           //actions[0] = SystemAction.get(NewStudyAction.class);
           actions[0] = SystemAction.get(NewStudyAction.class);
        } else {
           actions = new SystemAction[2];
           actions[0] = SystemAction.get(AddTrialAction.class);
           actions[1] = SystemAction.get(NewNurseryAction.class);
        }

        return actions;

    }

}
