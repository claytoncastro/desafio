package com.project.desafio.stub;

import com.project.desafio.dto.request.ProdutoPutRequest;

import java.math.BigDecimal;

public class ProdutoPutRequestStub {

    public static ProdutoPutRequest obterProduto() {
        return ProdutoPutRequest.builder()
                .id(1L)
                .nome("Celular Galaxy")
                .descricao("Galaxy S24 Ultra")
                .preco(new BigDecimal("2500.0"))
                .quantidadeEstoque(50)
                .build();
    }

}
