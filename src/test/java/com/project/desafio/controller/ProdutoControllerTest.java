package com.project.desafio.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.project.desafio.exceptions.RecursoNaoEncontradoException;
import com.project.desafio.service.ProdutoService;
import com.project.desafio.stub.ProdutoPostRequestStub;
import com.project.desafio.stub.ProdutoPostResponseStub;
import com.project.desafio.stub.ProdutoPutRequestStub;
import com.project.desafio.stub.ProdutoPutResponseStub;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ProdutoController.class)
class ProdutoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private ProdutoService produtoService;

    @Test
    void deveCriarProdutoERetornar201() throws Exception {
        var request = ProdutoPostRequestStub.obterProduto();
        var response = ProdutoPostResponseStub.obterProduto();

        when(produtoService.criarProduto(Mockito.any())).thenReturn(response);

        mockMvc.perform(post("/v1/produtos")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(response.getId()))
                .andExpect(jsonPath("$.nome").value(response.getNome()))
                .andExpect(jsonPath("$.descricao").value(response.getDescricao()))
                .andExpect(jsonPath("$.preco").value(response.getPreco()))
                .andExpect(jsonPath("$.quantidadeEstoque").value(response.getQuantidadeEstoque()));
    }

    @Test
    void deveAtualizarProdutoERetornar200() throws Exception {
        var request = ProdutoPutRequestStub.obterProduto();
        var response = ProdutoPutResponseStub.obterProduto();

        when(produtoService.atualizarProduto(Mockito.any()))
                .thenReturn(response);

        mockMvc.perform(put("/v1/produtos")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(response.getId()))
                .andExpect(jsonPath("$.nome").value(response.getNome()))
                .andExpect(jsonPath("$.descricao").value(response.getDescricao()))
                .andExpect(jsonPath("$.preco").value(response.getPreco()))
                .andExpect(jsonPath("$.quantidadeEstoque").value(response.getQuantidadeEstoque()));
    }

    @Test
    void deveListarTodosOsProdutos() throws Exception {
        var produtos = ProdutoPutResponseStub.obterListProdutos();
        when(produtoService.buscarTodos()).thenReturn(produtos);
        mockMvc.perform(get("/v1/produtos"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2));
    }

    @Test
    void deveBuscarProdutoPorId() throws Exception {
        var produto = ProdutoPutResponseStub.obterProduto();
        when(produtoService.buscarPorId(1L)).thenReturn(produto);
        mockMvc.perform(get("/v1/produtos/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1L));
    }

    @Test
    void deveDeletarProdutoPorId() throws Exception {
        Mockito.doNothing().when(produtoService).deletarProduto(1L);

        mockMvc.perform(delete("/v1/produtos/1"))
                .andExpect(status().isNoContent());
    }

    @Test
    void deveRetornar404QuandoRecursoNaoEncontrado() throws Exception {
        when(produtoService.buscarPorId(1L))
                .thenThrow(new RecursoNaoEncontradoException("Recurso não encontrado"));

        mockMvc.perform(get("/v1/produtos/1"))
                .andExpect(status().isNotFound())
                .andExpect(content().string("Recurso não encontrado"));
    }


    @Test
    void deveRetornar400QuandoValidacaoFalha() throws Exception {
        String jsonInvalido = "{}";
        mockMvc.perform(post("/v1/produtos")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonInvalido))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.nome").value("O nome é obrigatório"));
    }
}