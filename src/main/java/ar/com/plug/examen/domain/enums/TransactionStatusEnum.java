package ar.com.plug.examen.domain.enums;

public enum TransactionStatusEnum {

	APROBADO(0), PENDIENTE(1), RECHAZADO(2);

	private final Integer id;
	
	private TransactionStatusEnum(Integer i) {
		this.id = i;
	}

	public Integer getId() {
		return id;
	}
}
