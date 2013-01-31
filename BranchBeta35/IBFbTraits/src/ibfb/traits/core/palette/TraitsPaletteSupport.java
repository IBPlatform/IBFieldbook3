package ibfb.traits.core.palette;

import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.IOException;
import javax.swing.Action;
import org.netbeans.spi.palette.DragAndDropHandler;
import org.netbeans.spi.palette.PaletteActions;
import org.netbeans.spi.palette.PaletteController;
import org.netbeans.spi.palette.PaletteFactory;
import org.openide.nodes.AbstractNode;
import org.openide.nodes.Node;
import org.openide.util.Lookup;
import org.openide.util.datatransfer.ExTransferable;

/**
 *
 * @author TMSANCHEZ
 */
public class TraitsPaletteSupport {
    
    public static PaletteController createPalette() {
        AbstractNode paletteRoot = new AbstractNode(new TraitsGroupChildren());
        paletteRoot.setName("Traits Palette");
        paletteRoot.setDisplayName("Traits palette");
       
        return PaletteFactory.createPalette(paletteRoot, new MyActions(), null, new TraitDragAndDropHandler());
    }

    private static class MyActions extends PaletteActions {

        @Override
        public Action[] getImportActions() {
            return null;
        }

        @Override
        public Action[] getCustomPaletteActions() {
            return null;
        }

        @Override
        public Action[] getCustomCategoryActions(Lookup lookup) {
            return null;
        }

        @Override
        public Action[] getCustomItemActions(Lookup lookup) {
            return null;
        }

        @Override
        public Action getPreferredAction(Lookup lookup) {
            return null;
        }
    }

    private static class TraitDragAndDropHandler extends DragAndDropHandler {

        @Override
        public void customize(ExTransferable exTransferable, Lookup lookup) {
            Node node = lookup.lookup(Node.class);

            final TraitModel traitModel = node.getLookup().lookup(TraitModel.class);
            exTransferable.put(new ExTransferable.Single(TraitModel.DATA_FLAVOR) {

                @Override
                protected Object getData() throws IOException, UnsupportedFlavorException {
                    // return traitNode.gets;
                    //return trait.getTrname();
                    return traitModel;
                }
            });
        }
    }
}
