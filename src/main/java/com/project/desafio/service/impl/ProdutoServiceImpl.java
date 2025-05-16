package com.project.desafio.service.impl;

import com.project.desafio.dao.GenericDAO;
import com.project.desafio.dao.factory.DAOFactory;
import com.project.desafio.dto.request.ProdutoPostRequest;
import com.project.desafio.dto.request.ProdutoPutRequest;
import com.project.desafio.dto.response.ProdutoPostResponse;
import com.project.desafio.dto.response.ProdutoPutResponse;
import com.project.desafio.entity.Produto;
import com.project.desafio.exceptions.RecursoNaoEncontradoException;
import com.project.desafio.service.ProdutoService;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class ProdutoServiceImpl implements ProdutoService {

    private final GenericDAO<Produto, Long> produtoDAO;

    public ProdutoServiceImpl(DAOFactory daoFactory) {
        this.produtoDAO = daoFactory.createDAO(Produto.class);
    }

    @Override
    @Transactional
    public ProdutoPostResponse criarProduto(ProdutoPostRequest dto) {
        log.info("Criando novo Produto. . .");
        var produto = produtoDAO.salvar(ProdutoPostRequest.toEntity(dto));
        return ProdutoPostResponse.toDomain(produto);
    }

    @Override
    @Transactional
    public ProdutoPutResponse atualizarProduto(ProdutoPutRequest dto) {
        log.info("Atualizando Produto. . .");
        var produto = buscarProdutoPorId(dto.getId());
        var produtoToUpdate = ProdutoPutRequest.toUpdateEntity(dto, produto);

        return ProdutoPutResponse.toDomain(produtoDAO.atualizar(produtoToUpdate));
    }

    @Override
    public ProdutoPutResponse buscarPorId(Long id) {
        log.info("Buscando Produto pelo ID {}. . .", id);
        return ProdutoPutResponse.toDomain(buscarProdutoPorId(id));
    }

    @Override
    @Transactional
    public void deletarProduto(Long id) {
        log.info("Deletando Produto pelo ID {}. . .", id);
        var produto = buscarProdutoPorId(id);
        produtoDAO.deletar(produto);
    }


    @Override
    public List<ProdutoPutResponse> buscarTodos() {
        log.info("Buscando todos Produtos. . .");
        return produtoDAO.buscarTodos()
                .stream()
                .map(ProdutoPutResponse::toDomain)
                .toList();
    }

    private Produto buscarProdutoPorId(Long id) {
        return produtoDAO.buscarPorId(id)
                .orElseThrow(
                        () -> new RecursoNaoEncontradoException(
                                "Produto com ID " + id + " não encontrado.")
                );
    }
}