package ibfb.query.core;

import com.sun.rowset.CachedRowSetImpl;
import ibfb.query.classes.AtributsRecord;
import ibfb.query.classes.GermplsmRecord;
import ibfb.query.classes.GpidInfClass;
import ibfb.query.classes.NamesRecord;
import ibfb.query.utils.ConnectionUtils;
import ibfb.query.utils.DatabaseInfo;
import ibfb.query.utils.DatabaseType;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sql.rowset.RowSetMetaDataImpl;
import org.ini4j.Ini;
import org.openide.modules.InstalledFileLocator;

public class QueryCenter {

    public byte sendConnections(Connection CGMS, Connection CDMS, Connection LGMS, Connection LDMS) {
        stmtCentralGMS = null;
        stmtLocalGMS = null;
        conCentralGMS = CGMS;
        conCentralDMS = CDMS;
        conLocalGMS = LGMS;
        conLocalDMS = LDMS;
        return 0;
    }
    /*
     * TODO LO RELACIONADO A PEDIGREE FLEXIBLE
     */

    public void readFlexPedConfig() {
        nstatOrderedList = "2,1,0";
        ntypeOrderedList = "2,5,7,6,17,4,1200,13";
        nuidExcludeList = "20,47,66,76,84,90";
        levelZeroFullName = true;
        initializePreferredNameRules();

    }

    public void initialize(QueryCenter qc) {
    }

    public void readAndLoadDatabases() {

        String dmsCentralName = "";
        String gmsCentralName = "";
        String dmsLocalName = "";
        String gmsLocalName = "";


        InstalledFileLocator locator = InstalledFileLocator.getDefault();
        File databaseConfigFile = locator.locate("modules/ext/databaseconfig.properties", "org.cimmyt.cril.ibwb.provider", false);
        Properties config = new Properties();
        try {

            InputStream streamProperties = new FileInputStream(databaseConfigFile);
            config.load(streamProperties);


            if (config.getProperty("dmscentral.driverclassname").equals("sun.jdbc.odbc.JdbcOdbcDriver")) {
                String databaseName = config.getProperty("dmscentral.url");
                int index = databaseName.lastIndexOf(":");
                databaseName = databaseName.substring(index + 1);
                dmsCentralName = databaseName;
            } else {
                String centralUrl = config.getProperty("dmscentral.url");
                DatabaseInfo databaseInfo = ConnectionUtils.getDatabaseInfo(centralUrl);
                dmsCentralName = databaseInfo.getDatabaseName();
            }

            if (config.getProperty("gmscentral.driverclassname").equals("sun.jdbc.odbc.JdbcOdbcDriver")) {
                String databaseName = config.getProperty("gmscentral.url");
                int index = databaseName.lastIndexOf(":");
                databaseName = databaseName.substring(index + 1);
                gmsCentralName = databaseName;
            } else {
                String centralUrl = config.getProperty("gmscentral.url");
                DatabaseInfo databaseInfo = ConnectionUtils.getDatabaseInfo(centralUrl);
                dmsCentralName = databaseInfo.getDatabaseName();
            }

            if (config.getProperty("dmslocal.driverclassname").equals("sun.jdbc.odbc.JdbcOdbcDriver")) {
                String databaseName = config.getProperty("dmslocal.url");
                int index = databaseName.lastIndexOf(":");
                databaseName = databaseName.substring(index + 1);
                dmsLocalName = databaseName;
            } else {
                String localUrl = config.getProperty("dmslocal.url");
                DatabaseInfo databaseInfo = ConnectionUtils.getDatabaseInfo(localUrl);
                dmsLocalName = databaseInfo.getDatabaseName();
            }

            if (config.getProperty("gmslocal.driverclassname").equals("sun.jdbc.odbc.JdbcOdbcDriver")) {
                String databaseName = config.getProperty("gmslocal.url");
                int index = databaseName.lastIndexOf(":");
                databaseName = databaseName.substring(index + 1);
                gmsLocalName = databaseName;
            } else {
                String localUrl = config.getProperty("gmslocal.url");
                DatabaseInfo databaseInfo = ConnectionUtils.getDatabaseInfo(localUrl);
                gmsLocalName = databaseInfo.getDatabaseName();

            }


            String dmsUserCentral = config.getProperty("dmscentral.username");
            String dmsPwdCentral = config.getProperty("dmscentral.password");
            String dmsCentralUrl = config.getProperty("dmscentral.url");

            String gmsUserCentral = config.getProperty("gmscentral.username");
            String gmsPwdCentral = config.getProperty("gmscentral.password");
            String gmsCentralUrl = config.getProperty("gmscentral.url");

            String dmsUserLocal = config.getProperty("dmslocal.username");
            String dmsPwdLocal = config.getProperty("dmslocal.password");
            String dmsLocalUrl = config.getProperty("dmslocal.url");

            String gmsUserLocal = config.getProperty("gmslocal.username");
            String gmsPwdLocal = config.getProperty("gmslocal.password");
            String gmsLocalUrl = config.getProperty("gmslocal.url");




            Connection dmsCentral = ConnectionUtils.getConnection(DatabaseType.MYSQL, dmsCentralUrl, dmsUserCentral, dmsPwdCentral);
            Connection gmsCentral = ConnectionUtils.getConnection(DatabaseType.MYSQL, gmsCentralUrl, gmsUserCentral, gmsPwdCentral);
            Connection dmsLocal = ConnectionUtils.getConnection(DatabaseType.MYSQL, dmsLocalUrl, dmsUserLocal, dmsPwdLocal);
            Connection gmsLocal = ConnectionUtils.getConnection(DatabaseType.MYSQL, gmsLocalUrl, gmsUserLocal, gmsPwdLocal);

            this.sendConnections(gmsCentral, dmsCentral, gmsLocal, dmsLocal);//central gms, central dms, local gms, local dms;
            System.out.println("TODO CORRECTO AL CARGAR CONEXIONES QUERY CENTER");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("error al cargar conexuones query center " + e);
        }


    }

    public boolean readFlexPedIniFile() {


        try {
            Ini ini = new Ini(new File(this.getClass().getClassLoader().getResource("ibfb/query/ini/flexped.INI").getFile()));
            nstatOrderedList = ini.fetch("flexped", "NSTAT");
            ntypeOrderedList = ini.fetch("flexped", "NTYPE");
            nuidExcludeList = ini.fetch("flexped", "NAMES_EXCLUDE_NUID");
            levelZeroFullName = false;
            if (ini.fetch("flexped", "LEVEL_ZERO_FULL_NAME").equals("1")) {
                levelZeroFullName = true;
            }
            initializePreferredNameRules();
            return true;
        } catch (IOException ex) {
            System.out.println("ERROR AL CARGAR VALORES DE FLEX INI");
            ex.getStackTrace();
            return false;
        }

    }

    private void initializePreferredNameRules() {
        int iTemp = 0;
        //nstatOrderList
        String[] nstatArrayString = nstatOrderedList.split(",");
        nstatWeightArray = new int[nstatArrayString.length + 1];
        nstatArray = new Integer[nstatArrayString.length + 1];
        int calculito = -1;
        int concecutiv = 0;
        for (iTemp = nstatArrayString.length; iTemp > 0; iTemp--) {
            try {
                concecutiv += 1;
                nstatWeightArray[iTemp] = concecutiv * 100;
                nstatArray[iTemp] = Integer.valueOf(nstatArrayString[iTemp - 1]);
                if (nstatArray[iTemp] == 2 && calculito == -1) {
                    setUseFullNameInPedigree(true);
                    calculito = 0;
                }
                if (nstatArray[iTemp] == 1 && calculito == -1) {
                    setUseFullNameInPedigree(false);
                    calculito = 0;
                }
            } catch (Exception e) {
            }
        }
        //ntypeOrderList
        String[] ntypeArrayString = ntypeOrderedList.split(",");
        ntypeWeightArray = new int[ntypeArrayString.length + 1];
        ntypeArray = new Integer[ntypeArrayString.length + 1];
        int iTemp1 = 0;
        for (iTemp = ntypeArrayString.length; iTemp > 0; iTemp--) {
            try {
                iTemp1 += 1;
                ntypeWeightArray[iTemp] = iTemp1;
                ntypeArray[iTemp] = Integer.valueOf(ntypeArrayString[iTemp - 1]);
            } catch (Exception e) {
            }
        }
        //ntypeOrderList
        String[] nuidArrayString = nuidExcludeList.split(",");
        nuidArray = new Integer[nuidArrayString.length + 1];
        for (iTemp = nuidArrayString.length; iTemp > 0; iTemp--) {
            try {
                nuidArray[iTemp] = Integer.valueOf(nuidArrayString[iTemp - 1]);
            } catch (Exception e) {
            }
        }
        //levelZeroFullName
    }

    private int giveNameValue(int p_ntypeX, int p_nstatX) {
        int x = 0;
        int pr = 0;
        boolean useFullNames = isUseFullNameInPedigree();
        int t_nstatVal1 = 0, t_nstatIndx1 = 0;
        int t_nstatVal2 = 0, t_nstatIndx2 = 0;
        for (x = 1; x < nstatArray.length; x++) {
            if (nstatArray[x] == 1) {
                t_nstatIndx1 = x;
                t_nstatVal1 = nstatWeightArray[t_nstatIndx1];
            }
            if (nstatArray[x] == 2) {
                t_nstatIndx2 = x;
                t_nstatVal2 = nstatWeightArray[t_nstatIndx2];
            }
        }
        if (useFullNames) {
            if (t_nstatIndx1 > t_nstatIndx2) {
                nstatWeightArray[t_nstatIndx1] = t_nstatVal2;
                nstatWeightArray[t_nstatIndx2] = t_nstatVal1;
            }
        } else {
            if (t_nstatIndx2 > t_nstatIndx1) {
                nstatWeightArray[t_nstatIndx2] = t_nstatVal1;
                nstatWeightArray[t_nstatIndx1] = t_nstatVal2;
            }
        }/*
         * if (useFullNames) { if (t_nstatIndx1 >t_nstatIndx2) {
         * nstatArray[t_nstatIndx1] = t_nstatIndx2; nstatArray[t_nstatIndx2] =
         * t_nstatIndx1; nstatWeightArray[t_nstatIndx1] = t_nstatVal2;
         * nstatWeightArray[t_nstatIndx2] = t_nstatVal1; } } else { if
         * (t_nstatIndx2 >t_nstatIndx1) { nstatArray[t_nstatIndx1] =
         * t_nstatIndx2; nstatArray[t_nstatIndx2] = t_nstatIndx1;
         * nstatWeightArray[t_nstatIndx2] = t_nstatVal1;
         * nstatWeightArray[t_nstatIndx1] = t_nstatVal2; } }
         */
        for (x = 1; x < ntypeArray.length; x++) {
            if (ntypeArray[x] == p_ntypeX) {
                pr = ntypeWeightArray[x];
                x = ntypeArray.length + 1;
            }
        }
        for (int y = 1; y < nstatArray.length; y++) {
            if (nstatArray[y] == p_nstatX) {
                pr += nstatWeightArray[y];
                y = ntypeArray.length + 1;
            }
        }
        if (t_nstatIndx1 != 0) {
            nstatWeightArray[t_nstatIndx1] = t_nstatVal1;
        }
        if (t_nstatIndx2 != 0) {
            nstatWeightArray[t_nstatIndx2] = t_nstatVal2;
        }
        return pr;
    }

