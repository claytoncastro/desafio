package com.project.desafio.dto.request;

import com.project.desafio.entity.Produto;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;

@Getter
@Builder
public class ProdutoPostRequest {

    @Schema(description = "Nome do produto", example = "Celular Samsung")
    @NotBlank(message = "O nome é obrigatório")
    private String nome;

    @Schema(description = "Descrição do produto", example = "Aparelho celular")
    @NotBlank(message = "A descrição é obrigatória")
    private String descricao;

    @Schema(description = "Preço do produto", example = "1000.00")
    @NotNull(message = "O preço é obrigatório")
    @Positive(message = "O preço deve ser maior que zero")
    private BigDecimal preco;

    @Schema(description = "Quantidade em estoque", example = "200")
    @NotNull(message = "A quantidade em estoque é obrigatória")
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
