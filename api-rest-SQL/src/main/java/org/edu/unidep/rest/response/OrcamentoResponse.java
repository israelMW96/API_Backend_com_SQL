package org.edu.unidep.rest.response;

import org.edu.unidep.model.Orcamento;

import java.math.BigDecimal;
import java.time.LocalDate;

public record OrcamentoResponse(Long idOrcamento, LocalDate dataOrcamento, LocalDate dataVencimento, BigDecimal valor) {

    public static OrcamentoResponse toResponse(Orcamento orcamento) {
        return new OrcamentoResponse(orcamento.getIdOrcamento(), orcamento.getDataOrcamento(),
                orcamento.getDataVencimento(), orcamento.getValor());
    }
}
