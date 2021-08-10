package ar.com.plug.examen.domain.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.com.plug.examen.domain.dao.ClienteDao;
import ar.com.plug.examen.domain.dto.ClienteAltaDto;
import ar.com.plug.examen.domain.dto.ClienteUpdateDto;
import ar.com.plug.examen.domain.model.Cliente;
import ar.com.plug.examen.domain.service.ClienteService;

@Service
public class ClienteServiceImpl implements ClienteService {
	
	@Autowired
	private ClienteDao dao;

	@Override
	public List<Cliente> getClients() {
		return dao.findAll();
	}

	@Override
	public Optional<Cliente> findById(Integer id) {
		return dao.findById(id);
	}

	@Override
	public Cliente createClient(ClienteAltaDto source) {
		Cliente entity = new Cliente();
		entity.setApellido(source.getApellido());
		entity.setDireccion(source.getDireccion());
		entity.setDocumento(source.getDocumento());
		entity.setFechaNacimiento(source.getFechaNacimiento());
		entity.setNombre(source.getNombre());
		entity.setTelefono(source.getTelefono());
		return dao.save(entity);
	}

	@Override
	public Cliente updateClient(ClienteUpdateDto source) {
		Cliente entity = findById(source.getId()).get();
		if (Optional.ofNullable(source.getApellido()).isPresent())
			entity.setApellido(source.getApellido());
		if (Optional.ofNullable(source.getDireccion()).isPresent())
			entity.setDireccion(source.getDireccion());
		if (Optional.ofNullable(source.getDocumento()).isPresent())
			entity.setDocumento(source.getDocumento());
		if (Optional.ofNullable(source.getFechaNacimiento()).isPresent())
			entity.setFechaNacimiento(source.getFechaNacimiento());
		if (Optional.ofNullable(source.getNombre()).isPresent())
			entity.setNombre(source.getNombre());
		if (Optional.ofNullable(source.getTelefono()).isPresent())
			entity.setTelefono(source.getTelefono());
		return dao.save(entity);
	}

	@Override
	public void deleteClient(Integer id) {
		dao.deleteById(id);
	}

}
