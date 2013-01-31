package ibfb.studyexplorer.core;



import ibfb.domain.core.Trial;
import ibfb.studyexplorer.actions.ShowOptionsAction;
import ibfb.studyexplorer.actions.ShowPropAction;
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
public class TrialBeanNode extends BeanNode<Trial> {

    public TrialBeanNode(Trial trial) throws IntrospectionException {
        super(trial, Children.LEAF, Lookups.singleton(trial));
        setDisplayName("Trial");
        setIconBaseWithExtension("ibfb/studyexplorer/images/study.png");

    }

    @Override
    public Action[] getActions(boolean context) {
        SystemAction[] actions = new SystemAction[1];
        actions[0] = SystemAction.get(ShowPropAction.class);       
        return actions;
    }

    @Override
    public Action getPreferredAction() {
        return SystemAction.get(ShowOptionsAction.class);
    }
}
