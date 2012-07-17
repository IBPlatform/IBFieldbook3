package ibfb.studyeditor.designs;

import com.csvreader.CsvReader;
import ibfb.domain.core.DesignBean;
import ibfb.studyeditor.core.model.DesignTableModel;
import ibfb.studyeditor.roweditors.AlphaDesignsRowEditor;
import java.awt.Color;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.DefaultCellEditor;
import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableColumn;
import org.cimmyt.cril.ibwb.commongui.ConvertUtils;
import org.cimmyt.cril.ibwb.commongui.DialogUtil;
import org.cimmyt.cril.ibwb.commongui.FileUtils;

/**
 * Class to centralize all designs algorithms for designs
 * @author OZIEL
 */
public class DesignsUtils {

    private JTable jTableDesign;
    private JTextField jTextFieldEntries;
    ArrayList<KSValues> rep2;
    ArrayList<KSValues> rep3;
    ArrayList<KSValues> rep4;

    public DesignsUtils(JTable jTableDesign, JTextField jTextFieldEntries) {
        this.jTableDesign = jTableDesign;
        this.jTextFieldEntries = jTextFieldEntries;
    }

    /**
     * Assigns main editor for design
     * @param jTableDesign
     * @param conAlpha
     * @param conLattice
     * @return 
     */
    public String assignMainCellEditor(boolean conAlpha, boolean conLattice) {
        String inicio = "";
        JComboBox comboBox = new JComboBox();
        if (conAlpha) {
            comboBox.addItem(DesignsClass.ALFA_DESIGN);
        }
        if (conLattice) {
            comboBox.addItem(DesignsClass.LATICE_DESIGN);
        }
        comboBox.addItem(DesignsClass.UNREPLICATED_DESIGH_WITHOUT_RANDOMIZATION);
        comboBox.addItem(DesignsClass.UNREPLICATED_DESIGH_WITH_RANDOMIZATION);
        comboBox.addItem(DesignsClass.RANDOMIZE_COMPLETE_BLOCK);
        comboBox.addItem(DesignsClass.USER_DEFINED_DESIGN);
        inicio = comboBox.getItemAt(0).toString();
        comboBox.setSelectedItem(comboBox.getItemAt(0));
        comboBox.setSelectedIndex(0);
        TableColumn valueColumn = jTableDesign.getColumnModel().getColumn(1);
        valueColumn.setCellEditor(new DefaultCellEditor(comboBox));
        
        TableColumn designColumn = jTableDesign.getColumnModel().getColumn(1);
        DefaultTableCellRenderer designCellRenderer =  new DefaultTableCellRenderer();
        designCellRenderer.setToolTipText("Choose a design from list");
        designCellRenderer.setBackground(Color.YELLOW);
        designColumn.setCellRenderer(designCellRenderer);    

        TableColumn replicatesColumn = jTableDesign.getColumnModel().getColumn(2);
        DefaultTableCellRenderer replicationCellRenderrer =  new DefaultTableCellRenderer();
        replicationCellRenderrer.setToolTipText("Choose replication number");
        replicationCellRenderrer.setBackground(Color.YELLOW);
        replicatesColumn.setCellRenderer(replicationCellRenderrer);    
        
        TableColumn blockSizeColumn = jTableDesign.getColumnModel().getColumn(3);
        DefaultTableCellRenderer blockCellRenderer =  new DefaultTableCellRenderer();
        blockCellRenderer.setToolTipText("Choose block size");
        blockCellRenderer.setBackground(Color.YELLOW);
        blockSizeColumn.setCellRenderer(blockCellRenderer);            
        
        return inicio;
    }

    public ArrayList<KSValues> getRep2() {
        return rep2;
    }

    public ArrayList<KSValues> getRep3() {
        return rep3;
    }

    public ArrayList<KSValues> getRep4() {
        return rep4;
    }

    
    
