package org.cimmyt.cril.ibwb.provider.helpers;

import java.util.ArrayList;
import java.util.List;
import org.cimmyt.cril.ibwb.api.AppServices;
import org.cimmyt.cril.ibwb.api.CommonServices;
import org.cimmyt.cril.ibwb.domain.*;
import org.cimmyt.cril.ibwb.domain.inventory.InventoryData;

/**
 *
 * @author TMSANCHEZ
 */
public class HelperInventory {

    private Listnms listnms;
    private Integer transDate;
    private List<InventoryData> inventoryDataList;
    private int userid;
    private CommonServices localServices;
    private AppServices appServices;

    public HelperInventory() {
    }

    public void setInventoryDataList(List<InventoryData> inventoryDataList) {
        this.inventoryDataList = inventoryDataList;
    }

    public void setListnms(Listnms listnms) {
        this.listnms = listnms;
    }

    public void setLocalServices(CommonServices localServices) {
        this.localServices = localServices;
    }

    public void setTransDate(Integer transDate) {
        this.transDate = transDate;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }

    public void saveInventoryFromList() {
        for (InventoryData inventoryData : inventoryDataList) {
            ImsLot imsLot = new ImsLot(true);
            imsLot.setUserid(userid);
            imsLot.setEtype(ImsLot.ETYPE_GERMPLASM);
            imsLot.setEid(inventoryData.getGid());
            imsLot.setLocid(inventoryData.getLocationid());
            imsLot.setScaleid(inventoryData.getScale());
            imsLot.setStatus(ImsLot.LOT_STATUS_ACTIVE);
            imsLot.setSourceid(ImsLot.DEFAULT_SOURCE);
            imsLot.setComments(inventoryData.getComment());


            // first add a Lot
            localServices.addImsLot(imsLot);

            ImsTransaction transaction = new ImsTransaction(true);
            transaction.setUserid(userid);
            transaction.setLotid(imsLot.getLotid());
            transaction.setTrndate(transDate);
            transaction.setTrnstat(ImsTransaction.TRAN_STATUS_CONFIRMED);
            transaction.setTrnqty(inventoryData.getAmmount());
            transaction.setComments(inventoryData.getComment());
            transaction.setCmtdata(0);
            transaction.setSourcetype(ImsTransaction.SOURCE_TYPE_LIST);
            transaction.setSourceid(listnms.getListid());
            transaction.setPrevamount(0d);
            transaction.setPersonid(userid);

            localServices.addImsTransaction(transaction);

        }
    }

    public AppServices getAppServices() {
        return appServices;
    }

    public void setAppServices(AppServices appServices) {
        this.appServices = appServices;
    }

    public List<Scales> getScalesForInventory() {

        List<Scales> inventoryScales = new ArrayList<Scales>();
        
        Traits seedStockTrait = new Traits(true);
        
        seedStockTrait.setTrname(InventoryData.TRAIT_SEED_STOCK_TRNAME);
        seedStockTrait.setTrabbr(InventoryData.TRAIT_SEED_STOCK_TRABBR);
        seedStockTrait.setTrdesc(InventoryData.TRAIT_SEED_STOCK_TRDESC);
        seedStockTrait.setTnstat(InventoryData.TRAIT_SEED_STOCK_TNSTAT);
        seedStockTrait.setTraitGroup(InventoryData.TRAIT_SEED_STOCK_TRAITGROUP);
        seedStockTrait.setOntology(InventoryData.TRAIT_SEED_STOCK_ONTOLOGY);
        seedStockTrait.setTraittype(InventoryData.TRAIT_SEED_STOCK_TRAITTYPE);
        
        List<Traits> traitList = appServices.getListTraits(seedStockTrait, 0, 0, false);
        
        // if seed stock trait does not exist, then add it to traits
//        if (traitList.isEmpty()) {
//            seedStockTrait.setTid(0);
//            appServices.addOrUpdateTmsTrait(seedStockTrait);
//        } else {
            // get it from database
        if (! traitList.isEmpty() ) {
            Traits seedStock = traitList.get(0);
            // then retrieve their measured in
            List<Measuredin> measuredinList = appServices.getMeasuredInListByTrait(seedStock.getTid());
            for (Measuredin measuredIn: measuredinList) {
                inventoryScales.add(measuredIn.getScales());
            }
        }

        return inventoryScales;
    }
}
