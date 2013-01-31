package ibfb.r.renders;

import java.awt.Component;
import java.util.HashMap;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.ListCellRenderer;

public class JcbImag extends JLabel implements ListCellRenderer {

    HashMap<Object, ImageIcon> iconos = null;

    public JcbImag() {


        iconos = new HashMap<Object, ImageIcon>();
        iconos.put("One Site", new ImageIcon(getClass().getResource("/org/cimmyt/images/home24.png")));
        iconos.put("Multi Location", new ImageIcon(getClass().getResource("/org/cimmyt/images/multilocation.png")));

    }

    public void agregarElemento(Object o, ImageIcon img) {
        iconos.put(o, img);
    }

    @Override
    public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
        if (iconos.get(value) != null) {
            setIcon(iconos.get(value));
            setText("" + value);
        } else {
            setIcon(null);
            setText("" + value);
        }
        return this;
    }
}
