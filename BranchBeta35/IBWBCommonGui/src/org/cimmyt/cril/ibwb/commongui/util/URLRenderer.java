/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.cimmyt.cril.ibwb.commongui.util;

import java.awt.Component;
import java.awt.Desktop;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.net.URL;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

/**
 *
 * @author TMSANCHEZ
 */
public class URLRenderer
        extends DefaultTableCellRenderer
        implements MouseListener, MouseMotionListener {

    private int row = -1;
    private int col = -1;

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value,
            boolean isSelected, boolean hasFocus,
            int row, int column) {
        super.getTableCellRendererComponent(table, value, isSelected, false, row, column);
        if (!table.isEditing() && this.row == row && this.col == column) {
            setText("< html > < u > < font color='blue' >" + value.toString());
        } else if (hasFocus) {
            setText("< html > < font color='blue' >" + value.toString());
        } else {
            setText(value.toString());
        }
        return this;
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        JTable table = (JTable) e.getSource();
        Point pt = e.getPoint();
        row = table.rowAtPoint(pt);
        col = table.columnAtPoint(pt);
        if (row < 0 || col < 0) {
            row = -1;
            col = -1;
        }
        table.repaint();
    }

    @Override
    public void mouseExited(MouseEvent e) {
        JTable table = (JTable) e.getSource();
        row = -1;
        col = -1;
        table.repaint();
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        JTable table = (JTable) e.getSource();
        Point pt = e.getPoint();
        int crow = table.rowAtPoint(pt);
        int ccol = table.columnAtPoint(pt);
        if(table.convertColumnIndexToModel(ccol) == 2)
        if (table.getColumnClass(ccol).equals(URL.class)) {
            URL url = (URL)table.getValueAt(crow, ccol);

            System.out.println(table.getValueAt(crow, ccol));
            try{
              Desktop.getDesktop().browse(url.toURI());
            }catch(Exception ex) {
              ex.printStackTrace();
            }
        }
    }

    @Override
    public void mouseDragged(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mousePressed(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }
}
