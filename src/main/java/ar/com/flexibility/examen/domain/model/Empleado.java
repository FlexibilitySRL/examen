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
@Table(name = "Empleado")
public class Empleado {
	
	@Id
	@Column(name="id_empleado")
	private int idEmpleado;

	public int getIdEmpleado() {
		return idEmpleado;
	}

	public void setIdEmpleado(int idEmpleado) {
		this.idEmpleado = idEmpleado;
	}
	
	@JsonIgnore
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "empleado")
	private Set<Comprobante> comprobante = new HashSet<Comprobante>(0);

	public Set<Comprobante> getComprobante() {
		return comprobante;
	}

	public void setComprobante(Set<Comprobante> comprobante) {
		this.comprobante = comprobante;
	}
	
}
