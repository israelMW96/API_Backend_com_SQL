package org.edu.unidep.rest.response;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.edu.unidep.model.Pedido;
import org.edu.unidep.model.Orcamento;

import java.time.LocalDate;

@JsonSerialize
public record PedidoResponse(Long idPedido, LocalDate dataEmissao, String cliente, Orcamento orcamento) {

    public static PedidoResponse toResponse(Pedido pedido) {
        return new PedidoResponse(pedido.getIdPedido(), pedido.getDataEmissao(), pedido.getCliente(), pedido.getOrcamento());
    }
}
