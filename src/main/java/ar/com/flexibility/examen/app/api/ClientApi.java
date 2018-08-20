package ar.com.flexibility.examen.app.api;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

import javax.validation.constraints.NotNull;
import java.util.Date;

@JsonRootName(value = "product")
@JsonIgnoreProperties(ignoreUnknown = true)
public class ClientApi implements GenericApi {

    private static final long serialVersionUID = -491805240386575287L;
    @JsonProperty
    private Long id;

    @NotNull(message = "Date can not be null!")
    @JsonProperty("date_created")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd-HH:mm")
    private Date dateCreated;

    @NotNull(message = "Name can not be null!")
    @JsonProperty
    private String documentId;

    @NotNull(message = "Name can not be null!")
    @JsonProperty
    private String name;

    private ClientApi() {
        //Just to avoid public instantiation.
    }

    public ClientApi(Long id, Date dateCreated, String documentId, String name) {
        this.id = id;
        this.dateCreated = dateCreated;
        this.documentId = documentId;
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    @NotNull
    public Date getDateCreated() {
        return dateCreated;
    }

    @NotNull
    public String getDocumentId() {
        return documentId;
    }

    @NotNull
    public String getName() {
        return name;
    }

    public static ClientApiBuilder newBuilder() {
        return new ClientApiBuilder();
    }

    public static class ClientApiBuilder {
        private Long id;
        private Date dateCreated;
        private String documentId;
        private String name;

        public ClientApi.ClientApiBuilder setId(Long id) {
            this.id = id;
            return this;
        }

        public ClientApi.ClientApiBuilder setDateCreated(Date dateCreated) {
            this.dateCreated = dateCreated;
            return this;
        }

        public ClientApi.ClientApiBuilder setDocumentId(String documentId) {
            this.documentId = documentId;
            return this;
        }

        public ClientApi.ClientApiBuilder setName(String name) {
            this.name = name;
            return this;
        }

        public ClientApi build() {
            return new ClientApi(id, dateCreated, documentId, name);
        }
    }
}
