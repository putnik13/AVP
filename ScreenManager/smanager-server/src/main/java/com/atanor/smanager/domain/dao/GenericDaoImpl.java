package com.atanor.smanager.domain.dao;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Provider;
import javax.persistence.EntityManager;

import com.atanor.smanager.domain.entity.AbstractEntity;
import com.google.inject.persist.Transactional;

@SuppressWarnings("rawtypes")
public abstract class GenericDaoImpl<E extends AbstractEntity, P> implements
		GenericDao<E, P> {

	@Inject 
	private Provider<EntityManager> emProvider;

	@SuppressWarnings("unchecked")
	@Transactional
	public P insert(E entity) {
		emProvider.get().persist(entity);
		return (P) entity.getId();
	}

	@Transactional
	public E find(P id) {
		return emProvider.get().find(getEntityClass(), id);
	}

	@Transactional
	public void update(E entity) {
		emProvider.get().merge(entity);
	}

	@Transactional
	public void delete(E entity) {
		emProvider.get().remove(entity);
	}

	public EntityManager getEntityManager() {
		return emProvider.get();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<E> findAll() {
		return emProvider.get().createNamedQuery(
				getEntityClass().getSimpleName() + ".GetAll").getResultList();
	}
	
	@Override
	public abstract Class<E> getEntityClass();
}
