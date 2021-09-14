package ar.com.plug.examen.domain.service.impl;

import ar.com.plug.examen.domain.model.dto.ClienteCompraRestDto;
import ar.com.plug.examen.domain.model.dto.ClienteRestDto;
import ar.com.plug.examen.domain.model.entity.Cliente;
import ar.com.plug.examen.domain.model.repository.ClienteRepository;
import ar.com.plug.examen.domain.service.IClienteService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClienteServiceImpl implements IClienteService {

	@Autowired
	private ClienteRepository clienteRepository;
	
	@Override
	public ClienteRestDto updateCliente(ClienteRestDto clienteRestDto) {
		
		Cliente cliente = mapClienteRestDto(clienteRestDto);
		
		Cliente aux = clienteRepository.save(cliente);
		
		return mapCliente(aux);
	}

	@Override
	public  List<ClienteRestDto> getClientes (){
		
		List<ClienteRestDto> clienteRestDtos = new ArrayList<ClienteRestDto>();
		
		clienteRepository.findAll()
			.forEach(cliente -> clienteRestDtos.add(this.mapCliente(cliente)));
		
		return clienteRestDtos;
		
	}
	
	@Override
	public ClienteRestDto getClienteById(Long idCliente) {
		
		Optional<Cliente> opCliente = clienteRepository.findById(idCliente);

		return opCliente.isPresent() ? mapCliente(opCliente.get()) : null;
				
	}


	private ClienteRestDto mapCliente(Cliente cliente) {
		
		ClienteRestDto clienteRestDto = new ClienteRestDto();
		clienteRestDto.setId(cliente.getId());
		clienteRestDto.setNombre(cliente.getNombre());
		return clienteRestDto;
		
	}

	private Cliente mapClienteRestDto (ClienteRestDto clienteRestDto) {
		
		Cliente cliente = new Cliente();
		cliente.setId(clienteRestDto.getId());
		cliente.setNombre(clienteRestDto.getNombre());
		return cliente;
		
	}

	@Override
	public Boolean removeClienteById(Long id) {

		clienteRepository.deleteById(id);
		
		return true;
		
	}

	@Override
	public ClienteCompraRestDto getClienteComprasById(Long idCliente) {
		
		Optional<Cliente> opCliente = clienteRepository.findById(idCliente);		
		
		return opCliente.isPresent() ? mapClienteCompra(opCliente.get()) : null;
				
	}
	
	private ClienteCompraRestDto mapClienteCompra(Cliente cliente) {
		
		ClienteCompraRestDto clienteCompraRestDto = new ClienteCompraRestDto();
		clienteCompraRestDto.setId(cliente.getId());
		clienteCompraRestDto.setNombre(cliente.getNombre());
		clienteCompraRestDto.setCompras(CompraServiceImpl.mapComprasCompletas(cliente.getCompras()));
				
		return clienteCompraRestDto;
		
	}
	
	
}
