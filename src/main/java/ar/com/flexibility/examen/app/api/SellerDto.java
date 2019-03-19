package ar.com.flexibility.examen.app.api;

import ar.com.flexibility.examen.domain.model.Seller;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class SellerDto {

    @JsonProperty
    private long id;

    @JsonProperty
    private String lastName;

    @JsonProperty
    private String firstName;

    public SellerDto() {

    }

    public SellerDto(Seller seller) {
        id = seller.getId();
        firstName = seller.getFirstName();
        lastName = seller.getLastName();
    }

    public Seller toDomain() {
        final Seller seller = new Seller();
        seller.setId(getId());
        seller.setFirstName(getFirstName());
        seller.setLastName(getLastName());
        return seller;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
}

