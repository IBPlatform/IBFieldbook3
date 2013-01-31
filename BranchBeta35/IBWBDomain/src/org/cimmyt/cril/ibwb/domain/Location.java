package org.cimmyt.cril.ibwb.domain;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import org.cimmyt.cril.ibwb.domain.filter.BaseFilter;

/**
 *
 * @author jgcamarena
 */
@Entity
@Table(name = "location")
public class Location extends BaseFilter implements Serializable {

    private static final long serialVersionUID = 1L;

    public static final int LTYPE_CONTINENT = 401; //
    public static final int LTYPE_GEOGRAPHICAL_REGION = 402; // 
    public static final int LTYPE_GEOPOLITICAL_REGION = 403;
    public static final int LTYPE_ECOLOGICAL_REGION = 404;
    public static final int LTYPE_COUNTRY = 405;
    public static final int LTYPE_PROV = 406;
    public static final int LTYPE_DISTRICT = 407;
    public static final int LTYPE_MUN = 408;
    public static final int LTYPE_GERMPLASM_COLLECTION_SITE = 409;
    public static final int LTYPE_BREEDING_LOCATION = 410;
    public static final int LTYPE_INTERNATIONAL_AGRICULTURAL_RESEARCH_CENTER = 411;
    public static final int LTYPE_INTERNATIONAL_TEST_SITE = 412;
    public static final int LTYPE_PRIVATE_RESEARCH_COMPANY  = 413;
    public static final int LTYPE_STORAGE_SEED_STOCK_LOCATION = 1500;    
    
    @Id
    @Basic(optional = false)
    @Column(name = "locid")
    private Integer locid;
    @Basic(optional = false)
    @Column(name = "ltype")
    private Integer ltype;
    @Basic(optional = false)
    @Column(name = "nllp")
    private Integer nllp;
    @Basic(optional = false)
    @Column(name = "lname")
    private String lname;
    @Column(name = "labbr")
    private String labbr;
    @Basic(optional = false)
    @Column(name = "snl3id")
    private Integer snl3id;
    @Basic(optional = false)
    @Column(name = "snl2id")
    private Integer snl2id;
    @Basic(optional = false)
    @Column(name = "snl1id")
    private Integer snl1id;
    @Basic(optional = false)
    @Column(name = "cntryid")
    private Integer cntryid;
    @Basic(optional = false)
    @Column(name = "lrplce")
    private Integer lrplce;

    public Location() {
        setDefault();
    }

    public Location(boolean atrNulls) {
        if (!atrNulls) {
            setDefault();
        }
    }

    public void setDefault() {
        locid = 0;
        ltype = 0;
        nllp = 0;
        lname = "-";
        labbr = "-";
        snl3id = 0;
        snl2id = 0;
        snl1id = 0;
        cntryid = 0;
        lrplce = 0;
    }

    public Location(Integer locid) {
        this.locid = locid;
    }

    public Location(Integer locid, Integer ltype, Integer nllp, String lname, Integer snl3id, Integer snl2id, Integer snl1id, Integer cntryid, Integer lrplce) {
        this.locid = locid;
        this.ltype = ltype;
        this.nllp = nllp;
        this.lname = lname;
        this.snl3id = snl3id;
        this.snl2id = snl2id;
        this.snl1id = snl1id;
        this.cntryid = cntryid;
        this.lrplce = lrplce;
    }

    public Integer getLocid() {
        return locid;
    }

    public void setLocid(Integer locid) {
        this.locid = locid;
    }

    public Integer getLtype() {
        return ltype;
    }

    public void setLtype(Integer ltype) {
        this.ltype = ltype;
    }

    public Integer getNllp() {
        return nllp;
    }

    public void setNllp(Integer nllp) {
        this.nllp = nllp;
    }

    public String getLname() {
        return lname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }

    public String getLabbr() {
        return labbr;
    }

    public void setLabbr(String labbr) {
        this.labbr = labbr;
    }

    public Integer getSnl3id() {
        return snl3id;
    }

    public void setSnl3id(Integer snl3id) {
        this.snl3id = snl3id;
    }

    public Integer getSnl2id() {
        return snl2id;
    }

    public void setSnl2id(Integer snl2id) {
        this.snl2id = snl2id;
    }

    public Integer getSnl1id() {
        return snl1id;
    }

    public void setSnl1id(Integer snl1id) {
        this.snl1id = snl1id;
    }

    public Integer getCntryid() {
        return cntryid;
    }

    public void setCntryid(Integer cntryid) {
        this.cntryid = cntryid;
    }

    public Integer getLrplce() {
        return lrplce;
    }

    public void setLrplce(Integer lrplce) {
        this.lrplce = lrplce;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (locid != null ? locid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Location)) {
            return false;
        }
        Location other = (Location) object;
        if ((this.locid == null && other.locid != null) || (this.locid != null && !this.locid.equals(other.locid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.cimmyt.cril.ibworkbench.services.beans.Location[locid=" + locid + "]";
    }
}
