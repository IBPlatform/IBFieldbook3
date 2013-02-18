package ibfb.germplasmlist.renders;

import java.awt.Color;
import java.awt.Component;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JTable;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableCellRenderer;

/**
 *
 * @author TMSANCHEZ
 */
public class CrossGridDecorator extends DefaultTableCellRenderer {

    private int[] femaleColumns;
    private int[] maleColumns;
    private List<Integer> femaleList;
    private List<Integer> maleList;
    private Color purpleColor = new Color(128, 0, 128);

    public CrossGridDecorator(int[] femaleColumns, int[] maleColumns) {
        this.femaleColumns = femaleColumns;
        this.maleColumns = maleColumns;
        fillColors();
    }

    @Override
    public Component getTableCellRendererComponent(JTable jtable, Object value,
            boolean isSelected, boolean hasFocus, int row, int column) {

        super.getTableCellRendererComponent(jtable, value, isSelected, hasFocus, row, column);

        if (femaleList.contains(column)) {
            setForeground(purpleColor);
        } else if (maleList.contains(column)) {
            setForeground(Color.BLUE);
        } else {
           setForeground(Color.BLACK); 
        }

        
        setBackground(jtable.getTableHeader().getBackground());

        setBorder(UIManager.getBorder("TableHeader.cellBorder"));

        return this;
    }
    
    private void fillColors() {
        femaleList = new ArrayList<Integer>();
        for (int index = 0; index < this.femaleColumns.length; index++) {
            femaleList.add(femaleColumns[index]);
        }
        maleList = new ArrayList<Integer>();
        for (int index = 0; index < this.maleColumns.length; index++) {
            maleList.add(maleColumns[index]);
        }
        
    }
}
