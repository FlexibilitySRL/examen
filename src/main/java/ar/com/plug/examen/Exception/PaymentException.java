package ar.com.plug.examen.Exception;

import lombok.AllArgsConstructor;

public class PaymentException extends RuntimeException{
    private String message;

    public PaymentException(String message){super(message);}
}
