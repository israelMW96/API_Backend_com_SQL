package org.edu.unidep.repository;

import org.edu.unidep.command.OrcamentoCommand;
import org.edu.unidep.model.Orcamento;

import java.time.LocalDate;
import java.util.List;

public interface OrcamentoRepository {

    public void inserir(OrcamentoCommand command);

    public List<Orcamento> listarTodos();

    public Orcamento listarPorCodigo(Long codigo);

    public List<Orcamento> listarPelaData(LocalDate data);

    public List<Orcamento> listarPorMaiorValor();

    public void deletar(Long idEstoque);

}
