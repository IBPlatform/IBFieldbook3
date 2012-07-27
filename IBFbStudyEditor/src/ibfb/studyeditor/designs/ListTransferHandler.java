
package ibfb.studyeditor.designs;

import javax.swing.DefaultListModel;
import javax.swing.JComponent;
import javax.swing.JList;

class ListTransferHandler extends StringTransferHandler {
  private int[] indices = null;
  private int addIndex = -1; 

  private int addCount = 0; 

  
    @Override
  protected String exportString(JComponent c) {
    JList list = (JList) c;
    indices = list.getSelectedIndices();
    Object[] values = list.getSelectedValues();

    StringBuilder buff = new StringBuilder();

    for (int i = 0; i < values.length; i++) {
      Object val = values[i];
      buff.append(val == null ? "" : val.toString());
      if (i != values.length - 1) {
        buff.append("\n");
      }
    }

    
        
    return buff.toString();
  }

 
    @Override
  protected void importString(JComponent c, String str) {
       
    JList target = (JList) c;
    DefaultListModel listModel = (DefaultListModel) target.getModel();
    int index = target.getSelectedIndex();

    if (indices != null && index >= indices[0] - 1
        && index <= indices[indices.length - 1]) {
      indices = null;
      return;
    }

    int max = listModel.getSize();
    if (index < 0) {
      index = max;
    } else {
      index++;
      if (index > max) {
        index = max;
      }
    }
    addIndex = index;
    String[] values = str.split("\n");
    addCount = values.length;
    for (int i = 0; i < values.length; i++) {
      listModel.add(index++, values[i]);
    }
  }

  
    @Override
  protected void cleanup(JComponent c, boolean remove) {
    if (remove && indices != null) {
      JList source = (JList) c;
      DefaultListModel model = (DefaultListModel) source.getModel();

      if (addCount > 0) {
        for (int i = 0; i < indices.length; i++) {
          if (indices[i] > addIndex) {
            indices[i] += addCount;
          }
        }
      }
      for (int i = indices.length - 1; i >= 0; i--) {
        model.remove(indices[i]);
      }
    }
    indices = null;
    addCount = 0;
    addIndex = -1;
  }

    
}
