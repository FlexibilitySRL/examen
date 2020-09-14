package ar.com.flexibility.examen.domain.service;

import java.util.List;

import ar.com.flexibility.examen.domain.model.Compra;
import ar.com.flexibility.examen.domain.model.ItemsCompra;

public interface CompraInterface {
	
	List<Compra> obtenerCompras();
	
	boolean modificar(Compra compra);
	
	boolean eliminar(Long idCompra);
	
	boolean crear(Compra compra);
	
	Compra obtenerCompra(Long idCompra);
	
	List<ItemsCompra> obtenerItems();
	
	List<Compra> obtenerCompraCliente(Long idCliente);
	
	boolean autorizarCompra(Long idCompra);
}
