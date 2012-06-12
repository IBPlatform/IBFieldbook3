
package ibfb.nursery.models;

import ibfb.domain.core.Factor;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.util.ArrayList;
import java.util.List;
import javax.activation.DataHandler;
import javax.swing.JComponent;
import javax.swing.JTable;
import javax.swing.TransferHandler;


public class RemoveGermplasmTransferHandler extends TransferHandler {

   
    private JTable sourceTable;
    
    private List<List<Object>> sourceList;
    
    private List<Factor> factores;

    public RemoveGermplasmTransferHandler(JTable sourceTable, List<List<Object>> sourceList) {
        this.sourceTable = sourceTable;
        this.sourceList = sourceList;
    }

    @Override
    protected Transferable createTransferable(JComponent c) {
        return new DataHandler("", DataFlavor.stringFlavor.getMimeType());
    }

    @Override
    public int getSourceActions(JComponent c) {
        return COPY_OR_MOVE;
    }

    @Override
    public boolean canImport(TransferSupport support) {
        return support.isDataFlavorSupported(DataFlavor.stringFlavor);
    }

    @Override
    public boolean importData(TransferSupport support) {
        if (support.isDataFlavorSupported(DataFlavor.stringFlavor)) {
            List<List<Object>> toRemove = new ArrayList<List<Object>>();
            int[] selectedRows = sourceTable.getSelectedRows();
            for (int i = 0; i < selectedRows.length; i++) {
                toRemove.add(sourceList.get(selectedRows[i]));

            }
            sourceList.removeAll(toRemove);
            sourceTable.getSelectionModel().clearSelection();
            sourceTable.updateUI();

            return true;

        }
        return false;
    }

    public List<List<Object>> getSourceList() {
        return sourceList;
    }

    public void setSourceList(List<List<Object>> sourceList) {
        this.sourceList = sourceList;
    }

    public JTable getSourceTable() {
        return sourceTable;
    }

    public void setSourceTable(JTable sourceTable) {
        this.sourceTable = sourceTable;
    }

    public List<Factor> getFactores() {
        return factores;
    }

    public void setFactores(List<Factor> factores) {
        this.factores = factores;
    }
}
