package ibfb.nursery.editors;

import ibfb.domain.core.Condition;

import ibfb.nursery.models.NurseryConditionsTableModel;
import ibfb.nursery.persons.SelectLocationPanel;
import ibfb.nursery.persons.SelectPersonPanel;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JTable;
import org.openide.DialogDisplayer;
import org.openide.NotifyDescriptor;

public class SelectNurseryConditionItemListener implements ActionListener {

    private Integer locationId;
    private String locationName;
    private JTable table;
    private Object value;
    private int row;
    private int column;
    private Component component;

    public SelectNurseryConditionItemListener(JTable table) {
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
        NurseryConditionsTableModel trialConditionsTableModel = (NurseryConditionsTableModel) table.getModel();
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
                    JComboBox comboId = (JComboBox) component;
                    DefaultComboBoxModel dcbm = (DefaultComboBoxModel) comboId.getModel();
                    dcbm.setSelectedItem(value);
                }

                table.setValueAt(value, row, column);
            } else if (condition.getScale().equals("DBCV")) {
                value = selectLocationPanel.getLocationName();
                if (component instanceof JComboBox) {
                    JComboBox comboId = (JComboBox) component;
                    DefaultComboBoxModel dcbm = (DefaultComboBoxModel) comboId.getModel();
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
                    JComboBox comboId = (JComboBox) component;
                    DefaultComboBoxModel dcbm = (DefaultComboBoxModel) comboId.getModel();
                    dcbm.setSelectedItem(value);
                }
                table.setValueAt(value, row, column);
            } else if (condition.getScale().equals("DBCV")) {
                value = personPanel.getPersonName();
                if (component instanceof JComboBox) {
                    JComboBox comboId = (JComboBox) component;
                    DefaultComboBoxModel dcbm = (DefaultComboBoxModel) comboId.getModel();
                    dcbm.setSelectedItem(value);
                }
                table.setValueAt(value, row, column);

            }

        }
    }
}
