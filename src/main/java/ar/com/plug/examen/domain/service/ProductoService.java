package ar.com.plug.examen.domain.service;

import java.util.List;

import ar.com.plug.examen.domain.dto.ProductoDTO;

public interface ProductoService {

	List<ProductoDTO> listarProductos();
	ProductoDTO consultarProductoPorId(long id);
	ProductoDTO crearProducto(ProductoDTO productoDTO);
	ProductoDTO actualizarProducto(ProductoDTO productoDTO);
	void eliminarProducto(long id);
}
