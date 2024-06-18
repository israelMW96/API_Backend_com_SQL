package org.edu.unidep.rest;

import org.eclipse.microprofile.openapi.annotations.parameters.RequestBody;
import org.edu.unidep.repository.OrcamentoRepository;
import org.edu.unidep.rest.request.OrcamentoRequest;
import org.edu.unidep.rest.response.OrcamentoResponse;

import javax.inject.Inject;
import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Path("/orcamento")
public class OrcamentoRest {

    private final OrcamentoRepository orcamentoRepository;


    @Inject
    public OrcamentoRest(OrcamentoRepository orcamentoRepository) {
        this.orcamentoRepository = orcamentoRepository;
    }

    @POST
    public void inserir(@Valid @RequestBody OrcamentoRequest request) {
        //ProdutoCommand produtoCommand = ProdutoRequest.toComand(request);
        orcamentoRepository.inserir(OrcamentoRequest.toCommand(request));
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response listarTodos() {
        List<OrcamentoResponse> responses = orcamentoRepository.listarTodos().stream().map(orcamento -> new OrcamentoResponse(
                orcamento.getIdOrcamento(), orcamento.getDataOrcamento(), orcamento.getDataVencimento(), orcamento.getValor()
        )).toList();

        return Response.ok(responses).build();
    }

    @GET
    @Path("/{codigo}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response listarPorCodigo(@PathParam("codigo") Long codigo) {
        OrcamentoResponse response = OrcamentoResponse.toResponse(orcamentoRepository.listarPorCodigo(codigo));
        return Response.ok(response).build();
    }

    @GET
    @Path("/data/{data}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response listarPelaData(@PathParam("data") String dataString) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate data = LocalDate.parse(dataString.trim(), formatter);
        List<OrcamentoResponse> responses = orcamentoRepository.listarPelaData(data).stream().map(orcamento -> new OrcamentoResponse(
                orcamento.getIdOrcamento(), orcamento.getDataOrcamento(), orcamento.getDataVencimento(), orcamento.getValor()
        )).toList();

        return Response.ok(responses).build();
    }

    @GET
    @Path("/maiorvalor")
    @Produces(MediaType.APPLICATION_JSON)
    public Response listarPorMaiorValor() {
        List<OrcamentoResponse> responses = orcamentoRepository.listarPorMaiorValor().stream().map(orcamento -> new OrcamentoResponse(
                orcamento.getIdOrcamento(), orcamento.getDataOrcamento(), orcamento.getDataVencimento(), orcamento.getValor()
        )).toList();

        return Response.ok(responses).build();
    }

    @DELETE
    @Path("/{idOrcamento}")
    public void deletar(@PathParam("idOrcamento") Long idOrcamento) {
        orcamentoRepository.deletar(idOrcamento);
    }
}
