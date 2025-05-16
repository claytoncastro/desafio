package com.project.desafio.stub;

import com.project.desafio.dto.response.ProdutoPutResponse;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

public class ProdutoPutResponseStub {

    public static ProdutoPutResponse obterProduto() {
        return ProdutoPutResponse.builder()
                .id(1L)
                .nome("Celular Galaxy")
                .descricao("Galaxy S24 Ultra")
                .preco(new BigDecimal("2500.0"))
                .quantidadeEstoque(50)
                .build();
    }

    public static List<ProdutoPutResponse> obterListProdutos() {
        return Arrays.asList(
                ProdutoPutResponse.builder()
                        .id(1L)
                        .nome("Produto 1")
                        .descricao("Descrição 1")
                        .preco(new BigDecimal("10.00"))
                        .quantidadeEstoque(100)
                        .build(),
                ProdutoPutResponse.builder()
                        .id(2L)
                        .nome("Produto 2")
                        .descricao("Descrição 2")
                        .preco(new BigDecimal("20.00"))
                        .quantidadeEstoque(200)
                        .build()
        );
    }

}
