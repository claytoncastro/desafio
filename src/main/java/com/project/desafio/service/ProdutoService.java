package com.project.desafio.service;

import com.project.desafio.dto.ProdutoDTO;
import com.project.desafio.entity.Produto;

import java.util.List;

public interface ProdutoService {
    Produto criarProduto(ProdutoDTO dto);
    Produto atualizarProduto(Long id, ProdutoDTO dto);
    void deletarProduto(Long id);
    Produto buscarPorId(Long id);
    List<Produto> buscarTodos();
}