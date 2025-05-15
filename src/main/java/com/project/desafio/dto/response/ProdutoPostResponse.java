package com.project.desafio.dto.response;

import com.project.desafio.entity.Produto;
import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;

@Getter
@Builder
public class ProdutoPostResponse {
    private Long id;
    private String nome;
    private String descricao;
    private BigDecimal preco;
    private Integer quantidadeEstoque;

    public static ProdutoPostResponse toDomain(Produto produto) {
        return ProdutoPostResponse.builder()
                .id(produto.getId())
                .nome(produto.getNome())
                .descricao(produto.getDescricao())
                .preco(produto.getPreco())
                .quantidadeEstoque(produto.getQuantidadeEstoque())
                .build();
    }
}
