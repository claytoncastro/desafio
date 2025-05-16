package com.project.desafio.dto.request;

import com.project.desafio.entity.Produto;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;

@Getter
@Builder
public class ProdutoPutRequest {

    @NotNull(message = "O campo ID não pode ser nulo")
    private Long id;
    @NotBlank(message = "O nome é obrigatório")
    private String nome;
    @NotBlank(message = "A descrição é obrigatória")
    private String descricao;
    @NotNull(message = "O preço é obrigatório")
    @Positive(message = "O preço deve ser maior que zero")
    private BigDecimal preco;
    @NotNull(message = "A quantidade em estoque é obrigatória")
    private Integer quantidadeEstoque;

    public static Produto toEntity(ProdutoPutRequest produto) {
        return Produto.builder()
                .nome(produto.getNome())
                .descricao(produto.getDescricao())
                .preco(produto.getPreco())
                .quantidadeEstoque(produto.getQuantidadeEstoque())
                .build();
    }


    public static Produto toUpdateEntity(ProdutoPutRequest dto, Produto produto) {
        produto.setNome(dto.getNome());
        produto.setDescricao(dto.getDescricao());
        produto.setPreco(dto.getPreco());
        produto.setQuantidadeEstoque(dto.getQuantidadeEstoque());

        return produto;
    }

}
