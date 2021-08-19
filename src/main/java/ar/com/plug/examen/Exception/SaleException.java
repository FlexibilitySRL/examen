package ar.com.plug.examen.Exception;

public class SaleException extends RuntimeException{
    private String message;

    public SaleException(String message){super(message);}
}
