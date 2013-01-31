package ibfb.studyexplorer.explorer;

import ibfb.domain.core.SelectedExperiment;
import ibfb.domain.core.SelectedStudy;
import ibfb.domain.core.Study;
import ibfb.studyexplorer.actions.ConfigStudiesAction;
import ibfb.studyexplorer.actions.NewTrialAction;
import ibfb.studyexplorer.core.nodes.MainStudyRootNode;
import ibfb.studyexplorer.core.nodes.StudyChildren;
import ibfb.studyexplorer.jdialogs.JDNewOptions;
import ibfb.studyexplorerutil.core.StudyExplorerListener;
import ibfb.ui.core.JDExpert;
import java.awt.Component;
import java.awt.Cursor;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.swing.JFrame;
import org.cimmyt.cril.ibwb.api.AppServicesProxy;
import org.cimmyt.cril.ibwb.commongui.ConvertUtils;
import org.netbeans.api.settings.ConvertAsProperties;
import org.openide.awt.ActionID;
import org.openide.awt.ActionReference;
import org.openide.explorer.ExplorerManager;
import org.openide.explorer.ExplorerUtils;
import org.openide.explorer.view.BeanTreeView;
import org.openide.nodes.Node;
import org.openide.util.*;
import org.openide.windows.TopComponent;
import org.openide.windows.WindowManager;

@ConvertAsProperties(dtd = "-//ibfb.studyexplorer.explorer//StudyExplorer//EN",
autostore = false)
@TopComponent.Description(preferredID = "StudyExplorerTopComponent",
//iconBase="SET/PATH/TO/ICON/HERE", 
persistenceType = TopComponent.PERSISTENCE_NEVER)
@TopComponent.Registration(mode = "explorer", openAtStartup = true, position = 20000)
@ActionID(category = "Window", id = "ibfb.studyexplorer.explorer.StudyExplorerTopComponent")
@ActionReference(path = "Menu/Window" /*
 * , position = 333
 */)
@TopComponent.OpenActionRegistration(displayName = "#CTL_StudyExplorerAction",
preferredID = "StudyExplorerTopComponent")
public final class StudyExplorerTopComponent extends TopComponent implements ExplorerManager.Provider, LookupListener, StudyExplorerListener {

    private ExplorerManager explorerManager = new ExplorerManager();
    private Study actualStudy;
    private List<Study> studyList = new ArrayList<Study>();
    private org.openide.util.Lookup.Result<Study> result;
    private List<Study> selectedStudyList;

    public List<Study> getSelectedStudyList() {
        return selectedStudyList;
    }

    public void setSelectedStudyList(List<Study> selectedStudyList) {
        this.selectedStudyList = selectedStudyList;
    }

    public StudyExplorerTopComponent() {
        initComponents();
        setName(NbBundle.getMessage(StudyExplorerTopComponent.class, "CTL_StudyExplorerTopComponent"));
        setToolTipText(NbBundle.getMessage(StudyExplorerTopComponent.class, "HINT_StudyExplorerTopComponent"));
        putClientProperty(TopComponent.PROP_CLOSING_DISABLED, Boolean.TRUE);
        putClientProperty(TopComponent.PROP_UNDOCKING_DISABLED, Boolean.TRUE);
        //loadStudiesFromDB();  
        startWithEmptyStudyClear();
        //explorerManager.setRootContext(new StudyRootNode(Children.create(new StudyChildFactory(studyList), true)));
        explorerManager.setRootContext(new MainStudyRootNode(new StudyChildren(studyList)));
        associateLookup(ExplorerUtils.createLookup(explorerManager, getActionMap()));
    }

    public List<Study> getStudyList() {
        return studyList;
    }

    public void setStudyList(List<Study> studyList) {
        this.studyList = studyList;
    }

    private void startWithEmptyStudyClear() {
        studyList.clear();
    }

    public void removeSelectedNode() {
        studyList.remove(getSelectedNode());
        explorerManager.setRootContext(new MainStudyRootNode(new StudyChildren(studyList)));

    }

