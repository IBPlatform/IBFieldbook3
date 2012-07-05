package org.cimmyt.cril.ibwb.domain;

import java.io.Serializable;
import java.lang.reflect.Field;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;
import org.cimmyt.cril.ibwb.domain.filter.BaseFilter;

/**
 *
 * @author jgcamarena
 */
@Entity
@Table(name = "listdata")
public class Listdata extends BaseFilter implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @EmbeddedId
    protected ListdataPK listdataPK;
    
//    
//    @Basic(optional = false)
//    @Column(name = "listid")
//    private Integer listid;
//
//    @Basic(optional = false)
//    @Column(name = "lrecid")
//    private Integer lrecid;
    
    @Basic(optional = false)
    @Column(name = "entryid")
    private Integer entryid;
    @Column(name = "entrycd")
    private String entrycd;
    @Column(name = "source")
    private String source;
    @Basic(optional = false)
    @Column(name = "desig")
    private String desig;
    @Column(name = "grpname")
    private String grpname;
    @Basic(optional = false)
    @Column(name = "lrstatus")
    private Integer lrstatus;
    @Basic(optional = false)
    @Column(name = "gid")
    private Integer gid;
    @Transient
    private String nameBCID;
    @Transient
    private Integer giddata;
    
    @Transient
    private Integer gpid1;
    @Transient
    private Integer gpid2;
    /**
     * To manage number of parents
     */
    @Transient
    private Integer Gnpgs;
    
    //----------
    /**
     * For GLOCN
     */
    @Transient
    private Integer locationId;
    /**
     * For GDate
     */
    @Transient
    private Integer harvestDate;
    
    /**
     * For METHOD 
     */
    @Transient
    private Integer methodId;

    public Listdata() {
        setDefault();
    }

    public Listdata(boolean atrNulls) {
        if (!atrNulls) {
            setDefault();
        }
    }

    public void setDefault() {
        listdataPK = new ListdataPK();
        listdataPK.setLrecid(0);
        listdataPK.setListid(0);
//        listid = 0;
//        lrecid = 0;
        entryid = 0;
        entrycd = "-";
        source = "-";
        desig = "";
        grpname = "-";
        gid = 0;
        lrstatus = 0;
    }

    public Listdata(ListdataPK listdataPK) {
        this.listdataPK = listdataPK;
    }

    public ListdataPK getListdataPK() {
        return listdataPK;
    }

    public void setListdataPK(ListdataPK listdataPK) {
        this.listdataPK = listdataPK;
    }
    
//    public Integer getListid() {
//        return listid;
//    }
//
//    public void setListid(Integer listid) {
//        this.listid = listid;
//    }
//
//    public Integer getLrecid() {
//        return lrecid;
//    }
//
//    public void setLrecid(Integer llrecid) {
//        this.lrecid = llrecid;
//    }

    public Integer getEntryid() {
        return entryid;
    }

    public void setEntryid(Integer entryid) {
        this.entryid = entryid;
    }

    public String getEntrycd() {
        return entrycd;
    }

    public void setEntrycd(String entrycd) {
        this.entrycd = entrycd;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getDesig() {
        return desig;
    }

    public void setDesig(String desig) {
        this.desig = desig;
    }

    public String getGrpname() {
        return grpname;
    }

    public void setGrpname(String grpname) {
        this.grpname = grpname;
    }

    public Integer getGid() {
        return gid;
    }

    public void setGid(Integer gid) {
        this.gid = gid;
    }

    public Integer getLrstatus() {
        return lrstatus;
    }

    public void setLrstatus(Integer lrstatus) {
        this.lrstatus = lrstatus;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (listdataPK != null ? listdataPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Listdata)) {
            return false;
        }
        Listdata other = (Listdata) object;
        if ((this.listdataPK == null && other.listdataPK != null) || (this.listdataPK != null && !this.listdataPK.equals(other.listdataPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.cimmyt.cril.ibworkbench.services.beans.Listdata[listdataPK=" + listdataPK + "]";
    }

    public Integer getGiddata() {
        //return giddata;
        return this.listdataPK.getListid();
    }

    public void setGiddata(Integer giddata) {
        this.giddata = giddata;
    }

    public static void main(String[] args) {
        System.out.println("The name of class Foo is: " + Listdata.class.getName());
        for (Field field : Listdata.class.getDeclaredFields()) {
            System.out.println("The field: " + field.getName());
        }

    }

    public Integer getHarvestDate() {
        return harvestDate;
    }

    public void setHarvestDate(Integer harvestDate) {
        this.harvestDate = harvestDate;
    }

    public Integer getLocationId() {
        return locationId;
    }

    public void setLocationId(Integer locationId) {
        this.locationId = locationId;
    }

    public Integer getMethodId() {
        return methodId;
    }

    public void setMethodId(Integer methodId) {
        this.methodId = methodId;
    }

    public Integer getGpid1() {
        return gpid1;
    }

    public void setGpid1(Integer gpid1) {
        this.gpid1 = gpid1;
    }

    public Integer getGpid2() {
        return gpid2;
    }

    public void setGpid2(Integer gpid2) {
        this.gpid2 = gpid2;
    }

    public Integer getGnpgs() {
        return Gnpgs;
    }

    public void setGnpgs(Integer Gnpgs) {
        this.Gnpgs = Gnpgs;
    }

    /**
     * @return the nameBCID
     */
    public String getNameBCID() {
        return nameBCID;
    }

    /**
     * @param nameBCID the nameBCID to set
     */
    public void setNameBCID(String nameBCID) {
        this.nameBCID = nameBCID;
    }

    
    
}
