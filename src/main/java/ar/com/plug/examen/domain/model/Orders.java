package ar.com.plug.examen.domain.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Entity
public class Orders {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer orderId;
    private LocalDateTime date;
    private String orderStatus;

    @JsonIgnore
    @ManyToOne(cascade = CascadeType.ALL)
    private Customer customer;

    @Embedded
    @ElementCollection
    private List<Product> productList;
}
