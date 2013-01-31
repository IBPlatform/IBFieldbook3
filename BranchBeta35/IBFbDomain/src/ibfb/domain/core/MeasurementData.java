/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ibfb.domain.core;

import org.cimmyt.cril.ibwb.domain.DataC;
import org.cimmyt.cril.ibwb.domain.DataN;

/**
 *
 * @author TMSANCHEZ
 */
public class MeasurementData {

    public static final String DATA_N = "N";
    public static final String DATA_C = "C";
    private Variate variate;
    private String dataType;
    private Object data;

    /**
     * 
     */
    public void setData(Object data) {
        if (data instanceof DataC) {
            this.dataType = DATA_C;
        } else if (data instanceof DataN) {
            this.dataType = DATA_N;
        }
        this.data = data;
    }

    public void setData(String dataType, Object data) {
        this.dataType = dataType;
        this.data = data;
    }

    public Object getValue() {
        Object value = null;
        //if (data != null && !data.toString().isEmpty()) {
        if (data != null) {
            if (dataType.equals(DATA_C)) {
                DataC dataC = new DataC();
                if (data instanceof String) {
                    dataC.setDvalue(data.toString());
                } else if (data instanceof Integer) {
                    dataC.setDvalue(data.toString());
                }

                value = dataC;

            } else {
                Double dblValor = null;
                if (data instanceof String) {
                    String stringValue = (String)data;
                    if (stringValue != null && stringValue.isEmpty()) {
                        dblValor = null;
                    } else {
                        dblValor = new Double(stringValue);
                    }
                    
                } else if (data instanceof Integer) {
                    dblValor = Double.parseDouble(data.toString());
                } else if (data instanceof Double) {
                    dblValor = (Double)data;
                }
                DataN datan = new DataN();
                datan.setDvalue(dblValor);
                value = datan;
            }
        }

        return value;
    }

    /**
     * Return variate ID for this data
     * @return 
     */
    public Integer getVariateid() {
        Integer variateId = null;
        if (data != null) {
            if (dataType.equals(DATA_C)) {
                DataC dataC = (DataC) data;
                variateId = dataC.getDataCPK().getVariatid();
            } else if (dataType.equals(DATA_N)) {
                DataN dataN = (DataN) data;
                variateId = dataN.getDataNPK().getVariatid();
            }
        }
        return variateId;
    }

    public Object getValueData() {
        Object value = null;
        if (data != null && !data.toString().isEmpty()) {
            if (dataType.equals(DATA_C)) {
                DataC dataC = (DataC) data;
                value = dataC.getDvalue();
            } else {
                DataN datan = (DataN) data;
                value = datan.getDvalue();
            }
        }
        return value;
    }

    public String getDataType() {
        return dataType;
    }

    public void setDataType(String dataType) {
        this.dataType = dataType;
    }

    public Variate getVariate() {
        return variate;
    }

    public void setVariate(Variate variate) {
        this.variate = variate;
    }
}