    /**
     * 
     * @param jTableDesign 
     */
    public void assignCellEditorAlpha() {
        JComboBox comboBoxRep = new JComboBox();
        for (int i = 1; i < 4; i++) {
            comboBoxRep.addItem(Integer.valueOf(i + 1));
        }
        comboBoxRep.setEnabled(true);
        comboBoxRep.setEditable(false);
        comboBoxRep.setSelectedIndex(0);
        TableColumn repColumn = jTableDesign.getColumnModel().getColumn(2);
        repColumn.setCellEditor(new DefaultCellEditor(comboBoxRep));
    }


    
    
        public boolean alphaIsValidWithOutConstraints(int entries){
        
        rep2 = validaRep2(entries);
        rep3 = validaRep3(entries);
        rep4 = validaRep4(entries);            
        
        if ((rep2.size() > 0) || (rep3.size() > 0) ||(rep4.size() > 0) ) {
           return true;
        }else{
            return false;
        }    
    }
    
        
        
    
    public boolean alphaIsValid(int entries){
        
        rep2 = validaRep2(entries);
        rep3 = validaRep3(entries);
        rep4 = validaRep4(entries);
        
        //imprimeValores(rep2,rep3,rep4);
     
        if ((rep2.size() > 0) || (rep3.size() > 0) ||(rep4.size() > 0) ) {
           return true;
        }else{
            return false;
        }
      
    }
    
    
    
    public void imprimeValores( ArrayList<KSValues> repe2,  ArrayList<KSValues> repe3,  ArrayList<KSValues> repe4){
        for (int i = 0; i < repe2.size(); i++) {
            System.out.println("Entradas validas rep2: k=" + repe2.get(i).getBlockSize() + "   s=" + repe2.get(i).getBlocksNumber());
        }
        System.out.println("----------------------------------------");

        for (int i = 0; i < repe3.size(); i++) {
            System.out.println("Entradas validas rep3: k=" + repe3.get(i).getBlockSize() + "   s=" + repe3.get(i).getBlocksNumber());
        }

        System.out.println("----------------------------------------");
        for (int i = 0; i < repe4.size(); i++) {
            System.out.println("Entradas validas rep4: k=" + repe4.get(i).getBlockSize() + "   s=" + repe4.get(i).getBlocksNumber());
        }
    }
    
    
    
    
    public int assignCellEditorAlpha(int entries) {

        JComboBox comboBoxRep = new JComboBox();
        int selected=0;

        if (rep2.size() > 0) {
            comboBoxRep.addItem(2);
            selected=2;
        }
       
        if (rep3.size() > 0) {
            comboBoxRep.addItem(3);
            if(selected==0){
                selected=3;
            }
        }

        if (rep4.size() > 0) {
            comboBoxRep.addItem(4);
             if(selected==0){
                selected=4;
            }
        }
  
        comboBoxRep.setEnabled(true);
        comboBoxRep.setEditable(false);

        if (comboBoxRep.getItemCount() > 0) {
            comboBoxRep.setSelectedIndex(0);
        }
        TableColumn repColumn = jTableDesign.getColumnModel().getColumn(2);
        repColumn.setCellEditor(new DefaultCellEditor(comboBoxRep));
        return selected;
    }

    /**
     * 
     * @param jTableDesign 
     */
    public void assignCellEditorLattice() {
        JComboBox comboBoxRep = new JComboBox();
        for (int i = 1; i < 3; i++) {
            comboBoxRep.addItem(Integer.valueOf(i + 1));
        }
        comboBoxRep.setEnabled(true);
        comboBoxRep.setEditable(false);
        comboBoxRep.setSelectedIndex(0);
        TableColumn repColumn = jTableDesign.getColumnModel().getColumn(2);
        repColumn.setCellEditor(new DefaultCellEditor(comboBoxRep));
    }

    /**
     * 
     * @param jTableDesign 
     */
    public void assignCellEditorReplicated() {
        JComboBox comboBoxRep = new JComboBox();
        for (int i = 1; i < 20; i++) {
            comboBoxRep.addItem(Integer.valueOf(i + 1));
        }
        comboBoxRep.setEnabled(true);
        comboBoxRep.setEditable(false);
        comboBoxRep.setSelectedIndex(0);
        TableColumn repColumn = jTableDesign.getColumnModel().getColumn(2);
        repColumn.setCellEditor(new DefaultCellEditor(comboBoxRep));
    }

