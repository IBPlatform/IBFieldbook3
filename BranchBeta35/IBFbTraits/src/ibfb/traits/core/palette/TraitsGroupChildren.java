/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ibfb.traits.core.palette;

import java.util.List;
import org.cimmyt.cril.ibwb.api.AppServicesProxy;
import org.openide.nodes.Children;
import org.openide.nodes.Node;

/**
 *
 * @author TMSANCHEZ
 */
public class TraitsGroupChildren  extends Children.Keys<TraitGroup> {

    //private TraitGroup[] traitGroups = new TraitGroup[]{new TraitGroup("Flowering"), new TraitGroup("Disease")};

    @Override
    protected Node[] createNodes(TraitGroup key) {
        return new Node[]{new TraitGroupNode(key)};
    }

    @Override
    protected void addNotify() {
        super.addNotify();
        List<String> traitGroupsList = AppServicesProxy.getDefault().appServices().getTraitGroups();
        TraitGroup[] traitGroups = new TraitGroup[traitGroupsList.size()];
        
        int index = 0;
        for (String groupName : traitGroupsList) {
            traitGroups[index] =  new TraitGroup(groupName);
            index++;
        }
        
        TraitGroup[] groups = new TraitGroup[traitGroups.length];
        for (int i = 0; i < groups.length; i++) {
            TraitGroup traitGroup = new TraitGroup();
            traitGroup.setTraitGroup(traitGroups[i].getTraitGroup());
            groups[i] = traitGroup;
        }
        setKeys(groups);
    }
    
}
