package com.project.desafio.dto.request;

import com.project.desafio.entity.Produto;
import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;

@Getter
@Builder
public class ProdutoPostRequest {
    private String nome;
    private String descricao;
    private BigDecimal preco;
    private Integer quantidadeEstoque;

    public static Produto toEntity(ProdutoPostRequest produto) {
        return Produto.builder()
                .nome(produto.getNome())
                .descricao(produto.getDescricao())
                .preco(produto.getPreco())
                .quantidadeEstoque(produto.getQuantidadeEstoque())
                .build();
    }


    public static Produto toUpdateEntity(ProdutoPostRequest dto, Produto produto) {
        produto.setNome(dto.getNome());
        produto.setDescricao(dto.getDescricao());
        produto.setPreco(dto.getPreco());
        produto.setQuantidadeEstoque(dto.getQuantidadeEstoque());

        return produto;
    }

}
