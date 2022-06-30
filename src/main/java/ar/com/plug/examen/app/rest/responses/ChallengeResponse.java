package ar.com.plug.examen.app.rest.responses;

import lombok.Data;

@Data
public class ChallengeResponse<T>{

    private String status;
    private String code;
    private String message;
    private T data;

    public ChallengeResponse(String status, String code, String message, T data) {
        this.status = status;
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public ChallengeResponse(String status, String code, String message) {
        this.status = status;
        this.code = code;
        this.message = message;
    }
}
