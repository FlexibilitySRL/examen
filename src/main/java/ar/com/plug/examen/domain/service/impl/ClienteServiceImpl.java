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
	public Cliente createClient(ClienteAltaDto d) {
		Cliente cliente = new Cliente();
		cliente.setApellido(d.getApellido());
		cliente.setDireccion(d.getDireccion());
		cliente.setDocumento(d.getDocumento());
		cliente.setFechaNacimiento(d.getFechaNacimiento());
		cliente.setNombre(d.getNombre());
		cliente.setTelefono(d.getTelefono());
		return dao.save(cliente);
	}

	@Override
	public Cliente updateClient(ClienteUpdateDto d) {
		Cliente cliente = findById(d.getId()).get();
		if (Optional.ofNullable(d.getApellido()).isPresent())
			cliente.setApellido(d.getApellido());
		if (Optional.ofNullable(d.getDireccion()).isPresent())
			cliente.setDireccion(d.getDireccion());
		if (Optional.ofNullable(d.getDocumento()).isPresent())
			cliente.setDocumento(d.getDocumento());
		if (Optional.ofNullable(d.getFechaNacimiento()).isPresent())
			cliente.setFechaNacimiento(d.getFechaNacimiento());
		if (Optional.ofNullable(d.getNombre()).isPresent())
			cliente.setNombre(d.getNombre());
		if (Optional.ofNullable(d.getTelefono()).isPresent())
			cliente.setTelefono(d.getTelefono());
		return dao.save(cliente);
	}

	@Override
	public void deleteClient(Integer id) {
		dao.deleteById(id);
	}

}
