package ar.com.plug.examen.domain.model;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Column;
import javax.persistence.Entity;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Setter
@Getter
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idProduct;
    @Column
    private String descriptionProduct;
    @Column
    private String category;
    @Column
    private BigDecimal price;
    @Column
    @CreationTimestamp
    private LocalDateTime datePurchase;
    @Column
    private LocalDateTime updateDate;
    @Column
    private Integer stock;
}
