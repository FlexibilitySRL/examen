package ar.com.plug.examen.domain.exceptions;

/**
 * Custom exception to represent the invalid empty brand exception
 * @author Pablo Marrero
 *
 */
public class EmptyBrandException extends Exception{
    public EmptyBrandException(String message){
        super(message);
    }
}
