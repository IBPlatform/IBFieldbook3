package org.cimmyt.cril.ibwb.domain.inventory;

/**
 *
 * @author TMSANCHEZ
 */
public class InventoryData {
    // default information to get TRAIT SEED STOCK
    public static final String TRAIT_SEED_STOCK_TRNAME = "Seed Stock";
    public static final String TRAIT_SEED_STOCK_TRABBR = "SeedS";
    public static final String TRAIT_SEED_STOCK_TRDESC = "Store the seed stock for inventory";
    public static final int    TRAIT_SEED_STOCK_TNSTAT = 0;
    public static final String TRAIT_SEED_STOCK_TRAITGROUP = "Inventory";
    public static final String TRAIT_SEED_STOCK_ONTOLOGY = "";
    public static final String TRAIT_SEED_STOCK_TRAITTYPE = "V";    
    
    private Integer entry;
    private String desig;
    private Integer gid;
    private Integer locationid;
    private String comment;
    private Double ammount;
    private Integer scale;
    
    private String locationName;
    private String scaleName;

    public InventoryData() {
    }

    public InventoryData(Integer entry, String desig, Integer gid, Integer locationid, String comment, Double ammount, Integer scale) {
        this.entry = entry;
        this.desig = desig;
        this.gid = gid;
        this.locationid = locationid;
        this.comment = comment;
        this.ammount = ammount;
        this.scale = scale;
    }

    public Double getAmmount() {
        return ammount;
    }

    public void setAmmount(Double ammount) {
        this.ammount = ammount;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
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

    public Integer getLocationid() {
        return locationid;
    }

    public void setLocationid(Integer locationid) {
        this.locationid = locationid;
    }

    public Integer getScale() {
        return scale;
    }

    public void setScale(Integer scale) {
        this.scale = scale;
    }

    @Override
    public String toString() {
        return "InventoryData{" + "entry=" + entry + ", desig=" + desig + ", gid=" + gid + ", locationid=" + locationid + ", comment=" + comment + ", ammount=" + ammount + ", scale=" + scale + '}';
    }

    public String getLocationName() {
        return locationName;
    }

    public void setLocationName(String locationName) {
        this.locationName = locationName;
    }

    public String getScaleName() {
        return scaleName;
    }

    public void setScaleName(String scaleName) {
        this.scaleName = scaleName;
    }
    
    
    
}