    private void loadStudiesFromDB() {
        changeCursorWaitStatus(true);
        String selected = NbPreferences.forModule(ConfigStudiesAction.class).get("SELECTED", "");
        System.out.println("Tam SELECTED in LOAD STUDIES: " + selected.length());

        studyList.clear();

        List<org.cimmyt.cril.ibwb.domain.Study> studyDtoList =
                AppServicesProxy.getDefault().appServices().getStudyListByParent(0, Study.S_TYPE_EXPERIMENT);
        //AppServicesProxy.getDefault().appServices().getStudyList();
        int i = 0;

        for (org.cimmyt.cril.ibwb.domain.Study studyDto : studyDtoList) {

            if (selected.isEmpty()) {
                studyList.add(castStudy(studyDto));
            } else {
                if (i < selected.length() && selected.charAt(i) == '1') {
                    studyList.add(castStudy(studyDto));
                }

                i++;

            }

        }

        changeCursorWaitStatus(false);
    }

    @Override
    public void refreshStudyBrowser() {
        changeCursorWaitStatus(true);
        loadStudiesFromDB();
        explorerManager.setRootContext(new MainStudyRootNode(new StudyChildren(studyList)));
        changeCursorWaitStatus(false);
    }

    @Override
    public void refreshSelectedStudyBrowser() {
        changeCursorWaitStatus(true);
        studyList = this.getSelectedStudyList();
        explorerManager.setRootContext(new MainStudyRootNode(new StudyChildren(studyList)));
        changeCursorWaitStatus(false);

    }

    public void refreshStudyBrowserOnClose() {
        changeCursorWaitStatus(true);

        changeCursorWaitStatus(false);
    }

