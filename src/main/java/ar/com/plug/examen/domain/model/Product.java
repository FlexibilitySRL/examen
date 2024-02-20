package ar.com.plug.examen.domain.model;

import com.sun.istack.NotNull;
import lombok.Data;

import javax.persistence.*;

@Data
@Embeddable
@Entity
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer productId;
    private String productName;
    private Double price;
    private String color;
    private String dimension;
    private String specification;
    private String menufacturer;
}
