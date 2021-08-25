package ar.com.plug.examen.domain.mapper;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import ar.com.plug.examen.data.entity.Cliente;
import ar.com.plug.examen.domain.dto.ClienteDTO;
import ar.com.plug.examen.domain.exception.CustomException;

@Component
public class ClienteMapper {

	public ClienteDTO mapClienteToClienteDTO(Cliente cliente)  {

		ClienteDTO clienteDTO =  new ClienteDTO(cliente.getIdCliente(),cliente.getNombre(),cliente.getApellido(), 
				cliente.getDireccion(),cliente.getTelefono(),cliente.getEmail(),cliente.getNumIdent());

		return clienteDTO;
	}

	public Cliente mapClienteDTOToCliente(ClienteDTO clienteDTO) throws CustomException {

		Cliente cliente =  new Cliente(clienteDTO.getId(),clienteDTO.getNumeroIdentificacion(),clienteDTO.getNombre(), 
				clienteDTO.getApellido(),clienteDTO.getDireccion(),clienteDTO.getEmail(),clienteDTO.getTelefono());

		return cliente;
	}

	public List<ClienteDTO> mapListClienteToClienteDTO(List<Cliente> clientes){	
		List<ClienteDTO> listaClienteDTO = new ArrayList<ClienteDTO>();

		for (Cliente cliente : clientes) {
			ClienteDTO clienteDTO = mapClienteToClienteDTO(cliente);
			listaClienteDTO.add(clienteDTO);			
		}

		return listaClienteDTO;		
	}
}
