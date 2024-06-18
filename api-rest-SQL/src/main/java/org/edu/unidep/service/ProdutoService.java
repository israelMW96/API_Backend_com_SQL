package org.edu.unidep.service;

import org.edu.unidep.model.Produto;
import org.edu.unidep.repository.ProdutoRepository;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;

@ApplicationScoped
public class ProdutoService {

    private final ProdutoRepository produtoRepository;

    @Inject
    public ProdutoService(ProdutoRepository produtoRepository) {
        this.produtoRepository = produtoRepository;
    }

    @Transactional
    public Produto atualizar(Long codigo, Produto produto) {
        Produto produtoEncontrado = produtoRepository.listarPorCodigo(codigo);
        produtoEncontrado.setNome(produto.getNome());
        produtoEncontrado.setCategoria(produto.getCategoria());
        produtoEncontrado.setEan(produto.getEan());
        produtoEncontrado.setDataValidade(produto.getDataValidade());

        return produtoEncontrado;
    }
}