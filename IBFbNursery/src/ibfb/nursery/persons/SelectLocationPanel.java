package ibfb.nursery.persons;

import ibfb.nursery.naming.NamingConvention;
import java.util.List;
import org.cimmyt.cril.ibwb.api.AppServicesProxy;
import org.cimmyt.cril.ibwb.domain.Location;

public class SelectLocationPanel extends javax.swing.JPanel {

    private int currentLocid = 0;
    private Location currentLocation = new Location(false);
    private Location currentProvince = new Location(false);
    List<Location> allLocation = null;
    List<Location> countryList = null;
    List<Location> provinceList = null;
    List<Location> districtList = null;
    private String namingConvention;
    private boolean onlyShowInventoryLocations;

    /**
     * Creates new form SelectLocationPanel
     */
    public SelectLocationPanel() {
        namingConvention = NamingConvention.OTHER_CROPS;
        onlyShowInventoryLocations = false;
        initComponents();
        initAllLocations();
        initCountryBox();
    }
    
     public SelectLocationPanel(boolean onlyShowInventoryLocations) {
        namingConvention = NamingConvention.OTHER_CROPS;
        this.onlyShowInventoryLocations = onlyShowInventoryLocations;
        initComponents();
        initAllLocations();
        initCountryBox();
        assignInventoryLocations();
    }

    public void assignNamingConvention(String namingConvention) {
        this.namingConvention = namingConvention;
        if (!namingConvention.equals(NamingConvention.OTHER_CROPS)) {
            lblDistrict.setVisible(false);
            cboDistrict.setVisible(false);
        }
    }

    private void assignInventoryLocations() {
        if (onlyShowInventoryLocations) {
            lblState.setVisible(false);
            cboState.setVisible(false);
            lblDistrict.setVisible(false);
            cboDistrict.setVisible(false);
            lblCountry.setText("Storage or Seed stock Location");
        }
    }

