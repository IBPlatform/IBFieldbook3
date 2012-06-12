/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ibfb.studyeditor.core.model;

import java.awt.Color;
import java.awt.Component;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

/**
 *
 * @author TMSANCHEZ
 */
public class ValueCellRenderer extends DefaultTableCellRenderer {
    
    /**
     * Column used to specify column index for value
     */
    private int columValueIndex;
    
    public ValueCellRenderer(int columValueIndex) {
        this.columValueIndex = columValueIndex;
    }

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        Component comp = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
        if (column == columValueIndex) {
          comp.setBackground(Color.YELLOW);  
        } 
        return comp;
    }
    
}
