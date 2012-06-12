package ibfb.germplasmlist.location;

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

    /**
     * Creates new form SelectLocationPanel
     */
    public SelectLocationPanel() {
        initComponents();
        initAllLocations();
        initCountryBox();
    }

    private void initCountryBox() {
        Location locationFilter = new Location(true);
        locationFilter.setLtype(405);
        locationFilter.setLrplce(0);

        countryList = AppServicesProxy.getDefault().appServices().getListLocation(locationFilter, 0, 0, false);
        SortedComboBoxModel cboCountryModel = new SortedComboBoxModel();

        for (Location country : countryList) {
            if (country.getLocid() == 0) {
                country.setLname("- Choose Country -");
            }
            if (country.getCntryid().equals(country.getLocid())) {
                cboCountryModel.addElement(country.getLname());
            }

        }
        cboCountry.setModel(cboCountryModel);
        cboCountry.setSelectedIndex(0);
    }


    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        cboState = new javax.swing.JComboBox();
        lblDistrict = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        chosenLocationIDLabel = new javax.swing.JLabel();
        chosenLocationId = new javax.swing.JTextField();
        chosenLocation = new javax.swing.JTextField();
        chosenLocationLabel = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();
        lblCountry = new javax.swing.JLabel();
        cboCountry = new javax.swing.JComboBox();
        lblState = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        cboDistrict = new javax.swing.JComboBox();
        cboAllLocation = new javax.swing.JComboBox();

        cboState.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cboStateItemStateChanged(evt);
            }
        });

        lblDistrict.setText(org.openide.util.NbBundle.getMessage(SelectLocationPanel.class, "SelectLocationPanel.lblDistrict.text")); // NOI18N

        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        chosenLocationIDLabel.setText(org.openide.util.NbBundle.getMessage(SelectLocationPanel.class, "SelectLocationPanel.chosenLocationIDLabel.text")); // NOI18N

        chosenLocationId.setText(org.openide.util.NbBundle.getMessage(SelectLocationPanel.class, "SelectLocationPanel.chosenLocationId.text")); // NOI18N
        chosenLocationId.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chosenLocationIdActionPerformed(evt);
            }
        });

        chosenLocation.setText(org.openide.util.NbBundle.getMessage(SelectLocationPanel.class, "SelectLocationPanel.chosenLocation.text")); // NOI18N

        chosenLocationLabel.setText(org.openide.util.NbBundle.getMessage(SelectLocationPanel.class, "SelectLocationPanel.chosenLocationLabel.text")); // NOI18N

        jButton2.setText(org.openide.util.NbBundle.getMessage(SelectLocationPanel.class, "SelectLocationPanel.jButton2.text")); // NOI18N
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        org.jdesktop.layout.GroupLayout jPanel1Layout = new org.jdesktop.layout.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(jPanel1Layout.createSequentialGroup()
                        .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                            .add(chosenLocationIDLabel)
                            .add(chosenLocationLabel))
                        .add(32, 32, 32)
                        .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                            .add(chosenLocation, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 276, Short.MAX_VALUE)
                            .add(chosenLocationId, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 276, Short.MAX_VALUE))
                        .addContainerGap())
                    .add(org.jdesktop.layout.GroupLayout.TRAILING, jPanel1Layout.createSequentialGroup()
                        .add(jButton2)
                        .add(125, 125, 125))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(chosenLocationIDLabel)
                    .add(chosenLocationId, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.UNRELATED)
                .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(chosenLocationLabel)
                    .add(chosenLocation, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED, 8, Short.MAX_VALUE)
                .add(jButton2))
        );

        lblCountry.setText(org.openide.util.NbBundle.getMessage(SelectLocationPanel.class, "SelectLocationPanel.lblCountry.text")); // NOI18N

        cboCountry.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cboCountryItemStateChanged(evt);
            }
        });

        lblState.setText(org.openide.util.NbBundle.getMessage(SelectLocationPanel.class, "SelectLocationPanel.lblState.text")); // NOI18N

        jLabel4.setText(org.openide.util.NbBundle.getMessage(SelectLocationPanel.class, "SelectLocationPanel.jLabel4.text")); // NOI18N

        cboDistrict.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cboDistrictItemStateChanged(evt);
            }
        });

        cboAllLocation.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cboAllLocationItemStateChanged(evt);
            }
        });

        org.jdesktop.layout.GroupLayout jPanel2Layout = new org.jdesktop.layout.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .add(jPanel2Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(jPanel1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(org.jdesktop.layout.GroupLayout.TRAILING, jPanel2Layout.createSequentialGroup()
                        .add(jPanel2Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                            .add(org.jdesktop.layout.GroupLayout.TRAILING, lblState, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 120, Short.MAX_VALUE)
                            .add(lblDistrict, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 120, Short.MAX_VALUE)
                            .add(lblCountry))
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(jPanel2Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                            .add(cboCountry, 0, 272, Short.MAX_VALUE)
                            .add(cboDistrict, 0, 272, Short.MAX_VALUE)
                            .add(cboState, 0, 272, Short.MAX_VALUE)
                            .add(cboAllLocation, 0, 272, Short.MAX_VALUE))
                        .add(48, 48, 48))
                    .add(jPanel2Layout.createSequentialGroup()
                        .add(jLabel4, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 115, Short.MAX_VALUE)
                        .add(330, 330, 330)))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .add(jPanel2Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(cboCountry, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(lblCountry))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jPanel2Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(cboState, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(lblState))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jPanel2Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(cboDistrict, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(lblDistrict))
                .add(30, 30, 30)
                .add(jPanel2Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(cboAllLocation, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(jLabel4))
                .add(34, 34, 34)
                .add(jPanel1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        org.jdesktop.layout.GroupLayout layout = new org.jdesktop.layout.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(layout.createSequentialGroup()
                .add(8, 8, 8)
                .add(jPanel2, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(layout.createSequentialGroup()
                .addContainerGap(org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .add(jPanel2, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
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

    private void cboAllLocationItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cboAllLocationItemStateChanged
        locationChange();
    }//GEN-LAST:event_cboAllLocationItemStateChanged

    private void chosenLocationIdActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chosenLocationIdActionPerformed
        getLocationById();
    }//GEN-LAST:event_chosenLocationIdActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        clearFields(0);
        initAllLocations();
        initCountryBox();
    }//GEN-LAST:event_jButton2ActionPerformed
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox cboAllLocation;
    private javax.swing.JComboBox cboCountry;
    private javax.swing.JComboBox cboDistrict;
    private javax.swing.JComboBox cboState;
    private javax.swing.JTextField chosenLocation;
    private javax.swing.JLabel chosenLocationIDLabel;
    private javax.swing.JTextField chosenLocationId;
    private javax.swing.JLabel chosenLocationLabel;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
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

    private void stateChange() {
        String chosenState = cboState.getSelectedItem().toString();
        currentLocation = getLocationDetails(provinceList, chosenState);

        if (currentLocation != null) {
            if (currentLocation.getLocid() != 0) {
                chosenLocation.setText(currentLocation.getLname());
                chosenLocationId.setText(currentLocation.getLocid().toString());
                loadDistrict(currentLocation.getCntryid(), currentLocation.getLocid());
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

        allLocation = AppServicesProxy.getDefault().appServices().getListLocation(locationFilter, 0, 0, false);
        SortedComboBoxModel cboLocationModel = new SortedComboBoxModel();

        for (Location location : allLocation) {
            if (!location.getLname().startsWith("-")) {
                cboLocationModel.addElement(location.getLname());
            }
        }

        cboAllLocation.setModel(cboLocationModel);
        cboAllLocation.setSelectedIndex(0);
    }

    private void locationChange() {
        String chosenLoc = cboAllLocation.getSelectedItem().toString();
        currentLocation = getLocationDetails(allLocation, chosenLoc);

        if (currentLocation != null) {
            if (currentLocation.getLocid() != 0) {
                chosenLocation.setText(currentLocation.getLname());
                chosenLocationId.setText(currentLocation.getLocid().toString());
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
            case 3:
                cboAllLocation.setModel(new SortedComboBoxModel());
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
}
