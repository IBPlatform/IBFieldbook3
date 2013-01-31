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
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author jgcamarena
 */
@Entity
@Table(name = "persons")
public class Persons extends BaseFilter implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "personid")
    private Integer personid;
    @Basic(optional = false)
    @Column(name = "fname")
    private String fname;
    @Basic(optional = false)
    @Column(name = "lname")
    private String lname;
    @Basic(optional = false)
    @Column(name = "ioname")
    private String ioname;
    @Basic(optional = false)
    @Column(name = "institid")
    private Integer institid;
    @Basic(optional = false)
    @Column(name = "ptitle")
    private String ptitle;
    @Basic(optional = false)
    @Column(name = "poname")
    private String poname;
    @Basic(optional = false)
    @Column(name = "plangu")
    private Integer plangu;
    @Basic(optional = false)
    @Column(name = "pphone")
    private String pphone;
    @Basic(optional = false)
    @Column(name = "pextent")
    private String pextent;
    @Basic(optional = false)
    @Column(name = "pfax")
    private String pfax;
    @Basic(optional = false)
    @Column(name = "pemail")
    private String pemail;
    @Basic(optional = false)
    @Column(name = "prole")
    private Integer prole;
    @Basic(optional = false)
    @Column(name = "sperson")
    private Integer sperson;
    @Basic(optional = false)
    @Column(name = "eperson")
    private Integer eperson;
    @Basic(optional = false)
    @Column(name = "pstatus")
    private Integer pstatus;
    @Basic(optional = false)
    @Column(name = "pnotes")
    private String pnotes;
    @Basic(optional = false)
    @Column(name = "contact")
    private String contact;
   // @Basic(optional = false)
    //@Column(name = "idno")
    //private String idno;

    public Persons() {
    	setDefault();
    }
    
    public Persons(boolean atrNulls) {
    	if(!atrNulls)
    		setDefault();
    }
    
    public void setDefault(){
    	personid = 0;
    	fname = "-";
    	lname = "-";
    	ioname = "-";
    	institid = 0;
    	ptitle = "-";
    	poname = "-";
    	plangu = 0;
    	pphone = "-";
    	pextent = "-";
    	pfax = "-";
    	pemail = "-";
    	prole = 0;
    	sperson = 0;
    	eperson = 0;
    	pstatus = 0;
    	pnotes = "-";
    	contact = "-";
    	//idno = "-";
    }

    public Persons(Integer personid) {
        this.personid = personid;
    }

    public Persons(Integer personid, String fname, String lname, String ioname, Integer institid, String ptitle, String poname, Integer plangu, String pphone, String pextent, String pfax, String pemail, Integer prole, Integer sperson, Integer eperson, Integer pstatus, String pnotes, String contact, String idno) {
        this.personid = personid;
        this.fname = fname;
        this.lname = lname;
        this.ioname = ioname;
        this.institid = institid;
        this.ptitle = ptitle;
        this.poname = poname;
        this.plangu = plangu;
        this.pphone = pphone;
        this.pextent = pextent;
        this.pfax = pfax;
        this.pemail = pemail;
        this.prole = prole;
        this.sperson = sperson;
        this.eperson = eperson;
        this.pstatus = pstatus;
        this.pnotes = pnotes;
        this.contact = contact;
        //this.idno = idno;
    }

    public Integer getPersonid() {
        return personid;
    }

    public void setPersonid(Integer personid) {
        this.personid = personid;
    }

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getLname() {
        return lname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }

    public String getIoname() {
        return ioname;
    }

    public void setIoname(String ioname) {
        this.ioname = ioname;
    }

    public Integer getInstitid() {
        return institid;
    }

    public void setInstitid(Integer institid) {
        this.institid = institid;
    }

    public String getPtitle() {
        return ptitle;
    }

    public void setPtitle(String ptitle) {
        this.ptitle = ptitle;
    }

    public String getPoname() {
        return poname;
    }

    public void setPoname(String poname) {
        this.poname = poname;
    }

    public Integer getPlangu() {
        return plangu;
    }

    public void setPlangu(Integer plangu) {
        this.plangu = plangu;
    }

    public String getPphone() {
        return pphone;
    }

    public void setPphone(String pphone) {
        this.pphone = pphone;
    }

    public String getPextent() {
        return pextent;
    }

    public void setPextent(String pextent) {
        this.pextent = pextent;
    }

    public String getPfax() {
        return pfax;
    }

    public void setPfax(String pfax) {
        this.pfax = pfax;
    }

    public String getPemail() {
        return pemail;
    }

    public void setPemail(String pemail) {
        this.pemail = pemail;
    }

    public Integer getProle() {
        return prole;
    }

    public void setProle(Integer prole) {
        this.prole = prole;
    }

    public Integer getSperson() {
        return sperson;
    }

    public void setSperson(Integer sperson) {
        this.sperson = sperson;
    }

    public Integer getEperson() {
        return eperson;
    }

    public void setEperson(Integer eperson) {
        this.eperson = eperson;
    }

    public Integer getPstatus() {
        return pstatus;
    }

    public void setPstatus(Integer pstatus) {
        this.pstatus = pstatus;
    }

    public String getPnotes() {
        return pnotes;
    }

    public void setPnotes(String pnotes) {
        this.pnotes = pnotes;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }
//
//    public String getIdno() {
//        return idno;
//    }
//
//    public void setIdno(String idno) {
//        this.idno = idno;
//    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (personid != null ? personid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Persons)) {
            return false;
        }
        Persons other = (Persons) object;
        if ((this.personid == null && other.personid != null) || (this.personid != null && !this.personid.equals(other.personid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.cimmyt.cril.ibworkbench.services.beans.Persons[personid=" + personid + "]";
    }

    
    @Transactional
    public String getFullName() {
        StringBuilder fullName = new StringBuilder();
        fullName.append(this.lname);
        fullName.append(" ");
        fullName.append(this.fname);
        return fullName.toString();
    }
        
}
