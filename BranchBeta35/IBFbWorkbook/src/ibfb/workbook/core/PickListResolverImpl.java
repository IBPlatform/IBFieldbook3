package ibfb.workbook.core;

import ibfb.domain.core.PickItem;
import ibfb.workbook.api.PickListResolver;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import org.cimmyt.cril.ibwb.api.AppServicesProxy;
import org.cimmyt.cril.ibwb.domain.Location;
import org.cimmyt.cril.ibwb.domain.Methods;
import org.cimmyt.cril.ibwb.domain.Persons;

/**
 *
 * @author TMSANCHEZ
 */
public class PickListResolverImpl implements PickListResolver {

    private HashMap<String, List<PickItem>> pickLists = new HashMap<String, List<PickItem>>();
    private static PickListResolverImpl pickListResolverImpl;

    private PickListResolverImpl() {
        initPickLists();
    }

    public static PickListResolver instance() {
        if (pickListResolverImpl == null) {
            pickListResolverImpl = new PickListResolverImpl();
        }
        return pickListResolverImpl;
    }

    @Override
    public List<PickItem> getPickList(String labelName) {
        String labelToSearch = labelName.trim().toUpperCase();
        List<PickItem> resultList = pickLists.get(labelToSearch);
        if (resultList == null) {
            resultList = new ArrayList<PickItem>();
        }
        return resultList;
    }

    @Override
    public String getValue(String labelName, String label) {
        String labelToSearch = labelName.trim().toUpperCase();
        String result = "";
        List<PickItem> resultList = pickLists.get(labelToSearch);
        if (resultList != null) {
            for (PickItem pickItem : resultList) {
                if (pickItem.getDescription().equals(label)) {
                    result = pickItem.getValue();
                    break;
                }
            }
        }

        return result;
    }

    @Override
    public String getLabel(String labelName, String value) {
        String labelToSearch = labelName.trim().toUpperCase();
        String result = "";
        List<PickItem> resultList = pickLists.get(labelToSearch);
        if (resultList != null) {
            for (PickItem pickItem : resultList) {
                if (pickItem.getValue().equals(value)) {
                    result = pickItem.getDescription();
                    break;
                }
            }
        }
        return result;
    }

    private void initPickLists() {
        List<PickItem> studyItems = new ArrayList<PickItem>();
        
        List<Location> locationList = AppServicesProxy.getDefault().appServices().getLocationList();                
        List<PickItem> siteItems = new ArrayList<PickItem>();
        for (Location location:locationList) {
            PickItem pickItem =  new PickItem();
            pickItem.setValue(location.getLocid().toString());
            pickItem.setDescription(location.getLname().toUpperCase());
            siteItems.add(pickItem);
            
        }
        
        List<PickItem> prinInvestigatorItems = new ArrayList<PickItem>();
        List<Persons> personsList = AppServicesProxy.getDefault().appServices().getPersonsList();
        for (Persons persons:personsList) {
            PickItem pickItem =  new PickItem();
            String value = persons.getPersonid().toString();
            pickItem.setValue(value);
            pickItem.setDescription(persons.getFullName().toUpperCase());
            prinInvestigatorItems.add(pickItem);
        }
        
        List<PickItem> prinCooperatorItems = new ArrayList<PickItem>();
        for (Persons persons:personsList) {
            PickItem pickItem =  new PickItem();
            String value = persons.getPersonid().toString();
            pickItem.setValue(value);
            pickItem.setDescription(persons.getFullName().toUpperCase());
            prinCooperatorItems.add(pickItem);
        }
        
        
        List<PickItem> methodItems = new ArrayList<PickItem>();
        List<Methods> methodsList = AppServicesProxy.getDefault().appServices().getMethodsList();
        for (Methods method:methodsList) {
            PickItem pickItem =  new PickItem();
            String value = method.getMid().toString();
            pickItem.setValue(value);
            pickItem.setDescription(method.getMname().toUpperCase());
            methodItems.add(pickItem);
        }
        
        
        

        pickLists.put(LABEL_STUDY, studyItems);
        pickLists.put(LABEL_SITE, siteItems);
        pickLists.put(LABEL_PRINCIPAL_INVESTIGATOR, prinInvestigatorItems);
        pickLists.put(LABEL_PRINCIPAL_COOPERATOR, prinCooperatorItems);
        pickLists.put(LABEL_METHOD,methodItems);
    }
}