    private void initCountryBox() {
        Location locationFilter = new Location(true);

        if (onlyShowInventoryLocations) {
            locationFilter.setLtype(Location.LTYPE_STORAGE_SEED_STOCK_LOCATION);
        } else {
            locationFilter.setLtype(Location.LTYPE_COUNTRY);
        }
        locationFilter.setLrplce(0);

        countryList = AppServicesProxy.getDefault().appServices().getListLocation(locationFilter, 0, 0, false);
        SortedComboBoxModel cboCountryModel = new SortedComboBoxModel();

        for (Location country : countryList) {
            if (country.getLocid() == 0) {
                if (onlyShowInventoryLocations) {
                    country.setLname("- Choose Location -");
                } else {
                    country.setLname("- Choose Country -");
                }
            }
            if (!onlyShowInventoryLocations) {
                if (country.getCntryid().equals(country.getLocid())) {
                    cboCountryModel.addElement(country.getLname());
                }
            } else {
                 cboCountryModel.addElement(country.getLname());
            }

        }
        cboCountry.setModel(cboCountryModel);
        if (cboCountry.getItemCount() > 0) {
            cboCountry.setSelectedIndex(0);
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        chosenLocationIDLabel = new javax.swing.JLabel();
        chosenLocationId = new javax.swing.JTextField();
        chosenLocation = new javax.swing.JTextField();
        chosenLocationLabel = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        lblDistrict = new javax.swing.JLabel();
        lblCountry = new javax.swing.JLabel();
        cboState = new javax.swing.JComboBox();
        cboDistrict = new javax.swing.JComboBox();
        cboCountry = new javax.swing.JComboBox();
        lblState = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(org.openide.util.NbBundle.getMessage(SelectLocationPanel.class, "SelectLocationPanel.jPanel1.border.title"))); // NOI18N

        chosenLocationIDLabel.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        chosenLocationIDLabel.setText(org.openide.util.NbBundle.getMessage(SelectLocationPanel.class, "SelectLocationPanel.chosenLocationIDLabel.text")); // NOI18N

        chosenLocationId.setEditable(false);
        chosenLocationId.setText(org.openide.util.NbBundle.getMessage(SelectLocationPanel.class, "SelectLocationPanel.chosenLocationId.text")); // NOI18N
        chosenLocationId.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chosenLocationIdActionPerformed(evt);
            }
        });

        chosenLocation.setEditable(false);
        chosenLocation.setText(org.openide.util.NbBundle.getMessage(SelectLocationPanel.class, "SelectLocationPanel.chosenLocation.text")); // NOI18N

        chosenLocationLabel.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        chosenLocationLabel.setText(org.openide.util.NbBundle.getMessage(SelectLocationPanel.class, "SelectLocationPanel.chosenLocationLabel.text")); // NOI18N

        jButton2.setText(org.openide.util.NbBundle.getMessage(SelectLocationPanel.class, "SelectLocationPanel.jButton2.text")); // NOI18N
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(32, 32, 32)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(chosenLocationLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(chosenLocationIDLabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(chosenLocationId, javax.swing.GroupLayout.DEFAULT_SIZE, 237, Short.MAX_VALUE)
                    .addComponent(chosenLocation))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton2)
                .addGap(22, 22, 22))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(1, 1, 1)
                        .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, 69, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(chosenLocationIDLabel)
                            .addComponent(chosenLocationId, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(chosenLocationLabel)
                            .addComponent(chosenLocation, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(17, Short.MAX_VALUE))
        );

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(org.openide.util.NbBundle.getMessage(SelectLocationPanel.class, "SelectLocationPanel.jPanel3.border.title"))); // NOI18N

        lblDistrict.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblDistrict.setText(org.openide.util.NbBundle.getMessage(SelectLocationPanel.class, "SelectLocationPanel.lblDistrict.text")); // NOI18N

        lblCountry.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblCountry.setText(org.openide.util.NbBundle.getMessage(SelectLocationPanel.class, "SelectLocationPanel.lblCountry.text")); // NOI18N

        cboState.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cboStateItemStateChanged(evt);
            }
        });

        cboDistrict.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cboDistrictItemStateChanged(evt);
            }
        });

        cboCountry.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cboCountryItemStateChanged(evt);
            }
        });

        lblState.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblState.setText(org.openide.util.NbBundle.getMessage(SelectLocationPanel.class, "SelectLocationPanel.lblState.text")); // NOI18N

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ibfb/nursery/images/location.png"))); // NOI18N
        jLabel1.setText(org.openide.util.NbBundle.getMessage(SelectLocationPanel.class, "SelectLocationPanel.jLabel1.text")); // NOI18N
        jLabel1.setToolTipText(org.openide.util.NbBundle.getMessage(SelectLocationPanel.class, "SelectLocationPanel.jLabel1.toolTipText")); // NOI18N

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(lblState, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblDistrict, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblCountry, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(cboState, javax.swing.GroupLayout.Alignment.LEADING, 0, 267, Short.MAX_VALUE)
                    .addComponent(cboCountry, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(cboDistrict, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 38, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel3Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(cboCountry, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblCountry))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(cboState, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblState))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(cboDistrict, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblDistrict))))
                .addGap(12, 12, 12))
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(27, 27, 27)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void cboCountryItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cboCountryItemStateChanged
        countryChange();
    }//GEN-LAST:event_cboCountryItemStateChanged

    private void cboStateItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cboStateItemStateChanged
        stateChange();
    }//GEN-LAST:event_cboStateItemStateChanged

    private void cboDistrictItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cboDistrictItemStateChanged
        districtChange();
    }//GEN-LAST:event_cboDistrictItemStateChanged

    private void chosenLocationIdActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chosenLocationIdActionPerformed
        getLocationById();
    }//GEN-LAST:event_chosenLocationIdActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        clearFields(0);
        initAllLocations();
        initCountryBox();
    }//GEN-LAST:event_jButton2ActionPerformed
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox cboCountry;
    private javax.swing.JComboBox cboDistrict;
    private javax.swing.JComboBox cboState;
    private javax.swing.JTextField chosenLocation;
    private javax.swing.JLabel chosenLocationIDLabel;
    private javax.swing.JTextField chosenLocationId;
    private javax.swing.JLabel chosenLocationLabel;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JLabel lblCountry;
    private javax.swing.JLabel lblDistrict;
    private javax.swing.JLabel lblState;
    // End of variables declaration//GEN-END:variables

    private void countryChange() {
        String chosenCountry = cboCountry.getSelectedItem().toString();
        currentLocation = getLocationDetails(countryList, chosenCountry);

        if (currentLocation.getLocid() == 0) {
            chosenLocation.setText("");
            chosenLocationId.setText("");
        } else {
            chosenLocation.setText(currentLocation.getLname().toUpperCase());
            chosenLocationId.setText(currentLocation.getLocid().toString());

            loadState(currentLocation.getLocid());

        }
    }

    private void loadState(Integer locid) {
        if (namingConvention.equals(NamingConvention.OTHER_CROPS)) {
            loadStatesOtherCrops(locid);
        } else {
            loadStatesForCimmyt(locid);
        }

    }

    private void loadStatesOtherCrops(Integer locid) {
        Location locationFilter = new Location(true);

        locationFilter.setLtype(406);
        locationFilter.setLrplce(0);
        locationFilter.setCntryid(locid);

        SortedComboBoxModel cboStateModel = new SortedComboBoxModel();
        SortedComboBoxModel cboDistrictModel = new SortedComboBoxModel();

        provinceList = AppServicesProxy.getDefault().appServices().getListLocation(locationFilter, 0, 0, false);

        if (provinceList.isEmpty()) {
            cboStateModel.addElement("-No States/Provinces for " + currentLocation.getLname() + "-");
        } else {
            for (Location province : provinceList) {

                cboStateModel.addElement(province.getLname());
            }
            cboStateModel.addElement("-Choose State/Province-");
        }
        cboState.setModel(cboStateModel);
        cboState.setSelectedIndex(0);

        cboDistrictModel.removeAllElements();
        cboDistrict.setModel(cboDistrictModel);
    }

    private void loadStatesForCimmyt(Integer locid) {


        SortedComboBoxModel cboStateModel = new SortedComboBoxModel();
        SortedComboBoxModel cboDistrictModel = new SortedComboBoxModel();

        provinceList = AppServicesProxy.getDefault().appServices().getLocationsByCountryLocidRange(locid, 8000, 10000);

        if (provinceList.isEmpty()) {
            cboStateModel.addElement("-No States/Provinces for " + currentLocation.getLname() + "-");
        } else {
            for (Location province : provinceList) {

                cboStateModel.addElement(province.getLname());
            }
            cboStateModel.addElement("-Choose State/Province-");
        }
        cboState.setModel(cboStateModel);
        cboState.setSelectedIndex(0);

        cboDistrictModel.removeAllElements();
        cboDistrict.setModel(cboDistrictModel);
    }

    private void stateChange() {

        String chosenState = cboState.getSelectedItem().toString();
        currentLocation = getLocationDetails(provinceList, chosenState);

        if (currentLocation != null) {
            if (currentLocation.getLocid() != 0) {
                chosenLocation.setText(currentLocation.getLname());
                chosenLocationId.setText(currentLocation.getLocid().toString());
                if (namingConvention.equals(NamingConvention.OTHER_CROPS)) {
                    loadDistrict(currentLocation.getCntryid(), currentLocation.getLocid());
                }
            }
        }


    }

    private void loadDistrict(Integer cntryid, Integer locid) {

        Location locationFilter = new Location(true);
        locationFilter.setLrplce(0);
        locationFilter.setCntryid(cntryid);
        locationFilter.setSnl1id(locid);

        SortedComboBoxModel cboDistrictModel = new SortedComboBoxModel();

        districtList = AppServicesProxy.getDefault().appServices().getListLocation(locationFilter, 0, 0, false);

        if (districtList.isEmpty()) {
            cboDistrictModel.addElement("-No entries for " + currentLocation.getLname() + "-");
        } else {
            for (Location loc : districtList) {
                if (loc.getLocid().intValue() != loc.getSnl1id().intValue()) {
                    cboDistrictModel.addElement(loc.getLname());
                }
            }
            if (cboDistrictModel.getSize() > 0) {
                cboDistrictModel.addElement("-Choose District/City/Town-");
            } else {
                cboDistrictModel.addElement("-No entries for " + currentLocation.getLname() + "-");
            }
        }
        cboDistrict.setModel(cboDistrictModel);
        cboDistrict.setSelectedIndex(0);
    }

    private void districtChange() {
        String chosenDistrict = cboDistrict.getSelectedItem().toString();
        currentLocation = getLocationDetails(districtList, chosenDistrict);

        if (currentLocation != null) {
            if (currentLocation.getLocid() != 0) {
                chosenLocation.setText(currentLocation.getLname());
                chosenLocationId.setText(currentLocation.getLocid().toString());
            }
        }
    }

    private void initAllLocations() {
        Location locationFilter = new Location(true);
        locationFilter.setLrplce(0);

        if (onlyShowInventoryLocations) {
            locationFilter.setLtype(Location.LTYPE_STORAGE_SEED_STOCK_LOCATION);
        }

        allLocation = AppServicesProxy.getDefault().appServices().getListLocation(locationFilter, 0, 0, false);
        SortedComboBoxModel cboLocationModel = new SortedComboBoxModel();

        for (Location location : allLocation) {
            if (!location.getLname().startsWith("-")) {
                cboLocationModel.addElement(location.getLname());
            }
        }

    }

    private void getLocationById() {
        if (!chosenLocationId.getText().isEmpty()) {
            int chosenLocId = Integer.parseInt(chosenLocationId.getText());
            currentLocation = getLocationDetails(allLocation, chosenLocId);
            System.out.println("\n current location of inputted loc id is " + currentLocation.getLname());
            if (currentLocation != null) {
                if (currentLocation.getLocid() != 0) {
                    chosenLocation.setText(currentLocation.getLname());
                    chosenLocationId.setText(currentLocation.getLocid().toString());
                }
            }
        }
    }

    private Location getLocationDetails(List<Location> list, String loctn) {
        Location location = null;
        for (Location loc : list) {
            if (loc.getLname().equals(loctn)) {
                location = loc;
            }
        }
        return location;
    }

    private Location getLocationDetails(List<Location> list, int locid) {
        Location location = null;
        for (Location loc : list) {
            if (loc.getLocid().intValue() == locid) {
                location = loc;
            }
        }
        return location;
    }

    private void clearFields(int scope) {
        switch (scope) {
            case 0:
                cboCountry.setModel(new SortedComboBoxModel());
            case 1:
                cboState.setModel(new SortedComboBoxModel());
            case 2:
                cboDistrict.setModel(new SortedComboBoxModel());
            case 4:
                chosenLocation.setText("");
                chosenLocationId.setText("");
                break;
            default:
                System.out.println("Unknown scope code. nothing is cleared.");
                break;
        }
    }

    public Integer getLocationId() {
        Integer locationId = null;

        if (!chosenLocationId.getText().trim().isEmpty()) {
            locationId = Integer.parseInt(chosenLocationId.getText());
        }

        return locationId;
    }

    public String getLocationName() {
        String locationName = "";

        if (!chosenLocation.getText().trim().isEmpty()) {
            locationName = chosenLocation.getText();
        }

        return locationName;
    }

    public Location getSelectedLocation() {
        getLocationById();
        return currentLocation;
    }

    public boolean isOlyShowInventoryLocations() {
        return onlyShowInventoryLocations;
    }

    public void setOnlyShowInventoryLocations(boolean selectInventoryLocations) {
        this.onlyShowInventoryLocations = selectInventoryLocations;
        assignInventoryLocations();
    }
}
