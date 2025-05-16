package com.project.desafio.service;

import com.project.desafio.dto.request.ProdutoPostRequest;
import com.project.desafio.dto.request.ProdutoPutRequest;
import com.project.desafio.dto.response.ProdutoPostResponse;
import com.project.desafio.dto.response.ProdutoPutResponse;
import com.project.desafio.entity.Produto;

import java.util.List;

public interface ProdutoService {
    ProdutoPostResponse criarProduto(ProdutoPostRequest dto);
    ProdutoPutResponse atualizarProduto(ProdutoPutRequest dto);
    void deletarProduto(Long id);
    ProdutoPutResponse buscarPorId(Long id);
    List<ProdutoPutResponse> buscarTodos();
}