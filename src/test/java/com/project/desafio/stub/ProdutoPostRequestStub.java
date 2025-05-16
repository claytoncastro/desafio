package com.project.desafio.stub;

import com.project.desafio.dto.request.ProdutoPostRequest;
import com.project.desafio.entity.Produto;

import java.math.BigDecimal;

public class ProdutoPostRequestStub {

    public static ProdutoPostRequest obterProduto() {
        return ProdutoPostRequest.builder()
                .nome("Celular Samsung")
                .descricao("Aparelho celular")
                .preco(new BigDecimal("1000.0"))
                .quantidadeEstoque(200)
                .build();
    }

}
