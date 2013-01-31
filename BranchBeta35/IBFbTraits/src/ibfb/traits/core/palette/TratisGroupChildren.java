/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ibfb.traits.core.palette;

import org.openide.nodes.Children;
import org.openide.nodes.Node;

/**
 *
 * @author TMSANCHEZ
 */
public class TratisGroupChildren extends Children.Keys<TraitGroup> {

    private TraitGroup[] traitGroups = new TraitGroup[]{new TraitGroup("Flowering"), new TraitGroup("Disease")};

    @Override
    protected Node[] createNodes(TraitGroup key) {
        return new Node[]{new TraitGroupNode(key)};
    }

    @Override
    protected void addNotify() {
        super.addNotify();
        TraitGroup[] groups = new TraitGroup[traitGroups.length];
        for (int i = 0; i < groups.length; i++) {
            TraitGroup traitGroup = new TraitGroup();
            traitGroup.setTraitGroup(traitGroups[i].getTraitGroup());
            groups[i] = traitGroup;
        }
        setKeys(groups);
    }
    
}
