package ibfb.domain.core;

import java.util.Date;

public class Study {
    public static final String S_TYPE_EXPERIMENT ="E";
    public static final String S_TYPE_NURSERY = "N";    
    public static final String S_TYPE_TRIAL = "T";        

    //---Campos agregados de la base de datos----
    private Integer studyid;
    private Integer investid;
    private Integer userid;
    private Integer sstatus;
    //-------------------------------------------
    private String study;
    private String title;
    private String pmkey;
    private String objective;
    private Date starDate;
    private Date endDate;
    private String studyType;
    private Integer instances;
    
    private Integer shierarchy;

    public Integer getShierarchy() {
        return shierarchy;
    }

    public void setShierarchy(Integer shierarchy) {
        this.shierarchy = shierarchy;
    }

    public Integer getInstances() {
        return instances;
    }

    public void setInstances(Integer instances) {
        this.instances = instances;
    }


    public Study() {
    }

    public Study(String study, String title, String pmkey, String objective, Date starDate, Date endDate, String studyType) {
        this.study = study;
        this.title = title;
        this.pmkey = pmkey;
        this.objective = objective;
        this.starDate = starDate;
        this.endDate = endDate;
        this.studyType = studyType;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public String getObjective() {
        return objective;
    }

    public void setObjective(String objective) {
        this.objective = objective;
    }

    public String getPmkey() {
        return pmkey;
    }

    public void setPmkey(String pmkey) {
        this.pmkey = pmkey;
    }

    public Date getStarDate() {
        return starDate;
    }

    public void setStarDate(Date starDate) {
        this.starDate = starDate;
    }

    public String getStudy() {
        return study;
    }

    public void setStudy(String study) {
        this.study = study;
    }

    public String getStudyType() {
        return studyType;
    }

    public void setStudyType(String studyType) {
        this.studyType = studyType;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public String toString() {
        return "Study{" + "study=" + study + "title=" + title + "pmkey=" + pmkey + "objective=" + objective + "starDate=" + starDate + "endDate=" + endDate + "studyType=" + studyType + '}';
    }
    
    public Integer getStudyid() {
        return studyid;
    }

    public void setStudyid(Integer studyid) {
        this.studyid = studyid;
    }
    
    public Integer getInvestid() {
        return investid;
    }

    public void setInvestid(Integer investid) {
        this.investid = investid;
    }
    
    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }
    
    public Integer getSstatus() {
        return sstatus;
    }

    public void setSstatus(Integer sstatus) {
        this.sstatus = sstatus;
    }
}
