package ar.com.plug.examen.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name="product")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="id")
    private Integer id;

    @NotBlank(message="product name cannot be empty")
    @Column(name = "name", nullable = false)
    private String name;

    @Column(name="description")
    private String description;

    @Column(name="unit_cost")
    private Double unitCost;

    @Column(name="sale_price")
    private Double salePrice;

    @Column(name="quantity")
    private Integer quantity;

    @Column(name="status")
    private Integer idStatus;

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", unitCost='" + unitCost + '\'' +
                ", salePrice='" + salePrice + '\'' +
                ", quantity='" + quantity + '\'' +
                ", idStatus='" + idStatus + '\'' +
                '}';
    }

}
