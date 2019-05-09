package ar.com.flexibility.examen.domain.exception;


public class GenericProductException extends Exception
{

	private static final long serialVersionUID = -873605370727787575L;

	public static final String PRODUCT_ID_NOT_EXIST_FORMAT = "No existe el producto con ID=%d.";
	public static final String PRODUCT_ID_NOT_EXIST_ERROR = "No se encontró el Producto.";
	public static final String PRODUCT_TO_UPDATE_WITHOUT_CHANGES = "No hay cambios a actualizar en el Producto.";
	
	public GenericProductException(String msg)
	{
		super(msg);
	}

}