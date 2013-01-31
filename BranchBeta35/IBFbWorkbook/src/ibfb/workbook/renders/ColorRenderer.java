
package ibfb.workbook.renders;

import java.awt.Color;
import java.awt.Component;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.border.Border;
import javax.swing.table.TableCellRenderer;

public class ColorRenderer extends JLabel implements TableCellRenderer{
        Border unselectedBorder = null;
        Border selectedBorder = null;
        boolean isBordered = true;
        private Color color;
 
 
        public ColorRenderer(Color color) {
            this.color = color;
            setOpaque(true);
        }
 
    @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
            Color newColor = color;
            setBackground(newColor);
            if (isBordered) {
                if (isSelected) {
                    if (selectedBorder == null) {
                        selectedBorder = BorderFactory.createMatteBorder(0, 0, 0, 0,
                                table.getSelectionBackground());
                    }
                    setBorder(selectedBorder);
                } else {
                    if (unselectedBorder == null) {
                        unselectedBorder = BorderFactory.createMatteBorder(0, 0, 0, 0,
                                table.getBackground());
                    }
                    setBorder(unselectedBorder);
                }
            }
 
            setText((value == null) ? "" : value.toString());
 
            return this;
 
        }
}
