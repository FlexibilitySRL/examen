package ar.com.flexibility.examen.domain.service.impl;

import java.util.ArrayList;
import java.util.List;

import ar.com.flexibility.examen.domain.model.Cliente;
import ar.com.flexibility.examen.domain.service.ClienteInterface;

public class ClienteServiceImpl implements ClienteInterface {
	
	private List<Cliente> clientes;
	
	public ClienteServiceImpl(){
		this.clientes = this.obtenerClientes();		
	}
	
	@Override
	public List<Cliente> obtenerClientes() {
		clientes = new ArrayList<Cliente>();
		Cliente cliente;
		for(int i = 0; i < 10; i++) {
			cliente = new Cliente();
			cliente.setIdCliente((long) (i + 1));
			cliente.setNombreCliente("Cliente " + (i+1));
			cliente.setDireccion("Calle " + i + " No " + (i*3));
			clientes.add(cliente);
		}
		return clientes;
	}

	@Override
	public boolean modificar(Cliente cliente) {
		Cliente cliMod = this.clientes
				.stream()
				.filter(cli -> cli.getIdCliente().equals(cliente.getIdCliente()))
				.findAny()
				.orElse(null);
		if(cliMod != null) {			
			this.clientes.remove(cliMod);
		}else {
			return false;
		}
		this.clientes.add(cliente);
		return true;
	}

	@Override
	public boolean eliminar(Long idCliente) {
		boolean retorno = false;
		Cliente cliDel = this.clientes.stream()
		.filter(cliente -> cliente.getIdCliente().equals(idCliente))
		.findAny()
		.orElse(null);
		try {
			this.clientes.remove(cliDel);
			retorno = true;
		}catch(IndexOutOfBoundsException ex) {			
			retorno = false;
		}
		return retorno;
	}

	@Override
	public boolean crear(Cliente cliente) {
		Cliente cliFind = this.clientes.stream()
		.filter(cli -> cli.getIdCliente().equals(cliente.getIdCliente()))
		.findAny()
		.orElse(null);
		if(cliFind != null) {
			this.clientes.add(cliente);
			return true;
		}
		return false;
	}

	@Override
	public Cliente obtener(Long idCliente) {
		return this.clientes.stream()
				.filter(cli -> cli.getIdCliente().equals(idCliente))
				.findAny()
				.orElse(null);
	}
}
