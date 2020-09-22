package ar.com.flexibility.examen.domain.dto;

import java.util.List;

public class PurchaseRequestDTO {

	Long idCustomer;
	
	List<ProductsRequestDTO> products;

	public Long getIdCustomer() {
		return idCustomer;
	}

	public void setIdCustomer(Long idCustomer) {
		this.idCustomer = idCustomer;
	}

	public List<ProductsRequestDTO> getProducts() {
		return products;
	}

	public void setProducts(List<ProductsRequestDTO> products) {
		this.products = products;
	}
	
	
}
