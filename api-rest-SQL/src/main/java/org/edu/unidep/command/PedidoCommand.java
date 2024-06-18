package org.edu.unidep.command;

import org.edu.unidep.model.Orcamento;

import java.time.LocalDate;

public record PedidoCommand(LocalDate dataEmissao, String cliente, Orcamento orcamento) {
}
