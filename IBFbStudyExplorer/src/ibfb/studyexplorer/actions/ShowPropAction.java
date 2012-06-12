package ibfb.studyexplorer.actions;

import ibfb.domain.core.Study;
import java.awt.event.ActionEvent;
import org.openide.nodes.Node;;
import org.openide.windows.TopComponent;
import org.openide.windows.WindowManager;
import org.openide.util.HelpCtx;
import org.openide.util.actions.SystemAction;

public class ShowPropAction extends SystemAction {

    private org.openide.util.Lookup.Result<Study> result;
    
    Node miNodo = null;

    public ShowPropAction() {
        putValue(NAME, "Properties");
        setEnabled(Boolean.TRUE);
        result = org.openide.util.Utilities.actionsGlobalContext().lookupResult(Study.class);

    }

    @Override
    public void actionPerformed(ActionEvent e) {


        TopComponent prop = WindowManager.getDefault().findTopComponent("properties");
        if (!prop.isOpened()) {
            prop.open();
        }

        if (!prop.isVisible()) {
            prop.setVisible(true);
        }


        prop.requestVisible();

    }

    @Override
    public String getName() {
        return "Properties";
    }

    @Override
    public HelpCtx getHelpCtx() {
        return null;
    }
}
