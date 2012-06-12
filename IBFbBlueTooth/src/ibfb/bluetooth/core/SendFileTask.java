package ibfb.bluetooth.core;

import java.io.OutputStream;

import javax.microedition.io.Connection;

import javax.obex.ResponseCodes;

import javax.obex.ClientSession;
import javax.obex.HeaderSet;
import javax.obex.Operation;

public class SendFileTask {

    private String btConnectionURL;
    private byte[] file;
    private String filename;
    public static final int WRITE = 2;
    String logString;
    int responseCode;
    String obexURL;

    public SendFileTask(String url, byte[] file, String filename, String obexUrl) {


        this.file = file;
        this.filename = filename;
        this.obexURL = obexUrl;
    }

    public void run() {
        Connection connection = null;
        try {
            System.out.println(btConnectionURL);

            ClientSession cs = (ClientSession) connection;

            HeaderSet hs = cs.createHeaderSet();

            // now let's send the connect header
            cs.connect(hs);

            hs.setHeader(HeaderSet.NAME, filename);
            //System.out.println("sfname:"+filename);
            int dotIndex = filename.lastIndexOf(".");
            //System.out.println("doti:"+dotIndex);
            String extension = filename.substring(dotIndex).toLowerCase();
            //System.out.println("sfname:"+extension);

            if (extension.equals(".txt")) {
                hs.setHeader(HeaderSet.TYPE, "text/plain");
            } else if (extension.equals(".jpg") || extension.equals(".jpeg")) {
                hs.setHeader(HeaderSet.TYPE, "image/jpeg");
            } else if (extension.equals(".mpeg") || extension.equals(".mpg") || extension.equals(".mp3")) {
                hs.setHeader(HeaderSet.TYPE, "video/mpeg");
            } else if (extension.equals(".wav")) {
                hs.setHeader(HeaderSet.TYPE, "audio/x-wav");
            } else if (extension.equals(".3gp")) {
                hs.setHeader(HeaderSet.TYPE, "image/jpeg");
            } else if (extension.equals("mid") || extension.equals("rmi")) {
                hs.setHeader(HeaderSet.TYPE, "audio/mid");
            }



            hs.setHeader(HeaderSet.LENGTH, new Long(file.length));

            Operation putOperation = cs.put(hs);

            OutputStream outputStream = putOperation.openOutputStream();
            outputStream.write(file);

            outputStream.close();
            responseCode = putOperation.getResponseCode();


            putOperation.close();

            cs.disconnect(null);

            connection.close();


            // file successfully sent      


            System.out.println("RESPONSE CODE " + responseCode);
            if (responseCode == ResponseCodes.OBEX_HTTP_OK) {
                System.out.println("FILE SUCCESSFULLY SENT " + filename);
            } else {
                System.out.println("FILE SUCCESSFULLY NOT SENT" + filename + " not in exception");

            }


        } catch (Exception e) {
            System.out.println("FILE SUCCESSFULLY NOT SENT" + filename + " in exception");
            System.out.println("File not sent. ERROR "+e);
            try {
                connection.close();
            } catch (Exception ex) {
                System.out.println("error closing connection" + ex.toString());
            }
        }
    }
}
