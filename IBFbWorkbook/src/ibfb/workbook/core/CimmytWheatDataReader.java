package ibfb.workbook.core;

import ibfb.domain.core.Factor;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.cimmyt.cril.ibwb.api.AppServicesProxy;
import org.cimmyt.cril.ibwb.domain.util.WheatData;

/**
 *
 * @author TMSANCHEZ
 */
public class CimmytWheatDataReader {

    private static String CROSS_NAME = "CROSSNAMENAME";
    private static String SELECTION_HISTORY = "SELECTIONHISTORYNAME";
    private static String GID = "GERMPLASMIDDBID";
    private Map<String, Integer> wheatColums;
    private Map<Integer, WheatData> wheatDataMap;
    private Integer listId;
    private List<List<Object>> data;
    private List<Factor> factorList;

    public CimmytWheatDataReader(Integer listId, List<List<Object>> data, List<Factor> factorList) {
        this.listId = listId;
        this.data = data;
        this.factorList = factorList;
        initWheatColumns();
        initWheatData();
    }

    public void fillCimmytWheatData() {
        for (int rowIndex = 0; rowIndex < data.size(); rowIndex++) {
            List<Object> columnValues = data.get(rowIndex);
            // look for GID column index
            Integer gidColumnIndex = wheatColums.get(GID);
            Integer crossNameColumnIndex = wheatColums.get(CROSS_NAME);
            Integer selectionHistory = wheatColums.get(SELECTION_HISTORY);
            if (gidColumnIndex != null) {
                Integer gid = (Integer) columnValues.get(gidColumnIndex);
                WheatData wheatData = wheatDataMap.get(gid);
                if (wheatData != null) {
                    columnValues.set(crossNameColumnIndex, wheatData.getCrossName());
                    columnValues.set(selectionHistory, wheatData.getSelectionHistory());
                }
            }
        }
    }

    private void initWheatColumns() {
        wheatColums = new HashMap<String, Integer>();
        int columnIndex = 0;
        for (Factor factor : factorList) {
            String searchCriteria = factor.getProperty() + factor.getScale();
            searchCriteria = GermplasmAssigmentToolImpl.getStringWithOutBlanks(searchCriteria);
            if (searchCriteria.toUpperCase().equals(CROSS_NAME)) {
                wheatColums.put(CROSS_NAME, columnIndex);
            } else if (searchCriteria.toUpperCase().equals(SELECTION_HISTORY)) {
                wheatColums.put(SELECTION_HISTORY, columnIndex);
            } else if (searchCriteria.toUpperCase().equals(GID)) {
                wheatColums.put(GID, columnIndex);
            }
            columnIndex++;
        }

    }

    private void initWheatData() {
        wheatDataMap = new HashMap<Integer, WheatData>();
        
        AppServicesProxy.getDefault().appServices().getFullListnms(listId);
        
        List<WheatData> wheatDataList = AppServicesProxy.getDefault().appServices().getDataForCimmytWheat(listId);
        for (WheatData wheatData : wheatDataList) {
            wheatDataMap.put(wheatData.getGid(), wheatData);
        }
    }
}
