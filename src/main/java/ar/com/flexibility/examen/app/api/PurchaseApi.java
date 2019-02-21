/**
 * 
 */
package ar.com.flexibility.examen.app.api;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import ar.com.flexibility.examen.domain.model.Order;
import ar.com.flexibility.examen.domain.model.Purchase;

/**
 * @author ro
 *
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class PurchaseApi {
	
	@JsonProperty
	private Long idClient;
	@JsonProperty
	private Date date;
	@JsonProperty
	private List<OrderApi> orders;
	@JsonProperty
	private Long idPurchase;

	public PurchaseApi(Purchase p) {
		setDate(p.getDateOfPurchase());
		setIdClient(p.getClient().getIdClient());
		setIdPurchase(p.getIdPurchase());
		setOrders(getOrdersApi(p.getOrders()));
	}

	private List<OrderApi> getOrdersApi(List<Order> orders) {
		return orders.stream().map(o -> new OrderApi(o)).collect(Collectors.toList());
	}

	public Long getIdPurchase() {
		return idPurchase;
	}

	public void setIdPurchase(Long idPurchase) {
		this.idPurchase = idPurchase;
	}

	public Long getIdClient() {
		return idClient;
	}

	public void setIdClient(Long idClient) {
		this.idClient = idClient;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public List<OrderApi> getOrders() {
		return orders;
	}

	public void setOrders(List<OrderApi> orders) {
		this.orders = orders;
	}

	
}
