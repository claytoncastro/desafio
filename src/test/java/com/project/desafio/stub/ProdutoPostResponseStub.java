package com.project.desafio.stub;

import com.project.desafio.dto.response.ProdutoPostResponse;

import java.math.BigDecimal;

public class ProdutoPostResponseStub {

    public static ProdutoPostResponse obterProduto() {
        return ProdutoPostResponse.builder()
                .id(1L)
                .nome("Celular Samsung")
                .descricao("Aparelho celular")
                .preco(new BigDecimal("1000.0"))
                .quantidadeEstoque(200)
                .build();
    }

}