    public void generateBlocksSizeLattice() {
        final JComboBox comboBoxSize = new JComboBox();
        int entries = Integer.parseInt(jTextFieldEntries.getText());
        int con = entries;
        try {
            comboBoxSize.addItem((int) Math.sqrt(entries));
        } catch (NumberFormatException ex) {
            comboBoxSize.removeAllItems();
        }
        comboBoxSize.setEnabled(true);
        comboBoxSize.setEditable(false);
        TableColumn valueColumn = jTableDesign.getColumnModel().getColumn(3);
        valueColumn.setCellEditor(new DefaultCellEditor(comboBoxSize));
    }

    public void generateBlocksSize( int selected) {
        
        final JComboBox comboBoxSize = new JComboBox();
        
        switch (selected) {
            case 2:
                
                for (int i = 0; i < rep2.size(); i++) {
                  comboBoxSize.addItem(rep2.get(i).getBlockSize()); 
                    
                }
       
                
                break;
                case 3:
                    
                      for (int i = 0; i < rep3.size(); i++) {
                  comboBoxSize.addItem(rep3.get(i).getBlockSize()); 
                    
                }
                
                break;
                case 4:
                  for (int i = 0; i < rep4.size(); i++) {
                  comboBoxSize.addItem(rep4.get(i).getBlockSize()); 
                    
                }
                break;
                
        }
                    
       
        
        if(comboBoxSize.getItemCount()==0){
            return;
        }
        
        comboBoxSize.setEnabled(true);
        comboBoxSize.setEditable(false);
        comboBoxSize.setSelectedIndex(0);
        
        TableColumn valueColumn = jTableDesign.getColumnModel().getColumn(3);
        valueColumn.setCellEditor(new DefaultCellEditor(comboBoxSize));
    }

    public void assignCellEditorBlockSize() {
        AlphaDesignsRowEditor alphaRowEditor = new AlphaDesignsRowEditor(jTableDesign, Integer.parseInt(jTextFieldEntries.getText()),rep2,rep3,rep4);
        
        
        TableColumn valueColumn = jTableDesign.getColumnModel().getColumn(3);
        valueColumn.setCellEditor(alphaRowEditor);
        jTextFieldEntries.repaint();
    }

    /**
     * 
     * @param useSameDesignForAll 
     */
    public void useSameDesignForTrials(boolean useSameDesignForAll) {
        if (useSameDesignForAll) {
            Object design = jTableDesign.getValueAt(0, 1);
            Object replicates = jTableDesign.getValueAt(0, 2);
            Object blockSize = jTableDesign.getValueAt(0, 3);
            Object blocksPerReplicate = jTableDesign.getValueAt(0, 4);
            Object userDefinedDesign = jTableDesign.getValueAt(0, 5);
            for (int row = 1; row < jTableDesign.getRowCount(); row++) {
                jTableDesign.setValueAt(design, row, 1);
                jTableDesign.setValueAt(replicates, row, 2);
                jTableDesign.setValueAt(blockSize, row, 3);
                jTableDesign.setValueAt(blocksPerReplicate, row, 4);
                jTableDesign.setValueAt(userDefinedDesign, row, 5);
            }

        }
    }

    public void quitaCellEditors() {
        JTextField jtf = new JTextField();
        TableColumn valueColumn = this.jTableDesign.getColumnModel().getColumn(2);
        valueColumn.setCellEditor(new DefaultCellEditor(jtf));
        valueColumn = this.jTableDesign.getColumnModel().getColumn(3);
        valueColumn.setCellEditor(new DefaultCellEditor(jtf));
        valueColumn = this.jTableDesign.getColumnModel().getColumn(4);
        valueColumn.setCellEditor(new DefaultCellEditor(jtf));
    }

