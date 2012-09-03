/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.cimmyt.cril.ibwb.domain;

import java.io.Serializable;
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
@Table(name = "listnms")
public class Listnms extends BaseFilter implements Serializable {
    public static final String LIST_TYPE_FOLDER = "FLD";
    public static final String LIST_TYPE_LIST = "LST";
    public static final String LIST_TYPE_HARVEST = "Harvest";
    
    public static final int LSSTATUS_FOLDER = 0;
    public static final int LSSTATUS_OPEN_LIST = 1;
    public static final int LSSTATUS_DELETED = 9;
    
    
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "listid")
    private Integer listid;
    @Basic(optional = false)
    @Column(name = "listname")
    private String listname;
    @Basic(optional = false)
    @Column(name = "listdate")
    private Integer listdate;
    @Basic(optional = false)
    @Column(name = "listtype")
    private String listtype;
    @Basic(optional = false)
    @Column(name = "listuid")
    private Integer listuid;
    @Basic(optional = false)
    @Column(name = "listdesc")
    private String listdesc;
    @Column(name = "lhierarchy")
    private Integer lhierarchy;
    @Column(name = "liststatus")
    private Integer liststatus;
    
    @Transient
    private List<Listdata> lisdatas;
    
    public Listnms() {
    	setDefault();
    }
    
    public Listnms(boolean atrNulls) {
    	if(!atrNulls)
    		setDefault();
    }
    
    public void setDefault(){
    	listid = 0;
    	listname = "-";
    	listdate = 0;
    	listtype = "LST";
    	listuid = 0;
    	listdesc ="-";
    	lhierarchy = 0;
    	liststatus = 1;
    }
    
    public Listnms(Integer listid) {
        this.listid = listid;
    }

    public Listnms(Integer listid, String listname, Integer listdate, String listtype, Integer listuid, String listdesc) {
        this.listid = listid;
        this.listname = listname;
        this.listdate = listdate;
        this.listtype = listtype;
        this.listuid = listuid;
        this.listdesc = listdesc;
    }

    public Integer getListid() {
        return listid;
    }

    public void setListid(Integer listid) {
        this.listid = listid;
    }

    public String getListname() {
        return listname;
    }

    public void setListname(String listname) {
        this.listname = listname;
    }

    public Integer getListdate() {
        return listdate;
    }

    public void setListdate(Integer listdate) {
        this.listdate = listdate;
    }

    public String getListtype() {
        return listtype;
    }

    public void setListtype(String listtype) {
        this.listtype = listtype;
    }

    public Integer getListuid() {
        return listuid;
    }

    public void setListuid(Integer listuid) {
        this.listuid = listuid;
    }

    public String getListdesc() {
        return listdesc;
    }

    public void setListdesc(String listdesc) {
        this.listdesc = listdesc;
    }

    public Integer getLhierarchy() {
        return lhierarchy;
    }

    public void setLhierarchy(Integer lhierarchy) {
        this.lhierarchy = lhierarchy;
    }

    public Integer getListstatus() {
        return liststatus;
    }

    public void setListstatus(Integer liststatus) {
        this.liststatus = liststatus;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (listid != null ? listid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Listnms)) {
            return false;
        }
        Listnms other = (Listnms) object;
        if ((this.listid == null && other.listid != null) || (this.listid != null && !this.listid.equals(other.listid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        //return "org.cimmyt.cril.ibworkbench.services.beans.Listnms[listid=" + listid + "]";
        return this.getListname();
    }

    /**
     * @return the lisdatas
     */
    public List<Listdata> getLisdatas() {
        return lisdatas;
    }

    /**
     * @param lisdatas the lisdatas to set
     */
    public void setLisdatas(List<Listdata> lisdatas) {
        this.lisdatas = lisdatas;
    }

}
