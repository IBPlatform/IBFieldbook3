package ibfb.germplasmlist.filters;

import java.io.File;
import javax.swing.filechooser.FileFilter;

public class CSVFiltro extends FileFilter {

    @Override
    public boolean accept(File f) {
        return f.getName().toLowerCase().endsWith(".csv") || f.isDirectory();
    }

    @Override
    public String getDescription() {
        return "CSV TEXT FILE (.csv)";
    }
}
