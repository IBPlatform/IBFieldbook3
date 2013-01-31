package ibfb.studyeditor.roweditors;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Insets;
import java.awt.event.MouseEvent;
import java.util.*;

import javax.swing.DefaultCellEditor;
import javax.swing.Icon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.event.CellEditorListener;
import javax.swing.table.TableCellEditor;
import org.cimmyt.cril.ibwb.api.AppServicesProxy;
import org.cimmyt.cril.ibwb.domain.Location;
import org.cimmyt.cril.ibwb.domain.Persons;
import org.openide.util.ImageUtilities;

public class TrialConditionsRowEditor implements TableCellEditor {

    public final Icon DOTDOTDOT_ICON = ImageUtilities.loadImageIcon("ibfb/studyeditor/images/dots.png", false);
    protected HashMap<String, TableCellEditor> editors;
    protected TableCellEditor editor;
    protected TableCellEditor defaultEditor;
    private JTable table;
    private JButton customEditorButton = new JButton(DOTDOTDOT_ICON);
    protected int row;
    protected int column;
    private SelectTrialConditionItemListener selectTrialConditionItemListener;

    public TrialConditionsRowEditor(JTable table) {
        this.table = table;
        editors = new HashMap<String, TableCellEditor>();
        defaultEditor = new DefaultCellEditor(new JTextField());
        createEditors();
        customEditorButton.setFocusable(true);
        customEditorButton.setFocusPainted(true);
        customEditorButton.setMargin(new Insets(0, 0, 0, 0));
        selectTrialConditionItemListener = new SelectTrialConditionItemListener(table);
        customEditorButton.addActionListener(selectTrialConditionItemListener);                
    }

    public void setEditorAt(String row, TableCellEditor editor) {
        editors.put(row, editor);
    }

    @Override
    public Component getTableCellEditorComponent(JTable table, Object value,
            boolean isSelected, int row, int column) {
//        return editor.getTableCellEditorComponent(table, value, isSelected,
//                row, column);
        JPanel panel = new JPanel(new BorderLayout());
        panel.add(editor.getTableCellEditorComponent(table, value, isSelected, row, column));
        panel.add(customEditorButton, BorderLayout.EAST);
        this.table = table;
        this.row = row;
        this.column = column;
        //        return editor.getTableCellEditorComponent(table, value, isSelected,
        //                row, column);
        selectTrialConditionItemListener.setInfo(value, row, column,editor.getTableCellEditorComponent(table, value, isSelected, row, column) );

        return panel;
    }

    @Override
    public Object getCellEditorValue() {
        return editor.getCellEditorValue();
    }

    @Override
    public boolean stopCellEditing() {
        return editor.stopCellEditing();
    }

    @Override
    public void cancelCellEditing() {
        editor.cancelCellEditing();
    }

    @Override
    public boolean isCellEditable(EventObject anEvent) {
        if (anEvent instanceof MouseEvent) {
            selectEditor((MouseEvent) anEvent);
            return editor.isCellEditable(anEvent);
        } else {
            return false;
        }
    }

    @Override
    public void addCellEditorListener(CellEditorListener l) {
        editor.addCellEditorListener(l);
    }

    @Override
    public void removeCellEditorListener(CellEditorListener l) {
        editor.removeCellEditorListener(l);
    }

    @Override
    public boolean shouldSelectCell(EventObject anEvent) {
        selectEditor((MouseEvent) anEvent);
        return editor.shouldSelectCell(anEvent);
    }

    protected void selectEditor(MouseEvent e) {
        int row;
        if (e == null) {
            row = table.getSelectionModel().getAnchorSelectionIndex();
        } else {
            row = table.rowAtPoint(e.getPoint());
        }

        // Search for editor based on property and scale columns
        String propertyName = (String) table.getValueAt(row, 3);
        String scale = (String) table.getValueAt(row, 4);
        editor = editors.get(propertyName + scale);

        // if editor not found
        if (editor == null) {
            // then search from column name
            String conditionName = (String) table.getValueAt(row, 1);
            conditionName = conditionName.trim().toUpperCase();

            editor = editors.get(conditionName);

            // if not editor found then return default editor
            if (editor == null) {
                editor = defaultEditor;
            }
        }
    }

