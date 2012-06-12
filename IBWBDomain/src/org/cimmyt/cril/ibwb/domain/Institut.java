/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.cimmyt.cril.ibwb.domain;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import org.cimmyt.cril.ibwb.domain.filter.BaseFilter;

/**
 *
 * @author jgcamarena
 */
@Entity
@Table(name = "institut")
public class Institut extends BaseFilter implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Column(name = "institid")
    private Integer institid;
    @Column(name = "pinsid")
    private Integer pinsid;
    @Column(name = "insname")
    private String insname;
    @Column(name = "insacr")
    private String insacr;
    @Column(name = "instype")
    private Integer instype;
    @Column(name = "street")
    private String street;
    @Column(name = "postbox")
    private String postbox;
    @Column(name = "city")
    private String city;
    @Column(name = "stateid")
    private Integer stateid;
    @Column(name = "cpostal")
    private String cpostal;
    @Column(name = "cntryid")
    private Integer cntryid;
    @Column(name = "aphone")
    private String aphone;
    @Column(name = "afax")
    private String afax;
    @Column(name = "aemail")
    private String aemail;
    @Column(name = "weburl")
    private String weburl;
    @Column(name = "sins")
    private Integer sins;
    @Column(name = "eins")
    private Integer eins;
    @Column(name = "ichange")
    private Integer ichange;
//    @Column(name = "faocode")
//    private String faocode;

    public Institut() {
    	setDefault();
    }
    
    public Institut(boolean atrNull) {
    	if(!atrNull)
    		setDefault();
    }
    
    public void setDefault(){
    	institid = 0;
    }


    public Integer getInstitid() {
        return institid;
    }

    public void setInstitid(Integer institid) {
        this.institid = institid;
    }

    public Integer getPinsid() {
        return pinsid;
    }

    public void setPinsid(Integer pinsid) {
        this.pinsid = pinsid;
    }

    public String getInsname() {
        return insname;
    }

    public void setInsname(String insname) {
        this.insname = insname;
    }

    public String getInsacr() {
        return insacr;
    }

    public void setInsacr(String insacr) {
        this.insacr = insacr;
    }

    public Integer getInstype() {
        return instype;
    }

    public void setInstype(Integer instype) {
        this.instype = instype;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getPostbox() {
        return postbox;
    }

    public void setPostbox(String postbox) {
        this.postbox = postbox;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public Integer getStateid() {
        return stateid;
    }

    public void setStateid(Integer stateid) {
        this.stateid = stateid;
    }

    public String getCpostal() {
        return cpostal;
    }

    public void setCpostal(String cpostal) {
        this.cpostal = cpostal;
    }

    public Integer getCntryid() {
        return cntryid;
    }

    public void setCntryid(Integer cntryid) {
        this.cntryid = cntryid;
    }

    public String getAphone() {
        return aphone;
    }

    public void setAphone(String aphone) {
        this.aphone = aphone;
    }

    public String getAfax() {
        return afax;
    }

    public void setAfax(String afax) {
        this.afax = afax;
    }

    public String getAemail() {
        return aemail;
    }

    public void setAemail(String aemail) {
        this.aemail = aemail;
    }

    public String getWeburl() {
        return weburl;
    }

    public void setWeburl(String weburl) {
        this.weburl = weburl;
    }

    public Integer getSins() {
        return sins;
    }

    public void setSins(Integer sins) {
        this.sins = sins;
    }

    public Integer getEins() {
        return eins;
    }

    public void setEins(Integer eins) {
        this.eins = eins;
    }

    public Integer getIchange() {
        return ichange;
    }

    public void setIchange(Integer ichange) {
        this.ichange = ichange;
    }

//    public String getFaocode() {
//        return faocode;
//    }
//
//    public void setFaocode(String faocode) {
//        this.faocode = faocode;
//    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Institut other = (Institut) obj;
        if (this.institid != other.institid && (this.institid == null || !this.institid.equals(other.institid))) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 89 * hash + (this.institid != null ? this.institid.hashCode() : 0);
        return hash;
    }

    

    @Override
    public String toString() {
        return "org.cimmyt.cril.ibworkbench.services.beans.Institut[institutPK=" + institid + "]";
    }

}
