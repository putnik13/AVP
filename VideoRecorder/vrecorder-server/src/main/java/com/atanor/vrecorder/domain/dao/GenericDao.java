package com.atanor.vrecorder.domain.dao;

import java.util.List;

import javax.persistence.EntityManager;

import com.atanor.vrecorder.domain.entity.AbstractEntity;

@SuppressWarnings("rawtypes")
public interface GenericDao<E extends AbstractEntity, P> {

	/**
	 * Persist the indicated entity to database
	 * 
	 * @param entity
	 * @return the primary key
	 */
	P insert(E entity);

	/**
	 * Retrieve an object using indicated ID
	 * 
	 * @param id
	 * @return
	 */
	E find(P id);

	/**
	 * Update indicated entity to database
	 * 
	 * @param entity
	 */
	void update(E entity);

	/**
	 * Delete indicated entity from database
	 * 
	 * @param entity
	 */
	void delete(E entity);

	/**
	 * Return the entity class
	 * 
	 * @return
	 */
	Class<E> getEntityClass();

	/**
	 * Get the entity manager
	 * 
	 * @return
	 */
	EntityManager getEntityManager();

	/**
	 * 
	 * @return
	 */
	List<E> findAll();

}
