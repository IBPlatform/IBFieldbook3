
package ibfb.query.classes;

import java.io.Serializable;


public class NamesRecord implements Serializable {

    public int getNid() {
        return nid;
    }

    public void setNid(int nid) {
        this.nid = nid;
    }

    public int getGid() {
        return gid;
    }

    public void setGid(int gid) {
        this.gid = gid;
    }

    public int getNtype() {
        return ntype;
    }

    public void setNtype(int ntype) {
        this.ntype = ntype;
    }

    public int getNstat() {
        return nstat;
    }

    public void setNstat(int nstat) {
        this.nstat = nstat;
    }

    public int getNuid() {
        return nuid;
    }

    public void setNuid(int nuid) {
        this.nuid = nuid;
    }

    public String getNval() {
        return nval;
    }

    public void setNval(String nval) {
        this.nval = nval;
    }

    public int getNlocn() {
        return nlocn;
    }

    public void setNlocn(int nlocn) {
        this.nlocn = nlocn;
    }

    public int getNdate() {
        return ndate;
    }

    public void setNdate(int ndate) {
        this.ndate = ndate;
    }

    public int getNref() {
        return nref;
    }

    public void setNref(int nref) {
        this.nref = nref;
    }

    int nid;
    int gid;
    int ntype;
    int nstat;
    int nuid;
    String nval;
    int nlocn;
    int ndate;
    int nref;
}

