package com.project.desafio.controller;

import com.project.desafio.dto.request.ProdutoPostRequest;
import com.project.desafio.dto.request.ProdutoPutRequest;
import com.project.desafio.dto.response.ProdutoPostResponse;
import com.project.desafio.dto.response.ProdutoPutResponse;
import com.project.desafio.service.ProdutoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/v1/produtos")
public class ProdutoController {

    @Autowired
    private ProdutoService produtoService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ProdutoPostResponse criarProduto(@RequestBody ProdutoPostRequest produto) {
        return produtoService.criarProduto(produto);
    }

    @PutMapping
    @ResponseStatus(HttpStatus.OK)
    public ProdutoPutResponse atualizarProduto(@Valid @RequestBody ProdutoPutRequest produto) {
        return produtoService.atualizarProduto(produto);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<ProdutoPutResponse> buscarTodos() {
        return produtoService.buscarTodos();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ProdutoPutResponse buscarPorId(@PathVariable Long id) {
        return produtoService.buscarPorId(id);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletarProduto(@PathVariable Long id) {
        produtoService.deletarProduto(id);
    }
}
