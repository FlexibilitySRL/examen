package ar.com.plug.examen.domain.service;

public interface Messages {

	public static final String MSG_ADDING_NEW_CLIENT = "Adding a new client";
	
	public static final String MSG_EXCEPTION_DATA_REQUIRED = "Missing required data: %1$s";
	
	public static final String MSG_EXCEPTION_INVALID_TRANSACTION_STATUS = "Provided transaction status is invalid: %1$s";
	
	public static final String MSG_EXCEPTION_INVALID_PRODUCT_QUANTITY = "Provided product quantity is invalid: %1$s"; 

	public static final String MSG_EXCEPTION_TRANSACTION_STATUS_ALREADY = "Transaction status is already: %1$s";
	
	public static final String MSG_EXCEPTION_UNABLE_TO_FIND = "Unable to find requested %1$s";
	
	public static final String MSG_FOUND = "Found!";
	
	public static final String MSG_PREPARING_DELETION = "Preparing for deletion of a %1$s ...";

	public static final String MSG_PREPARING_PERSISTENCE = "Preparing for persistence of a %1$s ...";

	public static final String MSG_PREPARING_UPDATE = "Preparing for update of a %1$s ...";

	public static final String MSG_SEARCHING = "Searching %1$s";
	
	public static final String MSG_SEARCHING_REQUESTED_DATA = "Searching requested data";
	
	public static final String MSG_SUCCESS = "Success!";

	public static final String MSG_SUCCESSFULLY_CREATED = "%1$s successfully created with id: %2$s!";

	public static final String MSG_SUCCESSFULLY_DELETED = "%1$s successfully deleted!";
	
	public static final String MSG_SUCCESSFULLY_UPDATED = "%1$s with id: %2$s - successfully updated!";
	
	public static final String MSG_VALIDATING_PROVIDED_DATA = "Validating provided data ....";
	
	public static final String MSG_VALIDATION_SUCCESSFUL = "Successful validation!";

}
