
package ibfb.studyeditor.roweditors;
import ibfb.studyeditor.designs.KSValues;
import java.awt.Component;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.EventObject;
import java.util.HashMap;

import javax.swing.DefaultCellEditor;
import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.event.CellEditorListener;
import javax.swing.table.TableCellEditor;


public class AlphaDesignsRowEditor implements TableCellEditor {

    protected HashMap<String,TableCellEditor> editors;
    protected TableCellEditor editor;
    protected TableCellEditor defaultEditor;
    private JTable table;

    public AlphaDesignsRowEditor(JTable table,int entries,ArrayList<KSValues> rep2,ArrayList<KSValues> rep3,ArrayList<KSValues> rep4) {
        this.table = table;
        editors = new HashMap<String, TableCellEditor>();
        defaultEditor = new DefaultCellEditor(new JTextField());
        createEditors(entries,rep2,rep3,rep4);
    }

    public void setEditorAt(String row, TableCellEditor editor) {
        editors.put(row, editor);
    }

    @Override
    public Component getTableCellEditorComponent(JTable table, Object value,
            boolean isSelected, int row, int column) {
        //editor = (TableCellEditor)editors.get(new Integer(row));
        //if (editor == null) {
        //  editor = defaultEditor;
        //}
        return editor.getTableCellEditorComponent(table, value, isSelected,
                row, column);
    }

    @Override
    public Object getCellEditorValue() {
        return editor.getCellEditorValue();
    }

    @Override
    public boolean stopCellEditing() {
        return editor.stopCellEditing();
    }

    @Override
    public void cancelCellEditing() {
        editor.cancelCellEditing();
    }

    @Override
    public boolean isCellEditable(EventObject anEvent) {
        selectEditor((MouseEvent) anEvent);
        return editor.isCellEditable(anEvent);
    }

    @Override
    public void addCellEditorListener(CellEditorListener l) {
        editor.addCellEditorListener(l);
    }

    @Override
    public void removeCellEditorListener(CellEditorListener l) {
        editor.removeCellEditorListener(l);
    }

    @Override
    public boolean shouldSelectCell(EventObject anEvent) {
        selectEditor((MouseEvent) anEvent);
        return editor.shouldSelectCell(anEvent);
    }

    protected void selectEditor(MouseEvent e) {
        int row;
        if (e == null) {
            row = table.getSelectionModel().getAnchorSelectionIndex();
        } else {
            row = table.rowAtPoint(e.getPoint());
        }

        String replication = String.valueOf(table.getValueAt(row,2));
        replication = replication.trim().toUpperCase();

        editor =  editors.get(replication);
        if (editor == null) {
            editor = defaultEditor;
        }
    }

    private void createEditors(int entries,ArrayList<KSValues> rep2,ArrayList<KSValues> rep3,ArrayList<KSValues> rep4) {

        JComboBox jcbRep2 = new JComboBox();              
        
        
        for (int i = 0; i < rep2.size(); i++) {
            jcbRep2.addItem(rep2.get(i).getBlockSize());
            
        }
        jcbRep2.setEditable(false);

        
        JComboBox jcbRep3 = new JComboBox();              
        
        
        for (int i = 0; i < rep3.size(); i++) {
            jcbRep3.addItem(rep3.get(i).getBlockSize());
            
        }
        jcbRep3.setEditable(false);
        
        
        
        JComboBox jcbRep4 = new JComboBox();              
        
        
        for (int i = 0; i < rep4.size(); i++) {
            jcbRep4.addItem(rep4.get(i).getBlockSize());
            
        }
        jcbRep4.setEditable(false);
        


        JTextField textEditor = new JTextField();
        textEditor.setText("");
        textEditor.setEditable(false);

         editors.put("2", new DefaultCellEditor(jcbRep2));
         editors.put("3", new DefaultCellEditor(jcbRep3));
         editors.put("4", new DefaultCellEditor(jcbRep4));
         
    }
}
