package ar.com.plug.examen.domain.exceptions;

/**
 * Custom exception to represent the invalid empty name exception
 * @author Pablo Marrero
 *
 */

public class EmptyNameException extends Exception{
    public EmptyNameException(String message){
        super(message);
    }
}
