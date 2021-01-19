import java.util.ArrayList;

public class Cliente {
	private long clinteId;
	private String nombre;
	private String apellido;
	ArrayList <Cliente> listCliente = new ArrayList<Cliente>();
	
	public long getClinteId() {
		return clinteId;
	}
	public void setClinteId(long clinteId) {
		this.clinteId = clinteId;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getApellido() {
		return apellido;
	}
	public void setApellido(String apellido) {
		this.apellido = apellido;
	}
	
	
	public void agregarCliente(Cliente e) 
	{
		
		
		listCliente.add(e);
		
		
	}
	public void eliminarCliente (Cliente e) {
		
		listCliente.remove(e);
		
	} 
	
}

