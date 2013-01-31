package ibfb.lists.core;

import java.util.List;
import java.util.ResourceBundle;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import org.cimmyt.cril.ibwb.domain.Listnms;
import org.openide.DialogDisplayer;
import org.openide.NotifyDescriptor;
import org.openide.util.NbBundle;

/**
 * Utility class to search lists
 *
 * @author tmsanchez
 */
public class SelectListDialog {

    private ResourceBundle bundle = NbBundle.getBundle(SelectListDialog.class);
    /**
     * Selected Germplasm list in grid
     */
    private Listnms selectedListnms;
    /**
     * List of Germplasm found
     */
    private List<Listnms> germplasmList;

    /**
     * Shows a dialog to select Germplasm lists
     */
    public void showSearchDialog() {
        GermplasmSearch germplasmSearchPanel = new GermplasmSearch();

        selectedListnms = null;

        NotifyDescriptor notifyDescriptor = new NotifyDescriptor(germplasmSearchPanel, bundle.getString("SelectListDialog.title"), NotifyDescriptor.OK_CANCEL_OPTION, NotifyDescriptor.PLAIN_MESSAGE, null, NotifyDescriptor.OK_OPTION);

        if (DialogDisplayer.getDefault().notify(notifyDescriptor) == NotifyDescriptor.OK_OPTION) {
            this.selectedListnms = germplasmSearchPanel.getSelectedListnms();

        }
        this.germplasmList = germplasmSearchPanel.getGermplamList();
    }

    /**
     * Gets selected germplasm list
     *
     * @return Lisnms selected of
     * <code>null</code> if not selected
     */
    public Listnms getSeledtedListnms() {
        return this.selectedListnms;
    }

    /**
     * Checks if a List was selected
     *
     * @return
     * <code>true</code> if selected,
     * <code>false</code> if not
     */
    public boolean isListSelected() {
        return this.selectedListnms != null;
    }

    /**
     * Gets the germplasm list found
     */
    public List<Listnms> getGermplasmList() {
        return germplasmList;
    }

    public void populateComboListNames(JComboBox cboGermplasmList) {
        cboGermplasmList.setModel(new DefaultComboBoxModel(new String[]{NbBundle.getMessage(SelectListDialog.class, "SelectListDialog.selectOne")}));
        if (germplasmList != null) {
            for (Listnms list : germplasmList) {
                cboGermplasmList.addItem(list);
            }
        }
    }
}
