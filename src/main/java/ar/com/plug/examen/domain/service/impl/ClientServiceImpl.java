package ar.com.plug.examen.domain.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.com.plug.examen.domain.model.jpa.ClientJpaDao;
import ar.com.plug.examen.domain.service.IClientService;
import ar.com.plug.examen.dto.ClientDto;
import ar.com.plug.examen.mapper.ClientMapper;

@Service
public class ClientServiceImpl implements IClientService{

	@Autowired
	private ClientJpaDao dao;
	
	@Autowired
	private ClientMapper mapper;
	
	@Override
	public List<ClientDto> findAll() {
		return mapper.entityListToDtoList(dao.findAll());
	}

	@Override
	public ClientDto findById(Long id) throws Exception {
		return mapper.entityToDto(dao.findById(id).orElseThrow(() -> new Exception("No se encontro el cliente Nro: " + id)));
	}

	@Override
	public ClientDto save(ClientDto client) {
		return mapper.entityToDto(dao.save(mapper.dtoToEntity(client)));
	}
	
	@Override
	public ClientDto update(ClientDto client) throws Exception {
		if(dao.findById(client.getId()).isPresent()) {
			return mapper.entityToDto(dao.save(mapper.dtoToEntity(client)));			
		}
		else {
			throw new Exception ("No se puede actualizar el cliente Nro:" + client.getId());
		}
	}

	@Override
	public void delete(Long id) {
		dao.deleteById(id);		
	}

}
