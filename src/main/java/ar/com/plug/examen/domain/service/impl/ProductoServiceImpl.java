package ar.com.plug.examen.domain.service.impl;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.com.plug.examen.app.api.ProductoBean;
import ar.com.plug.examen.domain.model.Producto;
import ar.com.plug.examen.domain.service.ProductoService;

@Service
public class ProductoServiceImpl {

	@Autowired
	private ProductoService service;

	public ProductoBean createProducto(ProductoBean bean) {
		Producto p = new Producto();
		p.setNombreProducto(bean.getNombreProducto());
		p.setCodProducto(bean.getCodProducto());
		p.setDescripcionProducto(bean.getDescripcionProducto());
		p.setMontoProducto(bean.getMontoProducto());
		p.setFechaCreacion(LocalDateTime.now());

		/**
		 * Permite guardar la instancia de la entidad
		 */
		p = service.save(p);

		bean.setIdProducto(p.getIdProducto());
		bean.setFechaCreacion(p.getFechaCreacion());

		return bean;
	}

	public ProductoBean getProductoByID(Long idProducto) {
		Producto product = null;
		if (idProducto != null) {
			if (service.existsById(idProducto)) {
				product = service.getOne(idProducto);
				if (product != null) {
					return obtenerProductoBean(product);
				}
			}
			return null;
			
		}
		return null;
	}

	private ProductoBean obtenerProductoBean(Producto producto) {
		ProductoBean bean = new ProductoBean();
		bean.setCodProducto(producto.getCodProducto());
		bean.setDescripcionProducto(producto.getDescripcionProducto());
		bean.setFechaCreacion(producto.getFechaCreacion());
		bean.setIdProducto(producto.getIdProducto());
		bean.setMontoProducto(producto.getMontoProducto());
		bean.setNombreProducto(producto.getNombreProducto());

		return bean;
	}

	public void deleteProducto(ProductoBean bean) {
		Producto p = new Producto();
		p.setNombreProducto(bean.getNombreProducto());
		p.setCodProducto(bean.getCodProducto());
		p.setDescripcionProducto(bean.getDescripcionProducto());
		p.setMontoProducto(bean.getMontoProducto());
		p.setIdProducto(bean.getIdProducto());
		p.setFechaCreacion(bean.getFechaCreacion());
		service.delete(p);
	}

}
