package ar.com.plug.examen.domain.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.sun.istack.NotNull;

@Entity
@Table(name = "product")
public class Producto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3890158737251989L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_produc")
	private Long idProducto;
	
	@Column(name = "nom_produc", nullable = false)
	private String nombreProducto;
	
	@Column(name = "descri_produc")
	private String descripcionProducto;
	
	@Column(name = "cod_produc")
	private String codProducto;
	
	@Column(name = "fch_creacion")
	private LocalDateTime fechaCreacion;
	
	@Column(name = "monto_produc")
	private BigDecimal montoProducto;

	public Long getIdProducto() {
		return idProducto;
	}

	public void setIdProducto(Long idProducto) {
		this.idProducto = idProducto;
	}

	public String getNombreProducto() {
		return nombreProducto;
	}

	public void setNombreProducto(String nombreProducto) {
		this.nombreProducto = nombreProducto;
	}

	public String getDescripcionProducto() {
		return descripcionProducto;
	}

	public void setDescripcionProducto(String descripcionProducto) {
		this.descripcionProducto = descripcionProducto;
	}

	public String getCodProducto() {
		return codProducto;
	}

	public void setCodProducto(String codProducto) {
		this.codProducto = codProducto;
	}

	public LocalDateTime getFechaCreacion() {
		return fechaCreacion;
	}

	public void setFechaCreacion(LocalDateTime fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}

	public BigDecimal getMontoProducto() {
		return montoProducto;
	}

	public void setMontoProducto(BigDecimal montoProducto) {
		this.montoProducto = montoProducto;
	}

	@Override
	public String toString() {
		return String.format("Producto[id=%d, Nombre='%s'']", idProducto, nombreProducto);
	}
}
