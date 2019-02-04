package ar.com.flexibility.examen.domain.model;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "Comprobante")
public class Comprobante {

	@Id
	@Column(name="id_comprobante")
	private int idComprobante;
	
	@Column(name="fechaAutorizacion")
	private Long fechaAutorizacion;
	
	@Column(name="autorizado")
	private boolean autorizado;

	public int getIdComprobante() {
		return idComprobante;
	}

	public void setIdComprobante(int idComprobante) {
		this.idComprobante = idComprobante;
	}

	public Long getFechaAutorizacion() {
		return fechaAutorizacion;
	}

	public void setFechaAutorizacion(Long fechaAutorizacion) {
		this.fechaAutorizacion = fechaAutorizacion;
	}

	public boolean isAutorizado() {
		return autorizado;
	}

	public void setAutorizado(boolean autorizado) {
		this.autorizado = autorizado;
	}
	
	@JsonIgnore
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "cliente_dni", nullable = true)
	private Cliente cliente;
	
	@JsonIgnore
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "vendedor_id_vendedor", nullable = true)
	private Vendedor vendedor;
	
	@JsonIgnore
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "empleado_id_empleado", nullable = true)
	private Empleado empleado;
	
	@JsonIgnore
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "comprobante")
	private Set<Renglon> renglon = new HashSet<Renglon>(0);

	public Set<Renglon> getRenglon() {
		return renglon;
	}

	public void setRenglon(Set<Renglon> renglon) {
		this.renglon = renglon;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public Vendedor getVendedor() {
		return vendedor;
	}

	public void setVendedor(Vendedor vendedor) {
		this.vendedor = vendedor;
	}

	public Empleado getEmpleado() {
		return empleado;
	}

	public void setEmpleado(Empleado empleado) {
		this.empleado = empleado;
	}
	
}
