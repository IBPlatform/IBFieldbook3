/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ibfb.studyexplorer.core;

import ibfb.domain.core.Study;
import java.beans.IntrospectionException;
import java.util.ArrayList;
import java.util.List;
import org.cimmyt.cril.ibwb.api.AppServicesProxy;
import org.cimmyt.cril.ibwb.commongui.ConvertUtils;
import org.openide.nodes.Children;
import org.openide.nodes.Node;
import org.openide.util.Exceptions;

/**
 *
 * @author TMSANCHEZ
 */
public class StudyChildren extends Children.Keys {

    public StudyChildren() {
    }

    @Override
    @SuppressWarnings("unchecked")
    protected void addNotify() {
        Study study = null;        
        Node node = getNode();
        if (node instanceof StudyRootNode) {
            study = new Study();
            study.setShierarchy(0);
            
        } else if (node instanceof StudyBeanNode) {
            StudyBeanNode sbn = (StudyBeanNode)node;
            study = sbn.getStudy();
        }
        
        if (study == null) {
            study = new Study();
            study.setShierarchy(0);
        }
        List<Study> studyList = getStudyList(study);
        setKeys(studyList);
    }

    @Override
    protected Node[] createNodes(Object key) {
        try {
            Study study = (Study) key;
            return new Node[]{new StudyBeanNode(study)};
        } catch (IntrospectionException ex) {
            Exceptions.printStackTrace(ex);
        }
        return null;
    }

    private List<Study> getStudyList(Study study) {
        List<Study> studyList = new ArrayList<Study>();
        //List<org.cimmyt.cril.ibwb.domain.Study> ibwbStudys = AppServicesProxy.getDefault().appServices().getStudyListByParent(study.getShierarchy());
//
//        for (org.cimmyt.cril.ibwb.domain.Study studyDto : ibwbStudys) {
//            studyList.add(castStudy(studyDto));
//        }
//        
        return studyList;
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
        return studytmp;
    }
}
