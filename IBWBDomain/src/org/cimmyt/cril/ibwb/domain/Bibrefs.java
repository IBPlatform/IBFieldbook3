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
@Table(name = "bibrefs")
public class Bibrefs extends BaseFilter implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "refid")
    private Integer refid;
    @Column(name = "pubtype")
    private Integer pubtype;
    @Column(name = "pubdate")
    private Integer pubdate;
    @Basic(optional = false)
    @Column(name = "authors")
    private String authors;
    @Basic(optional = false)
    @Column(name = "editors")
    private String editors;
    @Basic(optional = false)
    @Column(name = "analyt")
    private String analyt;
    @Basic(optional = false)
    @Column(name = "monogr")
    private String monogr;
    @Basic(optional = false)
    @Column(name = "series")
    private String series;
    @Basic(optional = false)
    @Column(name = "volume")
    private String volume;
    @Basic(optional = false)
    @Column(name = "issue")
    private String issue;
    @Basic(optional = false)
    @Column(name = "pagecol")
    private String pagecol;
    @Basic(optional = false)
    @Column(name = "publish")
    private String publish;
    @Basic(optional = false)
    @Column(name = "pucity")
    private String pucity;
    @Basic(optional = false)
    @Column(name = "pucntry")
    private String pucntry;

    public Bibrefs() {
    	setDefault();
    }
    
    public Bibrefs(boolean atrNulls) {
    	if(! atrNulls)
    		setDefault();
    }
    
    public void setDefault(){
    	refid = 0;
    	pubtype = 0;
    	pubdate = 0;
    	authors = "-";
    	editors = "-";
    	analyt = "-";
    	monogr = "-";
    	series = "-";
    	volume = "-";
    	issue = "-";
    	pagecol = "-";
    	publish = "-";
    	pucity = "-";
    	pucntry = "-";
    }

    public Bibrefs(Integer refid) {
        this.refid = refid;
    }

    public Bibrefs(Integer refid, String authors, String editors, String analyt, String monogr, String series, String volume, String issue, String pagecol, String publish, String pucity, String pucntry) {
        this.refid = refid;
        this.authors = authors;
        this.editors = editors;
        this.analyt = analyt;
        this.monogr = monogr;
        this.series = series;
        this.volume = volume;
        this.issue = issue;
        this.pagecol = pagecol;
        this.publish = publish;
        this.pucity = pucity;
        this.pucntry = pucntry;
    }

    public Integer getRefid() {
        return refid;
    }

    public void setRefid(Integer refid) {
        this.refid = refid;
    }

    public Integer getPubtype() {
        return pubtype;
    }

    public void setPubtype(Integer pubtype) {
        this.pubtype = pubtype;
    }

    public Integer getPubdate() {
        return pubdate;
    }

    public void setPubdate(Integer pubdate) {
        this.pubdate = pubdate;
    }

    public String getAuthors() {
        return authors;
    }

    public void setAuthors(String authors) {
        this.authors = authors;
    }

    public String getEditors() {
        return editors;
    }

    public void setEditors(String editors) {
        this.editors = editors;
    }

    public String getAnalyt() {
        return analyt;
    }

    public void setAnalyt(String analyt) {
        this.analyt = analyt;
    }

    public String getMonogr() {
        return monogr;
    }

    public void setMonogr(String monogr) {
        this.monogr = monogr;
    }

    public String getSeries() {
        return series;
    }

    public void setSeries(String series) {
        this.series = series;
    }

    public String getVolume() {
        return volume;
    }

    public void setVolume(String volume) {
        this.volume = volume;
    }

    public String getIssue() {
        return issue;
    }

    public void setIssue(String issue) {
        this.issue = issue;
    }

    public String getPagecol() {
        return pagecol;
    }

    public void setPagecol(String pagecol) {
        this.pagecol = pagecol;
    }

    public String getPublish() {
        return publish;
    }

    public void setPublish(String publish) {
        this.publish = publish;
    }

    public String getPucity() {
        return pucity;
    }

    public void setPucity(String pucity) {
        this.pucity = pucity;
    }

    public String getPucntry() {
        return pucntry;
    }

    public void setPucntry(String pucntry) {
        this.pucntry = pucntry;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (refid != null ? refid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Bibrefs)) {
            return false;
        }
        Bibrefs other = (Bibrefs) object;
        if ((this.refid == null && other.refid != null) || (this.refid != null && !this.refid.equals(other.refid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.cimmyt.cril.ibworkbench.services.beans.Bibrefs[refid=" + refid + "]";
    }

}
