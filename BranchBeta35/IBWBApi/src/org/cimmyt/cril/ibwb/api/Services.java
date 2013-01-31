/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.cimmyt.cril.ibwb.api;

/**
 *
 * @author TMSANCHEZ
 */
public interface Services {
    /**
     * Gets the AppService interface to manage all logic defined
     * in AppService interface
     * @return an instance from this interface
     */
     public AppServices getAppServices();
     
     /**
      * Reloads the Spring application context, for example when
      * database parameters are changed
      */
     public void reloadSpringContext();
}
