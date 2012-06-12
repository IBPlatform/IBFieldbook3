package ibfb.traits.traits;

import java.awt.BorderLayout;
import java.awt.Component;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import org.cimmyt.cril.ibwb.domain.Scales;
import org.cimmyt.cril.ibwb.domain.TmsScaleCon;
import org.cimmyt.cril.ibwb.domain.TmsScaleDis;

/**
 *
 * @author TMSANCHEZ
 */
public class ScaleDefCellRenderer extends DefaultTableCellRenderer {

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected,
            boolean hasFocus, int row, int column) {
        JPanel panel = new JPanel(new BorderLayout());

        // cast as string the value
        String scaleType = "";
        if (value instanceof TmsScaleCon) {
            scaleType = Scales.SCALE_TYPE_CONTINOUS;
        } else {
            scaleType = Scales.SCALE_TYPE_DISCRETE;
        }

        // then check editor to use...
        if (scaleType.equals(Scales.SCALE_TYPE_CONTINOUS)) {
            panel.add(new ScaleConPanel((TmsScaleCon)value));
        } else if (scaleType.equals(Scales.SCALE_TYPE_DISCRETE)) {
            panel.add(new ScaleDisPanel((TmsScaleDis)value));
        }

        return panel;
    }
    
    
}
