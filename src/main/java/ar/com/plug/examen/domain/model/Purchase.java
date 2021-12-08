package ar.com.plug.examen.domain.model;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="purchase")
public class Purchase {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	@Column(name="id_compra")
	private Long idCompra;
	@Column(name="unidad")
	private int unidad;
	@Column(name="precio")
	private BigDecimal precio;
	@Column(name="id_product")
	private Long id_Product;
	@Column(name="estado_transaccion")
	private String estado_Transaccion;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getIdCompra() {
		return idCompra;
	}
	public void setIdCompra(Long idCompra) {
		this.idCompra = idCompra;
	}
	public int getUnidad() {
		return unidad;
	}
	public void setUnidad(int unidad) {
		this.unidad = unidad;
	}
	public BigDecimal getPrecio() {
		return precio;
	}
	public void setPrecio(BigDecimal precio) {
		this.precio = precio;
	}
	public Long getId_Product() {
		return id_Product;
	}
	public void setId_Product(Long id_Product) {
		this.id_Product = id_Product;
	}
	public String getEstado_Transaccion() {
		return estado_Transaccion;
	}
	public void setEstado_Transaccion(String estado_Transaccion) {
		this.estado_Transaccion = estado_Transaccion;
	}
	
	
}
