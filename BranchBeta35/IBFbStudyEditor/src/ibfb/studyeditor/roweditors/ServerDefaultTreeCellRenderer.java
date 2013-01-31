
package ibfb.studyeditor.roweditors;

import java.awt.Component;
import javax.swing.JTree;
import javax.swing.tree.DefaultTreeCellRenderer;

public class ServerDefaultTreeCellRenderer extends DefaultTreeCellRenderer{
   public ServerDefaultTreeCellRenderer() {
          super();
        }
 
    @Override
        public Component getTreeCellRendererComponent(
                            JTree tree,
                            Object value,
                            boolean sel,
                            boolean expanded,
                            boolean leaf,
                            int row,
                            boolean hasFocus) {
 
            super.getTreeCellRendererComponent(
                            tree, value, sel,
                            expanded, leaf, row,
                            hasFocus);
 
                setToolTipText("");
 
 
            return this;
        } 
    
    
    public void setToolTip (String tooltip){
        setToolTipText(tooltip);
    }
}
