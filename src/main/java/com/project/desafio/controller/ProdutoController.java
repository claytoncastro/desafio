package com.project.desafio.controller;

import com.project.desafio.dto.ProdutoDTO;
import com.project.desafio.entity.Produto;
import com.project.desafio.service.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/produtos")
public class ProdutoController {

    @Autowired
    private ProdutoService produtoService;

    @PostMapping("/criar-produto")
    public Produto criarProduto(@RequestBody ProdutoDTO produto) {
        return produtoService.criarProduto(produto);
    }
}
