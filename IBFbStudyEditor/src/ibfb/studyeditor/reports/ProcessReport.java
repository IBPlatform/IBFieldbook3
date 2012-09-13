package ibfb.studyeditor.reports;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ExecutionException;
import javax.swing.SwingWorker;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.util.JRLoader;
import org.netbeans.api.progress.ProgressHandle;
import org.netbeans.api.progress.ProgressHandleFactory;
import org.openide.modules.InstalledFileLocator;
import org.openide.util.Exceptions;

/**
 *
 * @author TMSANCHEZ
 */
public class ProcessReport {

    public static void printTraitsBarCodeReport(final List<TraitsReport> traits) {
        final ProgressHandle handle = ProgressHandleFactory.createHandle("Generating report... ");
        handle.start();

        (new SwingWorker<String, Object>() {

            InputStream streamReport = null;
            JRBeanCollectionDataSource datasource = new JRBeanCollectionDataSource(traits);
            InstalledFileLocator ifl = InstalledFileLocator.getDefault();
            File reportFile = ifl.locate("modules/reports/BarCodeReport.jasper", "ibfb.studyeditor.core", false);
            JasperPrint jasperPrint = null;

            @Override
            protected String doInBackground() throws Exception {
                streamReport = new FileInputStream(reportFile);
                JasperReport report = (JasperReport) JRLoader.loadObject(streamReport);
                report.setProperty("net.sf.jasperreports.components.barcode4j.image.producer", "image");
                jasperPrint = JasperFillManager.fillReport(report, new HashMap<String, Object>(), datasource);
                return "";
            }

            @Override
            protected void done() {
                super.done();
                try {
                    //            JRExporter exporter = new JRPdfExporter();
                    //            exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
                    //            exporter.setParameter(JRExporterParameter.OUTPUT_FILE, new java.io.File("c:/tmsanchez/temporal/reporte.pdf"));
                    //            exporter.exportReport();
                    String valor = get();
                    ReportViewerTopComponent reportViewerTopComponent = ReportViewerTopComponent.getDefault();
                    reportViewerTopComponent.setJasperPrint(jasperPrint);
                    reportViewerTopComponent.open();
                    reportViewerTopComponent.requestActive();
                } catch (InterruptedException ex) {
                    Exceptions.printStackTrace(ex);
                } catch (ExecutionException ex) {
                    Exceptions.printStackTrace(ex);
                } finally {
                    // close the progress handler
                    handle.finish();
                }
            }
        }).execute();

    }
}
