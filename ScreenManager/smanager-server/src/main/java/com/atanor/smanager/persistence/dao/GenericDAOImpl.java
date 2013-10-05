package com.atanor.smanager.persistence.dao;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;

import com.atanor.smanager.persistence.entity.AbstractEntity;

@SuppressWarnings("rawtypes")
public class GenericDAOImpl<E extends AbstractEntity, P> implements
		GenericDAO<E, P> {

	private EntityManager entityManager = Persistence
			.createEntityManagerFactory("SMANAGER").createEntityManager();
	private Class<E> entityClass;

	@SuppressWarnings("unchecked")
	public P insert(E entity) {
		entityManager.persist(entity);
		return (P) entity.getId();
	}

	public E find(P id) {
		return entityManager.find(getEntityClass(), id);
	}

	public void update(E entity) {
		entityManager.merge(entity);
	}

	public void delete(E entity) {
		entityManager.remove(entity);
	}

	public EntityManager getEntityManager() {
		return entityManager;
	}

	@SuppressWarnings("unchecked")
	public Class<E> getEntityClass() {
		if (entityClass == null) {
			Type type = getClass().getGenericSuperclass();
			if (type instanceof ParameterizedType) {
				ParameterizedType paramType = (ParameterizedType) type;

				entityClass = (Class<E>) paramType.getActualTypeArguments()[0];

			} else {
				throw new IllegalArgumentException(
						"Could not guess entity class by reflection");
			}
		}
		return entityClass;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<E> findAll() {
		return this.entityManager.createNamedQuery(
				getEntityClass().getSimpleName() + ".GetAll").getResultList();
	}

}
