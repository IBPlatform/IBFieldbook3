/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ibfb.traits.core.palette;

import org.openide.nodes.AbstractNode;
import org.openide.util.lookup.Lookups;

/**
 *
 * @author TMSANCHEZ
 */
public class TraitGroupNode extends AbstractNode {

    public TraitGroupNode(TraitGroup traitGroup) {
        super(new TraitChildren(traitGroup),Lookups.singleton(traitGroup));
        setDisplayName(traitGroup.getTraitGroup());
    }
    
}
