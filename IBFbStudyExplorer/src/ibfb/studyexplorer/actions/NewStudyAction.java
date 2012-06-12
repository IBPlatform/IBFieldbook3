

package ibfb.studyexplorer.actions;

import ibfb.studyexplorer.jdialogs.JDNewSTD;
import java.awt.event.ActionEvent;
import org.openide.util.HelpCtx;
import org.openide.util.actions.SystemAction;
import org.openide.windows.TopComponent;
import org.openide.windows.WindowManager;

/**
 *
 * @author TMSANCHEZ
 */
public class NewStudyAction extends SystemAction {
      
    public NewStudyAction() {
        putValue(NAME, "New Study...");
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
        return "New Study";
    }

    @Override
    public HelpCtx getHelpCtx() {
        return null;
    }

}
