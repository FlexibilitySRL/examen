package ar.com.plug.examen.domain.model;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * Class to represent the Purchase
 * @author Pablo Marrero
 *
 */

@Entity
@Table(name = "purchase")
public class Purchase {
    public Purchase(){}

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name ="purchaseDate")
    private LocalDateTime purchaseDate;

    @Column(name="total")
    private Double total;

    @JoinColumn(name = "id_client")
    @ManyToOne(fetch = FetchType.EAGER)
    private Client client;

    public Purchase(Double total, Client client){
        this.purchaseDate = LocalDateTime.now();
        this.total = total;
        this.client = client;
    }

    public Long getId() {
        return id;
    }

    public LocalDateTime getPurchaseDate() {
        return purchaseDate;
    }

    public Double getTotal() {
        return total;
    }

    public Client getClient() {
        return client;
    }



}
