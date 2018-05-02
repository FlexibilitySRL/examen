package ar.com.flexibility.examen.exception;

public abstract class GenericException extends Exception {
	private String errorCode;

	public GenericException(String errorCode, String message) {
		super(message);
		this.errorCode = errorCode;
	}

	public GenericException(String errorCode, String message, Throwable exception) {
		super(message, exception);
		this.errorCode = errorCode;
	}
	
	public String getErrorCode() {
		return errorCode;
	}
	
}
