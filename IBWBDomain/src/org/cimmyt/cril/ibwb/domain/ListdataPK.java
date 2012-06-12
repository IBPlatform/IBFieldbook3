/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.cimmyt.cril.ibwb.domain;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 *
 * @author jgcamarena
 */
@Embeddable
public class ListdataPK implements Serializable {

    @Basic(optional = false)
    @Column(name = "listid")
    private Integer listid;

    @Basic(optional = false)
    @Column(name = "lrecid")
    private Integer lrecid;

    public ListdataPK() {
    }

    public ListdataPK(Integer listid, Integer llrecid) {
        this.listid = listid;
        this.lrecid = llrecid;
    }

    public Integer getListid() {
        return listid;
    }

    public void setListid(Integer listid) {
        this.listid = listid;
    }

    public Integer getLrecid() {
        return lrecid;
    }

    public void setLrecid(Integer llrecid) {
        this.lrecid = llrecid;
    }
}
