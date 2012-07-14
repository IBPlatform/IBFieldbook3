package ibfb.germplasmlist.actions;

import ibfb.germplasmlist.core.JFNurseryManager;
import ibfb.germplasmlist.core.germplasmListTopComponent;
import ibfb.germplasmlist.core.nurseryManagerTopComponent;
import ibfb.germplasmlist.core.nurserySelectionTopComponent;
import ibfb.germplasmlist.selectionWizard.SelectionWizardWizardIterator;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.MessageFormat;
import org.openide.DialogDisplayer;
import org.openide.WizardDescriptor;
import org.openide.awt.ActionID;
import org.openide.awt.ActionReference;
import org.openide.awt.ActionReferences;
import org.openide.awt.ActionRegistration;
import org.openide.windows.TopComponent;
import org.openide.windows.WindowManager;

@ActionID(category = "Build",
id = "ibfb.germplasmlist.actions.NurseryManagerAction")
@ActionRegistration(iconBase = "ibfb/germplasmlist/images/managerIcon16.png",
displayName = "#CTL_NurseryManagerAction")
@ActionReferences({
    @ActionReference(path = "Toolbars/File", position = -273)
})


public final class NurseryManagerAction implements ActionListener {

    
    
    
    
    
    @Override
    public void actionPerformed(ActionEvent e) {

        closeBackground();
      //  showSelectionWizard();
        showNurseryManagerMenu();
        
       // showNurseryManagerTopComponent();
        
    }

    private void closeBackground() {
        TopComponent background = WindowManager.getDefault().findTopComponent("BackgroundWindowTopComponent");
        if (background.isOpened()) {
            background.close();
        }
    }
    
    
    
    private void showManagerTpComponent(){
        TopComponent newListTopComponent = WindowManager.getDefault().findTopComponent("nurseryManagerTopComponent");
        if (newListTopComponent == null) {
            newListTopComponent = new germplasmListTopComponent();
        }
        
        newListTopComponent.open();
        newListTopComponent.requestActive();
        
    }
    
    private void showSelectionWizard(){
        
        
        nurserySelectionTopComponent selectionTopComponent = new nurserySelectionTopComponent();
                   
              
        WizardDescriptor wiz = new WizardDescriptor(new SelectionWizardWizardIterator());
        
        
        wiz.setTitleFormat(new MessageFormat("{0} ({1})"));
        wiz.setTitle("Nursery Manager - Selection");
        
        SelectionWizardWizardIterator.selectionTopComponent=selectionTopComponent;
        
        if (DialogDisplayer.getDefault().notify(wiz) == WizardDescriptor.FINISH_OPTION) {
            
            selectionTopComponent.createSelectionList();    

            selectionTopComponent.open();
            selectionTopComponent.requestActive(); 
                     
                     
                 }
    }
    
    
    private void showNurseryManagerMenu(){
        JFNurseryManager nurseryManager=new JFNurseryManager(null,true);
        nurseryManager.setLocationRelativeTo(null);
        nurseryManager.setVisible(true);
        
    }

    private void showNurseryManagerTopComponent() {
       nurseryManagerTopComponent manager=new nurseryManagerTopComponent();
       manager.open();
       manager.requestActive();
    }
}
