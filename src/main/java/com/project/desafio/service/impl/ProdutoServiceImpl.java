package com.project.desafio.service.impl;

import com.project.desafio.dao.GenericDAO;
import com.project.desafio.dao.factory.DAOFactory;
import com.project.desafio.dto.ProdutoDTO;
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
    public Produto criarProduto(ProdutoDTO dto) {
        Produto produto = new Produto();
        copiarDados(dto, produto);
        return produtoDAO.salvar(produto);
    }

    @Override
    @Transactional
    public Produto atualizarProduto(Long id, ProdutoDTO dto) {
        Produto produto = produtoDAO.buscarPorId(id).orElseThrow();
        copiarDados(dto, produto);
        return produtoDAO.atualizar(produto);
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

    private void copiarDados(ProdutoDTO dto, Produto produto) {
        produto.setNome(dto.getNome());
        produto.setDescricao(dto.getDescricao());
        produto.setPreco(dto.getPreco());
        produto.setQuantidadeEstoque(dto.getQuantidadeEstoque());
    }
}