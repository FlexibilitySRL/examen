package ar.com.plug.examen.app.rest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import ar.com.plug.examen.app.api.ProductoBean;
import ar.com.plug.examen.domain.service.impl.ProductoServiceImpl;

@RestController
public class ProductoController {
	
	private static final Logger logger = LoggerFactory.getLogger(ProductoController.class);
	
	@Autowired
	private ProductoServiceImpl service;

	@RequestMapping(value = "/v1/producto", produces = { "application/json" }, method = RequestMethod.POST)
	public ResponseEntity<ProductoBean> createProducto(@RequestBody ProductoBean productoBean) {
		try {
			productoBean = service.createProducto(productoBean);
			logger.info("Se guarda correctamente el producto con id "+productoBean.getIdProducto());
			return new ResponseEntity<ProductoBean>(productoBean, HttpStatus.OK);
		} catch (Exception e) {
			logger.error( "Error en la creación del producto " + e.getMessage());
			return new ResponseEntity<ProductoBean>(productoBean, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@RequestMapping(value = "/v1/producto/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> deleteProducto(@PathVariable Long id) {
		logger.info("Eliminando producto con id: {}", id);
		ProductoBean productoBean = service.getProductoByID(id);
		if (productoBean == null) {
			logger.error( "Error eliminado el producto con id: " + id);
            return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
		}
		logger.info("Eliminado correctament el producto con id: {}", id);
		service.deleteProducto(productoBean);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}
	
	@RequestMapping(value = "/v1/producto/{id}", method = RequestMethod.PUT)
	public ResponseEntity<ProductoBean> update(@PathVariable Long id, @RequestBody ProductoBean productoBean) {
		try {
			productoBean = service.getProductoByID(id);
			if (productoBean == null) {
				return new ResponseEntity<ProductoBean>(HttpStatus.NOT_FOUND);
			}

			productoBean = service.createProducto(productoBean);
			logger.info("Se guarda correctamente el producto con id "+productoBean.getIdProducto());
			return new ResponseEntity<ProductoBean>(productoBean, HttpStatus.OK);
		} catch (Exception e) {
			logger.error( "Error en la creación del producto " + e.getMessage());
			return new ResponseEntity<ProductoBean>(productoBean, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
}
