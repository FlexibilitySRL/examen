package ar.com.plug.examen.domain.model;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;


@Entity
@Table(name = "tbl_products")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty(message = "The code cannot be empty")
    private String code;

    @NotEmpty(message = "The name cannot be empty")
    private String name;
    private String description;

    @Positive(message = "Stock must be greater than zero")
    private Double stock;
    private String status;
    @NotNull(message = "Price cannot be zero")
    private Double price;

    @Column(name = "photo_url")
    private String photoUrl;
}
