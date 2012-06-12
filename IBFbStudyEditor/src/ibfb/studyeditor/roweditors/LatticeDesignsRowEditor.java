
package ibfb.studyeditor.roweditors;
import java.awt.Component;
import java.awt.event.MouseEvent;
import java.util.EventObject;
import java.util.HashMap;

import javax.swing.DefaultCellEditor;
import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.event.CellEditorListener;
import javax.swing.table.TableCellEditor;


public class LatticeDesignsRowEditor implements TableCellEditor {

    protected HashMap<String,TableCellEditor> editors;
    protected TableCellEditor editor;
    protected TableCellEditor defaultEditor;
    private JTable table;

    public LatticeDesignsRowEditor(JTable table,int entries) {
        this.table = table;
        editors = new HashMap<String, TableCellEditor>();
        defaultEditor = new DefaultCellEditor(new JTextField());
        createEditors(entries);
    }

    public void setEditorAt(String row, TableCellEditor editor) {
        editors.put(row, editor);
    }

    @Override
    public Component getTableCellEditorComponent(JTable table, Object value,
            boolean isSelected, int row, int column) {

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

    private void createEditors(int entries) {

        JComboBox rep2 = new JComboBox();              
        
        for (int i = entries-1; i > 1; i--) {
          if( (entries % i)==0 ){

              int k=entries/i;
              int s=entries/(entries/i);
              if(k <= s)

              rep2.addItem(String.valueOf(entries / i));
          }
        }

        rep2.setEditable(false);

        JComboBox rep3 = new JComboBox();
        for (int i = entries-1; i > 1; i--) {
            int mod=entries%i;
          if( mod==0 ){
              int k=entries/i;
              int s=entries/(entries/i);
              
              if(s%2==0){//par
                  if(k <= s)
                  rep3.addItem(entries / i);

              }else{//impar

                  if(k <= s-1)
                  rep3.addItem(entries / i);
              }
          }
        }




        rep3.setEditable(false);
      

        JComboBox rep4 = new JComboBox();
        for (int i = entries-1; i > 1; i--) {

            
            
            if( (entries % i)==0 ){
              int k=entries/i;
              int s=entries/(entries/i);


              if(k <= s)
                  if(s%2!=0 && s%3!=0)
              rep4.addItem(entries / i);
          }

        }
        rep4.setEditable(false);


        JTextField textEditor = new JTextField();
        textEditor.setText("");
        textEditor.setEditable(false);

         editors.put("2", new DefaultCellEditor(rep2));
         editors.put("3", new DefaultCellEditor(rep3));
         editors.put("4", new DefaultCellEditor(rep4));
         
    }
}
