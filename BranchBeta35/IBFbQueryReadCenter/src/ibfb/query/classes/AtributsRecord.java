

package ibfb.query.classes;

import java.io.Serializable;


public class AtributsRecord implements Serializable {

    public int getAid() {
        return aid;
    }

    public void setAid(int aid) {
        this.aid = aid;
    }

    public int getGid() {
        return gid;
    }

    public void setGid(int gid) {
        this.gid = gid;
    }

    public int getAtype() {
        return atype;
    }

    public void setAtype(int atype) {
        this.atype = atype;
    }

    public int getAuid() {
        return auid;
    }

    public void setAuid(int auid) {
        this.auid = auid;
    }

    public String getAval() {
        return aval;
    }

    public void setAval(String aval) {
        this.aval = aval;
    }

    public int getAlocn() {
        return alocn;
    }

    public void setAlocn(int alocn) {
        this.alocn = alocn;
    }

    public int getAref() {
        return aref;
    }

    public void setAref(int aref) {
        this.aref = aref;
    }

    public int getAdate() {
        return adate;
    }

    public void setAdate(int adate) {
        this.adate = adate;
    }

    int aid;
    int gid;
    int atype;
    int auid;
    String aval;
    int alocn;
    int aref;
    int adate;
}
