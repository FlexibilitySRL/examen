package ar.com.flexibility.examen.domain.model;

public interface GenericEntity {

    Long getId();
    Boolean isDeleted();
    void setDeleted(Boolean deleted);
}
