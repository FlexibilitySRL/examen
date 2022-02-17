package ar.com.plug.examen.domain.dto;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.Column;
import java.time.LocalDateTime;

@Setter
@Getter
public class SellerDTO {
    private long idSeller;
    private String name;
    private String lastName;
    private String email;
    private String phone;
    private String documentNumber;
    private LocalDateTime createDate;
    private LocalDateTime updateDate;
}