    public void checkDesignTable(int colEditing, int fila, boolean useSameDesignForAll) {
        DesignTableModel designTableModel = (DesignTableModel) jTableDesign.getModel();
        DesignBean designBean = designTableModel.getDesignBean(fila);
        if (designBean.getDesign().equals(DesignsClass.ALFA_DESIGN)) {
            assignCellEditorAlpha(Integer.parseInt(jTextFieldEntries.getText()));
            generateBlocksSize(designBean.getReplications());
            assignCellEditorBlockSize();
            if (colEditing == 1) {
                jTableDesign.setValueAt(null, fila, 2); //repetitions
                jTableDesign.setValueAt(null, fila, 3);
                jTableDesign.setValueAt(null, fila, 4);
                jTableDesign.setValueAt(null, fila, 5);
                return;
            }
            if (colEditing == 2) {
                jTableDesign.setValueAt(null, fila, 3);
                jTableDesign.setValueAt(null, fila, 4);
                jTableDesign.setValueAt(null, fila, 5);
                useSameDesignForTrials(useSameDesignForAll);
                return;
            }
            Object blockSize = this.jTableDesign.getValueAt(fila, 3).toString();
            int entries = Integer.parseInt(this.jTextFieldEntries.getText());
            int size = Integer.parseInt(blockSize.toString());
            this.jTableDesign.setValueAt((entries / size), fila, 4);

            useSameDesignForTrials(useSameDesignForAll);
            return;
        }

        if (designBean.getDesign().equals(DesignsClass.LATICE_DESIGN)) {
            assignCellEditorLattice();
            generateBlocksSizeLattice();
            if (colEditing == 1) {
                jTableDesign.setValueAt(null, fila, 2); //repetitions
                jTableDesign.setValueAt(null, fila, 3);
                jTableDesign.setValueAt(null, fila, 4);
                jTableDesign.setValueAt(null, fila, 5);
                useSameDesignForTrials(useSameDesignForAll);
                return;
            }

            if (colEditing == 2) {
                jTableDesign.setValueAt(null, fila, 3);
                jTableDesign.setValueAt(null, fila, 4);
                jTableDesign.setValueAt(null, fila, 5);
                useSameDesignForTrials(useSameDesignForAll);
                return;
            }

            Object blockSize = this.jTableDesign.getValueAt(fila, 3).toString();
            int entries = Integer.parseInt(this.jTextFieldEntries.getText());
            int size = Integer.parseInt(blockSize.toString());
            this.jTableDesign.setValueAt((entries / size), fila, 4);

            useSameDesignForTrials(useSameDesignForAll);
            return;
        }



        if (designBean.getDesign().equals(DesignsClass.UNREPLICATED_DESIGH_WITHOUT_RANDOMIZATION)) {
            quitaCellEditors();
            this.jTableDesign.setValueAt(1, fila, 2);
            this.jTableDesign.setValueAt(Integer.parseInt(this.jTextFieldEntries.getText()), fila, 3);
            this.jTableDesign.setValueAt(1, fila, 4);
            jTableDesign.setValueAt(null, fila, 5);
            useSameDesignForTrials(useSameDesignForAll);
            return;
        }

        if (designBean.getDesign().equals(DesignsClass.UNREPLICATED_DESIGH_WITH_RANDOMIZATION)) {
            quitaCellEditors();
            this.jTableDesign.setValueAt(1, fila, 2);
            this.jTableDesign.setValueAt(Integer.parseInt(this.jTextFieldEntries.getText()), fila, 3);
            this.jTableDesign.setValueAt(1, fila, 4);
            jTableDesign.setValueAt(null, fila, 5);
            useSameDesignForTrials(useSameDesignForAll);
            return;
        }



        if (designBean.getDesign().equals(DesignsClass.RANDOMIZE_COMPLETE_BLOCK)) {
            quitaCellEditors();
            assignCellEditorReplicated();
            this.jTableDesign.setValueAt(Integer.parseInt(this.jTextFieldEntries.getText()), fila, 3);
            this.jTableDesign.setValueAt(1, fila, 4);
            jTableDesign.setValueAt(null, fila, 5);
            if (colEditing == 1) {
                this.jTableDesign.setValueAt(null, fila, 2);
                useSameDesignForTrials(useSameDesignForAll);
                return;
            }
            useSameDesignForTrials(useSameDesignForAll);

        }
        if (designBean.getDesign().equals(DesignsClass.USER_DEFINED_DESIGN)) {
            designBean.setUserDefinedDesign(FileUtils.openFile());

            if (designBean.getUserDefinedDesign() != null) {

                File userDefinedDesign = designBean.getUserDefinedDesign();

                int[] designValues = getDesignValues(Integer.parseInt(jTableDesign.getValueAt(fila, 0).toString()), userDefinedDesign);

                int rep = designValues[0];
                int block = designValues[1];
                int blockPerReplicate = designValues[3];

                jTableDesign.setValueAt(rep, fila, 2);
                jTableDesign.setValueAt(block, fila, 3);
                jTableDesign.setValueAt(blockPerReplicate, fila, 4);
                jTableDesign.setValueAt(userDefinedDesign, fila, 5);
                useSameDesignForTrials(useSameDesignForAll);
            }
        }

    }

