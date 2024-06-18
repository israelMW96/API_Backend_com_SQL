package org.edu.unidep.rest.response;

import org.edu.unidep.model.Produto;

import java.time.LocalDate;

public record ProdutoResponse(Long idProduto, String nome, String categoria, String ean, LocalDate dataValidade) {

    public static ProdutoResponse toResponse(Produto produto) {
        return new ProdutoResponse(produto.getIdProduto(), produto.getNome(), produto.getCategoria(),
                produto.getEan(), produto.getDataValidade());
    }
}

