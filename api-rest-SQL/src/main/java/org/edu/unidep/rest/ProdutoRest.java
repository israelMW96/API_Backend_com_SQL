package org.edu.unidep.rest;

import org.eclipse.microprofile.openapi.annotations.parameters.RequestBody;
import org.edu.unidep.repository.ProdutoRepository;
import org.edu.unidep.model.Produto;
import org.edu.unidep.rest.request.ProdutoRequest;
import org.edu.unidep.rest.response.ProdutoResponse;
import org.edu.unidep.service.ProdutoService;

import javax.inject.Inject;
import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/produto")
public class ProdutoRest {

    private final ProdutoRepository produtoRepository;
    private final ProdutoService produtoService;

    @Inject
    public ProdutoRest(ProdutoRepository produtoRepository, ProdutoService produtoService) {
        this.produtoRepository = produtoRepository;
        this.produtoService = produtoService;
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response buscarTodas() {
        List<ProdutoResponse> responses = produtoRepository.listarTodos().stream()
                .map(produto -> new ProdutoResponse(produto.getIdProduto(), produto.getNome(),
                        produto.getCategoria(), produto.getEan(), produto.getDataValidade())).toList();

        return Response.ok(responses).build();

    }

    @GET
    @Path("/{codigo}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response buscarPorCodigo(@PathParam("codigo") Long codigo) {
        ProdutoResponse response = ProdutoResponse.toResponse(produtoRepository.listarPorCodigo(codigo));
        return Response.ok(response).build();
    }

    @POST
    public void inserir(@Valid @RequestBody ProdutoRequest request) {
        //ProdutoCommand produtoCommand = ProdutoRequest.toComand(request);
        produtoRepository.inserir(ProdutoRequest.toComand(request));
    }

    @PUT
    @Path("/{codigo}")
    public Response alterar(@PathParam("codigo") Long codigo, @Valid @RequestBody Produto produto) {
        Produto produtoAlterado = produtoService.atualizar(codigo, produto);
        return Response.ok(produtoAlterado).build();
    }

    @DELETE
    @Path("/{codigo}")
    public void deletar(@PathParam("codigo") Long codigo) {
        produtoRepository.deletar(codigo);
    }

}