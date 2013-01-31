package ibfb.nursery.filters;

import java.io.File;
import javax.swing.filechooser.FileFilter;

public class Excel2007Filtro extends FileFilter {

    @Override
    public boolean accept(File f) {
        return f.getName().toLowerCase().endsWith(".xlsx") || f.isDirectory();
    }

    @Override
    public String getDescription() {
        return "EXCEL FILE (.xlsx)";
    }
}
