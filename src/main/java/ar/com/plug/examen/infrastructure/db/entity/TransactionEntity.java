package ar.com.plug.examen.infrastructure.db.entity;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;

import ar.com.plug.examen.domain.Transaction;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
@Entity
@Table(name = "transactions")
public class TransactionEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;

    private boolean approved;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonBackReference
    private ClientEntity client;

    private Double total;
    private LocalDateTime date;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "transaccion_id")
    private List<ItemProductEntity> items;

    public Transaction toTransaction() {
        return Transaction.builder()
                .id(id)
                .approved(approved)
                .clientId(client.getId())
                .total(total)
                .date(date)
                .items(items.stream().map(ItemProductEntity::toItemProduct).collect(Collectors.toList()))
                .build();
    }

}
