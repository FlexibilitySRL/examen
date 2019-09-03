package ar.com.flexibility.examen.domain.dto;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import ar.com.flexibility.examen.domain.base.BaseDTO;
import ar.com.flexibility.examen.domain.model.TransactionStatus;

public class TransactionDTO extends BaseDTO {

	private static final long serialVersionUID = 1L;

	private List<ProductDTO> products;
	private CustomerDTO customer;
	private TransactionStatus status;
	private BigDecimal amount;
	private Date creationDateTime;

	public TransactionDTO() {
	}

	public TransactionDTO(List<ProductDTO> products, CustomerDTO customer, TransactionStatus status,
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

	public TransactionStatus getStatus() {
		return status;
	}

	public void setStatus(TransactionStatus status) {
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
