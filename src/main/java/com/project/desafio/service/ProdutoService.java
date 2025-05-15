package com.project.desafio.service;

import com.project.desafio.dao.ProdutoDAO;
import com.project.desafio.dao.factory.DAOFactory;

public class ProdutoService {

    private final ProdutoDAO produtoDAO;

    public ProdutoService() {
        this.produtoDAO = DAOFactory.criarProdutoDAO();
    }


}
