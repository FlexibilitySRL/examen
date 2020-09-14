package ar.com.flexibility.examen.domain.service.impl;

import java.util.List;

import javax.inject.Inject;

import ar.com.flexibility.examen.domain.model.ItemsCompra;
import ar.com.flexibility.examen.domain.model.Producto;
import ar.com.flexibility.examen.domain.service.ItemBuyInterface;

public class ItemBuyServiceImpl implements ItemBuyInterface{

	private List<ItemsCompra> itemsCompras;
	
	@Inject
	ProductoServiceImpl productoImpl;
	
	@Inject
	CompraServiceImpl compraImpl;
	
	public ItemBuyServiceImpl() {
		ItemsCompra item;
		for(int i = 0; i < 10; i++) {
			item = new ItemsCompra();
			item.setIdItem((long) (i + 1));
			item.setCantidad(1 * 2);
			item.setIdProducto(this.productoImpl.obtenerProducto((long) i));
			item.setIdCompra(this.compraImpl.obtenerCompra((long) i));
			this.itemsCompras.add(item);
		}
	}
	
	@Override
	public List<ItemsCompra> obtenerItems() {
		return this.itemsCompras;
	}

	@Override
	public List<ItemsCompra> obtenerItemsCompra(Long idCompra) {
		
		
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean modificar(ItemsCompra item) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean eliminar(Long idItem) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean crear(ItemsCompra item) {
		// TODO Auto-generated method stub
		return false;
	}

}
