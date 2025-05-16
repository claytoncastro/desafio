package com.project.desafio.controller;

import com.project.desafio.dto.request.ProdutoPostRequest;
import com.project.desafio.dto.request.ProdutoPutRequest;
import com.project.desafio.dto.response.ProdutoPostResponse;
import com.project.desafio.dto.response.ProdutoPutResponse;
import com.project.desafio.service.ProdutoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/produtos")
@Tag(name = "Produtos", description = "Gerenciamento de produtos")
public class ProdutoController {

    @Autowired
    private ProdutoService produtoService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Criar um novo produto")
    public ProdutoPostResponse criarProduto(@Valid @RequestBody ProdutoPostRequest produto) {
        return produtoService.criarProduto(produto);
    }

    @PutMapping
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Atualizar um produto existente")
    public ProdutoPutResponse atualizarProduto(@Valid @RequestBody ProdutoPutRequest produto) {
        return produtoService.atualizarProduto(produto);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Listar todos os produtos")
    public List<ProdutoPutResponse> buscarTodos() {
        return produtoService.buscarTodos();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Buscar produto por ID")
    public ProdutoPutResponse buscarPorId(
            @Parameter(description = "ID do produto") @PathVariable Long id) {
        return produtoService.buscarPorId(id);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(summary = "Deletar produto por ID")
    public void deletarProduto(
            @Parameter(description = "ID do produto") @PathVariable Long id) {
        produtoService.deletarProduto(id);
    }
}
