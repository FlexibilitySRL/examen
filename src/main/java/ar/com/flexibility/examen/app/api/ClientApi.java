package ar.com.flexibility.examen.app.api;


import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

/**
 * A DTO for the Client entity.
 */
public class ClientApi implements Serializable {

    private Long id;

    @NotNull
    @Size(min = 3)
    private String name;

    @NotNull
    @Size(min = 2)
    private String lastname;

    @NotNull
    @Size(min = 2)
    private String username;

    @NotNull
    private LocalDate createdAt;

    private Long userId;

    private String userLogin;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public LocalDate getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDate createdAt) {
        this.createdAt = createdAt;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUserLogin() {
        return userLogin;
    }

    public void setUserLogin(String userLogin) {
        this.userLogin = userLogin;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        ClientApi clientApi = (ClientApi) o;
        if(clientApi.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), clientApi.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "ClientApi{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", lastname='" + getLastname() + "'" +
            ", username='" + getUsername() + "'" +
            ", createdAt='" + getCreatedAt() + "'" +
            "}";
    }
}
