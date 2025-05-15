package com.project.desafio.dao;

import java.util.List;
import java.util.Optional;

public interface GenericDAO<T, ID> {
    T salvar(T entidade);
    T atualizar(T entidade);
    void deletar(ID id);
    Optional<T> buscarPorId(ID id);
    List<T> buscarTodos();
}
