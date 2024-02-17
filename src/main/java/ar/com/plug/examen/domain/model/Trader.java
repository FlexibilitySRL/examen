package ar.com.plug.examen.domain.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "trader")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Trader {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @OneToMany(mappedBy = "trader", cascade = CascadeType.ALL)
    private List<Order> orders;

    @Override
    public String toString() {
        return "Trader{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
