
package ibfb.toolbar.actions;

import ibfb.studyeditor.core.StudyEditorTopComponent;
import org.openide.awt.ActionRegistration;
import org.openide.awt.ActionReference;
import org.openide.awt.ActionReferences;
import org.openide.awt.ActionID;
import org.openide.util.HelpCtx;
import org.openide.util.NbBundle.Messages;
import org.openide.util.actions.CallableSystemAction;
import org.openide.windows.TopComponent;
import org.openide.windows.WindowManager;

@ActionID(category = "File",
id = "ibfb.toolbar.actions.PrintLabelsAction")
@ActionRegistration(iconBase = "ibfb/toolbar/images/print16.png",
displayName = "#CTL_PrintLabelsAction")
@ActionReferences({
    @ActionReference(path = "Toolbars/IBFLabels", position = 3433)
})
@Messages("CTL_PrintLabelsAction=Print labels")

public final class PrintLabelsAction extends CallableSystemAction {



    
    
    @Override
       public boolean isEnabled(){   
       return true;
    }
    
       
       
       
    @Override
       public void setEnabled(boolean enable){
             super.setEnabled(enable);      
     }
    
    
@Override
    protected String iconResource() {
        return "ibfb/toolbar/images/print16.png";
    }

    @Override
    public void performAction() {          
     StudyEditorTopComponent trial=   (StudyEditorTopComponent)WindowManager.getDefault().getRegistry().getActivated();
     trial.printLabels();

    }

    @Override
    public String getName() {
        return("Print labels");
    }

    @Override
    public HelpCtx getHelpCtx() {
       return null;
    }
    
    
    
    @Override
    public boolean asynchronous() {
        return false;
    }
}
