package ar.com.flexibility.examen.domain.model;

public enum Aprobacion {
APROBADA, DESAPROBADA, EN_PROCESO;

public Aprobacion aprobarVenta() {
	return APROBADA;
}
public Aprobacion desaprobarVenta() {
	return DESAPROBADA;
}
}
