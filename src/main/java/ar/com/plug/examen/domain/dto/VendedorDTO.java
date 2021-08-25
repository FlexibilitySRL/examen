package ar.com.plug.examen.domain.dto;

public class VendedorDTO extends PersonaDTO{
	
	private String codigo;
	
	public VendedorDTO(long id, String nombre, String apellido, String direccion, String telefono, String email, String codigo) {
		super(id, nombre, apellido, direccion, telefono, email);
		this.codigo = codigo;
	}

	public String getCodigo() {
		return codigo;
	}	
	
}
