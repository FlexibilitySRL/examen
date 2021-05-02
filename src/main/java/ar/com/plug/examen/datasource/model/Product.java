package ar.com.plug.examen.datasource.model;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString(onlyExplicitlyIncluded = true)
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Product {

    @Id
    @GeneratedValue
    @ToString.Include
    @EqualsAndHashCode.Include
    Long id;

    @Column(nullable = false)
    String name;

    @OneToMany(mappedBy = "product")
    List<ProductPurchase> productPurchases;
}
