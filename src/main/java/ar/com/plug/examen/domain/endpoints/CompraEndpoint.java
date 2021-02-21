package ar.com.plug.examen.domain.endpoints;

import ar.com.plug.examen.domain.dtos.CompraDTO;

import java.util.List;

public interface CompraEndpoint {

    String BASE = "/v1";
    String GET_ALL_COMPRAS = "/get-all-compras";
    String ADD_COMPRA = "/add-compra";
    String DELETE_COMPRA = "/delete-compra";

    public List<CompraDTO> getAll();
    public CompraDTO save(CompraDTO compra) throws Exception;
    public void delete(CompraDTO compra);
}