    /**
     * Recursive procedure to generate the pedigree
     *
     * @param p_gid Input GID
     * @param nivel default zero
     * @param gpidinfClass empty class
     * @param fback default zero
     * @param mback default zero
     * @param Resp1 default zero
     * @param Resp2 default zero
     * @return
     * @throws Exception
     */
    public String arma_pedigree(int p_gid, int nivel, GpidInfClass gpidinfClass, int fback, int mback, int Resp1, int Resp2) throws Exception {
        String arma_pedigree = "";
        String ped = "";
        int xCurrent = 0;
        int xMax = 0;
        String xPrefName = "";
        String Delimiter = "";
        String cut = "";
        String p1 = "";
        String p2 = "";
        String[] oldp1 = new String[1];
        String[] oldp2 = new String[1];
        // oldp1[0] = p1;
        // oldp2[0] = p2;
        GpidInfClass fGpidInfClass = new GpidInfClass();
        GpidInfClass mGpidInfClass = new GpidInfClass();
        String LongStr;
        String ShortStr;
        boolean BackCrossFound = false;
        GermplsmRecord grTemp = new GermplsmRecord();
        try {
            int ret = armainfoGID(p_gid, grTemp);
            if (grTemp.getGid() == 0) {
                return "";
            }
            //listNL = Main.queryCenterTemp.getNamesList(grTemp.getGid());
            listNL = getNamesList(grTemp.getGid(), Arrays.asList(ntypeArray), Arrays.asList(nstatArray), Arrays.asList(nuidArray));
            // Determine if there was a Female or Male Backcross, that should be used in the pedigree, then the proper name of the
            // line can't be used. For instance if the name is 27217-A-4-8 and the pedigree is also MT/BRT and we are
            // called with mback representing BRT, then we must retrieve MT/BRT instead of 27217-A-4-8 to be able to reproduce
            // the name MT/2*BRT as the final pedigree. See example with GID=1935, before it generated 27217-A-4-8/BRT, now it
            // generates MT/2*BRT as expected.
            BackCrossFound = false;
            if ((grTemp.getGnpgs() == 2) && (grTemp.getGpid1() == fback) && (fback != 0)) {
                BackCrossFound = true;
            }
            if ((grTemp.getGnpgs() == 2) && (grTemp.getGpid2() == mback) && (mback != 0)) {
                BackCrossFound = true;
            }
            //' Also handle the CIMMYT retrocrosses A/B//A is A*2/B and B//A/B is A/2*B  JEN 2012-02-17
            if (grTemp.getGnpgs() == 2 && grTemp.getGpid2() == fback && fback != 0) {
                BackCrossFound = true;
            }
            if (grTemp.getGnpgs() == 2 && grTemp.getGpid1() == mback && mback != 0) {
                BackCrossFound = true;
            }
        } catch (Exception e) {
        }
        if ((!listNL.isEmpty()) && (grTemp.getGnpgs() == -1)) {
            // Name found, but we can only use it if not a backcross
            GermplsmRecord rs_back = new GermplsmRecord();
            if (grTemp.getGpid1() != 0) {
                int ret = armainfoGID(grTemp.getGpid1(), rs_back);
                if (rs_back.getGid() == 0) {
                    if ((fback != 0) && (fback == rs_back.getGpid1())) {
                        BackCrossFound = true;
                    }
                    if ((mback != 0) && (mback == rs_back.getGpid2())) {
                        BackCrossFound = true;
                    }
                    // New artificial backcrosses implemented 2 If's  JEN - 2012-02-13
                    if (fback != 0 && fback == rs_back.getGpid2()) {
                        BackCrossFound = true;
                    }
                    if (mback != 0 && mback == rs_back.getGpid1()) {
                        BackCrossFound = true;
                    }
                }
            }
        }
        // If the current GID is identical to one of the backcross GIDs that must be respected,
        // cancel it as a backross and find a proper name
        if ((Resp1 == p_gid) || (Resp2 == p_gid)) {
            BackCrossFound = false;
        }
        if ((!listNL.isEmpty()) && !(BackCrossFound)) {
            xMax = 0;
            for (NamesRecord namesrecord : listNL) {
                xCurrent = giveNameValue(namesrecord.getNtype(), namesrecord.getNstat());
                //Apply check if the LevelZeroFullName is true or not
                //If that is the case and we are at level zero, add 200 to xCurrent if NSTAT=1
                if ((nivel == 0) && levelZeroFullName && (namesrecord.getNstat() == 1)) {
                    xCurrent = xCurrent + 200;
                }
                if (xCurrent > xMax) {
                    xPrefName = namesrecord.getNval();
                    xMax = xCurrent;
                }
            }
            ped = xPrefName;
        } else {
            if ((grTemp.getGpid1() == 0) && (grTemp.getGpid2() == 0)) {
                ped = "Unknown";
            } else {
                if ((grTemp.getGnpgs() == -1) && (grTemp.getGpid2() != 0)) {
                    ped = arma_pedigree(grTemp.getGpid2(), nivel, gpidinfClass, fback, mback, Resp1, Resp2);
                } else if ((grTemp.getGnpgs() == -1) && (grTemp.getGpid1() != 0)) {
                    ped = arma_pedigree(grTemp.getGpid1(), nivel, gpidinfClass, fback, mback, Resp1, Resp2);
                } else {
                    gpidinfClass.gpid1 = grTemp.getGpid1();
                    gpidinfClass.gpid2 = grTemp.getGpid2();
                    if (grTemp.getGpid1() == fback) {
                        p1 = arma_pedigree(grTemp.getGpid1(), nivel + 1, fGpidInfClass, 0, 0, Resp1, Resp2);
                    } else {
                        p1 = arma_pedigree(grTemp.getGpid1(), nivel + 1, fGpidInfClass, 0, grTemp.getGpid2(), Resp1, Resp2);
                    }
                    if (grTemp.getGpid2() == mback) {
                        p2 = arma_pedigree(grTemp.getGpid2(), nivel + 1, mGpidInfClass, 0, 0, Resp1, Resp2);
                    } else {
                        p2 = arma_pedigree(grTemp.getGpid2(), nivel + 1, mGpidInfClass, grTemp.getGpid1(), 0, Resp1, Resp2);
                    }
                }
//         ' Since female/male backcross is a bit meaningless when IWIS2 false backrosses are handled, then
//         ' we just detect which part could be handled by length of p1 and p2, and if they are contained in
//         ' first part or last part of the other
                if (grTemp.getGpid1() == mGpidInfClass.gpid1 || grTemp.getGpid2() == fGpidInfClass.gpid1 || grTemp.getGpid2() == fGpidInfClass.gpid2 || grTemp.getGpid1() == mGpidInfClass.gpid2)//Handle Backcross
                {
                    veces_rep = 2;
                    if ((!p1.equals("")) && (!p2.equals(""))) {
                        if ((!p1.contains(p2)) && (!p2.contains(p1))) {
                            ped = "Houston we have a problem";
                            oldp1[0] = p1;
                            oldp2[0] = p2;
                            fGpidInfClass.gpid1 = 0;
                            fGpidInfClass.gpid2 = 0;
                            mGpidInfClass.gpid1 = 0;
                            mGpidInfClass.gpid2 = 0;
                            p1 = arma_pedigree(grTemp.getGpid1(), nivel + 1, fGpidInfClass, 0, 0, grTemp.getGpid1(), grTemp.getGpid2());
                            p2 = arma_pedigree(grTemp.getGpid2(), nivel + 1, mGpidInfClass, 0, 0, grTemp.getGpid1(), grTemp.getGpid2());
                            if ((!p1.contains(p2)) && (!p2.contains(p1))) {
                                ped = "Houston we have a BIG problem";
                                // Resolving situation of GID=29367 CID=22793 and GID=29456 CID=22881
                                // Female : CMH75A.66/2*CNO79  Male CMH75A.66/3*CNO79  Result: CMH75A.66/2*CNO79*2//CNO79
                                // Solution: convert p1 and p2 to the following
                                // p1 : CMH75A.66/2*CNO79  p2: CMH75A.66/2*CNO79//CNO79
                                //
                                // A valid way to pass parameters by reference according to http://www.cs.utoronto.ca/~dianeh/tutorials/params/swap.html
                               
                                GetParentsDoubleRetroCrossNew(oldp1, oldp2);
                                p1 = oldp1[0];
                                p2 = oldp2[0];
                            }
                        }
                        if (p1.length() > p2.length()) {
                            LongStr = p1;
                            ShortStr = p2;
                        } else {
                            LongStr = p2;
                            ShortStr = p1;
                        }
                        if (LongStr.substring(0, ShortStr.length()).equals(ShortStr)) {
                            //' Handle female type of backcross
                            cut = LongStr.substring(ShortStr.length());
                            if (cut.startsWith("/")) {
                            } else if (cut.startsWith("*")) {
                                if (cut.substring(2, 3).equals("/")) {
                                    veces_rep = Integer.valueOf(cut.substring(1, 2)); //
                                    cut = cut.substring(2);
                                } else {
                                    veces_rep = Integer.valueOf(cut.substring(1, 3));
                                    cut = cut.substring(3);
                                }
                                veces_rep = veces_rep + 1;
                            }
                            ped = ShortStr + "*" + veces_rep + cut;
                        }
                        if (LongStr.substring(LongStr.length() - ShortStr.length()).equals(ShortStr)) {
                            //' Handle male type of backcross
                            cut = LongStr.substring(0, LongStr.length() - ShortStr.length());
                            if (cut.endsWith("/")) {
                            } else if (cut.endsWith("*")) {
                                if (cut.substring(cut.length() - 3, cut.length() - 2).equals("/")) {
                                    veces_rep = Integer.valueOf(cut.substring(cut.length() - 2, cut.length() - 1));
                                    cut = cut.substring(0, (cut.length() - 2));
                                } else {
                                    veces_rep = Integer.valueOf(cut.substring(cut.length() - 3, cut.length() - 1));
                                    cut = cut.substring(0, cut.length() - 3);
                                }
                                veces_rep = veces_rep + 1;
                            }
                            ped = cut + veces_rep + "*" + ShortStr;
                        }
                    }
                }
                if ((grTemp.getGpid1() != mGpidInfClass.gpid1) && (grTemp.getGpid1() != fGpidInfClass.gpid1) && (grTemp.getGpid2() != fGpidInfClass.gpid2) && (grTemp.getGpid2() != fGpidInfClass.gpid2) && (grTemp.getGpid2() != mGpidInfClass.gpid2)) {
                    if (((!p1.equals("")) || (!p2.equals(""))) && (ped.equals(""))) {
                        if (p1.equals("")) {
                            p1 = "Missing";
                        }
                        if (p2.equals("")) {
                            p2 = "Missing";
                        }
                        Delimiter = GetNewDelimiter(p1 + p2).toString();
                        ped = p1 + Delimiter + p2;
                    }
                }
            }
        }
//        System.out.println( nivel + " : " + p_gid + ":" + ped );
        arma_pedigree = ped;
        return arma_pedigree;
    }

    private String GetNewDelimiter(String xPed) throws Exception {
        String GetNewDelimiter = "";
        int x = 0;
        int MaxLevel = 0;
        String cad = "";
        for (x = 32; x >= 1; x--) {
            cad = "/" + String.valueOf(x) + "/";
            if (x == 2) {
                cad = "//";
            }
            if (x == 1) {
                cad = "/";
            }
            if (xPed.contains(cad)) {
                MaxLevel = x;
                x = 0;
            }
        }
        // New level must be one higher than current one found
        MaxLevel = MaxLevel + 1;
        GetNewDelimiter = "/" + String.valueOf(MaxLevel) + "/";
        if (MaxLevel == 1) {
            GetNewDelimiter = "/";
        }
        if (MaxLevel == 2) {
            GetNewDelimiter = "//";
        }
        return GetNewDelimiter;
    }

