package ibfb.studyeditor.roweditors;

import com.toedter.calendar.JDateChooser;
import java.awt.Component;
import java.text.SimpleDateFormat;
import javax.swing.AbstractCellEditor;
import javax.swing.JTable;
import javax.swing.table.TableCellEditor;

public class DateCellEditor extends AbstractCellEditor implements TableCellEditor {

    //public static final String DATE_FORMAT = "MM-dd-yyyy";
    public static final String DATE_FORMAT = "yyyyMMdd";
    private JDateChooser dateChooser;

    public DateCellEditor() {
        dateChooser = new JDateChooser();
        dateChooser.setDateFormatString(DATE_FORMAT);
        dateChooser.setOpaque(false);
    }

    @Override
    public Object getCellEditorValue() {
        try {

            return new SimpleDateFormat(DATE_FORMAT).format(dateChooser.getDate());
        } catch (NullPointerException ex) {
            return "";
        }
    }

    @Override
    public Component getTableCellEditorComponent(JTable table,
            Object value, boolean isSelected, int row, int column) {

        return dateChooser;
    }
}
