package ar.com.plug.examen.domain.model;

import ar.com.plug.examen.domain.exceptions.InvalidTotalPurhcaseException;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Class to represent the Purchase
 * @author Pablo Marrero
 *
 */

@Entity
@Table(name = "purchase")
@ApiModel("Model purchase")
public class Purchase {
    public Purchase(){}

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    @ApiModelProperty(value = "the purchase's id", required = true)
    private Long id;

    @Column(name ="purchase_date")
    @ApiModelProperty(value = "the purchase's date")
    private LocalDateTime purchaseDate;

    @Column(name="total")
    @ApiModelProperty(value = "the purchase's total", required = true)
    private Double total= 0d;

    @JoinColumn(name = "client_id")
    @ManyToOne(fetch = FetchType.EAGER)
    @ApiModelProperty(value = "the purchase's Client", required = true)
    private Client client;

    @JoinColumn(name="item_id")
    @OneToMany
    @ApiModelProperty(value = "the item purchase's list")
    private List<ItemPurchase> items;


    public Purchase(Double total, Client client) throws InvalidTotalPurhcaseException {
        this.purchaseDate = LocalDateTime.now();
        this.validateTotal(total);
        this.client = client;
        this.items = new ArrayList<>();
    }

    private void validateTotal(Double total) throws InvalidTotalPurhcaseException {
        if (total <= 0.0){
            throw new InvalidTotalPurhcaseException("The total must be higher than 0.");
        }
        this.total = total;
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

    public List<ItemPurchase> getItems() {
        return items;
    }
}
