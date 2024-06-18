package org.edu.unidep.rest;

import org.eclipse.microprofile.openapi.annotations.parameters.RequestBody;
import org.edu.unidep.repository.PedidoRepository;
import org.edu.unidep.rest.request.PedidoRequest;
import org.edu.unidep.rest.response.PedidoResponse;

import javax.inject.Inject;
import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Path("/pedido")
public class PedidoRest {

    private final PedidoRepository pedidoRepository;


    @Inject
    public PedidoRest(PedidoRepository pedidoRepository) {
        this.pedidoRepository = pedidoRepository;
    }

    @POST
    public void inserir(@Valid @RequestBody PedidoRequest request) {
        pedidoRepository.inserir(PedidoRequest.toCommand(request));
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response listarTodos() {
        List<PedidoResponse> responses = pedidoRepository.listarTodos().stream()
                .map(pedido -> new PedidoResponse(pedido.getIdPedido(), pedido.getDataEmissao(), pedido.getCliente(),
                        pedido.getOrcamento())).toList();

        return Response.ok(responses).build();
    }

    @GET
    @Path("/data/{data}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response listarPelaData(@PathParam("data") String dataString) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate data = LocalDate.parse(dataString.trim(), formatter);
        List<PedidoResponse> responses = pedidoRepository.listarPelaData(data).stream()
                .map(pedido -> new PedidoResponse(pedido.getIdPedido(), pedido.getDataEmissao(), pedido.getCliente(),
                        pedido.getOrcamento())).toList();

        return Response.ok(responses).build();
    }

    @GET
    @Path("/cliente/{cliente}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response listarPeloCliente(@PathParam("cliente") String cliente) {
        List<PedidoResponse> responses = pedidoRepository.listarPeloCliente(cliente).stream()
                .map(pedido -> new PedidoResponse(pedido.getIdPedido(), pedido.getDataEmissao(), pedido.getCliente(),
                        pedido.getOrcamento())).toList();

        return Response.ok(responses).build();
    }

    @DELETE
    @Path("/{idPedido}")
    public void deletar(@PathParam("idVenda") Long idPedido) {
        pedidoRepository.deletar(idPedido);
    }
}
