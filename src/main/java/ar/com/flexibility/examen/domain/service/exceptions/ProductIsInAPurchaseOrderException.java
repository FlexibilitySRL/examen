package ar.com.flexibility.examen.domain.service.exceptions;

public final class ProductIsInAPurchaseOrderException extends UserServiceException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5374968146221254671L;

	private long productId;
	
	public ProductIsInAPurchaseOrderException(long productId) {
		this.productId = productId;
	}
}