    private void GetParentsDoubleRetroCross(String[] Mxp1, String[] Mxp2) {
        // Resolving situation of GID=29367 CID=22793 and GID=29456 CID=22881
        // Female : CMH75A.66/2*CNO79  Male CMH75A.66/3*CNO79  Result: CMH75A.66/2*CNO79*2//CNO79
        // Solution:
        // p1 : CMH75A.66/2*CNO79  p2: CMH75A.66/2*CNO79//CNO79
        // JEN  2012-07-16
        String xp1 = Mxp1[0];
        String xp2 = Mxp2[0];
        String BeforeStr = "";
        String AfterStr = "";
        String Delimiter = "";
        String CutStr = "";
        String CleanBefore = "";
        String CleanAfter = "";
        int x = 0;
        int y = 0;
        int Lev1 = 0;
        int Lev2 = 0;
        String xDel = "";
        String A = "";
        String B = "";
        // Default is true - 50% chance it is right
        boolean SlashLeft = true;  //  /2*A is SlashLeft, and A*2/ is not SlashLeft
        boolean Changed = false;   //
        for (x = 1; x <= xp1.length(); x++) {
            if (xp1.substring(x - 1, x).equals(xp2.substring(x - 1, x))) {
                BeforeStr = BeforeStr + xp1.substring(x - 1, x);
            } else {
                Lev1 = 0;
                x = BeforeStr.length() + 1;
                xDel = BeforeStr.substring(BeforeStr.length() - 1, BeforeStr.length());
                if (BeforeStr.length() > 1) {
                    if (isNumeric(BeforeStr.substring(BeforeStr.length() - 1, BeforeStr.length())) && BeforeStr.endsWith("*")) {
                        Lev1 = Integer.valueOf(BeforeStr.substring(BeforeStr.length() - 1, BeforeStr.length()));
                        xDel = "*";
                    }
                }
                if (xp1.substring(x - 1, x).equals("*")) {
                    xDel = "*";
                    x = x + 1;
                }
                if (xDel.equals("*") || xDel.equals("/")) {
                    for (y = x; y < xp1.length(); y++) {
                        if (!isNumeric(xp1.substring(y - 1, y))) {
                            // Exit for
                            break;
                        }
                        Lev1 = (Lev1 * 10) + Integer.valueOf(xp1.substring(y - 1, y));
                        //  Take off leading offset in AfterStr if that has now been put in Lev2
                        if ((xp1.length() - y) < AfterStr.length()) {
                            AfterStr = AfterStr.substring(1, AfterStr.length());
                        }
                    }
                    if (Lev1 == 0) {
                        Lev1 = 1;
                    }
                    // Exit for
                    break;
                }
            }
        }
        AfterStr = "";
        if (xp1.length() < xp2.length()) {
            CutStr = xp2.substring(xp2.length() - xp1.length()); //xp2.length() - xp1.length(),xp1.length());
        } else {
            //  CutStr = String.format("%1$", Mxp2)"                        ".substring(0, xp2.length() - xp1.length()) + xp2;
            //  CutStr = String.format("%1$" + (xp2.length() - xp1.length()) + "s", xp2);
            CutStr = String.format("%" + (-(xp2.length() - xp1.length())) + "s", xp2);
        }
        for (x = xp1.length(); x > 0; x--) {
            if (xp1.substring(x - 1, x).equals(CutStr.substring(x - 1, x))) {
                AfterStr = xp1.substring(x - 1, x) + AfterStr;
            } else {
                Lev2 = 0;
                x = BeforeStr.length() + 1;
                if (BeforeStr.length() > 1) {
                    // This criteria has problems 2012-07-17
                    if (isNumeric(BeforeStr.substring(BeforeStr.length() - 1, BeforeStr.length())) && BeforeStr.endsWith("*")) {
                        Lev2 = Integer.valueOf(BeforeStr.substring(BeforeStr.length() - 1, BeforeStr.length()));
                        xDel = "*";
                    }
                }
                if (xp2.substring(BeforeStr.length(), BeforeStr.length() + 1).equals("*")) {
                    xDel = "*";
                    x = BeforeStr.length() + 2;
                }
                if (xDel.equals("*") || xDel.equals("/")) {
                    for (y = x; y < xp2.length(); y++) {
                        // This criteria has problems 2012-07-17
                        if (!isNumeric(xp2.substring(y - 1, y))) {
                            // Exit for
                            break;
                        }
                        Lev2 = (Lev2 * 10) + Integer.valueOf(xp2.substring(y - 1, y));
                        //  Take off leading offset in AfterStr if that has now been put in Lev2
                        if ((xp2.length() - y) < AfterStr.length()) {
                            AfterStr = AfterStr.substring(1, AfterStr.length());
                        }
                    }
                }
                if (Lev2 == 0) {
                    Lev2 = 1;
                    Changed = true;
                    while ((AfterStr.length() > 2) && Changed) {
                        Changed = false;
                        A = AfterStr.substring(0, 1);
                        B = AfterStr.substring(1, 2);
                        if ((A.equals("/") || (isNumeric(A))) && (B.equals("/") || (isNumeric(B)))) {
                            AfterStr = AfterStr.substring(1, AfterStr.length());
                            Changed = true;
                        }
                    }
                }
                // Exit for
                break;
            }
        }
        //if (AfterStr.substring(0,1).equals("/")) {
        if (AfterStr.startsWith("/")) {
            SlashLeft = false;
        }
        CleanAfter = AfterStr;
        CleanBefore = BeforeStr;
        // Fixing CleanAfter
        // if (CleanAfter.substring(0,1).equals("*") || CleanAfter.substring(0,1).equals("/")) {
        if (CleanAfter.startsWith("*") || CleanAfter.startsWith("/")) {
            CleanAfter = CleanAfter.substring(1, CleanAfter.length() - 1);
        }
        // Fixing CleanBefore
        if (CleanBefore.length() > 1) {
            // This criteria has problems 2012-07-19
            if (isNumeric(CleanBefore.substring(CleanBefore.length() - 1, CleanBefore.length())) && ((CleanBefore.substring(CleanBefore.length() - 2, CleanBefore.length() - 1).equals("/")) || (CleanBefore.substring(CleanBefore.length() - 1, CleanBefore.length()).equals("*")))) {
                CleanBefore = CleanBefore.substring(0, CleanBefore.length() - 2);
            }
        }
        try {
            Delimiter = GetNewDelimiter(xp1 + xp2);
        } catch (Exception e) {
            System.out.println("Error en GetNewDelimiter " + e);
        }
        if (Lev1 > Lev2) {
            if (SlashLeft) {
                xp1 = xp2 + Delimiter + CleanAfter;
            } else {
                xp1 = CleanBefore + Delimiter + xp2;
            }
            if (Lev2 > Lev1) {
                if (SlashLeft) {
                    xp2 = xp1 + Delimiter + CleanAfter;
                } else {
                    xp2 = CleanBefore + Delimiter + xp1;
                }
            }
        }
        Mxp1[0] = xp1;
        Mxp2[0] = xp2;
    }
// private static boolean esNumeric(final String s) {
//    if (s.equals("")) return false;
//    for (int x = 0; x < s.length(); x++) {
//       final char c = s.charAt(x);
//       if (x == 0 && (c == '-')) continue;  // negative
//       if ((c >= '0') && (c <= '9')) continue;  // 0 - 9
//       return false; // invalid
//    }
//  return true; // valid
//}

    
     private void GetParentsDoubleRetroCrossNew(String[] Mxp1, String[] Mxp2) { //24 agosto 2012, Medificaciones por Jesper
        // Resolving situation of GID=29367 CID=22793 and GID=29456 CID=22881
        // Female : CMH75A.66/2*CNO79  Male CMH75A.66/3*CNO79  Result: CMH75A.66/2*CNO79*2//CNO79
        // Solution:
        // p1 : CMH75A.66/2*CNO79  p2: CMH75A.66/2*CNO79//CNO79
        // JEN  2012-07-16
        String xp1 = Mxp1[0];
        String xp2 = Mxp2[0];
        String BeforeStr = "";
        String AfterStr = "";
        String Delimiter = "";
        String CutStr = "";
        String CleanBefore = "";
        String CleanAfter = "";
        int x = 0;
        int y = 0;
        int xx = 0;
        int Lev1 = 0;
        int Lev2 = 0;
        String xDel = "";
        String A = "";
        String B = "";
        // Default is true - 50% chance it is right
        boolean SlashLeft = true;  //  /2*A is SlashLeft, and A*2/ is not SlashLeft
        boolean Changed = false;   //
        for (x = 1; x <= xp1.length(); x++) {
            if (xp1.substring(x - 1, x).equals(xp2.substring(x - 1, x))) {
                BeforeStr = BeforeStr + xp1.substring(x - 1, x);
            } else {
                Lev1 = 0;
                xx = BeforeStr.length() + 1;
                xDel = BeforeStr.substring(BeforeStr.length() - 1, BeforeStr.length());
                if (BeforeStr.length() > 1) {
                    if (isNumeric(BeforeStr.substring(BeforeStr.length() - 1, BeforeStr.length())) && BeforeStr.endsWith("*")) {
                        Lev1 = Integer.valueOf(BeforeStr.substring(BeforeStr.length() - 1, BeforeStr.length()));
                        xDel = "*";
                    }
                }
                if (xp1.substring(xx - 1, xx).equals("*")) {
                    xDel = "*";
                    xx = xx + 1;
                }
                if (xDel.equals("*") || xDel.equals("/")) {
                    for (y = xx; y < xp1.length(); y++) {
                        if (!isNumeric(xp1.substring(y - 1, y))) {
                            // Exit for
                            break;
                        }
                        Lev1 = (Lev1 * 10) + Integer.valueOf(xp1.substring(y - 1, y));
                        //  Take off leading offset in AfterStr if that has now been put in Lev2
                        if ((xp1.length() - y) < AfterStr.length()) {
                                AfterStr = AfterStr.substring(1, AfterStr.length());
                        }
                    }
                    if (Lev1 == 0) {
                        Lev1 = 1;
                    }
                }
                // Exit for
                break;
            }
        }
        AfterStr = "";
        if (xp1.length() < xp2.length()) {
            CutStr = xp2.substring(xp2.length() - xp1.length());
        } else {
            CutStr = String.format("%" + xp1.length() + "s", xp2);
        }
        for (x = xp1.length(); x > 0; x--) {
            if (xp1.substring(x - 1, x).equals(CutStr.substring(x - 1, x))) {
                AfterStr = xp1.substring(x - 1, x) + AfterStr;
            } else {
                Lev2 = 0;
                xx = BeforeStr.length() + 1;
                if (BeforeStr.length() > 1) {
                    // This criteria has problems 2012-07-17
                    if (isNumeric(BeforeStr.substring(BeforeStr.length() - 1, BeforeStr.length())) && BeforeStr.endsWith("*")) {
                        Lev2 = Integer.valueOf(BeforeStr.substring(BeforeStr.length() - 1, BeforeStr.length()));
                        xDel = "*";
                    }
                }
                if (xp2.substring(BeforeStr.length(), BeforeStr.length() + 1).equals("*")) {
                    xDel = "*";
                    xx = BeforeStr.length() + 2;
                }
                if (xDel.equals("*") || xDel.equals("/")) {
                    for (y = xx; y < xp2.length(); y++) {
                        // This criteria has problems 2012-07-17
                        if (!isNumeric(xp2.substring(y - 1, y))) {
                            // Exit for
                            break;
                        }
                        Lev2 = (Lev2 * 10) + Integer.valueOf(xp2.substring(y - 1, y));
                        //  Take off leading offset in AfterStr if that has now been put in Lev2
                        if ((xp2.length() - y) < AfterStr.length()) {
                            AfterStr = AfterStr.substring(1, AfterStr.length());
                        }
                    }
                }
                if (Lev2 == 0) {
                    Lev2 = 1;
                    Changed = true;
                    while ((AfterStr.length() > 2) && Changed) {
                        Changed = false;
                        A = AfterStr.substring(0, 1);
                        B = AfterStr.substring(1, 2);
                        if ((A.equals("/") || (isNumeric(A))) && (B.equals("/") || (isNumeric(B)))) {
                            AfterStr = AfterStr.substring(1, AfterStr.length());
                            Changed = true;
                        }
                    }
                }
                // Exit for
                break;
            }
        }
        //if (AfterStr.substring(0,1).equals("/")) {
        if (AfterStr.startsWith("/")) {
            SlashLeft = false;
        }
        CleanAfter = AfterStr;
        CleanBefore = BeforeStr;
        // Fixing CleanAfter
        // if (CleanAfter.substring(0,1).equals("*") || CleanAfter.substring(0,1).equals("/")) {
        if (CleanAfter.startsWith("*") || CleanAfter.startsWith("/")) {
            CleanAfter = CleanAfter.substring(1, CleanAfter.length());
        }
        // Fixing CleanBefore
        if (CleanBefore.length() > 1) {
            // This criteria has problems 2012-07-19
            if (isNumeric(CleanBefore.substring(CleanBefore.length() - 1, CleanBefore.length())) && ((CleanBefore.substring(CleanBefore.length() - 2, CleanBefore.length() - 1).equals("/")) || (CleanBefore.substring(CleanBefore.length() - 1, CleanBefore.length()).equals("*")))) {
                CleanBefore = CleanBefore.substring(0, CleanBefore.length() - 2);
            }
        }
        try {
            Delimiter = GetNewDelimiter(xp1 + xp2);
        } catch (Exception e) {
            System.out.println("Error en GetNewDelimiter " + e);
        }
        if (Lev1 > Lev2) {
            if (SlashLeft) {
                xp1 = xp2 + Delimiter + CleanAfter;
            } else {
                xp1 = CleanBefore + Delimiter + xp2;
            }
        }
        if (Lev2 > Lev1) {
            if (SlashLeft) {
                xp2 = xp1 + Delimiter + CleanAfter;
            } else {
                xp2 = CleanBefore + Delimiter + xp1;
            }
        }
        Mxp1[0] = xp1;
        Mxp2[0] = xp2;
    }


    
    
    
    
    
    private boolean isNumeric(String s) {
        boolean isNumeric = false;

        try {
            int x = Integer.parseInt(s);
            isNumeric = true;
        } catch (Exception e) {
            isNumeric = false;
        }
        return isNumeric;
    }
    /*
     * TODO LO RELACIONADO A PEDIGREE FLEXIBLE
     */

