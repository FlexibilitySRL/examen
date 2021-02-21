package ar.com.plug.examen.domain.endpoints;

import ar.com.plug.examen.domain.dtos.VendedorDTO;

import java.util.List;

public interface VendedorEndpoint {
    String BASE = "/v1";
    String GET_ALL_VENDEDORES = "/get-all-vendedores";
    String ADD_VENDEDOR = "/add-vendedor";
    String DELETE_VENDEDOR = "/delete-vendedor";

    public List<VendedorDTO> getAll();
    public VendedorDTO save(VendedorDTO vendedor);
    public void delete(VendedorDTO vendedor);
}

