/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ibfb.studyexplorer.core.nodes;

import ibfb.domain.core.Study;
import java.util.ArrayList;
import java.util.List;
import org.openide.nodes.Index;
import org.openide.nodes.Node;

/**
 *
 * @author TMSANCHEZ
 */
public class StudyExperimentChildren extends Index.ArrayChildren{
    
    private Study study;

    public StudyExperimentChildren(Study study) {
        this.study =  study;
    }

    @Override
    protected List<Node> initCollection() {
        List<Node> experimentNodeList =  new ArrayList<Node>();
        experimentNodeList.add(new ExperimentNode(new Experiment(Experiment.TRIAL,"Trials"),study));        
        experimentNodeList.add(new ExperimentNode(new Experiment(Experiment.NURSERY,"Nurseries"),study));
        return experimentNodeList;
    }
    
    
}
