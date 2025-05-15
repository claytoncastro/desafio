package com.project.desafio.dao.factory;

import com.project.desafio.dao.ProdutoDAO;
import com.project.desafio.dao.impl.ProdutoDAOImpl;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class DAOFactory {
    private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("default");

    public static ProdutoDAO criarProdutoDAO() {
        return new ProdutoDAOImpl(emf.createEntityManager());
    }
}

