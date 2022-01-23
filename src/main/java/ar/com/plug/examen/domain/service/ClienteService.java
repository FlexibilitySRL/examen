package ar.com.plug.examen.domain.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import ar.com.plug.examen.domain.model.ClienteModel;
import ar.com.plug.examen.app.api.ClienteRepository;

@Service
public class ClienteService{

	@Autowired
	ClienteRepository clienteRepository;
	

	public ArrayList<ClienteModel> findClientes(){
		return (ArrayList<ClienteModel>) clienteRepository.findAll();
	}

	public ClienteModel insertClient(ClienteModel clientes)  {
		return clienteRepository.save(clientes);
	}

	public Optional<ClienteModel> findId(Long id){
		return clienteRepository.findById(id);
	}


}