package com.project.desafio.dao;

import java.util.List;
import java.util.Optional;

public interface GenericDAO<T, ID> {
    T salvar(T entidade);
    T atualizar(T entidade);
    void deletar(T entidade);
    Optional<T> buscarPorId(ID id);
    List<T> buscarTodos();
}
