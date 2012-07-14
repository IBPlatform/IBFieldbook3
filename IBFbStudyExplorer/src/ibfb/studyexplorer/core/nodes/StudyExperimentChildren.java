
package ibfb.studyexplorer.core.nodes;

import ibfb.domain.core.Study;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import org.openide.nodes.Index;
import org.openide.nodes.Node;
import org.openide.util.NbBundle;

/**
 *
 * @author TMSANCHEZ
 */
public class StudyExperimentChildren extends Index.ArrayChildren{
    private ResourceBundle bundle = NbBundle.getBundle(StudyExperimentChildren.class);

    private Study study;

    public StudyExperimentChildren(Study study) {
        this.study =  study;
    }

    @Override
    protected List<Node> initCollection() {
        List<Node> experimentNodeList =  new ArrayList<Node>();
        experimentNodeList.add(new ExperimentNode(new Experiment(Experiment.TRIAL,bundle.getString("StudyExperimentChildren.trials")),study));        
        experimentNodeList.add(new ExperimentNode(new Experiment(Experiment.NURSERY,bundle.getString("StudyExperimentChildren.nurseries")),study));
        return experimentNodeList;
    }
    
    
}
