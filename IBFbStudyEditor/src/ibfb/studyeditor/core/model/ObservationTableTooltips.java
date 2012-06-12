/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ibfb.studyeditor.core.model;

import ibfb.domain.core.Variate;
import javax.swing.JTable;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;

/**
 *
 * @author TMSANCHEZ
 */
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
