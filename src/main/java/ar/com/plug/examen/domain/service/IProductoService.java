package ar.com.plug.examen.domain.service;

import java.util.List;

import ar.com.plug.examen.domain.model.dto.ProductoRestDto;

public interface IProductoService {

	 public ProductoRestDto updateProducto(ProductoRestDto productoRestDto);
	 
	 public List<ProductoRestDto> getProductos();

	 public ProductoRestDto getProductoById(Long idProducto);

	 public Boolean removeProductoById(Long id);


}
