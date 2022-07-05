package ar.com.plug.examen.app.api;

import ar.com.plug.examen.app.common.ValidationMessages;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.*;

@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Data
@Getter
@Setter
@ToString
@EqualsAndHashCode(callSuper = false)
@JsonIgnoreProperties(ignoreUnknown = true)
public class PersonalDataDTO {


    @NotNull(message = "document_number" + ValidationMessages.NOT_BLANK_NULL_NOR_EMPTY)
    @Range(min = 1, max = 99999999, message = "document_number " + ValidationMessages.DOCUMENT_NUMBER_VALID_VALUES)
    @JsonProperty(value = "document_number")
    private Integer document_number;

    @NotEmpty(message = "first_name" + ValidationMessages.NOT_BLANK_NULL_NOR_EMPTY)
    @Size(min = 1, max = 30, message = "first_name" + ValidationMessages.SIZE_MIN_AND_MAX)
    @JsonProperty(value = "first_name")
    private String first_name;

    @NotEmpty(message = "last_name" + ValidationMessages.NOT_BLANK_NULL_NOR_EMPTY)
    @Size(min = 1, max = 30, message = "last_name" + ValidationMessages.SIZE_MIN_AND_MAX)
    @JsonProperty(value = "last_name")
    private String last_name;
}
