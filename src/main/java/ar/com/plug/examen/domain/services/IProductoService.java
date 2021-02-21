package ar.com.plug.examen.domain.services;

import ar.com.plug.examen.domain.dtos.ClienteDTO;
import ar.com.plug.examen.domain.dtos.ProductoDTO;

import java.util.List;

public interface IProductoService {

    public List<ProductoDTO> getAll();
    public ProductoDTO save(ProductoDTO producto);
    public void delete(ProductoDTO producto);
}
