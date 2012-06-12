
package org.cimmyt.cril.ibwb.domain;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import org.cimmyt.cril.ibwb.domain.filter.BaseFilter;
/**
 *
 * @author TMSANCHEZ
 */
@Entity
@Table(name = "TMSSCALEDIS")
public class TmsScaleDis extends BaseFilter implements Serializable {
    @Id
    @Column(name="tmsscaledisid")
    private Integer tmsscaledisid;
    @Column(name="measuredinid")
    private Integer measuredinid;
    @Column(name="valuename")
    private String value;
    @Column(name="valdesc")
    private String valdesc;

    public TmsScaleDis() {
        setDefault();
    }

    public TmsScaleDis(boolean atrNulls) {
        if (! atrNulls) {
            setDefault();
        }
    }

    public TmsScaleDis(Integer tmscaledisid, Integer measuredinid, String value, String valdesc) {
        this.tmsscaledisid = tmscaledisid;
        this.measuredinid = measuredinid;
        this.value = value;
        this.valdesc = valdesc;
    }
    
    
    
    private void setDefault() {
        tmsscaledisid = 0;
        measuredinid =0;
        value = "-";
        valdesc = "-";
    }

    public Integer getMeasuredinid() {
        return measuredinid;
    }

    public void setMeasuredinid(Integer measuredinid) {
        this.measuredinid = measuredinid;
    }

    public Integer getTmsscaledisid() {
        return tmsscaledisid;
    }

    public void setTmsscaledisid(Integer tmscaledisid) {
        this.tmsscaledisid = tmscaledisid;
    }

    public String getValdesc() {
        return valdesc;
    }

    public void setValdesc(String valdesc) {
        this.valdesc = valdesc;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final TmsScaleDis other = (TmsScaleDis) obj;
        if (this.tmsscaledisid != other.tmsscaledisid && (this.tmsscaledisid == null || !this.tmsscaledisid.equals(other.tmsscaledisid))) {
            return false;
        }
        if (this.measuredinid != other.measuredinid && (this.measuredinid == null || !this.measuredinid.equals(other.measuredinid))) {
            return false;
        }
        if ((this.value == null) ? (other.value != null) : !this.value.equals(other.value)) {
            return false;
        }
        if ((this.valdesc == null) ? (other.valdesc != null) : !this.valdesc.equals(other.valdesc)) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 71 * hash + (this.tmsscaledisid != null ? this.tmsscaledisid.hashCode() : 0);
        hash = 71 * hash + (this.measuredinid != null ? this.measuredinid.hashCode() : 0);
        hash = 71 * hash + (this.value != null ? this.value.hashCode() : 0);
        hash = 71 * hash + (this.valdesc != null ? this.valdesc.hashCode() : 0);
        return hash;
    }

    @Override
    public String toString() {
        return "TmsScaleDis{" + "tmscaledisid=" + tmsscaledisid + ", measuredinid=" + measuredinid + ", value=" + value + ", valdesc=" + valdesc + '}';
    }
    
    
}
