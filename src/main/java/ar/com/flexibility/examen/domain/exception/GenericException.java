package ar.com.flexibility.examen.domain.exception;


public class GenericException extends Exception
{
	private static final long serialVersionUID = -873605370727787575L;

	public static final String GENERAL_ID_NOT_EXIST_FORMATED = "No existe el ID=%d.";
	public static final String GENERAL_NOT_CHANGES_TO_MADE = "No hay cambios a actualizar.";
	
	public GenericException(String msg)
	{
		super(msg);
	}

}