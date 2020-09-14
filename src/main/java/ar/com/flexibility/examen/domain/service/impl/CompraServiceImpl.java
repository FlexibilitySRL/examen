package ar.com.flexibility.examen.domain.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import ar.com.flexibility.examen.domain.model.Cliente;
import ar.com.flexibility.examen.domain.model.Compra;
import ar.com.flexibility.examen.domain.model.ItemsCompra;
import ar.com.flexibility.examen.domain.service.ClienteInterface;
import ar.com.flexibility.examen.domain.service.CompraInterface;

public class CompraServiceImpl implements CompraInterface{
	
	private List<Compra> compras = new ArrayList<Compra>();
	
	@Inject
	private ClienteInterface clienteInt;
	
	public CompraServiceImpl() {
		this.compras = this.obtenerCompras();
	}
	
	@Override
	public List<Compra> obtenerCompras() {
		this.compras = new ArrayList<Compra>();
		Compra compra;
		for(int i = 0; i < 5; i++) {
			compra = new Compra();
			compra.setIdCompra((long) (i + 1));
			Cliente c = this.clienteInt.obtener((long) (i*2));
			compra.setIdCliente(c);
			this.compras.add(compra);
		}
		return this.compras;
	}

	@Override
	public boolean modificar(Compra compra) {
		Compra comMod = this.compras
				.stream()
				.filter(co -> co.getIdCompra().equals(compra.getIdCompra()))
				.findAny()
				.orElse(null);
		if(comMod != null) {			
			this.compras.remove(comMod);
		}else {
			return false;
		}
		this.compras.add(comMod);
		return true;
	}

	@Override
	public boolean eliminar(Long idCompra) {
		boolean retorno = false;
		Compra comDel = this.compras.stream()
		.filter(com -> com.getIdCompra().equals(idCompra))
		.findAny()
		.orElse(null);
		try {
			this.compras.remove(comDel);
			retorno = true;
		}catch(IndexOutOfBoundsException ex) {			
			retorno = false;
		}
		return retorno;
	}

	@Override
	public boolean crear(Compra compra) {
		Compra comFind = this.compras.stream()
				.filter(com -> com.getIdCompra().equals(compra.getIdCompra()))
				.findAny()
				.orElse(null);
				if(comFind != null) {
					this.compras.add(comFind);
					return true;
				}
				return false;
			}

	@Override
	public Compra obtenerCompra(Long idCompra) {
		return this.compras.stream()
				.filter(com -> com.getIdCompra().equals(idCompra))
				.findAny()
				.orElse(null);
	}

	@Override
	public List<ItemsCompra> obtenerItems() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Compra> obtenerCompraCliente(Long idCliente) {
		List<Compra> listComprasCliente = new ArrayList<Compra>();
		for(Compra c : this.compras) {
			if(c.getIdCliente().getIdCliente().equals(idCliente)) {
				listComprasCliente.add(c);
			}
		}
		return listComprasCliente;
	}

	@Override
	public boolean autorizarCompra(Long idCompra) {
		Compra c = this.compras.stream()
		.filter(com -> com.getIdCompra().equals(idCompra))
		.findAny()
		.orElse(null);
		if(c != null) {
			c.setAutoizado(Boolean.TRUE);
			return true;
		}
		return false;
	}

}
