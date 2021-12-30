package ar.com.plug.examen.exception;

public class MicroserviceErrorException extends Exception {

    public MicroserviceErrorException(String message) {
        super(message);
    }

    public MicroserviceErrorException(String message, Throwable cause) {
        super(message, cause);
    }
}
