package ar.com.plug.examen.infrastructure.db.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Email;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import ar.com.plug.examen.domain.Client;
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
@Table(name = "clients")
public class ClientEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;
    private String lastName;
    private String docNumber;

    @Column(unique = true)
    @Email
    private String email;

    @OneToMany(mappedBy = "client", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<TransactionEntity> transactions;

    public Client toClient() {
        return Client.builder()
                .id(id).name(name).lastName(lastName).docNumber(docNumber).email(email)
                .build();
    }

    public ClientEntity(Client client) {
        this.name = client.getName();
        this.lastName = client.getLastName();
        this.docNumber = client.getDocNumber();
        this.email = client.getEmail();
    }

    public ClientEntity upDate(Client client) {
        this.name = client.getName();
        this.lastName = client.getLastName();
        this.docNumber = client.getDocNumber();
        this.email = client.getEmail();
        return this;
    }

    public void addTransactions(TransactionEntity transactionEntity) {
        if (Objects.isNull(transactions)) {
            transactions = new ArrayList<>();
        }
        transactions.add(transactionEntity);
    }
}
