package ar.com.flexibility.examen.app.api;

import ar.com.flexibility.examen.domain.model.Client;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ClientDto {

    @JsonProperty
    private long id;

    @JsonProperty
    private String firstName;

    @JsonProperty
    private String lastName;

    public ClientDto() {

    }

    public ClientDto(Client client) {
        if(client != null) {
            id = client.getId();
            firstName = client.getFirstName();
            lastName = client.getLastName();
        }
    }

    public Client toDomain() {
        final Client client = new Client();
        client.setId(getId());
        client.setFirstName(getFirstName());
        client.setLastName(getLastName());
        return client;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
}
