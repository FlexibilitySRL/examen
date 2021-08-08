package ar.com.plug.examen.domain.util;

public enum EstadoFactura {
	
	EN_ESPERA("En espera de aprobación"),
	APROBADA("Aprobada"),
	CANCELADA("Cancelada");
	
	EstadoFactura(String d) {
		this.description = d; 
	}

	private String description;

	public String getDescription() {
		return description;
	}

}
