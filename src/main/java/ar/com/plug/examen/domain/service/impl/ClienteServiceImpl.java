package ar.com.plug.examen.domain.service.impl;

import java.util.List;
import java.util.Optional;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.com.plug.examen.data.entity.Cliente;
import ar.com.plug.examen.data.repository.ClienteRepository;
import ar.com.plug.examen.domain.dto.ClienteDTO;
import ar.com.plug.examen.domain.exception.ResourceNotFoundException;
import ar.com.plug.examen.domain.mapper.ClienteMapper;
import ar.com.plug.examen.domain.service.ClienteService;

@Service
public class ClienteServiceImpl implements ClienteService{

	public static final Logger logger = Logger.getLogger(ClienteService.class);

	private ClienteRepository clienteRepository;

	private ClienteMapper clienteMapper;

	@Autowired
	private  ClienteServiceImpl(ClienteRepository clienteRepository,ClienteMapper clienteMapper) {
		this.clienteRepository = clienteRepository;
		this.clienteMapper = clienteMapper;
	}

	@Override
	public List<ClienteDTO> listarClientes() {	
		return this.clienteMapper.mapListClienteToClienteDTO(this.clienteRepository.findAll());
	}

	@Override
	public ClienteDTO consultarClientePorId(long id) {
		if(id <=0) {
			throw new RuntimeException();
		}
		Optional<Cliente> clienteInfo =  this.clienteRepository.findById(id); 
		if (clienteInfo.isPresent()) {
			logger.trace("Registro Encontrado: " +clienteInfo.get().toString());
			return this.clienteMapper.mapClienteToClienteDTO(clienteInfo.get());
		}else {
			throw new ResourceNotFoundException("Invalid id");
		}
	}

	@Override
	public ClienteDTO crearCliente(ClienteDTO clienteDTO)   {
		return  this.clienteMapper.mapClienteToClienteDTO(this.clienteRepository.save(this.clienteMapper.mapClienteDTOToCliente(clienteDTO)));		
	}

	@Override
	public ClienteDTO actualizarCliente(ClienteDTO clienteDTO) {
		Optional<Cliente> clienteInfo =  this.clienteRepository.findById(clienteDTO.getId());
		if (clienteInfo.isPresent()) {
			logger.trace("Registro actualizado: " +clienteInfo.get().toString());
			return this.clienteMapper.mapClienteToClienteDTO(this.clienteRepository.save(this.clienteMapper.mapClienteDTOToCliente(clienteDTO)));
		}else {
			throw new ResourceNotFoundException("Invalid id");
		}
	}

	@Override
	public void eliminarCliente(long id){
		Optional<Cliente> clienteInfo =  this.clienteRepository.findById(id);
		if (clienteInfo.isPresent()) {
			this.clienteRepository.deleteById(id);
			logger.trace("Registro eliminado: " +clienteInfo.get().toString());
		}else {
			throw new ResourceNotFoundException("Invalid id");
		}
	}

}
