/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.cimmyt.cril.ibwb.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import org.cimmyt.cril.ibwb.domain.filter.BaseFilter;

/**
 *
 * @author jgcamarena
 */
@Entity
@Table(name = "study")
public class Study extends BaseFilter implements Serializable{
    public static final String STYPE_EXPERIMENT ="E";
    public static final String STYPE_NURSERY ="N";
    public static final String STYPE_SURVEY ="S";
    public static final String STYPE_TRIAL ="T";  
    
    public static final int SSTATUS_ACTIVE = 0;
    public static final int SSTATUS_DELETED = 9;

    
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "studyid")
    private Integer studyid;
    @Basic(optional = false)
    @Column(name = "sname")
    private String sname;
    @Basic(optional = false)
    @Column(name = "pmkey")
    private Integer pmkey;
    @Basic(optional = false)
    @Column(name = "title")
    private String title;
    @Basic(optional = false)
    @Column(name = "objectiv")
    private String objectiv;
    @Basic(optional = false)
    @Column(name = "investid")
    private Integer investid;
    @Basic(optional = false)
    @Column(name = "stype")
    private String stype;
    @Column(name = "sdate")
    private Integer sdate;
    @Column(name = "edate")
    private Integer edate;
    @Column(name = "userid")
    private Integer userid;
    @Basic(optional = false)
    @Column(name = "sstatus")
    private Integer sstatus;
    @Basic(optional = false)
    @Column(name = "shierarchy")
    private Integer shierarchy;
    @Transient
    private List<Factor> factors = new ArrayList<Factor>();

    public Study() {
    	setDefault();
    }

    public Study(boolean atrNulls) {
        if (! atrNulls) {
        	setDefault();
        }
    }
    
    private void setDefault(){
    	studyid = 0;
        sname = "-";
        pmkey = 0;
        title = "-";
        objectiv = "-";
        investid = 0;
        stype = "-";
        sstatus = 1;
        shierarchy = 0;
    }
    
    public Study(Integer studyid) {
        this.studyid = studyid;
    }

    public Study(Integer studyid, String sname, Integer pmkey, String title, String objectiv, Integer investid, String stype, Integer sstatus, Integer shierarchy) {
        this.studyid = studyid;
        this.sname = sname;
        this.pmkey = pmkey;
        this.title = title;
        this.objectiv = objectiv;
        this.investid = investid;
        this.stype = stype;
        this.sstatus = sstatus;
        this.shierarchy = shierarchy;
    }

    public Integer getStudyid() {
        return studyid;
    }

    public void setStudyid(Integer studyid) {
        this.studyid = studyid;
    }

    public String getSname() {
        return sname;
    }

    public void setSname(String sname) {
        this.sname = sname;
    }

    public Integer getPmkey() {
        return pmkey;
    }

    public void setPmkey(Integer pmkey) {
        this.pmkey = pmkey;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getObjectiv() {
        return objectiv;
    }

    public void setObjectiv(String objectiv) {
        this.objectiv = objectiv;
    }

    public Integer getInvestid() {
        return investid;
    }

    public void setInvestid(Integer investid) {
        this.investid = investid;
    }

    public String getStype() {
        return stype;
    }

    public void setStype(String stype) {
        this.stype = stype;
    }

    public Integer getSdate() {
        return sdate;
    }

    public void setSdate(Integer sdate) {
        this.sdate = sdate;
    }

    public Integer getEdate() {
        return edate;
    }

    public void setEdate(Integer edate) {
        this.edate = edate;
    }

    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    public Integer getSstatus() {
        return sstatus;
    }

    public void setSstatus(Integer sstatus) {
        this.sstatus = sstatus;
    }

    public Integer getShierarchy() {
        return shierarchy;
    }

    public void setShierarchy(Integer shierarchy) {
        this.shierarchy = shierarchy;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (studyid != null ? studyid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Study)) {
            return false;
        }
        Study other = (Study) object;
        if ((this.studyid == null && other.studyid != null) || (this.studyid != null && !this.studyid.equals(other.studyid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.cimmyt.cril.ibworkbench.services.beans.Study[studyid=" + studyid + "]";
    }

    /**
     * @return the factors
     */
    public List<Factor> getFactors() {
        return factors;
    }

    /**
     * @param factors the factors to set
     */
    public void setFactors(List<Factor> factors) {
        this.factors = factors;
    }

}
