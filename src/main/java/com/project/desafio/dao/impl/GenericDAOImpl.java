package com.project.desafio.dao.impl;

import com.project.desafio.dao.GenericDAO;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;

import java.util.List;
import java.util.Optional;

public class GenericDAOImpl<T, ID> implements GenericDAO<T, ID> {

    private final EntityManager entityManager;
    private final Class<T> entidadeClass;

    public GenericDAOImpl(EntityManager entityManager, Class<T> entidadeClass) {
        this.entityManager = entityManager;
        this.entidadeClass = entidadeClass;
    }


    @Override
    public T salvar(T entidade) {
        entityManager.persist(entidade);
        return entidade;
    }

    @Override
    public T atualizar(T entidade) {
        return entityManager.merge(entidade);
    }

    @Override
    public void deletar(ID id) {
        T entidade = entityManager.find(entidadeClass, id);
        if (entidade != null) {
            entityManager.remove(entidade);
        }
    }

    @Override
    public Optional<T> buscarPorId(ID id) {
        return Optional.ofNullable(entityManager.find(entidadeClass, id));
    }

    @Override
    public List<T> buscarTodos() {
        TypedQuery<T> query = entityManager.createQuery("FROM " + entidadeClass.getSimpleName(), entidadeClass);
        return query.getResultList();
    }
}
