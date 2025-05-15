package com.project.desafio.dao;

import com.project.desafio.entity.Produto;

import java.util.List;

public interface ProdutoDAO {
    Produto salvar(Produto produto);
    Produto atualizar(Produto produto);
    void deletar(Long id);
    Produto buscarPorId(Long id);
    List<Produto> buscarTodos();
}
