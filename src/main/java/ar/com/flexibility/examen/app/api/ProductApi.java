package ar.com.flexibility.examen.app.api;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

import javax.validation.constraints.NotNull;
import java.util.Date;

@JsonRootName(value = "product")
@JsonIgnoreProperties(ignoreUnknown = true)
public class ProductApi {

    @JsonProperty
    private Long id;

    @NotNull(message = "Date can not be null!")
    @JsonProperty("date_created")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd-HH:mm")
    private Date dateCreated;

    @NotNull(message = "Name can not be null!")
    @JsonProperty
    private String name;

    private ProductApi() {
        //Just to avoid public instantiation
    }

    public ProductApi(Long id, @NotNull Date dateCreated, @NotNull String name) {
        this.id = id;
        this.dateCreated = dateCreated;
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public Date getDateCreated() {
        return dateCreated;
    }

    public String getName() {
        return name;
    }

    public static ProductApiBuilder newBuilder() {
        return new ProductApiBuilder();
    }

    public static class ProductApiBuilder {
        private Long id;
        private Date dateCreated;
        private String name;

        public ProductApi.ProductApiBuilder setId(Long id) {
            this.id = id;
            return this;
        }

        public ProductApi.ProductApiBuilder setDateCreated(Date dateCreated) {
            this.dateCreated = dateCreated;
            return this;
        }

        public ProductApi.ProductApiBuilder setName(String name) {
            this.name = name;
            return this;
        }

        public ProductApi build() {
            return new ProductApi(id, dateCreated, name);
        }
    }
}