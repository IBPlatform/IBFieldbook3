package ibfb.traits.traits;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.util.EventObject;
import java.util.LinkedList;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.event.CellEditorListener;
import javax.swing.event.ChangeEvent;
import javax.swing.table.TableCellEditor;
import org.cimmyt.cril.ibwb.domain.Scales;
import org.cimmyt.cril.ibwb.domain.TmsScaleCon;
import org.cimmyt.cril.ibwb.domain.TmsScaleDis;

/**
 *
 * @author TMSANCHEZ
 */
public class ScaleRowEditor implements TableCellEditor {

    private LinkedList suscriptores = new LinkedList();
    
    private ScaleConPanel scaleConPanel;
    
    private ScaleDisPanel scaleDisPanel;
    
    private String scaleType;

    @Override
    public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
        JPanel panel = new JPanel(new BorderLayout());

        if (value instanceof TmsScaleDis) {
            scaleType = Scales.SCALE_TYPE_DISCRETE;
        } else {
            scaleType = Scales.SCALE_TYPE_CONTINOUS;
        }
        
        // then check editor to use...
        if (scaleType.equals(Scales.SCALE_TYPE_CONTINOUS)) {
            scaleConPanel = new ScaleConPanel((TmsScaleCon)value);
            panel.add(scaleConPanel);
        } else if (scaleType.equals(Scales.SCALE_TYPE_DISCRETE)) {
            scaleDisPanel = new ScaleDisPanel((TmsScaleDis)value);
            panel.add(scaleDisPanel);
        }

        panel.addFocusListener(new FocusListener() {

            @Override
            public void focusGained(FocusEvent e) {
                
            }

            @Override
            public void focusLost(FocusEvent e) {
                editado(false);
            }
        });
        return panel;
    }

    @Override
    public Object getCellEditorValue() {
        if (scaleType.equals(Scales.SCALE_TYPE_CONTINOUS)) {
             return scaleConPanel.getCellValue();
        } else if (scaleType.equals(Scales.SCALE_TYPE_DISCRETE)) {
            return scaleDisPanel.getCellValue();
        }
        
        return "";
    }

    @Override
    public boolean isCellEditable(EventObject anEvent) {
        return true;
    }

    @Override
    public boolean shouldSelectCell(EventObject anEvent) {

        return true;
    }

    @Override
    public boolean stopCellEditing() {
        return true;
    }

    @Override
    public void cancelCellEditing() {
        
    }


    @Override
    public void removeCellEditorListener(CellEditorListener l) {
        suscriptores.remove(l);
    }

    protected void editado(boolean cambiado) {
        ChangeEvent evento = new ChangeEvent(this);
        int i;
        for (i = 0; i < suscriptores.size(); i++) {
            CellEditorListener aux = (CellEditorListener) suscriptores.get(i);
            if (cambiado) {
                aux.editingStopped(evento);
            } else {
                aux.editingCanceled(evento);
            }
        }
    }

    @Override
    public void addCellEditorListener(CellEditorListener l) {
       suscriptores.add (l);
    }
}
