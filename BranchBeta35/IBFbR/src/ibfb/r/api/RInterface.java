
package ibfb.r.api;


public interface RInterface {
    
    public void setScript(int elScript);
    
    public void setFile(String elFile);
    
    public void leeExcelyEjecutaR(String file);
    
    public void abreExplorador(String myFile);
    
    public void ejecutaR(String myFile);
    
    public void copiaArchivoCSV(String myFile, String myArchivo);
    
    public void creaDirectorio(String archivo);
    
    public void creaArchivoR(String myFile); 
}
