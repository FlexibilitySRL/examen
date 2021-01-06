package ar.com.plug.examen.domain.model;


import lombok.Getter;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "Purchase")
@Getter
public class Purchase {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name="id_product", nullable=false)
    private Product idProduct;
    @ManyToOne
    @JoinColumn(name="id_customer", nullable=false)
    private Customer idCustomer;
    private Long idWorker;
    private Date created;

    private Purchase(){}

    public static class Builder {

        private Purchase pruchase;

        public Builder() {
            pruchase = new Purchase();
        }

        public Builder withId(final Long id) {
            pruchase.id = id;
            return this;
        }

        public Builder withIdProduct(final Product idProduct){
            pruchase.idProduct = idProduct;
            return this;
        }
        public Builder withIdCustomer(final Customer idCustomer) {
            pruchase.idCustomer = idCustomer;
            return this;
        }
        public Builder withIdWorker(final Long idWorker) {
            pruchase.idWorker = idWorker;
            return this;
        }


        public Builder withCreated(final Date created) {
            pruchase.created = created;
            return this;
        }


        public Purchase build(){
            return pruchase;
        }
    }

}
