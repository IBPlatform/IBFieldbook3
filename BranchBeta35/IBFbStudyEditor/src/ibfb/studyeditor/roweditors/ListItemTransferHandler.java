package ibfb.studyeditor.roweditors;

import ibfb.studyeditor.core.StudyEditorTopComponent;
import ibfb.studyeditor.wizard.TrialWizardVisualPanel7;
import ibfb.studyeditor.wizard.TrialWizardVisualPanel8;
import ibfb.traits.core.palette.TraitModel;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import javax.activation.ActivationDataFlavor;
import javax.activation.DataHandler;
import javax.swing.DefaultListModel;
import javax.swing.JComponent;
import javax.swing.JList;
import javax.swing.TransferHandler;
import org.openide.nodes.Node;
import org.openide.nodes.NodeTransfer;
import org.openide.windows.WindowManager;

public class ListItemTransferHandler extends TransferHandler {

    private int[] indices = null;
    private int addIndex = -1;
    private int addCount = 0;
    private final DataFlavor localObjectFlavor;
    private Object[] transferedObjects = null;
    private JList source = null;
    private int type = 0;

    public ListItemTransferHandler(int tipo) {

        localObjectFlavor = new ActivationDataFlavor(Object[].class, "Array of items");
        this.type = tipo;
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
        return info.isDrop() && (info.isDataFlavorSupported(localObjectFlavor) || info.isDataFlavorSupported(TraitModel.DATA_FLAVOR));
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

            if (info.isDataFlavorSupported(TraitModel.DATA_FLAVOR)) {
                Node node = NodeTransfer.node(info.getTransferable(), NodeTransfer.DND_COPY_OR_MOVE);
                TraitModel trait = (TraitModel) node.getLookup().lookup(TraitModel.class);
                //listModel.addElement(trait.getTrait().getTrname().trim() + " (" + trait.getTrait().getScale().getScname() + ")");
                String scale = "NO SCALE";

                if (trait.getTrait().getMeasuredin() != null && trait.getTrait().getMeasuredin().getStandardscale() != null) {
                    scale = trait.getTrait().getMeasuredin().getStandardscale();
                }
                //displayName.append("(").append(traitModel.getTrait().getScale().getScname() ).append(")");
                StringBuilder traitToAdd = new StringBuilder();
                traitToAdd.append(trait.getTrait().getTrname().trim());
                traitToAdd.append(" (").append(scale).append(")");
                listModel.addElement(traitToAdd);
            } else {


                Object[] values = (Object[]) info.getTransferable().getTransferData(localObjectFlavor);
                for (int i = 0; i < values.length; i++) {
                    int idx = index++;
                    listModel.add(idx, values[i]);
                    target.addSelectionInterval(idx, idx);
                }
                addCount = (target == source) ? values.length : 0;
            }
            return true;
        } catch (UnsupportedFlavorException ufe) {
            System.out.println("UnsupportedFlavorException" + ufe);
        } catch (java.io.IOException ioe) {
            System.out.println("IOException" + ioe);

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

            if (type == 0) {
                TrialWizardVisualPanel7.cleanFields();
            }

            if (type == 1) {
                TrialWizardVisualPanel8.cleanFields();
            }

            if (type == 2) {
                StudyEditorTopComponent studyWindow = (StudyEditorTopComponent) WindowManager.getDefault().getRegistry().getActivated();
                studyWindow.cleanFields();
            }



//          
        }
        indices = null;
        addCount = 0;
        addIndex = -1;

    }
}
