/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.cimmyt.cril.ibwb.api;


import org.openide.util.Lookup;

/**
 *
 * @author TMSANCHEZ
 */
public class AppServicesProxy {

    private static AppServicesProxy appServicesProxy;
    private AppServices appServices;
    private Services services;

    public static AppServicesProxy getDefault() {
        if (appServicesProxy == null) {
            appServicesProxy = new AppServicesProxy();
        }
        return appServicesProxy;
    }

    private AppServicesProxy() {
        services = Lookup.getDefault().lookup(Services.class);
    }

    public AppServices appServices() {
        return services.getAppServices();
    }
}
