package ar.com.flexibility.examen.domain.model;

public class ItemsCompra {
	
	private Long idItem;
	private Compra idCompra;
	private Producto idProducto;
	private int cantidad;
	private double valor;
	
	public ItemsCompra() {
		
	}
	
	public ItemsCompra(Long idItem, Compra idCompra, Producto idProducto, int cantidad) {
		setIdItem(idItem);
		setIdCompra(idCompra);
		setIdProducto(idProducto);
		setCantidad(cantidad);
		setValor(getIdProducto().getPrecio() * getCantidad());
	}
	
	public Long getIdItem() {
		return idItem;
	}
	public void setIdItem(Long idItem) {
		this.idItem = idItem;
	}
	public Compra getIdCompra() {
		return idCompra;
	}
	public void setIdCompra(Compra idCompra) {
		this.idCompra = idCompra;
	}
	public Producto getIdProducto() {
		return idProducto;
	}
	public void setIdProducto(Producto idProducto) {
		this.idProducto = idProducto;
	}
	public int getCantidad() {
		return cantidad;
	}
	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}
	public double getValor() {
		return valor;
	}
	public void setValor(double valor) {
		this.valor = valor;
	}
}
