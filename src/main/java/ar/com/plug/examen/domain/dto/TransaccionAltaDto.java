package ar.com.plug.examen.domain.dto;

import javax.validation.constraints.NotNull;

public class TransaccionAltaDto {

	@NotNull
	private Integer idCliente;

	public Integer getIdCliente() {
		return idCliente;
	}

	public void setIdCliente(Integer idCliente) {
		this.idCliente = idCliente;
	}

}
