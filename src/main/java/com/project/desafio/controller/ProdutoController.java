package com.project.desafio.controller;

import com.project.desafio.dto.request.ProdutoPostRequest;
import com.project.desafio.dto.request.ProdutoPutRequest;
import com.project.desafio.dto.response.ProdutoPostResponse;
import com.project.desafio.dto.response.ProdutoPutResponse;
import com.project.desafio.service.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/v1/produtos")
public class ProdutoController {

    @Autowired
    private ProdutoService produtoService;

    @PostMapping("/criar-produto")
    public ProdutoPostResponse criarProduto(@RequestBody ProdutoPostRequest produto) {
        return produtoService.criarProduto(produto);
    }

    @PutMapping("/atualizar-produto")
    public ProdutoPutResponse atualizarProduto(@RequestBody ProdutoPutRequest produto) {
        return produtoService.atualizarProduto(produto);
    }

    @GetMapping("/buscar-produtos")
    public List<ProdutoPutResponse> buscarTodos() {
        return produtoService.buscarTodos();
    }

    @GetMapping("/buscar-produtos/{id}")
    public ProdutoPutResponse buscarPorId(@PathVariable(name = "id") Long id) {
        return produtoService.buscarPorId(id);
    }
}
