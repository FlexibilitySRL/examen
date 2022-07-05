package ar.com.plug.examen.app.api;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.validation.Valid;
import javax.validation.constraints.*;

import ar.com.plug.examen.app.common.ValidationMessages;
import ar.com.plug.examen.app.common.RegexPatterns;

@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@EqualsAndHashCode(callSuper = false)
public class ClientDTO {

    @JsonProperty(value = "client_id")
    private Long clientId;

    @Valid
    @NotNull(message = "client_data" + ValidationMessages.NOT_BLANK_NULL_NOR_EMPTY)
    @JsonProperty("client_data")
    private PersonalDataDTO personalData;

    @NotEmpty(message = "phone_number" + ValidationMessages.NOT_BLANK_NULL_NOR_EMPTY)
    @Pattern(regexp = RegexPatterns.REGEX_PHONE_NUMBER, message = "phone_number " + ValidationMessages.PHONE_NUMBER_VALID_VALUES)
    @JsonProperty("phone_number")
    private String phoneNumber;

    @NotEmpty(message = "email" + ValidationMessages.NOT_BLANK_NULL_NOR_EMPTY)
    @Pattern(regexp = RegexPatterns.REGEX_EMAIL, message = ValidationMessages.EMAIL_VALID_FORMAT)
    @JsonProperty("email")
    private String email;

}