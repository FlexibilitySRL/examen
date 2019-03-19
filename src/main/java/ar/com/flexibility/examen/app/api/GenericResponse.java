package ar.com.flexibility.examen.app.api;

public class GenericResponse {

    private Boolean success;

    public GenericResponse(Boolean success) {
        this.success = success;
    }

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }
}
