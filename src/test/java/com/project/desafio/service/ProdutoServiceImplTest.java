package com.project.desafio.service;


import com.project.desafio.dao.GenericDAO;
import com.project.desafio.dao.factory.DAOFactory;
import com.project.desafio.dto.request.ProdutoPostRequest;
import com.project.desafio.dto.request.ProdutoPutRequest;
import com.project.desafio.entity.Produto;
import com.project.desafio.exceptions.RecursoNaoEncontradoException;
import com.project.desafio.service.impl.ProdutoServiceImpl;
import com.project.desafio.stub.ProdutoPostRequestStub;
import com.project.desafio.stub.ProdutoPutRequestStub;
import com.project.desafio.stub.ProdutoStub;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class ProdutoServiceImplTest {

    @Mock
    private DAOFactory daoFactory;

    @Mock
    private GenericDAO<Produto, Long> produtoDAO;

    @InjectMocks
    private ProdutoServiceImpl produtoService;

    @BeforeEach
    void setUp() {
        produtoDAO = mock(GenericDAO.class);
        daoFactory = mock(DAOFactory.class);
        when(daoFactory.createDAO(any())).thenAnswer(invocation -> produtoDAO);
        produtoService = new ProdutoServiceImpl(daoFactory);
    }

    @Test
    void deveCriarProdutoComSucesso() {
        var request = ProdutoPostRequestStub.obterProduto();
        var produto = ProdutoPostRequest.toEntity(request);
        produto.setId(1L);

        when(produtoDAO.salvar(any(Produto.class))).thenReturn(produto);

        var response = produtoService.criarProduto(request);

        assertThat(response).isNotNull();
        assertThat(response.getId()).isEqualTo(1L);
        assertThat(response.getNome()).isEqualTo("Celular Samsung");
        verify(produtoDAO).salvar(any(Produto.class));
    }

    @Test
    void deveAtualizarProdutoComSucesso() {
        var request = ProdutoPutRequestStub.obterProduto();
        var produtoExistente = ProdutoStub.obterProduto();
        var produtoAtualizado = ProdutoPutRequest.toUpdateEntity(request, produtoExistente);

        when(produtoDAO.buscarPorId(1L)).thenReturn(Optional.of(produtoExistente));
        when(produtoDAO.atualizar(any(Produto.class))).thenReturn(produtoAtualizado);

        var response = produtoService.atualizarProduto(request);

        assertThat(response).isNotNull();
        assertThat(response.getNome()).isEqualTo("Celular Galaxy");
        verify(produtoDAO).atualizar(any(Produto.class));
    }

    @Test
    void deveBuscarProdutoPorIdComSucesso() {
        var produto = ProdutoStub.obterProduto();

        when(produtoDAO.buscarPorId(1L)).thenReturn(Optional.of(produto));

        var response = produtoService.buscarPorId(1L);

        assertThat(response).isNotNull();
        assertThat(response.getId()).isEqualTo(1L);
        verify(produtoDAO).buscarPorId(1L);
    }

    @Test
    void deveLancarExcecao_ProdutoNaoEncontrado_aoBuscarPorId() {
        when(produtoDAO.buscarPorId(999L)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> produtoService.buscarPorId(999L))
                .isInstanceOf(RecursoNaoEncontradoException.class)
                .hasMessageContaining("Produto com ID 999 não encontrado.");
    }

    @Test
    void deveDeletarProdutoComSucesso() {
        var produto = ProdutoStub.obterProduto();
        when(produtoDAO.buscarPorId(1L)).thenReturn(Optional.of(produto));

        produtoService.deletarProduto(1L);

        verify(produtoDAO).deletar(produto);
    }

    @Test
    void deveListarTodosOsProdutos() {
        var produtos = ProdutoStub.obterListProdutos();

        when(produtoDAO.buscarTodos()).thenReturn(produtos);

        var responses = produtoService.buscarTodos();

        assertThat(responses).hasSize(2);
        verify(produtoDAO).buscarTodos();
    }
}
