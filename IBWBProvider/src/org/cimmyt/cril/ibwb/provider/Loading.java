package org.cimmyt.cril.ibwb.provider;

import java.util.concurrent.ExecutionException;
import javax.swing.SwingWorker;
import org.netbeans.api.progress.ProgressHandle;
import org.netbeans.api.progress.ProgressHandleFactory;
import org.openide.util.Exceptions;

public class Loading {

    private JDAviso loadingDialog = new JDAviso(null, false);

    public void showForm() {



        loadingDialog.setLocationRelativeTo(null);
        loadingDialog.setVisible(true);




    }

    public void loadingFinished() {
        loadingDialog.setVisible(false);
    }

    public void showLoading() {

        final ProgressHandle handle = ProgressHandleFactory.createHandle("My task description (put to the left of the progress bar)");

        handle.start();

        (new SwingWorker<String, Object>() {

            @Override
            protected String doInBackground() throws Exception {

                showForm();
                return "HI";
            }

            @Override
            protected void done() {
                super.done();
                try {

                    String ca = get();
                    loadingFinished();


                } catch (InterruptedException ex) {
                    Exceptions.printStackTrace(ex);
                } catch (ExecutionException ex) {
                    Exceptions.printStackTrace(ex);
                } finally {

                    handle.finish();
                }
            }
        }).execute();
    }
}
