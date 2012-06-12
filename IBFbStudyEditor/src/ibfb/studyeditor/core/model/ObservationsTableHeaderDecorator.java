/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ibfb.studyeditor.core.model;

import ibfb.domain.core.Variate;
import java.awt.Color;
import java.awt.Component;
import javax.swing.JTable;
import javax.swing.UIManager;
import javax.swing.border.Border;
import javax.swing.table.DefaultTableCellRenderer;

/**
 * Utility class to show in different color header for observations table 
 * @author TMSANCHEZ
 */
public class ObservationsTableHeaderDecorator extends DefaultTableCellRenderer {

    public ObservationsTableHeaderDecorator() {
        setHorizontalAlignment(CENTER);
        setHorizontalTextPosition(LEFT);
        setOpaque(true);
    }

    @Override
    public Component getTableCellRendererComponent(JTable jtable, Object value,
            boolean isSelected, boolean hasFocus, int row, int colulmn) {
        
        super.getTableCellRendererComponent(jtable, value, isSelected, hasFocus, row, colulmn);

        // gets the table model
        ObservationsTableModel tableModel = (ObservationsTableModel) jtable.getModel();
        // if table model is a Variate, then chage color to blue 
        if (tableModel.getHeaders().get(colulmn) instanceof Variate) {
            setBackground(Color.blue.darker());
        } else {
            // is a factor, then use green color
            setBackground(Color.GREEN.darker());
        }
        setForeground(Color.WHITE);

        setBorder(UIManager.getBorder("TableHeader.cellBorder"));

        return this;
    }
}
