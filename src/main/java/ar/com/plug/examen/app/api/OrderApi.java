package ar.com.plug.examen.app.api;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderApi {
    private Long clientId;
    private Long traderId;
    private List<OrderItemApi> orderItems;
}
