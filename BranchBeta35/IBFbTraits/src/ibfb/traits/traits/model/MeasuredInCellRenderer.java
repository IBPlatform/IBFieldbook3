package ibfb.traits.traits.model;

import java.awt.Component;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import org.cimmyt.cril.ibwb.domain.Measuredin;
import org.openide.util.ImageUtilities;

/**
 *
 * @author TMSANCHEZ
 */
public class MeasuredInCellRenderer extends DefaultTableCellRenderer {

    private JLabel lblDefault = new JLabel();
    private ImageIcon selected = ImageUtilities.loadImageIcon("ibfb/traits/traits/standardyes.png", false);
    private ImageIcon unselected = ImageUtilities.loadImageIcon("ibfb/traits/traits/standardno.png", false);

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected,
            boolean hasFocus, int row, int column) {

        lblDefault.setText("");

        String strValue = (String) value;
        if (strValue.equals(Measuredin.STANTARD_SCALE_YES)) {
            lblDefault.setIcon(selected);
        } else {
            lblDefault.setIcon(unselected);
        }

        return lblDefault;
    }
}
