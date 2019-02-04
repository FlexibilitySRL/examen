package ar.com.flexibility.examen.domain.model;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "Cliente")
public class Cliente{

	@Id
	@Column(name="dni")
	private int dni;
	
	@Column(name="nombreYApellido")
	private String nombreYApellido;
	
	@Column(name="razonSocial")
	private String razonSocial;

	public int getDni() {
		return dni;
	}

	public void setDni(int dni) {
		this.dni = dni;
	}

	public String getNombreYApellido() {
		return nombreYApellido;
	}

	public void setNombreYApellido(String nombreYApellido) {
		this.nombreYApellido = nombreYApellido;
	}

	public String getRazonSocial() {
		return razonSocial;
	}

	public void setRazonSocial(String razonSocial) {
		this.razonSocial = razonSocial;
	}
	
	@JsonIgnore
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "cliente")
	private Set<Comprobante> comprobante = new HashSet<Comprobante>(0);

	public Set<Comprobante> getComprobante() {
		return comprobante;
	}

	public void setComprobante(Set<Comprobante> comprobante) {
		this.comprobante = comprobante;
	}
	
	
}