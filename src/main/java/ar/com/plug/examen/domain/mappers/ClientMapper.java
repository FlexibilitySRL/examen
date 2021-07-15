package ar.com.plug.examen.domain.mappers;

import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Component;

import ar.com.plug.examen.domain.model.ClientDTO;
import ar.com.plug.examen.entities.Client;

@Component
public class ClientMapper {

	
	public ClientDTO clientEntityToClientDTO(Client client) {
		ClientDTO clientDTO = new ClientDTO();
		clientDTO.setId(client.getId());
		clientDTO.setFirstname(client.getFirstname());
		clientDTO.setLastname(client.getLastname());
		clientDTO.setEmail(client.getEmail());
	    return clientDTO;
	  }

	  public Client clientDTOToClientEntity(ClientDTO clientDTO) {
	    Client client = new Client();
	    client.setId(clientDTO.getId());
	    client.setFirstname(clientDTO.getFirstname());
	    client.setLastname(clientDTO.getLastname());
	    client.setEmail(clientDTO.getEmail());
	    return client;
	  }

	  public List<ClientDTO> clientsToListClientDTO(List<Client> clients) {
	    return clients.stream().map(this::clientEntityToClientDTO).collect(Collectors.toList());
	  }
	
}
