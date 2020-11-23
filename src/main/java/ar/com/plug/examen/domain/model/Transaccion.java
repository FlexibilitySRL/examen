package ar.com.plug.examen.domain.model;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import ar.com.plug.examen.util.StateEnum;

@Entity
@Table(name = "transaction")
public class Transaccion implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -4355650529196514655L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_trx")
	private Long id;
	
	@Column(name = "cod_trx")
	private String codigoTrx;
	
	@ManyToOne(optional = false, cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
	@JoinColumn(name = "id_cliente", referencedColumnName = "id_cliente")
	private Cliente idCliente;
	
	@ManyToOne(optional = false, cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
	@JoinColumn(name = "id_produc", referencedColumnName = "id_produc")
	private Producto idProducto;
	
	@Column(name = "state", length = 20, nullable = false)
	@Enumerated(EnumType.STRING)
	private StateEnum estado;
	
	@Column(name = "fecha_creacion")
	private LocalDateTime fechaCreacion;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCodigoTrx() {
		return codigoTrx;
	}

	public void setCodigoTrx(String codigoTrx) {
		this.codigoTrx = codigoTrx;
	}
	
	public LocalDateTime getFechaCreacion() {
		return fechaCreacion;
	}

	public void setFechaCreacion(LocalDateTime fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}

	public Cliente getIdCliente() {
		return idCliente;
	}

	public void setIdCliente(Cliente idCliente) {
		this.idCliente = idCliente;
	}

	public Producto getIdProducto() {
		return idProducto;
	}

	public void setIdProducto(Producto idProducto) {
		this.idProducto = idProducto;
	}

	public StateEnum getEstado() {
		return estado;
	}

	public void setEstado(StateEnum estado) {
		this.estado = estado;
	}

	@Override
	public String toString() {
		return "Transaccion [id=" + id + ", codigoTrx=" + codigoTrx + "]";
	}
	
}
