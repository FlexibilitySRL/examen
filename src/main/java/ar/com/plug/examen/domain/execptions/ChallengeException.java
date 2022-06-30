package ar.com.plug.examen.domain.execptions;

import ar.com.plug.examen.domain.dtos.ErrorDTO;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class ChallengeException extends Exception {

    private final String code;
    private final int responseCode;
    private final List<ErrorDTO> errorList = new ArrayList<>();

    public ChallengeException(String code, int responseCode, String message) {
        super(message);
        this.code = code;
        this.responseCode = responseCode;
    }

    public ChallengeException(String code, int responseCode, String message, List<ErrorDTO> errorList) {
        super(message);
        this.code = code;
        this.responseCode = responseCode;
        this.errorList.addAll(errorList);
    }
}
