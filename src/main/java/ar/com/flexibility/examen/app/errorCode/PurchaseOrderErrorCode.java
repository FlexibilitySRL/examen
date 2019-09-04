package ar.com.flexibility.examen.app.errorCode;

public enum PurchaseOrderErrorCode {
	P_ORDER_NOT_FOUND("The Purchase order was not found."),
	P_ORDER_INVALID_CUSTOMER_ID("The Customer id for the Order is invalid."),
	P_ORDER_INVALID_PRODUCT_ID("The Product id for the Order is invalid."),
	P_ORDER_INVALID_AMOUNT("The Order amount is invalid.");
	
	private String description;
	
	private PurchaseOrderErrorCode(String description) {
		this.description = description;
	}
	
	public String getDescription() {
		return this.description;
	}
}
