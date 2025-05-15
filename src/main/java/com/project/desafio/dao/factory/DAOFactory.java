package com.project.desafio.dao.factory;

import com.project.desafio.dao.GenericDAO;
import com.project.desafio.dao.impl.GenericDAOImpl;
import jakarta.persistence.EntityManager;
import org.springframework.stereotype.Component;

@Component
public class DAOFactory {
    private final EntityManager entityManager;

    public DAOFactory(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public <T, ID> GenericDAO<T, ID> createDAO(Class<T> entityClass) {
        return new GenericDAOImpl<>(entityManager, entityClass);
    }
}

