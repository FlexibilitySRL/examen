package ar.com.plug.examen.domain.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ProductModel {

    private Long id;
    private String name;
    private String description;
    private Double unitCost;
    private Double salePrice;
    private Integer quantity;
    private Integer idStatus;

    @Override
    public String toString() {
        return "ProductModel{" +
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
