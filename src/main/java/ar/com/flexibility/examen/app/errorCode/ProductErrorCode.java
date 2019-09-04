package ar.com.flexibility.examen.app.errorCode;

public enum ProductErrorCode {
	PRODUCT_NOT_FOUND("The Product was not found."),
	PRODUCT_INVALID_NAME("The Product name is invalid."),
	PRODUCT_INVALID_CODE("The Product code is invalid.");
	
	private String description;
	
	private ProductErrorCode(String description) {
		this.description = description;
	}
	
	public String getDescription() {
		return this.description;
	}

}
