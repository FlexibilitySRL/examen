package ar.com.flexibility.examen.domain.dto;

public class ProductsRequestDTO {
	
	private Long idProduct;
	
	private Long count;

	public Long getIdProduct() {
		return idProduct;
	}

	public void setIdProduct(Long idProduct) {
		this.idProduct = idProduct;
	}

	public Long getCount() {
		return count;
	}

	public void setCount(Long count) {
		this.count = count;
	}
}
