package org.cimmyt.cril.ibwb.domain.util;

import java.io.Serializable;

/**
 * Utility class to store informatin related to wheat data
 * @author TMSANCHEZ
 */
public class WheatData implements Serializable {
    private Integer gid;
    private String bcid;
    private String selectionHistory;
    private String crossName;

    public WheatData() {
    }

    public WheatData(Integer gid, String bcid, String selectionHistory, String crossName) {
        this.gid = gid;
        this.bcid = bcid;
        this.selectionHistory = selectionHistory;
        this.crossName = crossName;
    }

    public String getBcid() {
        return bcid;
    }

    public void setBcid(String bcid) {
        this.bcid = bcid;
    }

    public String getCrossName() {
        return crossName;
    }

    public void setCrossName(String crossName) {
        this.crossName = crossName;
    }

    public Integer getGid() {
        return gid;
    }

    public void setGid(Integer gid) {
        this.gid = gid;
    }

    public String getSelectionHistory() {
        return selectionHistory;
    }

    public void setSelectionHistory(String selectionHistory) {
        this.selectionHistory = selectionHistory;
    }

        
}
