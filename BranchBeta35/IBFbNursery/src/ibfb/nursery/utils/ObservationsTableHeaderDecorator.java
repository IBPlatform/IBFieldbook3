
package ibfb.nursery.utils;

import ibfb.domain.core.Variate;
import ibfb.nursery.models.ObservationsTableModel;
import java.awt.Color;
import java.awt.Component;
import javax.swing.JTable;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableCellRenderer;


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

       
        ObservationsTableModel tableModel = (ObservationsTableModel) jtable.getModel();
        // if table model is a Variate, then chage color to blue 
        if (tableModel.getHeaders().get(colulmn) instanceof Variate) {
            setBackground(Color.blue.darker());
        } else {
            
            setBackground(Color.GREEN.darker());
        }
        setForeground(Color.WHITE);

        setBorder(UIManager.getBorder("TableHeader.cellBorder"));

        return this;
    }
}
