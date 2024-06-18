package org.edu.unidep.command;

import java.math.BigDecimal;
import java.time.LocalDate;

public record OrcamentoCommand(LocalDate dataOrcamento, LocalDate dataVencimento, BigDecimal valor) {
}
