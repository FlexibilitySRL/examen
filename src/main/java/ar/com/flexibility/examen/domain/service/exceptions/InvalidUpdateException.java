package ar.com.flexibility.examen.domain.service.exceptions;

public final class InvalidUpdateException extends BusinessException {
	/**
	 * 
	 */
	private static final long serialVersionUID = 5284477812252273073L;
	
	private long objectId;
	
	public InvalidUpdateException(long objectId) {
		this.objectId = objectId;
	}
	
	public long getObjectId() {
		return this.objectId;
	}

	@Override
	public <R> R accept(BusinessExceptionVisitor<R> visitor) {
		// TODO Auto-generated method stub
		return null;
	}
	
	
}
