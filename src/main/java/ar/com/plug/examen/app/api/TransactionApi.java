package ar.com.plug.examen.app.api;

import java.time.LocalDateTime;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonRootName;

import lombok.Getter;
import lombok.Setter;

@JsonRootName(value = "transaction")
@JsonIgnoreProperties(ignoreUnknown = true)
@Getter
@Setter
public class TransactionApi {

	private Long id;
	@NotNull
	private Long productId;
	@NotNull
	private Long clientId;
	@NotNull
	private Long sellerId;
	@NotNull
	private Double price;
	@NotNull
	private String status;
	@NotNull
	private LocalDateTime date;
	
	@Override
	public String toString() {
		return "TransactionApi [id=" + id + ", productId=" + productId + ", clientId=" + clientId + ", sellerId="
				+ sellerId + ", price=" + price + ", status=" + status + ", date=" + date + "]";
	}
	
	
}
