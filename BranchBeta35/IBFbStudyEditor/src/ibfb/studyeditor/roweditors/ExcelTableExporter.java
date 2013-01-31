package ibfb.studyeditor.roweditors;

import java.io.*;
import java.util.List;
import javax.swing.*;
import jxl.write.*;
import jxl.*;

public class ExcelTableExporter {

    private File file;
    private List<JTable> tables;
    private List<String> nombreTabs;

    public ExcelTableExporter(List<JTable> tables, File file, List<String> nombreTabs) throws Exception {
        this.file = file;
        this.tables = tables;
        this.nombreTabs = nombreTabs;
        if(nombreTabs.size()!=tables.size()){
            throw new Exception("Cantidad de tablas debe coincidir con el nombre de hojas");
        }
    }

    public boolean export() {
        try {
            DataOutputStream out = new DataOutputStream(new FileOutputStream(file));

            WritableWorkbook w = Workbook.createWorkbook(out);
            for (int index=0;index<tables.size();index++) {
                JTable table=tables.get(index);
                WritableSheet s = w.createSheet(nombreTabs.get(index), 0);

                for (int i = 0; i < table.getColumnCount(); i++) {
                    Object titulo = table.getColumnModel().getColumn(i).getHeaderValue();
                    
                     s.addCell(new Label(i, 0, String.valueOf(titulo)));
                    
                    
                    for (int j = 0; j < table.getRowCount(); j++) {
                        Object objeto = table.getValueAt(j, i);
                        s.addCell(new Label(i, j+1, String.valueOf(objeto)));
                    }
                }
            }
            w.write();
            w.close();
            out.close();
            return true;

        } catch (IOException ex) {
            System.out.println("ERROR IO EXPORT EXCEL IBFB "+ex);
        } catch (WriteException ex) {
            System.out.println("ERROR WRITEEXCEPTION EXPORT EXCEL IBFB "+ex);
        }
        return false;
    }
}
