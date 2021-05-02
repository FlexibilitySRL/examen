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
public class Customer {

    @Id
    @GeneratedValue
    @ToString.Include
    @EqualsAndHashCode.Include
    Long id;

    @Column(nullable = false)
    String name;

    @OneToMany(mappedBy = "customer")
    List<Purchase> purchases;
}
