package ar.com.flexibility.examen.domain.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import ar.com.genomasoft.jproject.core.entities.BaseAuditedEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;


@Entity
@ApiModel(	value="Cliente", 
			description="Entidad Cliente del sistema ", 
			parent=BaseAuditedEntity.class)

@Table(name = "USR_CLIENTE")
@Where(clause="DELETED_TIME IS NULL")
@SQLDelete(sql="UPDATE USR_CLIENTE SET DELETED_TIME = CURRENT_TIMESTAMP WHERE ID = ? AND VERSION = ?")
public class Cliente extends BaseAuditedEntity {

	private String cuit;
	private String denominacion;

	public Cliente() {

	}
	
	 
	@Override
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID", unique=true, nullable=false)
	@ApiModelProperty(value="Clave Primaria del Cliente", required=false, position=0)
	public Integer getId() {
		return super.id;
	}


	@Column(name = "CUIT", nullable=false, length=11, unique=true)
	@ApiModelProperty(value="CUIT del cliente.", required=true, position=2)
	public String getCuit() {
		return cuit;
	}

	
	public void setCuit(String cuit) {
		this.cuit = cuit;
	}

	
	@Column(name = "DENOMINACION", nullable=true, length=250, unique=false)
	@ApiModelProperty(value="Denominación del cliente.", required=true, position=3)
	public String getDenominacion() {
		return denominacion;
	}

	
	public void setDenominacion(String denominacion) {
		this.denominacion = denominacion;
	}

	
	@Override
	public void extractMutableValues(Object newObject) {
		super.extractMutableValues(newObject);
		Cliente other = (this.getClass().cast(newObject));
		this.setCuit(other.getCuit());
		this.setDenominacion(other.getDenominacion());
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((cuit == null) ? 0 : cuit.hashCode());
		result = prime * result + ((denominacion == null) ? 0 : denominacion.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		Cliente other = (Cliente) obj;
		if (cuit == null) {
			if (other.cuit != null)
				return false;
		} else if (!cuit.equals(other.cuit))
			return false;
		if (denominacion == null) {
			if (other.denominacion != null)
				return false;
		} else if (!denominacion.equals(other.denominacion))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Cliente [" 
				+ (id != null ? "id=" + id + ", " : "")
				+ (cuit != null ? "cuit=" + cuit + ", " : "")
				+ (denominacion != null ? "denominacion=" + denominacion : "") 
				+ (version != null ? "version=" + version : "") 
				+ (createdByUser != null ? "createdByUser=" + createdByUser + ", " : "")
				+ (createdTime != null ? "createdTime=" + createdTime + ", " : "")
				+ (updatedByUser != null ? "updatedByUser=" + updatedByUser + ", " : "")
				+ (updatedTime != null ? "updatedTime=" + updatedTime : "") 
				+ (deletedTime != null ? "deletedTime=" + deletedTime + ", " : "")
				+ "]";
	}


}