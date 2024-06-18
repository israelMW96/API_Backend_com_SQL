package org.edu.unidep.repository;

import org.edu.unidep.command.ProdutoCommand;
import org.edu.unidep.model.Produto;

import java.util.List;

public interface ProdutoRepository {

    public void inserir(ProdutoCommand command);

    public List<Produto> listarTodos();

    public Produto listarPorCodigo(Long codigo);

    public void deletar(Long codigo);
}