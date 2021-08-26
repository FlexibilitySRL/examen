package ar.com.plug.examen.domain.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

import ar.com.plug.examen.domain.dto.ResponseDTO;
import ar.com.plug.examen.domain.model.Productos;
import ar.com.plug.examen.domain.repository.ProductosRepository;
import ar.com.plug.examen.domain.service.ProductoService;

@Service
public class ProductoServiceImp implements ProductoService{
	
	ProductosRepository productosRepository;
	
	public ProductoServiceImp(ProductosRepository productosRepository) {
		this.productosRepository = productosRepository;
	}
	
	public ResponseDTO saveProductos(Productos producto) {

		ResponseDTO responseDTO = new ResponseDTO();
		responseDTO.setResponse(productosRepository.save(producto));
		responseDTO.setMessage("Code 200");
		return responseDTO;

	}

	public ResponseDTO updateProductos(Productos producto) {
		ResponseDTO responseDTO = new ResponseDTO();

		Productos oldProductos = findByIdProducto(producto.getId());
		
		if(oldProductos != null) {
			oldProductos.setProducto(producto.getProducto());
			oldProductos.setPrecio(producto.getPrecio());			
			responseDTO.setResponse(productosRepository.save(oldProductos));
			responseDTO.setMessage("code 200");
		}
						
		return responseDTO;

	}

	public Productos findByIdProducto(Integer id) {
		return productosRepository.getOne(id);
	}

	public ResponseDTO deleteProductos(Integer id) {
		Productos producto = findByIdProducto(id);
		Map<String, Boolean> response = new HashMap<>();
		ResponseDTO responseDTO = new ResponseDTO();

		if (producto != null) {
			productosRepository.delete(producto);
			response.put("delete", true);
			responseDTO.setResponse(response);
			responseDTO.setMessage("code 200");
			return responseDTO;
		}

		response.put("delete", false);
		responseDTO.setResponse(response);
		responseDTO.setMessage("code 400");
		return responseDTO;

	}

}