    public int[] getDesignValues(int currentTrial, File fileName) {
        int[] maxValues = {0, 0, 0, 0, 0 ,0};
        int rowTrialCount = 0, blkCounter = 1, blkRepCounter = 1;

        //  String file = OSUtils.getPathRWD() + File.separator + fileName;
        if (!verifyCsv(currentTrial, fileName)) {
            DialogUtil.displayError("No trial " + currentTrial + " in this CSV file.");

        } else {

            try {
                CsvReader csvReader = new CsvReader(fileName.toString());
                csvReader.readHeaders();


                while (csvReader.readRecord()) {

//                    int trial = Integer.valueOf(csvReader.get("TRIAL")).intValue();
//                    int rep = Integer.valueOf(csvReader.get("REP")).intValue();
//                    int block = Integer.valueOf(csvReader.get("BLOCK")).intValue();

                    int trial = ConvertUtils.getValueAsInteger(csvReader.get("TRIAL"));
                    int rep = ConvertUtils.getValueAsInteger(csvReader.get("REP"));
                    int block = ConvertUtils.getValueAsInteger(csvReader.get("BLOCK"));

                    if (trial == currentTrial) {
                        rowTrialCount++;

                        if (maxValues[2] < trial) {
                            maxValues[2] = trial;
                        }
                        if (maxValues[0] < rep) {
                            maxValues[0] = rep;
                            maxValues[3] = blkCounter;
                            blkCounter = 1;
                        } else if (maxValues[0] == rep) {
                            if (maxValues[1] < block) {
                                maxValues[1] = block;
                                maxValues[3] = blkRepCounter;
                                blkRepCounter = 1;
                            } else {
                                blkRepCounter++;
                            }
                        }

                    }
                }

                csvReader.close();

            } catch (FileNotFoundException ex) {
                System.out.println("FILE NOT FOUND. readDATAcsv.\n\t " + ex);

            } catch (IOException e) {
                System.out.println("IO EXCEPTION. readDATAcsv.\n\t " + e);
            }

            maxValues[1] = rowTrialCount / maxValues[0] / maxValues[3];
        }
        return maxValues;
    }

    public static boolean verifyCsv(int currentTrial, File fileName) {
        boolean isValid = false;

        try {
            CsvReader csvReader = new CsvReader(fileName.toString());
            csvReader.readHeaders();


            while (csvReader.readRecord()) {

                //int trial = Integer.valueOf(csvReader.get("TRIAL")).intValue();
                int trial = ConvertUtils.getValueAsInteger(csvReader.get("TRIAL"));
                if (trial == currentTrial) {
                    isValid = true;
                    break;

                }
            }
            csvReader.close();

        } catch (FileNotFoundException ex) {
            System.out.println("FILE NOT FOUND. readDATAcsv.\n\t " + ex);

        } catch (IOException e) {
            System.out.println("IO EXCEPTION. readDATAcsv.\n\t " + e);
        }


        return isValid;
    }

