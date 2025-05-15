package com.project.desafio.service.impl;

import com.project.desafio.dao.GenericDAO;
import com.project.desafio.dao.factory.DAOFactory;
import com.project.desafio.dto.request.ProdutoPostRequest;
import com.project.desafio.dto.response.ProdutoPostResponse;
import com.project.desafio.entity.Produto;
import com.project.desafio.service.ProdutoService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProdutoServiceImpl implements ProdutoService {

    private final GenericDAO<Produto, Long> produtoDAO;

    public ProdutoServiceImpl(DAOFactory daoFactory) {
        this.produtoDAO = daoFactory.createDAO(Produto.class);
    }

    @Override
    @Transactional
    public ProdutoPostResponse criarProduto(ProdutoPostRequest dto) {
        var produto = produtoDAO.salvar(ProdutoPostRequest.toEntity(dto));
        return ProdutoPostResponse.toDomain(produto);
    }

    @Override
    @Transactional
    public Produto atualizarProduto(Long id, ProdutoPostRequest dto) {
        Produto produto = produtoDAO.buscarPorId(id).orElseThrow();
        var produtoToUpdate = ProdutoPostRequest.toUpdateEntity(dto, produto);

        return produtoDAO.atualizar(produtoToUpdate);
    }

    @Override
    @Transactional
    public void deletarProduto(Long id) {
        produtoDAO.deletar(id);
    }

    @Override
    public Produto buscarPorId(Long id) {
        return produtoDAO.buscarPorId(id).orElseThrow();
    }

    @Override
    public List<Produto> buscarTodos() {
        return produtoDAO.buscarTodos();
    }
}