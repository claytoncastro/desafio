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
    @Operation(
            summary = "Criar um novo produto",
            description =
                    "## Descrição\n" +
                            "Cria um `Produto`. É importante que todos os atributos do objeto `ProdutoPostRequest` sejam preenchidos (nome, " +
                            "descricao, preco e quantidadeEstoque). Caso contrário uma _Exception_ `MethodArgumentNotValidException` " +
                            "será disparada e exibirá um _JSON_ com os atributos que devem ser preenchidos. \n\n" +
                            "Exemplo do _JSON_:\n" +
                            "```json\n" +
                            "{\n" +
                            "  \"nome\": \"O nome é obrigatório\",\n" +
                            "  \"descricao\": \"A descrição é obrigatória\"\n" +
                            "}\n" +
                            "```\n" +
                            "### Informações\n" +
                            "- **POST** `/v1/produtos`\n" +
                            "- **Response**: `201 Created`\n" +
                            "- **Response**: `400 Bad Request`\n"

    )
    public ProdutoPostResponse criarProduto(@Valid @RequestBody ProdutoPostRequest produto) {
        return produtoService.criarProduto(produto);
    }

    @PutMapping
    @ResponseStatus(HttpStatus.OK)
    @Operation(
            summary = "Atualizar um produto existente",
            description =
                    "## Descrição\n" +
                            "Atualiza um `Produto` cadastrado. É muito importante que o ID do Produto seja informado " +
                            "pois a partir dele se dará a alteração do produto.\n" +
                            "Caso o produto não exista uma _Exception_ `RecursoNaoEncontradoException` será disparada " +
                            "e retornará a mensagem \"**Produto com ID [ID_PRODUTO_INFORMADO] não encontrado.**\".\n" +
                            "### Informações\n" +
                            "- **PUT** `/v1/produtos`\n" +
                            "- **Response**: `200 OK`\n" +
                            "- **Response**: `404 Not Found`\n"
    )
    public ProdutoPutResponse atualizarProduto(@Valid @RequestBody ProdutoPutRequest produto) {
        return produtoService.atualizarProduto(produto);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    @Operation(
            summary = "Listar todos os produtos",
            description =
                    "## Descrição\n" +
                            "Aqui pode-se obter uma lista de todo `Produto` cadastrado. Caso não tenha Produtos cadastrados, deverá " +
                            "retornar uma lista de Produto vazia representada por \"[ ]\".\n" +
                            "### Informações\n" +
                            "- **GET** `/v1/produtos`\n" +
                            "- **Response**: `200 OK`"
    )
    public List<ProdutoPutResponse> buscarTodos() {
        return produtoService.buscarTodos();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(
            summary = "Buscar produto por ID",
            description =
                    "## Descrição\n" +
                            "Aqui pode-se obter uma `Produto` cadastrado por ID." +
                            "Caso o produto não exista uma _Exception_ `RecursoNaoEncontradoException` será disparada " +
                            "e retornará a mensagem \"**Produto com ID [ID_PRODUTO_INFORMADO] não encontrado.**\".\n" +
                            "### Informações\n" +
                            "- **GET** `/v1/produtos/ID_PRODUTO`\n" +
                            "- **Response**: `200 OK`\n" +
                            "- **Response**: `404 Not Found`\n"
    )
    public ProdutoPutResponse buscarPorId(
            @Parameter(description = "ID do produto", example = "1") @PathVariable Long id) {
        return produtoService.buscarPorId(id);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(
            summary = "Deletar produto por ID",
            description =
                    "## Descrição\n" +
                            "Aqui pode-se deletar uma `Produto` cadastrado por ID." +
                            "Caso o produto não exista uma _Exception_ `RecursoNaoEncontradoException` será disparada " +
                            "e retornará a mensagem \"**Produto com ID [ID_PRODUTO_INFORMADO] não encontrado.**\".\n" +
                            "### Informações\n" +
                            "- **DELETE** `/v1/produtos/ID_PRODUTO`\n" +
                            "- **Response**: `204 No Content`\n" +
                            "- **Response**: `404 Not Found`\n"
    )
    public void deletarProduto(
            @Parameter(description = "ID do produto", example = "1") @PathVariable Long id) {
        produtoService.deletarProduto(id);
    }
}
