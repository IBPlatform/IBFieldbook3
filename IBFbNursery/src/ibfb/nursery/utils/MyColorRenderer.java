
package ibfb.nursery.utils;

import java.awt.Color;
import java.awt.Component;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

public class MyColorRenderer extends DefaultTableCellRenderer {
Color background;
Color foreground;

public MyColorRenderer (Color background,Color foreground) {
super();
this.background = background;
this.foreground = foreground;
}
    @Override
public Component getTableCellRendererComponent(JTable table, Object value,boolean isSelected, boolean hasFocus, int row, int column) {
Component comp = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
comp.setBackground(background);
comp.setForeground(foreground);
this.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
comp.setFont(new java.awt.Font("Tahoma", 1, 11));
//this.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(14, 14, 162)));
this.setBorder(javax.swing.BorderFactory.createLineBorder(Color.BLACK));
return comp;
}
}
