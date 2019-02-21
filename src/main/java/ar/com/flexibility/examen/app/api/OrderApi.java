/**
 * 
 */
package ar.com.flexibility.examen.app.api;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import ar.com.flexibility.examen.domain.model.Order;

/**
 * @author ro
 *
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class OrderApi {

	@JsonProperty
	private Long idOrder;
	@JsonProperty
	private String nameProduct;
	@JsonProperty
	private Long idProduct;
	@JsonProperty
	private Integer items;
	
	public OrderApi(Order o) {
		setIdOrder(o.getIdOrder());
		setNameProduct(o.getProduct().getName());
		setIdProduct(o.getProduct().getIdProduct());
		setItems(o.getItems());
		
	}

	public Long getIdOrder() {
		return idOrder;
	}

	public void setIdOrder(Long idOrder) {
		this.idOrder = idOrder;
	}

	public String getNameProduct() {
		return nameProduct;
	}

	public void setNameProduct(String nameProduct) {
		this.nameProduct = nameProduct;
	}

	public Long getIdProduct() {
		return idProduct;
	}

	public void setIdProduct(Long idProduct) {
		this.idProduct = idProduct;
	}

	public Integer getItems() {
		return items;
	}

	public void setItems(Integer items) {
		this.items = items;
	}
	
	

}