    private ArrayList<KSValues> validaRep2(int entries) {
        ArrayList<KSValues> repet2 = new ArrayList();
        int con = entries;
        for (int i = entries; i > 0; i--) {
            if ((entries % i) == 0) {
                int s = entries / i;
                if (KisLessThanS(i, s)) {
                   // if (cumpleConArrays(i, s)) {
                        KSValues valoresKS = new KSValues();
                        valoresKS.setBlockSize(i);
                        valoresKS.setBlocksNumber(s);
                        repet2.add(valoresKS);
                   // }
                }
            }
        }
        return repet2;
    }

    public boolean KisLessThanS(int k, int s) {
        if (k <= s) {
            return true;
        } else {
            return false;
        }
    }

    private ArrayList<KSValues> validaRep3(int entries) {
        ArrayList<KSValues> repet3 = new ArrayList();
        for (int i = entries; i > 0; i--) {
            if ((entries % i) == 0) {
                int s = entries / i;

                if (isImpar(s)) {
                    if (KisLessThanS(i, s)) {
                      //  if (cumpleConArrays(i, s)) {
                            KSValues valoresKS = new KSValues();
                            valoresKS.setBlockSize(i);
                            valoresKS.setBlocksNumber(s);
                            repet3.add(valoresKS);
                       // }
                    }

                } else {

                    if (KisLessThanSminusOne(i, s)) {
                      //  if (cumpleConArrays(i, s)) {
                            KSValues valoresKS = new KSValues();
                            valoresKS.setBlockSize(i);
                            valoresKS.setBlocksNumber(s);
                            repet3.add(valoresKS);
                      //  }
                    }


                }
            }
        }
        return repet3;
    }

    private ArrayList<KSValues> validaRep4(int entries) {
        ArrayList<KSValues> repet4 = new ArrayList();
        for (int i = entries; i > 0; i--) {
            if ((entries % i) == 0) {
                int s = entries / i;

                if (isImparButNotMultipleOfThree(s)) {
                    if (KisLessThanS(i, s)) {
                       // if (cumpleConArrays(i, s)) {
                            KSValues valoresKS = new KSValues();
                            valoresKS.setBlockSize(i);
                            valoresKS.setBlocksNumber(s);
                            repet4.add(valoresKS);
                      //  }

                    }

                }
            }
        }
        return repet4;
    }

    private boolean isImpar(int s) {
        if (s % 2 == 0) {
            return false;
        } else {
            return true;
        }
    }

    private boolean KisLessThanSminusOne(int k, int s) {
        if (k <= s - 1) {
            return true;
        } else {
            return false;
        }
    }

    private boolean isImparButNotMultipleOfThree(int s) {
        if ((s % 2 != 0) && (s % 3 != 0)) {
            return true;
        } else {
            return false;
        }
    }

    private boolean cumpleConArrays(int k, int s) {
        boolean cumple = false;

        switch (s) {

            case 5:
                if(s==k){
                return true;
                }else{
                 return false;    
                }
                
            case 6:
                  if(s==k){
                return true;
                }else{
                 return false;    
                }
            case 7:
                  if(s==k){
                return true;
                }else{
                 return false;    
                }
            case 8:
                  if(s==k){
                return true;
                }else{
                 return false;    
                }
            case 9:
                  if(s==k){
                return true;
                }else{
                 return false;    
                }
            case 10:
                 if(s==k){
                return true;
                }else{
                 return false;    
                }

            case 11:
                if (k == 9) {
                    return true;
                } else {
                    return false;
                }

            case 12:
                if (k == 8) {
                    return true;
                } else {
                    return false;
                }

            case 13:
                if (k == 7) {
                    return true;
                } else {
                    return false;
                }

            case 14:

                if (k == 7) {
                    return true;
                } else {
                    return false;
                }

            case 15:

                if (k == 6) {
                    return true;
                } else {
                    return false;
                }
        }

        return cumple;
    }
}
