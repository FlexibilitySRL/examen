package ar.com.flexibility.examen.domain.exception;


public class GenericProductException extends Exception {

    public static final String PRODUCT_ID_NOT_EXIST = "No existe el producto con ID=%d.";
    public static final String PRODUCT_TO_UPDATE_WITHOUT_CHANGES = "No hay cambios a actualizar en el Producto.";
    public static final String PRODUCT_ID_MUST_BE_NULL = "El ID de producto debe ser nulo";

    public GenericProductException(String msg) {
        super(msg);
    }

}