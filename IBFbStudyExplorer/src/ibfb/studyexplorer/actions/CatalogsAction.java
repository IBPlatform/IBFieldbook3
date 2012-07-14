package ibfb.studyexplorer.actions;

import ibfb.traits.traits.TraitsExplorerTopComponent;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import org.openide.DialogDisplayer;
import org.openide.NotifyDescriptor;
import org.openide.awt.ActionID;
import org.openide.awt.ActionReference;
import org.openide.awt.ActionReferences;
import org.openide.awt.ActionRegistration;
import org.openide.windows.TopComponent;
import org.openide.windows.WindowManager;

@ActionID(category = "Build",
id = "ibfb.studyexplorer.actions.CatalogsAction")
@ActionRegistration(iconBase = "ibfb/studyexplorer/images/db16.png",
displayName = "#CTL_CatalogsAction")
@ActionReferences({
    @ActionReference(path = "Toolbars/File", position = -259)
})

public final class CatalogsAction implements ActionListener {

    @Override
    public void actionPerformed(ActionEvent e) {

        JPDBOptions myPanel = new JPDBOptions();


        NotifyDescriptor nd = new NotifyDescriptor(
                myPanel,
                "IBFIELDBOOK - Nursery",
                NotifyDescriptor.OK_CANCEL_OPTION,
                NotifyDescriptor.QUESTION_MESSAGE,
                null,
                NotifyDescriptor.OK_OPTION);


        if (DialogDisplayer.getDefault().notify(nd) == NotifyDescriptor.OK_OPTION) {

            if (myPanel.wantTraits()) {
                TopComponent traitsExplorer = WindowManager.getDefault().findTopComponent("TraitsExplorerTopComponent");
                if (traitsExplorer == null) {
                    traitsExplorer = new TraitsExplorerTopComponent();
                }
                traitsExplorer.open();
                traitsExplorer.requestActive();
            }

            if (myPanel.wantGsm()) {
                TopComponent ListExplorer = WindowManager.getDefault().findTopComponent("ListNamesExplorerTopComponent");
                ListExplorer.open();
                ListExplorer.requestActive();
            }

            if (myPanel.wantMethods()) {
                TopComponent explorer = WindowManager.getDefault().findTopComponent("MethodBrowserTopComponent");
                explorer.open();
                explorer.requestActive();
            }

            if (myPanel.wantScales()) {
                TopComponent traitsExplorer = WindowManager.getDefault().findTopComponent("ScalesBrowserTopComponent");
                traitsExplorer.open();
                traitsExplorer.requestActive();
            }





        }

    }
}
