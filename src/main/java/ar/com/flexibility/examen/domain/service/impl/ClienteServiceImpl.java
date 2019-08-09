package ar.com.flexibility.examen.domain.service.impl;

import ar.com.flexibility.examen.domain.service.ClienteService;
import ar.com.flexibility.examen.domain.model.Compra;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.stereotype.Service;

import ar.com.flexibility.examen.domain.model.Cliente;

@Service
public class ClienteServiceImpl implements ClienteService {

	/*
	 * TODO usar 
	 * private ClienteRepository clienteRepository;
	 */
	
	private Map<Long, Cliente> Clientes = new HashMap<Long,Cliente>();
	private static final AtomicLong id = new AtomicLong();
	
	public ClienteServiceImpl() {
		Cliente p1 = new Cliente(id.getAndIncrement(), "Juan", "123");
		Cliente p2 = new Cliente(id.getAndIncrement(), "Pedro", "345");
		Cliente p3 = new Cliente(id.getAndIncrement(), "Jose", "567");
		
		Clientes.put(p1.getId(), p1);
		Clientes.put(p2.getId(), p2);
		Clientes.put(p3.getId(), p3);
		
	}
	
	public void setClientes(List<Cliente> Clientes){
		for (Cliente p: Clientes) {
			this.Clientes.put(p.getId(), p);
		}
		
	}
	
	
	@Override
	public Cliente findById(long id) {
		if (Clientes.containsKey(id)) {
			return Clientes.get(id);
		}
		
		return null;
	}

	@Override
	public void update(Cliente Cliente) {
		Clientes.put(Cliente.getId(), Cliente);
	}

	@Override
	public void deleteById(long id) {
		Clientes.remove(id);
	}

	@Override
	public Cliente save(Cliente Cliente) {
		Cliente.setId(id.getAndIncrement());
		Clientes.put(Cliente.getId(), Cliente);
		return Cliente;
	}

	@Override
	public List<Cliente> getAll() {
		return new ArrayList<Cliente>(Clientes.values());
	}

	@Override
	public List<Compra> findComprasById(long id) {
		Cliente cliente = findById(id);
		 
		return cliente.compras();
	}
	
	
}
