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

import ar.com.plug.examen.app.api.ClienteBean;
import ar.com.plug.examen.domain.service.impl.ClienteServiceImpl;

@RestController
public class ClienteController {
	
	private static final Logger logger = LoggerFactory.getLogger(ClienteController.class);
	
	@Autowired
	private ClienteServiceImpl impl;
	
	/**
	 * Metodo que permite crear un cliente
	 * @param bean
	 * @return
	 */
	@RequestMapping(value = "/v1/cliente", produces = { "application/json" }, method = RequestMethod.POST)
	public ResponseEntity<ClienteBean> crearCliente(@RequestBody ClienteBean bean) {
		try {
			bean = impl.createCliente(bean);
			logger.info("Se guarda correctamente el cliente con id "+bean.getIdCliente());
			return new ResponseEntity<ClienteBean>(bean, HttpStatus.OK);
		} catch (Exception e) {
			logger.error( "Error en la creación del producto " + e.getMessage());
			return new ResponseEntity<ClienteBean>(bean, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	/**
	 * Metodo que permite dar de baja un cliente por medio del id
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/v1/cliente/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> eliminarCliente(@PathVariable Long id) {
		logger.info("Eliminando cliente con id: {}", id);
		ClienteBean bean = impl.getClienteByID(id);
		if (bean == null) {
			logger.error( "Error eliminado el producto con id: " + id);
            return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
		}
		logger.info("Eliminado correctament el producto con id: {}", id);
		impl.eliminarCliente(bean);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}
	
	/**
	 * Metodo que permite modificar un cliente
	 * @param id
	 * @param bean
	 * @return
	 */
	@RequestMapping(value = "/v1/cliente/{id}", method = RequestMethod.PUT)
	public ResponseEntity<ClienteBean> actualizarCliente(@PathVariable Long id, @RequestBody ClienteBean bean) {
		try {
			bean = impl.getClienteByID(id);
			if (bean == null) {
				return new ResponseEntity<ClienteBean>(HttpStatus.NOT_FOUND);
			}

			bean = impl.createCliente(bean);
			logger.info("Se actualiza correctamente el cliente con id "+bean.getIdCliente());
			return new ResponseEntity<ClienteBean>(bean, HttpStatus.OK);
		} catch (Exception e) {
			logger.error( "Error en la actualización del cliente " + e.getMessage());
			return new ResponseEntity<ClienteBean>(bean, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}
