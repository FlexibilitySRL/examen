package ar.com.flexibility.examen.domain.service.impl;

import java.util.ArrayList;
import java.util.List;

import ar.com.flexibility.examen.domain.model.Cliente;
import ar.com.flexibility.examen.domain.model.Compra;
import ar.com.flexibility.examen.domain.model.Producto;
import ar.com.flexibility.examen.domain.service.ProductoInterface;

public class ProductoServiceImpl implements ProductoInterface{
	
	private List<Producto> productos;
	
	public ProductoServiceImpl() {
		this.productos = this.obtenerProductos();
	}

	@Override
	public List<Producto> obtenerProductos() {
		this.productos = new ArrayList<Producto>();
		Producto producto;
		for(int i = 0; i < 5; i++) {
			producto = new Producto();
			producto.setIdProducto((long) (i + 1));
			producto.setNombreProducto("Producto "+(i + 1));
			this.productos.add(producto);
		}
		return this.productos;
	}

	@Override
	public boolean modificar(Producto producto) {
		Producto proMod = this.productos
				.stream()
				.filter(pr -> pr.getIdProducto().equals(producto.getIdProducto()))
				.findAny()
				.orElse(null);
		if(proMod != null) {			
			this.productos.remove(proMod);
		}else {
			return false;
		}
		this.productos.add(producto);
		return true;
	}

	@Override
	public boolean eliminar(Long idProducto) {
		boolean retorno = false;
		Producto proDel = this.productos
				.stream()
		.filter(pro -> pro.getIdProducto().equals(idProducto))
		.findAny()
		.orElse(null);
		try {
			this.productos.remove(proDel);
			retorno = true;
		}catch(IndexOutOfBoundsException ex) {			
			retorno = false;
		}
		return retorno;
	}

	@Override
	public boolean crear(Producto producto) {
		Producto proFind = this.productos.stream()
				.filter(pr -> pr.getIdProducto().equals(producto.getIdProducto()))
				.findAny()
				.orElse(null);
				if(proFind != null) {
					this.productos.add(proFind);
					return true;
				}
				return false;
	}

	@Override
	public Producto obtenerProducto(Long idProducto) {
		return this.productos
				.stream()
				.filter(pr -> pr.getIdProducto().equals(idProducto))
				.findAny()
				.orElse(null);
	}

	
}
