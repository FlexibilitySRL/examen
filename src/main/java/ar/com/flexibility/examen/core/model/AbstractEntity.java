package ar.com.flexibility.examen.core.model;

import javax.persistence.*;

/**
 *
 * Todas las entidades persistente deben heredar de esta clase.
 *
 */
@MappedSuperclass
public abstract class AbstractEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    protected Integer  id;
    @Version
    @Column(name="version")
    protected Integer version = 0;

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }
}
