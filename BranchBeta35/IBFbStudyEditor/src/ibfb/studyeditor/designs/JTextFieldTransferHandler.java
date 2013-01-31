/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ibfb.studyeditor.designs;

import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.IOException;
import javax.swing.JComponent;
import javax.swing.TransferHandler;
import javax.swing.text.JTextComponent;

/**
 *
 * @author Oziel
 */
public class JTextFieldTransferHandler extends TransferHandler{
    public int source=0;
    
    
    @Override
     public int getSourceActions(JComponent c) {
        return COPY_OR_MOVE;
    }

    @Override
    public Transferable createTransferable(JComponent c) {
        source=1;
        return new StringSelection(((JTextComponent) c).getSelectedText());
    }

    
    @Override
    public void exportDone(JComponent c, Transferable t, int action) {
        if(action == MOVE){
            ((JTextComponent) c).replaceSelection("");
         source=0;
        }
    }

    @Override
    public boolean canImport(TransferSupport ts) {
       
        JTextComponent c=(JTextComponent) ts.getComponent();
        
        if(source==1 || (!c.getText().isEmpty())){
            return false;
        }else{
        return ts.getComponent() instanceof JTextComponent;
        }
    }

    @Override
    public boolean importData(TransferSupport ts) {
        try {
            ((JTextComponent) ts.getComponent())
                .setText((String) ts
                         .getTransferable()
                         .getTransferData(DataFlavor.stringFlavor));
            return true;
        } catch(UnsupportedFlavorException e) {
            return false;
        } catch(IOException e) {
            return false;
        }
    }
}
