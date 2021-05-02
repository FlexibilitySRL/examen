package ar.com.plug.examen.datasource.model;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
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
    @ToString.Include
    @EqualsAndHashCode.Include
    long id;

    @Column(nullable = false)
    String name;

    @OneToMany(mappedBy = "customer")
    List<Purchase> purchases;
}
