package ar.com.plug.examen.domain.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "vendors")
@NoArgsConstructor
public class Vendor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long vendorId;

    @Column(name = "name")
    private String name;

    @Column(name = "ssn")
    private String ssn;

    @OneToMany(mappedBy = "vendor", cascade = CascadeType.ALL)
    private Set<Transaction> transactions = new HashSet<>();

    public Vendor(String name, String ssn) {
        this.name = name;
        this.ssn = ssn;
    }
}
