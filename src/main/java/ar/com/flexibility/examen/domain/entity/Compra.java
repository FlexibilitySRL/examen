package ar.com.flexibility.examen.domain.entity;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.fasterxml.jackson.annotation.JsonIgnore;


@Entity
@Table(name="compras")
public class Compra {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private Integer cant_art;
	private String tipo_pago;
	
	@ManyToOne(fetch= FetchType.LAZY, optional= false)
	@OnDelete(action = OnDeleteAction.CASCADE)
	@JoinColumn(name= "id_articulo",nullable=false)
	@JsonIgnore
	private Producto id_articulo;

	@ManyToOne(fetch= FetchType.LAZY, optional= false)
	@OnDelete(action = OnDeleteAction.CASCADE)
	@JoinColumn(name= "id_cliente",nullable=false)
	@JsonIgnore
	private Cliente id_cliente;
	
	@ManyToOne(fetch= FetchType.LAZY, optional= false)
	@OnDelete(action = OnDeleteAction.CASCADE)
	@JoinColumn(name= "id_vendedor",nullable=false)
	@JsonIgnore
	private Vendedor id_vendedor;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getCant_art() {
		return cant_art;
	}

	public void setCant_art(Integer cant_art) {
		this.cant_art = cant_art;
	}

	public String getTipo_pago() {
		return tipo_pago;
	}

	public void setTipo_pago(String tipo_pago) {
		this.tipo_pago = tipo_pago;
	}

	public Producto getId_articulo() {
		return id_articulo;
	}

	public void setId_articulo(Producto id_articulo) {
		this.id_articulo = id_articulo;
	}

	public Cliente getId_cliente() {
		return id_cliente;
	}

	public void setId_cliente(Cliente id_cliente) {
		this.id_cliente = id_cliente;
	}

	public Vendedor getId_vendedor() {
		return id_vendedor;
	}

	public void setId_vendedor(Vendedor id_vendedor) {
		this.id_vendedor = id_vendedor;
	}

	
	
	
}
