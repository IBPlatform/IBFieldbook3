/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ibfb.studyexplorer.core.nodes;

import ibfb.domain.core.Study;
import java.util.List;
import org.openide.nodes.Children;
import org.openide.nodes.Node;

/**
 *
 * @author TMSANCHEZ
 */
public class StudyChildren extends Children.Keys {

    private List<Study> studyList;
    private Study[] studies;

    public StudyChildren(List<Study> studyList) {
        this.studyList = studyList;
    }

    @Override
    protected Node[] createNodes(Object key) {
        Study study = (Study) key;
        return new Node[]{new StudyNode(study)};
    }

    @Override
    protected void addNotify() {
        super.addNotify();
        List<Study> studyList = getStudyList();
        setKeys(studyList);
    }


    private List<Study> getStudyList() {
        return studyList;
    }
}
