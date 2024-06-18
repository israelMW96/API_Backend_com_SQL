package org.edu.unidep.rest.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.smallrye.common.constraint.NotNull;
import org.edu.unidep.command.PedidoCommand;
import org.edu.unidep.model.Orcamento;
import org.edu.unidep.model.Produto;

import java.math.BigDecimal;
import java.time.LocalDate;

public record PedidoRequest(
        @NotNull
        @JsonProperty("dataEmissao")
        LocalDate dataEmissao,
        @NotNull
        @JsonProperty("cliente")
        String cliente,
        @NotNull
        @JsonProperty("orcamento")
        Orcamento orcamento) {

    public static PedidoCommand toCommand(PedidoRequest request) {
        return new PedidoCommand(request.dataEmissao, request.cliente, request.orcamento);
    }
}


