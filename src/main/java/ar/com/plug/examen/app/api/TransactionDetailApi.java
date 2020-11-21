package ar.com.plug.examen.app.api;

public class TransactionDetailApi {

	private Long id;

	private ProductApi product;

	private int quantity;

	public TransactionDetailApi() { }
	
	public TransactionDetailApi(Long id, ProductApi product, int quantity) {
		this.id = id;
		this.product = product;
		this.quantity = quantity;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public ProductApi getProduct() {
		return product;
	}

	public void setProduct(ProductApi product) {
		this.product = product;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

}