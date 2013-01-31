/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ibfb.traits.core.palette;

import java.beans.IntrospectionException;
import java.util.ArrayList;
import java.util.List;
import org.cimmyt.cril.ibwb.api.AppServicesProxy;
import org.cimmyt.cril.ibwb.domain.Trait;
import org.cimmyt.cril.ibwb.domain.Traits;
import org.openide.nodes.Index;
import org.openide.nodes.Node;
import org.openide.util.Exceptions;

/**
 *
 * @author TMSANCHEZ
 */
public class TraitChildren extends Index.ArrayChildren {

    private TraitGroup traitGroup;

    public TraitChildren(TraitGroup traitGroup) {
        this.traitGroup = traitGroup;
    }

    @Override
    protected List<Node> initCollection() {

        List<Node> traits = new ArrayList<Node>();
        Traits traitFilter = new Traits(true);
        traitFilter.setTraitGroup(traitGroup.getTraitGroup());
        List<Traits> traitList = AppServicesProxy.getDefault().appServices().getListTraits(traitFilter, 0, 0, false);
        try {
            for (Traits trait : traitList) {
                TraitModel traitModel = new TraitModel(trait);
                TraitNode traitNode = new TraitNode(traitModel);
                traits.add(traitNode);
            }
        } catch (IntrospectionException ex) {
            Exceptions.printStackTrace(ex);
        }

        return traits;
    }
}
