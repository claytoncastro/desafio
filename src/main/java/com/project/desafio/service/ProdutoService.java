package com.project.desafio.service;

import com.project.desafio.dto.request.ProdutoPostRequest;
import com.project.desafio.dto.response.ProdutoPostResponse;
import com.project.desafio.entity.Produto;

import java.util.List;

public interface ProdutoService {
    ProdutoPostResponse criarProduto(ProdutoPostRequest dto);
    Produto atualizarProduto(Long id, ProdutoPostRequest dto);
    void deletarProduto(Long id);
    Produto buscarPorId(Long id);
    List<Produto> buscarTodos();
}