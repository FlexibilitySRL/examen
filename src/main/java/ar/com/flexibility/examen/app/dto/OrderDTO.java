package ar.com.flexibility.examen.app.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashMap;
import java.util.Map;


@ApiModel(value = "OrderDTO")
@Getter @Setter @NoArgsConstructor
public class OrderDTO {

    @JsonIgnore
    private Long id;
    //PENDING, APPROVED, REJECTED, CANCELLED
    private int status;
    private Long customerID;
    private Long sellerID;
    private Map<Long, Integer> products = new HashMap<>();

    public OrderDTO(Long id,int status, Long customerID, Long sellerID, Map<Long, Integer> products) {
        this.id = id;
        this.status = status;
        this.customerID = customerID;
        this.sellerID = sellerID;
        this.products = products;
    }
}
