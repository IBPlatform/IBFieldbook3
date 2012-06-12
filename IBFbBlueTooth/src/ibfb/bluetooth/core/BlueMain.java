
package ibfb.bluetooth.core;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import org.openide.util.Exceptions;



public class BlueMain {
    public boolean StartSendFileTask(String deviceAddress, String fileToSend,String obexURL)
   {
       try
       {
           FileInputStream stream = new FileInputStream(fileToSend);

           File f = new File(fileToSend);
           int size = (int)f.length();
           byte file[] = new byte[size];
           stream.read(file);
           String filename = f.getName();
           System.out.println("***************Now sending file to device*****************");
           SendFileTask task = new SendFileTask(deviceAddress, file, filename, obexURL);

       }
       catch(Exception ex)
       {            
           System.out.println("ERROR EN BLUEMAIN "+ex);
           return false;
       }
       return true;
   }



   public static void main(String args[]) {
        try {
            // 	BlueMain bm = new BlueMain();
           //bm.StartSendFileTask("001E458436D5", "/Users/Oziel/Desktop/oziel.txt", "btgoep://001E458436D5:6;authenticate=false;encrypt=false;master=false");
            
             BlueTooth.start();
        } catch (IOException ex) {
            Exceptions.printStackTrace(ex);
        } catch (InterruptedException ex) {
            Exceptions.printStackTrace(ex);
        }
   
   }

}
