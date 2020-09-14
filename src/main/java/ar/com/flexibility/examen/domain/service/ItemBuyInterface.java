package ar.com.flexibility.examen.domain.service;

import java.util.List;

import ar.com.flexibility.examen.domain.model.ItemsCompra;

public interface ItemBuyInterface {
	
	List<ItemsCompra> obtenerItems();
	
	List<ItemsCompra> obtenerItemsCompra(Long idCompra);
	
	boolean modificar(ItemsCompra item);
	
	boolean eliminar(Long idItem);
	
	boolean crear(ItemsCompra item);
}
