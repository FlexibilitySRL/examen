package ar.com.plug.examen.domain.constants;

public final class ErrorConstants {
    public static final String INVALID_PARAMETERS_ERROR_CODE = "PARAMETROS_INVALIDOS/VACIOS";
    public static final String INVALID_PRODUCT_FIELD = "Los siguientes parametros son obligatorios: descriptionProduct, category, price Y stock por favor revisar ";
    public static final String PRODUCT_NOT_FOUND_ERROR_CODE = "PRODUCTO_NO_ENCONTRADO";
    public static final String PRODUCT_NOT_FOUND_ERROR_MESSAGE = "El producto ingresado no fue encontrado en base de datos";
    public static final String API_ERROR = "Se ha producido un error inesperado en el consumo de la aplicación";
    public static final String CUSTOMER_NOT_FOUND_ERROR_CODE = "CLIENTE_NO_ENCONTRADO";
    public static final String CUSTOMER_NOT_FOUND_ERROR_MESSAGE = "El cliente ingresado no fue encontrado en base de datos";
    public static final String SELLER_NOT_FOUND_ERROR_CODE = "VENDEDOR_NO_ENCONTRADO";
    public static final String SELLER_NOT_FOUND_ERROR_MESSAGE = "El vendedor ingresado no fue encontrado en base de datos";
    public static final String SAVE_SUCCESS = "Se guardo correctamente el registro con id ";
    public static final String ERROR_DATA_EMPTY = "Datos obligatorios no ingresados ";
    public static final String ERROR_UNKNOW = "Se presento un error desconocido con la aplicación, por favor revise ";
    public static final String ERROR_EXIST_PRODUCT = "Ya existe el producto con categoria y nombre de producto ";
    public static final String ERROR_EMPTY_ID = "No existe el registro con id  ";
    public static final String DELETE_SUCCESS = "Se elimino correctamente el registro con id ";
    public static final String EDIT_SUCCESS = "Se edito correctamente el registro con id ";
    public static final String ERROR_EXIST_CUSTOMER = "Ya existe el cliente con numero de identificación ";
    public static final String GET_PURCHASE_BD = "Se Consultaron correctamente las transacciones de compras ";
    public static final String APPROVE_PURCHASE = "Se aprobo la compra con id ";
    public static final String ERROR_EXIST_SELLER = "Ya existe el vendedor con numero de identificación ";
    public static final String PRODUCT_FOUND_ERROR_CODE = "PRODUCTO_ENCONTRADO";
    public static final String PRODUCT_FOUND_ERROR_MESSAGE = "El producto ingresado ya existe en base de datos";
    public static final String PRODUCT_FOUND_RELATION_ERROR_CODE = "PRODUCTO_RELACION_ENCONTRADA";
    public static final String PRODUCT_FOUND_RELATION_ERROR_MESSAGE = "No se puede eliminar el producto porque esta relacionado con la tabla compras";
    public static final String INVALID_CUSTOMER_FIELD = "Los siguientes parametros son obligatorios: name, lastName, documentNumber, email y phone  por favor revisar ";
    public static final String CUSTOMER_FOUND_ERROR_CODE = "CLIENTE_ENCONTRADO";
    public static final String CUSTOMER_FOUND_ERROR_MESSAGE = "El cliente ingresado ya existe en base de datos";
    public static final String CUSTOMER_FOUND_RELATION_ERROR_CODE = "CLIENTE_RELACION_ENCONTRADA";
    public static final String CUSTOMER_FOUND_RELATION_ERROR_MESSAGE = "No se puede eliminar el cliente porque esta relacionado con la tabla compras";
    public static final String SELLER_FOUND_ERROR_CODE = "VENDEDOR_ENCONTRADO";
    public static final String SELLER_FOUND_ERROR_MESSAGE = "El vendedor ingresado ya existe en base de datos";
    public static final String SELLER_FOUND_RELATION_ERROR_CODE = "VENDEDOR_RELACION_ENCONTRADA";
    public static final String SELLER_FOUND_RELATION_ERROR_MESSAGE = "No se puede eliminar el vendedor porque esta relacionado con la tabla compras";
    public static final String INVALID_PURCHASE_FIELD = "Los siguientes parametros son obligatorios: voucher, taxes, amount,idCustomer,idSeller,idProduct";

}
