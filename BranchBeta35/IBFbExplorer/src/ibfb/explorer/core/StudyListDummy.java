
package ibfb.explorer.core;

import ibfb.domain.core.Study;
import java.util.ArrayList;
import java.util.List;


/**
 *
 * @author TMSANCHEZ
 */
public class StudyListDummy {
    private static List<Study> studyList = new ArrayList<Study>();

    public static void addStudy(Study study) {
        //study.setTitle("Study " + studyList.size() );
        studyList.add(study);
    }

    public static List<Study> getStudyList() {
      return studyList;
    }
}
