package org.edu.unidep.rest.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.smallrye.common.constraint.NotNull;
import org.edu.unidep.command.OrcamentoCommand;
import org.edu.unidep.model.Produto;

import java.math.BigDecimal;
import java.time.LocalDate;

public record OrcamentoRequest(

        @NotNull
        @JsonProperty("dataOrcamento")
        LocalDate dataOrcamento,
        @NotNull
        @JsonProperty("dataVencimento")
        LocalDate dataVencimento,
        @NotNull
        @JsonProperty("valor")
        BigDecimal valor) {

    public static OrcamentoCommand toCommand(OrcamentoRequest request) {
        return new OrcamentoCommand(request.dataOrcamento, request.dataVencimento, request.valor);
    }
}
