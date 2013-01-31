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
@Table(name = "ims_transaction")
public class ImsTransaction extends BaseFilter implements Serializable {

    public static final int TRAN_STATUS_ANTICIPATED = 0;
    public static final int TRAN_STATUS_CONFIRMED = 1;
    public static final int TRAN_STATUS_CANCELED = 9;
    public static final String SOURCE_TYPE_STUDY = "STUDY";
    public static final String SOURCE_TYPE_LIST = "LIST";
    
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "trnid")
    private Integer trnid;
    @Column(name = "userid")
    private Integer userid;
    @Column(name = "lotid")
    private Integer lotid;
    @Column(name = "trndate")
    private Integer trndate;
    @Column(name = "trnstat")
    private Integer trnstat;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "trnqty")
    private Double trnqty;
    @Column(name = "comments")
    private String comments;
    @Column(name = "cmtdata")
    private Integer cmtdata;
    @Column(name = "sourcetype")
    private String sourcetype;
    @Column(name = "sourceid")
    private Integer sourceid;
    @Column(name = "recordid")
    private Integer recordid;
    @Column(name = "prevamount")
    private Double prevamount;
    @Column(name = "personid")
    private Integer personid;

    public ImsTransaction() {
        setDefault();
    }

    public ImsTransaction(Integer trnid) {
        this.trnid = trnid;
    }

    public ImsTransaction(boolean attrNulls) {
        if (!attrNulls) {
            setDefault();
        }
    }

    private void setDefault() {
    }

    public Integer getTrnid() {
        return trnid;
    }

    public void setTrnid(Integer trnid) {
        this.trnid = trnid;
    }

    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    public Integer getLotid() {
        return lotid;
    }

    public void setLotid(Integer lotid) {
        this.lotid = lotid;
    }

    public Integer getTrndate() {
        return trndate;
    }

    public void setTrndate(Integer trndate) {
        this.trndate = trndate;
    }

    public Integer getTrnstat() {
        return trnstat;
    }

    public void setTrnstat(Integer trnstat) {
        this.trnstat = trnstat;
    }

    public Double getTrnqty() {
        return trnqty;
    }

    public void setTrnqty(Double trnqty) {
        this.trnqty = trnqty;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public Integer getCmtdata() {
        return cmtdata;
    }

    public void setCmtdata(Integer cmtdata) {
        this.cmtdata = cmtdata;
    }

    public String getSourcetype() {
        return sourcetype;
    }

    public void setSourcetype(String sourcetype) {
        this.sourcetype = sourcetype;
    }

    public Integer getSourceid() {
        return sourceid;
    }

    public void setSourceid(Integer sourceid) {
        this.sourceid = sourceid;
    }

    public Integer getRecordid() {
        return recordid;
    }

    public void setRecordid(Integer recordid) {
        this.recordid = recordid;
    }

    public Double getPrevamount() {
        return prevamount;
    }

    public void setPrevamount(Double prevamount) {
        this.prevamount = prevamount;
    }

    public Integer getPersonid() {
        return personid;
    }

    public void setPersonid(Integer personid) {
        this.personid = personid;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (trnid != null ? trnid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ImsTransaction)) {
            return false;
        }
        ImsTransaction other = (ImsTransaction) object;
        if ((this.trnid == null && other.trnid != null) || (this.trnid != null && !this.trnid.equals(other.trnid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.cimmyt.cril.ibwb.domain.ImsTransaction[ trnid=" + trnid + " ]";
    }
}
