/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ibfb.studyeditor.core.model;

import ibfb.domain.core.DesignBean;
import ibfb.studyeditor.designs.DesignsClass;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import javax.swing.event.TableModelListener;
import javax.swing.table.AbstractTableModel;
import org.apache.log4j.Logger;
import org.openide.util.NbBundle;

/**
 * Table model use in Design Table
 * @author TMSANCHEZ
 */
public class DesignTableModel extends AbstractTableModel {

    private static Logger log = Logger.getLogger(DesignTableModel.class);
    private static final String TRIAL = NbBundle.getMessage(DesignTableModel.class, "design.trial");
    private static final String DESIGN = NbBundle.getMessage(DesignTableModel.class, "design.design");
    private static final String REPLICATES = NbBundle.getMessage(DesignTableModel.class, "design.replicates");
    private static final String BLOCK_SIZE = NbBundle.getMessage(DesignTableModel.class, "design.blocksize");
    private static final String BLOCK_PER_REPLICATE = NbBundle.getMessage(DesignTableModel.class, "design.blockperreplicate");
    private static final String USER_DEFINED_DESIGN = NbBundle.getMessage(DesignTableModel.class, "design.userdefineddesign");
    private static final String[] columnNames = {TRIAL, DESIGN, REPLICATES, BLOCK_SIZE, BLOCK_PER_REPLICATE, USER_DEFINED_DESIGN};
    /**
     * Design items for each TRIAL
     */
    private List<DesignBean> designList = new ArrayList<DesignBean>();

    public DesignTableModel() {
    }

    public DesignTableModel(List<DesignBean> designList) {
        this.designList = designList;
    }

    @Override
    public int getRowCount() {
        return designList.size();
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    @Override
    public String getColumnName(int column) {
        return columnNames[column];
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        DesignBean designBean = designList.get(rowIndex);
        switch (columnIndex) {
            // trial column is never editable
            case 0:
                return false;
            case 1:
                return true;
            // block per replicate is never editable   
            case 2:
                if (designBean.getDesign().equals(DesignsClass.UNREPLICATED_DESIGH_WITHOUT_RANDOMIZATION)
                        || designBean.getDesign().equals(DesignsClass.UNREPLICATED_DESIGH_WITH_RANDOMIZATION)) {
                    return false;
                } else {
                    return true;
                }
            case 3:
                if (designBean.getDesign().equals(DesignsClass.UNREPLICATED_DESIGH_WITHOUT_RANDOMIZATION)
                        || designBean.getDesign().equals(DesignsClass.UNREPLICATED_DESIGH_WITH_RANDOMIZATION)) {
                    return false;
                } else {
                    return true;
                }
            case 4:
                return false;
            case 5:
                if (designBean.getDesign().equals(DesignsClass.USER_DEFINED_DESIGN)) {
                    return true;
                } else {
                    return false;
                }
            default:
                return true;
        }

    }

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        DesignBean design = designList.get(rowIndex);

        switch (columnIndex) {
            case 0:
                design.setTrialNumber((Integer) aValue);
                break;
            case 1:
                design.setDesign((String) aValue);
                break;
            case 2:
                if (aValue instanceof String) {
                    design.setReplications(Integer.parseInt(((String.valueOf(aValue)))));
                } else if (aValue instanceof Integer) {
                    design.setReplications((Integer) aValue);
                } else {
                }
                break;
            case 3:
                if (aValue instanceof String) {
                    design.setBlockSize(Integer.parseInt(((String.valueOf(aValue)))));
                } else if (aValue instanceof Integer) {
                    design.setBlockSize((Integer) aValue);
                } else {
                }
                break;
            case 4:
                design.setBlocksPerReplicate((Integer) aValue);
                break;
            case 5:
                File file = null;
                if (aValue != null) {
                    if (aValue instanceof String) {
                        file = (new File(String.valueOf(aValue)));
                    } else if (aValue instanceof File) {
                        file = (File) aValue;
                    }
                }
                design.setUserDefinedDesign(file);
                break;
        }

        fireTableCellUpdated(rowIndex, columnIndex);
        fireTableDataChanged();
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        DesignBean design = designList.get(rowIndex);

        switch (columnIndex) {
            case 0:
                return design.getTrialNumber();
            case 1:
                return design.getDesign();
            case 2:
                return design.getReplications();
            case 3:
                return design.getBlockSize();
            case 4:
                return design.getBlocksPerReplicate();
            case 5:
                return design.getUserDefinedDesign();
            default:
                return "";
        }
    }

    public List<DesignBean> getDesignList() {
        return designList;
    }

    public void setDesignList(List<DesignBean> designList) {
        this.designList = designList;
        fireTableStructureChanged();
    }

    /**
     * Adds a design bean to collection
     * @param designBean 
     */
    public void addDesignBean(DesignBean designBean) {
        designList.add(designBean);
    }

    /**
     * Return a DesignBean from list according to rowIndex
     * @param rowIndex
     * @return 
     */
    public DesignBean getDesignBean(int rowIndex) {
        log.info("Requested for rowIndex " + rowIndex);
        log.info("designList.size() " + designList.size());
        for (DesignBean bean : designList) {
            log.info("bean " + bean.toString());
        }

        if (designList.isEmpty()) {
            return null;
        } else {
            return designList.get(rowIndex);
        }
    }

    @Override
    public void addTableModelListener(TableModelListener l) {
        super.addTableModelListener(l);
    }
}
