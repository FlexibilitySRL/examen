package ar.com.plug.examen.app.api;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;
import ar.com.plug.examen.domain.model.OrderStatus;

@JsonRootName(value = "requestOrderUpdateStatus")
@JsonIgnoreProperties(ignoreUnknown = true)
public class RequestOrderUpdateStatus {

    @JsonProperty
    private OrderStatus status;

	public OrderStatus getStatus() {
		return status;
	}

	public void setStatus(OrderStatus status) {
		this.status = status;
	}
 
    
}
