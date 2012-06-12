/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ibfb.studyeditor.util;

import ibfb.studyeditor.roweditors.DateCellEditor;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JOptionPane;
import javax.swing.JTable;

/**
 * Date utility class for date validation
 * @author TMSANCHEZ
 */
public class DateUtil {

    /**
     * Check date range validation
     * @param instance
     * @param jTableTrialConditions
     * @param row 
     */
    public static void compareDates(int instance, JTable jTableTrialConditions, int row) {
        int rowDate1 = encuentraComplemento("PDATE-1 EARLY", instance, jTableTrialConditions);
        int rowDate2 = encuentraComplemento("PDATE-2 LATE", instance, jTableTrialConditions);
        Object startValue = jTableTrialConditions.getValueAt(rowDate1, 5).toString();
        Object endValue = jTableTrialConditions.getValueAt(rowDate2, 5).toString();
        SimpleDateFormat sdf = new SimpleDateFormat(DateCellEditor.DATE_FORMAT);
        if (startValue != null && endValue != null) {
            Date dateStart = null;
            Date dateEnd = null;

            try {
                dateStart = sdf.parse(startValue.toString());
                dateEnd = sdf.parse(endValue.toString());
                if (dateStart.compareTo(dateEnd) >= 0) {
                    jTableTrialConditions.setValueAt("", row, 5);
                    JOptionPane.showMessageDialog(null, "The Late date must be greater than early date", "", instance);
                }
            } catch (Exception e) {
            }
        }

    }

    /**
     * 
     * @param myConditionToSearch
     * @param instancia
     * @param jTableTrialConditions
     * @return 
     */
    private static int encuentraComplemento(String myConditionToSearch, int instancia, JTable jTableTrialConditions) {
        int filaComplemento = -1;

        for (int i = 0; i < jTableTrialConditions.getRowCount(); i++) {
            Object instance = jTableTrialConditions.getValueAt(i, 0);
            Object condition = jTableTrialConditions.getValueAt(i, 1).toString().toUpperCase();

            if (condition.equals(myConditionToSearch) && (instancia == instance)) {
                filaComplemento = i;
                break;
            }
        }

        return filaComplemento;
    }
}
