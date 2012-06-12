/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ibfb.studyexplorer.core.nodes;

import ibfb.domain.core.Study;
import java.util.ArrayList;
import java.util.List;
import org.cimmyt.cril.ibwb.api.AppServicesProxy;
import org.cimmyt.cril.ibwb.commongui.ConvertUtils;
import org.openide.nodes.Index;
import org.openide.nodes.Node;

/**
 *
 * @author TMSANCHEZ
 */
public class ExperimentChildren extends Index.ArrayChildren {

    private Experiment experiment;
    private Study study;

    public ExperimentChildren(Experiment experiment, Study study) {
        this.experiment = experiment;
        this.study = study;
    }

    @Override
    protected List<Node> initCollection() {
        List<Node> studyNodeList = new ArrayList<Node>();


        List<org.cimmyt.cril.ibwb.domain.Study> ibwbStudys = AppServicesProxy.getDefault().appServices().getStudyListByParent(study.getStudyid(), experiment.getType());

        for (org.cimmyt.cril.ibwb.domain.Study studyDto : ibwbStudys) {

            studyNodeList.add(new StudyExperimentNode(castStudy(studyDto)));
        }

        return studyNodeList;
    }

    private Study castStudy(org.cimmyt.cril.ibwb.domain.Study studyDto) {
        Study studytmp = new Study();
        studytmp.setStudy(studyDto.getSname());
        studytmp.setTitle(studyDto.getTitle());
        studytmp.setObjective(studyDto.getObjectiv());
        studytmp.setEndDate(ConvertUtils.getIntegerAsDate(studyDto.getEdate()));
        studytmp.setStarDate(ConvertUtils.getIntegerAsDate(studyDto.getEdate()));
        studytmp.setPmkey(studyDto.getPmkey().toString());
        studytmp.setShierarchy(studyDto.getShierarchy());
        studytmp.setStudyid(studyDto.getStudyid());
        studytmp.setStudyType(studyDto.getStype());
        return studytmp;
    }
}
