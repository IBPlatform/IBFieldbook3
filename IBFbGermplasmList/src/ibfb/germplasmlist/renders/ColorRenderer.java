
package ibfb.germplasmlist.renders;

import java.awt.Color;
import java.awt.Component;
import java.util.ArrayList;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.border.Border;
import javax.swing.table.TableCellRenderer;

      public class ColorRenderer extends JLabel implements TableCellRenderer{
        Border unselectedBorder = null;
        Border selectedBorder = null;
        boolean isBordered = true;
        private Color color;
          ArrayList<Integer> posiciones;       
        
        public ColorRenderer(ArrayList<Integer> pos) {
            this.posiciones=pos;
            setOpaque(true);
        }
         
    @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
   
            if(posiciones.size()>0){
            if (posiciones.contains(row)) {
             setForeground(Color.black);  
             setBackground(Color.green);  
            }else{
             setForeground(Color.black);  
             setBackground(Color.white); 
            }
            }else{
                
            setForeground(Color.black);  
            setBackground(Color.white); 
            }
            
            setText((value == null) ? "" : value.toString());
            return this;
        }
}
