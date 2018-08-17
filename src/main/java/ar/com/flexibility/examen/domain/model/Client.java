package ar.com.flexibility.examen.domain.model;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.Type;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Entity
@Table(
        name = "client"
)
public class Client implements GenericEntity {

    @Id
    private Long id;

    @Column(name = "date_created", nullable = false)
    @Type(type="java.util.Date")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private Date dateCreated;

    @Column(name = "document_id", nullable = false)
    @ColumnDefault("false")
    private String documentId;

    @Column(name = "name", nullable = false)
    @ColumnDefault("false")
    private String name;

    @Column(nullable = false)
    @ColumnDefault("false")
    private Boolean deleted = false;

    public Client(){}

    public Client(Long id, Date dateCreated, String documentId, String name) {
        this.id = id;
        this.dateCreated = dateCreated;
        this.documentId = documentId;
        this.name = name;
    }

    @Override
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }

    public String getDocumentId() {
        return documentId;
    }

    public void setDocumentId(String documentId) {
        this.documentId = documentId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean isDeleted() {
        return deleted;
    }

    @Override
    public void setDeleted(Boolean deleted) {
        this.deleted = deleted;
    }
}
