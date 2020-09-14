package ar.com.flexibility.examen.domain.model;

import java.sql.Date;
import java.util.List;

public class Compra {

	private Long idCompra;
	private Cliente idCliente;
	private Double valorTotal;
	private Date fechaCompra;
	private Vendedor idVendedor;
	private List<ItemsCompra> items;
	private boolean autoizado;
	
	public Long getIdCompra() {
		return idCompra;
	}
	public void setIdCompra(Long idCompra) {
		this.idCompra = idCompra;
	}
	public Cliente getIdCliente() {
		return idCliente;
	}
	public void setIdCliente(Cliente idCliente) {
		this.idCliente = idCliente;
	}
	public Double getValorTotal() {
		return valorTotal;
	}
	public void setValorTotal(Double valorTotal) {
		this.valorTotal = valorTotal;
	}
	public Date getFechaCompra() {
		return fechaCompra;
	}
	public void setFechaCompra(Date fechaCompra) {
		this.fechaCompra = fechaCompra;
	}
	public Vendedor getIdVendedor() {
		return idVendedor;
	}
	public void setIdVendedor(Vendedor idVendedor) {
		this.idVendedor = idVendedor;
	}
	
	public void setImtes(List<ItemsCompra> items) {
		this.items = items;
	}
	public List<ItemsCompra> getItems() {
		return items;
	}
	public void setItems(List<ItemsCompra> items) {
		this.items = items;
	}
	public boolean isAutoizado() {
		return autoizado;
	}
	public void setAutoizado(boolean autoizado) {
		this.autoizado = autoizado;
	}
	
	
}
