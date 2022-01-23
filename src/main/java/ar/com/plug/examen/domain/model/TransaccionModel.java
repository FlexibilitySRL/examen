package ar.com.plug.examen.domain.model;


import javax.persistence.*;
import java.util.Date;
import ar.com.plug.examen.domain.model.ProductosModel;
import ar.com.plug.examen.domain.model.ClienteModel;

@Entity
@Table(name = "transacciones")
public class TransaccionModel {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    private Long id;

    private Date datePurch;

    @Column(unique = true, nullable = false)
    private String reference;
    
    private String estatus;
    private Double totalPrice;

    @ManyToOne(optional = false, cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private ClienteModel client;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "transacciones")
    private ProductosModel products; 


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getDatePurch() {
        return datePurch;
    }

    public void setDatePurch(Date datePurch) {
        this.datePurch = datePurch;
    }

    public String getReference() {
        return reference;
    }

    public void setReference(Double reference) {
        this.reference = reference;
    }

    public String getEstatus() {
        return estatus;
    }

    public void setEstatus(String estatus) {
        this.estatus = estatus;
    }

    public Double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public ClienteModel getClient() {
        return client;
    }

    public void setClient(ClienteModel client) {
        this.client = client;
    }

    public ProductosModel getProducts() {
        return products;
    }

    public void setProducts(ProductosModel products) {
        this.products = products;
    }


}