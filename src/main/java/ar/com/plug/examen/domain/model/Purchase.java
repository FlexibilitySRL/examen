package ar.com.plug.examen.domain.model;

import javax.persistence.*;
import java.util.Set;
    
@Entity
@Table(name = "purchase")
public class Purchase {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    private Long id;

    @OneToMany(mappedBy = "purchase", cascade = CascadeType.ALL)
    private Set<ProductSold> productos_sold;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="dni")
    private Customer customer;

    private Float total;
    private boolean aprobada;


    public Long getId() {
        return id;
    }

    public Float getTotal() {
        return total;
    }


    public void setTotal(Float total) {
        this.total = total;
    }


    public boolean isAprobada() {
        return aprobada;
    }


    public void setAprobada(boolean aprobada) {
        this.aprobada = aprobada;
    }

}
