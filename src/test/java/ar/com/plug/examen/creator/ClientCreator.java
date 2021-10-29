package ar.com.plug.examen.creator;

import java.util.ArrayList;
import java.util.List;

import ar.com.plug.examen.domain.model.entities.Client;
import ar.com.plug.examen.dto.ClientDto;

public class ClientCreator {

	public static Client createClient(String name, String lastname, String email) {
		Client c = new Client();
		c.setName(name);
		c.setLastname(lastname);
		c.setEmail(email);
		return c;
	}
	
	public static ClientDto createClientDto(String name, String lastname, String email){
		ClientDto c = new ClientDto();
		c.setName(name);
		c.setLastname(lastname);
		c.setEmail(email);
		return c;
	}
	
	public static ClientDto createClientDtoWithId(Long id){
		ClientDto c = new ClientDto();
		c.setId(id);
		return c;
	}

	public static Client createClientWithId(Long id){
		Client c = new Client();
		c.setId(id);
		return c;
	}

	public static List<ClientDto> createClientDtoList(ClientDto c1, ClientDto c2) {
		List<ClientDto> cList = new ArrayList<ClientDto>();
		cList.add(c1);
		cList.add(c2);
		return cList;
	}
	
	public static ClientDto createClientDtoMock() {
		ClientDto c = new ClientDto();
		c.setName("Client 1");
		c.setLastname("Client 1");
		c.setEmail("Client 1");
		return c;
	}
	public static Client createClientMock() {
		Client c = new Client();
		c.setName("Client 1");
		c.setLastname("Client 1");
		c.setEmail("Client 1");
		return c;
	}
}