    private void createEditors() {

        JComboBox comboEditorProgram = new JComboBox();
        comboEditorProgram.addItem("PROGRAM A");
        comboEditorProgram.addItem("PROGRAM B");
        comboEditorProgram.addItem("PROGRAM C");
        comboEditorProgram.setEditable(true);


        JComboBox comboEditorCycle = new JComboBox();
        comboEditorCycle.addItem("CYCLE A");
        comboEditorCycle.addItem("CYCLE B");
        comboEditorCycle.addItem("CYCLE C");
        comboEditorCycle.setEditable(true);

        List<Persons> personsList = AppServicesProxy.getDefault().appServices().getPersonsList();
        JComboBox comboPI_ID = new JComboBox();
        List<String> sortedPersons = new ArrayList<String>();
        for (Persons persons : personsList) {
            comboPI_ID.addItem(persons.getPersonid());
            sortedPersons.add(persons.getFullName().toUpperCase());
        }
        comboPI_ID.setEditable(true);


        Collections.sort(sortedPersons);

        JComboBox comboPI_NAME = new JComboBox();
        //for (Persons persons : personsList) {
        for (String personName : sortedPersons) {
            //comboPI_NAME.addItem(persons.getFullName().toUpperCase());
            comboPI_NAME.addItem(personName);
        }
        comboPI_NAME.setEditable(true);

        JComboBox comboCO_ID = new JComboBox();
        for (Persons persons : personsList) {
            comboCO_ID.addItem(persons.getPersonid().toString());
        }

        comboCO_ID.setEditable(true);


        JComboBox comboCO_NAME = new JComboBox();
        //for (Persons persons : personsList) {
        for (String personName : sortedPersons) {
            //comboCO_NAME.addItem(persons.getFullName().toUpperCase());
            comboCO_NAME.addItem(personName);
        }
        comboCO_NAME.setEditable(true);

        JComboBox comboSITE_ID = new JComboBox();
        JComboBox comboSITE_NAME = new JComboBox();

        List< Location> locationList = AppServicesProxy.getDefault().appServices().getLocationList();
        List<String> sortedLocationName = new ArrayList<String>();

        for (Location location : locationList) {
            comboSITE_ID.addItem(location.getLocid().toString());
            sortedLocationName.add(location.getLname().toUpperCase());
        }

        Collections.sort(sortedLocationName);

        for (String location : sortedLocationName) {
            comboSITE_NAME.addItem(location);
        }
        comboSITE_ID.setEditable(true);

        JTextField textEditor = new JTextField();
        textEditor.setText("");
        textEditor.setEditable(true);

        editors.put("NID", new DefaultCellEditor(textEditor));
        editors.put("PROGRAM", new DefaultCellEditor(comboEditorProgram));
        editors.put("CYCLE", new DefaultCellEditor(comboEditorCycle));
        //editors.put("COOPERATOR ID", new DefaultCellEditor(comboCO_ID));
        //editors.put("COOPERATOR", new DefaultCellEditor(comboCO_NAME));
        //editors.put("SITE ID", new DefaultCellEditor(comboSITE_ID));
        //editors.put("SITE", new DefaultCellEditor(comboSITE_NAME));
        editors.put("PERSONDBID", new DefaultCellEditor(comboCO_ID));
        editors.put("PERSONDBCV", new DefaultCellEditor(comboCO_NAME));
        editors.put("LOCATIONDBID", new DefaultCellEditor(comboSITE_ID));
        editors.put("LOCATIONDBCV", new DefaultCellEditor(comboSITE_NAME));

        editors.put("PDATE-1 EARLY", new DateCellEditor());
        editors.put("PDATE-2 LATE", new DateCellEditor());

    }
}
