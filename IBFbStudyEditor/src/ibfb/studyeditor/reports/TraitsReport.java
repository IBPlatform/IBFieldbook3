package ibfb.studyeditor.reports;

/**
 * Bean used to generate the bar code label report
 * @author TMSANCHEZ
 */
public class TraitsReport {
    private Integer trial;
    private Integer rep;
    private Integer block;
    private Integer subBlock;
    private Integer plot;
    private Integer entry;
    private Integer gid;
    private String desig;

    public TraitsReport() {
    }

    public TraitsReport(Integer trial, Integer rep, Integer block, Integer subBlock, Integer plot, Integer entry, Integer gid, String desig) {
        this.trial = trial;
        this.rep = rep;
        this.block = block;
        this.subBlock = subBlock;
        this.plot = plot;
        this.entry = entry;
        this.gid = gid;
        this.desig = desig;
    }

    public Integer getBlock() {
        return block;
    }

    public void setBlock(Integer block) {
        this.block = block;
    }

    public String getDesig() {
        return desig;
    }

    public void setDesig(String desig) {
        this.desig = desig;
    }

    public Integer getEntry() {
        return entry;
    }

    public void setEntry(Integer entry) {
        this.entry = entry;
    }

    public Integer getGid() {
        return gid;
    }

    public void setGid(Integer gid) {
        this.gid = gid;
    }

    public Integer getPlot() {
        return plot;
    }

    public void setPlot(Integer plot) {
        this.plot = plot;
    }

    public Integer getRep() {
        return rep;
    }

    public void setRep(Integer rep) {
        this.rep = rep;
    }

    public Integer getSubBlock() {
        return subBlock;
    }

    public void setSubBlock(Integer subBlock) {
        this.subBlock = subBlock;
    }

    public Integer getTrial() {
        return trial;
    }

    public void setTrial(Integer trial) {
        this.trial = trial;
    }
    
    
}
