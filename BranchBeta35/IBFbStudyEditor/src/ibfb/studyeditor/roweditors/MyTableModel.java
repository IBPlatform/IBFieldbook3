
package ibfb.studyeditor.roweditors;


import java.util.Vector;
import javax.swing.table.DefaultTableModel;


public class MyTableModel extends DefaultTableModel {
    
    public void removeColumn(int columnIndex) { 
for (int r = 0; r < getRowCount(); r++) { 
Vector row = (Vector)dataVector.elementAt(r); 
row.removeElementAt(columnIndex); 
}
columnIdentifiers.removeElementAt(columnIndex); 
fireTableStructureChanged();
}
    
}
