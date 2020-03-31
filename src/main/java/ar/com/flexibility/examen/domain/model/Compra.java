package ar.com.flexibility.examen.domain.model;

import java.util.Collection;

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
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import ar.com.genomasoft.jproject.core.entities.BaseAuditedEntity;
import ar.com.genomasoft.jproject.core.entities.BaseClientAuditedEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@Entity
@ApiModel(	value="Compra", 
			parent=BaseAuditedEntity.class)
@Table(name = "COMPRA")
@Where(clause="DELETED_TIME IS NULL")
@SQLDelete(sql="UPDATE COMPRA SET DELETED_TIME = CURRENT_TIMESTAMP WHERE ID = ? AND VERSION = ?")
public class Compra extends BaseClientAuditedEntity{

	public enum Estado {
		GENERADO,   // Estado inicial
		APROBADO   //APROBADO
	}
	

	
	private Cliente cliente;
	private Estado estado = Estado.GENERADO;
	private Collection<CompraDetalle> detalles;


	public Compra() {

	}
	
	@Override
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID", unique=true, nullable=false)
	@ApiModelProperty(value="Clave Primaria de la Presentación", required=false, position=0)
	public Integer getId() {
		return super.id;
	}

	
	@ManyToOne(fetch = FetchType.LAZY , cascade = CascadeType.REFRESH)
	@JoinColumn(name = "CLIENTE_ID")
	@ApiModelProperty(value = "Cliente", required = true, position = 1)
	public Cliente  getCliente() {
		return cliente;
	}

	
	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	/**
	 * @return the estado
	 */
	@Column(name="ESTADO")
	@Enumerated(EnumType.STRING)
	@ApiModelProperty(value="Estado de la copra", required=true, position=4)	
	public Estado getEstado() {
		return estado;
	}

	/**
	 * @param estado the estado to set
	 */
	public void setEstado(Estado estado) {
		this.estado = estado;
	}

	
	/**
	 * @return the detalles
	 */
	@JsonManagedReference(value = "compra-detalle")
	@LazyCollection(LazyCollectionOption.TRUE)
	@OneToMany(mappedBy="compra",  cascade = CascadeType.DETACH   , fetch = FetchType.LAZY, orphanRemoval = false)
	@ApiModelProperty(value="Detalle de compras", required=false, position=8)
	public Collection<CompraDetalle> getDetalles() {
		return detalles;
	}

	/**
	 * @param detalles the detalles to set
	 */
	public void setDetalles(Collection<CompraDetalle> detalles) {
		this.detalles = detalles;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	
}
