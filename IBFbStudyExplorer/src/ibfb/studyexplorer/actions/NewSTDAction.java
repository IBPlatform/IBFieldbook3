
package ibfb.studyexplorer.actions;

import ibfb.studyexplorer.jdialogs.JDNewSTD;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import org.openide.awt.ActionID;
import org.openide.awt.ActionReference;
import org.openide.awt.ActionReferences;
import org.openide.awt.ActionRegistration;
import org.openide.windows.TopComponent;
import org.openide.windows.WindowManager;

@ActionID(category = "File",
id = "ibfb.studyexplorer.actions.NewSTDAction")
@ActionRegistration(iconBase = "ibfb/studyexplorer/images/newStudy16.png",
displayName = "#CTL_NewSTDAction")
@ActionReferences({
    @ActionReference(path = "Menu/File", position = 1200),
    @ActionReference(path = "Toolbars/File", position = -400)
})

public final class NewSTDAction implements ActionListener {
public static JDNewSTD newStudy=new JDNewSTD(null, true);
   
@Override
    public void actionPerformed(ActionEvent e) {
    
         TopComponent background = WindowManager.getDefault().findTopComponent("BackgroundWindowTopComponent");
        
        if (background.isOpened()) {
            background.close();
        }
        
        newStudy.setLocationRelativeTo(null);
        
         newStudy.OpenJDialog();
        
    }
}
