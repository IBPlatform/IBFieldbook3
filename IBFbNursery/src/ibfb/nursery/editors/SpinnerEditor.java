package ibfb.nursery.editors;

import java.awt.Component;
import java.awt.event.MouseEvent;
import java.util.EventObject;
import javax.swing.AbstractCellEditor;
import javax.swing.JComponent;
import javax.swing.JFormattedTextField;
import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;
import javax.swing.table.TableCellEditor;

public final class SpinnerEditor extends AbstractCellEditor implements TableCellEditor {

    final JSpinner spinner = new JSpinner();
    final SpinnerNumberModel modeloinstances = new SpinnerNumberModel(1, 1, 1000, 1);

    public SpinnerEditor() {
        spinner.setModel(modeloinstances);

        JFormattedTextField ftf = getTextField(spinner);
        if (ftf != null) {
            ftf.setHorizontalAlignment((int) JTextField.CENTER_ALIGNMENT);
        }
    }

    @Override
    public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected,
            int row, int column) {
     
    spinner.setValue(new Integer(value.toString()).intValue());
    spinner.setCursor(null);
    return spinner;
    }

    @Override
    public boolean isCellEditable(EventObject evt) {
        if (evt instanceof MouseEvent) {
            return ((MouseEvent) evt).getClickCount() >= 2;
        }
        return true;
    }

    @Override
    public Object getCellEditorValue() {
        return spinner.getValue();
    }

    public JFormattedTextField getTextField(JSpinner spinner) {
        JComponent editor = spinner.getEditor();
        if (editor instanceof JSpinner.DefaultEditor) {
            return ((JSpinner.DefaultEditor) editor).getTextField();
        } else {
            System.err.println("Error editor: "
                    + spinner.getEditor().getClass()
                    + " no es descendiente de DefaultEditor");
            return null;
        }
    }
}
