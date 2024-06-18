package org.edu.unidep.repository;

import org.edu.unidep.command.PedidoCommand;
import org.edu.unidep.model.Pedido;

import java.time.LocalDate;
import java.util.List;

public interface PedidoRepository {

    public void inserir(PedidoCommand command);

    public List<Pedido> listarTodos();

    public List<Pedido> listarPelaData(LocalDate data);

    public List<Pedido> listarPeloCliente(String cliente);

    public void deletar(Long idPedido);

}
