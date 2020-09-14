package ar.com.flexibility.examen.domain.service.impl;

import java.util.ArrayList;
import java.util.List;

import ar.com.flexibility.examen.domain.model.Cliente;
import ar.com.flexibility.examen.domain.model.Vendedor;
import ar.com.flexibility.examen.domain.service.VendedorInterface;

public class VendedorServiceImpl implements VendedorInterface {
	
	private List<Vendedor> vendedores;
	
	public VendedorServiceImpl() {
		this.vendedores = this.obtenerVendedores();
	}
	@Override
	public List<Vendedor> obtenerVendedores() {
		vendedores = new ArrayList<Vendedor>();
		Vendedor vendedor;
		for(int i = 0; i < 10; i++) {
			vendedor = new Vendedor();
			vendedor.setIdVendedor((long) (i + 1));
			vendedor.setNombre("Vendedor " + (i+1));
			vendedores.add(vendedor);
		}
		return vendedores;
	}

	@Override
	public boolean modificar(Vendedor vendedor) {
		Vendedor venMod = this.vendedores
				.stream()
				.filter(ven -> ven.getIdVendedor().equals(vendedor.getIdVendedor()))
				.findAny()
				.orElse(null);
		if(venMod != null) {			
			this.vendedores.remove(venMod);
		}else {
			return false;
		}
		this.vendedores.add(venMod);
		return true;
	}

	@Override
	public boolean eliminar(Long idVendedor) {
		boolean retorno = false;
		Vendedor venDel = this.vendedores.stream()
		.filter(ven -> ven.getIdVendedor().equals(idVendedor))
		.findAny()
		.orElse(null);
		try {
			this.vendedores.remove(venDel);
			retorno = true;
		}catch(IndexOutOfBoundsException ex) {			
			retorno = false;
		}
		return retorno;
	}

	@Override
	public boolean crear(Vendedor vendedor) {
		Vendedor venFind = this.vendedores.stream()
				.filter(ven -> ven.getIdVendedor().equals(ven.getIdVendedor()))
				.findAny()
				.orElse(null);
				if(venFind != null) {
					this.vendedores.add(vendedor);
					return true;
				}
				return false;
	}

	@Override
	public Vendedor obtener(Long idVendedor) {
		return this.vendedores.stream()
				.filter(ven -> ven.getIdVendedor().equals(idVendedor))
				.findAny()
				.orElse(null);
	}
	
	
}
