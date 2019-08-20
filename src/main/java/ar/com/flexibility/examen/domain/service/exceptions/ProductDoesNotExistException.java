package ar.com.flexibility.examen.domain.service.exceptions;

public final class ProductDoesNotExists extends UserServiceException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2501660762626977014L;
	
	private long productId;
	
	public ProductDoesNotExists(long productId) {
		this.productId = productId;
	}
	
	public long getProductId() {
		return this.productId;
	}
}
