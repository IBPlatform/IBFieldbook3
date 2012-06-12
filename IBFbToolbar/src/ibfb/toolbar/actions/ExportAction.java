
package ibfb.toolbar.actions;

import ibfb.nursery.core.NurseryEditorTopComponent;
import ibfb.studyeditor.core.StudyEditorTopComponent;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import org.openide.awt.ActionRegistration;
import org.openide.awt.ActionReference;
import org.openide.awt.ActionReferences;
import org.openide.awt.ActionID;
import org.openide.util.NbBundle.Messages;
import org.openide.windows.TopComponent;
import org.openide.windows.WindowManager;

@ActionID(category = "File",
id = "ibfb.toolbar.actions.ExportAction")
@ActionRegistration(iconBase = "ibfb/toolbar/images/export16.png",
displayName = "#CTL_ExportAction")
@ActionReferences({
    @ActionReference(path = "Toolbars/IBF", position = 3433)
})
@Messages("CTL_ExportAction=Export data")
public final class ExportAction implements ActionListener {

    @Override
    public void actionPerformed(ActionEvent e) {
        TopComponent active=   WindowManager.getDefault().getRegistry().getActivated();
    
    
    if(active.getName().startsWith("Nursery")){
        
        NurseryEditorTopComponent nursery=(NurseryEditorTopComponent)WindowManager.getDefault().getRegistry().getActivated();
        nursery.exportData();
        
    }else{
               
     StudyEditorTopComponent trial=   (StudyEditorTopComponent)WindowManager.getDefault().getRegistry().getActivated();
     //trial.exportData();
     
    }
        
    }
}
