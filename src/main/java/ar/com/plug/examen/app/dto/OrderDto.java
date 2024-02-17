package ar.com.plug.examen.app.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderDto {
    private String orderNumber;
    private Date dateCreated;
    private Date dateUpdated;
    private String status;
    private ClientDto client;
    private TraderDto trader;
    private List<OrderItemDto> orderItems;
}
