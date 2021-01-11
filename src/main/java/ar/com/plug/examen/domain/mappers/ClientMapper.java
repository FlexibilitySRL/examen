package ar.com.plug.examen.domain.mappers;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.stereotype.Component;

import ar.com.plug.examen.app.api.ClientApi;
import ar.com.plug.examen.domain.model.Client;

@Component
public class ClientMapper implements Mapper<Client, ClientApi>{

	@Override
	public ClientApi getDto(Client entity) {
		
		ClientApi dto = new ClientApi();
		
		dto.setId(entity.getId());
		dto.setName(entity.getName());
		
		return dto;
		
	}

	@Override
	public Client fillEntity(Client entity, ClientApi dto) {
		
		entity.setId(dto.getId());
		entity.setName(dto.getName());
		
		return entity;
	}

	@Override
	public List<ClientApi> getDto(Collection<Client> entities) {
		
		List<ClientApi> dto = new ArrayList<>();
		
		for(Client client : entities) {
			dto.add(getDto(client));
		}
		
		return dto;
	}

}
