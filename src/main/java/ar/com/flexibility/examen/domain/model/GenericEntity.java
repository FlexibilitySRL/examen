package ar.com.flexibility.examen.domain.model;

/**
 * Generic database entity.
 */
public interface GenericEntity {

    Long getId();
    Boolean isDeleted();
    void setDeleted(Boolean deleted);
}
