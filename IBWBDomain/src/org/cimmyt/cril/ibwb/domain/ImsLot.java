/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
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
@Table(name = "ims_lot")
public class ImsLot extends BaseFilter implements Serializable {
    public static final String ETYPE_GERMPLASM = "GERMPLASM";
    public static final int LOT_STATUS_ACTIVE = 1;
    public static final int LOT_STATUS_CLOSED = 0;
   
    public static final int DEFAULT_SOURCE = 0;
    
  
     
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "lotid")
    private Integer lotid;
    @Column(name = "userid")
    private Integer userid;
    @Column(name = "etype")
    private String etype;
    @Column(name = "eid")
    private Integer eid;
    @Column(name = "locid")
    private Integer locid;
    @Column(name = "scaleid")
    private Integer scaleid;
    @Column(name = "status")
    private Integer status;
    @Column(name = "sourceid")
    private Integer sourceid;
    @Column(name = "comments")
    private String comments;

    public ImsLot() {
        setDefault();
    }
    
    public ImsLot(Integer lotid) {
        this.lotid = lotid;
    }
    
    public ImsLot(boolean attrNulls) {
        if (! attrNulls) {
            setDefault();
        }
    }
    
    private void setDefault() {
        
    }

    public Integer getLotid() {
        return lotid;
    }

    public void setLotid(Integer lotid) {
        this.lotid = lotid;
    }

    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    public String getEtype() {
        return etype;
    }

    public void setEtype(String etype) {
        this.etype = etype;
    }

    public Integer getEid() {
        return eid;
    }

    public void setEid(Integer eid) {
        this.eid = eid;
    }

    public Integer getLocid() {
        return locid;
    }

    public void setLocid(Integer locid) {
        this.locid = locid;
    }

    public Integer getScaleid() {
        return scaleid;
    }

    public void setScaleid(Integer scaleid) {
        this.scaleid = scaleid;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getSourceid() {
        return sourceid;
    }

    public void setSourceid(Integer sourceid) {
        this.sourceid = sourceid;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (lotid != null ? lotid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ImsLot)) {
            return false;
        }
        ImsLot other = (ImsLot) object;
        if ((this.lotid == null && other.lotid != null) || (this.lotid != null && !this.lotid.equals(other.lotid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.cimmyt.cril.ibwb.domain.ImsLot[ lotid=" + lotid + " ]";
    }
    
}
