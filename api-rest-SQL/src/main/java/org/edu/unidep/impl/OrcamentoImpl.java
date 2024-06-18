package org.edu.unidep.impl;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import org.edu.unidep.command.OrcamentoCommand;
import org.edu.unidep.model.Orcamento;
import org.edu.unidep.repository.OrcamentoRepository;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.List;

@ApplicationScoped
public class OrcamentoImpl implements PanacheRepository<Orcamento>, OrcamentoRepository {

    @Transactional
    @Override
    public void inserir(OrcamentoCommand command) {
        Orcamento orcamento = new Orcamento();
        orcamento.setDataOrcamento(command.dataOrcamento());
        orcamento.setDataVencimento(command.dataVencimento());
        orcamento.setValor(command.valor());

        persist(orcamento);
    }

    @Override
    public List<Orcamento> listarTodos() {
        return listAll();
    }

    @Override
    public Orcamento listarPorCodigo(Long codigo) {
        return findById(codigo);
    }

    @Override
    public List<Orcamento> listarPelaData(LocalDate data) {

        String jpql = """
                SELECT o FROM Orcamento o WHERE o.dataOrcamento = :data
                """;

        TypedQuery<Orcamento> query = getEntityManager().createQuery(jpql, Orcamento.class).setParameter("data", data);
        return query.getResultList();
    }

    @Override
    public List<Orcamento> listarPorMaiorValor() {

        String jpql = """
                SELECT o FROM Orcamento o ORDER BY o.valor DESC
                """;
        TypedQuery<Orcamento> query = getEntityManager().createQuery(jpql, Orcamento.class);
        return query.getResultList();
    }

    @Transactional
    @Override
    public void deletar(Long idEstoque) {
        deleteById(idEstoque);
    }
}
