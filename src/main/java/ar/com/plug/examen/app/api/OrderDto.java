package ar.com.plug.examen.app.api;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

@JsonRootName(value = "order")
@JsonIgnoreProperties(ignoreUnknown = true)
public class OrderDto {

   
    @JsonProperty
    private Long idCustomer;
    
    @JsonProperty
    private Long idSeller;

    
    @JsonProperty
    private List<OrderItemDto> items;


	public List<OrderItemDto> getItems() {
		return items;
	}


	public void setItems(List<OrderItemDto> items) {
		this.items = items;
	}


	public Long getIdCustomer() {
		return idCustomer;
	}


	public void setIdCustomer(Long idCustomer) {
		this.idCustomer = idCustomer;
	}


	public Long getIdSeller() {
		return idSeller;
	}


	public void setIdSeller(Long idSeller) {
		this.idSeller = idSeller;
	}

	
	
	
    
}
