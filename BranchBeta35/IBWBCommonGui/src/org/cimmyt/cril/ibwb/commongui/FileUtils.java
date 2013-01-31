
package org.cimmyt.cril.ibwb.commongui;

import java.io.File;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileFilter;
import org.cimmyt.cril.ibwb.commongui.filters.CSVFiltro;

/**
 *
 * @author TMSANCHEZ
 */
public class FileUtils {

   
    public static String getSelectedFolder(String currentFolder) {
        String selectedFolder = "";
        JFileChooser fileChooser = new JFileChooser();

        fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);

        if (fileChooser.showOpenDialog(null) == JFileChooser.CANCEL_OPTION) {
            return currentFolder;
        }

        selectedFolder = fileChooser.getSelectedFile().getPath();


        return selectedFolder;
    }
    
    public static File openFile() {
        File fileName=null;
        FileFilter[] fileFilter = new FileFilter[10];
        JFileChooser fileSelect = new JFileChooser();
        fileFilter = fileSelect.getChoosableFileFilters();

        String documentsPath = OSUtils.getDocumentsPath();
        File curDirectory = new File(documentsPath);

        for (int i = 0; i < fileFilter.length; i++) {
            FileFilter filter = fileFilter[i];
            fileSelect.removeChoosableFileFilter(filter);
        }


        fileSelect.setCurrentDirectory(curDirectory);
        fileSelect.setFileSelectionMode(JFileChooser.FILES_ONLY);
        fileSelect.addChoosableFileFilter(new CSVFiltro());
        int result = fileSelect.showOpenDialog(null);

        if (result == JFileChooser.CANCEL_OPTION) {
            return fileName;
        }
        fileName = fileSelect.getSelectedFile();
        return fileName;

    }
    
     public static void openDirectory(String directory) {
        File fileDirectory = new File(directory);
        JFileChooser fileSelect = new JFileChooser();
      
        fileSelect.setCurrentDirectory(fileDirectory);

        int result = fileSelect.showOpenDialog(null);


    }
     
     /**
      * Extract the file name without the path from a String that
      * contains all path separator
      * @param fullPath File name with full path
      * @return file name without path
      */
     public static String extractFileName(String fullPath) {
         int lastIndexSeparator = fullPath.lastIndexOf(File.separator);
        return fullPath.substring(lastIndexSeparator+1,fullPath.length());
     }
}
