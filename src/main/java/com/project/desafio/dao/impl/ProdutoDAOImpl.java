package com.project.desafio.dao.impl;

import com.project.desafio.dao.ProdutoDAO;
import com.project.desafio.entity.Produto;
import jakarta.persistence.EntityManager;

import java.util.List;

public class ProdutoDAOImpl implements ProdutoDAO {

    private final EntityManager entityManager;

    public ProdutoDAOImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public Produto salvar(Produto produto) {
        entityManager.persist(produto);
        return produto;
    }

    @Override
    public Produto atualizar(Produto produto) {
        return null;
    }

    @Override
    public void deletar(Long id) {

    }

    @Override
    public Produto buscarPorId(Long id) {
        return null;
    }

    @Override
    public List<Produto> buscarTodos() {
        return List.of();
    }


}
