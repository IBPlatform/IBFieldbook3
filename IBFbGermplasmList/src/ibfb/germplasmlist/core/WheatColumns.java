package ibfb.germplasmlist.core;

/**
 * Constants used to manage grid for wheat crossing
 * @author TMSANCHEZ
 */
public class WheatColumns {
    public static final int ENTRY = 0;
    public static final int BCID = 1;
    public static final int FEMALE_PEDIGREE = 2;
    public static final int MALE_PEDIGREE = 3;    
    public static final int FEMALE_MALE = 4;
    public static final int GID = 5;
    public static final int METHOD = 6;
    public static final int FTID = 7;
    public static final int FOCC = 8;
    public static final int FENTRY = 9;
    public static final int FDESIG = 10;
    public static final int FGID = 11;
    public static final int MTID = 12;
    public static final int MOCC = 13;
    public static final int MENTRY = 14;
    public static final int MDESIG = 15;
    public static final int MGID = 16;
    
    public static int[] FEMALE_COLUMNS = {FEMALE_PEDIGREE,FTID,FOCC,FENTRY,FDESIG,FGID};
    public static int[] MALE_COLUMNS = {MALE_PEDIGREE,MTID,MOCC,MENTRY,MDESIG,MGID};
}
