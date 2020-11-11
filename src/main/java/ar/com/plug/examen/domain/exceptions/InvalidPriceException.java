package ar.com.plug.examen.domain.exceptions;

/**
 * Custom exception to represent the invalid price exception
 * @author Pablo Marrero
 *
 */
public class InvalidPriceException extends Exception{
    public InvalidPriceException(String message){
        super(message);
    }
}
