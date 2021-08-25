package ar.com.plug.examen.domain.dto;

public class ClienteDTO extends PersonaDTO {
	
	private String numeroIdentificacion;	
	
	public ClienteDTO(Long id, String nombre, String apellido, String direccion,String telefono, String email, String numeroIdentificacion) {
		super(id,  nombre, apellido, direccion, telefono, email);
		this.numeroIdentificacion = numeroIdentificacion;
	}

	public String getNumeroIdentificacion() {
		return numeroIdentificacion;
	}

	public void setNumeroIdentificacion(String numeroIdentificacion) {
		this.numeroIdentificacion = numeroIdentificacion;
	}
	
}
