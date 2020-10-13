package ar.com.plug.examen.constants;

public class BusinessExceptionConstants {

	//Base
	private static final String GENERAL = "General.";
	private static final String API = "Api.";
	private static final String BASE_VALIDATION = "Validation.";
	
	//Api
	private static final String CREATE = "Create.";
	private static final String DELETE = "Delete.";
	private static final String UPDATE = "Update.";
	private static final String SUCCESS = "Success";
	
	//Entities
	private static final String ENTITY = "Entity.";
	private static final String CLIENT_ENTITY = "Client.";
	private static final String SELLER_ENTITY = "Seller.";
	private static final String PURCHASE_ENTITY = "Purchase.";
	private static final String PRODUCT_ENTITY = "Product.";
	private static final String STATUS = "Status.";
	
	//Validation
	private static final String NOT_FOUND = "NotFound";
	private static final String NOT_VALID = "NotValid";
	private static final String CANT_MODIFY = "CantModify";
	private static final String CANT_DELETE = "CantDelete";
	private static final String FIELD = "Field";
	
	public static final String ENTITY_NOT_FOUND = BASE_VALIDATION + ENTITY + NOT_FOUND;
	
	public static final String UNEXPECTED_EXCEPTION_REQUEST = "UnexpectedExceptionProcessingRequest";
	
	public static final String FIELD_VALIDATION = BASE_VALIDATION + GENERAL + FIELD;
	
	public static final String CREATE_SUCCESS = API + CREATE + SUCCESS;
	public static final String UPDATE_SUCCESS = API + UPDATE + SUCCESS;
	public static final String DELETE_SUCCESS = API + DELETE + SUCCESS;
	
	public static final String STATUS_NOT_VALID = BASE_VALIDATION + STATUS + NOT_VALID;
	
	public static final String CANT_MODIFY_NOT_PENDING_PURCHASE = BASE_VALIDATION + PURCHASE_ENTITY + CANT_MODIFY;
	public static final String CANT_DELETE_NOT_PENDING_PURCHASE = BASE_VALIDATION + PURCHASE_ENTITY + CANT_DELETE;
	
	public static final String CANT_DELETE_CLIENT_PENDING_PURCHASES = BASE_VALIDATION + CLIENT_ENTITY + CANT_DELETE;
	public static final String CANT_DELETE_PRODUCT = BASE_VALIDATION + PRODUCT_ENTITY + CANT_DELETE;
	public static final String CANT_DELETE_SELLER_PENDING_PURCHASES = BASE_VALIDATION + SELLER_ENTITY + CANT_DELETE;
	
	
}
