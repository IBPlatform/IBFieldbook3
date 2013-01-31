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
@Table(name = "TMSSCALECON")
public class TmsScaleCon extends BaseFilter implements Serializable {

    @Id
    @Column(name = "tmsscaleconid")
    private Integer tmsscaleconid;
    @Column(name = "measuredinid")
    private Integer measuredinid;
    @Column(name = "slevel")
    private Double slevel;
    @Column(name = "elevel")
    private Double elevel;

    public TmsScaleCon() {
        setDefault();
    }

    public TmsScaleCon(boolean atrNulls) {
        if (!atrNulls) {
            setDefault();
        }
    }

    private void setDefault() {
        tmsscaleconid = 0;
        measuredinid = 0;
        slevel = 0.0;
        elevel = 0.0;
    }

    public TmsScaleCon(Integer tmsscaleconid, Integer measuredinid, Double slevel, Double elevel) {
        this.tmsscaleconid = tmsscaleconid;
        this.measuredinid = measuredinid;
        this.slevel = slevel;
        this.elevel = elevel;
    }

    public Double getElevel() {
        return elevel;
    }

    public void setElevel(Double elevel) {
        this.elevel = elevel;
    }

    public Integer getMeasuredinid() {
        return measuredinid;
    }

    public void setMeasuredinid(Integer measuredinid) {
        this.measuredinid = measuredinid;
    }

    public Double getSlevel() {
        return slevel;
    }

    public void setSlevel(Double slevel) {
        this.slevel = slevel;
    }

    public Integer getTmsscaleconid() {
        return tmsscaleconid;
    }

    public void setTmsscaleconid(Integer tmsscaleconid) {
        this.tmsscaleconid = tmsscaleconid;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final TmsScaleCon other = (TmsScaleCon) obj;
        if (this.tmsscaleconid != other.tmsscaleconid && (this.tmsscaleconid == null || !this.tmsscaleconid.equals(other.tmsscaleconid))) {
            return false;
        }
        if (this.measuredinid != other.measuredinid && (this.measuredinid == null || !this.measuredinid.equals(other.measuredinid))) {
            return false;
        }
        if (this.slevel != other.slevel && (this.slevel == null || !this.slevel.equals(other.slevel))) {
            return false;
        }
        if (this.elevel != other.elevel && (this.elevel == null || !this.elevel.equals(other.elevel))) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 31 * hash + (this.tmsscaleconid != null ? this.tmsscaleconid.hashCode() : 0);
        hash = 31 * hash + (this.measuredinid != null ? this.measuredinid.hashCode() : 0);
        hash = 31 * hash + (this.slevel != null ? this.slevel.hashCode() : 0);
        return hash;
    }

    @Override
    public String toString() {
        return "TmsScaleCon{" + "tmsscaleconid=" + tmsscaleconid + ", measuredinid=" + measuredinid + ", slevel=" + slevel + ", elevel=" + elevel + '}';
    }
    
    
    
}
