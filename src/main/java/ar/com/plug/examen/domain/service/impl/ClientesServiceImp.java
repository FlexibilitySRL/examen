package ar.com.plug.examen.domain.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

import ar.com.plug.examen.domain.dto.ResponseDTO;
import ar.com.plug.examen.domain.model.Clientes;
import ar.com.plug.examen.domain.repository.ClientesRepository;
import ar.com.plug.examen.domain.service.ClienteService;

@Service
public class ClientesServiceImp implements ClienteService{
	
	private ClientesRepository clientesRepository;
	
	public ClientesServiceImp(ClientesRepository clientesRepository) {
		this.clientesRepository = clientesRepository;
	}
	
	public ResponseDTO saveClientes(Clientes clientes) {

		ResponseDTO responseDTO = new ResponseDTO();
		responseDTO.setResponse(clientesRepository.save(clientes));
		responseDTO.setMessage("Code 200");
		return responseDTO;

	}

	public ResponseDTO updateClientes(Clientes clientes) {
		ResponseDTO responseDTO = new ResponseDTO();

		Clientes oldclientes = findByIdclientes(clientes.getId());
		
		if(oldclientes != null) {
			oldclientes.setNombre(clientes.getNombre());
			oldclientes.setCedula(clientes.getCedula());			
			responseDTO.setResponse(clientesRepository.save(oldclientes));
			responseDTO.setMessage("code 200");
		}
						
		return responseDTO;

	}

	public Clientes findByIdclientes(Integer id) {
		return clientesRepository.getOne(id);
	}

	public ResponseDTO deleteClientes(Integer id) {
		Clientes clientes = findByIdclientes(id);
		Map<String, Boolean> response = new HashMap<>();
		ResponseDTO responseDTO = new ResponseDTO();

		if (clientes != null) {
			clientesRepository.delete(clientes);
			response.put("delete", true);
			responseDTO.setResponse(response);
			responseDTO.setMessage("code 200");
			return responseDTO;
		}

		response.put("delete", false);
		responseDTO.setResponse(response);
		responseDTO.setMessage("code 400");
		return responseDTO;

	}

}