    public Study castStudy(org.cimmyt.cril.ibwb.domain.Study studyDto) {
        Study study = new Study();
        study.setUserid(studyDto.getUserid());
        study.setStudyid(studyDto.getStudyid());
        study.setStudy(studyDto.getSname());
        study.setTitle(studyDto.getTitle());
        study.setObjective(studyDto.getObjectiv());
        study.setEndDate(ConvertUtils.getIntegerAsDate(studyDto.getEdate()));
        study.setStarDate(ConvertUtils.getIntegerAsDate(studyDto.getEdate()));
        study.setPmkey(studyDto.getPmkey().toString());
        study.setShierarchy(studyDto.getShierarchy());
        study.setStudyType(studyDto.getStype());
        return study;
    }

    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        scrllStudyTree = new BeanTreeView();

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(scrllStudyTree, javax.swing.GroupLayout.DEFAULT_SIZE, 218, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(scrllStudyTree, javax.swing.GroupLayout.DEFAULT_SIZE, 300, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane scrllStudyTree;
    // End of variables declaration//GEN-END:variables

    @Override
    public void componentOpened() {
        result = org.openide.util.Utilities.actionsGlobalContext().lookupResult(Study.class);
        result.addLookupListener(this);
    }

    @Override
    public void componentClosed() {
        result.removeLookupListener(this);
    }

    void writeProperties(java.util.Properties p) {
        // better to version settings since initial version as advocated at
        // http://wiki.apidesign.org/wiki/PropertyFiles
        p.setProperty("version", "1.0");
        // TODO store your settings
    }

    void readProperties(java.util.Properties p) {
        String version = p.getProperty("version");

    }

    @Override
    public ExplorerManager getExplorerManager() {
        return this.explorerManager;
    }

    @Override
    public void resultChanged(LookupEvent ev) {
        Collection<? extends Study> allStudies = result.allInstances();
        for (Study study : allStudies) {
            actualStudy = study;
           // System.out.println("seleccionado: " + study.getStudy());
            setProperties(study);
        }

    }

    private void setProperties(Study study) {
        JDExpert.studyOBJ.setStudy(study.getStudy());
        JDExpert.studyOBJ.setTitle(study.getTitle());
        JDExpert.studyOBJ.setObjective(study.getObjective());
        JDExpert.studyOBJ.setStarDate(study.getStarDate());
        JDExpert.studyOBJ.setEndDate(study.getEndDate());
        JDExpert.studyOBJ.setPmkey(study.getPmkey());
        JDExpert.studyOBJ.setShierarchy(study.getShierarchy());
        JDExpert.studyOBJ.setStudyType(study.getStudyType());
        JDExpert.studyOBJ.setUserid(study.getUserid());
        JDExpert.studyOBJ.setStudyid(study.getStudyid());

        JDNewOptions.studyOBJ.setStudy(study.getStudy());
        JDNewOptions.studyOBJ.setTitle(study.getTitle());
        JDNewOptions.studyOBJ.setObjective(study.getObjective());
        JDNewOptions.studyOBJ.setStarDate(study.getStarDate());
        JDNewOptions.studyOBJ.setEndDate(study.getEndDate());
        JDNewOptions.studyOBJ.setPmkey(study.getPmkey());
        JDNewOptions.studyOBJ.setShierarchy(study.getShierarchy());
        JDNewOptions.studyOBJ.setStudyType(study.getStudyType());
        JDNewOptions.studyOBJ.setUserid(study.getUserid());
        JDNewOptions.studyOBJ.setStudyid(study.getStudyid());

        NewTrialAction.studyOBJ.setStudy(study.getStudy());
        NewTrialAction.studyOBJ.setTitle(study.getTitle());
        NewTrialAction.studyOBJ.setObjective(study.getObjective());
        NewTrialAction.studyOBJ.setStarDate(study.getStarDate());
        NewTrialAction.studyOBJ.setEndDate(study.getEndDate());
        NewTrialAction.studyOBJ.setPmkey(study.getPmkey());
        NewTrialAction.studyOBJ.setShierarchy(study.getShierarchy());
        NewTrialAction.studyOBJ.setStudyType(study.getStudyType());
        NewTrialAction.studyOBJ.setUserid(study.getUserid());
        NewTrialAction.studyOBJ.setStudyid(study.getStudyid());

//Objeto a reemplazar por todo lo de arriba


        SelectedStudy.selected.setStudy(study.getStudy());
        SelectedStudy.selected.setTitle(study.getTitle());
        SelectedStudy.selected.setObjective(study.getObjective());
        SelectedStudy.selected.setStarDate(study.getStarDate());
        SelectedStudy.selected.setEndDate(study.getEndDate());
        SelectedStudy.selected.setPmkey(study.getPmkey());
        SelectedStudy.selected.setShierarchy(study.getShierarchy());
        SelectedStudy.selected.setStudyType(study.getStudyType());
        SelectedStudy.selected.setUserid(study.getUserid());
        SelectedStudy.selected.setStudyid(study.getStudyid());

        SelectedExperiment.selected.setType(study.getStudyType());
        SelectedExperiment.selected.setDescription(study.getStudy());

    }

    private static void changeCursorWaitStatus(final boolean isWaiting) {
        Mutex.EVENT.writeAccess(new Runnable() {

            @Override
            public void run() {
                try {
                    JFrame mainFrame =
                            (JFrame) WindowManager.getDefault().getMainWindow();
                    Component glassPane = mainFrame.getGlassPane();
                    if (isWaiting) {
                        glassPane.setVisible(true);

                        glassPane.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
                    } else {
                        glassPane.setVisible(false);

                        glassPane.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
                    }
                } catch (Exception e) {
                }
            }
        });
    }

    /**
     * Return current node
     */
    public Object getSelectedNode() {
        Node[] selectedNodes = this.explorerManager.getSelectedNodes();
        if (selectedNodes.length == 0) {
            return null;
        } else {
            Node firstNode = selectedNodes[0];
            return firstNode;
        }

    }

    public void deleteNode() {

        Node[] selectedNodes = this.explorerManager.getSelectedNodes();

        if (selectedNodes.length == 0) {
        } else {
            Node firstNode = selectedNodes[0];
            try {
                firstNode.destroy();
            } catch (IOException ex) {
                Exceptions.printStackTrace(ex);
            }

        }



    }
}
