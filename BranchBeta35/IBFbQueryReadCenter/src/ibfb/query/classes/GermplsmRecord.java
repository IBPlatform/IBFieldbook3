
package ibfb.query.classes;

import java.io.Serializable;

public class GermplsmRecord implements Serializable {

    public int getCid() {
        return cid;
    }

    public void setCid(int cid) {
        this.cid = cid;
    }

    public int getGdate() {
        return gdate;
    }

    public void setGdate(int gdate) {
        this.gdate = gdate;
    }

    public int getGermuid() {
        return germuid;
    }

    public void setGermuid(int germuid) {
        this.germuid = germuid;
    }

    public int getGid() {
        return gid;
    }

    public void setGid(int gid) {
        this.gid = gid;
    }

    public int getGlocn() {
        return glocn;
    }

    public void setGlocn(int glocn) {
        this.glocn = glocn;
    }

    public int getGnpgs() {
        return gnpgs;
    }

    public void setGnpgs(int gnpgs) {
        this.gnpgs = gnpgs;
    }

    public int getGpid1() {
        return gpid1;
    }

    public void setGpid1(int gpid1) {
        this.gpid1 = gpid1;
    }

    public int getGpid2() {
        return gpid2;
    }

    public void setGpid2(int gpid2) {
        this.gpid2 = gpid2;
    }

    public int getGref() {
        return gref;
    }

    public void setGref(int gref) {
        this.gref = gref;
    }

    public int getGrplce() {
        return grplce;
    }

    public void setGrplce(int grplce) {
        this.grplce = grplce;
    }

    public int getLgid() {
        return lgid;
    }

    public void setLgid(int lgid) {
        this.lgid = lgid;
    }

    public int getMgid() {
        return mgid;
    }

    public void setMgid(int mgid) {
        this.mgid = mgid;
    }

    public int getSid() {
        return sid;
    }

    public void setSid(int sid) {
        this.sid = sid;
    }
    public int getMethn() {
        return methn;
    }

    public void setMethn(int methn) {
        this.methn = methn;
    }
        public int getGchange() {
        return gchange;
    }

    public void setGchange(int gchange) {
        this.gchange = gchange;
    }

    int gid;
    int gnpgs;
    int gpid1;
    int gpid2;
    int methn;
    int germuid;
    int lgid;
    int glocn;
    int gdate;
    int gref;
    int grplce;
    int mgid;
    int cid;
    int sid;
    int gchange;
}
