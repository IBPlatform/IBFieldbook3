
package ibfb.germplasmlist.models;

import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.util.List;
import javax.activation.ActivationDataFlavor;
import javax.activation.DataHandler;
import javax.swing.DefaultListModel;
import javax.swing.JComponent;
import javax.swing.JList;
import javax.swing.TransferHandler;


public class ListTransferHandler extends TransferHandler {

    private int[] indices = null;
    private int addIndex = -1;
    private int addCount = 0;
    private final DataFlavor localObjectFlavor;
    private Object[] transferedObjects = null;
    private JList source = null;
    private int type = 0;
    private DataFlavor additionalDataFlavorSupported;
    private DropTargetCommand dropTargetCommand;

    public ListTransferHandler() {

        localObjectFlavor = new ActivationDataFlavor(Object[].class, "Array of items");

    }

    @Override
    protected Transferable createTransferable(JComponent c) {
        source = (JList) c;
        indices = source.getSelectedIndices();
        transferedObjects = source.getSelectedValues();
        return new DataHandler(transferedObjects, localObjectFlavor.getMimeType());
    }

    @Override
    public boolean canImport(TransferHandler.TransferSupport info) {
        boolean result = false;
        result = info.isDrop() && (info.isDataFlavorSupported(localObjectFlavor) || info.isDataFlavorSupported(DataFlavor.stringFlavor));
        return result;
    }

    @Override
    public int getSourceActions(JComponent c) {
        return TransferHandler.MOVE;
    }

    @Override
    public boolean importData(TransferHandler.TransferSupport info) {
        if (!canImport(info)) {
            return false;
        }

        if (info.isDataFlavorSupported(DataFlavor.stringFlavor)) {
            
                // Get the string that is being dropped.
                Transferable t = info.getTransferable();
                String data = null;
                try {
                    data = (String) t.getTransferData(DataFlavor.stringFlavor);
                } catch (Exception e) {
                    return false;
                }
                if (data != null && dropTargetCommand != null) {
                    dropTargetCommand.onDropExecute(data);
                }
                return true;
            
        } else {

            JList target = (JList) info.getComponent();
            JList.DropLocation dl = (JList.DropLocation) info.getDropLocation();
            DefaultListModel listModel = (DefaultListModel) target.getModel();
            int index = dl.getIndex();
            int max = listModel.getSize();

            if (index < 0 || index > max) {
                index = max;
            }

            addIndex = index;

            try {
                Object[] values = (Object[]) info.getTransferable().getTransferData(localObjectFlavor);
                for (int i = 0; i < values.length; i++) {
                    int idx = index++;
                    listModel.add(idx, values[i]);
                    target.addSelectionInterval(idx, idx);
                }
                addCount = (target == source) ? values.length : 0;
                return true;
            } catch (UnsupportedFlavorException ufe) {
                System.out.println("UnsupportedFlavorException" + ufe);
            } catch (java.io.IOException ioe) {
                System.out.println("IOException" + ioe);

            }
        }
        return false;
    }

    @Override
    protected void exportDone(JComponent c, Transferable data, int action) {
        cleanup(c, action == TransferHandler.MOVE);

    }

    private void cleanup(JComponent c, boolean remove) {
        if (remove && indices != null) {
            JList origen = (JList) c;



            DefaultListModel model = (DefaultListModel) origen.getModel();
            if (addCount > 0) {
                for (int i = 0; i < indices.length; i++) {
                    if (indices[i] >= addIndex) {
                        indices[i] += addCount;
                    }
                }
            }
            for (int i = indices.length - 1; i >= 0; i--) {
                model.remove(indices[i]);
            }




//          
        }
        indices = null;
        addCount = 0;
        addIndex = -1;

    }

    public DataFlavor getAdditionalDataFlavorSupported() {
        return additionalDataFlavorSupported;
    }

    public void setAdditionalDataFlavorSupported(DataFlavor additionalDataFlavorSupported) {
        this.additionalDataFlavorSupported = additionalDataFlavorSupported;
    }

    public DropTargetCommand getDropTargetCommand() {
        return dropTargetCommand;
    }

    public void setDropTargetCommand(DropTargetCommand dropTargetCommand) {
        this.dropTargetCommand = dropTargetCommand;
    }
    
    
}
