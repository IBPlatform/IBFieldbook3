package ibfb.studyexplorer.core;

import ibfb.domain.core.Study;
import ibfb.nursery.actions.OpenNurseryAction;
import ibfb.studyexplorer.actions.OpenStudyAction;
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
public class StudyBeanNode extends BeanNode<Study> {

    private Study study;

    public StudyBeanNode(Study study) throws IntrospectionException {
        super(study, Children.LEAF, Lookups.singleton(study));


        setDisplayName(study.getStudy() + " - " + study.getTitle());
        setIconBaseWithExtension("ibfb/studyexplorer/images/study.png");
        this.study = study;
    }

    @Override
    public Action[] getActions(boolean context) {
        SystemAction[] actions = new SystemAction[4];
        actions[0] = SystemAction.get(ShowOptionsAction.class);
        actions[1] = SystemAction.get(ShowPropAction.class);
        actions[2] = SystemAction.get(OpenStudyAction.class);
        actions[3] = SystemAction.get(OpenNurseryAction.class);
        return actions;
    }

    @Override
    public Action getPreferredAction() {
        return SystemAction.get(ShowOptionsAction.class);
    }

    public Study getStudy() {
        return this.study;
    }
}
