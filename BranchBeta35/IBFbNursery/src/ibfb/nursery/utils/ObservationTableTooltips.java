
package ibfb.nursery.utils;


import ibfb.nursery.models.ObservationsTableModel;
import javax.swing.JTable;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;


public class ObservationTableTooltips {

    public  static void assignTooltips(JTable observationsTable) {
        JTableHeader header = observationsTable.getTableHeader();
        ObservationsTableModel tableModel =(ObservationsTableModel)observationsTable.getModel();
        
        TableHeaderTooltips tooltips = new TableHeaderTooltips();
        
        for (int col=0; col < observationsTable.getColumnCount(); col++) {
            TableColumn column = observationsTable.getColumnModel().getColumn(col);
            tooltips.setToolTip(column, tableModel.getDescriptionForColumn(col) );
        }      
        header.addMouseMotionListener(tooltips);
        observationsTable.getTableHeader().setDefaultRenderer(new ObservationsTableHeaderDecorator());
    }

    
}
