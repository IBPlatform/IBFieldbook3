/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ibfb.traits.core.palette;

import java.beans.IntrospectionException;
import org.openide.nodes.BeanNode;
import org.openide.nodes.Children;
import org.openide.util.lookup.Lookups;

/**
 *
 * @author TMSANCHEZ
 */
@SuppressWarnings("unchecked")
public class TraitNode extends BeanNode {

    private TraitModel traitModel;

    public TraitNode(TraitModel traitModel) throws IntrospectionException {
        super(traitModel, Children.LEAF, Lookups.singleton(traitModel));
        StringBuilder displayName = new StringBuilder(traitModel.getTrait().getTrname().trim());
        displayName.append("  ");
        String scale = "NO SCALE";

        if (traitModel.getTrait().getMeasuredin() != null && traitModel.getTrait().getMeasuredin().getStandardscale() != null) {
            scale = traitModel.getTrait().getMeasuredin().getStandardscale();
        }
        //displayName.append("(").append(traitModel.getTrait().getScale().getScname() ).append(")");
        displayName.append("(").append(scale).append(")");

        setDisplayName(displayName.toString());
        this.traitModel = traitModel;
    }
}
