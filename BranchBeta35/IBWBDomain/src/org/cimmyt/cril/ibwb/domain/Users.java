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
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import org.cimmyt.cril.ibwb.domain.filter.BaseFilter;

/**
 *
 * @author jgcamarena
 */
@Entity
@Table(name = "users")
public class Users extends BaseFilter implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "userid")
    private Integer userid;
    @Basic(optional = false)
    @Column(name = "instalid")
    private Integer instalid;
    @Basic(optional = false)
    @Column(name = "ustatus")
    private Integer ustatus;
    @Basic(optional = false)
    @Column(name = "uaccess")
    private Integer uaccess;
    @Basic(optional = false)
    @Column(name = "utype")
    private Integer utype;
    @Basic(optional = false)
    @Column(name = "uname")
    private String uname;
    @Basic(optional = false)
    @Column(name = "upswd")
    private String upswd;
    @Basic(optional = false)
    @Column(name = "personid")
    private Integer personid;
    @Basic(optional = false)
    @Column(name = "adate")
    private Integer adate;
    @Basic(optional = false)
    @Column(name = "cdate")
    private Integer cdate;

    public Users() {
    	setDefault();
    }
    
    public Users(boolean atrNulls) {
    	if(!atrNulls)
    		setDefault();
    }
    
    public void setDefault(){
    	userid = 0;
    	instalid = 0;
    	ustatus = 0;
    	uaccess = 0;
    	utype = 0;
    	uname = "-";
    	upswd = "-";
    	personid = 0;
    	adate = 0;
    	cdate = 0;
    }

    public Users(Integer userid) {
        this.userid = userid;
    }

    public Users(Integer userid, Integer instalid, Integer ustatus, Integer uaccess, Integer utype, String uname, String upswd, Integer personid, Integer adate, Integer cdate) {
        this.userid = userid;
        this.instalid = instalid;
        this.ustatus = ustatus;
        this.uaccess = uaccess;
        this.utype = utype;
        this.uname = uname;
        this.upswd = upswd;
        this.personid = personid;
        this.adate = adate;
        this.cdate = cdate;
    }

    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    public Integer getInstalid() {
        return instalid;
    }

    public void setInstalid(Integer instalid) {
        this.instalid = instalid;
    }

    public Integer getUstatus() {
        return ustatus;
    }

    public void setUstatus(Integer ustatus) {
        this.ustatus = ustatus;
    }

    public Integer getUaccess() {
        return uaccess;
    }

    public void setUaccess(Integer uaccess) {
        this.uaccess = uaccess;
    }

    public Integer getUtype() {
        return utype;
    }

    public void setUtype(Integer utype) {
        this.utype = utype;
    }

    public String getUname() {
        return uname;
    }

    public void setUname(String uname) {
        this.uname = uname;
    }

    public String getUpswd() {
        return upswd;
    }

    public void setUpswd(String upswd) {
        this.upswd = upswd;
    }

    public Integer getPersonid() {
        return personid;
    }

    public void setPersonid(Integer personid) {
        this.personid = personid;
    }

    public Integer getAdate() {
        return adate;
    }

    public void setAdate(Integer adate) {
        this.adate = adate;
    }

    public Integer getCdate() {
        return cdate;
    }

    public void setCdate(Integer cdate) {
        this.cdate = cdate;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (userid != null ? userid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Users)) {
            return false;
        }
        Users other = (Users) object;
        if ((this.userid == null && other.userid != null) || (this.userid != null && !this.userid.equals(other.userid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.cimmyt.cril.ibworkbench.services.beans.Users[userid=" + userid + "]";
    }

}
