package ar.com.flexibility.examen.app.errorCode;

public enum CustomerErrorCode {
	CUSTOMER_NOT_FOUND("The Customer was not found."), 
	CUSTOMER_INVALID_CUIT("The Customer cuit is invalid.");

	private String description;

	public String getDescription() {
		return this.description;
	}

	private CustomerErrorCode(String description) {
		this.description = description;
	}

}