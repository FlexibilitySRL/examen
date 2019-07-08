package ar.com.flexibility.examen.domain.service.impl;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.data.jpa.repository.JpaRepository;
import ar.com.flexibility.examen.domain.repository.IClienteRepo;
import ar.com.flexibility.examen.domain.model.Cliente;
import ar.com.flexibility.examen.domain.model.Producto;


@Service("servicioCliente")
public class ClienteImpl {
	
	@Autowired
	@Qualifier("repositorio")
	private IClienteRepo clienteRepo;
	
	private static final Log Logger = LogFactory.getLog(ClienteImpl.class);
	
	
	public boolean insertar (Cliente cliente) {
			
		Logger.info("agregando cliente");
		
		try {
			clienteRepo.save(cliente);
			Logger.info("cliente agregado");			 
			 return true;
		}catch(Exception e) {
			Logger.info("no pudo agregarse el cliente"); 
			return false;
		} 
	}
		
	public List<Cliente> findAll(){
		
		return clienteRepo.findAll();
	}
	
	
	public boolean modificar(Cliente cliente) {
		
		try {
			clienteRepo.save(cliente);
			Logger.info("cliente modificado");			 
			 return true;
		}catch(Exception e) {
			Logger.info("no pudo modificar el cliente"); 
			return false;
		} 
	}
	
	
	
	public boolean eliminar (String nombre) {
		
		try {
			Cliente cliente = clienteRepo.findByNombre(nombre);
			clienteRepo.delete(cliente);
			return true;
		}catch(Exception e) {
			return false;
		}
	}
	
}

