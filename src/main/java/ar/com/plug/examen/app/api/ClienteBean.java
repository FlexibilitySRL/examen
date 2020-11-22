package ar.com.plug.examen.app.api;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ClienteBean {

	@JsonProperty
	@JsonInclude(JsonInclude.Include.NON_NULL)
	private Long idCliente;
	
	@JsonProperty
	@JsonInclude(JsonInclude.Include.NON_NULL)
	private String nombreCliente;
	
	@JsonProperty
	@JsonInclude(JsonInclude.Include.NON_NULL)
	private String apellidoCliente;
	
	@JsonProperty
	@JsonInclude(JsonInclude.Include.NON_NULL)
	private String tipoDocumento;
	
	@JsonProperty
	@JsonInclude(JsonInclude.Include.NON_NULL)
	private String numeroDocumento;
	
	@JsonProperty
	@JsonInclude(JsonInclude.Include.NON_NULL)
	private LocalDateTime fechaCreacion;

	public Long getIdCliente() {
		return idCliente;
	}

	public void setIdCliente(Long idCliente) {
		this.idCliente = idCliente;
	}

	public String getNombreCliente() {
		return nombreCliente;
	}

	public void setNombreCliente(String nombreCliente) {
		this.nombreCliente = nombreCliente;
	}

	public String getApellidoCliente() {
		return apellidoCliente;
	}

	public void setApellidoCliente(String apellidoCliente) {
		this.apellidoCliente = apellidoCliente;
	}

	public String getTipoDocumento() {
		return tipoDocumento;
	}

	public void setTipoDocumento(String tipoDocumento) {
		this.tipoDocumento = tipoDocumento;
	}

	public String getNumeroDocumento() {
		return numeroDocumento;
	}

	public void setNumeroDocumento(String numeroDocumento) {
		this.numeroDocumento = numeroDocumento;
	}

	public LocalDateTime getFechaCreacion() {
		return fechaCreacion;
	}

	public void setFechaCreacion(LocalDateTime fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}
	
	
}
