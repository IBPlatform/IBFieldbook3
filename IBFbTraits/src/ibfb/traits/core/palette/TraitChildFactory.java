/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ibfb.traits.core.palette;

import java.util.List;
import org.cimmyt.cril.ibwb.api.AppServicesProxy;
import org.cimmyt.cril.ibwb.domain.Trait;
import org.cimmyt.cril.ibwb.domain.Traits;
import org.openide.nodes.ChildFactory;
import org.openide.nodes.Node;

/**
 *
 * @author TMSANCHEZ
 */
public class TraitChildFactory extends ChildFactory<TraitModel>{

    private TraitGroup traitGroup;

    public TraitChildFactory(TraitGroup traitGroup) {
        this.traitGroup = traitGroup;
    }

    @Override
    protected boolean createKeys(List<TraitModel> list) {
    
       for (Traits trait : AppServicesProxy.getDefault().appServices().getTraitsList()) {
           TraitModel traitModel =  new TraitModel(trait);
           list.add(traitModel);
       } 
       return true;
    }

    @Override
    protected Node createNodeForKey(TraitModel key) {
        Node node = null;
        try {
            node = new TraitNode(key);
        } catch (Exception e) {

        }
        return node;
    }

    
}
