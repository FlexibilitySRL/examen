package ar.com.plug.examen.domain.util;

public enum EstadoTransaccion {
	
	EN_ESPERA("En espera de aprobación"),
	APROBADA("Aprobada"),
	CANCELADA("Cancelada");
	
	EstadoTransaccion(String d) {
		this.description = d; 
	}

	private String description;

	public String getDescription() {
		return description;
	}

}
