package ar.com.flexibility.examen.model;


import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Data
@NoArgsConstructor
@Entity
@Table(name = "order_product")
public class OrderProduct {

    @EmbeddedId
    private OrderProductId id = new OrderProductId();

    @ManyToOne
    @MapsId("orderId")
    private Order order;

    @ManyToOne
    @MapsId("productId")
    private Product product;

    private int quantity;

    @Data
    @NoArgsConstructor
    public static class OrderProductId implements Serializable {

        private static final long serialVersionUID = 1L;

        private Long orderId;
        private Long productId;

        public OrderProductId(Long orderId, Long productId) {
            this.orderId = orderId;
            this.productId = productId;
        }
    }
}
