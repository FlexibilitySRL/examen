package ar.com.plug.examen.domain.exception;
public class NotFoundException extends RuntimeException{

    public NotFoundException(String message){
        super(message);
    }
}
