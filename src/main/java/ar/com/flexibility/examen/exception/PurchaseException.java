package ar.com.flexibility.examen.exception;

public class PurchaseException extends GenericException
{

    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    public static final String E001 = "E001";
    public static final String E002 = "E002";

    public PurchaseException(String errorCode, String message, Throwable exception) {
        super(errorCode, message, exception);
         
    }
    
    public PurchaseException(String errorCode, String message) {
        super(errorCode, message);
    }
}
