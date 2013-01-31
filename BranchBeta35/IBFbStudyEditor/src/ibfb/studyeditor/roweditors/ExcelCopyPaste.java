
package ibfb.studyeditor.roweditors;

import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.StringSelection;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.StringTokenizer;
import javax.swing.JComponent;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.KeyStroke;





public class ExcelCopyPaste implements ActionListener
   {
   private String rowstring,value;
   private Clipboard system;
   private StringSelection stsel;
   private JTable thisTable ;

public ExcelCopyPaste(JTable myJTable)
   {
     this.thisTable = myJTable;
      KeyStroke copy = KeyStroke.getKeyStroke(KeyEvent.VK_C,ActionEvent.CTRL_MASK,false);

      KeyStroke paste = KeyStroke.getKeyStroke(KeyEvent.VK_V,ActionEvent.CTRL_MASK,false);

thisTable.registerKeyboardAction(this,"Copy",copy,JComponent.WHEN_FOCUSED);
thisTable.registerKeyboardAction(this,"Paste",paste,JComponent.WHEN_FOCUSED);
      system = Toolkit.getDefaultToolkit().getSystemClipboard();
   }

public JTable getJTable() {
    return thisTable;
}

public void setJTable(JTable jTable1) {
    this.thisTable=jTable1;
}

    @Override
public void actionPerformed(ActionEvent e)
   {
      if (e.getActionCommand().compareTo("Copy")==0)
      {
         StringBuilder sbf=new StringBuilder();
         // Check to ensure we have selected only a contiguous block of
         // cells
         int numcols=thisTable.getSelectedColumnCount();
         int numrows=thisTable.getSelectedRowCount();
         int[] rowsselected=thisTable.getSelectedRows();
         int[] colsselected=thisTable.getSelectedColumns();
         if (!((numrows-1==rowsselected[rowsselected.length-1]-rowsselected[0] &&
                numrows==rowsselected.length) &&
(numcols-1==colsselected[colsselected.length-1]-colsselected[0] &&
                numcols==colsselected.length)))
         {
            JOptionPane.showMessageDialog(null, "Invalid Copy Selection",
                                          "Invalid Copy Selection",
                                          JOptionPane.ERROR_MESSAGE);
            return;
         }
         for (int i=0;i<numrows;i++)
         {
            for (int j=0;j<numcols;j++)
            {
sbf.append(thisTable.getValueAt(rowsselected[i],colsselected[j]));
               if (j<numcols-1) sbf.append("\t");
            }
            sbf.append("\n");
         }
         stsel  = new StringSelection(sbf.toString());
         system = Toolkit.getDefaultToolkit().getSystemClipboard();
         system.setContents(stsel,stsel);
      }
      if (e.getActionCommand().compareTo("Paste")==0)
      {
          System.out.println("Trying to Paste");
          int startRow=(thisTable.getSelectedRows())[0];
          int startCol=(thisTable.getSelectedColumns())[0];
          try
          {
             String trstring= (String)(system.getContents(this).getTransferData(DataFlavor.stringFlavor));
             System.out.println("String is:"+trstring);
             StringTokenizer st1=new StringTokenizer(trstring,"\n");
             for(int i=0;st1.hasMoreTokens();i++)
             {
                rowstring=st1.nextToken();
                StringTokenizer st2=new StringTokenizer(rowstring,"\t");
                for(int j=0;st2.hasMoreTokens();j++)
                {
                   value=st2.nextToken();
                   if (startRow+i< thisTable.getRowCount()  &&
                       startCol+j< thisTable.getColumnCount())
                      thisTable.setValueAt(value,startRow+i,startCol+j);
                   System.out.println("Putting "+ value+"at row="+startRow+i+"column="+startCol+j);
               }
            }
         }
         catch(Exception ex){
             System.out.println("ERROR: "+ex);}
      }
   }
}


