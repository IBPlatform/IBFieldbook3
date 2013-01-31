package ibfb.nursery.utils;

import ibfb.nursery.editors.DateCellEditor;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JTable;
import org.openide.DialogDisplayer;
import org.openide.NotifyDescriptor;
import org.openide.util.NbBundle;

public class DateUtil {

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
                    NotifyDescriptor d = new NotifyDescriptor.Message(NbBundle.getMessage(DateUtil.class,"DateUtil.greater"), NotifyDescriptor.INFORMATION_MESSAGE);
                    DialogDisplayer.getDefault().notify(d);                                                      
                }
            } catch (Exception e) {
            }
        }

    }

  
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
