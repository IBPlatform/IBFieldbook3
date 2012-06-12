package org.cimmyt.cril.ibwb.commongui;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JTextField;

/**
 *
 * @author TMSANCHEZ
 */
public class NumericKeyAdapter extends KeyAdapter {

    final static String badchars = "`~!@#$%^&*()_+=\\|\"':;?/><, ";
    private JTextField textField;

    public NumericKeyAdapter(JTextField textField) {
        this.textField = textField;
    }

    @Override
    public void keyTyped(KeyEvent e) {
        char c = e.getKeyChar();
        if ((Character.isLetter(c) && !e.isAltDown())
                || badchars.indexOf(c) > -1) {
            e.consume();
            return;
        }

        if (c == '-' && textField.getDocument().getLength() > 0) {
            e.consume();
        }

        if (c == '.' && textField.getText().contains(".")) {
            e.consume();
        }
    }
}
