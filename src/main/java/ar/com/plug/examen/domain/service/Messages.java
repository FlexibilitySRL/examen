package ar.com.plug.examen.domain.service;

public interface Messages {

	public static final String MSG_ADDING_NEW_CLIENT = "Adding a new client";
	
	public static final String MSG_EXCEPTION_DATA_REQUIRED = "Missing required data: %1$s";
	
	public static final String MSG_EXCEPTION_INVALID_TRANSACTION_STATUS = "Provided transaction status is invalid: %1$s";
	
	public static final String MSG_EXCEPTION_INVALID_PRODUCT_QUANTITY = "Provided product quantity is invalid: %1$s"; 

	public static final String MSG_EXCEPTION_TRANSACTION_STATUS_ALREADY = "Transaction status is already: %1$s";
	
	public static final String MSG_EXCEPTION_UNABLE_TO_FIND = "Unable to find requested %1$s";
	
	public static final String MSG_SEARCHING_REQUESTED_DATA = "Searching requested data";

	public static final String MSG_SUCCESSFULLY_CREATED = "%1$s successfully created with id: %2$s!";

	public static final String MSG_SUCCESSFULLY_DELETED = "%1$s successfully deleted!";
	
	public static final String MSG_SUCCESSFULLY_UPDATED = "%1$s with id: %2$s - successfully updated!";
	
	public static final String MSG_VALIDATING_PROVIDED_DATA = "Validating provided data ....";

}
