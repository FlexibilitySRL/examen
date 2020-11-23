package ar.com.plug.examen.domain.service.impl;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.com.plug.examen.app.api.ClienteBean;
import ar.com.plug.examen.app.api.ProductoBean;
import ar.com.plug.examen.app.api.TransaccionBean;
import ar.com.plug.examen.domain.model.Cliente;
import ar.com.plug.examen.domain.model.Producto;
import ar.com.plug.examen.domain.model.Transaccion;
import ar.com.plug.examen.domain.service.ClienteService;
import ar.com.plug.examen.domain.service.ProductoService;
import ar.com.plug.examen.domain.service.TransaccionService;

@Service
public class TransaccionServiceImpl {
	
	@Autowired
	private TransaccionService service;
	
	@Autowired
	private ClienteService service2;
	
	@Autowired
	private ProductoService service3;
	
	/**
	 * Metodo que permite crear una transaccion de compra
	 * @param bean
	 * @return
	 */
	public TransaccionBean crearTransaccion(TransaccionBean bean) {
		Transaccion t = new Transaccion();
		t.setCodigoTrx(bean.getCodigoTrx());
		t.setEstado(bean.getEstado());
		t.setFechaCreacion(LocalDateTime.now());
		
		if (bean.getIdCliente().getNumeroDocumento() != null && !bean.getIdCliente().getNumeroDocumento().isEmpty()) {
			Cliente cl = service2.findByNumeroDocumento(bean.getIdCliente().getNumeroDocumento());
			t.setIdCliente(cl);
		}
		
		if (bean.getIdProducto().getCodProducto() != null && !bean.getIdProducto().getCodProducto().isEmpty()) {
			Producto producto = service3.findByCodProducto(bean.getIdProducto().getCodProducto());
			t.setIdProducto(producto);
		}
		
		t = service.save(t);
		
		bean.setId(t.getId());
		bean.setFechaCreacion(t.getFechaCreacion());
		return bean;
	}
	
	/**
	 * Metodo que permite consultar una transaccion
	 * @param codigoTrx
	 * @return
	 */
	public TransaccionBean getTransaccionById(String codigoTrx) {
		Transaccion transaccion = null;
		if(!codigoTrx.isEmpty() ) {
			transaccion = service.findByCodigoTrx(codigoTrx);
			if (transaccion != null) {
				return obtenerTransaccionBean(transaccion);
			}
		}
		return null;
	}
	
	/**
	 * Metodo que permite mapear la entidad al bean de la transaccion
	 * @param transaccion
	 * @return
	 */
	private TransaccionBean obtenerTransaccionBean(Transaccion transaccion) {
		TransaccionBean bean = new TransaccionBean();
		bean.setCodigoTrx(transaccion.getCodigoTrx());
		bean.setEstado(transaccion.getEstado());
		bean.setFechaCreacion(transaccion.getFechaCreacion());
		bean.setIdCliente(obtenerClienteBean(transaccion.getIdCliente()));
		bean.setIdProducto(obtenerProductoBean(transaccion.getIdProducto()));
		return bean;
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

	private ClienteBean obtenerClienteBean(Cliente cliente) {
		ClienteBean bean = new ClienteBean();
		bean.setApellidoCliente(cliente.getApellidoCliente());
		bean.setFechaCreacion(cliente.getFechaCreacion());
		bean.setIdCliente(cliente.getIdCliente());
		bean.setNombreCliente(cliente.getNombreCliente());
		bean.setNumeroDocumento(cliente.getNumeroDocumento());
		bean.setTipoDocumento(cliente.getTipoDocumento());
		return bean;
	}

}
