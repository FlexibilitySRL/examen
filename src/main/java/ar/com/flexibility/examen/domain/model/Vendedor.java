package ar.com.flexibility.examen.domain.model;
import java.io.Serializable;
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
@Table(name = "Vendedor")
public class Vendedor{
	
	@Id
	@Column(name="id_vendedor")
	private int idVendedor;

	public int getIdVendedor() {
		return idVendedor;
	}

	public void setIdVendedor(int idVendedor) {
		this.idVendedor = idVendedor;
	}
	
	@JsonIgnore
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "vendedor")
	private Set<Comprobante> comprobante = new HashSet<Comprobante>(0);

	public Set<Comprobante> getComprobante() {
		return comprobante;
	}

	public void setComprobante(Set<Comprobante> comprobante) {
		this.comprobante = comprobante;
	}
	
}
