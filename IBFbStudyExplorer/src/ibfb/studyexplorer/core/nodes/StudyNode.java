/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ibfb.studyexplorer.core.nodes;

import ibfb.domain.core.Study;
import ibfb.studyexplorer.actions.CloseAction;
import org.openide.nodes.AbstractNode;
import ibfb.studyexplorer.actions.OpenStudyAction;
import ibfb.studyexplorer.actions.ShowOptionsAction;
import ibfb.studyexplorer.actions.ShowPropAction;
import javax.swing.Action;
import org.openide.util.actions.SystemAction;
import org.openide.util.lookup.Lookups;

/**
 *
 * @author TMSANCHEZ
 */
class StudyNode extends AbstractNode {

    private Study study;

    public StudyNode(Study study) {
        super(new StudyExperimentChildren(study), Lookups.singleton(study));
        this.study = study;
        setDisplayName(study.getStudy());
        if (study.getStudyid().intValue() < 0) {
           setIconBaseWithExtension("ibfb/studyexplorer/core/nodes/studylocal.png"); 
        } else {
            setIconBaseWithExtension("ibfb/studyexplorer/core/nodes/studyicon.png");
        }
    }

    @Override
    public Action[] getActions(boolean context) {
        SystemAction[] actions = new SystemAction[2];
        actions[0] = SystemAction.get(ShowOptionsAction.class);
        actions[1] = SystemAction.get(ShowPropAction.class);
       // actions[2] = SystemAction.get(CloseAction.class ) ;
        
        //actions[2] = SystemAction.get(OpenStudyAction.class);
        return actions;
    }

    @Override
    public Action getPreferredAction() {
                return SystemAction.get(ShowOptionsAction.class);
    }

    public Study getStudy() {
        return this.study;
    }
    
     @Override
    public boolean canDestroy() {
        return true;
    }
}
