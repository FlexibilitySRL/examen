package ar.com.plug.examen.app.api;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import ar.com.plug.examen.util.StateEnum;

@JsonIgnoreProperties(ignoreUnknown = true)
public class TransaccionBean {
	
	@JsonProperty
	@JsonInclude(JsonInclude.Include.NON_NULL)
	private Long id;
	
	@JsonProperty
	@JsonInclude(JsonInclude.Include.NON_NULL)
	private String codigoTrx;
	
	@JsonProperty
	@JsonInclude(JsonInclude.Include.NON_NULL)
	private ClienteBean idCliente;
	
	@JsonProperty
	@JsonInclude(JsonInclude.Include.NON_NULL)
	private ProductoBean idProducto;
	
	@JsonProperty
	@JsonInclude(JsonInclude.Include.NON_NULL)
	private StateEnum estado;
	
	@JsonProperty
	@JsonInclude(JsonInclude.Include.NON_NULL)
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

	public ClienteBean getIdCliente() {
		return idCliente;
	}

	public void setIdCliente(ClienteBean idCliente) {
		this.idCliente = idCliente;
	}

	public ProductoBean getIdProducto() {
		return idProducto;
	}

	public void setIdProducto(ProductoBean idProducto) {
		this.idProducto = idProducto;
	}

	public StateEnum getEstado() {
		return estado;
	}

	public void setEstado(StateEnum estado) {
		this.estado = estado;
	}

	public LocalDateTime getFechaCreacion() {
		return fechaCreacion;
	}

	public void setFechaCreacion(LocalDateTime fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}
	
}
