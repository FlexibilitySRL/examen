package com.example.core.model;

import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "sale")
@Where(clause = "active <> 0")
public class Sale {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private Date saleDate;
    @Column
    private Boolean bought;
    @OneToOne
    private Client client;
    @OneToMany
    private Set<SaleDetail> saleDetails;
    @Column
    private BigDecimal total;
    @Column
    private Boolean active;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getSaleDate() {
        return saleDate;
    }

    public void setSaleDate(Date saleDate) {
        this.saleDate = saleDate;
    }

    public Boolean getBought() {
        return bought;
    }

    public void setBought(Boolean bought) {
        this.bought = bought;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Set<SaleDetail> getSaleDetails() {
        return saleDetails;
    }

    public void setSaleDetails(Set<SaleDetail> saleDetails) {
        this.saleDetails = saleDetails;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    @Transient
    public static List<SaleDetail> getDetailsDefault() {
        List<SaleDetail> details = new ArrayList<>();
        Client client = Client.getClientDefault();

        Sale sale = new Sale();
        sale.setBought(Boolean.FALSE);
        sale.setActive(Boolean.TRUE);
        sale.setClient(client);
        sale.setTotal(new BigDecimal(100));
        sale.setId(1L);

        Product product = Product.getProductDefault();

        SaleDetail saleDetail = new SaleDetail();
        saleDetail.setActive(Boolean.TRUE);
        saleDetail.setId(1L);
        saleDetail.setProduct(product);
        saleDetail.setQuantity(10);
        saleDetail.setSubTotal(new BigDecimal(100));
        saleDetail.setSale(sale);
        details.add(saleDetail);
        return details;
    }
}
