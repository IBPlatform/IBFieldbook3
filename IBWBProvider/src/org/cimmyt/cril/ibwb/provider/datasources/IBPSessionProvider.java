/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.cimmyt.cril.ibwb.provider.datasources;

import org.generationcp.middleware.hibernate.HibernateSessionProvider;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

/**
 *
 * @author TMSANCHEZ
 */
public class IBPSessionProvider implements HibernateSessionProvider {

    private SessionFactory sessionFactory;

    public IBPSessionProvider(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public Session getSession() {
        return sessionFactory.getCurrentSession();
    }

    @Override
    public void close() {
    }
}
