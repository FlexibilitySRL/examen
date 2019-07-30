package ar.com.flexibility.examen.domain.model;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Objects;

@Entity
public class Purcharse {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @OneToOne
    @JoinColumn(name="shopping_list_id")
    private ShoppingList shoppingList;
    //    private LocalDateTime currentDate;
    private BigDecimal cost;

    @Enumerated()
    private PurcharseEnum state;

    public Purcharse() {    }

    public Purcharse buy(ShoppingList shoppingList, Client client) {
        //TODO persistir LocalDateTime
//        currentDate = LocalDateTime.now();
        this.shoppingList = shoppingList;

        BigDecimal cost = calculatePrice(shoppingList);

        state = PurcharseEnum.valueOf("APPROVED");

        client.addPurcharse(this);

        return this;
    }

    private BigDecimal calculatePrice(ShoppingList shoppingList) {
        cost = BigDecimal.valueOf(0);
        for (Product product : shoppingList.getProducts()) {
            cost = cost.add(product.getPrice());
        }

        return cost;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BigDecimal getCost() {
        return cost;
    }

    public void setCost(BigDecimal cost) {
        this.cost = cost;
    }

    public ShoppingList getShoppingList() {
        return shoppingList;
    }

    public void setShoppingList(ShoppingList shoppingList) {
        this.shoppingList = shoppingList;
    }

    public String getState() {
        return state.getState();
    }

    public void setState(PurcharseEnum state) {
        this.state = state;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Purcharse purcharse = (Purcharse) o;
        return Objects.equals(id, purcharse.id) &&
                Objects.equals(shoppingList, purcharse.shoppingList) &&
                Objects.equals(cost, purcharse.cost) &&
                state == purcharse.state;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, shoppingList, cost, state);
    }
}
