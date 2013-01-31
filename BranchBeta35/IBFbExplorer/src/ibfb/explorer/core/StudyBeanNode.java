package ibfb.explorer.core;

import ibfb.domain.core.Study;
import java.beans.IntrospectionException;
import javax.swing.Action;

import org.openide.nodes.BeanNode;
import org.openide.nodes.Children;
import org.openide.util.actions.SystemAction;
import org.openide.util.lookup.Lookups;

/**
 *
 * @author TMSANCHEZ
 */
public class StudyBeanNode extends BeanNode<Study> {

    public StudyBeanNode(Study study) throws IntrospectionException {
        super(study, Children.LEAF, Lookups.singleton(study));
        setDisplayName(study.getStudy() + " - " + study.getTitle());
        setIconBaseWithExtension("org/cimmyt/explorer/study.png");

    }

    @Override
    public Action[] getActions(boolean context) {
        SystemAction[] currentActions = (SystemAction[]) super.getActions(context);
        SystemAction[] actions = new SystemAction[2];
        actions[0] = SystemAction.get(ShowOptonsAction.class);
        actions[1] = SystemAction.get(ShowPropAction.class);
        return actions;
    }

    @Override
    public Action getPreferredAction() {
        return SystemAction.get(ShowOptonsAction.class);
    }
}
