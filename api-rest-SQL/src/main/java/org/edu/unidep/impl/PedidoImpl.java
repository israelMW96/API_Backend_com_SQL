package org.edu.unidep.impl;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import org.edu.unidep.command.PedidoCommand;
import org.edu.unidep.model.Orcamento;
import org.edu.unidep.model.Pedido;
import org.edu.unidep.repository.PedidoRepository;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.List;

@ApplicationScoped
public class PedidoImpl implements PanacheRepository<Pedido>, PedidoRepository {

    @Transactional
    @Override
    public void inserir(PedidoCommand command) {
        Pedido pedido = new Pedido();
        pedido.setDataEmissao(command.dataEmissao());
        pedido.setCliente(command.cliente());
        pedido.setOrcamento(command.orcamento());

        persist(pedido);
    }

    @Override
    public List<Pedido> listarTodos() {
        return listAll();
    }

    @Override
    public List<Pedido> listarPelaData(LocalDate data) {
        String jpql = """
                SELECT p FROM Pedido p WHERE p.dataEmissao = :data
                """;

        TypedQuery<Pedido> query = getEntityManager().createQuery(jpql, Pedido.class).setParameter("data", data);
        return query.getResultList();
    }

    @Override
    public List<Pedido> listarPeloCliente(String cliente) {

        String jpql = """
                SELECT p FROM Pedido p WHERE p.cliente LIKE :cliente
                      """;

        TypedQuery<Pedido> query = getEntityManager().createQuery(jpql, Pedido.class).setParameter("cliente", "%" + cliente + "%");
        return query.getResultList();
    }

    @Transactional
    @Override
    public void deletar(Long idPedido) {
        deleteById(idPedido);
    }
}
