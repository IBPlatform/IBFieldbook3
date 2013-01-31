package ibfb.studyexplorer.core.nodes;

import ibfb.domain.core.SelectedExperiment;
import ibfb.domain.core.SelectedStudy;
import ibfb.domain.core.Study;
import ibfb.nursery.actions.OpenNurseryAction;
import ibfb.studyexplorer.actions.DeleteStudyAction;
import org.openide.nodes.Children;
import org.openide.nodes.AbstractNode;
import ibfb.studyexplorer.actions.OpenStudyAction;
import javax.swing.Action;
import org.openide.util.NbBundle;
import org.openide.util.actions.SystemAction;
import org.openide.util.lookup.Lookups;

/**
 *
 * @author TMSANCHEZ
 */
public class StudyExperimentNode extends AbstractNode {

    private Study study;

    public StudyExperimentNode(Study key) {
        super(Children.LEAF, Lookups.fixed(new Object[]{key}));
        this.study = key;
        setDisplayName(key.getStudy());
    }

    @Override
    public Action[] getActions(boolean context) {

        SystemAction[] actions = new SystemAction[2];

        if (study.getStudyType().equals("T")) {

            actions[0] = SystemAction.get(OpenStudyAction.class);
        } else {
            actions[0] = SystemAction.get(OpenNurseryAction.class);
        }


        actions[1] = SystemAction.get(DeleteStudyAction.class);

        DeleteStudyAction deleteStudyAction = (DeleteStudyAction)actions[1];
        if (study.getStudyType().equals("T")) {
           deleteStudyAction.setDeleteTitle(NbBundle.getMessage(DeleteStudyAction.class, "DeleteStudyTrial.title")); 
        } else {
           deleteStudyAction.setDeleteTitle(NbBundle.getMessage(DeleteStudyAction.class, "DeleteStudyNursery.title"));  
        }

        actions[1].setEnabled(this.study.getStudyid().intValue() < 0);

        return actions;
    }

    @Override
    public Action getPreferredAction() {

        if (study.getStudyType().equals("T")) {
            return SystemAction.get(OpenStudyAction.class);
        } else {
            return SystemAction.get(OpenNurseryAction.class);
        }

    }

    public Study getStudy() {
        return this.study;
    }

    @Override
    public boolean canDestroy() {
        return true;
    }
}
