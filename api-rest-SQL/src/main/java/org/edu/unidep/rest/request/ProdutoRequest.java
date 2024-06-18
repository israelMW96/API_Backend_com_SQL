package org.edu.unidep.rest.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.smallrye.common.constraint.NotNull;
import org.edu.unidep.command.ProdutoCommand;

import java.time.LocalDate;

public record ProdutoRequest(

        @NotNull
        @JsonProperty("nome")
        String nome,
        @NotNull
        @JsonProperty("categoria")
        String categoria,
        @NotNull
        @JsonProperty("ean")
        String ean,
        @NotNull
        @JsonProperty("dataValidade")
        LocalDate dataValidade) {

    public static ProdutoCommand toComand(ProdutoRequest request) {
        return new ProdutoCommand(request.nome, request.categoria, request.ean, request.dataValidade);
    }

}
