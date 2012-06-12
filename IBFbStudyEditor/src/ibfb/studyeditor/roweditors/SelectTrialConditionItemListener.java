/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ibfb.studyeditor.roweditors;

import ibfb.domain.core.Condition;
import ibfb.studyeditor.core.model.TrialConditionsTableModel;
import ibfb.studyeditor.util.SelectLocationPanel;
import ibfb.studyeditor.util.SelectPersonPanel;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JTable;
import org.openide.DialogDisplayer;
import org.openide.NotifyDescriptor;

/**
 *
 * @author TMSANCHEZ
 */
public class SelectTrialConditionItemListener implements ActionListener {

    private Integer locationId;
    private String locationName;
    private JTable table;
    private Object value;
    private int row;
    private int column;
    private Component component;

    public SelectTrialConditionItemListener(JTable table) {
        this.table = table;
    }

    public void setInfo(Object value, int row, int column, Component component) {
        this.value = value;
        this.row = row;
        this.column = column;
        this.component = component;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        // get table model
        TrialConditionsTableModel trialConditionsTableModel = (TrialConditionsTableModel) table.getModel();
        // then get current row
        Condition condition = trialConditionsTableModel.getTrialConditions().get(row);

        // launch dialog only for person or location
        if (condition.getProperty().equals("PERSON") || condition.getProperty().equals("LOCATION")) {

            if (condition.getProperty().equals("PERSON")) {
                launchPersonDialog(condition);
            } else {
                launchLocationDialog(condition);
            }
        }

    }

    private void launchLocationDialog(Condition condition) {
        SelectLocationPanel selectLocationPanel = new SelectLocationPanel();

        NotifyDescriptor notifyDescriptor = new NotifyDescriptor(selectLocationPanel, "Choose location", NotifyDescriptor.OK_CANCEL_OPTION, NotifyDescriptor.PLAIN_MESSAGE, null, NotifyDescriptor.OK_OPTION);

        if (DialogDisplayer.getDefault().notify(notifyDescriptor) == NotifyDescriptor.OK_OPTION) {
            if (condition.getScale().equals("DBID")) {
                value = selectLocationPanel.getLocationId();
                if (component instanceof JComboBox) {
                    JComboBox comboId = (JComboBox)component;
                    DefaultComboBoxModel dcbm = (DefaultComboBoxModel)comboId.getModel();
                    dcbm.setSelectedItem(value);
                }
                
                table.setValueAt(value, row, column);
            } else if (condition.getScale().equals("DBCV")) {
                value = selectLocationPanel.getLocationName();
                if (component instanceof JComboBox) {
                    JComboBox comboId = (JComboBox)component;
                    DefaultComboBoxModel dcbm = (DefaultComboBoxModel)comboId.getModel();
                    dcbm.setSelectedItem(value);
                }
                table.setValueAt(value, row, column);
            }
        }
    }

    private void launchPersonDialog(Condition condition) {
        SelectPersonPanel personPanel = new SelectPersonPanel();

        NotifyDescriptor notifyDescriptor = new NotifyDescriptor(personPanel, "Choose Person", NotifyDescriptor.OK_CANCEL_OPTION, NotifyDescriptor.PLAIN_MESSAGE, null, NotifyDescriptor.OK_OPTION);

        if (DialogDisplayer.getDefault().notify(notifyDescriptor) == NotifyDescriptor.OK_OPTION) {
            if (condition.getScale().equals("DBID")) {
                value = personPanel.getPersonId();
                if (component instanceof JComboBox) {
                    JComboBox comboId = (JComboBox)component;
                    DefaultComboBoxModel dcbm = (DefaultComboBoxModel)comboId.getModel();
                    dcbm.setSelectedItem(value);
                }
                table.setValueAt(value, row, column);
            } else if (condition.getScale().equals("DBCV")) {
                value = personPanel.getPersonName();
                if (component instanceof JComboBox) {
                    JComboBox comboId = (JComboBox)component;
                    DefaultComboBoxModel dcbm = (DefaultComboBoxModel)comboId.getModel();
                    dcbm.setSelectedItem(value);
                }
                table.setValueAt(value, row, column);
                
            }

        }
    }
}
