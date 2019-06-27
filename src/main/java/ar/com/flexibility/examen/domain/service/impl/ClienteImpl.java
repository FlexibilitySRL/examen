package ar.com.flexibility.examen.domain.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.data.jpa.repository.JpaRepository;
import ar.com.flexibility.examen.domain.repository.IClienteRepo;
import ar.com.flexibility.examen.domain.model.Cliente;
import ar.com.flexibility.examen.domain.service.ClienteService;

@Service
public class ClienteImpl implements ClienteService{
	
	@Autowired
	private IClienteRepo clienteRepo;
	
	@Override
	public Cliente insertar(Cliente cliente) {
		return clienteRepo.save(cliente); 
	}
		
	@Override
	public List<Cliente> findAll(){
		
		return clienteRepo.findAll();
	}
	
	@Override
	public Cliente modificar(Cliente cliente) {
		
		return clienteRepo.save(cliente);
	}
	
	@Override
	public Cliente eliminar (Cliente cliente) {
		
		return clienteRepo.save(cliente);
	}
	
}

