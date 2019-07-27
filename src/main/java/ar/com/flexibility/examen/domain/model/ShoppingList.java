package ar.com.flexibility.examen.domain.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
public class ShoppingList {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "shoppingList_id")
    private List<Product> products;

    public ShoppingList() {
        products = new ArrayList<>();
    }

    public void add(Product product) {
        products.add(product);
    }

    public void delete(Product product) {
        for (Product producti : products) {
            if (product.equals(producti)) {
                products.remove(producti);
            }
        }
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

}
