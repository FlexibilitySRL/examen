package ar.com.plug.examen.domain.service;

import ar.com.plug.examen.domain.dto.ResponseDTO;
import ar.com.plug.examen.domain.model.Productos;

public interface ProductoService {

	public ResponseDTO saveProductos(Productos productos);

	public ResponseDTO deleteProductos(Integer id);

	public ResponseDTO updateProductos(Productos producto);

}
