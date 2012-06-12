package org.cimmyt.cril.ibwb.provider;

import java.awt.Component;
import java.awt.Cursor;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import org.cimmyt.cril.ibwb.api.AppServices;
import org.cimmyt.cril.ibwb.api.Services;
import org.openide.util.Mutex;
import org.openide.util.lookup.ServiceProvider;
import org.openide.windows.WindowManager;
import org.springframework.beans.factory.support.BeanDefinitionReader;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.support.GenericApplicationContext;
import org.springframework.core.io.ClassPathResource;

/**
 *
 * @author TMSANCHEZ
 */
@ServiceProvider(position = 10, service = Services.class)
public class ServicesImpl<T> implements Services {

    private static final boolean CONNECT_TO_DATABASE = true;
    private static final Logger logger = Logger.getLogger(ServicesImpl.class.getName());
    private static final String appContextFileName = "ibwApiApplicationContext.xml";
    private static final String appContextFileNameMemory = "ibwbApplicationContextMemory.xml";
    private static final String APP_SERVICES_BEAN = "ibwbAppServices";
    private AppServices appServices;
    private GenericApplicationContext context;
    
    private Loading loading = new Loading();
    
    public ServicesImpl() {

        
        changeCursorWaitStatus(true);
        
        //loading.showForm();

        try {


            context = new GenericApplicationContext();

            logger.info("Getting spring context...");
            if (CONNECT_TO_DATABASE) {
                loadXMLConfig(ServicesImpl.class, appContextFileName);
            } else {
                loadXMLConfig(ServicesImpl.class, appContextFileNameMemory);
            }
            logger.info("Getting spring context DONE!");

        } catch (IOException ex) {
            logger.log(Level.SEVERE, "Error getting spring context", ex);

        }
        updateClassLoader();

        if (CONNECT_TO_DATABASE) {
            context.getBean("configResource");
        }

        appServices = (AppServices) context.getBean(APP_SERVICES_BEAN);
        changeCursorWaitStatus(false);
        
        //loading.loadingFinished();

    }

    @Override
    public void reloadSpringContext() {
        context.refresh();
    }

    private void defaultApplicationContextLoad() {
        ApplicationContext appContext = new ClassPathXmlApplicationContext(
                appContextFileName, ServicesImpl.class);

        appServices = (AppServices) context.getBean(APP_SERVICES_BEAN);

    }

    public void loadXMLConfig(Class clazz, String resourceName) throws IOException {
        ClassPathResource resource = getClassPathResource(clazz, resourceName);
        BeanDefinitionReader reader = new XmlBeanDefinitionReader((BeanDefinitionRegistry) context);
        reader.loadBeanDefinitions(resource);
    }

    private ClassPathResource getClassPathResource(Class clazz, String resourceName) throws IOException {
        ClassPathResource resource = new ClassPathResource(resourceName, clazz);
        if (!resource.exists()) {
            throw new FileNotFoundException("The resource " + resourceName + " for class " + clazz.getName() + " does not exist.");
        }
        return resource;
    }

    public Object getBean(String beanName) {
        Object o = context.getBean(beanName);
        return o;
    }

    @SuppressWarnings("unchecked")
    public T getBean(String beanName, Class beanInterface) {
        Object o = context.getBean(beanName, beanInterface);
        return (T) o;
    }

    public String[] getBeanNames(Class beanInterface) {
        String[] names = context.getBeanNamesForType(beanInterface);
        return names;
    }

    public void updateClassLoader() {
        context.setClassLoader(Thread.currentThread().getContextClassLoader());
    }

    @Override
    public AppServices getAppServices() {
        return this.appServices;
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
}
