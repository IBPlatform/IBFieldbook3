
package ibfb.explorer.core;

import ibfb.domain.core.Study;
import java.beans.IntrospectionException;
import java.util.List;
import org.openide.nodes.ChildFactory;
import org.openide.nodes.Node;
import org.openide.util.Exceptions;

/**
 *
 * @author TMSANCHEZ
 */
public class StudyChildFactory extends ChildFactory<Study>{

    private List<Study> resultList;

    public StudyChildFactory(List<Study> resultList) {
        this.resultList = resultList;
    }

    @Override
    protected boolean createKeys(List<Study> list) {
       for (Study study: resultList)    {
           list.add(study);
       }
       return true;
    }

    @Override
    protected Node createNodeForKey(Study study) {
        try {
           //return new BeanNode(study);
          return new StudyBeanNode(study);

        } catch (IntrospectionException ie) {
            Exceptions.printStackTrace(ie);
            return null;
        }
    }

}
