/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ibfb.studyeditor.core.model;

import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JTable;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;

/**
 * Utility class to show tool tips in column headers
 * @author TMSANCHEZ
 */
public class TableHeaderTooltips extends MouseMotionAdapter {

    TableColumn currentColumn;
    Map tips = new HashMap();
    // If tooltip is null, removes any tooltip text.

    /**
     * Assign tool top to a column header
     * @param col Selected column where tool top appear
     * @param tooltip Tool tip text to show
     */
    public void setToolTip(TableColumn col, String tooltip) {
        if (tooltip == null) {
            tips.remove(col);
        } else {
            tips.put(col, tooltip);
        }
    }

    @Override
    public void mouseMoved(MouseEvent evt) {
        TableColumn col = null;
        JTableHeader header = (JTableHeader) evt.getSource();
        JTable table = header.getTable();
        TableColumnModel colModel = table.getColumnModel();
        int vColIndex = colModel.getColumnIndexAtX(evt.getX());

        // Return if not clicked on any column header
        if (vColIndex >= 0) {
            col = colModel.getColumn(vColIndex);
        }

        if (col != currentColumn) {
            header.setToolTipText((String) tips.get(col));
            currentColumn = col;
        }
    }
}

