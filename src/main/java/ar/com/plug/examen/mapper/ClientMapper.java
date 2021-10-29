package ar.com.plug.examen.mapper;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import ar.com.plug.examen.domain.model.entities.Client;
import ar.com.plug.examen.dto.ClientDto;

@Component
public class ClientMapper {

	public ClientDto entityToDto(Client dao) {
		ClientDto dto = new ClientDto();
		dto.setName(dao.getName());
		dto.setLastname(dao.getLastname());
		dto.setEmail(dao.getEmail());
		return dto;
	}
	
	public Client dtoToEntity(ClientDto dto) {
		Client dao = new Client();
		dao.setName(dto.getName());
		dao.setLastname(dto.getLastname());
		dao.setEmail(dto.getEmail());
		return dao;
	}
	
	public List<ClientDto> entityListToDtoList(List<Client> daos) {
		return daos.stream().map(d -> this.entityToDto(d)).collect(Collectors.toList());
	}
	
	public List<Client> dtoListToEntityList(List<ClientDto> dtos) {
		return dtos.stream().map(d -> this.dtoToEntity(d)).collect(Collectors.toList());
	}
}
