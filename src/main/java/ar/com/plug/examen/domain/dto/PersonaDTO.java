package ar.com.plug.examen.domain.dto;

public class PersonaDTO {
	
	private long id;
	private String nombre;
	private String apellido;
	private String direccion;
	private String telefono;
	private String email;
	
	public PersonaDTO(long id, String nombre, String apellido, String direccion,
			String telefono, String email) {
		this.id = id;
		this.nombre = nombre;
		this.apellido = apellido;
		this.direccion = direccion;
		this.telefono = telefono;
		this.email = email;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public String getDireccion() {
		return direccion;
	}

	public String getTelefono() {
		return telefono;
	}

	public String getEmail() {
		return email;
	}
}
