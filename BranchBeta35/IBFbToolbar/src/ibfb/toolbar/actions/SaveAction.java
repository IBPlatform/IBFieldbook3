package ibfb.toolbar.actions;


import ibfb.nursery.core.NurseryEditorTopComponent;
import ibfb.studyeditor.core.StudyEditorTopComponent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import org.openide.awt.ActionRegistration;
import org.openide.awt.ActionReference;
import org.openide.awt.ActionReferences;
import org.openide.awt.ActionID;
import org.openide.util.NbBundle.Messages;
import org.openide.windows.TopComponent;
import org.openide.windows.WindowManager;

@ActionID(category = "File",
id = "ibfb.toolbar.actions.SaveAction")
@ActionRegistration(iconBase = "ibfb/toolbar/images/save16.png",
displayName = "#CTL_SaveAction")
@ActionReferences({
    @ActionReference(path = "Toolbars/IBF", position = 3333)
})
@Messages("CTL_SaveAction=Save")
public final class SaveAction implements ActionListener {

    
    @Override
    public void actionPerformed(ActionEvent ev) {
    TopComponent active=   WindowManager.getDefault().getRegistry().getActivated();
    
    
    if(active.getName().startsWith("Nursery")){
        
        NurseryEditorTopComponent nursery=(NurseryEditorTopComponent)WindowManager.getDefault().getRegistry().getActivated();
       // nursery.save();
        
    }else{
               
     StudyEditorTopComponent trial=   (StudyEditorTopComponent)WindowManager.getDefault().getRegistry().getActivated();
     //trial.save();
     
    }
        
    }
}
