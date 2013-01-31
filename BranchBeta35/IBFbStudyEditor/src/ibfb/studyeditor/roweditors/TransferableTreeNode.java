
package ibfb.studyeditor.roweditors;



import java.io.*;

import java.awt.datatransfer.*;
import javax.swing.tree.*;

public class TransferableTreeNode implements Transferable {

  public static DataFlavor TREE_PATH_FLAVOR = new DataFlavor(TreePath.class,"Tree Path");
  DataFlavor flavors[] = { TREE_PATH_FLAVOR };
  TreePath path;
						   
  public TransferableTreeNode(TreePath tp) {
    path = tp;
  }

    @Override
  public synchronized DataFlavor[] getTransferDataFlavors() {
    return flavors;
  }

    @Override
  public boolean isDataFlavorSupported(DataFlavor flavor) {
    return (flavor.getRepresentationClass() == TreePath.class);
  }

    @Override
  public synchronized Object getTransferData(DataFlavor flavor) 
    throws UnsupportedFlavorException, IOException {
    if (isDataFlavorSupported(flavor)) {
      return (Object)path;
    } else {
      throw new UnsupportedFlavorException(flavor);
    }
  }
}