package ar.com.flexibility.examen.domain.service.exceptions;

public final class ClientDoesNotExistException extends UserServiceException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -463083335787911832L;

	private long clientId;
	
	public ClientDoesNotExistException(long clientId) {
		this.clientId = clientId;
	}
	
	public long getClientId() {
		return this.clientId;
	}
}
