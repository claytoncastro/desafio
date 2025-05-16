package com.project.desafio.dao;

import com.project.desafio.dao.impl.GenericDAOImpl;
import com.project.desafio.entity.Produto;
import com.project.desafio.stub.ProdutoStub;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class GenericDAOImplTest {

    @Mock
    private EntityManager entityManager;

    @Mock
    private TypedQuery<Produto> typedQuery;

    private GenericDAOImpl<Produto, Long> genericDAO;

    private Produto produto;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        genericDAO = new GenericDAOImpl<>(entityManager, Produto.class);
        produto = ProdutoStub.obterProduto();
    }

    @Test
    void testSalvar() {
        Produto resultado = genericDAO.salvar(produto);
        verify(entityManager).persist(produto);
        assertEquals(produto, resultado);
    }

    @Test
    void testAtualizar() {
        when(entityManager.merge(produto)).thenReturn(produto);
        Produto resultado = genericDAO.atualizar(produto);
        verify(entityManager).merge(produto);
        assertEquals(produto, resultado);
    }

    @Test
    void testDeletar() {
        genericDAO.deletar(produto);
        verify(entityManager).remove(produto);
    }

    @Test
    void testDeletarNulo() {
        genericDAO.deletar(null);
        verify(entityManager, never()).remove(any());
    }

    @Test
    void testBuscarPorId() {
        when(entityManager.find(Produto.class, 1L)).thenReturn(produto);
        Optional<Produto> resultado = genericDAO.buscarPorId(1L);
        assertTrue(resultado.isPresent());
        assertEquals(produto, resultado.get());
    }

    @Test
    void testBuscarPorIdNaoEncontrado() {
        when(entityManager.find(Produto.class, 2L)).thenReturn(null);
        Optional<Produto> resultado = genericDAO.buscarPorId(2L);
        assertFalse(resultado.isPresent());
    }

    @Test
    void testBuscarTodos() {
        when(entityManager.createQuery("FROM Produto", Produto.class)).thenReturn(typedQuery);
        when(typedQuery.getResultList()).thenReturn(Collections.singletonList(produto));

        List<Produto> resultado = genericDAO.buscarTodos();
        assertEquals(1, resultado.size());
        assertEquals(produto, resultado.getFirst());
    }
}
