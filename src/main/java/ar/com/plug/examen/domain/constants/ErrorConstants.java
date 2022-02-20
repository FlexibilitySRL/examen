package ar.com.plug.examen.domain.constants;

public final class ErrorConstants {
    public static final String INVALID_PRODUCT_PARAMETERS_ERROR_CODE = "PARAMETROS_INVALIDOS/VACIOS";
    public static final String INVALID_PRODUCT_FIELD = "Los siguientes parametros son obligatorios: descriptionProduct, category, price Y stock por favor revisar ";
    public static final String PRODUCT_NOT_FOUND_ERROR_CODE = "PRODUCTO_NO_ENCONTRADO";
    public static final String PRODUCT_NOT_FOUND_ERROR_MESSAGE = "El producto ingresado no fue encontrado en base de datos";
    public static final String API_ERROR = "Se ha producido un error inesperado en el consumo de la aplicación";
}
