package ar.com.plug.examen.domain.exception;

public class ServiceException extends RuntimeException {
	
	private static final long serialVersionUID = -5648477811255506682L;

	public ServiceException(String errorMessage) {
        super(errorMessage);
    }
}