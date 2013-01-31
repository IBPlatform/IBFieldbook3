package ibfb.r.filters;

import java.io.File;
import javax.swing.filechooser.FileFilter;

public class ExcelFiltro extends FileFilter {

    @Override
    public boolean accept(File f) {
        return f.getName().toLowerCase().endsWith(".xls") || f.isDirectory();
    }

    @Override
    public String getDescription() {
        return "EXCEL FILE (.xls)";
    }
}
