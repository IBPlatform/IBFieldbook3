
package ibfb.studyexplorer.actions;

import ibfb.domain.core.Experiment;
import ibfb.domain.core.SelectedExperiment;
import ibfb.domain.core.SelectedStudy;
import ibfb.studyexplorer.jdialogs.JDNewOptions;
import java.awt.event.ActionEvent;
import java.util.ResourceBundle;

import org.openide.util.HelpCtx;
import org.openide.util.NbBundle;
import org.openide.util.actions.SystemAction;

/**
 *
 * @author TMSANCHEZ
 */
public class ShowOptionsAction extends SystemAction {
    private ResourceBundle bundle = NbBundle.getBundle(ShowOptionsAction.class);

    public ShowOptionsAction() {
        putValue(NAME, bundle.getString("ShowOptionsAction.new"));
        setEnabled(Boolean.TRUE);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
       JDNewOptions opciones = new JDNewOptions(null, true);
       opciones.setLocationRelativeTo(null);
       
 
       if(SelectedExperiment.selected.getType().equals(Experiment.TRIAL)){
          opciones.setOption(0); 
       }
       
       else if(SelectedExperiment.selected.getType().equals(Experiment.NURSERY)){
         opciones.setOption(1);   
       }
       
       opciones.setVisible(true);
    }

    @Override
    public String getName() {
        return bundle.getString("ShowOptionsAction.newName");
    }

    @Override
    public HelpCtx getHelpCtx() {
        return null;
    }
}
