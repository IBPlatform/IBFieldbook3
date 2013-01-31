
package ibfb.nursery.importwizard;

import ibfb.nursery.models.GermplasmEntriesTableModel;
import ibfb.nursery.models.ObservationsTableModel;
import ibfb.nursery.utils.CSVOziel;
import java.awt.Component;
import java.awt.Dialog;
import java.io.File;
import java.text.MessageFormat;
import javax.swing.JComponent;
import javax.swing.JList;
import javax.swing.JTable;
import org.cimmyt.cril.ibwb.commongui.DialogUtil;
import org.openide.DialogDisplayer;
import org.openide.NotifyDescriptor;
import org.openide.WizardDescriptor;
import org.openide.util.NbBundle;

public class ImportData {

    private WizardDescriptor.Panel[] panels;
    private JTable observationsTable;
    private JTable germplasmTable;
    private CSVOziel csv;
    private int instances;
    private File myFile;

     @SuppressWarnings("unchecked")
    public ImportData(JTable observationsTable, CSVOziel csv) {
        this.observationsTable = observationsTable;
        this.csv = csv;
    }

        public ImportData(JTable germplasmTabla) {
        this.germplasmTable = germplasmTabla;
  
    }
     
    public ImportData(JTable observationsTable, CSVOziel csv, int instan) {
        this.observationsTable = observationsTable;
        this.csv = csv;
        this.instances=instan;
    }
     
    public void launchWizard() {
        WizardDescriptor wizardDescriptor = new WizardDescriptor(getPanels());
        // {0} will be replaced by WizardDesriptor.Panel.getComponent().getName()
        wizardDescriptor.setTitleFormat(new MessageFormat("{0}"));
        wizardDescriptor.setTitle(NbBundle.getMessage(ImportData.class, "ImportData.title"));
        Dialog dialog = DialogDisplayer.getDefault().createDialog(wizardDescriptor);
        
        dialog.setVisible(true);
        dialog.toFront();
        boolean cancelled = wizardDescriptor.getValue() != WizardDescriptor.FINISH_OPTION;
        
        
        if (!cancelled) {
    
            
            ImportWizardWizardPanel1 iwvp = (ImportWizardWizardPanel1) panels[0];
            ImportWizardVisualPanel1 visualPanel = (ImportWizardVisualPanel1) iwvp.getComponent();
            
              this.myFile=iwvp.getMyFile();
            


            if (visualPanel.getjRadioButtonToFieldlog().isSelected()) {
                
                importFromFieldroid( this.myFile);
            } else {
                importFromExcel( this.myFile);
            }
        }
    }

    /**
     * Initialize panels representing individual wizard's steps and sets
     * various properties for them influencing wizard appearance.
     */
    private WizardDescriptor.Panel[] getPanels() {
        if (panels == null) {
            panels = new WizardDescriptor.Panel[]{
                new ImportWizardWizardPanel1()
            };
            String[] steps = new String[panels.length];
            for (int i = 0; i < panels.length; i++) {
                Component c = panels[i].getComponent();
                // Default step name to component name of panel. Mainly useful
                // for getting the name of the target chooser to appear in the
                // list of steps.
                steps[i] = c.getName();
                if (c instanceof JComponent) { // assume Swing components
                    JComponent jc = (JComponent) c;
                    // Sets step number of a component
                    // TODO if using org.openide.dialogs >= 7.8, can use WizardDescriptor.PROP_*:
                    jc.putClientProperty("WizardPanel_contentSelectedIndex", new Integer(i));
                    // Sets steps names for a panel
                    jc.putClientProperty("WizardPanel_contentData", steps);
                    // Turn on subtitle creation on each step
                    jc.putClientProperty("WizardPanel_autoWizardStyle", Boolean.TRUE);
                    // Show steps on the left side with the image on the background
                    jc.putClientProperty("WizardPanel_contentDisplayed", Boolean.TRUE);
                    // Turn on numbering of all steps
                    jc.putClientProperty("WizardPanel_contentNumbered", Boolean.TRUE);
                }
            }
        }
        return panels;
    }

       public void importFromFieldroid(File fileToImport) {
        try {

            CSVOziel csvOziel = new CSVOziel(this.observationsTable, new JList());

            if (!csvOziel.isValid(fileToImport)) {

                DialogUtil.displayError("The file is invalid");
                return;
            }
    
            csvOziel.readDATAnew(fileToImport);
  
            DialogUtil.displayInfo(ImportData.class, "ImportData.importsuccess");
        } catch (Exception e) {
           
            DialogUtil.displayWarning(ImportData.class, "ImportData.importfail");

        }
    }

    public void importFromExcel(File selectedFile) {
        System.out.println("Starting IMPORT FROM EXCEL");
        ExcelNurseryReader myReader = new ExcelNurseryReader();
        myReader.setFileName(selectedFile.toString());
        ObservationsTableModel tableModel = (ObservationsTableModel) observationsTable.getModel();
        myReader.setModel(tableModel);
        myReader.setInstances(instances);
        myReader.readExcelFile();


    }
    
     public void importFromCrossInfoToGermplasm(File selectedFile) {
        System.out.println("Starting IMPORT FROM CROSS INFO TO GERMPLASM");
        ExcelNurseryReader myReader = new ExcelNurseryReader();
        myReader.setFileName(selectedFile.toString());
        GermplasmEntriesTableModel tableModel=(GermplasmEntriesTableModel) germplasmTable.getModel();               
        myReader.setGermplasmModel(tableModel);
        myReader.readExcelFileCrossInfoToGermplasm();
    }
    
}
