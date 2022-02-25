package ar.com.plug.examen.domain.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Builder
public class CustomerDTO {
    private long idCustomer;
    private String documentNumber;
    private String name;
    private String lastName;
    private String email;
    private String phone;
}
