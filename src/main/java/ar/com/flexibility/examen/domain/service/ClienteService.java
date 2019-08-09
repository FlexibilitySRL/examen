package ar.com.flexibility.examen.domain.service;

import java.util.List;

import ar.com.flexibility.examen.domain.model.Compra;
import ar.com.flexibility.examen.domain.model.Cliente;


public interface ClienteService {
	Cliente findById(long id);
	void update(Cliente cliente);
	void deleteById(long id);
	Cliente save(Cliente cliente);
	List<Cliente> getAll();
	List<Compra> findComprasById(long id);
}
