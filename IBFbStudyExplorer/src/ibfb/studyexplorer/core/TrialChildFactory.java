
package ibfb.studyexplorer.core;


import ibfb.domain.core.Trial;
import java.beans.IntrospectionException;
import java.util.List;
import org.openide.nodes.ChildFactory;
import org.openide.nodes.Node;
import org.openide.util.Exceptions;

public class TrialChildFactory extends ChildFactory<Trial>{

    private List<Trial> resultList;

    public TrialChildFactory(List<Trial> resultList) {
        this.resultList = resultList;
    }

    @Override
    protected boolean createKeys(List<Trial> list) {
       for (Trial trial: resultList)    {
           list.add(trial);
       }
       this.refresh(true);
       return true;
    }

    @Override
    protected Node createNodeForKey(Trial trial) {
        try {
          
          return new TrialBeanNode(trial);
      

        } catch (IntrospectionException ie) {
            Exceptions.printStackTrace(ie);
            return null;
        }
        
      
    }

}
