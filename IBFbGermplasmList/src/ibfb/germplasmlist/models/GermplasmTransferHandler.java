package ibfb.germplasmlist.models;

import ibfb.domain.core.Factor;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.util.ArrayList;
import java.util.List;

import javax.activation.DataHandler;
import javax.swing.JComponent;
import javax.swing.JTable;
import javax.swing.TransferHandler;

/**
 * Utility class for handling drag and drop
 *
 * @author TMSANCHEZ
 */
public class GermplasmTransferHandler extends TransferHandler {

    /**
     * Source table for drag
     */
    private JTable sourceTable;
    private JTable sourceTable2;
    /**
     * Destination table for drop
     */
    private JTable destTable;
    /**
     * Source list
     */
    private List<List<Object>> sourceList;
    /**
     * Destination list
     */
    private List<List<Object>> destList;
    /**
     * Factores
     */
    private List<Factor> factores;
    /**
     * Delete all items in source list
     */
    private boolean deleteFromSourceList = true;

    public GermplasmTransferHandler(JTable sourceTable, JTable destTable, List<List<Object>> sourceList, List<List<Object>> destList) {
        this.sourceTable = sourceTable;
        this.destTable = destTable;
        this.sourceList = sourceList;
        this.destList = destList;
    }

    public GermplasmTransferHandler(JTable sourceTable, JTable sourceTable2, JTable destTable, List<List<Object>> sourceList, List<List<Object>> destList) {
        this.sourceTable = sourceTable;
        this.destTable = destTable;
        this.sourceList = sourceList;
        this.destList = destList;
        this.sourceTable2 = sourceTable2;
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
                sourceList.get(selectedRows[i]).add(1);
                sourceList.get(selectedRows[i]).add(1);

                destList.add(sourceList.get(selectedRows[i]));

                toRemove.add(sourceList.get(selectedRows[i]));
            }
            if (deleteFromSourceList) {
                sourceList.removeAll(toRemove);
            }
            sourceTable.getSelectionModel().clearSelection();
            sourceTable.updateUI();

            GermplasmEntriesTableModelChecks tableModel = new GermplasmEntriesTableModelChecks(factores, destList);
            tableModel.setHasChecks(true);
            destTable.setModel(tableModel);
            destTable.updateUI();

            return true;

        }
        return false;
    }

    public List<List<Object>> getDestList() {
        return destList;
    }

    public void setDestList(List<List<Object>> destList) {
        this.destList = destList;
    }

    public JTable getDestTable() {
        return destTable;
    }

    public void setDestTable(JTable destTable) {
        this.destTable = destTable;
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

    public boolean isDeleteFromSourceList() {
        return deleteFromSourceList;
    }

    public void setDeleteFromSourceList(boolean deleteFromSourceList) {
        this.deleteFromSourceList = deleteFromSourceList;
    }
    
    
}
