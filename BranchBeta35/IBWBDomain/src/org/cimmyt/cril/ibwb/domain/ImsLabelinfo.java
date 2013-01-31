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
@Table(name = "ims_labelinfo")
public class ImsLabelinfo extends BaseFilter implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Column(name = "labelinfo_id")
    private Integer labelinfoId;
    @Column(name = "group_prefix")
    private String groupPrefix;
    @Column(name = "labelitemcount")
    private Integer labelitemcount;

    public ImsLabelinfo() {
        setDefault();
    }

    public ImsLabelinfo(Integer id) {
        this.id = id;
    }
    
    public ImsLabelinfo(boolean attrNulls) {
        if (! attrNulls) {
            setDefault();
        }
    }
    
    private void setDefault() {
        
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getLabelinfoId() {
        return labelinfoId;
    }

    public void setLabelinfoId(Integer labelinfoId) {
        this.labelinfoId = labelinfoId;
    }

    public String getGroupPrefix() {
        return groupPrefix;
    }

    public void setGroupPrefix(String groupPrefix) {
        this.groupPrefix = groupPrefix;
    }

    public Integer getLabelitemcount() {
        return labelitemcount;
    }

    public void setLabelitemcount(Integer labelitemcount) {
        this.labelitemcount = labelitemcount;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ImsLabelinfo)) {
            return false;
        }
        ImsLabelinfo other = (ImsLabelinfo) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.cimmyt.cril.ibwb.domain.ImsLabelinfo[ id=" + id + " ]";
    }
    
}
