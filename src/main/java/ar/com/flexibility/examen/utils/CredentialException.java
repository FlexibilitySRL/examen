package ar.com.flexibility.examen.utils;

import ar.com.flexibility.examen.exception.GenericException;

public class CredentialException extends GenericException
{
    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    public static final String E001 = "E001";

    public CredentialException(String errorCode, String message, Throwable exception) {
        super(errorCode, message, exception);
    }
    
    public CredentialException(String errorCode, String message) {
        super(errorCode, message);
    }

}
