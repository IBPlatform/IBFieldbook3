package ibfb.studyeditor.roweditors;

import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import javax.swing.ComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.JTextComponent;
import javax.swing.text.PlainDocument;


public final class AutoCompleteJCB extends PlainDocument implements FocusListener, KeyListener, PropertyChangeListener {
    private JComboBox comboBox;
    private ComboBoxModel model;
    private JTextComponent editor;
    private boolean hidePopupOnFocusLoss;


    public AutoCompleteJCB() {
        hidePopupOnFocusLoss = System.getProperty("java.version").startsWith("1.6");
    }

    public AutoCompleteJCB(JComboBox jcb) {
        this();
        registraComboBox(jcb);       
    }

    public void registraComboBox(JComboBox jcb) {
        desregistraComboBox();
        this.comboBox = jcb;
        comboBox.setEditable(true);
        model = comboBox.getModel();
        editor = (JTextComponent) comboBox.getEditor().getEditorComponent();
        editor.setDocument(this);
        editor.addFocusListener(this);
        editor.addKeyListener(this);
        comboBox.addPropertyChangeListener(this);
        Object selected = comboBox.getSelectedItem();
        if (selected != null) {
            editor.setText(selected.toString());
        } else {
            editor.setText("");
        }
    }

    public void desregistraComboBox() {
        if (comboBox != null) {
            comboBox.getEditor().getEditorComponent().removeFocusListener(this);
            comboBox.getEditor().getEditorComponent().removeKeyListener(this);
            comboBox.removePropertyChangeListener(this);
            comboBox.setSelectedItem(null);
            comboBox = null;
        }
    }

    @Override
    public void insertString(int offs, String str, AttributeSet a) throws BadLocationException {
     
        String currentText = getText(0, getLength());
        String beforeOffset = currentText.substring(0, offs);
        String afterOffset = currentText.substring(offs, currentText.length());
        String futureText = beforeOffset + str + afterOffset;
        Object item = lookupItem(futureText);
        if (item != null) {
            comboBox.setSelectedItem(item);
        } else {
            item = comboBox.getSelectedItem();
            offs = offs - str.length();
            comboBox.getToolkit().beep();
        }
        super.remove(0, getLength());
        super.insertString(0, item.toString(), a);
        if (item.toString().equals(str) && offs == 0) {
            highlightCompletedText(0);
        } else {
            highlightCompletedText(offs + str.length());
            if (comboBox.isShowing() && comboBox.isFocusOwner()) {
                comboBox.setPopupVisible(true);
            }
        }
    }

    private void highlightCompletedText(int start) {
        editor.setCaretPosition(getLength());
        editor.moveCaretPosition(start);
    }

    private Object lookupItem(String pattern) {
        Object selectedItem = model.getSelectedItem();
        if (selectedItem != null && startsWithIgnoreCase(selectedItem.toString(), pattern)) {
            return selectedItem;
        } else {
            for (int i = 0, n = model.getSize(); i < n; i++) {
                Object currentItem = model.getElementAt(i);
                if (startsWithIgnoreCase(currentItem.toString(), pattern)) {
                    return currentItem;
                }
            }
        }
        return null;
    }

    private boolean startsWithIgnoreCase(String str1, String str2) {
        return str1.toUpperCase().startsWith(str2.toUpperCase());
    }

    @Override
    public void focusGained(FocusEvent e) {
    }

    @Override
    public void focusLost(FocusEvent e) {
        highlightCompletedText(0);
        if (hidePopupOnFocusLoss) {
            comboBox.setPopupVisible(false);
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {       
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_ENTER) {
            highlightCompletedText(0);
        } else if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
            comboBox.setSelectedIndex(0);
            editor.setText(comboBox.getSelectedItem().toString());
            highlightCompletedText(0);
        }
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if (evt.getPropertyName().equals("model")) {
            registraComboBox(comboBox);
        }
    }
}