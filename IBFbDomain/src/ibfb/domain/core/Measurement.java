/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ibfb.domain.core;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author TMSANCHEZ
 */
public class Measurement {
    
    private Integer trial;
    private Integer replication;
    private Integer block;
    private Integer entry;
    private Integer gid;
    private String designation;
    private Integer plot;
    private Integer row;
    private Integer column;
    
    private Integer ounitId;
    
    /**
     * Each value for factor labels
     */
    private List<Object> factorLabelData;
    /**
     * Each value for data_n and data_c
     */
    private List<MeasurementData> measurementsData;
    
    public Integer getOunitId() {
        return ounitId;
    }
    
    public void setOunitId(Integer ounitId) {
        this.ounitId = ounitId;
    }
        
    
    public Integer getBlock() {
        return block;
    }
    
    public void setBlock(Integer block) {
        this.block = block;
    }
    
    public String getDesignation() {
        return designation;
    }
    
    public void setDesignation(String designation) {
        this.designation = designation;
    }
    
    public Integer getEntry() {
        return entry;
    }
    
    public void setEntry(Integer entry) {
        this.entry = entry;
    }
    
    public Integer getGid() {
        return gid;
    }
    
    public void setGid(Integer gid) {
        this.gid = gid;
    }
    
    public Integer getReplication() {
        return replication;
    }
    
    public void setReplication(Integer replication) {
        this.replication = replication;
    }
    
    public Integer getTrial() {
        return trial;
    }
    
    public void setTrial(Integer trial) {
        this.trial = trial;
    }
    
    public Integer getColumn() {
        return column;
    }
    
    public void setColumn(Integer column) {
        this.column = column;
    }
    
    public List<MeasurementData> getMeasurementsData() {
        return measurementsData;
    }
    
    public void setMeasurementsData(List<MeasurementData> measurementsData) {
        this.measurementsData = measurementsData;
    }
    
    public Integer getRow() {
        return row;
    }
    
    public void setRow(Integer row) {
        this.row = row;
    }
    
    public Integer getPlot() {
        return plot;
    }
    
    public void setPlot(Integer plot) {
        this.plot = plot;
    }

    /**
     * Initialize each measurement row with empty data columns
     * @param variateCount Number of variates to add
     */
    public void initMeasurementData(int variateCount) {
        List<MeasurementData> measurementsDataList = new ArrayList<MeasurementData>();
        for (int index = 0; index < variateCount; index++) {
            MeasurementData measurementData = new MeasurementData();
            measurementsDataList.add(measurementData);
        }
        setMeasurementsData(measurementsDataList);
    }
    
    public void setMeasurementData(int index, MeasurementData measurementData) {
       measurementsData.set(index, measurementData);
    }
    
    public List<Object> getFactorLabelData() {
        return factorLabelData;
    }
    
    public void setFactorLabelData(List<Object> factorLabelData) {
        this.factorLabelData = factorLabelData;
    }
}