package com.project.desafio.stub;

import com.project.desafio.entity.Produto;

import java.math.BigDecimal;
import java.util.List;

public class ProdutoStub {

    public static Produto obterProduto() {
        return Produto.builder()
                .id(1L)
                .nome("Produto Teste")
                .descricao("Descrição do Produto Teste")
                .preco(new BigDecimal("10.00"))
                .quantidadeEstoque(100)
                .build();
    }

    public static List<Produto> obterListProdutos() {
        return List.of(
                new Produto(1L, "Produto 1", "Desc 1", BigDecimal.TEN, 1),
                new Produto(2L, "Produto 2", "Desc 2", BigDecimal.valueOf(20), 2)
        );
    }
}
