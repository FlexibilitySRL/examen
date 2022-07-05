package ar.com.plug.examen.app.dtoResponse;

import com.fasterxml.jackson.annotation.JsonProperty;
import ar.com.plug.examen.app.enumeration.ErrorCodeEnumeration;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class ErrorDTO {

    @JsonProperty("status")
    private String status;

    @JsonProperty("code")
    private String code;

    @JsonProperty("title")
    private String title;

    public static ErrorDTO map(final ErrorCodeEnumeration errorCodeEnumeration) {
        return ErrorDTO.builder().code(errorCodeEnumeration.getCode()).status(errorCodeEnumeration.getStatus())
                .title(errorCodeEnumeration.getMessage()).build();
    }
}