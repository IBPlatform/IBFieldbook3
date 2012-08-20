
package ibfb.query.core;
import ibfb.query.classes.GermplsmRecord;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ControlQuerys {
int i8;

public int myExponential(int base, int exp){
    if (base<=0)
        return 0;
    if (exp==0)
        return 1;
    int itemp=0;
    int pr=0;
    pr=1;
    for (itemp=1;itemp<=exp;itemp++){
      pr=pr * base;      
    }
    return pr;
}

    private int getGPIDs(int[] arrGIDs, int idxgid, int idxgpid1, int idxgpid2) {
//////'si getgpid1 no se encuentra en  LOCAL GMS entonces lo buscamos en CENTRAL GMS
        int t_gid = arrGIDs[idxgid];
        int temp_gid;
        int temp_gid1;
//////CENTRAL GMS
        temp_gid = t_gid;
        int gnpgs = 0;
        int pr = 0;

        String ps = "";
        Statement statement = null;
        Connection connection = null;

        while (gnpgs != 2 && temp_gid != 0) {
            rs = null;
            ps = "Select gnpgs, gpid1, gpid2 from germplsm where gid = " + temp_gid;
            try {
                if (temp_gid < 0) {
                    connection = QueryCenter.conLocalGMS;
                } else {
                    connection = QueryCenter.conCentralGMS;
                }
                statement = connection.createStatement();
                rs = statement.executeQuery(ps);
                if (rs.next()) {
                    gnpgs = rs.getInt("gnpgs");
                    if (gnpgs == -1) {
                        temp_gid = rs.getInt("gpid2");
                        temp_gid1 = rs.getInt("gpid1");
                        if (temp_gid1 > 0 && temp_gid == 0) {
                            temp_gid = temp_gid1;
                        }
                    }
                }
            } catch (SQLException sqle) {
                try {
                    throw sqle;
                } catch (SQLException ex) {
                    Logger.getLogger(ControlQuerys.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        if (temp_gid != 0) {
            ps = "Select gpid1, gpid2 from germplsm where gid = " + temp_gid;
            rs = null;
            try {
                if (temp_gid < 0) {
                    connection = QueryCenter.conLocalGMS;
                } else {
                    connection = QueryCenter.conCentralGMS;
                }
                statement = connection.createStatement();
                rs = statement.executeQuery(ps);
                if (rs.next()) {
                    arrGIDs[idxgpid1] = rs.getInt("gpid1");
                    arrGIDs[idxgpid2] = rs.getInt("gpid2");
                }
            } catch (SQLException sqle) {
                throw sqle;
            } finally {
                return pr;
            }
        }
        return pr;
    }
    
    /**
     * 
     * @param p_gid
     * @param NM
     * @param arrGIDs
     * @return
     */
    public int getArrayGids(int p_gid, int NM, int[] arrGIDs) {
        arrGIDs[0] = p_gid;
        int indxA = 1;
        int i8Max = 0;
        int pr = 0;
        i8Max = myExponential(2, NM);
        for (i8 = 0; i8 <= i8Max - 2; i8++) {
            if (arrGIDs[i8] != 0) {
                getGPIDs(arrGIDs, i8, indxA, indxA + 1);
            }
            indxA += 2;
        }
        return pr;
    }

    /**
     * 
     * @param studyID_
     * @return
     * @throws org.cimmyt.cril.crossinfonew.ControlQuerys.InvalidTransferException
     * @throws SQLException
     */
    public int obtieneOffset(long studyID_) throws InvalidTransferException, SQLException {
        int pr = 0;
        String ps = "select lvalue from level_n where (labelid= (Select labelID from factor Where (fname = 'offset' and studyid = " + studyID_ + ")))";
        rs = null;
        try {
            if (stmtCentralDMS == null) {
                stmtCentralDMS = QueryCenter.conCentralDMS.createStatement();
            }
            rs = stmtCentralDMS.executeQuery(ps);
            if (rs.next()) {
                pr = rs.getInt("lvalue");
            }
        } catch (SQLException sqle) {
            throw sqle;
        } finally {
            return pr;
        }
    }
    /**
     * 
     * @param studyID_
     * @param GID_
     * @return
     * @throws org.cimmyt.cril.crossinfonew.ControlQuerys.InvalidTransferException
     * @throws SQLException
     */
    public int obtieneEnt(int studyID_, int GID_) throws InvalidTransferException, SQLException {
        int tempOffset;
        int tempLevelNO=0;
        int pr = 0;
        String ps;
        ps="SELECT levelno FROM factor f INNER JOIN level_n l ON l.factorid=f.factorid AND l.labelid = f.labelid WHERE fname = 'GID' AND lvalue =" + GID_ + " AND studyid =" + studyID_ + ";";
        rs = null;
        try {
            if (stmtCentralDMS == null) {
                stmtCentralDMS = QueryCenter.conCentralDMS.createStatement();
            }
            rs = stmtCentralDMS.executeQuery(ps);
            if (rs.next()) {
                    tempLevelNO = rs.getInt("levelno");
                    tempOffset = obtieneOffset(studyID_);
                    pr = Integer.valueOf(QueryCenter.tqp.obtieneEstoDeEstaEntradaLevelNo_N("entry number", studyID_, tempLevelNO));   
            }
        } catch (SQLException sqle) {
            throw sqle;
        } finally {
            return pr;
        }
    }
    /**
     * 
     * @param studyID_
     * @return
     * @throws org.cimmyt.cril.crossinfonew.ControlQuerys.InvalidTransferException
     * @throws SQLException
     */
    public int obtieneElNumeroDeEntradas(long studyID_) throws InvalidTransferException, SQLException {
        int pr = 0;
        String ps = "select MIN(lvalue), MAX(lvalue) from level_n where (labelid = (Select labelID from factor Where (fname = 'ENTRY NUMBER' and studyid = " + studyID_ + ")))";
        rs = null;
        try {
            Connection connection;
            if (studyID_<0){
            connection=QueryCenter.conLocalDMS;
            }else{
                connection=QueryCenter.conCentralDMS;
            }
            Statement statement=null;
            statement=connection.createStatement();
            rs = statement.executeQuery(ps);
            rs.next();
            int i1, i2;
            i1 = rs.getInt(1);
            i2 = rs.getInt(2);
            QueryCenter.offset = (i1 - 1);
            pr = i2 - i1 + 1;
        } catch (SQLException sqle) {
            throw sqle;
        } finally {
            return pr;
        }
    }

    /**
     * 
     * @param que_quiere
     * @param studyID_
     * @param levelNo_
     * @return
     * @throws SQLException
     */
    public String obtieneEstoDeEstaEntradaLevelNo_N(String que_quiere, int studyID_, int levelNo_) throws SQLException {
        String pr = "";
        String ps = "select lvalue from level_n where (labelid = (Select labelID from factor Where (fname = '" + que_quiere + "' and studyid = " + studyID_ + "))) ";
        if (levelNo_!=0)
        ps = ps + " and levelno = " + levelNo_;        
        rs = null;
        try {
            Connection connection;
            if (studyID_<0){
            connection=QueryCenter.conLocalDMS;
            }else{
                connection=QueryCenter.conCentralDMS;
            }
            Statement statement=null;
            statement=connection.createStatement();
            rs = statement.executeQuery(ps);
            if (rs.next()){
                pr = String.valueOf(rs.getInt("lvalue"));
            }

        } catch (SQLException sqle) {
            throw sqle;
        } finally {
            return pr;
        }
    }

    /**
     * 
     * @param que_quiere
     * @param studyID_
     * @param levelNo_
     * @return
     * @throws SQLException
     */
    public String obtieneEstoDeEstaEntradaLevelNo_C(String que_quiere, long studyID_, int levelNo_) throws SQLException {
        String pr = "";
        String ps = "select lvalue from level_c where (labelid = (Select labelID from factor Where (fname = '" + que_quiere + "' and studyid = " + studyID_ + "))) " ;
        if (levelNo_!=0)
        ps = ps + " and levelno = " + levelNo_;
        rs = null;
        try {
            Connection connection;
            if (studyID_<0){
            connection=QueryCenter.conLocalDMS;
            }else{
                connection=QueryCenter.conCentralDMS;
            }
            Statement statement=null;
            statement=connection.createStatement();
            rs = statement.executeQuery(ps);
            rs.next();
            pr = rs.getString("lvalue");
            if (pr.equals("0")){
                pr="";
            }
        } catch (SQLException sqle) {
            throw sqle;
        } finally {
            return pr;
        }
    }

    /**
     * 
     * @param studyID_
     * @param entry_
     * @return
     * @throws SQLException
     */
    public int buscaelLevelNoParaEstaEntrada(long studyID_, long entry_) throws SQLException {
        int pr = 0;
        String ps = "select levelno from level_n where (labelid = (Select labelID from factor Where (fname = 'entry number' and studyid = " + studyID_ + "))) and lvalue = " + entry_;
        rs = null;
        try {
            Connection connection;
            if (studyID_<0){
            connection=QueryCenter.conLocalDMS;
            }else{
                connection=QueryCenter.conCentralDMS;
            }
            Statement statement=null;
            statement=connection.createStatement();
            rs = statement.executeQuery(ps);
            rs.next();
            pr = rs.getInt("levelno");
        } catch (SQLException sqle) {
            throw sqle;
        } finally {
            return pr;
        }
    }
//seccion central

    /**
     * 
     * @param Intrid_
     * @param ent_
     * @return
     */
    public int buscaTidParaEsteIntridEntrada(String Intrid_, int ent_) {
        String cadenaLEVELNO = "";
        String ps;
        rs = null;
        try {
            if (stmtCentralDMS == null) {
                stmtCentralDMS = QueryCenter.conCentralDMS.createStatement();
            }
            ps = "SELECT levelno FROM level_c L INNER JOIN FACTOR F ON F.LABELID = L.LABELID AND F.FACTORID=L.FACTORID WHERE FNAME ='INTRID' AND L.lvalue IN ('" + Intrid_ + "')";
            System.out.println(ps);
            rs = stmtCentralDMS.executeQuery(ps);
            while (rs.next()) {
                cadenaLEVELNO += rs.getString("levelno") + ",";
            }
            rs.close();
            QueryCenter.studyID=0;
            if (cadenaLEVELNO.length() > 1) {
                cadenaLEVELNO = cadenaLEVELNO.substring(0, cadenaLEVELNO.length() - 1);
                ps = "SELECT studyid FROM level_N l1 INNER JOIN FACTOR F ON F.LABELID = L1.LABELID AND F.FACTORID=L1.FACTORID WHERE FNAME ='ENTRY NUMBER' AND L1.lvalue = " + ent_ + " AND levelno IN(" + cadenaLEVELNO + ")";
                System.out.println(ps);
                rs = stmtCentralDMS.executeQuery(ps);
                if (rs.next()) {
                    QueryCenter.studyID = rs.getInt("studyid");
                }
            }
            return QueryCenter.studyID;
        } catch (SQLException sqle) {
            System.out.println("obtieneTIDParaesteINTRIDENT:" + sqle.getMessage());
            return 0;
        }
    }
    
    /**
     * 
     * @param cid_
     * @param sid_
     * @return
     * @throws SQLException
     */
    public int getGIDBYCIDPlusSID(long cid_, long sid_) throws SQLException {
       //falta tomar en cuenta changes y la base local.
        String ps; //regresa el GId correcto. Toma en cuenta GRPLCE
        rs = null;
        int tempGID = 0;
        try {
            if (stmtCentralGMS == null) {
                stmtCentralGMS = QueryCenter.conCentralGMS.createStatement();
            }
            ps = "SELECT GID, GRPLCE FROM GERMPLSM WHERE GID<>GRPLCE and CID=" + cid_ + " and SID = " + sid_;
            rs = stmtCentralGMS.executeQuery(ps);
            long tempGRPLCE = 0;
            if (rs.next()) {
                tempGRPLCE = rs.getInt("GRPLCE");
                tempGID = rs.getInt("GID");
            }
            while (tempGRPLCE != 0) {
                ps = "SELECT GID, GRPLCE FROM GERMPLSM WHERE GID<>GRPLCE and GID=" + tempGRPLCE;
                rs.close();
                rs = stmtCentralGMS.executeQuery(ps);
                if (rs.next()) {
                    tempGRPLCE = rs.getInt("GRPLCE");
                    tempGID = rs.getInt("GID");
                }
            }
            return tempGID;//aqui es en donde se asigna el correcto GID
        } catch (SQLException sqle) {
            System.out.println("obtieneCIDSIDInfo:" + sqle.getMessage());
            throw sqle;
        }
    }
    /**
     * 
     * @param grTemp
     * @throws SQLException
     */
    
    
    
   /* 
    public void consigueDatosCruza(GermplsmRecord grTemp) throws SQLException {
        //comentario en consigue datos cruza
        String ps;
        rs = null;
        if (grTemp.getCid() == 0) {
            try {
                MainWindow.cYear = String.valueOf(grTemp.getGdate());
                if (MainWindow.cYear.length() > 4) {
                    MainWindow.cYear = MainWindow.cYear.substring(0, 4);
                }
                Connection connection;
                if (grTemp.getGid() < 0) {
                    connection = QueryCenter.conLocalGMS;
                } else {
                    connection = QueryCenter.conCentralGMS;
                }
                Statement statement = null;
                statement = connection.createStatement();
                if (grTemp.getGlocn() > 300 && grTemp.getGlocn() != 9004) {
                    ps = "SELECT lname, labbr FROM location WHERE locid=" + grTemp.getGlocn() + ";";
                    rs = statement.executeQuery(ps);
                    if (rs.next()) {
                        MainWindow.cLocation = rs.getString(2);
                        if (Main.copts.isOptShowFullLocationName()) {
                            MainWindow.cLocation += " [" + rs.getString(1) + "]";
                        }
                    }
                    rs.close();
                    ps = "SELECT isoabbr, isothree FROM cntry WHERE cntryid=(SELECT cntryid FROM location WHERE locid=" + grTemp.getGlocn() + "))";
                    rs = statement.executeQuery(ps);
                    if (rs.next()) {
                        MainWindow.cCountry = rs.getString(1);
                        if (Main.copts.isOptUseCC3forCountry()) {
                            MainWindow.cCountry = rs.getString(2);
                        }
                    }
                    rs.close();
                }
                ps = "SELECT aval FROM atributs WHERE GID=" + grTemp.getGid() + " AND atype=1194";
                rs = statement.executeQuery(ps);
                if (rs.next()) {
                    MainWindow.cProgram = rs.getString(1);
                }
                rs.close();
                ps = "SELECT aval FROM atributs WHERE GID=" + grTemp.getGid() + " AND atype=105";
                rs = statement.executeQuery(ps);
                if (rs.next()) {
                    String strTemp = rs.getString(1);
                    int temppos = strTemp.indexOf("-");
                    if (temppos > 0) {
                        MainWindow.cOrganization = strTemp.substring(0, temppos).trim();
                    }
                }
            } catch (Exception e) {
            }
            return;
        }
        try {

            if (stmtCentralGMS == null) {
                stmtCentralGMS = QueryCenter.conCentralGMS.createStatement();
            }
            ps = "SELECT GID, GRPLCE FROM GERMPLSM WHERE GID<>GRPLCE and CID=" + grTemp.getCid() + " and SID = 0";
            rs = stmtCentralGMS.executeQuery(ps);
            long tempGRPLCE = 0;
            int tempGID = 0;
            if (rs.next()) {
                tempGID = rs.getInt(1);
                tempGRPLCE = rs.getInt(2);
            }
            while (tempGRPLCE != 0) {
                ps = "SELECT GID, GRPLCE FROM GERMPLSM WHERE GID<>GRPLCE and GID=" + tempGRPLCE;
                rs.close();
                rs = stmtCentralGMS.executeQuery(ps);
                if (rs.next()) {
                    tempGID = rs.getInt(1);
                    tempGRPLCE = rs.getInt(2);
                }
            }
            if (tempGID == 0) {
                return;
            }
            ps = "SELECT gdate FROM GERMPLSM WHERE GID=" + tempGID;
            rs = stmtCentralGMS.executeQuery(ps);
            MainWindow.cYear = "0";
            if (rs.next()) {
                MainWindow.cYear = rs.getString(1);
            }
            if (MainWindow.cYear.length() > 4) {
                MainWindow.cYear = MainWindow.cYear.substring(0, 4);
            }

            rs.close();
            ps = "SELECT lname, labbr FROM location WHERE locid=(SELECT glocn FROM germplsm WHERE gID=" + tempGID + " and glocn>300 and glocn<>9004)";
            rs = stmtCentralGMS.executeQuery(ps);
            if (rs.next()) {
                MainWindow.cLocation = rs.getString(2);
                if (Main.copts.isOptShowFullLocationName()) {
                    MainWindow.cLocation += " [" + rs.getString(1) + "]";
                }
            }
            rs.close();
            ps = "SELECT isoabbr, isothree FROM cntry WHERE cntryid=(SELECT cntryid FROM location WHERE locid=(SELECT glocn FROM germplsm WHERE GID=" + tempGID + " and glocn<>9004))";
            rs = stmtCentralGMS.executeQuery(ps);
            if (rs.next()) {
                MainWindow.cCountry = rs.getString(1);
                if (Main.copts.isOptUseCC3forCountry()) {
                    MainWindow.cCountry = rs.getString(2);
                }
            }
            rs.close();
            ps = "SELECT aval FROM atributs WHERE GID=" + tempGID + " AND atype=1194";
            rs = stmtCentralGMS.executeQuery(ps);
            if (rs.next()) {
                MainWindow.cProgram = rs.getString(1);
            }
            rs.close();
            ps = "SELECT aval FROM atributs WHERE GID=" + tempGID + " AND atype=105";
            rs = stmtCentralGMS.executeQuery(ps);
            if (rs.next()) {
                String strTemp = rs.getString(1);
                int temppos = strTemp.indexOf("-");
                if (temppos > 0) {
                    MainWindow.cOrganization = strTemp.substring(0, temppos).trim();
                }
            }
        } catch (SQLException sqle) {
            System.out.println("consigueDatosCruza:" + sqle.getMessage());
            throw sqle;
        } finally {
        }
    }
    
    
    */
    
    
    
    /**
     * 
     * @param TempGR
     * @throws SQLException
     */
    
    
   /* 
    
    public void consigueDatosSeleccion(GermplsmRecord TempGR) throws SQLException {
        String ps;//IWIS_CENTRAL_GMS.
        int tempGlocn = 0;
        rs = null;
        try {
            Connection connection;
            if (TempGR.getGid() < 0) {
                connection = Main.conLocalGMS;
            } else {
                connection = Main.conCentralGMS;
            }
            Statement statement = null;
            statement = connection.createStatement();
            if (TempGR.getSid() == 0) {
                MainWindow.sYear = String.valueOf(TempGR.getGdate());
                if (MainWindow.sYear.length() > 4) {
                    MainWindow.sYear = MainWindow.sYear.substring(0, 4);
                }
                int tempCntryId = 0;
               // if (TempGR.getGlocn()==9004){
               //    TempGR.setGlocn(0);
               // }
                ps = "SELECT lname, labbr, cntryid FROM location WHERE locid=" + TempGR.getGlocn();
                rs = statement.executeQuery(ps);
                if (rs.next()) {
                    MainWindow.sLocation = rs.getString(2);
                    if (Main.copts.isOptShowFullLocationName()) {
                        MainWindow.sLocation += " [" + rs.getString(1) + "]";
                    }
                    tempCntryId = rs.getInt(3);
                }
                rs.close();
                ps = "SELECT isoabbr, isothree FROM cntry WHERE cntryid=" + tempCntryId;
                rs = statement.executeQuery(ps);
                if (rs.next()) {
                    MainWindow.sCountry = rs.getString(1);
                    if (Main.copts.isOptUseCC3forCountry()) {
                        MainWindow.sCountry = rs.getString(2);
                    }
                }
                return;
            } else {
                //primero debemos verificar que sid <>0
                ps = "SELECT count(*) FROM GERMPLSM WHERE SID <> 0 and GID=" + TempGR.getGid();
                rs = statement.executeQuery(ps);
                rs.next();
                if (rs.getInt(1) == 0) {
                    return;
                }
//         ps = "SELECT gdate, glocn FROM GERMPLSM WHERE GID=" + gid_ + " AND MGID=GID";//para local poner este source
                ps = "SELECT gdate, glocn FROM GERMPLSM WHERE SID <> 0 and GID=" + TempGR.getGid();
                rs = statement.executeQuery(ps);
                MainWindow.sYear = "0";
                if (rs.next()) {
                    MainWindow.sYear = rs.getString(1);
                    tempGlocn = rs.getInt(2);
                }
                if (MainWindow.sYear.length() > 4) {
                    MainWindow.sYear = MainWindow.sYear.substring(0, 4);
                }
                rs.close();
                int tempCntryId = 0;
                ps = "SELECT lname, labbr, cntryid FROM location WHERE locid<> 9004 and locid=" + tempGlocn;
                rs = statement.executeQuery(ps);
                if (rs.next()) {
                    MainWindow.sLocation = rs.getString(2);
                    if (Main.copts.isOptShowFullLocationName()) {
                        MainWindow.sLocation += " [" + rs.getString(1) + "]";
                    }
                    tempCntryId = rs.getInt(3);
                }
                rs.close();
                ps = "SELECT isoabbr, isothree FROM cntry WHERE cntryid=" + tempCntryId;
                rs = statement.executeQuery(ps);
                if (rs.next()) {
                    MainWindow.sCountry = rs.getString(1);
                    if (Main.copts.isOptUseCC3forCountry()) {
                        MainWindow.sCountry = rs.getString(2);
                    }
                }
            }
        } catch (SQLException sqle) {
            System.out.println("consigueDatosSeleccion:" + sqle.getMessage());
            throw sqle;
        } finally {
        }
    }

    
    
    */
    /**
     * 
     * @param gid_
     * @throws SQLException
     */
    
    /*
    
    public void consigueDatosFAO(long gid_) throws SQLException {
        String ps;//IWIS_CENTRAL_GMS.
        rs = null;
        try {
            Connection connection;
            if (gid_<0){
            connection=QueryCenter.conLocalGMS;
            }else{
                connection=Main.conCentralGMS;
            }
            Statement statement=null;
            statement=connection.createStatement();
            long tempGID = 0;
            tempGID = gid_;
            ps = "SELECT aval FROM atributs WHERE GID=" + tempGID + " AND atype=1198";
            rs = statement.executeQuery(ps);
            MainWindow.faoIntrust="N";
            if (rs.next()) {
                MainWindow.faoIntrust = rs.getString(1);
                rs.close();
                ps = "SELECT aval FROM atributs WHERE GID=" + tempGID + " AND atype=223";
                rs = statement.executeQuery(ps);
                if (rs.next()) {
                    MainWindow.faoDesignationDate = rs.getString(1);
                }
            }
        } catch (SQLException sqle) {
            System.out.println("consigueDatosFAO:" + sqle.getMessage());
            throw sqle;
        }
    }

    
    */
    
    
    /**
     * 
     * @param N_TYPE_
     * @param GID_
     * @param STATE_
     * @param ALTERNATIVE_NTYPE_
     * @return
     * @throws SQLException
     */
    public String obtieneEstoDeEstaEntradaGMS(int N_TYPE_, long GID_, int STATE_, int ALTERNATIVE_NTYPE_) throws SQLException {
        String pr = "";
        String ps;//IWIS_CENTRAL_GMS.
        ps = "select NVAL from NAMES where (NTYPE = " + N_TYPE_;
        if (ALTERNATIVE_NTYPE_ > 0) {
            ps += " OR NTYPE = " + ALTERNATIVE_NTYPE_;
        }
        ps += ") and GID = " + GID_;
        if (STATE_ > 0) {
            ps = ps + " AND NSTAT = " + STATE_ + " ORDER BY NTYPE";
        }
        rs = null;
        try {
            Connection connection;
            if (GID_<0){
            connection=QueryCenter.conLocalGMS;
            }else{
                connection=QueryCenter.conCentralGMS;
            }
            Statement statement=null;
            statement=connection.createStatement();
            rs = statement.executeQuery(ps);
            if (rs.next()) {
                pr = rs.getString("NVAL");
            }
        } catch (SQLException sqle) {
            System.out.println("obtieneEstoDeEstaEntradaGMS:" + sqle.getMessage());
            throw sqle;
        }
        return pr;
    }

    /**
     * 
     * @param pSelectionHistory_
     * @return
     * @throws SQLException
     */
    
   /*
    
    public int obtieneGIDApartirDeSelectionHistory(String pSelectionHistory_) throws SQLException {
        int pr = 0;
        String ps;//IWIS_CENTRAL_GMS.
        String XParaSelect = "";
        rs = null;
        try {
            if (stmtCentralGMS == null) {
                stmtCentralGMS = Main.conCentralGMS.createStatement();
            }
            XParaSelect = "from NAMES where  nval = '" + pSelectionHistory_ + "'  and NTYPE in (1027,1028)  And NSTAT=1;";
            ps = "select count(*) " + XParaSelect;
            rs = stmtCentralGMS.executeQuery(ps);
            rs.next();
            if (rs.getInt(1) > 0) {
                rs.close();
                rs = null;
                ps = "select GID " + XParaSelect;
                rs = stmtCentralGMS.executeQuery(ps);
                rs.next();
                pr = rs.getInt("GID");
                return pr;
            } else {
                rs.close();
                rs = null;
                XParaSelect = "from NAMES where  nval = '" + pSelectionHistory_ + "'  and NTYPE in (1027,1028)  And NSTAT<>9;";
                ps = "select count(*) " + XParaSelect;
                rs = stmtCentralGMS.executeQuery(ps);
                rs.next();
                if (rs.getInt(1) > 0) {
                    rs.close();
                    rs = null;
                    ps = "select GID " + XParaSelect;
                    rs = stmtCentralGMS.executeQuery(ps);
                    rs.next();
                    pr = rs.getInt("GID");
                    return pr;
                }
            }
        } catch (SQLException sqle) {
            System.out.println("obtieneGIDApartirDeSelectionHistory:" + sqle.getMessage());
            throw sqle;
        }
        return pr;
    }

    */
    
    /**
     * 
     * @param pPedigree_
     * @return
     * @throws SQLException
     */
    public int obtieneGIDApartirDePedigree(String pPedigree_) throws SQLException {
        int pr = 0;
        String ps;//IWIS_CENTRAL_GMS.
        String XParaSelect = "";
        rs = null;
        try {
            if (stmtCentralGMS == null) {
                stmtCentralGMS = QueryCenter.conCentralGMS.createStatement();
            }
            XParaSelect = "from NAMES where  nval = '" + pPedigree_ + "'  and NTYPE in (2,6,7,17,1062) And NSTAT IN (1,2);";
            ps = "select count(*) " + XParaSelect;
            rs = stmtCentralGMS.executeQuery(ps);
            rs.next();
            if (rs.getInt(1) > 0) {
                rs.close();
                rs = null;
                ps = "select GID " + XParaSelect;
                rs = stmtCentralGMS.executeQuery(ps);
                if (rs.next())
                pr = rs.getInt(1);
                return pr;
            }
             else {
                rs.close();
                rs = null;
                XParaSelect = "from NAMES where  nval = '" + pPedigree_ + "' And NTYPE = 1029 And NSTAT<>9;";
                ps = "select count(*) " + XParaSelect;
                rs = stmtCentralGMS.executeQuery(ps);
                rs.next();
                if (rs.getInt(1) > 0) {
                    rs.close();
                    rs = null;
                     ps = "select GID " + XParaSelect;
                    rs = stmtCentralGMS.executeQuery(ps);
                    if (rs.next()) {
                        pr = rs.getInt(1);
                        return pr;
                    }
                }
                else{
//                    XParaSelect="hhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhheeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeyyyyyyyyyyy"
//                    String XParaSelect1="";
//                    XParaSelect1 = "from germplsm where gpid1 IN( " + XParaSelect + ") And mgid=gid;";
//                    ps = "select count(*) " + XParaSelect1;
//                    rs = stmtCentralGMS.executeQuery(ps);
//                    rs.next();
//                    if (rs.getInt(1) > 0) {
//                        rs.close();
//                        rs = null;
//                        ps = "select GID " + XParaSelect1;
//                        //System.out.println(ps);
//                        rs = stmtCentralGMS.executeQuery(ps);
//                        rs.next();
//                        pr = rs.getInt(1);
//                        return pr;
//                    }
                }
            }
        } catch (SQLException sqle) {
            System.out.println("obtieneGIDApartirDePedigree:" + sqle.getMessage());
            throw sqle;
        }
        return pr;
    }

    /**
     * 
     * @param specificName_
     * @return
     * @throws SQLException
     */
    public int getGIDbySpecificName(String specificName_) throws SQLException {
        int pr = 0;
        String ps;//IWIS_CENTRAL_GMS.
        String XParaSelect = "";
        rs = null;
        try {
            if (stmtCentralGMS == null) {
                stmtCentralGMS = QueryCenter.conCentralGMS.createStatement();
            }
            XParaSelect = "from NAMES where  nval = '" + specificName_ + "'  and NTYPE in (6,7,17) And NSTAT IN (0,1);";
            ps = "select count(*) " + XParaSelect;
            rs = stmtCentralGMS.executeQuery(ps);
            rs.next();
            if (rs.getInt(1) > 0) {
                rs.close();
                rs = null;
                ps = "select GID " + XParaSelect;
                rs = stmtCentralGMS.executeQuery(ps);
                rs.next();
                pr = rs.getInt(1);
                return pr;
            }
         else{
            XParaSelect = "from NAMES where  nval = '" + specificName_ + "';";
            ps = "select count(*) " + XParaSelect;
            rs = stmtCentralGMS.executeQuery(ps);
            rs.next();
            if (rs.getInt(1) > 0) {
                rs.close();
                rs = null;
                ps = "select GID " + XParaSelect;
                rs = stmtCentralGMS.executeQuery(ps);
                rs.next();
                pr = rs.getInt(1);
                return pr;
            }
         }
        } catch (SQLException sqle) {
            System.out.println("obtieneGIDApartirDePedigree:" + sqle.getMessage());
            throw sqle;
        }
        return pr;                        
    }
//////
//////    public Long obtieneGIDDeEstoEnGMSCENTRAL(String val_, byte queEs) throws SQLException {
//////        Long pr = (long) 0;
//////        String paraIN = "";
//////        paraIN = "23,1";
//////        String ps;//IWIS_CENTRAL_GMS.
//////        ps = "select GID from NAMES where  nval = '" + val_ + "'  and NTYPE in ( " + paraIN + ")  And NSTAT<>9";
////////System.out.println(ps);
//////        rs = null;
//////        try {
//////            if (stmtCentralGMS == null) {
//////                stmtCentralGMS = Main.conCentralGMS.createStatement();
//////            }
//////            rs = stmtCentralGMS.executeQuery(ps);
//////            if (rs.next()) {
//////                pr = rs.getInt(1);
//////            }
//////        } catch (SQLException sqle) {
//////            System.out.println("obtieneGIDDeEstoEnGMSCENTRAL:" + sqle.getMessage());
//////            throw sqle;
//////        }
//////        return pr;
//////    }

        /**
         * 
         * @param p_intrid
         * @return
         * @throws SQLException
         */
        public int obtieneGIDApartirdeINTRID(String p_intrid) throws SQLException {
        int pr = (int) 0;
        String paraIN = "";
        String ps;
        paraIN = "23,1, 1701";
        ps = "select GID from NAMES where  nval = '" + p_intrid + "'  and NTYPE in ( " + paraIN + ")  And NSTAT<>9 order by ntype desc";
        rs = null;
        try {
            if (stmtCentralGMS == null) {
                stmtCentralGMS = QueryCenter.conCentralGMS.createStatement();
            }
            rs = stmtCentralGMS.executeQuery(ps);
            if (rs.next()) {
                pr = rs.getInt(1);
            }

        } catch (SQLException sqle) {
            System.out.println("obtieneGIDDeEstoEnGMSCENTRAL:" + sqle.getMessage());
            throw sqle;
        }
        return pr;
    }

        /**
         * 
         * @param p_GID
         * @return
         * @throws SQLException
         */
        public String SPGMSGetIWIS3SelHist(int p_GID) throws SQLException {
        String p_SelHist;//varchar(255) = '' output
        int x_count = 0;
        p_SelHist = "";
        rs = null;
        try {
            Connection connection;
            if (p_GID < 0) {
                connection = QueryCenter.conLocalGMS;
            } else {
                connection = QueryCenter.conCentralGMS;
            }
            Statement statement = null;
            statement = connection.createStatement();
            rs = statement.executeQuery("Select Count(*) From NAMES Where GID=" + p_GID + " And NSTAT=1 And NTYPE IN (1027,1028)");

            rs.next();
            x_count = rs.getInt(1);
            if (x_count > 0) {
                rs.close();
                rs = statement.executeQuery("Select NVAL From NAMES Where GID=" + p_GID + " And NSTAT=1 And NTYPE IN (1027,1028)");
                rs.next();
                p_SelHist = rs.getString(1);
                if (p_SelHist.startsWith("###")) {
                    p_SelHist = "";

                }
                return p_SelHist;

            }
            rs.close();
            rs = statement.executeQuery("Select Count(*) From NAMES Where GID=" + p_GID + " And NTYPE IN (1027,1028) And NSTAT<>9");
            rs.next();
            x_count = rs.getInt(1);
            if (x_count > 0) {
                rs.close();

                rs = statement.executeQuery("Select nval From NAMES Where GID=" + p_GID + " And NTYPE IN (1027,1028) And NSTAT<>9");
                rs.next();
                p_SelHist = rs.getString(1);
                if (p_SelHist.startsWith("###")) {
                    p_SelHist = "";
                }
                return p_SelHist;
            }
        } catch (Exception e) {
            System.out.println("SP_GMS_Get_IWIS3_SelHist:" + e.getMessage());
        }
        System.out.println("Nothing found");
        return p_SelHist;
    }
// public String SPGMSGetIWIS3SelHist(int p_GID) throws SQLException {
//        String p_SelHist;//varchar(255) = '' output
//        int x_count = 0;
//        p_SelHist = "";
//        rs = null;
//        try {
//            Connection connection;
//            if (p_GID < 0) {
//                connection = Main.conLocalGMS;
//            } else {
//                connection = Main.conCentralGMS;
//            }
//            Statement statement = null;
//            statement = connection.createStatement();
//            rs = statement.executeQuery("Select Count(*) From NAMES Where GID=" + p_GID + " And NSTAT=1 And NTYPE IN (1027,1028)");
//
//            rs.next();
//            x_count = rs.getInt(1);
//            if (x_count > 0) {
//                rs.close();
//                rs = statement.executeQuery("Select NVAL From NAMES Where GID=" + p_GID + " And NSTAT=1 And NTYPE IN (1027,1028)");
//                rs.next();
//                p_SelHist = rs.getString(1);
//                if (p_SelHist.startsWith("###")) {
//                    p_SelHist = "";
//
//                }
//                return p_SelHist;
//
//            }
//            rs.close();
//            rs = statement.executeQuery("Select Count(*) From NAMES Where GID=" + p_GID + " And NTYPE IN (1027,1028) And NSTAT<>9");
//            rs.next();
//            x_count = rs.getInt(1);
//            if (x_count > 0) {
//                rs.close();
//
//                rs = statement.executeQuery("Select nval From NAMES Where GID=" + p_GID + " And NTYPE IN (1027,1028) And NSTAT<>9");
//                rs.next();
//                p_SelHist = rs.getString(1);
//                if (p_SelHist.startsWith("###")) {
//                    p_SelHist = "";
//                }
//                return p_SelHist;
//            }
//        } catch (Exception e) {
//            System.out.println("SP_GMS_Get_IWIS3_SelHist:" + e.getMessage());
//        }
//        System.out.println("Nothing found");
//        return p_SelHist;
//    }
//

        /**
         * 
         * @param p_gid
         * @return
         * @throws SQLException
         */
        public String getSpecificName(int p_gid) throws SQLException {
        String pr = "";
        String ps;
        ps = "select NVAL from NAMES where NTYPE IN (6, 7) AND NSTAT IN (0, 1) " + " and GID = " + p_gid + " ORDER BY NUID, nstat desc";
        rs = null;
        Connection connection;
        Statement statement = null;
        try {
            if (p_gid < 0) {
                connection = QueryCenter.conLocalGMS;
            } else {
                connection = QueryCenter.conCentralGMS;
            }
            statement = connection.createStatement();
            rs = statement.executeQuery(ps);
            if (rs.next()) {
                pr = rs.getString("NVAL");
            }
            if (pr.equals("")) {
                pr = obtieneEstoDeEstaEntradaGMS(17,  p_gid, 1, 0);
            }
        } catch (SQLException sqle) {
            System.out.println("getSpecificName:" + sqle.toString());
            throw sqle;
        }
        return pr;
    }

        /**
         * 
         * @param gid_
         * @return
         * @throws SQLException
         */
        public String getnameAbbreviation(int gid_) throws SQLException {
        String pr = "";
        try {
            pr = obtieneEstoDeEstaEntradaGMS(17, gid_, 2, 13);//cambiar por rutina solo hace caso a nstate=2
            if (pr.equals("")) {
                pr = obtieneEstoDeEstaEntradaGMS(17, gid_, 1, 0);
            }
        } catch (Exception e) {
            System.out.println("getnameAbbreviation:" + e.toString());
        }
        return pr;
    }
    /**
     * 
     * @param p_gid
     * @return
     * @throws SQLException
     */
    public String getNameYear(int p_gid) throws SQLException {
        String pr = "0", ps = "";
        rs1 = null;
        Connection connection;
        Statement statement = null;
        try {
            if (p_gid < 0) {
                connection = QueryCenter.conLocalGMS;
            } else {
                connection = QueryCenter.conCentralGMS;
            }
            statement = connection.createStatement();
            ps = "SELECT names.ndate FROM names where names.gid=" + p_gid + " AND names.nstat=1 AND names.ntype in (2,6,7,17)";
            rs1 = statement.executeQuery(ps);
            if (rs1.next()) {
                pr = rs1.getString(1);
            }
            if (pr.length() > 4) {
                pr = pr.substring(0, 4);
            }
        } catch (SQLException sqle) {
            System.out.println("getNameYear:" + sqle.toString());
            throw sqle;
        } finally {
            return pr;
        }
    }
    /**
     * 
     * @param p_gid
     * @return
     * @throws SQLException
     */
    
    
    /*
    public String getNameCountry(int p_gid) throws SQLException {
        String pr = "", ps = "";
        rs = null;
        Connection connection;
        Statement statement = null;
        int tempCntryId = 0;
        try {
           if (p_gid < 0) {
                connection = QueryCenter.conLocalGMS;
            } else {
                connection = QueryCenter.conCentralGMS;
            }
            statement = connection.createStatement();

            ps = "SELECT LOCATION.CNTRYID FROM LOCATION INNER JOIN names ON names.NLOCN = LOCATION.LOCID"
                    + " where (((names.gid)=" + p_gid + ") AND ((names.nstat)=1) AND ((names.ntype)in (6,17,2,7)) and (names.NLOCN<>9004))";
            rs = statement.executeQuery(ps);
            if (rs.next()) {
                tempCntryId = rs.getInt(1);
            }
            ps = "SELECT isoabbr, isothree FROM cntry WHERE cntryid=" + tempCntryId;
            rs = statement.executeQuery(ps);
            if (rs.next()) {
                pr = rs.getString(1);
                if (Main.copts.isOptUseCC3forCountry()) {
                    pr = rs.getString(2);
                }
            }
            return pr;
        } catch (SQLException sqle) {
            System.out.println("getNameCountry:" + sqle.toString());
            throw sqle;
        }
    }
    
    
    */

    /**
     * 
     * @param p_gid
     * @return
     */
    public ResultSet consigueRSNonPreferedNames(int p_gid) {
        ResultSet rsax = null;
        String ps;
        ps = "select nval from names where ntype = 1062 and GID=" + p_gid;
        System.out.println("quiero entrar en consigueRSNonPreferedNames:" + ps);
        try {
            if (stmtCentralGMSMain2 == null) {
                stmtCentralGMSMain2 = QueryCenter.conCentralGMS.createStatement();
            }
            rsax = stmtCentralGMSMain2.executeQuery(ps);
        } catch (Exception e) {
            System.out.println("error, RS consigueRSNonPreferedNames:" + e.getMessage());
        }
        return rsax;
    }
    /**
     * 
     * @param p_cid
     * @return
     */
    
    
    /*
    public ResultSet consigueRSAllSelectionsForCID(int p_cid) {
        ResultSet rsax = null;
        String ps;
        String cadenaIN="";
        boolean tieneGPIDs=true;
        //ps = "select GID, CID, SID from GERMPLSM where grplce=0 and CID=" + Main._cid + " and SID <> " + Main._sid + " order by SID";
        ps = "select GID, CID, SID from GERMPLSM where grplce=0 and CID=" + p_cid + " order by SID";
        if (Main.copts.isOptShowForeignGIDS()) {
            // antes ps = "SELECT DISTINCT GPid1 FROM GERMPLSM WHERE CID= SELECT GERMPLSM.GID, GERMPLSM.CID, GERMPLSM.SID FROM GERMPLSM WHERE (GERMPLSM.SID)<>" + Main._sid + " and (GPid1 in (SELECT GPid1 FROM GERMPLSM WHERE CID= " + Main._cid + ") OR gid in (SELECT GPid1 FROM GERMPLSM WHERE CID= " + Main._cid + ")) order by CID, SID";
            ps = "SELECT DISTINCT GPid1 FROM GERMPLSM WHERE gnpgs=-1 and GPid1<>0 AND CID=" + p_cid;
            cadenaIN = "";
            try {
                tieneGPIDs=false;
                if (stmtCentralGMSMain == null) {
                    stmtCentralGMSMain = QueryCenter.conCentralGMS.createStatement();
                }
                rsax = stmtCentralGMSMain.executeQuery(ps);                
                while (rsax.next()){
                    tieneGPIDs=true;
                    cadenaIN+=rsax.getString(1) + ",";
                }
                if (tieneGPIDs){
                   cadenaIN=cadenaIN.substring(0, cadenaIN.length()-1);
                }

            } catch (Exception e) {
                System.out.println("RS consiue all selections P1:" + e.getMessage());
            }
            ps = "SELECT GID, CID, SID FROM GERMPLSM WHERE ";
            ps += "(GPid1 IN("+ cadenaIN +") ";
            ps += "OR ";
            ps += "gid IN(" + cadenaIN + ")) ";
            ps += "AND grplce=0 ORDER BY CID desc, SID";
        }
        System.out.println("quiero entrar en all Selections" + ps);
        try {
            if (stmtCentralGMSMain == null) {
                stmtCentralGMSMain = QueryCenter.conCentralGMS.createStatement();
            }
            if (tieneGPIDs){
            rsax = stmtCentralGMSMain.executeQuery(ps);
            }
        } catch (Exception e) {
            System.out.println("RS consiue all selections:" + e.getMessage());
        }
        return rsax;
    }
    
    */
    
    /**
     * 
     * @param valINTRID
     * @param p_gid
     * @return
     */
    public ResultSet consigueRSAllINTRID(String valINTRID, int p_gid) {
        ResultSet rsax = null;
        String ps;
        ps = "SELECT Nval FROM NAMES WHERE ntype IN (1, 23, 1701) AND GID = " + p_gid + " and nval<> '" + valINTRID + "' order by nval";
        System.out.println("quiero entrar en all INTRID" + ps);
        try {
            if (stmtCentralGMSMain1 == null) {
                stmtCentralGMSMain1 = QueryCenter.conCentralGMS.createStatement();
            }
            rsax = stmtCentralGMSMain1.executeQuery(ps);
        } catch (Exception e) {
            System.out.println("RS consiue all INTRIDs:" + e.getMessage());
        }
        return rsax;
    }
    /**
     * 
     * @param valGID
     * @return
     */
    public ResultSet consigueRSAllTIDS(long valGID) {
        ResultSet rsax = null;
        String ps;
        ps = "SELECT studyid, levelno FROM factor f inner join level_n l on l.factorid=f.factorid and l.labelid = f.labelid where fname = 'GID' AND lvalue = " + valGID;
        if (QueryCenter.studyID>0)
            ps= ps + " and studyid <>" + QueryCenter.studyID;
        try {
            if (stmtCentralDMS1 == null) {
                stmtCentralDMS1 = QueryCenter.conCentralDMS.createStatement();
            }
            rsax = stmtCentralDMS1.executeQuery(ps);
        } catch (Exception e) {
            System.out.println("RS consigue all TIDs:" + e.getMessage());
        }
        return rsax;
    }

    /**
     * 
     * @param p_Tid
     * @return
     * @throws SQLException
     */
    public String getSname(long p_Tid) throws SQLException {
        String pr = "";
        String ps;
        Connection connection;
        Statement statement = null;
        ps = "select sname from study where  studyid = " + p_Tid;
        rs = null;
        try {
           if (p_Tid < 0) {
                connection = QueryCenter.conLocalDMS;
            } else {
                connection = QueryCenter.conCentralDMS;
            }
            statement = connection.createStatement();
            rs = statement.executeQuery(ps);
            if (rs.next()) {
                pr = rs.getString(1);
            }
        } catch (SQLException sqle) {
            System.out.println("obtiene sname:" + sqle.getMessage());
            throw sqle;
        }
        return pr;
    }
    /**
     * 
     * @param p_Tid
     * @return
     * @throws SQLException
     */
    public String getTitle(long p_Tid) throws SQLException {
        Connection connection;
        Statement statement = null;
        String pr = "";
        String ps;
        ps = "select title from study where  studyid = " + p_Tid;
        rs = null;
        try {
            if (p_Tid < 0) {
                connection = QueryCenter.conLocalDMS;
            } else {
                connection = QueryCenter.conCentralDMS;
            }
            statement = connection.createStatement();
            rs = statement.executeQuery(ps);
            if (rs.next()) {
                pr = rs.getString(1);
            }
        } catch (SQLException sqle) {
            System.out.println("obtiene sname:" + sqle.getMessage());
            throw sqle;
        }
        return pr;
    }

    class InvalidTransferException extends Exception {

        public InvalidTransferException(String message) {
            super(message);
        }
    }
    //public static Statement stmtLocalGMS;//aun no se usa
    //public static Statement stmtLocalDMS;//aun no se usa
    
    /**
     * 
     */
    public static Statement stmtCentralGMS;
    /**
     * 
     */
    public static Statement stmtLocalGMS;
    /**
     * 
     */
    public static Statement stmtCentralDMS;
    /**
     * 
     */
    public static Statement stmtLocalDMS;
    /**
     * 
     */
    public static Statement stmtCentralDMS1;
    /**
     * 
     */
    public static Statement stmtCentralGMSMain;
    /**
     * 
     */
    public static Statement stmtCentralGMSMain1;
    /**
     * 
     */
    public static Statement stmtCentralGMSMain2;
    /**
     * 
     */
    public static ResultSet rs;
    /**
     * 
     */
    public static ResultSet rs1;
}
