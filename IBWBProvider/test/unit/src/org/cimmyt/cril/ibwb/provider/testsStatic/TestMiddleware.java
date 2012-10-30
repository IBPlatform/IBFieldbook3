package org.cimmyt.cril.ibwb.provider.testsStatic;

import java.util.List;
import org.apache.log4j.Logger;
import org.generationcp.middleware.exceptions.QueryException;
import org.generationcp.middleware.manager.Database;
import org.generationcp.middleware.manager.FindGermplasmByNameModes;
import org.generationcp.middleware.manager.GermplasmNameType;
import org.generationcp.middleware.manager.Operation;
import org.generationcp.middleware.manager.api.GermplasmDataManager;
import org.generationcp.middleware.pojos.*;
import org.openide.util.Exceptions;

/**
 *
 * @author TMSANCHEZ
 */
public class TestMiddleware extends TestService {

    private static Logger log = Logger.getLogger(TestWorkbook.class);
    //get the DataManager object you want to use
    GermplasmDataManager manager = this.servicios.getGermplasmDataManager();

    public void testGermplasmTree() {
        //get the DataManager object you want to use


        //use the function needed from the manager
        try {
            System.out.println("Retrieving the pedigree tree of 3888...");
            GermplasmPedigreeTree tree = manager.generatePedigreeTree(Integer.valueOf(3888), 4);

            //print the result on the console
            if (tree != null) {
                System.out.println("Printing the pedigree tree of 50533...");
                printNode(tree.getRoot(), 1);
            }
        } catch (QueryException ex) {
            System.out.println(ex);
            ex.printStackTrace();
        }
    }

    public void testgetAttributesByGID() {


        //use the function needed from the manager
        try {
            Integer gid = 3888;
            System.out.println("Attributs for... " + gid);
            List<Attribute> atributeList = manager.getAttributesByGID(Integer.valueOf(gid));
            System.out.println("Attibuts for ");
            for (Attribute attribute : atributeList) {
                System.out.println(attribute.getAid() + "\t" + attribute.getAval());
            }
        } catch (QueryException ex) {
            System.out.println(ex);
            ex.printStackTrace();
        }
    }

    public void testgetAllMethods() {
        try {
            List<Method> list = manager.getAllMethods();
            for (Method method : list) {
                System.out.println(method.getMname());
            }
        } catch (QueryException ex) {
            Exceptions.printStackTrace(ex);
        }

    }

    private void printNode(GermplasmPedigreeTreeNode node, int level) {
        StringBuilder tabs = new StringBuilder();

        for (int ctr = 1; ctr < level; ctr++) {
            tabs.append("\t");
        }

        String name = node.getGermplasm().getPreferredName() != null ? node.getGermplasm().getPreferredName().getNval() : null;
        System.out.println(tabs.toString() + node.getGermplasm().getGid() + " : " + name);

        for (GermplasmPedigreeTreeNode parent : node.getLinkedNodes()) {
            printNode(parent, level + 1);
        }
    }
    
    private void testFindGermplasm() {
        String germplasm = "UKA";//"IR99602-1B-1-1";
        try {
             List<Germplasm> list =manager.findGermplasmByName(germplasm, 0, 0, FindGermplasmByNameModes.NORMAL, Operation.EQUAL, null, null, Database.CENTRAL);
             for (Germplasm g : list) {
                 log.info(" " + g.getGid());
             }
        } catch (QueryException ex) {
            ex.printStackTrace();
        }
    }

    public static void main(String[] args) {
        TestMiddleware testMiddleware = new TestMiddleware();
        //testMiddleware.testGermplasmTree();
        ///testMiddleware.testgetAttributesByGID();
        //testMiddleware.testgetAllMethods();
        testMiddleware.testFindGermplasm();
    }
}
