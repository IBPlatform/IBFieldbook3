/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ibfb.traits.core.palette;

import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.IOException;
import org.cimmyt.cril.ibwb.domain.Trait;
import org.cimmyt.cril.ibwb.domain.Traits;

/**
 *
 * @author TMSANCHEZ
 */
public class TraitModel implements Transferable {
    private Traits trait;

    public static final DataFlavor DATA_FLAVOR = new DataFlavor(TraitModel.class, "trait");

    public TraitModel() {
    }

    public TraitModel(Traits trait) {
        this.trait = trait;
    }

    public Traits getTrait() {
        return trait;
    }

    public void setTrait(Traits trait) {
        this.trait = trait;
    }
    
    
    

    @Override
    public DataFlavor[] getTransferDataFlavors() {
        return new DataFlavor[]{DATA_FLAVOR};
    }

    @Override
    public boolean isDataFlavorSupported(DataFlavor flavor) {
        return flavor == DATA_FLAVOR;
    }

    @Override
    public Object getTransferData(DataFlavor flavor) throws UnsupportedFlavorException, IOException {
        if (flavor == DATA_FLAVOR) {
            return this;
        } else {
            throw new UnsupportedFlavorException(flavor);
        }
    }
}
