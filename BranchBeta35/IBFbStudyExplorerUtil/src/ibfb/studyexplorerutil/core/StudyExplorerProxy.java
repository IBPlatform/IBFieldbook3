/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ibfb.studyexplorerutil.core;


import org.openide.nodes.AbstractNode;
import org.openide.nodes.Node;

/**
 * Helper Class to get access to  StudyExplorerModule and avoid a Cyclic dependency
 * between StudyExplorer and StudyEditorTopComponent
 * @author TMSANCHEZ
 */
public class StudyExplorerProxy {
    private static AbstractNode abstractNode;
    private static Node node;
    
    
    public static void refreshStudyExplorer() {
       
    }
}
