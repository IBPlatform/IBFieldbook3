

package ibfb.studyexplorer.actions;

import ibfb.studyexplorer.jdialogs.JDNewSTD;
import java.awt.event.ActionEvent;
import java.util.ResourceBundle;
import org.openide.util.HelpCtx;
import org.openide.util.NbBundle;
import org.openide.util.actions.SystemAction;
import org.openide.windows.TopComponent;
import org.openide.windows.WindowManager;

/**
 *
 * @author TMSANCHEZ
 */
public class NewStudyAction extends SystemAction {
    private ResourceBundle bundle = NbBundle.getBundle(NewStudyAction.class);
      
    public NewStudyAction() {
        
        putValue(NAME, bundle.getString("NewStudyAction.newStudy"));
    }
    
    

    @Override
    public void actionPerformed(ActionEvent e) {
        JDNewSTD newStudy=new JDNewSTD(null, true);
       
        TopComponent background = WindowManager.getDefault().findTopComponent("BackgroundWindowTopComponent");
        
        if (background.isOpened()) {
            background.close();
        }
        
        newStudy.setLocationByPlatform(true);
        newStudy.setResizable(false);
        newStudy.OpenJDialog();
    }

    @Override
    public String getName() {
        return bundle.getString("NewStudyAction.newStudy");
    }

    @Override
    public HelpCtx getHelpCtx() {
        return null;
    }

}
