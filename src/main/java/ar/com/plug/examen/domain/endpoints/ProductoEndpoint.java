package ar.com.plug.examen.domain.endpoints;

import ar.com.plug.examen.domain.dtos.ProductoDTO;

import java.util.List;

public interface ProductoEndpoint {
    String BASE = "/v1";
    String GET_ALL_PRODUCTOS = "/get-all-productos";
    String ADD_PRODUCTO = "/add-producto";
    String DELETE_PRODUCTO = "/delete-producto";

    public List<ProductoDTO> getAll();
    public ProductoDTO save(ProductoDTO producto);
    public void delete(ProductoDTO producto);
}

