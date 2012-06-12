package org.cimmyt.cril.ibwb.api.dao;

import java.io.Serializable;

/**
 * Generic interfaces for Data Access Objects
 * 
 * @author <a href="mailto:t.m.sanchez@cgiar.org">tmsanchez</a>
 * @author last edited by: $Author: tmsanchez $
 * 
 * @version $Revision$, $Date: 2010-08-11 17:38:20 -0500 (Wed, 11 Aug 2010) $
 */
public interface GenericDAO<T, PK extends Serializable>  {

	/** Persist the newInstance object into database */
	T create(T newInstance);

	/**
	 * Retrieve an object that was previously persisted to the database using
	 * the indicated id as primary key
	 */
	T read(PK id);

	/** Save changes made to a persistent object. */
	void update(T transientObject);

	/** Remove an object from persistent storage in the database */
	void delete(T persistentObject);
        
        /**
         * Add or update an object into database
         * @param transientObject 
         */
        T addOrUpdate(T transientObject);
}