    private byte armainfoGID(int p_gid, GermplsmRecord p_gr) {// "GERMPLSM"
        ResultSet rsTemp;
        int p_grplce = 0;
        String parasource1 = "select * from germplsm where gid<>GRPLCE and gid =" + p_gid;
        p_gr.setGid(0);
        rsTemp = null;
        boolean tiene_registro = false;
        if (p_gid < 0) {
            try {
                if (stmtLocalGMS == null) {
                    stmtLocalGMS = conLocalGMS.createStatement();
                }
                rsTemp = stmtLocalGMS.executeQuery(parasource1);
                if (rsTemp.next()) {
                    tiene_registro = true;
                }
            } catch (SQLException sqle) {
                System.out.println("local p_gid<0:" + sqle.toString());
            }
        } else {
            try {
                if (stmtCentralGMS == null) {
                    stmtCentralGMS = conCentralGMS.createStatement();
                }
                rsTemp = stmtCentralGMS.executeQuery(parasource1);
                if (rsTemp.next()) {
                    tiene_registro = true;
                    try {
                        p_gr.setGchange(rsTemp.getInt("GCHANGE"));// CENTRAL
                    } catch (Exception e) {
                    }
                }
            } catch (SQLException sqle) {
                System.out.println("central p_gid>0" + sqle.toString());
            }
        }
        if (tiene_registro) {
            try {
                int new_gid = 0;
                p_grplce = rsTemp.getInt("GRPLCE");
                p_gr.setGid(rsTemp.getInt("GID"));
                p_gr.setMethn(rsTemp.getInt("METHN"));
                p_gr.setGnpgs(rsTemp.getInt("GNPGS"));
                p_gr.setGpid1(rsTemp.getInt("GPID1"));
                p_gr.setGpid2(rsTemp.getInt("GPID2"));
                p_gr.setGermuid(rsTemp.getInt("GERMUID"));
                p_gr.setLgid(rsTemp.getInt("LGID"));
                p_gr.setGlocn(rsTemp.getInt("GLOCN"));
                p_gr.setGdate(rsTemp.getInt("GDATE"));
                p_gr.setGref(rsTemp.getInt("GREF"));
                p_gr.setGrplce(p_grplce);
                p_gr.setMgid(rsTemp.getInt("MGID"));
                try {
                    p_gr.setCid(rsTemp.getInt("CID")); // OPCIONAL
                    p_gr.setSid(rsTemp.getInt("SID")); // OPCIONAL
                } catch (Exception e) {
                    p_gr.setCid(0); // OPCIONAL
                    p_gr.setSid(0); // OPCIONAL
                }
                if (p_grplce == 0) {
                    new_gid = getReplaceGID(p_gid);
                }
                if (new_gid == p_gid) {
                    new_gid = 0;
                } else {
                    if (p_grplce != 0 && p_grplce != p_gid) {
                        new_gid = p_grplce;
                    }
                }
                if (new_gid != 0) {
                    byte ret = armainfoGID(new_gid, p_gr);
                } else {
                    byte ret1 = getFieldsUpdatedGermplsm(p_gid, p_gr);
                }
            } catch (SQLException ex) {
                Logger.getLogger(QueryCenter.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            System.out.println("Lo sentimos no existe informacion para el gid " + p_gid);
        }
        return 0;
    }

    private byte getFieldsUpdatedGermplsm(int p_crecord, GermplsmRecord p_gr) {
        byte pr = 0;
        String t_cfield = "";
        ResultSet rsTemp1 = null;
        try {
            if (stmtLocalGMS == null) {
                stmtLocalGMS = conLocalGMS.createStatement();
            }
            pq = "select cfield, cto from changes where ctable='GERMPLSM' and cstatus=0 and crecord =" + p_crecord + " order by cdate,ctime;";
            rsTemp1 = stmtLocalGMS.executeQuery(pq);
            while (rsTemp1.next()) {
                t_cfield = rsTemp1.getString("cfield").trim().toUpperCase();
                if (t_cfield.equals("METHN")) {
                    p_gr.setMethn(rsTemp1.getInt("cto"));
                }
                if (t_cfield.equals("GNPGS")) {
                    p_gr.setGnpgs(rsTemp1.getInt("cto"));
                }
                if (t_cfield.equals("GPID1")) {
                    p_gr.setGpid1(rsTemp1.getInt("cto"));
                }
                if (t_cfield.equals("GPID2")) {
                    p_gr.setGpid2(rsTemp1.getInt("cto"));
                }
                if (t_cfield.equals("GERMUID")) {
                    p_gr.setGermuid(rsTemp1.getInt("cto"));
                }
                if (t_cfield.equals("LGID")) {
                    p_gr.setLgid(rsTemp1.getInt("cto"));
                }
                if (t_cfield.equals("GLOCN")) {
                    p_gr.setGlocn(rsTemp1.getInt("cto"));
                }
                if (t_cfield.equals("GDATE")) {
                    p_gr.setGdate(rsTemp1.getInt("cto"));
                }
                if (t_cfield.equals("GREF")) {
                    p_gr.setGref(rsTemp1.getInt("cto"));
                }
                if (t_cfield.equals("GRPLCE")) {
                    p_gr.setGrplce(rsTemp1.getInt("cto"));
                }
                if (t_cfield.equals("MGID")) {
                    p_gr.setMgid(rsTemp1.getInt("cto"));
                }
                if (t_cfield.equals("CID")) {
                    p_gr.setCid(rsTemp1.getInt("cto"));
                }
                if (t_cfield.equals("SID")) {
                    p_gr.setSid(rsTemp1.getInt("cto"));
                }
            }
            return 0;
        } catch (SQLException sqle) {
            System.out.println("getFieldsUpdatedGermplsm error:" + sqle.toString());
            return 0;
        }
    }

    private int getReplaceGID(int p_crecord) {
        int pr = 0;
        ResultSet rs11 = null;
        try {
            if (stmtLocalGMS == null) {
                stmtLocalGMS = conLocalGMS.createStatement();
            }
            pq = "select cto from changes where cstatus=0 and ctable ='GERMPLSM' and cfield='GID' and crecord =" + p_crecord + " order by cdate desc , ctime desc;";
            rs11 = stmtLocalGMS.executeQuery(pq);
            if (rs11.next()) {
                pr = rs11.getInt("cto");
            } else {
                rs11 = null;
                pq = "select cto from changes where cstatus=0 and ctable ='GERMPLSM' and cfield='grplce' and crecord =" + p_crecord + " order by cdate desc , ctime desc;";
                rs11 = stmtLocalGMS.executeQuery(pq);
                if (rs11.next()) {
                    pr = rs11.getInt("cto");
                }
            }
        } catch (SQLException sqle) {
            System.out.println("get remplaceGID:" + sqle.toString());
            throw sqle;
        } finally {
            return pr;
        }
    }

    public GermplsmRecord getGermplsmRecord(int p_gid) {
        GermplsmRecord gr = new GermplsmRecord();
        byte ret = armainfoGID(p_gid, gr);
        return gr;
    }

    private byte armainfoAID(int p_gid, AtributsRecord p_ar) {//'ATRIBUTS"   "GERMPLSM"   "NAMES"
        ResultSet rsTemp = null;
        String parasource1 = "select * from atributs where gid =" + p_gid;
        p_ar.setGid(0);
        boolean tiene_registro = false;
        if (p_gid < 0) {
            try {
                if (stmtLocalGMS == null) {
                    stmtLocalGMS = conLocalGMS.createStatement();
                }
                rsTemp = stmtLocalGMS.executeQuery(parasource1);
                if (rsTemp.next()) {
                    tiene_registro = true;
                }
            } catch (SQLException sqle) {
                System.out.println("aid p_gid:" + sqle.toString());
            }
        } else {
            try {
                if (stmtCentralGMS == null) {
                    stmtCentralGMS = conCentralGMS.createStatement();
                }
                rsTemp = stmtCentralGMS.executeQuery(parasource1);
                if (rsTemp.next()) {
                    tiene_registro = true;
                }
            } catch (SQLException sqle) {
                System.out.println("aid p_gid>0" + sqle.toString());
            }
        }
        if (tiene_registro) {
            try {
                p_ar.setAid(rsTemp.getInt("AID"));
                p_ar.setGid(rsTemp.getInt("GID"));
                p_ar.setAtype(rsTemp.getInt("ATYPE"));
                p_ar.setAuid(rsTemp.getInt("AUID"));
                p_ar.setAval(rsTemp.getString("AVAL"));
                p_ar.setAlocn(rsTemp.getInt("ALOCN"));
                p_ar.setAref(rsTemp.getInt("AREF"));
                p_ar.setAdate(rsTemp.getInt("ADATE"));
            } catch (SQLException ex) {
                Logger.getLogger(QueryCenter.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            System.out.println("Lo sentimos no existe informacion en attributs para el gid " + p_gid);
        }
        return 0;
    }

    public AtributsRecord getAtributsRecord(int p_gid) {
        AtributsRecord ar = new AtributsRecord();
        byte ret = armainfoAID(p_gid, ar);
        return ar;
    }

    private byte armainfoNIDRecord(int p_nid, NamesRecord p_nr) {// "NAMES" pendiente obtener inf actualizada
        ResultSet rsTemp;
        String parasource1 = "select * from names where nid =" + p_nid;
        p_nr.setNid(0);
        rsTemp = null;
        boolean tiene_registro = false;
        if (p_nid < 0) {
            try {
                if (stmtLocalGMS == null) {
                    stmtLocalGMS = conLocalGMS.createStatement();
                }
                rsTemp = stmtLocalGMS.executeQuery(parasource1);
                if (rsTemp.next()) {
                    tiene_registro = true;
                }
            } catch (SQLException sqle) {
                System.out.println("nid p_gid<0:" + sqle.toString());
            }
        } else {
            try {
                if (stmtCentralGMS == null) {
                    stmtCentralGMS = conCentralGMS.createStatement();
                }
                rsTemp = stmtCentralGMS.executeQuery(parasource1);
                if (rsTemp.next()) {
                    tiene_registro = true;
                }
            } catch (SQLException sqle) {
                System.out.println("nid p_gid>0:" + sqle.toString());
            }
        }
        if (tiene_registro) {
            try {
                p_nr.setNid(rsTemp.getInt("NID"));
                p_nr.setGid(rsTemp.getInt("GID"));
                p_nr.setNtype(rsTemp.getInt("NTYPE"));
                p_nr.setNuid(rsTemp.getInt("NUID"));
                p_nr.setNval(rsTemp.getString("NVAL"));
                p_nr.setNlocn(rsTemp.getInt("NLOCN"));
                p_nr.setNstat(rsTemp.getInt("NSTAT"));
                p_nr.setNref(rsTemp.getInt("NREF"));
                p_nr.setNdate(rsTemp.getInt("NDATE"));
                int replaceNID = getReplaceNID(p_nr);
                if (replaceNID != 0) {
                    byte ret2 = armainfoNIDRecord(replaceNID, p_nr);
                }
                if (p_nr.getNid() > 0) {
                    getFieldsUpdatedNames(p_nr);
                }
            } catch (SQLException ex) {
                Logger.getLogger(QueryCenter.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            System.out.println("Lo sentimos no existe informacion en names para el nid " + p_nid);
        }
        return 0;
    }

    private int getReplaceNID(NamesRecord p_nr) {
        int pr = 0;
        ResultSet rs12;
        rs12 = null;
        try {
            if (stmtLocalGMS == null) {
                stmtLocalGMS = conLocalGMS.createStatement();
            }
            pq = "select cto from changes where cstatus=0 and ctable ='names' and cfield='nval' and crecord =" + p_nr.getGid() + " and  cfrom=" + p_nr.getNid() + ";";
            rs12 = stmtLocalGMS.executeQuery(pq);
            if (rs12.next()) {
                pr = rs12.getInt("cto");
            }
        } catch (SQLException sqle) {
            System.out.println("get remplaceGID:" + sqle.toString());
            throw sqle;
        } finally {
            return pr;
        }
    }

    public ArrayList getNamesList(int p_gid) {
        //observacion regresa nombres tomando en cuenta changes (bases local y central),
        //no regresa nombres con status=9
        ArrayList pr = null;
        pr = new ArrayList();
        ResultSet rsTemp = null;
        String parasource1 = "select nid from names where gid =" + p_gid;
        try {
            Statement statement = null;
            if (p_gid < 0) {
                statement = conLocalGMS.createStatement();
            } else {
                statement = conCentralGMS.createStatement();
            }
            rsTemp = statement.executeQuery(parasource1);
            while (rsTemp.next()) {
                NamesRecord nr = new NamesRecord();
                byte ret2 = armainfoNIDRecord(rsTemp.getInt("nid"), nr);
                if (nr.getNstat() != 9) {
                    pr.add(nr);
                }
                nr = null;
            }
            if (pr == null) {
                System.out.println("Lo sentimos no existe informacion en names para el gid " + p_gid);
            }
            statement = null;
        } catch (Exception e) {
            System.out.println("p_gid=" + p_gid + ":" + e.toString());
        }
        return pr;
    }

    public ArrayList getNamesList(int p_gid, List<Integer> ntypeArray, List<Integer> nstatArray) {
        //observacion regresa nombres tomando en cuenta changes (bases local y central), y no agrega los que no esten en la lista
        //no regresa nombres con status=9
        ArrayList pr = null;
        pr = new ArrayList();
        ResultSet rsTemp;
        String parasource1 = "select nid from names where gid =" + p_gid;
        rsTemp = null;
        try {
            Statement statement = null;
            if (p_gid < 0) {
                statement = conLocalGMS.createStatement();
            } else {
                statement = conCentralGMS.createStatement();
            }
            rsTemp = statement.executeQuery(parasource1);
            while (rsTemp.next()) {
                NamesRecord nr = new NamesRecord();
                byte ret2 = armainfoNIDRecord(rsTemp.getInt("nid"), nr);
                if (nr.getNstat() != 9) {
                    if (ntypeArray.contains(nr.getNtype()) && nstatArray.contains(nr.getNstat())) {
                        pr.add(nr);
                    }
                }
                nr = null;
            }
            if (pr == null) {
                System.out.println("Lo sentimos no existe informacion en names para el gid " + p_gid);
            }
            statement = null;
        } catch (Exception e) {
            System.out.println("p_gid=" + p_gid + ":" + e.toString());
        }
        return pr;
    }

    public ArrayList getNamesList(int p_gid, List<Integer> ntypeArray, List<Integer> nstatArray, List<Integer> nuiArray) {
        //observacion regresa nombres tomando en cuenta changes (bases local y central), y no agrega los que no esten en la lista
        //no regresa nombres con status=9
        ArrayList pr = null;
        pr = new ArrayList();
        ResultSet rsTemp;
        String parasource1 = "select nid from names where gid =" + p_gid;
        rsTemp = null;
        try {
            Statement statement = null;
            if (p_gid < 0) {
                statement = conLocalGMS.createStatement();
            } else {
                statement = conCentralGMS.createStatement();
            }
            rsTemp = statement.executeQuery(parasource1);
            while (rsTemp.next()) {
                NamesRecord nr = new NamesRecord();
                byte ret2 = armainfoNIDRecord(rsTemp.getInt("nid"), nr);
                if (nr.getNstat() != 9) {
                    if (ntypeArray.contains(nr.getNtype()) && nstatArray.contains(nr.getNstat()) && !nuiArray.contains(nr.getNuid())) {
                        pr.add(nr);
                    }
                }
                nr = null;
            }
            if (pr == null) {
                System.out.println("Lo sentimos no existe informacion en names para el gid " + p_gid);
            }
            statement = null;
        } catch (Exception e) {
            System.out.println("p_gid=" + p_gid + ":" + e.toString());
        }
        return pr;
    }

    private void getFieldsUpdatedNames(NamesRecord p_nr) {
        String t_cfield = "";
        ResultSet rsTemp1 = null;
        try {
            if (stmtLocalGMS == null) {
                stmtLocalGMS = conLocalGMS.createStatement();
            }
            pq = "select cfield, cto from changes where ctable='names' and cstatus=0 and crecord =" + p_nr.getNid() + " order by cdate, ctime;";
            rsTemp1 = stmtLocalGMS.executeQuery(pq);
            while (rsTemp1.next()) {
                t_cfield = rsTemp1.getString("cfield").trim().toUpperCase();
                if (t_cfield.equals("GID")) {
                    p_nr.setGid(rsTemp1.getInt("cto"));
                }
                if (t_cfield.equals("NDATE")) {
                    p_nr.setNdate(rsTemp1.getInt("cto"));
                }
                if (t_cfield.equals("NLOCN")) {
                    p_nr.setNlocn(rsTemp1.getInt("cto"));
                }
                if (t_cfield.equals("NSTAT")) {
                    p_nr.setNstat(rsTemp1.getInt("cto"));
                }
                if (t_cfield.equals("NTYPE")) {
                    p_nr.setNtype(rsTemp1.getInt("cto"));
                }
                if (t_cfield.equals("NUID")) {
                    p_nr.setNuid(rsTemp1.getInt("cto"));
                }
            }
        } catch (SQLException sqle) {
            System.out.println("getFieldsUpdatedNames error:" + sqle.toString());
        }
    }
    //VERSION

    public String SP_GMS_Get_IWIS3_Cross_Name(int p_GID) {
        //System.out.println("lo llame con giud=" + p_GID);
        String p_Cross_Name;//varchar(255) = '' output
        Connection connection;
        Statement statement = null;

        int x_count = 0, x_GNPGS = 0, x_GPID1 = 0, x_GPID2, tmp_GNPGS, x_CID, x_SID, x_Search_GID;
        p_Cross_Name = "";
        ResultSet rs11 = null;
        rs11 = null;
        try {
            if (p_GID < 0) {
                connection = conLocalGMS;
            } else {
                connection = conCentralGMS;
            }
            statement = connection.createStatement();

            rs11 = statement.executeQuery("Select Count(*) From NAMES Where GID=" + p_GID + " And NSTAT=1 And NTYPE IN (2,6,7,17,1062)");
            rs11.next();
            x_count = rs11.getInt(1);
            rs11.close();
            if (x_count > 0) {
                rs11 = statement.executeQuery("Select NVAL From NAMES Where GID=" + p_GID + " And NSTAT=1 And NTYPE IN (2,6,7,17,1062)");
                rs11.next();
                p_Cross_Name = rs11.getString(1);
                rs11.close();
                //System.out.println("CrossNameA NSTAT=1 And NTYPE IN (2,6,7,17,1062)" + p_Cross_Name);
                return p_Cross_Name;
            }
            rs11 = statement.executeQuery("Select GNPGS, GPID1 From GERMPLSM Where GID =" + p_GID);
            if (rs11.next()) {
                x_GNPGS = rs11.getInt(1);
                x_GPID1 = rs11.getInt(2);
            }
            rs11.close();
            if (x_GNPGS == -1) {
                x_Search_GID = p_GID;
                //*** Seek for inheritance of name from a previous selection
                while (x_Search_GID != 0) {
                    if (x_Search_GID < 0) {
                        connection = conLocalGMS;
                    } else {
                        connection = conCentralGMS;
                    }
                    statement = connection.createStatement();
                    rs11 = statement.executeQuery("Select GPID2, GNPGS From GERMPLSM Where GID =" + x_Search_GID);
                    rs11.next();
                    x_GPID2 = rs11.getInt(1);
                    tmp_GNPGS = rs11.getInt(2);
                    rs11.close();

                    rs11 = statement.executeQuery("Select Count(*) From NAMES Where GID =" + x_Search_GID + " And NSTAT=1 And NTYPE IN (2,6,7,17,1062)");
                    rs11.next();
                    x_count = rs11.getInt(1);
                    rs11.close();
                    if (x_count > 0) {
                        rs11 = statement.executeQuery("Select NVAL From NAMES Where GID =" + x_Search_GID + " And NSTAT=1 And NTYPE IN (2,6,7,17,1062)");
                        rs11.next();
                        p_Cross_Name = rs11.getString(1);
                        rs11.close();
                        return p_Cross_Name;
                    }
                    x_Search_GID = x_GPID2;
                    if (tmp_GNPGS >= 0) {
                        x_Search_GID = 0;
                    }
                }
            }
            x_Search_GID = p_GID;
            if (x_GNPGS == -1) {
                x_Search_GID = x_GPID1;
            }
            if (x_Search_GID < 0) {
                connection = conLocalGMS;
            } else {
                connection = conCentralGMS;
            }
            statement = connection.createStatement();
            rs11 = statement.executeQuery("Select Count(*) From NAMES Where GID =" + x_Search_GID + " And NTYPE = 1029 And NSTAT<>9");
            rs11.next();
            x_count = rs11.getInt(1);
            rs11.close();
            if (x_count > 0) {
                rs11 = statement.executeQuery("Select NVAL From NAMES Where GID =" + x_Search_GID + " And NTYPE = 1029 And NSTAT<>9");
                rs11.next();
                p_Cross_Name = rs11.getString(1);
                rs11.close();
                return p_Cross_Name;
            }
            if (p_GID < 0) {
                connection = conLocalGMS;
            } else {
                connection = conCentralGMS;
            }
            statement = connection.createStatement();
            rs11 = statement.executeQuery("Select CID From GERMPLSM Where GID =" + p_GID);
            if (rs11.next()) {
                x_CID = rs11.getInt(1);
                rs11.close();
                rs11 = statement.executeQuery("Select MIN(SID) From GERMPLSM Where GERMPLSM.CID =" + x_CID + " And GERMPLSM.GRPLCE=0 And exists(select * from names where GERMPLSM.gid=NAMES.gid and NAMES.nstat=1 and NAMES.ntype in (2,6,7,17,1062))");
                rs11.next();
                x_SID = rs11.getInt(1);
                rs11.close();
                rs11 = statement.executeQuery("Select GID from GERMPLSM where CID =" + x_CID + " And SID =" + x_SID);
                rs11.next();
                x_Search_GID = rs11.getInt(1);
                rs11.close();
                if (x_Search_GID < 0) {
                    connection = conLocalGMS;
                } else {
                    connection = conCentralGMS;
                }
                statement = connection.createStatement();
                rs11 = statement.executeQuery("Select NVAL From NAMES Where GID =" + x_Search_GID + " And NSTAT=1 And NTYPE<>1027");
                if (rs11.next()) {
                    p_Cross_Name = rs11.getString(1);
                    rs11.close();
                }
            }
            if (!p_Cross_Name.equals("")) {
                return p_Cross_Name;
            }
        } catch (Exception e) {
            System.out.println("error SP_GMS_Get_IWIS3_Cross_Name:" + e.getMessage());
        }
        System.out.println("Cross NameE Nothing found");
        return p_Cross_Name;
    }

//    public ResultSet getDMSInfo(String p_listaFactoresKey, int cuantosFK, String p_listaFactoresReturn, int p_tid, int p_occ) {
//        ResultSet pr = null;
//        Statement st = null;
//        ResultSet rsTemp = null;
//        String parasource1 = "SELECT represno, COUNT(*) FROM effect e INNER JOIN factor f ON e.factorid=f.factorid WHERE studyid=" + p_tid + " AND f.factorid = f.labelid AND fname IN(" + p_listaFactoresKey + ") GROUP BY represno HAVING COUNT(*)=" + cuantosFK;
//        try {
//            if (p_tid < 0) {
//                st = conLocalDMS.createStatement();
//            } else {
//                st = conCentralDMS.createStatement();
//            }
//            rsTemp = st.executeQuery(parasource1);
//            int trepresNo = 0;
//            if (rsTemp.next()) {
//                trepresNo = rsTemp.getInt("represno");
//            }
//            rsTemp.close();
//            if (trepresNo == 0) {
//                return null;
//            }
//            RowSetMetaDataImpl rsmd = new RowSetMetaDataImpl();
//            parasource1 = "SELECT count(*) FROM factor WHERE studyid=" + p_tid + " and fname IN(" + p_listaFactoresReturn + ")";
//            rsTemp = st.executeQuery(parasource1);
//            int cuantosFR = 0;
//            rsTemp.next();
//            cuantosFR = rsTemp.getInt(1);
//            rsTemp.close();
//            parasource1 = "SELECT fname, ltype FROM factor WHERE studyid=" + p_tid + " and fname IN(" + p_listaFactoresReturn + ")";
//            rsTemp = st.executeQuery(parasource1);
//            rsmd.setColumnCount(cuantosFR);
//            int tconsecutivo = 0;
//            while (rsTemp.next()) {
//                tconsecutivo += 1;
//                rsmd.setColumnName(tconsecutivo, rsTemp.getString("FNAME"));
//                if (rsTemp.getString("ltype").equals("N")) {
//                    rsmd.setColumnType(tconsecutivo, Types.INTEGER);
//                } else {
//                    rsmd.setColumnType(tconsecutivo, Types.VARCHAR);
//                }
//            }
//            rsTemp.close();
//            CachedRowSetImpl crs = new CachedRowSetImpl();
//            int i889 = 0;
//            crs.setMetaData(rsmd);
//            String paraWhere = "f.fname IN (" + p_listaFactoresReturn + ") AND studyid = " + p_tid + " AND represno =" + trepresNo + "";
//            parasource1 = "SELECT O.OUNITID, FNAME, LVALUE, LTYPE FROM FACTOR F INNER JOIN (LEVEL_N L INNER JOIN OINDEX O ON (L.LEVELNO = O.LEVELNO) AND (L.FACTORID = O.FACTORID)) ON (F.FACTORID = L.FACTORID) AND (F.LABELID = L.LABELID) WHERE " + paraWhere + "";
//            parasource1 += " UNION ";
//            parasource1 += "SELECT O.OUNITID, FNAME, LVALUE, LTYPE FROM FACTOR F INNER JOIN (LEVEL_C L INNER JOIN OINDEX O ON (L.LEVELNO = O.LEVELNO) AND (L.FACTORID = O.FACTORID)) ON (F.FACTORID = L.FACTORID) AND (F.LABELID = L.LABELID) WHERE " + paraWhere + "";
//            parasource1 += " ORDER BY OUNITID";
//            if (p_tid < 0) {
//                parasource1 += " desc";
//            }
//            //System.out.println(parasource1);
//            rsTemp = st.executeQuery(parasource1);
//            int tounitidAnt = 0;
//            int tounitidActual = 0;
//            String fname = "";
//            GpidInfClass gpidInfClass = new GpidInfClass();
//            GpidStringInfClass gpidStringInfClass = new GpidStringInfClass();
//
//            String ped = "";
//            try {
//                for (int i = 0; i < 1; i++) {
//                    ped = arma_pedigree(9999999, 0, gpidInfClass, 0, 0, 0, 0);
//                }
//            } catch (Exception e) {
//            }
//            while (rsTemp.next()) {
//                try {
//                    tounitidActual = rsTemp.getInt("ounitid");
//                    if (tounitidAnt != tounitidActual) {
//                        if (tounitidAnt != 0) {
//                            crs.insertRow();
//                        }
//                        crs.moveToInsertRow();
//                        for (i889 = 1; i889 <= cuantosFR; i889++) {
//                            crs.updateNull(i889);
//                        }
//                    }
//                    fname = rsTemp.getString("fname");
//                    if (rsTemp.getString("ltype").trim().toUpperCase().equals("N")) {
//                        crs.updateInt(fname, rsTemp.getInt("lvalue"));
//                    } else {
//                        if (fname.equals("CROSS NAME") & 2 == 3) {
//                            crs.updateString(fname, ped);
//                        } else {
//                            crs.updateString(fname, rsTemp.getString("lvalue"));
//                        }
//                    }
//                    tounitidAnt = tounitidActual;
//                } catch (Exception e) {
//                    System.out.println(e.getMessage());
//                }
//            }
//            if (tounitidAnt != 0) {
//                crs.insertRow();
//            }
//            crs.moveToCurrentRow();
//            crs.beforeFirst();
//            pr = crs;
//        } catch (SQLException sqle) {
//            pr = null;
//        }
//        return pr;
//    }
    
    public ResultSet getTrialRandomization(int p_tid, int p_occ) {
        ResultSet pr = null;
        Statement st = null;
        ResultSet rsTemp = null;
        String parasource1 = "SELECT represno, COUNT(*) FROM effect e INNER JOIN factor f ON e.factorid=f.factorid WHERE studyid=" + p_tid + " AND f.factorid = f.labelid AND fname IN(" + listaFactoresTrialRandomizationKey + ") GROUP BY represno HAVING COUNT(*)=" + cuantosFactoresTrialRandomizationKey;
        try {
            if (p_tid < 0) {
                st = conLocalDMS.createStatement();
            } else {
                st = conCentralDMS.createStatement();
            }
            rsTemp = st.executeQuery(parasource1);
            int trepresNo = 0;
            if (rsTemp.next()) {
                trepresNo = rsTemp.getInt("represno");
            }
            rsTemp.close();
            if (trepresNo == 0) {
                return null;
            }
            RowSetMetaDataImpl rsmd = new RowSetMetaDataImpl();
            parasource1 = "SELECT count(*) FROM factor WHERE studyid=" + p_tid + " and fname IN(" + listaFactoresTrialRandomizationReturn + ")";
            rsTemp = st.executeQuery(parasource1);
            int cuantosFR = 0;
            rsTemp.next();
            cuantosFR = rsTemp.getInt(1);
            rsTemp.close();
            parasource1 = "SELECT fname, ltype FROM factor WHERE studyid=" + p_tid + " and fname IN(" + listaFactoresTrialRandomizationReturn + ")";
            rsTemp = st.executeQuery(parasource1);
            rsmd.setColumnCount(cuantosFR);
            int tconsecutivo = 0;
            while (rsTemp.next()) {
                tconsecutivo += 1;
                rsmd.setColumnName(tconsecutivo, rsTemp.getString("FNAME"));
                if (rsTemp.getString("ltype").equals("N")) {
                    rsmd.setColumnType(tconsecutivo, Types.INTEGER);
                } else {
                    rsmd.setColumnType(tconsecutivo, Types.VARCHAR);
                }
            }
            rsTemp.close();
            CachedRowSetImpl crs = new CachedRowSetImpl();
            int i889 = 0;
            crs.setMetaData(rsmd);
            String paraWhere = "f.fname IN (" + listaFactoresTrialRandomizationReturn + ") AND studyid = " + p_tid + " AND represno =" + trepresNo + "";
            if (p_occ > 0) {
                parasource1 = "SELECT OUNITID FROM FACTOR F INNER JOIN (LEVEL_N L INNER JOIN OINDEX O ON (L.LEVELNO = O.LEVELNO) AND (L.FACTORID = O.FACTORID)) ON (F.FACTORID = L.FACTORID) AND (F.LABELID = L.LABELID)  WHERE f.fname IN ('" + fnameKeyOcc + "') AND studyid = " + p_tid + " AND represno =" + trepresNo + " AND lvalue=" + p_occ;
                rsTemp = st.executeQuery(parasource1);
                int cuantosRegistros = 0;
                String cadOunitid = "";
                while (rsTemp.next()) {
                    cuantosRegistros += 1;
                    cadOunitid += rsTemp.getString("ounitid") + ",";
                }
                rsTemp.close();
                if (cuantosRegistros == 0) {
                    return null;
                }
                cadOunitid = cadOunitid.substring(0, cadOunitid.length() - 1);
                paraWhere += " and ounitid in (" + cadOunitid + ")";
            }
            parasource1 = "SELECT O.OUNITID, FNAME, CAST(LVALUE AS CHAR) AS LVALUE, LTYPE FROM FACTOR F INNER JOIN (LEVEL_N L INNER JOIN OINDEX O ON (L.LEVELNO = O.LEVELNO) AND (L.FACTORID = O.FACTORID)) ON (F.FACTORID = L.FACTORID) AND (F.LABELID = L.LABELID) WHERE " + paraWhere + "";
            parasource1 += " UNION ";
            parasource1 += "SELECT O.OUNITID, FNAME, CAST(LVALUE AS CHAR) AS LVALUE, LTYPE FROM FACTOR F INNER JOIN (LEVEL_C L INNER JOIN OINDEX O ON (L.LEVELNO = O.LEVELNO) AND (L.FACTORID = O.FACTORID)) ON (F.FACTORID = L.FACTORID) AND (F.LABELID = L.LABELID) WHERE " + paraWhere + "";
            parasource1 += " ORDER BY OUNITID";
            if (p_tid < 0) {
                parasource1 += " desc";
            }
            System.out.println(parasource1);
            rsTemp = st.executeQuery(parasource1);
            int tounitidAnt = 0;
            int tounitidActual = 0;
            String fname = "";
            int tlvalue = 0;
            while (rsTemp.next()) {
                try {
                    tounitidActual = rsTemp.getInt("ounitid");
                    if (tounitidAnt != tounitidActual) {
                        if (tounitidAnt != 0) {
                            crs.insertRow();
                        }
                        crs.moveToInsertRow();
                        for (i889 = 1; i889 <= cuantosFR; i889++) {
                            crs.updateNull(i889);
                        }
                    }
                    fname = rsTemp.getString("fname");
                    if (rsTemp.getString("ltype").trim().toUpperCase().equals("N")) {
                        tlvalue = rsTemp.getInt("lvalue");
                        crs.updateInt(fname, tlvalue);
                    } else {
                        crs.updateString(fname, rsTemp.getString("lvalue"));
                    }
                    tounitidAnt = tounitidActual;
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            }
            if (tounitidAnt != 0) {
                crs.insertRow();
            }
            crs.moveToCurrentRow();
            crs.beforeFirst();
            pr = crs;
        } catch (SQLException sqle) {
            pr = null;
        }
        return pr;
    }

    public ResultSet getTrial_Entries(int p_tid, int p_ent) {
        ResultSet pr = null;
        Statement st = null;
        ResultSet rsTemp = null;
        String parasource1 = "SELECT levelno FROM level_n l INNER JOIN factor f ON f.factorid=l.factorid AND F.LABELID = L.LABELID WHERE fname ='" + fnameKeyEntryNumber + "' AND studyid =" + p_tid;
        if (p_ent != 0) {
            parasource1 += " and lvalue= " + p_ent;
        }
        try {
            if (p_tid < 0) {
                st = conLocalDMS.createStatement();
            } else {
                st = conCentralDMS.createStatement();
            }
            rsTemp = st.executeQuery(parasource1);
            int cuantasEntradas = 0;
            String cadLevelno = "";
            while (rsTemp.next()) {
                cuantasEntradas += 1;
                cadLevelno += rsTemp.getString("levelno") + ",";
            }
            rsTemp.close();
            if (cuantasEntradas == 0) {
                return null;
            }
            cadLevelno = cadLevelno.substring(0, cadLevelno.length() - 1);
            RowSetMetaDataImpl rsmd = new RowSetMetaDataImpl();
            parasource1 = "SELECT f.fname, ltype FROM level_n l INNER JOIN factor f ON f.labelid=l.labelid AND f.factorid=l.factorid WHERE levelno in (" + cadLevelno + ")";
            parasource1 += " UNION ";
            parasource1 += "SELECT f.fname, ltype FROM level_c l INNER JOIN factor f ON f.labelid=l.labelid AND f.factorid=l.factorid WHERE levelno in (" + cadLevelno + ")";
            rsTemp = st.executeQuery(parasource1);
            int cuantosFR = 0;
            while (rsTemp.next()) {
                cuantosFR += 1;
            }
            rsTemp.close();

            rsTemp = st.executeQuery(parasource1);
            rsmd.setColumnCount(cuantosFR);

            int tconsecutivo = 0;
            String columnName = "";
            while (rsTemp.next()) {
                tconsecutivo += 1;
                columnName = rsTemp.getString("FNAME");
                //System.out.println(columnName);
                rsmd.setColumnName(tconsecutivo, columnName);
                if (rsTemp.getString("ltype").equals("N")) {
                    rsmd.setColumnType(tconsecutivo, Types.INTEGER);
                } else {
                    rsmd.setColumnType(tconsecutivo, Types.VARCHAR);
                }
            }
            rsTemp.close();
            CachedRowSetImpl crs = new CachedRowSetImpl();
            crs.setMetaData(rsmd);
            parasource1 = "SELECT levelno, f.fname, CAST(LVALUE AS CHAR) AS LVALUE, ltype FROM level_n l INNER JOIN factor f ON f.labelid=l.labelid AND f.factorid=l.factorid WHERE levelno in (" + cadLevelno + ")";
            parasource1 += " UNION ";
            parasource1 += "SELECT levelno, f.fname, CAST(LVALUE AS CHAR) AS LVALUE, ltype FROM level_c l INNER JOIN factor f ON f.labelid=l.labelid AND f.factorid=l.factorid WHERE levelno in (" + cadLevelno + ")";
            parasource1 += " ORDER BY levelno";
            if (p_tid < 0) {
                parasource1 += " desc";
            }
            rsTemp = st.executeQuery(parasource1);
            int tlevelnoAnt = 0;
            int tlevelnoActual = 0;
            String fname = "";
            int tlvalue = 0;
            GpidInfClass gpidInfClass;
            String ped = "";
            while (rsTemp.next()) {
                try {
                    tlevelnoActual = rsTemp.getInt("levelno");
                    if (tlevelnoAnt != tlevelnoActual) {
                        if (tlevelnoAnt != 0) {
                            GermplsmRecord germplsmRecord = getGermplsmRecord(crs.getInt("GID"));
                            if (germplsmRecord.getGid() != 0) {
                                try {
                                    crs.updateInt("gid", germplsmRecord.getGid());
                                } catch (Exception e) {
                                }
                                try {
                                    crs.updateInt("cid", germplsmRecord.getCid());
                                } catch (Exception e) {
                                }
                                try {
                                    crs.updateInt("sid", germplsmRecord.getSid());
                                } catch (Exception e) {
                                }
                            }
                            if (buildPedigree == BuildPedigree.DINAMIC_NAMES) {
                                //ped = arma_pedigree(crs.getInt("GID"), 0, new GpidInfClass(), 0, 0, 0, 0);
                                //ped = SP_GMS_Get_IWIS3_Cross_Name(crs.getInt("GID"));
                                //crs.updateString(fnamePedigree, ped);
                            }
                            if (buildPedigree == BuildPedigree.SAVED_NAMES) {
                                //ped = SP_GMS_Get_IWIS3_Cross_Name(crs.getInt("GID"));
                                //crs.updateString(fnamePedigree, ped);
                            }
                            crs.insertRow();
                        }
                        crs.moveToInsertRow();
                        for (int i889 = 1; i889 <= cuantosFR; i889++) {
                            crs.updateNull(i889);
                        }
                    }
                    fname = rsTemp.getString("fname");
                    if (rsTemp.getString("ltype").trim().toUpperCase().equals("N")) {
                        tlvalue = rsTemp.getInt("lvalue");
                        crs.updateInt(fname, tlvalue);
                    } else {
                        crs.updateString(fname, rsTemp.getString("lvalue"));
                    }
                    tlevelnoAnt = tlevelnoActual;
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            }
            if (tlevelnoAnt != 0) {
                try {
                    if (buildPedigree == BuildPedigree.DINAMIC_NAMES) {
                        gpidInfClass = new GpidInfClass();
                        //ped = arma_pedigree(crs.getInt("GID"), 0, gpidInfClass, 0, 0, 0, 0);
                        //ped = SP_GMS_Get_IWIS3_Cross_Name(crs.getInt("GID"));
                        //crs.updateString(fnamePedigree, ped);
                    }
                    if (buildPedigree == BuildPedigree.SAVED_NAMES) {
                        // ped = SP_GMS_Get_IWIS3_Cross_Name(crs.getInt("GID"));
                        //crs.updateString(fnamePedigree, ped);
                    }
                } catch (Exception e) {
                }
                crs.insertRow();
            }
            crs.moveToCurrentRow();
            crs.beforeFirst();
            pr = crs;
        } catch (SQLException sqle) {
            pr = null;
        }
        return pr;
    }

    public ResultSet getOccData(int p_tid, int p_occ) {
        ResultSet pr = null;
        Statement st = null;
        ResultSet rsTemp = null;
        String cadLevelno = "";
        String parasource1 = "SELECT levelno FROM level_n l INNER JOIN factor f ON f.factorid=l.factorid AND F.LABELID = L.LABELID WHERE fname ='" + fnameKeyOcc + "' AND studyid =" + p_tid;
        if (p_occ != 0) {
            parasource1 += " and lvalue= " + p_occ;
        }
        try {
            if (p_tid < 0) {
                st = conLocalDMS.createStatement();
            } else {
                st = conCentralDMS.createStatement();
            }
            rsTemp = st.executeQuery(parasource1);
            int cuantasEntradas = 0;
            while (rsTemp.next()) {
                cuantasEntradas += 1;
                cadLevelno += rsTemp.getString("levelno") + ",";
            }
            rsTemp.close();
            if (cuantasEntradas == 0) {
                return null;
            }
            cadLevelno = cadLevelno.substring(0, cadLevelno.length() - 1);
            RowSetMetaDataImpl rsmd = new RowSetMetaDataImpl();
            parasource1 = "SELECT f.fname, ltype FROM level_n l INNER JOIN factor f ON f.labelid=l.labelid AND f.factorid=l.factorid WHERE levelno in (" + cadLevelno + ")";
            parasource1 += " UNION ";
            parasource1 += "SELECT f.fname, ltype FROM level_c l INNER JOIN factor f ON f.labelid=l.labelid AND f.factorid=l.factorid WHERE  levelno in (" + cadLevelno + ")";
            rsTemp = st.executeQuery(parasource1);
            int cuantosFR = 0;
            while (rsTemp.next()) {
                cuantosFR += 1;
            }
            rsTemp.close();
            rsmd.setColumnCount(cuantosFR);
            int tconsecutivo = 0;
            String columnName = "";
            rsTemp = st.executeQuery(parasource1);
            while (rsTemp.next()) {
                tconsecutivo += 1;
                columnName = rsTemp.getString("FNAME");
                //System.out.println("Column Name: " + columnName);
                rsmd.setColumnName(tconsecutivo, columnName);
                if (rsTemp.getString("ltype").equals("N")) {
                    rsmd.setColumnType(tconsecutivo, Types.INTEGER);
                } else {
                    rsmd.setColumnType(tconsecutivo, Types.VARCHAR);
                }
            }

            rsTemp.close();
            CachedRowSetImpl crs = new CachedRowSetImpl();
            crs.setMetaData(rsmd);
            parasource1 = "SELECT levelno, f.fname, CAST(LVALUE AS CHAR) AS LVALUE, ltype FROM level_n l INNER JOIN factor f ON f.labelid=l.labelid AND f.factorid=l.factorid WHERE levelno in (" + cadLevelno + ")";
            parasource1 += " UNION ";
            parasource1 += "SELECT levelno, f.fname, CAST(LVALUE AS CHAR) AS LVALUE, ltype FROM level_c l INNER JOIN factor f ON f.labelid=l.labelid AND f.factorid=l.factorid WHERE levelno in (" + cadLevelno + ")";
            parasource1 += " ORDER BY levelno";
            if (p_tid < 0) {
                parasource1 += " desc";
            }
            rsTemp = st.executeQuery(parasource1);
            int tlevelnoAnt = 0;
            int tlevelnoActual = 0;
            String fname = "";
            int tlvalue = 0;
            while (rsTemp.next()) {
                try {
                    tlevelnoActual = rsTemp.getInt("levelno");
                    if (tlevelnoAnt != tlevelnoActual) {
                        if (tlevelnoAnt != 0) {
                            crs.insertRow();
                        }
                        crs.moveToInsertRow();
                        for (int i889 = 1; i889 <= cuantosFR; i889++) {
                            crs.updateNull(i889);
                        }
                    }
                    fname = rsTemp.getString("fname");
                    if (rsTemp.getString("ltype").trim().toUpperCase().equals("N")) {
                        tlvalue = rsTemp.getInt("lvalue");
                        crs.updateInt(fname, tlvalue);
                    } else {
                        crs.updateString(fname, rsTemp.getString("lvalue"));
                    }
                    tlevelnoAnt = tlevelnoActual;
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            }
            if (tlevelnoAnt != 0) {
                crs.insertRow();
            }
            crs.moveToCurrentRow();
            crs.beforeFirst();
            pr = crs;
        } catch (SQLException sqle) {
            pr = null;
        }
        return pr;
    }

    public ResultSet getTrialInfo(int p_tid) {
        ResultSet pr = null;
        Statement st1 = null;
        ResultSet rsTemp = null;
        Statement st = null;
        String parasource1 = "SELECT l.levelno FROM level_n l INNER JOIN factor f ON f.factorid=l.factorid AND F.LABELID = L.LABELID WHERE fname ='tid' and studyid =" + p_tid;
        try {
            if (p_tid < 0) {
                st = conLocalDMS.createStatement();
                st1 = conLocalDMS.createStatement();
            } else {
                st = conCentralDMS.createStatement();
                st1 = conCentralDMS.createStatement();
            }
            rsTemp = st.executeQuery(parasource1);
            int levelNo = 0;
            if (rsTemp.next()) {
                levelNo = rsTemp.getInt(1);
            }
            rsTemp.close();
            if (levelNo == 0) {
                return null;
            }
            parasource1 = "SELECT f.fname, ltype FROM level_n l INNER JOIN factor f ON f.labelid=l.labelid AND f.factorid=l.factorid WHERE fname not in('studyid','sname','title') and levelno = " + levelNo;
            parasource1 += " UNION ";
            parasource1 += "SELECT f.fname, ltype FROM level_c l INNER JOIN factor f ON f.labelid=l.labelid AND f.factorid=l.factorid WHERE fname not in('studyid','sname','title') and levelno =" + levelNo;
            rsTemp = st.executeQuery(parasource1);
            int cuantosFR = 3;
            while (rsTemp.next()) {
                cuantosFR += 1;
            }
            rsTemp.close();
            RowSetMetaDataImpl rsmd = new RowSetMetaDataImpl();
            rsmd.setColumnCount(cuantosFR);
            String columnName = "";
            rsmd.setColumnType(1, Types.INTEGER);
            rsmd.setColumnName(1, "studyid");
            rsmd.setColumnType(2, Types.VARCHAR);
            rsmd.setColumnName(2, "sname");
            rsmd.setColumnType(3, Types.VARCHAR);
            rsmd.setColumnName(3, "title");
            int tconsecutivo = 3;
            rsTemp = st.executeQuery(parasource1);
            while (rsTemp.next()) {
                tconsecutivo += 1;
                columnName = rsTemp.getString("FNAME");
                //System.out.println("cn" + columnName);
                rsmd.setColumnName(tconsecutivo, columnName);
                if (rsTemp.getString("ltype").equals("N")) {
                    rsmd.setColumnType(tconsecutivo, Types.INTEGER);
                } else {
                    rsmd.setColumnType(tconsecutivo, Types.VARCHAR);
                }
            }
            rsTemp.close();
            CachedRowSetImpl crs = new CachedRowSetImpl();
            crs.setMetaData(rsmd);
            parasource1 = "SELECT levelno, f.fname, CAST(LVALUE AS CHAR) AS LVALUE, ltype FROM level_n l INNER JOIN factor f ON f.labelid=l.labelid AND f.factorid=l.factorid WHERE fname not in('studyid','sname','title') and levelno = " + levelNo;
            parasource1 += " UNION ";
            parasource1 += "SELECT levelno, f.fname, CAST(LVALUE AS CHAR) AS LVALUE, ltype FROM level_c l INNER JOIN factor f ON f.labelid=l.labelid AND f.factorid=l.factorid WHERE fname not in('studyid','sname','title') and levelno =" + levelNo;
            parasource1 += " ORDER BY levelno";
            if (p_tid < 0) {
                parasource1 += " desc";
            }
            int tlevelnoAnt = 0;
            int tlevelnoActual = 0;
            String fname = "";
            int tlvalue = 0;
            rsTemp = st.executeQuery(parasource1);
            ResultSet rsTemp1 = null;
            while (rsTemp.next()) {
                try {
                    tlevelnoActual = rsTemp.getInt("levelno");
                    if (tlevelnoAnt != tlevelnoActual) {
                        if (tlevelnoAnt != 0) {
                            crs.insertRow();
                        }
                        crs.moveToInsertRow();
                        parasource1 = "SELECT studyid,sname,title FROM study WHERE studyid = " + p_tid;
                        rsTemp1 = st1.executeQuery(parasource1);
                        crs.updateNull(1);
                        crs.updateNull(2);
                        crs.updateNull(3);
                        if (rsTemp1.next()) {
                            crs.updateInt("studyid", rsTemp1.getInt("studyid"));
                            crs.updateString("sname", rsTemp1.getString("sname"));
                            crs.updateString("title", rsTemp1.getString("title"));
                        }
                        rsTemp1.close();
                        for (int i889 = 4; i889 <= cuantosFR; i889++) {
                            crs.updateNull(i889);
                        }
                    }
                    fname = rsTemp.getString("fname");
                    if (rsTemp.getString("ltype").trim().toUpperCase().equals("N")) {
                        tlvalue = rsTemp.getInt("lvalue");
                        crs.updateInt(fname, tlvalue);
                    } else {
                        crs.updateString(fname, rsTemp.getString("lvalue"));
                    }
                    tlevelnoAnt = tlevelnoActual;
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            }
            rsTemp.close();
            if (tlevelnoAnt != 0) {
                crs.insertRow();
            }
            crs.moveToCurrentRow();
            crs.beforeFirst();
            pr = crs;
        } catch (SQLException sqle) {
            pr = null;
        }
        return pr;
    }

    public boolean fu_trial_randomized(int p_tid, int p_occ) throws Exception {
        ResultSet rsTemp = null;
        Statement st = null;
        Statement st1 = null;
        boolean ret = false;
        String parasource1 = "";
        int max_PLOT = 0;
        int max_entry_number = 0;
        ResultSet rsALLOCCsESteTId = null;
        v_tid = p_tid;
        v_occ = p_occ;
        ret = false;
        consigueLABELIDS_PLOT_ENT();
        if (v_occ > 0) {
            ret = DeterminaSiNORandomizacion(v_tid, v_occ);
        } else {
            //primero validamos si existe un plot mayor que el numero la entrada maxima, esto nos asegura que hay randomizacion
            try {
                if (p_tid < 0) {
                    st = conLocalDMS.createStatement();
                    st1 = conLocalDMS.createStatement();
                } else {
                    st = conCentralDMS.createStatement();
                    st1 = conCentralDMS.createStatement();
                }
            } catch (Exception e) {
            }

            parasource1 = "SELECT MAX(lvalue) as maxplot FROM level_n WHERE labelid =" + labelidPLOT;
            rsTemp = st.executeQuery(parasource1);
            if (rsTemp.next()) {
                max_PLOT = rsTemp.getInt("maxplot");
            }
            rsTemp.close();
            parasource1 = "SELECT MAX(lvalue) as maxent FROM level_n WHERE labelid =" + labelidEntryNumber;
            rsTemp = st.executeQuery(parasource1);
            if (rsTemp.next()) {
                max_entry_number = rsTemp.getInt("maxent");
            }
            rsTemp.close();
            if (max_PLOT > max_entry_number) {
                // si tiene randomizacion
                ret = true;
                return ret;
                // tenemos que revisar occ por occ, hasta encontrar una con randomizacion
            } else {
                parasource1 = "SELECT lvalue FROM factor f INNER JOIN level_n l ON l.labelid=f.labelid WHERE fname = '" + fnameKeyOcc + "' AND studyid =" + v_tid;
                rsALLOCCsESteTId = st.executeQuery(parasource1);
                while (rsALLOCCsESteTId.next()) {
                    v_occ = rsALLOCCsESteTId.getInt("lvalue");
                    ret = DeterminaSiNORandomizacion(v_tid, v_occ);
                    if (ret == true) {
                        v_occ = 0;
                        return ret; // fin del programa, ya encontramos un occ con randomizacion
                    }
                }
                rsALLOCCsESteTId.close();
            }
        }
        return ret;
    }

    private boolean consigueLABELIDS_PLOT_ENT() throws Exception {
        ResultSet rsTemp = null;
        Statement st = null;
        String paraSource2 = "";
        try {
            if (v_tid < 0) {
                st = conLocalDMS.createStatement();
            } else {
                st = conCentralDMS.createStatement();
            }
        } catch (Exception e) {
        }

        boolean pr = false;
        //consigue labelid del plot
        paraSource2 = "SELECT labelid FROM factor WHERE fname = 'plot' AND studyid = " + v_tid;
        rsTemp = st.executeQuery(paraSource2);
        labelidPLOT = 0;
        if (rsTemp.next()) {
            labelidPLOT = rsTemp.getInt("labelid");
        }
        //consigue labelid del Entry Number
        paraSource2 = "SELECT labelid FROM factor WHERE fname = '" + fnameKeyEntryNumber + "' AND studyid = " + v_tid;
        rsTemp = st.executeQuery(paraSource2);
        labelidEntryNumber = 0;
        if (rsTemp.next()) {
            labelidEntryNumber = rsTemp.getInt("labelid");
        }
        pr = true;
        return pr;
    }

    private boolean DeterminaSiNORandomizacion(int tid, int occ) throws Exception {
        boolean pr = false;
        ResultSet rsTemp = null;
        ResultSet rsOUNITID = null;
        ResultSet rsENT = null;
        ResultSet rsPLOT = null;
        Statement st = null;
        Statement st1 = null;
        Statement st2 = null;
        Statement st3 = null;
        String paraSource1 = "";
        try {
            if (tid < 0) {
                st = conLocalDMS.createStatement();
                st1 = conLocalDMS.createStatement();
                st2 = conLocalDMS.createStatement();
                st3 = conLocalDMS.createStatement();
            } else {
                st = conCentralDMS.createStatement();
                st1 = conCentralDMS.createStatement();
                st2 = conCentralDMS.createStatement();
                st3 = conCentralDMS.createStatement();
            }
        } catch (Exception e) {
        }
        int levelnoOCC = 0;
        String pdebug = "";
        pr = false;
        if (occ == 0) {
            return pr;
        }
        //consigue levelno de la occ solicitada del TID
        paraSource1 = "SELECT l.levelno FROM factor f INNER JOIN level_n l ON l.labelid=f.labelid AND l.factorid=f.factorid WHERE fname = '" + fnameKeyOcc + "' AND studyid = " + tid + " AND lvalue=" + occ;
        rsTemp = st.executeQuery(paraSource1);
        if (rsTemp.next()) {
            levelnoOCC = rsTemp.getInt("levelno");
        }
        //-- determinamos que ounitid utilizan los plots en la OCC v_occ del TID v_tid
        // -- levelno OCC v_occ"
        paraSource1 = "SELECT ounitid FROM oindex WHERE levelno = " + levelnoOCC + " AND ounitid ";
        paraSource1 = paraSource1 + " IN(";
        //-- plot ' el labelid=factorid para el factor "plot"
        paraSource1 = paraSource1 + "SELECT ounitid FROM oindex WHERE factorid=" + labelidPLOT;
        paraSource1 = paraSource1 + ") ORDER BY ounitid";
        rsOUNITID = st1.executeQuery(paraSource1);
        //-- para cada ounitid, tenemos que averigurar a que entrada y a que entrada randomizada pertenecen
        //-- compararemos sus valores si alguno de estos en diferente quiere decir que hay randomizacion.
        int tounitid = 0;
        while (rsOUNITID.next()) {
            tounitid = rsOUNITID.getInt("OUNITID");
            //plot
            paraSource1 = "SELECT LVALUE FROM oindex o INNER JOIN level_n l ON l.levelno=o.levelno WHERE o.ounitid=" + tounitid + " AND LABELID= " + labelidPLOT;
            rsPLOT = st2.executeQuery(paraSource1);
            //entry number
            paraSource1 = "SELECT LVALUE FROM oindex o INNER JOIN level_n l ON l.levelno=o.levelno WHERE o.ounitid=" + tounitid + " AND LABELID= " + labelidEntryNumber;
            rsENT = st3.executeQuery(paraSource1);
//      if( modo_depuracion )
//      {
//        pdebug = "";
//        if( rsPLOT.getRecordCount() > 0 )
//        {
//          pdebug = "PLOT=>" + rsPLOT.getFields().getField("lvalue").getValue();
//        }
//        if( rsENT.getRecordCount() > 0 )
//        {
//          pdebug = pdebug + " VS ENT=>" + rsENT.getFields().getField("lvalue").getValue();
//        }
//        System.out.println( pdebug );
//      }

            if ((rsENT.next()) && (rsPLOT.next())) {
                if (rsENT.getInt("lvalue") != rsPLOT.getInt("lvalue")) {
                    pr = true;
                    if (!(modo_depuracion)) {
                        return pr;
                    }
                }
            }
        }
        return pr;
    }

    public BuildPedigree getBuildPedigree() {
        return buildPedigree;
    }

    public void setBuildPedigree(BuildPedigree buildPedigree) {
        this.buildPedigree = buildPedigree;
    }

    public String getFnameKeyEntryNumber() {
        return fnameKeyEntryNumber;
    }

    public void setFnameKeyEntryNumber(String fnameKeyEntryNumber) {
        this.fnameKeyEntryNumber = fnameKeyEntryNumber;
    }

    public String getFnameKeyOcc() {
        return fnameKeyOcc;
    }

    public void setFnameKeyOcc(String fnameKeyOcc) {
        this.fnameKeyOcc = fnameKeyOcc;
    }

    public static enum BuildPedigree {

        NOBUILD, SAVED_NAMES, DINAMIC_NAMES
    };

    public int getCuantosFactoresTrialRandomizationKey() {
        return cuantosFactoresTrialRandomizationKey;
    }

    public void setCuantosFactoresTrialRandomizationKey(int cuantosFactoresTrialRandomizationKey) {
        this.cuantosFactoresTrialRandomizationKey = cuantosFactoresTrialRandomizationKey;
    }

    public String getListaFactoresTrialRandomizationKey() {
        return listaFactoresTrialRandomizationKey;
    }

    public void setListaFactoresTrialRandomizationKey(String listaFactoresTrialRandomizationKey) {
        this.listaFactoresTrialRandomizationKey = listaFactoresTrialRandomizationKey;
    }

    public String getListaFactoresTrialRandomizationReturn() {
        return listaFactoresTrialRandomizationReturn;
    }

    public void setListaFactoresTrialRandomizationReturn(String listaFactoresTrialRandomizationReturn) {
        this.listaFactoresTrialRandomizationReturn = listaFactoresTrialRandomizationReturn;
    }

    public String getFnamePedigree() {
        return fnamePedigree;
    }

    public void setFnamePedigree(String fnamePedigree) {
        this.fnamePedigree = fnamePedigree;
    }

    public static boolean isLevelZeroFullName() {
        return levelZeroFullName;
    }

    public static void setLevelZeroFullName(boolean levelZeroFullName) {
        QueryCenter.levelZeroFullName = levelZeroFullName;
    }

    public static boolean isUseFullNameInPedigree() {
        return useFullNameInPedigree;
    }

    public static void setUseFullNameInPedigree(boolean useFullNameInPedigree) {
        QueryCenter.useFullNameInPedigree = useFullNameInPedigree;
    }
    private String fnamePedigree = "cross name";
    private String listaFactoresTrialRandomizationKey = "'study','occ','entry number','plot'";
    private String listaFactoresTrialRandomizationReturn = "'occ','entry number','plot','rep','SUB BLOCK','code28','row','col'";
    private int cuantosFactoresTrialRandomizationKey = 4;
    public static BuildPedigree builPedigreeEnum;
    private String fnameKeyEntryNumber = "";
    private String fnameKeyOcc = "";
    private BuildPedigree buildPedigree;
    private String pq = "";
    private static String nstatOrderedList;
    private static String ntypeOrderedList;
    private static String nuidExcludeList;
    private static boolean levelZeroFullName;
    private static boolean useFullNameInPedigree;
    private static Integer[] ntypeArray;
    private static Integer[] nstatArray;
    private static Integer[] nuidArray;
    private static int[] nstatWeightArray;
    private static int[] ntypeWeightArray;
    private List<NamesRecord> listNL = null;
    private int veces_rep = 0;
    private static Statement stmtCentralGMS;
    private static Statement stmtLocalGMS;
    private static int v_tid = 0;
    private static int v_occ = 0;
    private static final boolean modo_depuracion = true;
    private static int labelidPLOT = 0;
    private static int labelidEntryNumber = 0;
    public static Connection conCentralGMS, conCentralDMS;
    public static Connection conLocalGMS, conLocalDMS;
    public static int studyID = 0;
    public static ControlQuerys tqp;
    public static int offset = 0;
}