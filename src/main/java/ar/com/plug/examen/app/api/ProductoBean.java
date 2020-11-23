package ar.com.plug.examen.app.api;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ProductoBean {

	@JsonProperty
	@JsonInclude(JsonInclude.Include.NON_NULL)
	private Long idProducto;
	
	@JsonProperty
	@JsonInclude(JsonInclude.Include.NON_NULL)
	private String nombreProducto;
	
	@JsonProperty
	@JsonInclude(JsonInclude.Include.NON_NULL)
	private String descripcionProducto;
	
	@JsonProperty
	@JsonInclude(JsonInclude.Include.NON_NULL)
	private String codProducto;
	
	@JsonProperty
	@JsonInclude(JsonInclude.Include.NON_NULL)
	private LocalDateTime fechaCreacion;
	
	@JsonProperty
	@JsonInclude(JsonInclude.Include.NON_NULL)
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
	
	
}
