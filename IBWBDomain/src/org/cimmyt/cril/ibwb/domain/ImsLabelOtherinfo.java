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
@Table(name = "ims_label_otherinfo")
public class ImsLabelOtherinfo extends BaseFilter implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Column(name = "otherinfo_id")
    private Integer otherinfoId;
    @Column(name = "labelinfo_id")
    private Integer labelinfoId;
    @Column(name = "group_prefix")
    private String groupPrefix;
    @Column(name = "tablename")
    private String tablename;
    @Column(name = "fieldname")
    private String fieldname;
    @Column(name = "foreign_fieldname")
    private String foreignFieldname;

    public ImsLabelOtherinfo() {
        setDefault();
    }

    public ImsLabelOtherinfo(Integer id) {
        this.id = id;
    }

    public ImsLabelOtherinfo(boolean attrNulls) {
        if (!attrNulls) {
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

    public Integer getOtherinfoId() {
        return otherinfoId;
    }

    public void setOtherinfoId(Integer otherinfoId) {
        this.otherinfoId = otherinfoId;
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

    public String getTablename() {
        return tablename;
    }

    public void setTablename(String tablename) {
        this.tablename = tablename;
    }

    public String getFieldname() {
        return fieldname;
    }

    public void setFieldname(String fieldname) {
        this.fieldname = fieldname;
    }

    public String getForeignFieldname() {
        return foreignFieldname;
    }

    public void setForeignFieldname(String foreignFieldname) {
        this.foreignFieldname = foreignFieldname;
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
        if (!(object instanceof ImsLabelOtherinfo)) {
            return false;
        }
        ImsLabelOtherinfo other = (ImsLabelOtherinfo) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.cimmyt.cril.ibwb.domain.ImsLabelOtherinfo[ id=" + id + " ]";
    }
}
