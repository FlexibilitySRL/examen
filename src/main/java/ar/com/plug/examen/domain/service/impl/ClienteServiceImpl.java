package ar.com.plug.examen.domain.service.impl;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.com.plug.examen.app.api.ClienteBean;
import ar.com.plug.examen.domain.model.Cliente;
import ar.com.plug.examen.domain.service.ClienteService;

@Service
public class ClienteServiceImpl {
	
	@Autowired
	private ClienteService service;
	
	public ClienteBean createCliente(ClienteBean bean) {
		Cliente cliente = new Cliente();
		cliente.setNombreCliente(bean.getNombreCliente());
		cliente.setApellidoCliente(bean.getApellidoCliente());
		cliente.setNumeroDocumento(bean.getNumeroDocumento());
		cliente.setTipoDocumento(bean.getTipoDocumento());
		cliente.setFechaCreacion(LocalDateTime.now());
		
		cliente = service.save(cliente);
		
		bean.setIdCliente(cliente.getIdCliente());
		bean.setFechaCreacion(cliente.getFechaCreacion());
		return bean;
	}
	
	public ClienteBean getClienteByID(Long id) {
		Cliente cliente = null;
		if (id != null) {
			if (service.existsById(id)) {
				cliente = service.getOne(id);
				if (cliente != null) {
					return obtenerClienteBean(cliente);
				}
			}
			return null;
			
		}
		return null;
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
	
	public void eliminarCliente(ClienteBean bean) {
		Cliente cliente = new Cliente();
		cliente.setNombreCliente(bean.getNombreCliente());
		cliente.setApellidoCliente(bean.getApellidoCliente());
		cliente.setNumeroDocumento(bean.getNumeroDocumento());
		cliente.setTipoDocumento(bean.getTipoDocumento());
		cliente.setFechaCreacion(bean.getFechaCreacion());
		service.delete(cliente);
	}

}
