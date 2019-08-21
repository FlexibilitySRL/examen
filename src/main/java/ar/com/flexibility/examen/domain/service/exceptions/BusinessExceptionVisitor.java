package ar.com.flexibility.examen.domain.service.exceptions;

public interface BusinessExceptionVisitor<R> {
	public R visit(ClientDoesNotExistException exception);
	public R visit(ClientIsInAPurchaseOrderException exception);
	public R visit(ProductDoesNotExistException exception);
	public R visit(ProductIsInAPurchaseOrderException exception);
	public R visit(PurchaseOrderDoesNotExistException exception);
	public R visit(PurchaseOrderHasBeenApprovedException exception);
	public R visit(PurchaseTransactionDoesNotExistException exception);
	public R visit(InvalidUpdateException exception);
	public R visit(UnexpectedNullValueException exception);
}
