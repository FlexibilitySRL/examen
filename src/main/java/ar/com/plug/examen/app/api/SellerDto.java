package ar.com.plug.examen.app.api;

public class SellerDto {
    private Long id;

    private String name;

    private Boolean active;

    public SellerDto() {
    }

    public SellerDto(Long id, String description, Boolean active) {
        this.id = id;
        this.name = description;
        this.active = active;
    }

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

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }
}
