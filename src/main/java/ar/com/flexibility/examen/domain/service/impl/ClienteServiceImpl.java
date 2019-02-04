package ar.com.flexibility.examen.domain.service.impl;
import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ar.com.flexibility.examen.domain.model.Cliente;
import ar.com.flexibility.examen.domain.service.ClienteService;
import ar.com.flexibility.examen.repository.ClienteRepository;

@Service
public class ClienteServiceImpl implements ClienteService{
	
	@Autowired
	private ClienteRepository clienteRepository;

	@Override
	public ArrayList<Cliente> findAll() {
		return (ArrayList<Cliente>) clienteRepository.findAll();
	}

	@Override
	public void removeByid(int id) {
		 clienteRepository.delete(id);
	}

	@Override
	public Cliente findById(Integer id) {
		return clienteRepository.findOne(id);
	}

	@Override
	public Cliente save(Cliente c) {
		return clienteRepository.save(c); 
	}
	
}
