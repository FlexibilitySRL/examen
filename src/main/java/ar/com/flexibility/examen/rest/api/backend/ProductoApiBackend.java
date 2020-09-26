package ar.com.flexibility.examen.rest.api.backend;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import ar.com.flexibility.examen.domain.service.ProductService;
import ar.com.flexibility.examen.factory.ProductFactory;
import ar.com.flexibility.examen.factory.ProductoDTOFactory;
import ar.com.flexibility.examen.rest.api.model.ProductoDTO;
import ar.com.flexibility.examen.rest.api.model.RespuestaDTO;

/**
 * 
 * @author juank525
 *
 */
@Component
public class ProductoApiBackend {

	private final Logger logger = LoggerFactory.getLogger(ProductoApiBackend.class);

	@Autowired
	private ProductService productService;

	/**
	 * Metodo que permite actualizar la informacion de un producto
	 * 
	 * @param id
	 * @param productoDTO
	 * @return
	 */
	public ResponseEntity<ProductoDTO> actualizarProducto(String id, ProductoDTO productoDTO) {
		logger.debug("Inicia actualizacion del producto {}", id);
		ProductoDTO productoDTOResponse = ProductoDTOFactory.INSTANCE
				.from(productService.updateProduct(ProductFactory.INSTANCE.from(id, productoDTO)));
		logger.debug("Finaliza actualizacion del producto {} correctamente", id);
		return new ResponseEntity<>(productoDTOResponse, HttpStatus.OK);
	}

	/**
	 * Metodo que permite la creacion de un producto
	 * 
	 * @param productoDTO
	 * @return
	 */
	public ResponseEntity<ProductoDTO> crearProducto(ProductoDTO productoDTO) {
		logger.debug("Inicia creacion del producto {}", productoDTO);
		ProductoDTO productoDTOResponse = ProductoDTOFactory.INSTANCE
				.from(productService.createProduct(ProductFactory.INSTANCE.from(productoDTO)));
		logger.debug("Finaliza creacion del producto {} exitosamente", productoDTO);
		return new ResponseEntity<>(productoDTOResponse, HttpStatus.OK);
	}

	/**
	 * Metodo que permite eliminar informacion de un producto
	 * 
	 * @param id
	 * @return
	 */
	public ResponseEntity<RespuestaDTO> eliminarProducto(String id) {
		logger.debug("Inicia la eliminacion del producto {}", id);
		productService.deleteProduct(id);
		RespuestaDTO respuestaDTO = new RespuestaDTO();
		respuestaDTO.setCode(HttpStatus.OK.value());
		respuestaDTO.setMessage("Registro eliminado correctamente");
		logger.debug("Finaliza la eliminacion del producto {} exitosamente", id);
		return new ResponseEntity<>(respuestaDTO, HttpStatus.OK);
	}

}
