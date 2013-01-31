package ibfb.branding.core;

import java.util.Collection;
import java.util.Iterator;
import org.openide.LifecycleManager;
import org.openide.util.Lookup;


@SuppressWarnings("unchecked")
public class MyLifeCycleManager extends LifecycleManager {

    @Override
    public void saveAll() {
    }

    @Override
    public void exit() {

        Collection c = Lookup.getDefault().lookup(
                new Lookup.Template(LifecycleManager.class)).allInstances();

        for (Iterator i = c.iterator(); i.hasNext();) {
            LifecycleManager lm = (LifecycleManager) i.next();
            if (lm != this) {
                lm.exit();
            }
        }
    }
    


}
