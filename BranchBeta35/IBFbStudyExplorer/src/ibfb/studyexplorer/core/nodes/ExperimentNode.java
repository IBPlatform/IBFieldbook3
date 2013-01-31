/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ibfb.studyexplorer.core.nodes;

import ibfb.domain.core.SelectedExperiment;
import ibfb.domain.core.Study;
import ibfb.nursery.actions.NewWizardNurseryAction;
import ibfb.nursery.actions.QuickNurseryCreationAction;
import ibfb.studyexplorer.actions.NewTrialAction;
import ibfb.studyexplorer.actions.QuickCreationAction;
import ibfb.studyexplorer.actions.ShowOptionsAction;
import javax.swing.Action;
import org.openide.nodes.AbstractNode;
import org.openide.util.actions.SystemAction;
import org.openide.util.lookup.Lookups;

/**
 *
 * @author TMSANCHEZ
 */
public class ExperimentNode extends AbstractNode {

    private Experiment experiment;
    private Study study;

    public ExperimentNode(Experiment key, Study study) {
        //super(Children.LEAF, Lookups.fixed(new Object[]{key}));
        super( new ExperimentChildren(key,study),Lookups.fixed(new Object[]{key, study}));
        this.experiment = key;
        this.study = study;
        setDisplayName(key.getDescription());
        
        if (key.getType().equals(Experiment.TRIAL)) {
            setIconBaseWithExtension("ibfb/studyexplorer/core/nodes/trialicon.png");  
          
        } else if (key.getType().equals(Experiment.NURSERY)) {
            setIconBaseWithExtension("ibfb/studyexplorer/core/nodes/nurseryicon.png");     
       
            
        }
        
        
    }

    @Override
    public Action[] getActions(boolean context) {
        SystemAction[] actions = new SystemAction[2];
        if (experiment.getType().equals(Experiment.TRIAL)) {
            actions[0] = SystemAction.get(NewTrialAction.class);
            actions[1] = SystemAction.get(QuickCreationAction.class);
        } else if (experiment.getType().equals(Experiment.NURSERY)) {
            actions[0] = SystemAction.get(NewWizardNurseryAction.class);
            actions[1] = SystemAction.get(QuickNurseryCreationAction.class);
            
        }
        
        return actions;
    }

    @Override
    public Action getPreferredAction() {    
        if(this.experiment.getType().equals(Experiment.TRIAL)){
            SelectedExperiment.selected.setType(Experiment.TRIAL);
            return SystemAction.get(ShowOptionsAction.class);
            
        }else{
             SelectedExperiment.selected.setType(Experiment.NURSERY);   
             return SystemAction.get(ShowOptionsAction.class);      
        }
        
    }
    
     @Override
    public boolean canDestroy() {
        return true;
    }

    public Study getStudy() {
        return study;
    }
     
     
     
}
