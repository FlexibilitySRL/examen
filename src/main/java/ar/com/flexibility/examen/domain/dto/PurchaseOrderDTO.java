package ar.com.flexibility.examen.domain.dto;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import ar.com.flexibility.examen.domain.base.BaseDTO;
import ar.com.flexibility.examen.domain.model.PurchaseOrderStatus;

public class PurchaseOrderDTO extends BaseDTO {

	private static final long serialVersionUID = 1L;

	private List<ProductDTO> products;
	private CustomerDTO customer;
	private PurchaseOrderStatus status;
	private BigDecimal amount;
	private Date creationDateTime;

	public PurchaseOrderDTO() {
	}

	public PurchaseOrderDTO(List<ProductDTO> products, CustomerDTO customer, PurchaseOrderStatus status,
			BigDecimal amount) {
		super();
		this.setProducts(products);
		this.setCustomer(customer);
		this.setStatus(status);
		this.setAmount(amount);
	}

	public List<ProductDTO> getProducts() {
		return products;
	}

	public void setProducts(List<ProductDTO> products) {
		this.products = products;
	}

	public CustomerDTO getCustomer() {
		return customer;
	}

	public void setCustomer(CustomerDTO customer) {
		this.customer = customer;
	}

	public PurchaseOrderStatus getStatus() {
		return status;
	}

	public void setStatus(PurchaseOrderStatus status) {
		this.status = status;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public Date getCreationDateTime() {
		return creationDateTime;
	}

	public void setCreationDateTime(Date creationDateTime) {
		this.creationDateTime = creationDateTime;
	}

}
