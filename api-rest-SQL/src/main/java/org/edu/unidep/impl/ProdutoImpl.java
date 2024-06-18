package org.edu.unidep.impl;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import org.edu.unidep.command.ProdutoCommand;
import org.edu.unidep.repository.ProdutoRepository;
import org.edu.unidep.model.Produto;

import javax.enterprise.context.ApplicationScoped;
import javax.transaction.Transactional;
import javax.ws.rs.core.Response;
import java.math.BigDecimal;
import java.util.List;

@ApplicationScoped
public class ProdutoImpl implements PanacheRepository<Produto>, ProdutoRepository {

    @Transactional
    @Override
    public void inserir(ProdutoCommand command) {
        Produto produto = new Produto();
        produto.setNome(command.nome());
        produto.setCategoria(command.categoria());
        produto.setEan(command.ean());
        produto.setDataValidade(command.dataValidade());

        persist(produto);
    }

    @Override
    public List<Produto> listarTodos() {
        return listAll();
    }

    @Override
    public Produto listarPorCodigo(Long codigo) {
        return findById(codigo);
    }

    @Transactional
    @Override
    public void deletar(Long codigo) {
        deleteById(codigo);
    }
}