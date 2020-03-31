package ar.com.flexibility.examen.domain.model;

import java.math.BigDecimal;

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

import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import com.fasterxml.jackson.annotation.JsonBackReference;

import ar.com.genomasoft.jproject.core.entities.BaseAuditedEntity;
import ar.com.genomasoft.jproject.core.entities.BaseClientAuditedEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;


@Entity
@ApiModel(	value="Detalle de la compra", 
			parent=BaseAuditedEntity.class)
@Table(name = "COMPRA_DETALLE")
@Where(clause="DELETED_TIME IS NULL")
@SQLDelete(sql="UPDATE COMPRA_DETALLE SET DELETED_TIME = CURRENT_TIMESTAMP WHERE ID = ? AND VERSION = ?")
public class CompraDetalle extends BaseClientAuditedEntity {

    
    
    private Compra compra;
	private Producto producto;
	private BigDecimal cantidad;
	
	
	@Override
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID", unique=true, nullable=false)
	@ApiModelProperty(value="Clave Primaria del Detalle", required=false, position=0)
	public Integer getId() {
		return this.id;
	}
    

	@JsonBackReference(value = "compra-detalle")
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "COMPRA_ID")
	@ApiModelProperty(value = "Compra", required = true, position = 1)
	public Compra getCompra() {
		return compra;
	}

	
	public void setCompra(Compra compra) {
		this.compra = compra;
	}

	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "PRODUCTO_ID")
	@ApiModelProperty(value = "Producto", required = true, position = 2)	
	public Producto getProducto() {
		return producto;
	}


	public void setProducto(Producto producto) {
		this.producto = producto;
	}
    
	
	@Column(name="CANTIDAD")
	@ApiModelProperty(value="cantidad de productos", required=true, position=3)	
	public BigDecimal getCantidad() {
		return cantidad;
	}


	public void setCantidad(BigDecimal cantidad) {
		this.cantidad = cantidad;
	}

	
	
}
