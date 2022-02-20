package ar.com.plug.examen.domain.model;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Column;
import javax.persistence.Entity;
import java.time.LocalDateTime;

@Setter
@Getter
@Entity
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idCustomer;
    @Column
    private String documentNumber;
    @Column
    private String name;
    @Column
    private String lastName;
    @Column
    private String email;
    @CreationTimestamp
    private LocalDateTime datePurchase;
    @Column
    private LocalDateTime updateDate;
    @Column
    private String phone;
}
