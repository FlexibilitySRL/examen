package ar.com.flexibility.examen.domain.service.dto;

public class ExceptionDTO {

    private String description;

    public ExceptionDTO(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
